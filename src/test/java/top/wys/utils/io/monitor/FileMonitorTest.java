package top.wys.utils.io.monitor;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import top.wys.utils.io.monitor.impl.DelayMonitor;

import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

/**
 * {@link FileMonitor} 集成测试。
 *
 * <p>测试通过真实的临时目录和文件触发操作系统文件事件，避免使用模拟事件导致测试与实际行为脱节。</p>
 */
public class FileMonitorTest {

    private static final Logger log = LoggerFactory.getLogger(FileMonitorTest.class);

    /** 文件事件最长等待时间，避免异步监听测试无限阻塞。 */
    private static final long EVENT_TIMEOUT_SECONDS = 3L;

    /**
     * 验证目录监听器能够收到文件创建、修改和删除事件。
     */
    @Test
    public void shouldReceiveCreateModifyAndDeleteEvents() throws Exception {
        Path directory = Files.createTempDirectory("file-monitor-");
        FileMonitor monitor = null;
        RecordingMonitor recordingMonitor = new RecordingMonitor();
        Path file = directory.resolve("sample.txt");

        try {
            log.info("开始测试文件增删改事件，监听目录：{}，目标文件：{}", directory, file);
            monitor = FileMonitor.createAll(directory, recordingMonitor);
            monitor.start();

            // 依次执行文件操作，并等待对应的异步事件到达。
            log.info("创建文件：{}", file);
            Files.createFile(file);
            assertTrue(recordingMonitor.created.await(EVENT_TIMEOUT_SECONDS, TimeUnit.SECONDS));

            log.info("修改文件：{}", file);
            Files.write(file, "updated".getBytes(StandardCharsets.UTF_8), StandardOpenOption.WRITE);
            assertTrue(recordingMonitor.modified.await(EVENT_TIMEOUT_SECONDS, TimeUnit.SECONDS));

            log.info("删除文件：{}", file);
            Files.delete(file);
            assertTrue(recordingMonitor.deleted.await(EVENT_TIMEOUT_SECONDS, TimeUnit.SECONDS));
            assertTrue(recordingMonitor.createCount.get() >= 1);
            assertTrue(recordingMonitor.modifyCount.get() >= 1);
            assertEquals(1, recordingMonitor.deleteCount.get());
        } finally {
            closeMonitor(monitor);
            Files.deleteIfExists(file);
            Files.deleteIfExists(directory);
        }
    }

    /**
     * 验证单文件监听不会把同目录下其他文件的修改事件转发给观察者。
     */
    @Test
    public void singleFileMonitor_shouldIgnoreEventsFromOtherFiles() throws Exception {
        Path directory = Files.createTempDirectory("single-file-monitor-");
        Path target = directory.resolve("target.txt");
        Path other = directory.resolve("other.txt");
        FileMonitor monitor = null;
        RecordingMonitor recordingMonitor = new RecordingMonitor();

        try {
            Files.write(target, "target".getBytes(StandardCharsets.UTF_8));
            Files.write(other, "other".getBytes(StandardCharsets.UTF_8));
            monitor = FileMonitor.create(target, FileMonitor.ENTRY_MODIFY).setWatcher(recordingMonitor);
            monitor.start();
            log.info("开始修改其他文件：{}", other);
            Files.write(other, "changed".getBytes(StandardCharsets.UTF_8), StandardOpenOption.WRITE);
            log.info("修改文件 {} 结束，等待文件修改事件,预期无法收到该事件", other);
            Thread.sleep(250L);
            assertEquals(0, recordingMonitor.modifyCount.get());

            log.info("开始修改文件：{}", target);
            Files.write(target, "changed".getBytes(StandardCharsets.UTF_8), StandardOpenOption.WRITE);
            log.info("修改文件 {} 结束，等待文件修改事件,应该能收到该事件", target);
            assertTrue(recordingMonitor.modified.await(EVENT_TIMEOUT_SECONDS, TimeUnit.SECONDS));
            assertTrue(recordingMonitor.modifyCount.get() >= 1);
        } finally {
            closeMonitor(monitor);
            Files.deleteIfExists(target);
            Files.deleteIfExists(other);
            Files.deleteIfExists(directory);
        }
    }

    public static void main(String[] args) throws Exception {
        new FileMonitorTest().recursiveMonitor_shouldWatchDirectoriesCreatedAfterStartup();
    }
    /**
     * 验证递归监听能够注册启动后创建的子目录，并继续监听子目录中的文件。
     */
    @Test
    public void recursiveMonitor_shouldWatchDirectoriesCreatedAfterStartup() throws Exception {
        Path directory = Files.createTempDirectory("recursive-file-monitor-");
        Path childDirectory = directory.resolve("child");
        Path file = childDirectory.resolve("nested.txt");
        FileMonitor monitor = null;
        RecordingMonitor recordingMonitor = new RecordingMonitor();

        try {
            // 递归监听同时使用延迟观察者，合并同一文件短时间内重复的修改事件。
            monitor = FileMonitor.create(directory, 2, FileMonitor.EVENTS_ALL)
                    .setWatcher(new DelayMonitor(recordingMonitor, 200L));
            monitor.start();

            Files.createDirectory(childDirectory);
            assertTrue(recordingMonitor.created.await(EVENT_TIMEOUT_SECONDS, TimeUnit.SECONDS));

            Files.createFile(file);
            Files.write(file, "nested".getBytes(StandardCharsets.UTF_8));
            assertTrue(awaitCount(recordingMonitor.createCount, 2));
            assertTrue(recordingMonitor.modified.await(EVENT_TIMEOUT_SECONDS, TimeUnit.SECONDS));
            assertEquals(1, recordingMonitor.modifyCount.get());
        } finally {
            closeMonitor(monitor);
            Files.deleteIfExists(file);
            Files.deleteIfExists(childDirectory);
            Files.deleteIfExists(directory);
        }
    }

    /**
     * 验证同一文件在短时间内连续修改时，延迟监听器只触发一次修改回调。
     */
    @Test
    public void delayMonitor_shouldMergeRapidModifyEventsForSameFile() throws Exception {
        Path directory = Files.createTempDirectory("delay-file-monitor-");
        Path file = directory.resolve("sample.txt");
        FileMonitor monitor = null;
        RecordingMonitor recordingMonitor = new RecordingMonitor();

        try {
            Files.write(file, "initial".getBytes(StandardCharsets.UTF_8));
            monitor = FileMonitor.createAll(file, recordingMonitor, 200L);
            monitor.start();

            for (int i = 0; i < 5; i++) {
                Files.write(file, ("update-" + i).getBytes(StandardCharsets.UTF_8), StandardOpenOption.WRITE);
                Thread.sleep(20L);
            }

            assertTrue(recordingMonitor.modified.await(EVENT_TIMEOUT_SECONDS, TimeUnit.SECONDS));
            Thread.sleep(300L);
            assertEquals(1, recordingMonitor.modifyCount.get());
        } finally {
            closeMonitor(monitor);
            Files.deleteIfExists(file);
            Files.deleteIfExists(directory);
        }
    }

    /**
     * 在指定时间内等待计数器达到目标值。
     *
     * @param count    事件计数器
     * @param expected 期望的最小事件数量
     * @return 是否在超时前达到目标值
     */
    private static boolean awaitCount(AtomicInteger count, int expected) throws InterruptedException {
        long deadline = System.nanoTime() + TimeUnit.SECONDS.toNanos(EVENT_TIMEOUT_SECONDS);
        while (count.get() < expected && System.nanoTime() < deadline) {
            Thread.sleep(20L);
        }
        return count.get() >= expected;
    }

    /**
     * 关闭监听线程并确认线程已经退出，防止测试留下后台线程或打开的 WatchService。
     */
    private static void closeMonitor(FileMonitor monitor) throws InterruptedException {
        if (monitor != null) {
            monitor.close();
            monitor.join(2_000L);
            assertFalse(monitor.isAlive());
        }
    }

    /**
     * 记录监听回调次数，并通过 CountDownLatch 为异步测试提供等待信号。
     */
    private static final class RecordingMonitor extends BaseMonitor {
        /** 创建事件等待信号。 */
        private final CountDownLatch created = new CountDownLatch(1);
        /** 修改事件等待信号。 */
        private final CountDownLatch modified = new CountDownLatch(1);
        /** 删除事件等待信号。 */
        private final CountDownLatch deleted = new CountDownLatch(1);
        /** 各类事件的累计次数。 */
        private final AtomicInteger createCount = new AtomicInteger();
        private final AtomicInteger modifyCount = new AtomicInteger();
        private final AtomicInteger deleteCount = new AtomicInteger();

        @Override
        public void onCreate(java.nio.file.WatchEvent<?> event, Path currentPath) {
            log.info("收到文件创建事件：kind={}, currentPath={}, context={}",
                    event.kind(), currentPath, event.context());
            // 计数和释放等待线程必须先后执行，确保断言看到最新计数。
            createCount.incrementAndGet();
            created.countDown();
        }

        @Override
        public void onModify(java.nio.file.WatchEvent<?> event, Path currentPath) {
            log.info("收到文件修改事件：kind={}, currentPath={}, context={}",
                    event.kind(), currentPath, event.context());
            modifyCount.incrementAndGet();
            modified.countDown();
        }

        @Override
        public void onDelete(java.nio.file.WatchEvent<?> event, Path currentPath) {
            log.info("收到文件删除事件：kind={}, currentPath={}, context={}",
                    event.kind(), currentPath, event.context());
            deleteCount.incrementAndGet();
            deleted.countDown();
        }
    }
}

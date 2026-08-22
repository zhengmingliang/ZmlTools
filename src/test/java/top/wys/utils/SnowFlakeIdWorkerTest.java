package top.wys.utils;

import org.junit.Test;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

/**
 * {@link SnowFlakeIdWorker} 单元测试。
 */
public class SnowFlakeIdWorkerTest {

    private static final long EPOCH = 1288834974657L;
    private static final long WORKER_ID_MASK = 0x1FL;
    private static final long DATACENTER_ID_MASK = 0x1FL;
    private static final long SEQUENCE_MASK = 0xFFFL;

    @Test
    public void singleton_shouldBeInitializedWithValidNodeIds() {
        assertNotNull(SnowFlakeIdWorker.INSTANCE);
        assertTrue(isValidNodeId(SnowFlakeIdWorker.INSTANCE.getWorkerId()));
        assertTrue(isValidNodeId(SnowFlakeIdWorker.INSTANCE.getDatacenterId()));
    }

    @Test
    public void constructor_shouldKeepWorkerAndDatacenterIds() {
        SnowFlakeIdWorker worker = new SnowFlakeIdWorker(3, 7, 0);

        assertEquals(3, worker.getWorkerId());
        assertEquals(7, worker.getDatacenterId());
    }

    @Test
    public void constructor_shouldRejectWorkerIdOutsideSupportedRange() {
        assertConstructorFails(-1, 0);
        assertConstructorFails(32, 0);
    }

    @Test
    public void constructor_shouldRejectDatacenterIdOutsideSupportedRange() {
        assertConstructorFails(0, -1);
        assertConstructorFails(0, 32);
    }

    @Test
    public void nextId_shouldContainConfiguredNodeIdsAndCurrentTimestamp() {
        SnowFlakeIdWorker worker = new SnowFlakeIdWorker(3, 7, 0);
        long before = System.currentTimeMillis();

        long id = worker.nextId();

        long after = System.currentTimeMillis();
        long timestamp = (id >>> 22) + EPOCH;
        assertTrue(id > 0);
        assertEquals(3, (id >>> 12) & WORKER_ID_MASK);
        assertEquals(7, (id >>> 17) & DATACENTER_ID_MASK);
        assertEquals(0, id & SEQUENCE_MASK);
        assertTrue(timestamp >= before);
        assertTrue(timestamp <= after);
    }

    @Test
    public void nextId_shouldBeStrictlyIncreasingAndUnique() {
        SnowFlakeIdWorker worker = new SnowFlakeIdWorker(1, 1, 0);
        Set<Long> ids = new HashSet<>();
        long previous = -1L;

        for (int i = 0; i < 1_000; i++) {
            long id = worker.nextId();

            assertTrue(id > previous);
            assertTrue(ids.add(id));
            previous = id;
        }

        assertEquals(1_000, ids.size());
    }

    @Test
    public void nextStringId_shouldReturnDecimalRepresentationOfNextId() {
        SnowFlakeIdWorker worker = new SnowFlakeIdWorker(1, 1, 0);

        String id = worker.nextStringId();

        assertNotNull(id);
        assertFalse(id.isEmpty());
        assertEquals(Long.toString(Long.parseLong(id)), id);
    }

    @Test
    public void getTimestamp_shouldReturnCurrentTime() {
        long before = System.currentTimeMillis();

        long timestamp = SnowFlakeIdWorker.INSTANCE.getTimestamp();

        long after = System.currentTimeMillis();
        assertTrue(timestamp >= before);
        assertTrue(timestamp <= after);
    }

    @Test
    public void getDatacenterId_shouldReturnIdWithinRequestedRange() {
        long datacenterId = SnowFlakeIdWorker.getDatacenterId(31);

        assertTrue(datacenterId >= 0);
        assertTrue(datacenterId <= 31);
    }

    @Test
    public void nextId_shouldRemainUniqueWhenGeneratedConcurrently() throws Exception {
        final int threadCount = 8;
        final int idsPerThread = 250;
        final SnowFlakeIdWorker worker = new SnowFlakeIdWorker(2, 2, 0);
        final Set<Long> ids = ConcurrentHashMap.newKeySet();
        final CountDownLatch ready = new CountDownLatch(threadCount);
        final CountDownLatch start = new CountDownLatch(1);
        ExecutorService executor = Executors.newFixedThreadPool(threadCount);
        List<Future<?>> futures = new ArrayList<>(threadCount);

        try {
            for (int i = 0; i < threadCount; i++) {
                futures.add(executor.submit(() -> {
                    ready.countDown();
                    start.await();
                    for (int j = 0; j < idsPerThread; j++) {
                        ids.add(worker.nextId());
                    }
                    return null;
                }));
            }

            ready.await();
            start.countDown();
            for (Future<?> future : futures) {
                future.get();
            }
        } finally {
            executor.shutdownNow();
        }

        assertEquals(threadCount * idsPerThread, ids.size());
    }

    private static boolean isValidNodeId(long id) {
        return id >= 0 && id <= 31;
    }

    private static void assertConstructorFails(long workerId, long datacenterId) {
        try {
            new SnowFlakeIdWorker(workerId, datacenterId, 0);
            fail("Expected IllegalArgumentException");
        } catch (IllegalArgumentException expected) {
            // 预期异常。
        }
    }
}

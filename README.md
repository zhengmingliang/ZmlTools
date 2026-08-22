# ZmlTools
集成了各种工具类，包含数据类型转换、系统参数获取、JSON实体转换、文件操作、文件编码、字符串常用工具、反射常用方法等

[![Maven Central](https://img.shields.io/maven-central/v/top.wuyongshi/ZmlTools?style=flat-square)](https://search.maven.org/artifact/top.wuyongshi/ZmlTools)
[![License](https://img.shields.io/badge/license-Apache%202-4EB1BA.svg)](https://www.apache.org/licenses/LICENSE-2.0.html)

## 环境要求
* JDK 1.8 及以上

## 依赖方式
### maven 集成方式
```xml
<!-- https://mvnrepository.com/artifact/top.wuyongshi/ZmlTools -->
<dependency>
    <groupId>top.wuyongshi</groupId>
    <artifactId>ZmlTools</artifactId>
    <version>${release-version}</version>
</dependency>
```

### Gradle集成
```
// https://mvnrepository.com/artifact/top.wuyongshi/ZmlTools
compile group: 'top.wuyongshi', name: 'ZmlTools', version: ${release-version}

```

## 文件监听

文件监听功能位于 `top.wys.utils.io.monitor` 包，基于 JDK `WatchService` 实现，支持监听文件或目录的创建、修改和删除事件。

### 基础用法

`FileMonitor` 是一个线程对象，调用 `start()` 后会在后台线程中监听文件事件。使用结束后必须调用 `close()` 释放 `WatchService` 和监听线程资源。

```java
import top.wys.utils.io.monitor.BaseMonitor;
import top.wys.utils.io.monitor.FileMonitor;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.WatchEvent;

Path directory = Paths.get("/tmp/config");

BaseMonitor watcher = new BaseMonitor() {
    @Override
    public void onCreate(WatchEvent<?> event, Path currentPath) {
        System.out.println("文件创建：" + resolveEventPath(event, currentPath));
    }

    @Override
    public void onModify(WatchEvent<?> event, Path currentPath) {
        System.out.println("文件修改：" + resolveEventPath(event, currentPath));
    }

    @Override
    public void onDelete(WatchEvent<?> event, Path currentPath) {
        System.out.println("文件删除：" + resolveEventPath(event, currentPath));
    }

    private Path resolveEventPath(WatchEvent<?> event, Path currentPath) {
        return currentPath.resolve((Path) event.context());
    }
};

FileMonitor monitor = FileMonitor.createAll(directory, watcher);
monitor.start();

// 使用结束后关闭监听
monitor.close();
```

也可以使用 `setWatcher` 设置观察者：

```java
FileMonitor monitor = FileMonitor.create(
        directory,
        FileMonitor.EVENTS_ALL
).setWatcher(watcher);
monitor.start();
```

### 监听单个文件

监听单个文件时，`FileMonitor` 实际监听该文件所在的目录，并通过文件路径过滤其他文件事件。因此目标文件被删除后重新创建，仍然可以收到创建和删除事件。

```java
Path file = Paths.get("/tmp/config/application.yml");

FileMonitor monitor = FileMonitor.create(
        file,
        FileMonitor.ENTRY_CREATE,
        FileMonitor.ENTRY_MODIFY,
        FileMonitor.ENTRY_DELETE
).setWatcher(watcher);

monitor.start();
```

如果目录中其他文件发生变化，不会触发该单文件监听器的回调。

### 递归监听目录

通过 `maxDepth` 指定递归深度：

- `maxDepth <= 1`：只监听当前目录。
- `maxDepth = 2`：监听当前目录和直接子目录。
- `maxDepth = 3`：监听当前目录和下两层目录。

监听期间新创建的子目录也会根据剩余深度自动注册。

```java
FileMonitor monitor = FileMonitor.create(
        Paths.get("/tmp/project"),
        3,
        FileMonitor.EVENTS_ALL
).setWatcher(watcher);

monitor.start();
```

### 合并重复的修改事件

不同操作系统和编辑器可能会在一次文件保存过程中触发多次 `ENTRY_MODIFY` 事件。直接使用 `FileMonitor` 时，这些事件会按照 `WatchService` 原始结果逐个回调。

如果只希望在文件停止修改一段时间后处理一次，可以使用延迟合并监听器：

```java
FileMonitor monitor = FileMonitor.createAll(
        Paths.get("/tmp/config/application.yml"),
        watcher,
        200L
);

monitor.start();
```

上例表示：同一个文件在 200 毫秒内连续修改时，重新计算延迟时间；文件停止修改 200 毫秒后只触发一次 `onModify` 回调。创建、删除和溢出事件不会延迟。

递归监听和延迟合并可以组合使用：

```java
import top.wys.utils.io.monitor.impl.DelayMonitor;

FileMonitor monitor = FileMonitor.create(
        Paths.get("/tmp/project"),
        3,
        FileMonitor.EVENTS_ALL
).setWatcher(new DelayMonitor(watcher, 200L));

monitor.start();
```

### 事件类型

可以通过 `WatchEvent#kind()` 判断事件类型，也可以直接使用 `FileMonitor` 中提供的常量：

| 常量 | 说明 |
| --- | --- |
| `FileMonitor.ENTRY_CREATE` | 文件或目录创建 |
| `FileMonitor.ENTRY_MODIFY` | 文件或目录修改 |
| `FileMonitor.ENTRY_DELETE` | 文件或目录删除 |
| `FileMonitor.OVERFLOW` | WatchService 事件丢失或溢出 |
| `FileMonitor.EVENTS_ALL` | 监听全部事件 |

回调方法中的 `currentPath` 是产生事件的目录，`event.context()` 是相对于该目录的路径。获取完整路径时可以使用：

```java
Path eventPath = currentPath.resolve((Path) event.context());
```

`OVERFLOW` 事件的上下文不一定是 `Path`，处理溢出事件时不要强制转换 `event.context()`。

### 监听生命周期和注意事项

1. `FileMonitor` 创建时会初始化 `WatchService`，调用 `start()` 或 `watch()` 后开始接收事件。
2. `start()` 用于启动后台监听线程；`watch()` 会阻塞当前线程。
3. 使用结束后调用 `close()`，否则可能留下后台线程或打开的文件监听资源。
4. 监听不存在的路径时，带扩展名的路径会按待创建文件处理并监听其父目录；其他不存在路径会创建为目录并监听。
5. 修改事件是否重复取决于底层文件系统和应用程序的写入方式，需要稳定处理时建议使用 `DelayMonitor`。
6. `MonitorException` 用于包装监听初始化、路径注册和监听过程中的运行时异常。

package top.wys.utils;

import org.junit.Test;

import static org.junit.Assert.*;

public class SnowFlakeIdWorkerTest {

    @Test
    public void nextId() {
        System.out.println(SnowFlakeIdWorker.INSTANCE.nextId());
    }
}
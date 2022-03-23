package cn.isif.demo.utils;

public class Logger {
    public static void debug(String msg) {
        String threadName = Thread.currentThread().getName();
        System.out.println("[" + threadName.toUpperCase() + "] : " + msg);
    }
}

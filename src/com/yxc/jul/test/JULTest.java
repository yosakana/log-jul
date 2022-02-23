package com.yxc.jul.test;

import org.junit.Test;

import java.io.IOException;
import java.util.Formatter;
import java.util.logging.*;

public class JULTest {

    @Test
    public void test01() {
        /*
            JUL的引入包 java.util.logging.Logger;
            日志入口程序

            Logger对象的创建不能直接new出来，通过 Logger.getLogger("本类的全路径")获取
         */
        Logger logger = Logger.getLogger("com.yxc.jul.test.JULTest");

        /*
            输出日志的两种方式（console）
         */
        logger.log(Level.INFO, "输出msg1");
        logger.info("输出msg2");

        /*
            多个参数的日志输出
         */
        String name = "yxc";
        int age = 21;
        logger.log(Level.INFO, "我的名字叫:{0},年龄是:{1}", new Object[]{name, age});
    }

    @Test
    public void test2() {
        /*
            日志级别:
                SEVERE : 最高级别的错误 100
                WARNING : 警告 900
                INFO : 一般信息 800
                CONFIG : 配置 700
                FINE : 详细信息 （少） 500
                FINER : 详细信息 （中） 400
                FINEST : 详细信息 （多）300

            两个特殊级别:
                OFF(MAX_VALUE):关闭日志记录
                ALL(MIN_VALUE):开启所有消息的日志记录

            选择低点数的输出等级，则只会输出自己跟比自己点数高的信息

            默认状态下只会输出到默认等级info
         */
        Logger logger = Logger.getLogger("com.yxc.jul.test.JULTest");
        logger.severe("server");
        logger.warning("warning");
        logger.info("info");
        logger.config("config");
        logger.fine("fine");
        logger.finer("finer");
        logger.finest("finest");
    }

    @Test
    public void test3() {
        /*
            自定义日志等级
        */
        Logger logger = Logger.getLogger("com.yxc.jul.test.JULTest");
        //1。关闭默认的打印方式（关闭父logger默认的打印方式）
        logger.setUseParentHandlers(false);

        //2.创建console handler
        ConsoleHandler consoleHandler = new ConsoleHandler();
        //3.创建日志格式化组件对象
        SimpleFormatter simpleFormatter = new SimpleFormatter();
        //4.给处理器设置输出格式
        consoleHandler.setFormatter(simpleFormatter);
        //5.给日志对象设置处理器
        logger.addHandler(consoleHandler);

        //logger和handler都需要设置等级
        logger.setLevel(Level.FINER);
        consoleHandler.setLevel(Level.FINER);

        logger.severe("server");
        logger.warning("warning");
        logger.info("info");
        logger.config("config");
        logger.fine("fine");
        logger.finer("finer");
        logger.finest("finest");
    }

    @Test
    public void test4() throws IOException {
        Logger logger = Logger.getLogger("com.yxc.jul.test.JULTest");
        //          磁盘路径 + 即将输出的文件名(一定要是.log后缀名)
        FileHandler fileHandler = new FileHandler("F:\\LOG\\myTestLog.log");
        SimpleFormatter simpleFormatter = new SimpleFormatter();
        fileHandler.setFormatter(simpleFormatter);
        logger.addHandler(fileHandler);

        ConsoleHandler consoleHandler = new ConsoleHandler();
        logger.addHandler(consoleHandler);

        logger.setLevel(Level.WARNING);
        consoleHandler.setLevel(Level.WARNING);

        logger.severe("server");
        logger.warning("warning");
        logger.info("info");
    }

    @Test
    public void test5(){
        // 父类logger的父类 是最高Logger RootLogger
        Logger logger1 = Logger.getLogger("com.yxc.jul.test");
        // 子类logger则是具体到类
        Logger logger2 = Logger.getLogger("com.yxc.jul.test.JULTest");

        System.out.println(logger2.getParent().getName());

        //父类Logger的设置会作用到子类logger上
        logger1.setUseParentHandlers(false);
        ConsoleHandler consoleHandler = new ConsoleHandler();
        SimpleFormatter simpleFormatter = new SimpleFormatter();
        consoleHandler.setFormatter(simpleFormatter);
        logger1.addHandler(consoleHandler);

        logger1.setLevel(Level.WARNING);
        consoleHandler.setLevel(Level.WARNING);

        logger2.severe("server");
        logger2.warning("warning");
        logger2.info("info");
    }
}

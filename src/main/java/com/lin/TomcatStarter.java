package com.lin;

import com.lin.tomcat.MyTomcat;

/**
 * @author lkmc2
 * @date 2018/9/18
 * @description 自定义Tomcat启动器
 */
public class TomcatStarter {

    public static void main(String[] args) {
        new MyTomcat().start();
    }

}

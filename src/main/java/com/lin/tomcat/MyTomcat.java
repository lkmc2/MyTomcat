package com.lin.tomcat;

import com.lin.servlet.Servlet;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

/**
 * @author lkmc2
 * @date 2018/9/18
 * @description 自定义Tomcat
 */
public class MyTomcat {

    // 监听端口号
    private static final int PORT = 8888;

    // 属性配置
    private static final Properties PROPERTIES = new Properties();

    // Servlet映射Map
    private static final Map<String, Servlet> SERVLET_MAP = new HashMap<>();

    /**
     * 初始化
     */
    private void init() {
        // 输入流
        InputStream inputStream = null;

        // 项目资源路径
        String basePath;

        try {
            basePath = MyTomcat.class.getResource("/").toURI().getPath();
            System.out.println("项目资源路径：" + basePath);

            // 获取web配置文件流
            inputStream = new FileInputStream(basePath + "web.properties");

            // 加载配置
            PROPERTIES.load(inputStream);

            // 保存配置到Servlet映射Map中
            for (Object key : PROPERTIES.keySet()) {
                // key中包含url单词
                if (key.toString().contains("url")) {
                    // 获取请求网址和对应的Servlet类名的key值
                    String urlKey = key.toString();
                    String classNameKey = urlKey.replace("url", "classname");

                    // 获取请求网址和对应的Servlet类名
                    String url = PROPERTIES.getProperty(urlKey);
                    String className = PROPERTIES.getProperty(classNameKey);

                    // 将请求网址和Servlet的关系存入Map
                    SERVLET_MAP.put(url, (Servlet) Class.forName(className).newInstance());
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (inputStream != null) {
                try {
                    inputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    /**
     * 启动Tomcat进行监听
     */
    public void start() {
        // 初始化Tomcat
        init();

        try {
            // 启动Socket服务器，监听8888端口
            ServerSocket serverSocket = new ServerSocket(PORT);

            do {
                // 获取监听到的客户端
                Socket socket = serverSocket.accept();
                System.out.println(socket);

                // 启动线程处理客户端任务
                Thread thread = new SocketProcess(socket);
                thread.start();
            } while (true);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * 获取Servlet映射关系Map
     */
    public static Map<String, Servlet> getServletMap() {
        return SERVLET_MAP;
    }

}

package com.lin.tomcat;

import com.lin.http.Request;
import com.lin.http.Response;
import com.lin.servlet.ErrorServlet;
import com.lin.servlet.Servlet;

import java.io.IOException;
import java.net.Socket;

/**
 * @author lkmc2
 * @date 2018/9/18
 * @description 客户端Socket处理类
 */
public class SocketProcess extends Thread {

    // 网络连接
    private Socket socket;

    public SocketProcess(Socket socket) {
        this.socket = socket;
    }

    @Override
    public void run() {
        try {
            // 获取网络连接的请求和响应
            Request request = new Request(socket.getInputStream());
            Response response = new Response(socket.getOutputStream());

            // 根据请求的网址获取对应的Servlet
            Servlet servlet = MyTomcat.getServletMap().get(request.getUrl());

            // Servlet为空
            if (servlet == null) {
                // 创建用来处理异常的Servlet
                servlet = new ErrorServlet();
            }

            // 处理客户端的请求
            servlet.service(request, response);
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (socket != null) {
                try {
                    socket.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

}

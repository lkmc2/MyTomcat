package com.lin.servlet;

import com.lin.http.Request;
import com.lin.http.Response;

import java.io.IOException;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;

/**
 * @author lkmc2
 * @date 2018/9/18
 * @description Servlet基类
 */
public abstract class Servlet {

    /**
     * 处理网络请求
     * @param request 请求
     * @param response 响应
     */
    public void service(Request request, Response response) {
        // 判断调用doGet还是doPost方法
        if ("get".equalsIgnoreCase(request.getMethod())) {
            this.doGet(request, response);
        } else {
            this.doPost(request, response);
        }
    }

    /**
     * 处理Get请求
     * @param request 请求
     * @param response 响应
     */
    protected abstract void doGet(Request request, Response response);

    /**
     * 处理Post请求
     * @param request 请求
     * @param response 响应
     */
    protected abstract void doPost(Request request, Response response);

    /**
     * 向浏览器输出文字
     * @param response 响应
     * @param text 输出的文字
     */
    protected void writeText(Response response, String text) {
        // 向客户端输出的内容
        String result = Response.RESPONSE_HEADER + text;

        // 打开输出流，此处可根据需要将编码从GBK改成UTF-8
        try(OutputStream outputStream = response.getOutputStream();
            OutputStreamWriter outputStreamWriter = new OutputStreamWriter(outputStream, "GBK");
            PrintWriter writer = new PrintWriter(outputStreamWriter)) {

            // 向客户端写入返回的数据
            writer.write(result);

            // 刷新数据流的内容
            outputStream.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}

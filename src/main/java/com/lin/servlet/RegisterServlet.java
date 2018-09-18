package com.lin.servlet;

import com.lin.http.Request;
import com.lin.http.Response;

/**
 * @author lkmc2
 * @date 2018/9/18
 * @description 注册Servlet
 */
public class RegisterServlet extends Servlet {

    @Override
    protected void doGet(Request request, Response response) {
        this.doPost(request, response);
    }

    @Override
    protected void doPost(Request request, Response response) {
        String text = "欢迎来到注册页面";

        // 向浏览器输出文字
        writeText(response, text);
    }

}

package com.lin.http;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Arrays;

/**
 * @author lkmc2
 * @date 2018/9/18
 * @description 请求
 */
public class Request {

    // 请求方法
    private String method;

    // 请求网址
    private String url;

    public Request(InputStream inputStream) throws IOException {
        // 打开socket传来的输入流
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));

        // 将读取到的第一行数据按空格拆分成请求方法名和请求网址
        String[] methodAndUrl = bufferedReader.readLine().split(" ");

        // 当系统请求的不是favicon.ico图标时，设置请求方法和网址
        if (!"/favicon.ico".equals(methodAndUrl[2])) {
            this.method = methodAndUrl[0];
            this.url = methodAndUrl[1];
        }
    }

    public String getMethod() {
        return method;
    }

    public String getUrl() {
        return url;
    }

}

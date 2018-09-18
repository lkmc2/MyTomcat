package com.lin.http;

import java.io.OutputStream;

/**
 * @author lkmc2
 * @date 2018/9/18
 * @description 响应
 */
public class Response {

    // 输出流
    private OutputStream outputStream;

    // 响应头
    public static final String RESPONSE_HEADER = "HTTP/1.1 200 \r\n"
            + "Content-Type: text/html\r\n"
            + "\r\n";

    public Response(OutputStream outputStream) {
        this.outputStream = outputStream;
    }

    public OutputStream getOutputStream() {
        return outputStream;
    }

}

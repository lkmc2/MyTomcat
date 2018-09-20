# MyTomcat
一个自定义的Tomcat，可对网络路径请求进行处理。

## 设计思路

1. 读取配置文件的信息，将URL和对应的Servlet存储到Map中。
2. 启动ServerSocket监听8888端口。
3. ServerSocket获取浏览器的连接，并开启线程处理。
4. 线程中获取客户端连接的输入流和输出流，生成Request和Response对象。
5. Request对象中读取输入流，获取请求方式和路径。
6. 线程中根据Request获取的请求路径从Map从获取对应的Servlet，将Request和Response交给Servlet的service方法处理。
7. Servlet的service方法根据Request中的请求方式决定调用doGet()还是doPost()方法。
8. 执行doGet()或doPost()方法后返回数据给浏览器。

## 启动方式

运行TomcatStarter类的main方法。
```java
public class TomcatStarter {

    public static void main(String[] args) {
        new MyTomcat().start();
    }

}
```

## 新建Servlet步骤

1.新建类继承Servlet类，并重写doGet和doPost方法。
```java
public class LoginServlet extends Servlet {

    @Override
    protected void doGet(Request request, Response response) {
        this.doPost(request, response);
    }

    @Override
    protected void doPost(Request request, Response response) {
        String text = "欢迎来到登陆页面";

        // 向浏览器输出文字
        writeText(response, text);
    }

}
```

2.打开resources目录下的web.properties文件，将请求路径和Servlet类名按如下形式填写。
```text
# url和对应的Servlet类名需成对出现
servlet.one.url=/login
servlet.one.classname=com.lin.servlet.LoginServlet
```

3.运行TomcatStarter类的main方法，启动自定义Tomcat。

4.浏览器访问网址：http://localhost:8888/login。
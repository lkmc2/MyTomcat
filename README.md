# MyTomcat
一个自定义的Tomcat，可对网络路径请求进行处理。

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

**1.新建类继承Servlet类，并重写doGet和doPost方法。**
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

**2.打开resources目录下的web.properties文件，将请求路径和Servlet类名按如下形式填写。**
```text
# url和对应的Servlet类名需成对出现
servlet.one.url=/login
servlet.one.classname=com.lin.servlet.LoginServlet
```

**3.运行TomcatStarter类的main方法，启动自定义Tomcat。**

**4.浏览器访问网址：http://localhost:8888/login，即可看到对应页面。**
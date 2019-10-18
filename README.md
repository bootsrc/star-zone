# 社交App系统架构-星座空间

Android客户端[https://github.com/liushaoming/star-zone-android](https://github.com/liushaoming/star-zone-android)


目前社交类应用有App，小程序，网站，微信公众号等，其中最普遍有效的客户端还是App。
本人利用空余时间开发了一款Android版App--星座空间（英文名star-zone）
<br/>
<b>如果该项目对您有帮忙，您可以右上角'star'支持一下，谢谢！</b>
<br/>
源码在开源到了github上[https://github.com/liushaoming/star-zone](https://github.com/liushaoming/star-zone)
Android客户端[https://github.com/liushaoming/star-zone-android](https://github.com/liushaoming/star-zone-android)
<br/>

![星座空间图标](doc/image/ic_launcher.png)

 [🚀点我--下载Android客户端](http://resources.appjishu.com/app/star-zone.apk)
<br/>
大家可以下载后用下用用看。

## 代码分析
* [star-zone-back &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; Java后台](/star-zone-back)

* [star-zone-common &nbsp;&nbsp;&nbsp;&nbsp;&nbsp; 公共类库](/star-zone-common)

* [star-zone-manage &nbsp;&nbsp;&nbsp;&nbsp;&nbsp; 管理页面静态项目(做了前后端分离)](/star-zone-manage)

* [star-zone-android &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; Android客户端](https://github.com/liushaoming/star-zone-android)

## 技术选型
下面列举技术栈，并说明选择的原因:
软件分为以下几块：（全部都是我一个人开发的，很辛苦的^_^，全栈工程师+架构师）
1.Android
采用原生Android开发

很多人以为用React Native开发App就不需要处理兼容性问题。其实使用React Native开发，也会遇到类似于原生开发里的gradle jar包依赖的冲突的问题。 而且，比如需要用到一些第三方厂商的硬件驱动程序jni等，或者原生android的库。 这种情况下React Native来调用这些库，就异常艰难。以国际大厂Airbnb为例，该公司曾是比较早采用React Native来在生产环境开发App的公司，极力推进React Native。结果后来还是放弃了。
Airbnb 在 Medium 上发博文宣布，“由于许多技术上和组织上的问题，我们决定放弃 React Native，将所有精力投入到原生应用上。”
另外一个原因：我是一个Java架构师，对Java语言比较熟悉，也会Android开发，于是就采用了原生开发

2.后台服务
基于Java，Spring Boot, Spring Cloud
数据库MySQL
缓存Redis

3.后台管理网站（纯静态网站）
做了前后端分离，动态和静态分离，静态网站放在nginx上，可以应对高并发访问
前端使用ES6语法，
框架采用vue.js,
插件使用babel, 
样式使用element-ui

4.App的推广官网（纯静态网站）
做了前后端分离，动态和静态分离，静态网站放在nginx上，可以应对高并发访问

##  总体架构

1. 后台总体架构
Android通过网络，到达服务器后，经过nginx反向代理到后台服务。
调用后台接口的时候， 一部分接口是进行了权限验证的，权限验证使用userId+token
参考我的另外一个框架[fpassport](https://github.com/liushaoming/fpassport)
<br/> 点击后可以访问github <br/>
其中使用了Redis存放userId和token的对应关系。提高了系统的响应速度。
由于app里后面会加上电商功能，对于下单功能。订单数量是商品数量的很多倍。
采用了分表的架构。分表原理采用一致性Hash。

2. 微服务采用了Spring Cloud
Spring Cloud是采用HTTP协议， Dubbo采用RPC方案，采用TCP协议通信。
性能方面Spring Cloud略差一点。 但是影响不大。两种框架各有利弊。我采用了自己熟悉的Spring Cloud

3. 数据库分表操作
基于一致性hash，见github源码

4. 前后端分离，动静分离
Java动态网站比较吃内存，操作系统允许的线程数也是一定的。 最多几千个线程。
而且一个线程一般就要占用512KB的内存。 光这些HTTP连接就至少占用1G内存。
还有其他的CPU计算，也要占用1G以上的内存。 

动静分离后。 页面的每次请求不需要经过后端渲染，减少了Java Http连接数，减小了内存开销。
nginx部署静态网站后。不做优化，nginx本身就能承受上万个并发连接，很适合做静态资源的服务。

5. 推送服务,即时通讯
使用了小米的推送服务，我自己也自主研发了一个推送服务基于netty，项目地址
[ttps://github.com/liushaoming/fpush](https://github.com/liushaoming/fpush)

5.前端方案
vue，双向数据绑定，提高编程效率。
element-ui界面简洁，使用简单，适合后台管理系统开发，跟vue结合有天然的便利。
有饿了么公司强大后盾。
使用es6或者js都行。

上班比较忙。暂时写这么多。
本文后面还会继续编辑更新。把更多内容分享给大家。

项目地址是[星座空间App](http://resources.appjishu.com/app/star-zone.apk)

源码在开源到了github上[https://github.com/liushaoming/star-zone](https://github.com/liushaoming/star-zone)
Android客户端[https://github.com/liushaoming/star-zone-android](https://github.com/liushaoming/star-zone-android)
<br/>
<b>如果该项目对您有帮忙，您可以右上角'star'支持一下，谢谢！</b>
<br/>

## 小米推送的jar包 为什么maven编译的时候会找不到?

比如下面两个jar MiPush-Server.jar, MiPush-json-simple.jar依赖会找不到

```xml
<dependency>
    <groupId>com.xiaomi</groupId>
    <artifactId>MiPush-Server</artifactId>
    <version>2.2.19</version>
</dependency>
<dependency>
    <groupId>com.xiaomi</groupId>
    <artifactId>MiPush-json-simple</artifactId>
    <version>1.1.1</version>
</dependency>
```

解决办法：

这个是需要本地jar instal到本地maven仓库的。具体操作如下
```text
添加本地jar到maven springboot项目中

maven项目中，如果想引用第三方提供的，或者自己的本地jar包。  是非常困难的事情。

因为这些jar没有groupId， artifactId， maven命令打包无法引用。
解决思路：
用mvn install:install-file命令把本地的jar安装到local maven repository。 然后就可以在pom里像普通的maven引用来引用这个jar了
实例如下：

step1 首先把本地的jar都放到项目根路径下新建的lib中去

step2，命令行进入lib路径

mvn install:install-file -DgroupId=com.xiaomi -DartifactId=MiPush-Server -Dversion=2.2.19 -Dpackaging=jar -Dfile=MiPush-Server-2.2.19.jar

mvn install:install-file -DgroupId=com.xiaomi -DartifactId=MiPush-json-simple -Dversion=1.1.1 -Dpackaging=jar -Dfile=MiPush-json-simple-1.1.1.jar


这这两个jar包都install到local repository了。 
然后，在自己的springboot项目里。 就可以直接添加dependency就可以了 

<dependency>
    <groupId>com.xiaomi</groupId>
    <artifactId>MiPush-Server</artifactId>
    <version>2.2.19</version>
</dependency>
<dependency>
    <groupId>com.xiaomi</groupId>
    <artifactId>MiPush-json-simple</artifactId>
    <version>1.1.1</version>
</dependency>


groupId 和artifactId和version都是我自己创建制定的。 并非官方给的

```
注意这里MiPush的jar包官方在不断升级。version自己可以改成最新的，比如2.2.21
在本项目里的MiPush_SDK_Server.jar路径为
[MiPush_SDK_Server_2_2_21.jar](/sdk/MiPush_Server_Java_20190420/java/sdk/MiPush_SDK_Server_2_2_21.jar)

[json-simple-1.1.1.jar](/sdk/MiPush_Server_Java_20190420/java/sdk/json-simple-1.1.1.jar)

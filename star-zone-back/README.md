#ShopServiceApp

## 调试步骤

1. 本地安装MySQL，MongoDB <br/>
注意MongoDB用户名和密码均不要设置
开发及其上安装 Jdk9， MySQL，新建库'mall'

2. 添加分表设置 <br/>
用户订单order表   <br/>
按照mall-service/doc/order_0.sql   <br/>
新建表order_0 -- order_3 共4张表   <br/>

3. 启动fly-dbproxy项目（作为数据库中间件运行）
并用浏览器访问
<a href="http://localhost:10010/shard/add?name=order&count=4">http://localhost:10010/shard/add?name=order&count=4</a>   <br/>
来在MongoDB中新建分配配置元数据
<br/>

4. 启动mall-shop-service这个后台RestAPI
<br?>
下面是一些测试链接
<br/>
http://localhost:10100/test
<br/>
插入1条测试数据到订单表中去
<br/>
http://localhost:10100/order/test
查看订单列表
http://localhost:10100/order/list?userId=1&page=1&limit=10
<br/>
测试添加商品数据
http://localhost:10100/goods/test
<br/>
获取商品详情列表<br/>
http://localhost:10100/goods/listByPage?page=1&limit=10
<br/>
获取商品简略数据列表<br/>
http://localhost:10100/goods/listBase?page=1&limit=10
<br/>
http://192.168.0.102:10100/pay-monitor/notify

短信验证码的返回结果demo
{"result":0,"errmsg":"OK","ext":"","sid":"8:TlkNhy1WDFJUBIdHWCr20180523","fee":1}



//
app调用流程
1. 
http://localhost:10100/captcha/fetchCaptcha?mobile=15601623391

2.

http://localhost:10100/passport/register?mobile=15601623391&captcha=1111&password=333333



http://localhost:10100/user/topic/byPage?page=1&limit=10





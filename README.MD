# GOLD-GAME

## 简介

该项目是基于Spring MVC + MyBatis + Maven搭建的项目
## 启动项目
首先需要根据**db.sql**创建数据库
数据库连接方式
```
driver=com.mysql.jdbc.Driver
url=jdbc:mysql://127.0.0.1:3306/user_info
username=root
password=root
```
根据实际情况修改**/src/main/resoures/jdbc.properties**即可 
直接运行**gold-game.bat**或者直接运行**mvn jetty:run**即可
## API调用方式
- 添加用户和金币
```
curl -X POST -d "user_id=1&coins=10" "http://localhost:8080/gold-game/user/add"
```
- 给定用户ID, 返回当前金币金额

```
curl -X GET http://localhost:8080/gold-game/coins/user/1
```

- 给定user_id1, user_id2, 金额n, 完成从user1 向user2 的账户转账n金币
```
curl -X POST -d "from_user_id=1&to_user_id=2&coins=500" "http://localhost:8080/transaction/transfer"
```
## 单测覆盖率

运行 **mvn cobertura:cobertura** 即可
在target目录下面 **target/site/cobertura/index.html**
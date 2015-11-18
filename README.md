##简介
1.  根据需求描述，该项目采用简单的MVP模型进行架构模型设计，模式倾向DDD领域驱动模型的充血模型（目前给出的代码实现并没有完全完成这个设计，
时间有限应暂还是用的mvc模式，留由下个版本迭代）
2.  工程中，controller负责完成消息的路由功能，service负责对针对每个业务进行实际的操作，在项目中model对象和dao层合并，
业务层不在直接调用dao层，而是通过model的状态改变直接作用于数据层，有model来维护实体与数据库之间的状态同步。

##数据库准备环境:
1.  工程标配了一个云服务器的 mysql 和 redis(之后开通) 用于测试。
2.  可自行准备 mysql 和 redis 库，【本例中创建的数据库名称为test，配置在application.properties中，建表脚本在工程中user.sql】

##运行
1.  在工程下使用命令 mvn package 打jar包
2.  进入tager目录，使用命令 java -jar【包名】启动工程
3.  通过浏览器或curl命令访问测试

##配置文件说明
1.  resources为资源配置文件。
2.  主要的配置文件为application.properties，这个文件在启动时候会被自动加载，
    很多相关配置都可以配置在里面（本例中放置了关于数据库的配置，spring mvc的配置）
3.  UserMapper.xml为mybatis的配置文件。

##待完善
1.  junit的完善
2.  redis缓存的完善
3.  统一日志机制完善
4.  统一异常机制完善

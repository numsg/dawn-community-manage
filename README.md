# Java 服务端种子工程结构约定和示例

> 1. [如何使用模型验证](docs/how-to-use-model-validate.markdown)
> 2. [spring正确使用事务](docs/how-to-use-transactional.markdown)
> 3. [如何正确使用http码](docs/how-to-use-http-status-code.markdown)
> 4. [分布式事务处理](docs/distributed-transactional.markdown)
> 5. [集成OData](https://github.com/numsg/numsg-odata#31-%E4%B8%8Espring-boot-seed%E9%9B%86%E6%88%90)
> 6. [上传jar包到中央仓库](docs/publish-release-jar.markdown)

## 一、介绍
这个过程结构主要是解决在规模较大的软件项目中目录结构、工程结构等等。

## 二、maven的groupid、artifactid命名
1. xx系统中所有的业务模块组件的groupid都命名为：com.numsg.xsystem
* artifactid命名规则为：numsg-xsystem-[modulename]-services 或 numsg-xsystem-[modulename]-webapi  

## 三、版本号规则
1. 采用语义化命名规则，初始版本号为:1.0.0
* Releases版本规则为：1.0.0-RELEASES
* Snapshot版本规则为：1.0.0-SNAPSHOT


## 其它

常用gradle命令  
gradle clean  或gradlew  clean          -- 清理  
gradle build  或gradlew  build          -- 编译  
gradle build --refresh-dependencies     --编译并下载最新依赖包  


`注意： 如果使用tomcat运行发生异常，建议更新tomcat版本到apache-tomcat-8.5.9`
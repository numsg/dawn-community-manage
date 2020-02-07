# dawn-community-manage工程
> 此工程为预案管理系统的基础支撑工程。   


## Quick Start  

### 1、环境依赖   
* 开发工具--------------- IDEA  2018.1.x
* 构建工具--------------- Gradle4.8     
* 代码质量审查工具---SonarLint    
	
### 2、clone工程  
```  
  git clone http://guoquan@bitbucket.gjsy.gsafety.com/scm/gspms/dawn-community-manage.git
```
__本地切换到develop分支__    

### 3、使用IDEA打开工程  
使用`IDEA 2018.1.5 `打开工程, 选择`use default gradle wrapper`。  

### 4、编译工程    

在IDEA窗口右侧导航栏选择`Gradle` ,打开`dawn-community-manage`根目录，选择`task`，然后`bulid`.
![](docs/images/build.PNG)   



### 5、运行工程    
点击idea窗口上方工具栏中绿色的三角形，启动工程。或者进入`Application`中启动里面的`main`方法。
![](docs/images/run.PNG)
### 6、检查运行结果  
查看运行输出窗口，无错误日志后，在浏览器地址栏输入`http://localhost:8666`即可访问`swagger-ui`。 
    

## 工程介绍  

### 一、工程目录结构说明  
```
dawn-community-manage/                    - 基础支撑工程根目录
	├─ .idea/                        - idea工作目录，存放了idea项目的一些描述文件等。
	├─ .gradle/                      - gradle工作目录
	├─ dawn-community-manage-backend  	 - 基础支撑工程的宿主程序
	├─ dawn-community-manage-contract 	 - 基础支撑工程的业务模块契约，包含服务和数据契约   
	├─ dawn-community-manage-service 	 - 基础支撑工程的业务模块服务实现，含此业务模块对应的datamapper,entity,repository,serviceimpl
	├─ dawn-community-manage-webapi  	 - 基础支撑工程的业务模块webapi-controller
	├─ dawn-community-manage-common  	 - 基础支撑工程的公共模块
	├─ build/                        - gradle构建结果的输出目录。
	├─ docs                          - 基础支撑工程的相关文档。
	├─ gradle/                       - gradle存放gradle-wrapper包。
	├─ scripts                       - 通用脚本组件(git,publish,sonar,jacoco...)
	├─ .gitgnore                     - git忽略文件
	├─ dawn-community-manage.iml     	 - 存放基础支撑工程的配置信息
	├─ gradlew                       - Gradle start up script for UNINX
	├─ gradlew.bat                   - Gradle startup script for Windows
	├─ build.gradle                  - gradle构建脚本
	└─ README.md                     - *基础支撑工程的描述*
```
* [详情参考种子工程](http://172.22.3.3/vNextDevTechs/bosk-seed)

### 二、版本号规则
1. 采用语义化命名规则，初始版本号为:1.0.0
* Releases版本规则为：1.0.0-RELEASES
* Snapshot版本规则为：1.0.0-SNAPSHOT

### 三、规范  
* [开发过程控制](http://172.22.3.3/vNextDevTechs/Cooperation)
* [Java编码规范](http://172.18.24.38:51206)
* [RestfulApi设计规范](http://172.22.3.3/vNextDevTechs/Cooperation/blob/master/docs/Gsafety-REST-API-design-rules.md)

### 四、持续构建  
to do



  
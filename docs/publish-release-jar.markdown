# 怎么将jar包发布到中央仓库

在我们开发java程序中，我们经常会需要依赖一些第三方jar包。这些jar包的来源有几种方式，比如我们直接去发布该jar包的社区去下载，还可以在官方maven仓库下载。当然第二种方式会更加优雅。

对于经常单兵作战的java程序猿来说，能有一个位置统一管理开发的jar包，是多么方面的一件事情。今天主要讲解的是如何将jar包上传到官方maven中央仓库。

## 准备工作
说明：
1. https://issues.sonatype.org 工单管理地址，就是申请上传资格和groupId 的地方。
2. https://oss.sonatype.org/#welcome  构件仓库，把jar包上传到这里，Release 之后就会同步到maven中央仓库。
3. http://search.maven.org/  最终表现在可以在这里搜索到。

步骤如下

### 创建一个工单
首先在 https://issues.sonatype.org 注册一个账号（已经有请忽略），账号密码要记住。然后创建一个工单，如下图，
![](./images/table.png)

需要注意点：
1. Group Id ，唯一标识，我用的是com.github.xxxxx  如果用的是其他的比如：com.numsg 之类的，管理员会问你这个是不是属于你的网站，你只需要用@numsg.com发送一个邮件到central@sonatype.com即可（用com.github.xxxxx 这种比较方便）。官方文档：http://central.sonatype.org/pages/choosing-your-coordinates.html 
2. ProjectURL ，填项目源码的地址，官方有一篇文章说，如果不想公布源码，那么填写一个只含README 的项目的地址就可以了。

`其实管理员主要就是审核Group Id，其他的不重要`

这时你的工单的状态Status 是Open ，等到审核通过状态会变为RESOLVED ，第一次审核要一天左右 ，因为时差原因，他们工作时间是我们的晚上，之后在创建工单如果GroupId 满足基本要求基本就是秒过。

### 准备好上传脚本(gradle)
```
uploadArchives{
    repositories{
        mavenDeployer{
//            //为Pom文件做数字签名
            beforeDeployment { MavenDeployment deployment -> signing.signPom(deployment) }
            //远程releases库
            repository(url:"https://oss.sonatype.org/service/local/staging/deploy/maven2/"){
                authentication(
                    userName:https://issues.sonatype.org的账号,
                    password:https://issues.sonatype.org的密码
                )
            }

            // 远程snapshot库
            snapshotRepository(url:"https://oss.sonatype.org/content/repositories/snapshots/"){
                authentication(
                    userName:https://issues.sonatype.org的账号,
                    password:https://issues.sonatype.org的密码
                    )
            }

            // 如果version为*.*.*-RELEASES将会自动上传至releases库,
            // 如果为*.*.*-SNAPSHOT将会自动上传至snapshot库。
            // $artifactVersion定义在gradle.perproties文件中。
            pom.project{
                name = "numsg-odata-service"
                packaging = "jar"
                version = "0.0.1"
                description = "numsg-odata-service is based on olingo"
                url = "https://github.com/numsg/numsg-odata.git"

                scm {
                    url 'scm:git@github.com:numsg/numsg-odata.git'
                    connection 'scm:git@github.com:numsg/numsg-odata.git'
                    developerConnection 'scm:git@github.com:numsg/numsg-odata.git'
                }

                licenses {
                    license {
                        name 'The Apache Software License, Version 2.0'
                        url 'http://www.apache.org/licenses/LICENSE-2.0.txt'
                        distribution 'repo'
                    }
                }

                developers {
                    developer {
                        id 'numsg'
                        name 'numsg'
                    }
                }
            }
        }
    }
}
```
注意:
1. 需要配置好上传账号密码
2. pom需要添加必应元素，如name，url，description
3. 设置好scm
4. 设置好licenses
5. 生产Javadoc

### GPG环境
我们需要一个GPG环境，用来对上传的文件进行加密和签名，保证你的jar包不被篡改，下载地址：https://www.gpg4win.org/ 

安装完成后 命令行窗口中输入 gpg –version 查看是否安装成功

1. 首先创建公钥 New Certificate...
2. 生产对应的Key id 和password
3. 导出私钥secret.gpg文件 Exports Secret Key...
4. Send Keys (send a public key server ("hkp://keys.gnupg.net"))

通过公约 Key id在 http://keys.gnupg.net/ 查询
![](./images/key-search.png)
查询结果：
![](./images/search-result.png)


使用secret.gpg文件对jar包进行加密
```
apply plugin: 'signing' //使用signing plugin做数字签名

//为所有的jar包做数字签名
signing {
    sign configurations.archives
}

signing.keyId=xxxxxxxx
signing.password=password
signing.secretKeyRingFile=./secret.gpg
```
### 上传到中间仓库Staging Repositories，

执行jar包上传

在 https://oss.sonatype.org/#stagingRepositories 中找到自己刚刚上传的jar 包，点击Close，进行Jar规范性校验，如果没问题可以直接release即可

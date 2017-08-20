# WebserviceTemplate -- 基于Java的通用Webservice模板工程

## 简介
基于Spring MVC 4.3.9 + Mybatis 3.4.4 + Quartz 2.3.0 + Gradle 构建，支持从数据库自动生成Mybatis的相关类，支持通过Docker打包运行.

## 环境要求：
- Java 8+
- Tomcat 8.5+
- Gradle 2.14 +
- Ant 1.9.7+(生成Mybatis类文件时需要)
- Mysql 5.7 +

## 子工程说明
| 子工程名 | 说明 |
|--------------|--------------------|
| sub-common | 基础类，工具类的存放工程 |
| sub-dal | 数据库访问相关 |
| sub-core  | 主要存放业务代码，定时任务等 |
| sub-webapp  | 基于Spring MVC，提供对外的访问接口 |
| sub-test  | 测试代码 |


## 子工程依赖关系
sub-common -> sub-dal -> sub-core -> sub-webapp -> sub-test

## 打包命令及配置文件说明
打包命令执行时，会自动加载相应配置文件中的配置项。

| 环境 | 打包命令 | 使用的配置文件 |
|--------------|--------------------|--------------------|
| Dev（开发环境） | gradle packageDevWar | configs/dev/config.properties |
| Prod（正式环境） | gradle packageProdWar | configs/prod/config.properties |
| Test（测试环境）  | gradle packageTestWar | configs/test/config.properties |
**`configs/common.properties`为通用配置文件，其中的配置项在每个环境中都会生效**

## Mybatis自动生成说明
生成Mybatis类的程序基于ant，需要提前安装好ant环境。
1. 使用`extra/sql`文件夹内的sql文件生成mysql数据表，或自行在mysql中创建表定义。
1. 修改`extra/MyBatisGenerator/generatorConfig.xml`配置文件中的`<table>`标签，设置要生成哪些表的类文件。
1. 在`extra/MyBatisGenerator`文件夹下执行 `ant`命令。
1. 类文件及XML文件在`sub-dal`工程内自动生成。

## 定时任务
1. 定时任务基于Quartz
    - Quartz的配置文件位于：`sub-core/src/main/resources/scheduled-tasks.xml`
1. 定时任务的实现类建议放在`sub-core/src/main/java/com/istuary/webserviceTemplate/api/core/scheduled`包内

## Spring MVC
1. Controller类建议到该包内:`sub-webapp/src/main/java/com/istuary/webserviceTemplate/api/webapp/controller`
1. 目前Webservice主要支持返回Json格式的数据。
    - 建议使用`DefaultServiceResult`包裹返回数据。

## Docker
Docker相关文件在docker文件夹内。执行`local_run.sh`脚本可自动打包image并运行脚本。 `TODO`
**需要提前安装好Docker 1.11+**


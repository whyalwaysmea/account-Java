# SpringBoot 与 Logback 
SLF4J——Simple Logging Facade For Java，它是一个针对于各类Java日志框架的统一Facade抽象。Java日志框架众多——常用的有java.util.logging, log4j, logback，commons-logging, Spring框架使用的是Jakarta Commons Logging API (JCL)。而SLF4J定义了统一的日志抽象接口，而真正的日志实现则是在运行时决定的——它提供了各类日志框架的binding。

Logback是log4j框架的作者开发的新一代日志框架，它效率更高、能够适应诸多的运行环境，同时天然支持SLF4J。 

默认情况下，Spring Boot会用Logback来记录日志，并用INFO级别输出到控制台。在运行应用程序和其他例子时，你应该已经看到很多INFO级别的日志了。

# 简单配置     
## 级别控制 
所有支持的日志记录系统都可以在Spring环境中设置记录级别（例如在application.properties中）   
格式为：`logging.level.* = LEVEL`

- logging.level：日志级别控制前缀，*为包名或Logger名   
- LEVEL：选项TRACE, DEBUG, INFO, WARN, ERROR, FATAL, OFF  
  

## 控制台输出 
日志级别从低到高分为TRACE < DEBUG < INFO < WARN < ERROR < FATAL，如果设置为WARN，则低于WARN的信息都不会输出。 

Spring Boot中默认配置ERROR、WARN和INFO级别的日志输出到控制台。您还可以通过启动您的应用程序--debug标志来启用“调试”模式（开发的时候推荐开启）,以下两种方式皆可：
- 在运行命令后加入--debug标志，如：$ java -jar springTest.jar --debug  
- 在application.properties中配置debug=true，该属性置为true的时候，核心Logger（包含嵌入式容器、hibernate、spring）会输出更多内容，但是你自己应用的日志并不会输出为DEBUG级别。 

## 文件输出 
默认情况下，Spring Boot将日志输出到控制台，不会写到日志文件。如果要编写除控制台输出之外的日志文件，则需在application.properties中设置logging.file或logging.path属性。 
- logging.file，设置文件，可以是绝对路径，也可以是相对路径。如：logging.file=my.log  
- logging.path，设置目录，会在该目录下创建spring.log文件，并写入日志内容，如：logging.path=/var/log  

如果只配置 logging.file，会在项目的当前路径下生成一个 xxx.log 日志文件。   
如果只配置 logging.path，在 /var/log文件夹生成一个日志文件为 spring.log    
注：二者不能同时使用，如若同时使用，则只有logging.file生效   

# 自定义配置   
Spring Boot官方推荐优先使用带有-spring的文件名作为你的日志配置（如使用logback-spring.xml，而不是logback.xml），命名为logback-spring.xml的日志配置文件，并且放在src/main/resources下面。  

## 根节点 `<configuration>` 
- scan:当此属性设置为true时，配置文件如果发生改变，将会被重新加载，默认值为true。  
- scanPeriod:设置监测配置文件是否有修改的时间间隔，如果没有给出时间单位，默认单位是毫秒。当scan为true时，此属性生效。默认的时间间隔为1分钟。  
- debug:当此属性设置为true时，将打印出logback内部日志信息，实时查看logback运行状态。默认值为false。   

scanPeriod的时间单位：milliseconds, seconds, minutes , hours


### 设置上下文名称-`<contextName>` 
每个logger都关联到logger上下文，默认上下文名称为“default”。但可以使用<contextName>设置成其他名字，用于区分不同应用程序的记录。一旦设置，不能修改,可以通过%contextName来打印日志上下文名称。
```xml
<contextName>logback</contextName>
``` 

### 设置变量-`<property>` 
用来定义变量值的标签，<property> 有两个属性，name和value；其中name的值是变量的名称，value的值时变量定义的值。通过<property>定义的值会被插入到logger上下文中。定义变量后，可以使“${}”来使用变量。
```xml
<property name="log.path" value="E:\\logback.log" />
```

### 子节点1： `<appender>`   
appender用来格式化日志输出节点，有俩个属性name和class，class用来指定哪种输出策略，常用就是控制台输出策略和文件输出策略。
  
#### 控制台输出ConsoleAppender： 
```xml
<appender name="STDOUT" class="ch.qos.logback.core.ConsoleAppender">
    <encoder>
        <!--格式化输出：%d:表示日期 %thread:表示线程名 %-5level:级别从左显示5个字符宽度 %logger{36}:日志输出者的名字 %msg:日志消息    %n:是换行符-->
        <pattern>%d{yyyy-MM-dd HH:mm:ss.SSS} [%thread] %-5level %logger{36} - %msg%n</pattern>
        <charset>UTF-8</charset>
    </encoder>
</appender>
```

#### 输出到文件RollingFileAppender 
```xml
<appender name="logfile" class="ch.qos.logback.core.rolling.RollingFileAppender">
    <Encoding>UTF-8</Encoding>
    <!-- 正在记录的日志文件的路径及文件名 -->
    <File>${log.path}/sys.log</File>
    <!-- 日志记录器的滚动策略，按日期，按大小记录 -->
    <rollingPolicy class="ch.qos.logback.core.rolling.TimeBasedRollingPolicy">
        <!-- 归档的日志文件的路径 -->
        <FileNamePattern>logback.%d{yyyy-MM-dd}.%i.log</FileNamePattern>
        <!-- 每产生一个日志文件，该日志文件的保存期限为30天 -->   
        <maxHistory>30</maxHistory>    
        <!-- 除按日志记录之外，还配置了日志文件不能超过2M，若超过2M，日志文件会以索引0开始 -->
        <timeBasedFileNamingAndTriggeringPolicy class="ch.qos.logback.core.rolling.SizeAndTimeBasedFNATP">
            <maxFileSize>2MB</maxFileSize>
        </timeBasedFileNamingAndTriggeringPolicy> 
    </rollingPolicy>
    <encoder>
        <!-- pattern节点，用来设置日志的输入格式 -->  
        <pattern>%date|%msg%n</pattern>
        <charset>utf-8</charset>
    </encoder>
</appender>
```

### 子节点2：`<root>`
root节点是必选节点，用来指定最基础的日志输出级别，只有一个level属性。

level:用来设置打印级别，大小写无关：TRACE, DEBUG, INFO, WARN, ERROR, ALL 和 OFF，不能设置为INHERITED或者同义词NULL，默认是DEBUG。  

<root>可以包含零个或多个<appender-ref>元素，标识这个appender将会添加到这个loger。

```xml
<root level="info">
    <appender-ref ref="STDOUT"/>
    <appender-ref ref="logfile" />
</root>
```
  
### 子节点3： `<loger>` 
<loger>用来设置某一个包或者具体的某一个类的日志打印级别、以及指定<appender>。<loger>仅有一个name属性，一个可选的level和一个可选的addtivity属性。 
  
- name:用来指定受此loger约束的某一个包或者具体的某一个类。  
- level:用来设置打印级别，大小写无关：TRACE, DEBUG, INFO, WARN, ERROR, ALL 和 OFF，还有一个特俗值INHERITED或者同义词NULL，代表强制执行上级的级别。如果未设置此属性，那么当前loger将会继承上级的级别。  
- addtivity:是否向上级loger传递打印信息。默认是true。
   

### 多环境日志输出
据不同环境（prod:生产环境，test:测试环境，dev:开发环境）来定义不同的日志输出，在 logback-spring.xml中使用 springProfile 节点来定义，方法如下： 
```xml
<springProfile name="test,dev">
    <logger name="com.whyalwaysmea.account" level="info" />
</springProfile>

<springProfile name="prod">
    <logger name="com.whyalwaysmea.account" level="EEROR" />
</springProfile> 
```
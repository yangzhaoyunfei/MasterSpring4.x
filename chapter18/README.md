# chapter 18 论坛开发案例

这是一个web应用,系统严格采用3层体系结构:持久层,服务层,web层;
上一层的程序可以调用下一层的程序,反之则不行,从而达到层与层之间的松散耦合;

## 构建工具

maven

## 目录结构

### web层(web/controller包):
web层程序
使用Spring MVC进行请求的处理和响应
为了解决中文乱码问题,我们在web层,提供一个字符编码转换过滤器;

### 服务层(service包,service/serviceImpl)
服务层的程序
xiaochun-service.xml,,Spring配置文件

### 持久层(dao包)
持久层的程序
xiaochun-dao.xml,Spring配置文件

#### 领域对象(domain包,PO/VO/DTO...)
由于PO会在多个层中出现,因此我们为其提供一个单独的domain包

#### 常量包(cons包)
为了避免在程序中直接使用常量字面值(硬编码),所以在cons包中定义了应用级的常量

#### 异常管理(exception包)
为了统一管理应用系统异常体系,我们在exception包中定义了业务异常类,系统异常等

#### webapp包
web应用的文件
大部分jsp放在 WEB-INF/jsp目录中
WEB-INF/xiaochun-servlet.xml    Spring MVC的配置文件

## resources文件夹
jdbc.properties属性文件:提供数据库连接的信息,被xiaochun-service.xml使用
log4j.properties属性文件:Log4j配置文件
上述这些配置文件直接放到classpath下


### 单元测试

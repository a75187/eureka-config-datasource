#cloud config 
配置切换

## 必须配置rabbitmq
```
spring.rabbitmq.host/port/username/password
```
## 可选配置：添加到enreka注册服务器
```
<dependency>  
    <groupId>org.springframework.cloud</groupId>  
    <artifactId>spring-cloud-starter-eureka</artifactId>  
</dependency>  
```
```
eureka.client.serviceUrl.defaultZone=http://localhost:1111/eureka/  
```
```
主类中添加@EnableDiscoveryClient注解
```

## 设置配置源
```
spring.cloud.config.server.svn.uri/username/password
spring.profiles.active:subversion
```
```
spring.cloud.config.server.git.uri/username/password
spring.profiles.active:git
```
```
spring.cloud.config.server.native.search-locations
spring.profiles.active:native
```

## 客户端配置
```
spring.cloud.config.profile=dev  
spring.cloud.config.label=master  
```

## 访问url
```
/{application}/{profile}[/{label}]
/{application}-{profile}.yml
/{label}/{application}-{profile}.yml
/{application}-{profile}.properties
/{label}/{application}-{profile}.properties
```
上面的url会映射{application}-{profile}.properties对应的配置文件，{label}对应git上不同的分支，默认为master | truck。
http://blog.csdn.net/chenqipc/article/details/53322830


- 在这个例子中，所有的服务都会被忽略
```propeties
zuul:
    ignoredServices: '*'
```

- 这表示，HTTP调用 “/myusers” 会转到 “user” 服务
（例如：”/myusers/101”跳转到”/101”）。
```propeties
 zuul:
  routes:
    users: /myusers/**
```

- 更细粒度的配置方法
```propeties
 zuul:
  routes:
    users:
      path: /myusers/**
      serviceId: users_service
```

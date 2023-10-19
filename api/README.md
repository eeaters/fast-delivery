# 后端服务

Gradle + JDK 17 + Spring boot 3.1.3  

## 相关地址

| 功能  |                   地址                   |
|:---:|:--------------------------------------:|
| doc | http:127.0.0.1:8080/swagger/index.html |


## 配置
权限属于系统不关心的功能(并且企业都有自己的用户权限体系) , 直接写死了一个用户, 可以修改下面的配置让权限关闭掉

```yaml
delivery:
  mock:
    user:
      enabled: false
```

## 引用
名字随机生成: [name-builder](https://github.com/YeautyYE/name-builder/tree/master)

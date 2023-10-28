# fast-delivery - 智能配送系统

fast-delivery 是对接三方配送, 并负责配送的调度

* [**api**](https://github.com/eeaters/fast-delivery/tree/master/api) :
后端服务
* [**web**](https://github.com/eeaters/fast-delivery/tree/master/web) : 
前端服务

## 分支说明

| 分支   | 分支说明                              |
| ------ | ------------------------------------- |
| master | 主分支                                |
| h2     | 数据库使用h2数据库,无组件引用启动项目 |



## 顶层图

![顶层图](doc/img/顶层图.jpg)

## 订单推送流程图

![创建订单](doc/img/创建订单.jpg)

## Getting Started

- 运行后端服务
  - h2分支可以直接启动服务
  - master分支需要在resources/config/dev/db.yml中修改为自己的数据库 , 初始化数据在resources/sql中进行数据库初始化
- 运行前端服务

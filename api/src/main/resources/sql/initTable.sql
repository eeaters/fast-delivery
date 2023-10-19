create schema temp collate utf8mb4_general_ci;

use
temp;


create table if not exists t_delivery
(
    id                 bigint auto_increment
        primary key,
    order_id           bigint                             not null comment '订单id',
    order_code         varchar(20)                        not null comment '订单code',
    channel_order_id   varchar(20)                        null comment '三方渠道订单编号',
    channel_price      int                                null comment '三方配送费',
    status             tinyint  default 10                null comment '配送状态',
    channel            varchar(20)                        null comment '配送渠道',
    push_time          datetime                           null comment '推送时间',
    rider_receive_time datetime                           null comment '骑手接单时间',
    arrive_store_time  datetime                           null comment '骑手到店时间',
    delivering_time    datetime                           null comment '开始配送时间',
    complete_time      datetime                           null comment '配送完成时间',
    error_time         datetime                           null comment '订单异常时间',
    error_code         varchar(20)                        null comment '异常时三方异常码',
    error_message      varchar(50)                        null comment '异常时三方异常原因',
    cancel_time        datetime                           null comment '取消时间',
    cancel_code        varchar(20)                        null comment '取消时,取消code',
    cancel_message     varchar(50)                        null comment '取消原因',
    rider_name         varchar(20)                        null comment '骑手姓名',
    rider_phone        varchar(20)                        null comment '骑手联系电话',
    create_time        datetime default CURRENT_TIMESTAMP not null,
    update_time        datetime default CURRENT_TIMESTAMP not null on update CURRENT_TIMESTAMP comment '更新时间'
)
    comment '运单';



create table if not exists t_order
(
    id                   bigint auto_increment comment '主键id'
    primary key,
    store_code           varchar(20)                        not null comment '门店编码',
    order_code           varchar(50)                        not null comment '订单编码',
    callback_url         varchar(50)                        not null comment '回调地址',
    total_price          int                                null comment '订单总金额',
    receive_user_name    varchar(20)                        not null comment '收货联系人',
    receive_user_phone   varchar(20)                        not null comment '收货人联系电话',
    receive_user_address varchar(50)                        not null comment '收货人联系地址',
    order_time           datetime                           not null comment '用户下单时间',
    weight_info          varchar(500)                       null comment '权重信息',
    is_mock              tinyint  default 0                 null comment '0：正常数据 1:mock数据',
    create_user          varchar(20)                        null comment '创建人',
    create_time          datetime default CURRENT_TIMESTAMP null comment '创建时间'
    );

create table if not exists t_plan
(
    id          bigint auto_increment
    primary key,
    plan_name   varchar(20)                           not null comment '方案名称',
    type        tinyint     default 1                 null comment '排序规则, 1: 默认排序 2:时间排序 3:价格排序',
    `desc`      varchar(20)                           not null comment '方案描述',
    time_period int         default 15                not null comment '超时未分配间隔',
    create_user varchar(20) default 'SYSTEM'          not null comment '创建人',
    create_time datetime    default CURRENT_TIMESTAMP null comment '创建时间',
    update_user varchar(20) default 'System'          null comment '更新人',
    update_time datetime    default CURRENT_TIMESTAMP not null on update CURRENT_TIMESTAMP comment '更新时间'
    );

create table if not exists t_plan_channel_mapping
(
    id          bigint auto_increment
    primary key,
    plan_id     bigint                                not null comment '方案id',
    channel     varchar(20)                           not null comment '渠道',
    sort        int         default 0                 not null comment '排序值',
    create_user varchar(20) default 'SYSTEM'          not null comment '创建人',
    create_time datetime    default CURRENT_TIMESTAMP null comment '创建时间'
    );

create table if not exists t_product_info
(
    id             bigint auto_increment comment '主键id'
    primary key,
    order_code     varchar(50)   not null comment '订单code',
    product_name   varchar(64)   not null comment '商品名称',
    product_num    int default 1 null comment '商品数量',
    product_remark varchar(100)  null comment '商品描述'
    )
    comment '商品明细';

create table if not exists t_region
(
    id        int(10)           not null
    primary key,
    name      varchar(50)       not null,
    parent_id int(10) default 0 not null
    );

create table if not exists t_store
(
    id                   bigint auto_increment
    primary key,
    store_name           varchar(25)                           not null comment '门店名称',
    store_code           varchar(25)                           null comment '门店编码',
    province_id          int                                   not null comment '省份id',
    city_id              int                                   not null comment '市id',
    district_id          int                                   not null comment '区id',
    address              varchar(50)                           null comment '详细地址',
    longitude            varchar(20)                           not null comment '经度',
    latitude             varchar(20)                           not null comment '纬度',
    plan_id              bigint                                null comment '方案id',
    store_contract       varchar(20)                           not null comment '门店联系人',
    store_contract_phone varchar(20)                           null comment '门店联系方式',
    create_user          varchar(20) default 'System'          null comment '创建人',
    create_time          datetime    default CURRENT_TIMESTAMP not null comment '创建时间',
    update_user          varchar(20) default 'System'          null comment '更新人',
    update_time          datetime    default CURRENT_TIMESTAMP not null on update CURRENT_TIMESTAMP comment '更新时间',
    constraint store_store_code_uindex
    unique (store_code)
    );

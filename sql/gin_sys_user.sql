create table sys_user
(
    id       bigint auto_increment
        primary key,
    username varchar(50)                                                               not null,
    password varchar(100)                                                              not null,
    status   int          default 1                                                    not null,
    gender   varchar(32)                                                               null,
    avatar   varchar(500) default 'http://img.wxcha.com/file/202001/19/da61368923.jpg' null,
    age      int                                                                       null,
    nickname varchar(20)                                                               null
)
    comment '用户表';

INSERT INTO gin.sys_user (id, username, password, status, gender, avatar, age, nickname) VALUES (3, 'admin', '2de3f287c2b06ab4e5573d8e416b40870aad0dacdd959fa73a781da2a00d9e74', 999, 'FEMALE', 'https://i.loli.net/2020/03/02/TP81J27RbG3yixC.jpg', 24, '管理员');
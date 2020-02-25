create table sys_user
(
    id       bigint auto_increment
        primary key,
    username varchar(50)                                                               not null,
    password varchar(100)                                                              not null,
    status   int          default 1                                                    not null,
    gender   varchar(32)                                                               null,
    avatar   varchar(500) default 'http://img.wxcha.com/file/202001/19/da61368923.jpg' null
)
    comment '用户表';

INSERT INTO gin.sys_user (id, username, password, status, gender, avatar) VALUES (3, 'admin', 'ce85a9fd6a92d7521c2b470c93397558691da617a89d760676c4a2561a9bebab', 1, null, 'http://img.wxcha.com/file/202001/19/da61368923.jpg');
INSERT INTO gin.sys_user (id, username, password, status, gender, avatar) VALUES (4, 'tom', 'ce85a9fd6a92d7521c2b470c93397558691da617a89d760676c4a2561a9bebab', 1, null, 'http://img.wxcha.com/file/202001/19/da61368923.jpg');
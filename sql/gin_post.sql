create table post
(
    id          bigint auto_increment
        primary key,
    title       varchar(50)   not null,
    content     varchar(9999) null,
    cover       varchar(999)  null,
    create_time bigint        not null,
    user_id     bigint        null
);

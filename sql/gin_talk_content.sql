create table talk_content
(
    id          bigint auto_increment
        primary key,
    user_id     bigint        null,
    username    varchar(50)   null,
    content     varchar(200)  null,
    create_time bigint        null,
    replied     int default 0 null
);


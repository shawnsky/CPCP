create table test_result
(
    id          bigint auto_increment
        primary key,
    user_id     int          null,
    result      varchar(100) null,
    val         int          null,
    create_time bigint       null
);


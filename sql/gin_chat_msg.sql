create table chat_msg
(
    id          bigint auto_increment
        primary key,
    from_id     bigint       not null,
    to_id       bigint       not null,
    msg_content varchar(999) not null,
    create_time bigint       not null
);


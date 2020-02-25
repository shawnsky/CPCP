create table chat_msg
(
    id          bigint auto_increment
        primary key,
    from_id     bigint       not null,
    to_id       bigint       not null,
    msg_content varchar(999) not null,
    create_time bigint       not null
);

INSERT INTO gin.chat_msg (id, from_id, to_id, msg_content, create_time) VALUES (5, 3, 4, 'nihao', 1580717172762);
INSERT INTO gin.chat_msg (id, from_id, to_id, msg_content, create_time) VALUES (6, 3, 4, 'i am a simple man.', 1580717175267);
INSERT INTO gin.chat_msg (id, from_id, to_id, msg_content, create_time) VALUES (7, 4, 3, '你是人吗', 1580717183896);
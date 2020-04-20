create table test
(
    id       bigint auto_increment
        primary key,
    question varchar(50) not null,
    ans_a    varchar(50) not null,
    ans_b    varchar(50) not null,
    ans_c    varchar(50) not null,
    ans_d    varchar(50) not null
);

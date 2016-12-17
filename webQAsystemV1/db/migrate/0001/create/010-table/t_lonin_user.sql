create table T_LONIN_USER (
    USER_MANEGE_ID bigint not null auto_increment,
    CLASSIFICATION_ID bigint not null,
    NAME varchar(40) not null,
    PASSWORD varchar(30) not null,
    USER_LOGIN_ID varchar(30) not null,
    constraint T_LONIN_USER_PK primary key(USER_MANEGE_ID)
);

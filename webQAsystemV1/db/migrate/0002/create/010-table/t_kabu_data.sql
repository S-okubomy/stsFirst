create table T_KABU_DATA (
    KABU_MANEGE_ID bigint not null auto_increment,
    KABU_VOLUME bigint not null,
    KABU_CLOSE bigint not null,
    KABU_LOW bigint not null,
    KABU_HIGH bigint not null,
    KABU_OPEN bigint not null,
    KABU_NAME varchar(200) not null,
    KABU_CODE varchar(100) not null,
    KABU_DATE timestamp not null,
    constraint T_KABU_DATA_PK primary key(KABU_MANEGE_ID)
);

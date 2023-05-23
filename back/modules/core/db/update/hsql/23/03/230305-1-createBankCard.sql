create table UNIQCARS_BANK_CARD (
    ID varchar(36) not null,
    VERSION integer not null,
    CREATE_TS timestamp,
    CREATED_BY varchar(50),
    UPDATE_TS timestamp,
    UPDATED_BY varchar(50),
    DELETE_TS timestamp,
    DELETED_BY varchar(50),
    --
    USER_ID varchar(36),
    NUMBER_ integer,
    EXPIRE_DATE date,
    NAME_ON_CARD varchar(255),
    --
    primary key (ID)
);
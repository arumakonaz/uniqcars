create table UNIQCARS_BANK_CARD (
    ID uuid,
    VERSION integer not null,
    CREATE_TS timestamp,
    CREATED_BY varchar(50),
    UPDATE_TS timestamp,
    UPDATED_BY varchar(50),
    DELETE_TS timestamp,
    DELETED_BY varchar(50),
    --
    USER_ID uuid,
    NUMBER_ integer not null,
    EXPIRE_DATE date not null,
    NAME_ON_CARD varchar(255) not null,
    --
    primary key (ID)
);
create table UNIQCARS_ODER (
    ID uuid,
    VERSION integer not null,
    CREATE_TS timestamp,
    CREATED_BY varchar(50),
    UPDATE_TS timestamp,
    UPDATED_BY varchar(50),
    DELETE_TS timestamp,
    DELETED_BY varchar(50),
    --
    STATUS varchar(50) not null,
    ITEM_ID uuid not null,
    COUNT_ integer,
    --
    primary key (ID)
);
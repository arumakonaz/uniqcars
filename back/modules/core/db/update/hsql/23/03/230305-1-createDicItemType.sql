create table UNIQCARS_DIC_ITEM_TYPE (
    ID varchar(36) not null,
    VERSION integer not null,
    CREATE_TS timestamp,
    CREATED_BY varchar(50),
    UPDATE_TS timestamp,
    UPDATED_BY varchar(50),
    DELETE_TS timestamp,
    DELETED_BY varchar(50),
    NAME_RU longvarchar,
    NAME_KZ longvarchar,
    NAME_EN longvarchar,
    --
    primary key (ID)
);
create table UNIQCARS_ITEM (
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
    DESCRIPTION_RU longvarchar,
    DESCRIPTION_KZ longvarchar,
    DESCRIPTION_EN longvarchar,
    --
    ITEM_TYPE_ID varchar(36) not null,
    PRICE decimal(19, 2) not null,
    --
    primary key (ID)
);
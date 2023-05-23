create table UNIQCARS_ITEM (
    ID uuid,
    VERSION integer not null,
    CREATE_TS timestamp,
    CREATED_BY varchar(50),
    UPDATE_TS timestamp,
    UPDATED_BY varchar(50),
    DELETE_TS timestamp,
    DELETED_BY varchar(50),
    NAME_RU text,
    NAME_KZ text,
    NAME_EN text,
    DESCRIPTION_RU text,
    DESCRIPTION_KZ text,
    DESCRIPTION_EN text,
    --
    ITEM_TYPE_ID uuid not null,
    PRICE decimal(19, 2) not null,
    --
    primary key (ID)
);
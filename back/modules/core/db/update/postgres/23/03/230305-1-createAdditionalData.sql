create table UNIQCARS_ADDITIONAL_DATA (
    ID uuid,
    VERSION integer not null,
    CREATE_TS timestamp,
    CREATED_BY varchar(50),
    UPDATE_TS timestamp,
    UPDATED_BY varchar(50),
    DELETE_TS timestamp,
    DELETED_BY varchar(50),
    NAME_RU text,
    CODE varchar(255),
    NAME_KZ text,
    NAME_EN text,
    --
    TYPE_ID uuid not null,
    VALUE_RU text,
    VALUE_KZ text,
    VALUE_EN text,
    ITEM_ID uuid,
    --
    primary key (ID)
);
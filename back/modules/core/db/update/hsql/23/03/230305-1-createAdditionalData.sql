create table UNIQCARS_ADDITIONAL_DATA (
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
    TYPE_ID varchar(36) not null,
    VALUE_RU longvarchar,
    VALUE_KZ longvarchar,
    VALUE_EN longvarchar,
    --
    primary key (ID)
);
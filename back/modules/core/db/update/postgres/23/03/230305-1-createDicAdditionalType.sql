create table UNIQCARS_DIC_ADDITIONAL_TYPE (
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
    ORDER_ integer not null,
    --
    primary key (ID)
);
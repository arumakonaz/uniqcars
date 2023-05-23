create table UNIQCARS_SERVICE_LOGGER (
    ID uuid,
    VERSION integer not null,
    CREATE_TS timestamp,
    CREATED_BY varchar(50),
    UPDATE_TS timestamp,
    UPDATED_BY varchar(50),
    DELETE_TS timestamp,
    DELETED_BY varchar(50),
    --
    METHOD_NAME varchar(255) not null,
    PARAMS text,
    MESSAGE text not null,
    STACK_TRACE text,
    IS_SUCCESS boolean not null,
    --
    primary key (ID)
);
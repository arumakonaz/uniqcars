-- begin UNIQCARS_ADDITIONAL_DATA
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
    CODE varchar(255),
    NAME_KZ longvarchar,
    NAME_EN longvarchar,
    --
    TYPE_ID varchar(36) not null,
    VALUE_RU longvarchar,
    VALUE_KZ longvarchar,
    VALUE_EN longvarchar,
    ITEM_ID varchar(36),
    --
    primary key (ID)
)^
-- end UNIQCARS_ADDITIONAL_DATA
-- begin UNIQCARS_DIC_ADDITIONAL_TYPE
create table UNIQCARS_DIC_ADDITIONAL_TYPE (
    ID varchar(36) not null,
    VERSION integer not null,
    CREATE_TS timestamp,
    CREATED_BY varchar(50),
    UPDATE_TS timestamp,
    UPDATED_BY varchar(50),
    DELETE_TS timestamp,
    DELETED_BY varchar(50),
    NAME_RU longvarchar,
    CODE varchar(255),
    NAME_KZ longvarchar,
    NAME_EN longvarchar,
    --
    ORDER_ integer not null,
    --
    primary key (ID)
)^
-- end UNIQCARS_DIC_ADDITIONAL_TYPE
-- begin UNIQCARS_DIC_ITEM_TYPE
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
    CODE varchar(255),
    NAME_KZ longvarchar,
    NAME_EN longvarchar,
    --
    primary key (ID)
)^
-- end UNIQCARS_DIC_ITEM_TYPE
-- begin UNIQCARS_ITEM
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
)^
-- end UNIQCARS_ITEM

-- begin UNIQCARS_ADDITIONAL_DATA_FILE_DESCRIPTOR_LINK
create table UNIQCARS_ADDITIONAL_DATA_FILE_DESCRIPTOR_LINK (
    ADDITIONAL_DATA_ID varchar(36) not null,
    FILE_DESCRIPTOR_ID varchar(36) not null,
    primary key (ADDITIONAL_DATA_ID, FILE_DESCRIPTOR_ID)
)^
-- end UNIQCARS_ADDITIONAL_DATA_FILE_DESCRIPTOR_LINK
-- begin UNIQCARS_ITEM_FILE_DESCRIPTOR_LINK
create table UNIQCARS_ITEM_FILE_DESCRIPTOR_LINK (
    ITEM_ID varchar(36) not null,
    FILE_DESCRIPTOR_ID varchar(36) not null,
    primary key (ITEM_ID, FILE_DESCRIPTOR_ID)
)^
-- end UNIQCARS_ITEM_FILE_DESCRIPTOR_LINK
-- begin UNIQCARS_BANK_CARD
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
    NUMBER_ integer not null,
    EXPIRE_DATE date not null,
    NAME_ON_CARD varchar(255) not null,
    --
    primary key (ID)
)^
-- end UNIQCARS_BANK_CARD
-- begin SEC_USER
alter table SEC_USER add column BONUS decimal(19, 2) ^
alter table SEC_USER add column DTYPE varchar(31) ^
update SEC_USER set DTYPE = 'base$UserExt' where DTYPE is null ^
-- end SEC_USER
-- begin UNIQCARS_SERVICE_LOGGER
create table UNIQCARS_SERVICE_LOGGER (
    ID varchar(36) not null,
    VERSION integer not null,
    CREATE_TS timestamp,
    CREATED_BY varchar(50),
    UPDATE_TS timestamp,
    UPDATED_BY varchar(50),
    DELETE_TS timestamp,
    DELETED_BY varchar(50),
    --
    METHOD_NAME varchar(255) not null,
    PARAMS longvarchar,
    MESSAGE longvarchar not null,
    STACK_TRACE longvarchar,
    IS_SUCCESS boolean not null,
    --
    primary key (ID)
)^
-- end UNIQCARS_SERVICE_LOGGER
-- begin UNIQCARS_ODER
create table UNIQCARS_ODER (
    ID varchar(36) not null,
    VERSION integer not null,
    CREATE_TS timestamp,
    CREATED_BY varchar(50),
    UPDATE_TS timestamp,
    UPDATED_BY varchar(50),
    DELETE_TS timestamp,
    DELETED_BY varchar(50),
    --
    STATUS varchar(50) not null,
    ITEM_ID varchar(36) not null,
    COUNT_ integer,
    --
    primary key (ID)
)^
-- end UNIQCARS_ODER

-- begin UNIQCARS_SERVICE_LOGGER
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
)^
-- end UNIQCARS_SERVICE_LOGGER

-- begin UNIQCARS_BANK_CARD
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
    NUMBER_ varchar(255) not null,
    EXPIRE_DATE date not null,
    NAME_ON_CARD varchar(255) not null,
    --
    primary key (ID)
)^
-- end UNIQCARS_BANK_CARD
-- begin UNIQCARS_ITEM
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
)^
-- end UNIQCARS_ITEM
-- begin UNIQCARS_ADDITIONAL_DATA
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
    CAR_ID uuid,
    --
    primary key (ID)
)^
-- end UNIQCARS_ADDITIONAL_DATA
-- begin UNIQCARS_DIC_ADDITIONAL_TYPE
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
)^
-- end UNIQCARS_DIC_ADDITIONAL_TYPE
-- begin UNIQCARS_DIC_ITEM_TYPE
create table UNIQCARS_DIC_ITEM_TYPE (
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
    primary key (ID)
)^
-- end UNIQCARS_DIC_ITEM_TYPE
-- begin UNIQCARS_ADDITIONAL_DATA_FILE_DESCRIPTOR_LINK
create table UNIQCARS_ADDITIONAL_DATA_FILE_DESCRIPTOR_LINK (
    ADDITIONAL_DATA_ID uuid,
    FILE_DESCRIPTOR_ID uuid,
    primary key (ADDITIONAL_DATA_ID, FILE_DESCRIPTOR_ID)
)^
-- end UNIQCARS_ADDITIONAL_DATA_FILE_DESCRIPTOR_LINK
-- begin UNIQCARS_ITEM_FILE_DESCRIPTOR_LINK
create table UNIQCARS_ITEM_FILE_DESCRIPTOR_LINK (
    ITEM_ID uuid,
    FILE_DESCRIPTOR_ID uuid,
    primary key (ITEM_ID, FILE_DESCRIPTOR_ID)
)^
-- end UNIQCARS_ITEM_FILE_DESCRIPTOR_LINK
-- begin SEC_USER
alter table SEC_USER add column BONUS decimal(19, 2) ^
alter table SEC_USER add column IMAGE_ID uuid ^
alter table SEC_USER add column OTP_CODE integer ^
alter table SEC_USER add column DTYPE varchar(31) ^
update SEC_USER set DTYPE = 'base$UserExt' where DTYPE is null ^
-- end SEC_USER
-- begin UNIQCARS_CAR_FILE_DESCRIPTOR_LINK
create table UNIQCARS_CAR_FILE_DESCRIPTOR_LINK (
    CAR_ID uuid,
    FILE_DESCRIPTOR_ID uuid,
    primary key (CAR_ID, FILE_DESCRIPTOR_ID)
)^
-- end UNIQCARS_CAR_FILE_DESCRIPTOR_LINK
-- begin UNIQCARS_CAR
create table UNIQCARS_CAR (
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
    PRICE decimal(19, 2) not null,
    CHASIS_TYPE varchar(50),
    LOCATION varchar(50),
    YEAR_ integer,
    DISPLACEMENT double precision,
    MILEAGE integer,
    TRANSMISSION varchar(50),
    ENGINE_TYPE varchar(50),
    IS_FAVOURITE boolean,
    --
    primary key (ID)
)^
-- end UNIQCARS_CAR
-- begin UNIQCARS_ORDER
create table UNIQCARS_ORDER (
    ID uuid,
    VERSION integer not null,
    CREATE_TS timestamp,
    CREATED_BY varchar(50),
    UPDATE_TS timestamp,
    UPDATED_BY varchar(50),
    DELETE_TS timestamp,
    DELETED_BY varchar(50),
    --
    USER_EXT_ID uuid,
    STATUS varchar(50),
    BANK_CARD_ID uuid,
    CAR_ID uuid,
    SELF_LINK varchar(255),
    APPROVE_LINK varchar(255),
    BONUS decimal(19, 2),
    --
    primary key (ID)
)^
-- end UNIQCARS_ORDER
-- begin UNIQCARS_ORDER_ITEM_LINK
create table UNIQCARS_ORDER_ITEM_LINK (
    ORDER_ID uuid,
    ITEM_ID uuid,
    primary key (ORDER_ID, ITEM_ID)
)^
-- end UNIQCARS_ORDER_ITEM_LINK
-- begin UNIQCARS_ORDER_CAR_LINK
create table UNIQCARS_ORDER_CAR_LINK (
    ORDER_ID uuid,
    CAR_ID uuid,
    primary key (ORDER_ID, CAR_ID)
)^
-- end UNIQCARS_ORDER_CAR_LINK
-- begin UNIQCARS_PARTNER
create table UNIQCARS_PARTNER (
    ID uuid,
    VERSION integer not null,
    CREATE_TS timestamp,
    CREATED_BY varchar(50),
    UPDATE_TS timestamp,
    UPDATED_BY varchar(50),
    DELETE_TS timestamp,
    DELETED_BY varchar(50),
    --
    NAME varchar(255),
    IMAGE_ID uuid,
    YEAR_ integer,
    LOCATION varchar(50),
    --
    primary key (ID)
)^
-- end UNIQCARS_PARTNER
-- begin UNIQCARS_MEDIA
create table UNIQCARS_MEDIA (
    ID uuid,
    VERSION integer not null,
    CREATE_TS timestamp,
    CREATED_BY varchar(50),
    UPDATE_TS timestamp,
    UPDATED_BY varchar(50),
    DELETE_TS timestamp,
    DELETED_BY varchar(50),
    --
    IMAGE_ID uuid,
    NAME varchar(255),
    LOCATION varchar(50),
    DATE_ date,
    PARTNER_ID uuid,
    --
    primary key (ID)
)^
-- end UNIQCARS_MEDIA
-- begin UNIQCARS_MODEL_RANGE
create table UNIQCARS_MODEL_RANGE (
    ID uuid,
    VERSION integer not null,
    CREATE_TS timestamp,
    CREATED_BY varchar(50),
    UPDATE_TS timestamp,
    UPDATED_BY varchar(50),
    DELETE_TS timestamp,
    DELETED_BY varchar(50),
    --
    IMAGE_ID uuid,
    NAME varchar(255),
    PRICE decimal(19, 2),
    PARTNER_ID uuid,
    --
    primary key (ID)
)^
-- end UNIQCARS_MODEL_RANGE

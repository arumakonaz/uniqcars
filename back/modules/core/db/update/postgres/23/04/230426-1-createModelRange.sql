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
);
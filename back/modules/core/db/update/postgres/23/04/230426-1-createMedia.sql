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
);
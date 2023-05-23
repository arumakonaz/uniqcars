create table UNIQCARS_ITEM_FILE_DESCRIPTOR_LINK (
    ITEM_ID varchar(36) not null,
    FILE_DESCRIPTOR_ID varchar(36) not null,
    primary key (ITEM_ID, FILE_DESCRIPTOR_ID)
);

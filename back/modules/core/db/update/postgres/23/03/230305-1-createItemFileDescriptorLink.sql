create table UNIQCARS_ITEM_FILE_DESCRIPTOR_LINK (
    ITEM_ID uuid,
    FILE_DESCRIPTOR_ID uuid,
    primary key (ITEM_ID, FILE_DESCRIPTOR_ID)
);

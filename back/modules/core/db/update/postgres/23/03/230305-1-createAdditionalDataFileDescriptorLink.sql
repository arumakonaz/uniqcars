create table UNIQCARS_ADDITIONAL_DATA_FILE_DESCRIPTOR_LINK (
    ADDITIONAL_DATA_ID uuid,
    FILE_DESCRIPTOR_ID uuid,
    primary key (ADDITIONAL_DATA_ID, FILE_DESCRIPTOR_ID)
);
create table UNIQCARS_CAR_FILE_DESCRIPTOR_LINK (
    CAR_ID uuid,
    FILE_DESCRIPTOR_ID uuid,
    primary key (CAR_ID, FILE_DESCRIPTOR_ID)
);

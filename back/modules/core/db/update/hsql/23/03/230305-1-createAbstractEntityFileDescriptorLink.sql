create table UNIQCARS_ABSTRACT_ENTITY_FILE_DESCRIPTOR_LINK (
    ABSTRACT_ENTITY_ID varchar(36) not null,
    FILE_DESCRIPTOR_ID varchar(36) not null,
    primary key (ABSTRACT_ENTITY_ID, FILE_DESCRIPTOR_ID)
);

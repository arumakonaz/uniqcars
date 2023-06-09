alter table UNIQCARS_MODEL_RANGE add constraint FK_UNIQCARS_MODEL_RANGE_ON_IMAGE foreign key (IMAGE_ID) references SYS_FILE(ID);
alter table UNIQCARS_MODEL_RANGE add constraint FK_UNIQCARS_MODEL_RANGE_ON_PARTNER foreign key (PARTNER_ID) references UNIQCARS_PARTNER(ID);
create index IDX_UNIQCARS_MODEL_RANGE_ON_IMAGE on UNIQCARS_MODEL_RANGE (IMAGE_ID);
create index IDX_UNIQCARS_MODEL_RANGE_ON_PARTNER on UNIQCARS_MODEL_RANGE (PARTNER_ID);

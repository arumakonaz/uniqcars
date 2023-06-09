alter table UNIQCARS_MEDIA add constraint FK_UNIQCARS_MEDIA_ON_IMAGE foreign key (IMAGE_ID) references SYS_FILE(ID);
alter table UNIQCARS_MEDIA add constraint FK_UNIQCARS_MEDIA_ON_PARTNER foreign key (PARTNER_ID) references UNIQCARS_PARTNER(ID);
create index IDX_UNIQCARS_MEDIA_ON_IMAGE on UNIQCARS_MEDIA (IMAGE_ID);
create index IDX_UNIQCARS_MEDIA_ON_PARTNER on UNIQCARS_MEDIA (PARTNER_ID);

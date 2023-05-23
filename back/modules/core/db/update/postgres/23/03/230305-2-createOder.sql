alter table UNIQCARS_ODER add constraint FK_UNIQCARS_ODER_ON_ITEM foreign key (ITEM_ID) references UNIQCARS_ITEM(ID);
create index IDX_UNIQCARS_ODER_ON_ITEM on UNIQCARS_ODER (ITEM_ID);

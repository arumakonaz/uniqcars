alter table UNIQCARS_ORDER add constraint FK_UNIQCARS_ORDER_ON_CAR foreign key (CAR_ID) references UNIQCARS_CAR(ID);
create index IDX_UNIQCARS_ORDER_ON_CAR on UNIQCARS_ORDER (CAR_ID);

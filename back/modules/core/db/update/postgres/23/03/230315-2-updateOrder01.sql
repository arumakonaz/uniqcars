alter table UNIQCARS_ORDER add constraint FK_UNIQCARS_ORDER_ON_BANK_CARD foreign key (BANK_CARD_ID) references UNIQCARS_BANK_CARD(ID);
create index IDX_UNIQCARS_ORDER_ON_BANK_CARD on UNIQCARS_ORDER (BANK_CARD_ID);

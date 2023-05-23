update UNIQCARS_BANK_CARD set NUMBER_ = 0 where NUMBER_ is null ;
alter table UNIQCARS_BANK_CARD alter column NUMBER_ set not null ;
update UNIQCARS_BANK_CARD set EXPIRE_DATE = current_date where EXPIRE_DATE is null ;
alter table UNIQCARS_BANK_CARD alter column EXPIRE_DATE set not null ;
update UNIQCARS_BANK_CARD set NAME_ON_CARD = '' where NAME_ON_CARD is null ;
alter table UNIQCARS_BANK_CARD alter column NAME_ON_CARD set not null ;

alter table UNIQCARS_BANK_CARD rename column number_ to number___u67490 ;
alter table UNIQCARS_BANK_CARD alter column number___u67490 drop not null ;
alter table UNIQCARS_BANK_CARD add column NUMBER_ varchar(255) ^
update UNIQCARS_BANK_CARD set NUMBER_ = '' where NUMBER_ is null ;
alter table UNIQCARS_BANK_CARD alter column NUMBER_ set not null ;

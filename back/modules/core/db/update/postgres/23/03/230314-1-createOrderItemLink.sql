create table UNIQCARS_ORDER_ITEM_LINK (
    ORDER_ID uuid,
    ITEM_ID uuid,
    primary key (ORDER_ID, ITEM_ID)
);

create table UNIQCARS_ORDER_CAR_LINK (
    ORDER_ID uuid,
    CAR_ID uuid,
    primary key (ORDER_ID, CAR_ID)
);

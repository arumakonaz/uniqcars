package kz.almaty.uniqcars.service;

import kz.almaty.uniqcars.entity.pojo.ItemPojo;
import kz.almaty.uniqcars.entity.pojo.ItemsPojoList;

import javax.validation.constraints.NotNull;
import java.util.UUID;

public interface ItemService {
    String NAME = "uniqcars_ItemService";

    ItemsPojoList getItems(@NotNull String type, @NotNull Integer page,String locale) throws Exception;

    ItemPojo getItemById(@NotNull UUID itemId,String locale) throws Exception;
}
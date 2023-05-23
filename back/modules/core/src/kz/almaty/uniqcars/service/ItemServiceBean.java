package kz.almaty.uniqcars.service;

import com.haulmont.cuba.core.entity.FileDescriptor;
import com.haulmont.cuba.core.global.DataManager;
import com.haulmont.cuba.core.global.Metadata;
import com.haulmont.cuba.core.global.UserSessionSource;
import kz.almaty.uniqcars.config.LoadingConfig;
import kz.almaty.uniqcars.entity.AdditionalData;
import kz.almaty.uniqcars.entity.Item;
import kz.almaty.uniqcars.entity.pojo.AdditionalDataPojo;
import kz.almaty.uniqcars.entity.pojo.ImagePojo;
import kz.almaty.uniqcars.entity.pojo.ItemPojo;
import kz.almaty.uniqcars.entity.pojo.ItemsPojoList;
import org.springframework.stereotype.Service;

import javax.inject.Inject;
import javax.validation.constraints.NotNull;
import java.util.*;

@Service(ItemService.NAME)
public class ItemServiceBean implements ItemService {

    private static final String ITEM_QUERY = "select e from uniqcars_Item e";
    private static final String ITEM_TYPE_QUERY = ITEM_QUERY + " where e.itemType.code = '%s'";
    @Inject
    protected DataManager dataManager;
    @Inject
    protected LoadingConfig loadingConfig;
    @Inject
    protected CommonService commonService;
    @Inject
    protected Metadata metadata;
    @Inject
    protected LoggerService loggerService;
    @Inject
    protected FileDowloaderService fileDowloaderService;
    @Inject
    protected UserSessionSource userSessionSource;
    @Inject
    protected UserService userService;
    @Inject
    protected FileDescriptorService fileDescriptorService;


    @Override
    public ItemsPojoList getItems(@NotNull String type, @NotNull Integer page, String locale) throws Exception {

        try {

            if (locale != null && !locale.isEmpty())
                userService.changeUserSessionLocale(locale);

            Integer maxResults = loadingConfig.getMaxPerPage();
            Integer firstResult = getFirstResult(page, maxResults);

            List<Item> items = dataManager.load(Item.class)
                    .query(String.format(ITEM_TYPE_QUERY, type))
                    .view("item-getAllview")
                    .maxResults(maxResults)
                    .firstResult(firstResult)
                    .list();

            if (items.isEmpty())
                return null;

            return itemsToPojo(items, type, maxResults);
        } catch (Exception exception) {
            loggerService.saveLog("ItemServiceBean.getItems", type + " :type,page: " + page, exception);
            throw new Exception("Обратитесь к администратору");
        }

    }

    @Override
    public ItemPojo getItemById(@NotNull UUID itemId, String locale) throws Exception {

        if (locale != null && !locale.isEmpty())
            userService.changeUserSessionLocale(locale);

        try {

            Item item = dataManager.load(Item.class)
                    .id(itemId)
                    .view("item-getAllview")
                    .optional().orElse(null);

            if (item == null)
                return null;

            return itemToPojo(item);
        } catch (Exception exception) {
            loggerService.saveLog("ItemServiceBean.getItemById", itemId, exception);
            throw new Exception("Обратитесь к администратору");
        }

    }


    private Integer getFirstResult(Integer page, Integer maxResults) {
        if (page == 1)
            return 0;

        return maxResults * page - 1;
    }


    private ItemPojo itemToPojo(Item item) {
        List<AdditionalDataPojo> additionalDataPojos = new ArrayList<>();
        ItemPojo itemPojo = getItemPojoFromItem(item);

        for (AdditionalData additionalData : item.getAdditionalDatas()) {
            AdditionalDataPojo additionalDataPojo = new AdditionalDataPojo();
            additionalDataPojo.setId(additionalData.getId());
            additionalDataPojo.setName(additionalData.getType().getName());
            additionalDataPojo.setOrder(additionalData.getType().getOrder());
            additionalDataPojo.setValue(additionalData.getValue());
            additionalDataPojos.add(additionalDataPojo);
        }
        itemPojo.setAdditionalData(additionalDataPojos);
        return itemPojo;
    }

    private ItemPojo getItemPojoFromItem(Item item) {
        ItemPojo itemPojo = metadata.create(ItemPojo.class);
        itemPojo.setId(item.getId());
        itemPojo.setName(item.getName());
        itemPojo.setPrice(item.getPrice());
        itemPojo.setImages(fileDescriptorService.getImages(item.getImages()));
        itemPojo.setDescription(item.getDescription());
        return itemPojo;
    }

    private ItemsPojoList itemsToPojo(List<Item> items, String type, Integer maxResults) {
        Integer maxPage = commonService.getCount(Item.class, String.format(ITEM_TYPE_QUERY, type), null).intValue() / maxResults;

        List<ItemPojo> itemPojos = new ArrayList<>();

        ItemsPojoList itemsPojoList = metadata.create(ItemsPojoList.class);
        itemsPojoList.setMaxPage(maxPage <= 0 ? 1 : maxPage);
        items.forEach(e -> itemPojos.add(getItemPojoFromItem(e)));

        itemsPojoList.setItems(itemPojos);

        return itemsPojoList;
    }
}
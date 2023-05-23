package kz.almaty.uniqcars.service;

import com.haulmont.cuba.core.entity.Entity;
import com.haulmont.cuba.core.global.DataManager;
import com.haulmont.cuba.core.global.LoadContext;
import com.haulmont.cuba.core.global.Metadata;
import org.springframework.stereotype.Service;

import javax.inject.Inject;
import java.util.Map;

@Service(CommonService.NAME)
public class CommonServiceBean implements CommonService {


    @Inject
    protected Metadata metadata;
    @Inject
    protected DataManager dataManager;

    @Override
    public <T extends Entity> T getEntity(Class<T> entityClass, String code) {
        LoadContext<T> loadContext = LoadContext.create(entityClass);

        loadContext.setQuery(LoadContext
                .createQuery("select e from " + metadata.getClassNN(entityClass)
                        .getName() + " e where e.code = :code")
                .setParameter("code", code));
        return dataManager.load(loadContext);
    }

    @Override
    public <T extends Entity> Long getCount(Class<T> tClass, String queryString, Map<String, Object> params) {
        return dataManager.getCount(getLoadContext(tClass, queryString, params));
    }

    public <T extends Entity> LoadContext<T> getLoadContext(Class<T> entityClass, String queryString, Map<String, Object> map) {
        LoadContext<T> loadContext = LoadContext.create(entityClass);
        LoadContext.Query query = LoadContext.createQuery(queryString);
        if (map != null && map.size() > 0) {
            for (Map.Entry<String, Object> entry : map.entrySet()) {
                query.setParameter(entry.getKey().replace(".", ""), entry.getValue());
            }
        }
        loadContext.setQuery(query);
        return loadContext;
    }
}
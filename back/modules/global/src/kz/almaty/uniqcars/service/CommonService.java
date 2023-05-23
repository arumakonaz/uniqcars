package kz.almaty.uniqcars.service;

import com.haulmont.cuba.core.entity.Entity;

import java.util.Map;

public interface CommonService {
    String NAME = "uniqcars_CommonService";

    public <T extends Entity> T getEntity(Class<T> entityClass, String code);

    <T extends Entity> Long getCount(Class<T> tClass, String queryString, Map<String, Object> params);
}
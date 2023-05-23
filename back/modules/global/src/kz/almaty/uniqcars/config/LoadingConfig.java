package kz.almaty.uniqcars.config;

import com.haulmont.cuba.core.config.Config;
import com.haulmont.cuba.core.config.Property;
import com.haulmont.cuba.core.config.Source;
import com.haulmont.cuba.core.config.SourceType;
import com.haulmont.cuba.core.config.defaults.DefaultInteger;
import com.haulmont.cuba.core.config.defaults.DefaultString;

@Source(type = SourceType.DATABASE)
public interface LoadingConfig extends Config {

    @DefaultInteger(5)
    @Property("kz.almaty.uniqcars.load.magePerPage")
    Integer getMaxPerPage();


    @Property("kz.almaty.uniqcars.availableLocales")
    @DefaultString("kz,en,ru")
    String getAvailableLocales();

    @Property("kz.almaty.uniqcars.amazonLink")
    @DefaultString("https://uniqcars.s3.eu-central-1.amazonaws.com/")
    String getAmazonLink();
}
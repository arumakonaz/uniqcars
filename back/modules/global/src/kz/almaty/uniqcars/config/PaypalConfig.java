package kz.almaty.uniqcars.config;

import com.haulmont.cuba.core.config.Config;
import com.haulmont.cuba.core.config.Property;
import com.haulmont.cuba.core.config.Source;
import com.haulmont.cuba.core.config.SourceType;
import com.haulmont.cuba.core.config.defaults.DefaultString;
import com.haulmont.cuba.core.global.Secret;

@Source(type = SourceType.DATABASE)
public interface PaypalConfig extends Config {

    @Property("kz.almaty.uniqcars.paypal.username")
    @DefaultString("Abhrc7X2scctFYLtTx3FTSTR0obZQqQ_CeTgc563c5YyLzKbQj3HOjLt4GAfdndkjHSh95z4HQUjMmo8")
    String getPaypalUsername();

    @Property("kz.almaty.uniqcars.paypal.password")
    @Secret
    @DefaultString("EG6OAncDZU9-2rg9Hp7MQlkxGSxQOUsi5S3oJYj-PBetecLH3blYfQuYFjVW198rhqpFgtp87smthlF5")
    String getPaypalPassword();

    @Property("kz.almaty.uniqcars.paypal.url")
    @DefaultString("https://api-m.sandbox.paypal.com")
    String getPaypalUrl();
}
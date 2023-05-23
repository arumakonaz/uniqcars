package kz.almaty.uniqcars.service;

import com.haulmont.cuba.core.global.Messages;
import com.haulmont.cuba.core.global.Metadata;
import kz.almaty.uniqcars.entity.Location;
import kz.almaty.uniqcars.entity.pojo.LocationListPojo;
import kz.almaty.uniqcars.entity.pojo.LocationPojo;
import org.springframework.stereotype.Service;

import javax.inject.Inject;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

@Service(LocationService.NAME)
public class LocationServiceBean implements LocationService {

    @Inject
    protected Messages messages;
    @Inject
    protected Metadata metadata;
    @Inject
    protected UserService userService;
    @Inject
    protected LoggerService loggerService;

    @Override
    public String getLocationLocale(Location location) {
        return messages.getMessage(Location.class, "Location." + location.name());
    }

    public LocationListPojo getLocations(String locale) {
        try {
            if (locale != null && !locale.isEmpty())
                userService.changeUserSessionLocale(locale);

            LocationListPojo locationListPojo = metadata.create(LocationListPojo.class);

            List<LocationPojo> locations = new ArrayList<>();

            for (Location value : Location.values()) {
                LocationPojo locationPojo = metadata.create(LocationPojo.class);
                locationPojo.setCode(value.getId());
                locationPojo.setValue(getLocationLocale(value));
                locations.add(locationPojo);
            }
            locationListPojo.setLocations(locations);
            return locationListPojo;
        } catch (Exception exception) {
            loggerService.saveLog("LocationServiceBean.getLocations", locale, exception);
            return null;
        }

    }
}
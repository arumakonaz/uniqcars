package kz.almaty.uniqcars.service;

import kz.almaty.uniqcars.entity.Location;
import kz.almaty.uniqcars.entity.pojo.LocationListPojo;

public interface LocationService {
    String NAME = "uniqcars_LocationService";

    String getLocationLocale(Location location);

    LocationListPojo getLocations(String locale);
}
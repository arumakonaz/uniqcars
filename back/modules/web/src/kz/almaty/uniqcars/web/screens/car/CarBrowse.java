package kz.almaty.uniqcars.web.screens.car;

import com.haulmont.cuba.gui.screen.*;
import kz.almaty.uniqcars.entity.Car;

@UiController("uniqcars_Car.browse")
@UiDescriptor("car-browse.xml")
@LookupComponent("carsTable")
@LoadDataBeforeShow
public class CarBrowse extends StandardLookup<Car> {
}
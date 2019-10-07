package com.fed.androidschool_converter;

import androidx.annotation.StringRes;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;


public enum Conversion {
    LENGTH(R.string.length, Arrays.asList(Unit.METRES, Unit.KILOMETERES, Unit.MILE)),
    WEIGHT (R.string.weight, Arrays.asList(Unit.KILOGRAM, Unit.GRAM)),
    AREA(R.string.area, Collections.emptyList()),
    FORCE(R.string.force, Collections.emptyList()),
    VOLUME(R.string.volume, Collections.emptyList()),
    PRESSURE(R.string.pressure, Collections.emptyList()),
    VOLTAGE(R.string.voltage, Collections.emptyList()),
    POWER(R.string.power, Collections.emptyList()),
    ENERGY(R.string.energy, Collections.emptyList()),
    TEMPRETURE(R.string.tempreture, Collections.emptyList());

    @StringRes
    final int strRes;
    final List<Unit> units;

    Conversion(@StringRes int strRes, List<Unit> units){
        this.strRes = strRes;
        this.units = units;
    }
}

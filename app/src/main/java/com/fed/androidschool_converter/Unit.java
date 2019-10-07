package com.fed.androidschool_converter;

import androidx.annotation.StringRes;


public enum Unit {
    KILOMETERES(R.string.kilometeres, 0.001, 1000),
    METRES(R.string.metres, 1, 1),
    MILE(R.string.mile, 0.625, 1.6),
    KILOGRAM(R.string.kilogram, 1, 1),
    GRAM(R.string.gram, 0.001, 1000);

    @StringRes
    final int labelResource;
    final double conversionToBase;
    final double conversionFromBase;

    Unit(@StringRes int res, double conversionToBase, double conversionFromBase){
        labelResource = res;
        this.conversionFromBase=conversionFromBase;
        this.conversionToBase = conversionToBase;
    }


}

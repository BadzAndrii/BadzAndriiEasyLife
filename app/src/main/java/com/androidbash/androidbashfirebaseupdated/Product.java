package com.androidbash.androidbashfirebaseupdated;

/**
 * Created by andri on 02.09.2017.
 */

public class Product {
    public String name;
    public String price;
    public boolean box;


    public Product(String _describe, String _price, boolean _box) {
        name = _describe;
        price = _price;
        box = _box;
    }

    public String getStartTime() {
        return name;
    }

}

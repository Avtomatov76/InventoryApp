package com.example.android.inventoryapp.data;

import android.provider.BaseColumns;

public final class ProductContract {

    private ProductContract() {}

    public static abstract class ProductEntry implements BaseColumns {

        public static final String TABLE_NAME = "products";

        public static final String _ID = BaseColumns._ID;
        public static final String COLUMN_PRODUCT_NAME = "Product Name";
        public static final String COLUMN_PRODUCT_PRICE = "Price";
        public static final String COLUMN_PRODUCT_QUANTITY = "Quantity";
        public static final String COLUMN_PRODUCT_SUPPLIER = "Supplier Name";
        public static final String COLUMN_PRODUCT_SUPPLIER_PHONE = "Supplier Phone Number";
    }
}

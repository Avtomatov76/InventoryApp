package com.example.android.inventoryapp;

import android.content.Context;
import android.database.Cursor;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CursorAdapter;
import android.widget.TextView;

import com.example.android.inventoryapp.data.ProductContract.ProductEntry;


/**
 * An adapter for a list that uses product data to create list items for each row
 * of product data in the Cursor.
 */
public class ProductCursorAdapter extends CursorAdapter {

    public ProductCursorAdapter(Context context, Cursor c) {
        super(context, c, 0);
    }

    // This method make a new blank list item view.
    @Override
    public View newView(Context context, Cursor cursor, ViewGroup parent) {
        return LayoutInflater.from(context).inflate(R.layout.list_item, parent, false);
    }

    // This method binds the product data to a given list item layout.
    @Override
    public void bindView(View view, Context context, Cursor cursor) {
        // Find individual views to modify in the list item layout
        TextView productNameTextView = (TextView) view.findViewById(R.id.product_name);
        TextView productPriceTextView = (TextView) view.findViewById(R.id.product_price);
        TextView productQuantityTextView = (TextView) view.findViewById(R.id.product_quantity);

        // Find the columns of product attributes from the Cursor for the current product.
        int nameColumnIndex = cursor.getColumnIndex(ProductEntry.COLUMN_PRODUCT_NAME);
        int priceColumnIndex = cursor.getColumnIndex(ProductEntry.COLUMN_PRODUCT_PRICE);
        int quantityColumnIndex = cursor.getColumnIndex(ProductEntry.COLUMN_PRODUCT_QUANTITY);

        // Read the product attributes from the Cursor for the current product.
        String name = cursor.getString(nameColumnIndex);
        int price = cursor.getInt(priceColumnIndex);
        int quantity = cursor.getInt(quantityColumnIndex);

        // Update the TextViews with the attributes for the current product.
        productNameTextView.setText(name);
        productPriceTextView.setText(price);
        productQuantityTextView.setText(quantity);
    }
}


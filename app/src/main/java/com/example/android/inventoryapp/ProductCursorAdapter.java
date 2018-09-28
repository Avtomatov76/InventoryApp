package com.example.android.inventoryapp;

import android.content.ContentUris;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CursorAdapter;
import android.widget.TextView;
import android.widget.Toast;

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
    public void bindView(View view, final Context context, Cursor cursor) {
        // Find individual views to modify in the list item layout
        TextView productNameTextView = view.findViewById(R.id.product_name);
        TextView productPriceTextView = view.findViewById(R.id.product_price);
        TextView productQuantityTextView = view.findViewById(R.id.product_quantity);
        Button sellItemButton = view.findViewById(R.id.sell_item);

        // Find the columns of product attributes from the Cursor for the current product.
        int nameColumnIndex = cursor.getColumnIndex(ProductEntry.COLUMN_PRODUCT_NAME);
        int priceColumnIndex = cursor.getColumnIndex(ProductEntry.COLUMN_PRODUCT_PRICE);
        int quantityColumnIndex = cursor.getColumnIndex(ProductEntry.COLUMN_PRODUCT_QUANTITY);

        // Read the product attributes from the Cursor for the current product.
        String name = cursor.getString(nameColumnIndex);
        int price = cursor.getInt(priceColumnIndex);
        final int quantity = cursor.getInt(quantityColumnIndex);

        // Update the TextViews with the attributes for the current product.
        productNameTextView.setText(name);
        productPriceTextView.setText(Integer.toString(price));
        productQuantityTextView.setText(Integer.toString(quantity));

        // Extracting a String value for quantity and translating it into an Integer
        // to perform the "sale" function.
        String quantityString = cursor.getString(quantityColumnIndex);
        final int currentQuantity = Integer.valueOf(quantityString);

        // Getting an item's ID.
        final int itemId = cursor.getInt(cursor.getColumnIndex(ProductEntry._ID));

        // Setting up "sale" functionality, where the quantity is decreased by 1
        // and updated using CONTENT_URI and ContentValues.
        sellItemButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (currentQuantity > 0) {
                    int newQuantity = currentQuantity - 1;

                    Uri quantityUri = ContentUris.withAppendedId(ProductEntry.CONTENT_URI, itemId);

                    ContentValues values = new ContentValues();
                    values.put(ProductEntry.COLUMN_PRODUCT_QUANTITY, newQuantity);
                    context.getContentResolver().update(quantityUri, values, null,
                            null);
                    Toast.makeText(context, R.string.quantity_updated, Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(context, R.string.no_more_items,
                            Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}


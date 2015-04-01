package example.com.databaseapplication;


import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.DatabaseUtils;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Log;

import java.io.ByteArrayOutputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;


/**
 * Created by Alexandru on 21-Mar-15.
 */
public class DatabaseHelper extends SQLiteOpenHelper {

    protected static SQLiteDatabase mydb;
    public static final String DATABASE_NAME = "ourDatabase.db";
    public static final String TABLE_NAME = "products";
    public static final String COLUMN_ID = "id";
    public static final String COLUMN_PRODUCT_NAME = "product_name";
    public static final String COLUMN_PRODUCT_QUANTITY = "product_quantity";
    public static final String COLUMN_PRODUCT_IMAGE = "product_image";

    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, 1);
        initDB();
    }

    private void initDB() {
        mydb = this.getWritableDatabase();
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        // TODO Auto-generated method stub
        db.execSQL(
                "create table " + TABLE_NAME + " (" +
                        COLUMN_ID + " integer primary key, " +
                        COLUMN_PRODUCT_NAME + " text, " +
                        COLUMN_PRODUCT_QUANTITY + " integer, " +
                        COLUMN_PRODUCT_IMAGE + " varbinary(2147483647))"
        );
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // TODO Auto-generated method stub
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(db);
    }

    public boolean insertProduct(Product product) {
        if (!mydb.isOpen()) {
            initDB();
        }
        try {
            ContentValues contentValues = new ContentValues();

            contentValues.put(COLUMN_PRODUCT_NAME, product.getProductName());
            contentValues.put(COLUMN_PRODUCT_QUANTITY, product.getProductQuantity());

            ByteArrayOutputStream bos = new ByteArrayOutputStream();
            product.getProductImage().compress(Bitmap.CompressFormat.PNG, 100, bos);
            byte[] bArray = bos.toByteArray();
            contentValues.put(COLUMN_PRODUCT_IMAGE, bArray);

            mydb.insert(TABLE_NAME, null, contentValues);

            return true;
        } catch (Exception e) {
            e.printStackTrace();
            mydb.close();
        }
        return false;
    }

    public ArrayList<Product> getAllProducts() {
        if (!mydb.isOpen()) {
            initDB();
        }

        ArrayList<Product> products = new ArrayList<>();
        try {
            Cursor cursor = mydb.query(TABLE_NAME,
                    null, null, null, null, null, null);
            Integer columnIdIndex = cursor.getColumnIndex(COLUMN_ID);
            Integer columnNameIndex = cursor.getColumnIndex(COLUMN_PRODUCT_NAME);
            Integer columnQuantityIndex = cursor.getColumnIndex(COLUMN_PRODUCT_QUANTITY);
            Integer columnImageIndex = cursor.getColumnIndex(COLUMN_PRODUCT_IMAGE);

            if (cursor.moveToFirst()) {
                do {
                    int id = cursor.getInt(columnIdIndex);
                    String name = cursor.getString(columnNameIndex);
                    int quantity = cursor.getInt(columnQuantityIndex);
                    byte[] data = cursor.getBlob(columnImageIndex);
                    Bitmap bitmap = BitmapFactory.decodeByteArray(data, 0, data.length);

                    products.add(new Product(name, quantity, bitmap, id));
                } while (cursor.moveToNext());
            }
        } catch (Exception e) {
            e.printStackTrace();
            mydb.close();
        }

        return products;
    }

    public int getProductQuantity(String productName) {
        if (!mydb.isOpen()) {
            initDB();
        }

        try {
            Cursor cursor = mydb.query(TABLE_NAME,
                    new String[]{COLUMN_PRODUCT_QUANTITY},
                    COLUMN_PRODUCT_NAME + " = ?",
                    new String[]{productName},
                    null, null, null);

            if (cursor.moveToFirst()) {
                do {
                    Integer columnIndex = cursor.getColumnIndex(COLUMN_PRODUCT_QUANTITY);
                    int quantity = cursor.getInt(columnIndex);
                    return quantity;
                } while (cursor.moveToNext());
            }
        } catch (Exception e) {
            e.printStackTrace();
            mydb.close();
        }

        return 0;
    }

    private void updateProduct(String productName, int productQuantity) {
        if (!mydb.isOpen()) {
            initDB();
        }
        try {
            ContentValues contentValues = new ContentValues();
            contentValues.put(COLUMN_PRODUCT_QUANTITY, productQuantity);
            mydb.update(TABLE_NAME, contentValues, productName + " = ? ", new String[]{productName});
        } catch (Exception e) {
            e.printStackTrace();
            mydb.close();
        }
    }

    public int numberOfRows() {
        if (!mydb.isOpen()) {
            initDB();
        }
        try {
            int numRows = (int) DatabaseUtils.queryNumEntries(mydb, TABLE_NAME);
            return numRows;
        } catch (Exception e) {
            e.printStackTrace();
            mydb.close();
        }
        return 0;
    }

    public Integer deleteProduct(String productName) {
        if (!mydb.isOpen()) {
            initDB();
        }
        try {
            return mydb.delete(TABLE_NAME,
                    COLUMN_PRODUCT_NAME + " = ? ",
                    new String[]{productName});
        } catch (Exception e) {
            e.printStackTrace();
            mydb.close();
        }
        return -1;
    }

}

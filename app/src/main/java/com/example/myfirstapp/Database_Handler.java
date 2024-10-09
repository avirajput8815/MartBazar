package com.example.myfirstapp;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.DatabaseErrorHandler;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;


public class Database_Handler extends SQLiteOpenHelper {

    private static final int DATABASE_VERSION = 8;
    private static final String DATABASE_NAME = "EcomApp";
    private static final String TABLE_PRODUCTS = "Products";
    private static final String KEY_ID = "id";
    private static final String KEY_TITLE = "title";
    private static final String KEY_DESCRIPTION = "description";
    private static final String KEY_PRICE = "price";
    private static final String KEY_DISCOUNT_PERCENTAGE = "discountPercentage";
    private static final String KEY_RATING = "rating";
    private static final String KEY_STOCK = "stock";
    private static final String KEY_BRAND = "brand";
    private static final String KEY_CATEGORY = "category";
    private static final String KEY_THUMBNAIL = "thumbnail";
    private static final String KEY_QUANTITY = "quantity";
    private static final String KEY_PRODUCT_ID = "ProductId";

    private  static final String TABLE_IMAGES = "Product_Images";
    private static final String KEY_IMAGES = "images";
    private static final String KEY_PRODUCT_ID_FK = "ProductFkId";

    private  static Database_Handler databaseHandler;

    // Contructor
    private Database_Handler(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        String CREATE_CONTACTS_TABLE = "CREATE TABLE " + TABLE_PRODUCTS + "(" + KEY_ID + " INTEGER PRIMARY KEY," + KEY_TITLE + " TEXT,"
                + KEY_DESCRIPTION + " TEXT," + KEY_PRICE + " TEXT," + KEY_DISCOUNT_PERCENTAGE + " TEXT," + KEY_RATING + " TEXT,"
                + KEY_STOCK + " INTEGER," + KEY_BRAND + " TEXT," + KEY_CATEGORY + " TEXT,"  + KEY_THUMBNAIL + " TEXT,"
                + KEY_QUANTITY + " INTEGER,"
                + KEY_PRODUCT_ID + " INTEGER "
                +")";
        sqLiteDatabase.execSQL(CREATE_CONTACTS_TABLE);

        String CREATE_IMAGE_TABLE = "CREATE TABLE " + TABLE_IMAGES + "("
                + KEY_ID + " INTEGER PRIMARY KEY,"
                + KEY_IMAGES + " TEXT,"
                + KEY_PRODUCT_ID_FK + " INTEGER,"
                + " FOREIGN KEY (" + KEY_PRODUCT_ID_FK + ") REFERENCES "
                + TABLE_PRODUCTS + "(" + KEY_PRODUCT_ID + ")"
                + ")";
        sqLiteDatabase.execSQL(CREATE_IMAGE_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int oldVersion, int newVersion) {

        // Drop older table if existed
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + TABLE_PRODUCTS);
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + TABLE_IMAGES);
        // Create tables again
        onCreate(sqLiteDatabase);
    }

    public  static  Database_Handler getInstance(Context context){
        if(databaseHandler == null){
            databaseHandler = new Database_Handler(context);
        }
        return databaseHandler;
    }

    void addToCart(Product product) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(KEY_TITLE, product.getTitle());
        values.put(KEY_DESCRIPTION, product.getDescription());
        values.put(KEY_PRICE, product.getPrice());
        values.put(KEY_DISCOUNT_PERCENTAGE, product.getDiscountPercentage());
        values.put(KEY_RATING, product.getRating());
        values.put(KEY_STOCK, product.getStock());
        values.put(KEY_BRAND, product.getBrand());
        values.put(KEY_CATEGORY, product.getCategory());
        values.put(KEY_THUMBNAIL, product.getThumbnail());
        values.put(KEY_QUANTITY, product.getQuantity());
        values.put(KEY_PRODUCT_ID,product.getId());

        // Inserting Row
        db.insert(TABLE_PRODUCTS, null, values);
        //2nd argument is String containing nullColumnHack

        addImages(product.getImages(), product.getId());
    }

    public void addImages(List<String> images ,  int productId){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        if(images !=null && images.size()>0){
            for(int i =0;i<images.size();i++){
                values.put(KEY_IMAGES, images.get(i));
                values.put(KEY_PRODUCT_ID_FK, productId);
                db.insert(TABLE_IMAGES, null, values);
            }

        }

    }

    public List<Product> getProductFromCart(){
        List<Product> productList = new ArrayList<Product>();

        // Select All Query
        String selectQuery = "SELECT  * FROM " + TABLE_PRODUCTS;

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);

        Log.d("cursor.", "" + cursor.getColumnCount());

        // looping through all rows and adding to list
        if (cursor.moveToFirst()) {
            do {
                Product product = new Product();
                product.setId(Integer.parseInt(cursor.getString(0)));
                product.setTitle(cursor.getString(1));
                product.setDescription(cursor.getString(2));
                product.setPrice(cursor.getInt(3));
                product.setDiscountPercentage(cursor.getDouble(4));
                product.setRating(cursor.getDouble(5));
                product.setStock(cursor.getInt(6));
                product.setBrand(cursor.getString(7));
                product.setCategory(cursor.getString(8));
                product.setThumbnail(cursor.getString(9));
                product.setQuantity(cursor.getInt(10));


                // Adding contact to list
                productList.add(product);
            } while (cursor.moveToNext());
        }
        // return contact list
        return productList;
    }

    // deleteProduct

    // updateQuantity

    public int updateQuantity(Product product) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(KEY_QUANTITY, product.getQuantity());

        // updating row
        return db.update(TABLE_PRODUCTS, values, KEY_ID + " = ?", new String[] { String.valueOf(product.getId())});
    }

    // Deleting single contact
    public void deleteQuantity(Product product) {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(TABLE_PRODUCTS, KEY_ID + " = ?", new String[] { String.valueOf(product.getId()) });
//        db.close();


    }


}
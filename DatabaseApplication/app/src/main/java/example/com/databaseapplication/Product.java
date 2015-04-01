package example.com.databaseapplication;

import android.graphics.Bitmap;

/**
 * Created by Alexandru on 21-Mar-15.
 */
public class Product {
    private String productName;
    private int productQuantity;
    private int productID;
    private Bitmap productImage;

    public Product(String productName, int productQuantity, Bitmap productImage) {
        this.productName = productName;
        this.productQuantity = productQuantity;
        this.productImage = productImage;
    }

    public Product(String productName, int productQuantity, Bitmap productImage, int productID) {
        this.productName = productName;
        this.productQuantity = productQuantity;
        this.productImage = productImage;
        this.productID = productID;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public int getProductQuantity() {
        return productQuantity;
    }

    public void setProductQuantity(int productQuantity) {
        this.productQuantity = productQuantity;
    }

    public int getProductID() {
        return productID;
    }

    public void setProductID(int productID) {
        this.productID = productID;
    }

    public Bitmap getProductImage() {
        return productImage;
    }

    public void setProductImage(Bitmap productImage) {
        this.productImage = productImage;
    }
}

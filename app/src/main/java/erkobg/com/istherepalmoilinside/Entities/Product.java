package erkobg.com.istherepalmoilinside.Entities;

import java.util.Date;

/**
 * Created by erkobg on 11/07/2015.
 */
public class Product {
    private String barcode;
    private String name;
    private String Description;
    private boolean HasPalmOil;
    private Date DateCreated;

    public Product() {
    }

    public Product(String barcode, String name, String Description, boolean HasPalmOil) {
        this.barcode = barcode;
        this.name = name;
        this.Description = Description;
        this.HasPalmOil = HasPalmOil;
        this.DateCreated = new Date();
    }


    public String getBarcode() {
        return barcode;
    }

    public void setBarcode(String barcode) {
        this.barcode = barcode;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return Description;
    }

    public void setDescription(String description) {
        Description = description;
    }

    public boolean isHasPalmOil() {
        return HasPalmOil;
    }

    public void setHasPalmOil(boolean hasPalmOil) {
        HasPalmOil = hasPalmOil;
    }

    public Date getDateCreated() {
        return DateCreated;
    }

    public void setDateCreated(Date dateCreated) {
        DateCreated = dateCreated;
    }
}

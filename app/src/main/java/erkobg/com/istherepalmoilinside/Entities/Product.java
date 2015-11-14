package erkobg.com.istherepalmoilinside.Entities;

import com.parse.ParseClassName;
import com.parse.ParseObject;
import com.parse.ParseQuery;

/**
 * Data model for a Products.
 */
@ParseClassName("Products")
public class Product extends ParseObject {

  public static String productClassName = "Products";
  public static String barcodeColumn = "barcode";
  public static String nameColumn = "name";
  public static String descriptionColumn = "description";
  public static String haspalmoilColumn = "haspalmoil";
  public static String usernameColumn = "username";
  //barcode
  public String getBarcode() {
    return getString(barcodeColumn);
  }

  public void setBarcode(String value) {
    put(barcodeColumn, value);
  }

  //name
  public String getName() {
    return getString(nameColumn);
  }

  public void setName(String value) {
    put(nameColumn, value);
  }


  //description
  public String getDescription() {
    return getString(descriptionColumn);
  }

  public void setDescription(String value) {
    put(descriptionColumn, value);
  }


  //description
  public Boolean getHasPalmOil() {
    return getBoolean(haspalmoilColumn);
  }

  public void setHasPalmOil(Boolean value) {
    put(haspalmoilColumn, value);
  }


  public static ParseQuery<Product> getQuery() {
    return ParseQuery.getQuery(Product.class);
  }
}

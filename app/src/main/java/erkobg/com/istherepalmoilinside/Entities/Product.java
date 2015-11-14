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
  //barcode
  public String getBarcode() {
    return getString("barcode");
  }

  public void setBarcode(String value) {
    put("barcode", value);
  }

  //name
  public String getName() {
    return getString("name");
  }

  public void setName(String value) {
    put("name", value);
  }


  //description
  public String getDescription() {
    return getString("description");
  }

  public void setDescription(String value) {
    put("description", value);
  }


  //description
  public Boolean getHasPalmOil() {
    return getBoolean("haspalmoil");
  }

  public void setHasPalmOil(Boolean value) {
    put("haspalmoil", value);
  }


  public static ParseQuery<Product> getQuery() {
    return ParseQuery.getQuery(Product.class);
  }
}

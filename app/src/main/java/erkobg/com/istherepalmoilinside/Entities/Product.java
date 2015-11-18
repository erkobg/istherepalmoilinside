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

  public static ParseQuery<Product> getQuery() {
    return ParseQuery.getQuery(Product.class);
  }

  //barcode
  public String getBarcode() {
    return getString(barcodeColumn);
  }

  public void setBarcode(String value) {
    putValueLimited(barcodeColumn, value, 50);
  }

  //name
  public String getName() {
    return getString(nameColumn);
  }

  public void setName(String value) {
    putValueLimited(nameColumn, value, 50);
  }

  //description
  public String getDescription() {
    return getString(descriptionColumn);
  }

  public void setDescription(String value) {
    putValueLimited(descriptionColumn, value, 300);
  }

  //description
  public Boolean getHasPalmOil() {
    return getBoolean(haspalmoilColumn);
  }

  public void setHasPalmOil(Boolean value) {
    put(haspalmoilColumn, value);
  }

  private void putValueLimited(String field, String value, int limit) {
    if (value.length() > limit)
      put(field, value.substring(0, limit));
    else
      put(field, value);
  }

}

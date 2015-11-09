package erkobg.com.istherepalmoilinside.Utils;

import android.content.Context;
import android.graphics.Paint;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

import erkobg.com.istherepalmoilinside.Entities.Product;
import erkobg.com.istherepalmoilinside.R;

public class ProductAdapter extends ArrayAdapter<Product> {
    private Context mContext;
    private List<Product> mProducts;

    public ProductAdapter(Context context, List<Product> objects) {
        super(context, R.layout.list_products_row_item, objects);
        this.mContext = context;
        this.mProducts = objects;
    }

    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            LayoutInflater mLayoutInflater = LayoutInflater.from(mContext);
            convertView = mLayoutInflater.inflate(R.layout.list_products_row_item, null);
        }

        Product product = mProducts.get(position);

        TextView nameView = (TextView) convertView.findViewById(R.id.product_name);

        nameView.setText(product.getName());


        final ImageView imageView = (ImageView) convertView.findViewById(R.id.image_has_palm_oil);
        if (product.getHasPalmOil()) {
            imageView.setImageResource(R.drawable.thumbs_down);
        } else {
            imageView.setImageResource(R.drawable.thumbs_up);
        }

        return convertView;
    }

}

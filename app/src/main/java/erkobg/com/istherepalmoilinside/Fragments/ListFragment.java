package erkobg.com.istherepalmoilinside.Fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.parse.FindCallback;
import com.parse.ParseException;
import com.parse.ParseQuery;

import java.util.ArrayList;
import java.util.List;

import erkobg.com.istherepalmoilinside.Entities.Product;
import erkobg.com.istherepalmoilinside.R;
import erkobg.com.istherepalmoilinside.Utils.MyBaseFragment;
import erkobg.com.istherepalmoilinside.Utils.ProductAdapter;

public class ListFragment extends MyBaseFragment {

    private ListView mListView;
    private ProductAdapter mAdapter;

    public View onCreateView(LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        View v = inflater.inflate(
                R.layout.list_products_fragment_layout, container, false);
        mAdapter = new ProductAdapter(this.getContext(), new ArrayList<Product>());
        mListView = (ListView) v.findViewById(R.id.product_list);
        mListView.setAdapter(mAdapter);

        updateData();
        return v;


    }

    @Override
    public void onResume() {
        super.onResume();
        updateData();
    }

    public void updateData() {
        ParseQuery<Product> query = ParseQuery.getQuery(Product.class);
        query.findInBackground(new FindCallback<Product>() {

            @Override
            public void done(List<Product> products, ParseException error) {
                if (products != null) {
                    mAdapter.clear();
                    mAdapter.addAll(products);
                }
            }
        });
    }


}

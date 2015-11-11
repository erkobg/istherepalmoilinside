package erkobg.com.istherepalmoilinside.Fragments;

import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.ProgressBar;

import com.parse.FindCallback;
import com.parse.ParseException;
import com.parse.ParseQuery;

import java.util.ArrayList;
import java.util.List;

import erkobg.com.istherepalmoilinside.Entities.Product;
import erkobg.com.istherepalmoilinside.Interfaces.OnDataProcessListener;
import erkobg.com.istherepalmoilinside.MainActivity;
import erkobg.com.istherepalmoilinside.R;
import erkobg.com.istherepalmoilinside.Utils.ProductAdapter;

public class ListProductsFragment extends Fragment implements AdapterView.OnItemClickListener {
    // Store reference to the progress bar later
    private OnDataProcessListener listener;
    ProgressBar progressBarFooter;
    private ListView mListView;
    private ProductAdapter mAdapter;


    public static ListProductsFragment newInstance() {
        ListProductsFragment fragment = new ListProductsFragment();
        return fragment;
    }

    public View onCreateView(LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        View v = inflater.inflate(
                R.layout.list_products_fragment_layout, container, false);
        mAdapter = new ProductAdapter(this.getContext(), new ArrayList<Product>());
        mListView = (ListView) v.findViewById(R.id.list);
        mListView.setAdapter(mAdapter);


        mListView.setOnItemClickListener(this);

        progressBarFooter = (ProgressBar)
                v.findViewById(R.id.pbFooterLoading);
        listener = (MainActivity) getActivity();
        return v;


    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        updateData();

    }




    @Override
    public void onResume() {
        super.onResume();
        updateData();
    }


    public void updateData() {
        progressBarFooter.setVisibility(View.VISIBLE);

        ParseQuery<Product> query = ParseQuery.getQuery(Product.class);
        query.findInBackground(new FindCallback<Product>() {

            @Override
            public void done(List<Product> products, ParseException error) {
                progressBarFooter.setVisibility(View.GONE);
                if (products != null) {
                    mAdapter.clear();
                    mAdapter.addAll(products);
                }
            }
        });
    }


    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        Product sel_prod = mAdapter.getItem(position);
        listener.onProductCheckCompletedSuccess(sel_prod);
    }
}

package erkobg.com.istherepalmoilinside.Fragments;

import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.ProgressBar;

import com.parse.CountCallback;
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

public class ListProductsFragment extends Fragment implements AdapterView.OnItemClickListener, AbsListView.OnScrollListener {
    // Set the limit of products to show
    public int limit = 15;
    public int currentLimit = 0;
    ProgressBar progressBarFooter;
    // Store reference to the progress bar later
    private OnDataProcessListener listener;
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
        mListView.setOnScrollListener(this);
        progressBarFooter = (ProgressBar)
                v.findViewById(R.id.pbFooterLoading);
        listener = (MainActivity) getActivity();
        setTitle(0);

        return v;


    }

    private void setTitle(int count) {
        ((MainActivity) getActivity()).getSupportActionBar().setTitle(String.format("%s (%d)", getString(R.string.title_list), count));
    }


    @Override
    public void onResume() {
        super.onResume();
        mAdapter.clear();
        getProductsCount();
        new LoadMoreDataTask().execute();
    }


    private void getProductsCount() {
        ParseQuery<Product> query = ParseQuery.getQuery(Product.class);
        query.countInBackground(new CountCallback() {

            public void done(int count, ParseException e) {
                if (e == null) {
                    setTitle(count);
                } else {
                    setTitle(0);
                }
            }
        });
    }


    private void getProductsQuery() {
        ParseQuery<Product> query = ParseQuery.getQuery(Product.class);

        query.orderByAscending(Product.nameColumn);
        //Paging
        if (currentLimit != 0)
            query.setSkip(currentLimit);
        // Set the limit of objects to show
        query.setLimit(limit);


        query.findInBackground(new FindCallback<Product>() {

            @Override
            public void done(List<Product> products, ParseException error) {
                progressBarFooter.setVisibility(View.GONE);
                if (products != null && products.size() > 0) {
                    mAdapter.addAll(products);
                    currentLimit += limit;
                }
            }
        });
    }


    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        Product sel_prod = mAdapter.getItem(position);
        listener.onProductSelected(sel_prod);
    }

    @Override
    public void onScrollStateChanged(AbsListView view, int scrollState) {
        int threshold = 1;
        int count = mListView.getCount();

        if (scrollState == SCROLL_STATE_IDLE) {
            if (mListView.getLastVisiblePosition() >= count
                    - threshold) {
                // Execute LoadMoreDataTask AsyncTask
                new LoadMoreDataTask().execute();
            }
        }
    }

    @Override
    public void onScroll(AbsListView view, int firstVisibleItem, int visibleItemCount, int totalItemCount) {
        //nothing
    }

    private class LoadMoreDataTask extends AsyncTask<Void, Void, Void> {
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            // show a progressdialog
            progressBarFooter.setVisibility(View.VISIBLE);

        }

        @Override
        protected Void doInBackground(Void... params) {
            getProductsQuery();
            return null;
        }

        @Override
        protected void onPostExecute(Void result) {
            int position = mListView.getLastVisiblePosition();
            mListView.setSelectionFromTop(position, 0);

        }
    }


}

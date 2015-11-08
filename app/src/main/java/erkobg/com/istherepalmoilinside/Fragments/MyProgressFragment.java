package erkobg.com.istherepalmoilinside.Fragments;


import android.os.Bundle;
import android.os.Handler;
import android.view.*;

import erkobg.com.istherepalmoilinside.R;

import com.devspark.progressfragment.ProgressFragment;


public class MyProgressFragment extends ProgressFragment {
    private View mContentView;
    private Handler mHandler;
    private final Runnable mShowContentRunnable = new Runnable() {

        @Override
        public void run() {
            setContentShown(true);
        }

    };

    public static MyProgressFragment newInstance() {
        return new MyProgressFragment();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        mContentView = inflater.inflate(R.layout.progress_fragment, null);
        return super.onCreateView(inflater, container, savedInstanceState);
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        // Setup content view
        setContentView(mContentView);
        // Setup text for empty content
        setEmptyText(R.string.empty);
        obtainData();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        mHandler.removeCallbacks(mShowContentRunnable);
    }


    private void obtainData() {
        // Show indeterminate progress
        setContentShown(false);

        mHandler = new Handler();
        mHandler.postDelayed(mShowContentRunnable, 8000);
    }
}

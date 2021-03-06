package erkobg.com.istherepalmoilinside;

import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.zxing.integration.android.IntentIntegrator;
import com.google.zxing.integration.android.IntentResult;
import com.parse.ParseUser;

import java.util.Arrays;
import java.util.Collection;

import erkobg.com.istherepalmoilinside.Entities.Product;
import erkobg.com.istherepalmoilinside.Fragments.AboutFragment;
import erkobg.com.istherepalmoilinside.Fragments.HomeFragment;
import erkobg.com.istherepalmoilinside.Fragments.ListProductsFragment;
import erkobg.com.istherepalmoilinside.Fragments.MyProgressFragment;
import erkobg.com.istherepalmoilinside.Fragments.NewProductFragment;
import erkobg.com.istherepalmoilinside.Fragments.ShowProductFragment;
import erkobg.com.istherepalmoilinside.Fragments.User.LoginUserFragment;
import erkobg.com.istherepalmoilinside.Fragments.User.RegisterUserFragment;
import erkobg.com.istherepalmoilinside.Fragments.User.UserDetailsFragment;
import erkobg.com.istherepalmoilinside.Interfaces.OnDataProcessListener;
import erkobg.com.istherepalmoilinside.Utils.CONSTANTS;
import erkobg.com.istherepalmoilinside.Utils.ParseHelper;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener, OnDataProcessListener {
    private boolean viewIsAtHome;
    private boolean backToList;
    private int previousView = 0;
    private NavigationView mNavigationView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Log.d("MainActivity", "onCreate(Bundle savedInstanceState)");
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        // " adds"
        AdView mAdView = (AdView) findViewById(R.id.adView);
        AdRequest adRequest = new AdRequest.Builder().build();
        mAdView.loadAd(adRequest);



        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                callBarcodeIntent();

            }
        });

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        mNavigationView = (NavigationView) findViewById(R.id.nav_view);
        mNavigationView.setNavigationItemSelectedListener(this);
        Menu menuNav = mNavigationView.getMenu();
        ParseUser currentUser = ParseUser.getCurrentUser();
        if (currentUser != null) {

            menuNav.findItem(R.id.nav_log_in).setVisible(false);
            menuNav.findItem(R.id.nav_register).setVisible(false);
        } else {
            menuNav.findItem(R.id.nav_user_details).setVisible(false);
        }
        // Check that the activity is using the layout version with
        // the fragment_container FrameLayout
        if (findViewById(R.id.fragment_container) != null) {

            // However, if we're being restored from a previous state,
            // then we don't need to do anything and should return or else
            // we could end up with overlapping fragments.
            if (savedInstanceState != null) {
                return;
            }

            displayView(R.id.nav_home);
        }

        //Initialy launch the scanner as this is the intended action in most cases
        callBarcodeIntent();
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public void onSaveInstanceState(Bundle outState) {
        //TODO check whether it is working or not?
    }

    private void callBarcodeIntent() {
        if (isCameraAvailable()) {
            previousView = 0;
            previousView = 0;
            IntentIntegrator integrator = new IntentIntegrator(this);
            Collection<String> oDesiredFormats = Arrays.asList("UPC_A", "UPC_E", "EAN_8", "EAN_13", "RSS_14", "UPC_A", "UPC_E", "EAN_8", "EAN_13", "CODE_39", "CODE_93", "CODE_128",
                    "ITF", "RSS_14", "RSS_EXPANDED");

            integrator.setDesiredBarcodeFormats(oDesiredFormats);
            integrator.setPrompt(getString(R.string.string_scan_barcode));
            integrator.setCameraId(0);  // Use a specific camera of the device
            integrator.setBeepEnabled(true);
            integrator.setBarcodeImageEnabled(true);

            integrator.initiateScan();
        } else {
            Toast.makeText(this, R.string.camera_not_available, Toast.LENGTH_SHORT).show();
        }
    }


    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            if (!viewIsAtHome) { //if the current view is not the News fragment
                if (backToList)
                    displayView(R.id.nav_product_list);
                else
                    displayView(R.id.nav_home); //display the Home fragment
            } else {
                super.onBackPressed();
            }

        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        //noinspection SimplifiableIfStatement
     /*   if (id == R.id.action_login) {
            //TODO: handle Login users
            Toast.makeText(this, R.string.login_message, Toast.LENGTH_LONG).show();
            return true;
        }*/
        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {

        displayView(item.getItemId());
        return true;

    }

    private void displayView(int viewId) {
        displayView(viewId, "", null);
    }

    private void displayView(int viewId, Product product) {
        displayView(viewId, "", product);
    }

    private void displayView(int viewId, String barCode) {
        displayView(viewId, barCode, null);
    }

    private void displayView(int viewId, String barCode, Product product) {

        Fragment fragment = null;
        String title = getString(R.string.app_name);

        backToList = false;
        switch (viewId) {
            case R.id.nav_home:
                fragment = new HomeFragment();
                title = getString(R.string.title_home);
                viewIsAtHome = true;
                mNavigationView.setCheckedItem(viewId);
                break;
            case R.id.nav_camera:
                callBarcodeIntent();
                DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
                drawer.closeDrawer(GravityCompat.START);
                //no_big need to continue as this is another activity
                mNavigationView.setCheckedItem(R.id.nav_home);
                return;


            case R.id.nav_register:
                title = getString(R.string.sign_up_label);
                viewIsAtHome = false;
                fragment = new RegisterUserFragment();
                mNavigationView.setCheckedItem(viewId);
                break;
            case R.id.nav_log_in:
                title = getString(R.string.sign_in_label);
                viewIsAtHome = false;
                fragment = new LoginUserFragment();
                mNavigationView.setCheckedItem(viewId);
                break;
            case R.id.nav_user_details:
                title = getString(R.string.user_details_label);
                viewIsAtHome = false;
                fragment = new UserDetailsFragment();
                mNavigationView.setCheckedItem(viewId);
                break;


            case R.id.nav_about:
                fragment = new AboutFragment();
                title = getString(R.string.title_about);
                viewIsAtHome = false;

                mNavigationView.setCheckedItem(viewId);
                break;
            case CONSTANTS.NAVIGATE_SHOW_PROGRESS:
                fragment = new MyProgressFragment();
                title = getResources().getString(R.string.checking);
                viewIsAtHome = false;
                mNavigationView.setCheckedItem(R.id.nav_home);
                break;

            case CONSTANTS.NAVIGATE_ADD_PRODUCT:
                fragment = new NewProductFragment();
                title = getString(R.string.title_new_product);
                Bundle args = new Bundle();
                args.putString(CONSTANTS.ARGUMENT_BAR_CODE, barCode);
                fragment.setArguments(args);
                mNavigationView.setCheckedItem(R.id.nav_home);
                viewIsAtHome = false;
                break;


            case CONSTANTS.NAVIGATE_SHOW_PRODUCT:
                if (previousView == R.id.nav_product_list)
                    backToList = true;
                fragment = new ShowProductFragment();
                title = getString(R.string.title_show_product);
                Bundle args2 = new Bundle();
                args2.putString(CONSTANTS.ARGUMENT_BAR_CODE, product.getBarcode());
                args2.putString(CONSTANTS.ARGUMENT_NAME, product.getName());
                args2.putString(CONSTANTS.ARGUMENT_DESCRIPTION, product.getDescription());
                args2.putBoolean(CONSTANTS.ARGUMENT_HAS_PALM_OIL, product.getHasPalmOil());
                fragment.setArguments(args2);
                mNavigationView.setCheckedItem(R.id.nav_home);
                viewIsAtHome = false;
                break;
            case R.id.nav_product_list:
                title = getString(R.string.title_list);
                fragment = new ListProductsFragment();
                viewIsAtHome = false;
                mNavigationView.setCheckedItem(viewId);
                break;
        }

        previousView = viewId;
        if (fragment != null) {
            FragmentManager fragmentManager = getSupportFragmentManager();

            FragmentTransaction transaction = fragmentManager.beginTransaction();
            transaction.setCustomAnimations(R.anim.enter, R.anim.exit, R.anim.pop_enter, R.anim.pop_exit);

            transaction.replace(R.id.fragment_container, fragment);

            transaction.commitAllowingStateLoss();
        }

        // set the toolbar title
        if (getSupportActionBar() != null) {
            getSupportActionBar().setTitle(title);
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        IntentResult result = IntentIntegrator.parseActivityResult(requestCode, resultCode, data);
        if (result != null) {
            if (result.getContents() == null) {
                displayView(R.id.nav_home); //display the Home fragment
            } else {
                //First load Progress fragment!
                displayView(CONSTANTS.NAVIGATE_SHOW_PROGRESS);
                //then query for progress
                String scannedCode = result.getContents();
                String barcodeFormat = result.getFormatName();
                ParseHelper tmp = ParseHelper.getInstance(this, this);

                try {
                    tmp.CheckProduct(scannedCode, barcodeFormat);
                } catch (Exception e) {
                    e.printStackTrace();
                }

            }
        } else {
            Log.d("MainActivity", "Weird");
            // This is important, otherwise the result will not be passed to the fragment
            super.onActivityResult(requestCode, resultCode, data);
        }
    }

    @Override
    public void onProductSelected(Product product) {
        displayView(CONSTANTS.NAVIGATE_SHOW_PRODUCT, product);

    }

    @Override
    public void onProductCheckCompletedSuccess(Product product) {
        displayView(CONSTANTS.NAVIGATE_SHOW_PRODUCT, product);

    }

    @Override
    public void onProductCheckCompletedFail(String barcode, String barcodeformat) {
        Log.d("Fail", "Not Found");
        Toast.makeText(this, R.string.not_found, Toast.LENGTH_LONG).show();
        displayView(CONSTANTS.NAVIGATE_ADD_PRODUCT, barcode);
    }

    @Override
    public void onDataCheckCancel() {
        Toast.makeText(this, R.string.canceled, Toast.LENGTH_LONG).show();
        displayView(R.id.nav_home);
    }

    @Override
    public void onDataSubmittedSuccess() {
        Toast.makeText(this, R.string.product_added, Toast.LENGTH_LONG).show();
        displayView(R.id.nav_home);
    }

    @Override
    public void onDataSubmittedError(String error) {
        Toast.makeText(this, error, Toast.LENGTH_LONG).show();
        displayView(R.id.nav_home);
    }

    @Override
    public void onUserCreated() {
        Toast.makeText(this, R.string.user_registered, Toast.LENGTH_LONG).show();
        displayView(R.id.nav_home);
    }

    @Override
    public void onUserLogged() {
        Menu menuNav = mNavigationView.getMenu();
        menuNav.findItem(R.id.nav_log_in).setVisible(false);
        menuNav.findItem(R.id.nav_register).setVisible(false);
        menuNav.findItem(R.id.nav_user_details).setVisible(true);
        Toast.makeText(this, R.string.user_logged_in, Toast.LENGTH_LONG).show();
        displayView(R.id.nav_home);
    }

    @Override
    public void onUserLoggedOut() {
        Menu menuNav = mNavigationView.getMenu();
        menuNav.findItem(R.id.nav_user_details).setVisible(false);
        menuNav.findItem(R.id.nav_log_in).setVisible(true);
        menuNav.findItem(R.id.nav_register).setVisible(true);
        Toast.makeText(this, R.string.user_logged_out, Toast.LENGTH_LONG).show();
        displayView(R.id.nav_home);
    }

    private boolean isCameraAvailable() {
        PackageManager pm = getPackageManager();
        return pm.hasSystemFeature(PackageManager.FEATURE_CAMERA);
    }


}
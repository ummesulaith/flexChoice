package flexchoice.com.flexchoice.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import flexchoice.com.flexchoice.Fragments.CartFragment;
import flexchoice.com.flexchoice.Fragments.CategorieshrFragment;
import flexchoice.com.flexchoice.Fragments.DepositFragment;
import flexchoice.com.flexchoice.Fragments.FnqFragment;
import flexchoice.com.flexchoice.Fragments.ImageFlipperFragment;
import flexchoice.com.flexchoice.Fragments.OrderFragment;
import flexchoice.com.flexchoice.Fragments.PaymentFragment;
import flexchoice.com.flexchoice.Fragments.ProfileFragment;
import flexchoice.com.flexchoice.Fragments.ReturnsFragment;
import flexchoice.com.flexchoice.Fragments.StoreFragment;
import flexchoice.com.flexchoice.Fragments.TrackFragment;
import flexchoice.com.flexchoice.R;

public class HomeActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {
    private FirebaseAuth firebaseAuth;
    Toolbar toolbar;
    Fragment fragment ;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setTitle("Home");
        setSupportActionBar(toolbar);


        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        FragmentManager fm = getSupportFragmentManager();
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();

        ImageFlipperFragment mAboutFragment = new ImageFlipperFragment();
        fragmentTransaction.replace(R.id.content_frame, mAboutFragment, "home");
        fragmentTransaction.commit();

        firebaseAuth = FirebaseAuth.getInstance();

        if (firebaseAuth.getCurrentUser() == null) {
            finish();
            startActivity(new Intent(this, LoginActivity.class));
        }
        FirebaseUser user = firebaseAuth.getCurrentUser();
//        CategorieshrFragment banner = new CategorieshrFragment();
//        fragmentTransaction.replace(R.id.content_frame, banner, "home");
//        fragmentTransaction.commit();
//    }
    }
    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.home, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
//        if (id == R.id.action_settings) {
//            return true;
//        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.

        Fragment fragment = null;

        int id = item.getItemId();

        if (id == R.id.nav_home)
        {
            Intent i = new Intent(this,HomeActivity.class);
            startActivity(i);
        }

        else if (id == R.id.nav_profile)
        {
            fragment = new ProfileFragment();
            toolbar.setTitle("Profile");
        }
        else if (id == R.id.nav_order)
        {
            fragment = new OrderFragment();
            toolbar.setTitle("Orders");

        }
        else if (id == R.id.nav_trackorder)
        {
                fragment = new TrackFragment();
            toolbar.setTitle("Track Order");
        }
        else if (id == R.id.nav_cart)
        {
            fragment = new CartFragment();
            toolbar.setTitle("Cart");
        }
        else if (id == R.id.nav_payment)
        {
                fragment = new PaymentFragment();
            toolbar.setTitle("Payment");
        }
        else if (id == R.id.nav_deposit)
        {
                fragment = new DepositFragment();
            toolbar.setTitle("Deposit");
        }
        else if (id == R.id.nav_store)
        {
            fragment = new StoreFragment();
            toolbar.setTitle("Store");
        }
        else if (id == R.id.nav_fnq)
        {
            fragment = new FnqFragment();
            toolbar.setTitle("Feedback and Query");
        }
        else if (id == R.id.nav_returns)
        {
            fragment = new ReturnsFragment();
            toolbar.setTitle("Returns and Refunds");
        }
        else if (id == R.id.nav_logout)
        {
            FirebaseAuth.getInstance().signOut();
            startActivity(new Intent(this, LoginActivity.class));
            finish();
        }

    if (fragment !=null)
    {
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction ft = fragmentManager.beginTransaction();
        ft.replace(R.id.content_frame,fragment);
        ft.commit();
    }


        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}

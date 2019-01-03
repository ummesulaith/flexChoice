package flexchoice.com.flexchoice;

import android.content.Intent;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;


import com.github.paolorotolo.appintro.AppIntro;
import com.github.paolorotolo.appintro.AppIntroFragment;

public class AppIntroActivity extends AppIntro {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //setContentView(R.layout.activity_app_intro);

        addSlide(AppIntroFragment.newInstance("FlexChoice", "Furniture at your door step",
                R.drawable.welcome, ContextCompat.getColor(getApplicationContext(), R.color.foreground_material_light)));
        addSlide(AppIntroFragment.newInstance("FlexChoice", "Local Store for the look and feel of the furniture",
                R.drawable.shop, ContextCompat.getColor(getApplicationContext(), R.color.colorAccent)));
        addSlide(AppIntroFragment.newInstance("FlexChoice", "Get Furniture at reasonable price and good quality",
                R.drawable.payment, ContextCompat.getColor(getApplicationContext(), R.color.colorPrimaryDark)));
    }


    @Override
    public void onDonePressed(Fragment currentFragment) {
        super.onDonePressed(currentFragment);
        Intent intent=new Intent(getApplicationContext(),LoginActivity.class);
        startActivity(intent);
        finish();
    }

    @Override
    public void onSkipPressed(Fragment currentFragment) {
        super.onSkipPressed(currentFragment);
        Intent intent=new Intent(getApplicationContext(),HomeActivity.class);
        startActivity(intent);
        finish();
    }
}

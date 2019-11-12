package flexchoice.com.flexchoice.Fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import flexchoice.com.flexchoice.R;

public class ReturnsFragment extends Fragment {

    ImageView img1,img2;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        //returning our layout file
        //change R.layout.yourlayoutfilename for each of your fragments
        View v= inflater.inflate(R.layout.fragment_returns, container, false);

        img1=(ImageView) v.findViewById(R.id.image_cart);
        img1.setImageResource(R.drawable.f1);
        img2=(ImageView) v.findViewById(R.id.imagecart2);
        img2.setImageResource(R.drawable.f1);
        return v;
    }


    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        //you can set the title for your toolbar here for different fragments different titles

    }
}

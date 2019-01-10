package flexchoice.com.flexchoice.Fragments;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import flexchoice.com.flexchoice.R;

public class ItemdetailFragment extends Fragment {

    private TextView tvtitle,tvdescription,tvcategory;
    private ImageView img;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        //returning our layout file
        //change R.layout.yourlayoutfilename for each of your fragments
        View v= inflater.inflate(R.layout.itemdetail_fragment, container, false);
        tvtitle = (TextView) v.findViewById(R.id.txttitle);
        tvdescription = (TextView)v. findViewById(R.id.txtDesc);
        tvcategory = (TextView)v. findViewById(R.id.txtCat);
        img = (ImageView)v. findViewById(R.id.bookthumbnail);



        return v;


    }


    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        //you can set the title for your toolbar here for different fragments different titles

    }
}


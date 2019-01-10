package flexchoice.com.flexchoice.Fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.ViewFlipper;

import java.util.ArrayList;

import flexchoice.com.flexchoice.Adapters.RecyclerGridViewAdapter;
import flexchoice.com.flexchoice.Adapters.RecyclerViewAdapter;
import flexchoice.com.flexchoice.R;

import static android.support.constraint.Constraints.TAG;


public class ImageFlipperFragment extends Fragment {



    //vars
    GridView gridView;
    // mThumbIds;
    private ArrayList<String> mNames = new ArrayList<>();
    private ArrayList<String> mImageUrls = new ArrayList<>();

    private ArrayList<String> mData = new ArrayList<>();
    private ArrayList<String> mImage = new ArrayList<>();



    ViewFlipper v_flipper;
    private static final Integer[] images = {
            R.drawable.f1,
            R.drawable.f2,
            R.drawable.f3
    };
//

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootView= inflater.inflate(R.layout.fragment_image_flipper, container, false);


        v_flipper= (ViewFlipper) rootView.findViewById(R.id.v_flipper);

        for(int image: images)
        {
            flipperImages(image);
        }




        Log.d(TAG, "initRecyclerView: init recyclerview");

        LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity(), LinearLayoutManager.HORIZONTAL, false);
        RecyclerView recyclerView = rootView.findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(layoutManager);
        RecyclerViewAdapter adapter = new RecyclerViewAdapter(getActivity(), mNames, mImageUrls);
        recyclerView.setAdapter(adapter);

//        gridView = (GridView) rootView.findViewById(R.id.grid);
//       getgrid();

        RecyclerView myrv = (RecyclerView)rootView. findViewById(R.id.recyclerView_id);
        RecyclerGridViewAdapter myAdapter = new RecyclerGridViewAdapter(getActivity(),  mData,mImage);
        myrv.setLayoutManager(new GridLayoutManager(getActivity(),2));
        myrv.setAdapter(myAdapter);

        getFimg();
        getImages();
        return rootView;
    }

    public void flipperImages(int image)
    {
        ImageView imageView = new ImageView(getActivity());
        imageView.setBackgroundResource(image);
        v_flipper.addView(imageView);
        v_flipper.setFlipInterval(4000);
        v_flipper.setAutoStart(true);

        v_flipper.setInAnimation(getActivity(),android.R.anim.slide_in_left);
        v_flipper.setInAnimation(getActivity(),android.R.anim.slide_out_right);


    }
    private void getImages() {
        Log.d(TAG, "initImageBitmaps: preparing bitmaps.");

        mImageUrls.add("https://c1.staticflickr.com/5/4636/25316407448_de5fbf183d_o.jpg");
        mNames.add("Havasu Falls");

        mImageUrls.add("https://i.redd.it/tpsnoz5bzo501.jpg");
        mNames.add("Trondheim");

        mImageUrls.add("https://i.redd.it/qn7f9oqu7o501.jpg");
        mNames.add("Portugal");

        mImageUrls.add("https://i.redd.it/j6myfqglup501.jpg");
        mNames.add("Rocky Mountain National Park");


        mImageUrls.add("https://i.redd.it/0h2gm1ix6p501.jpg");
        mNames.add("Mahahual");

        mImageUrls.add("https://i.redd.it/k98uzl68eh501.jpg");
        mNames.add("Frozen Lake");


        mImageUrls.add("https://i.redd.it/glin0nwndo501.jpg");
        mNames.add("White Sands Desert");

        mImageUrls.add("https://i.redd.it/obx4zydshg601.jpg");
        mNames.add("Austrailia");

        mImageUrls.add("https://i.imgur.com/ZcLLrkY.jpg");
        mNames.add("Washington");
    }


    private void getFimg(){

        Log.d(TAG, "initImageBitmaps: preparing bitmaps.");

        mImage.add("https://c1.staticflickr.com/5/4636/25316407448_de5fbf183d_o.jpg");
        mData.add("Havasu Falls");

        mImage.add("https://i.redd.it/tpsnoz5bzo501.jpg");
        mData.add("Trondheim");

        mImage.add("https://i.redd.it/qn7f9oqu7o501.jpg");
        mData.add("Portugal");

        mImage.add("https://i.redd.it/j6myfqglup501.jpg");
        mData.add("Rocky Mountain National Park");


        mImage.add("https://i.redd.it/0h2gm1ix6p501.jpg");
        mData.add("Mahahual");

        mImage.add("https://i.redd.it/k98uzl68eh501.jpg");
        mData.add("Frozen Lake");


        mImage.add("https://i.redd.it/glin0nwndo501.jpg");
        mData.add("White Sands Desert");

        mImage.add("https://i.redd.it/obx4zydshg601.jpg");
        mData.add("Austrailia");

        mImage.add("https://i.imgur.com/ZcLLrkY.jpg");
        mData.add("Washington");



    }


//        public void  getgrid(){
//    //grid
//
//
////    gridView.setAdapter(new GridAdapter(getActivity(),mThumbIds));
//
//    GridAdapter gridAdapter  = new GridAdapter(getActivity(),mThumbIds);
//    gridView.setAdapter(gridAdapter);
//}

}

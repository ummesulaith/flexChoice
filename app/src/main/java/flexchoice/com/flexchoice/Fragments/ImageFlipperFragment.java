package flexchoice.com.flexchoice.Fragments;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Paint;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.GestureDetector;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.GridView;

import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

import flexchoice.com.flexchoice.Activity.Itemdetail;
import flexchoice.com.flexchoice.Adapters.RecyclerGridViewAdapter;
import flexchoice.com.flexchoice.Adapters.RecyclerViewAdapter;
import flexchoice.com.flexchoice.Adapters.ViewFlipperIndicator;
import flexchoice.com.flexchoice.Models.Post;
import flexchoice.com.flexchoice.R;

import static android.support.constraint.Constraints.TAG;

public class ImageFlipperFragment extends Fragment implements GestureDetector.OnGestureListener  {

    Activity context;


    //vars
    GridView gridView;
    // mThumbIds;
    private ArrayList<String> mNames = new ArrayList<>();
    private ArrayList<String> mImageUrls = new ArrayList<>();

    private ArrayList<String> mData = new ArrayList<>();
    private ArrayList<String> mPrice = new ArrayList<>();
    private ArrayList<String> mDesc = new ArrayList<>();
    private ArrayList<String> mImage = new ArrayList<>();



    //flipper
    private ViewFlipperIndicator flipper;
    private Animation lInAnim;
    private Animation lOutAnim;

    private String KEY_FLIPPER_POSITION = "flipper_position";

    private GestureDetector detector = null;

    private Handler myHandler = new Handler();



    // flipper restarts flipping
    private Runnable flipController = new Runnable() {
        @Override
        public void run() {
            flipper.setInAnimation(lInAnim);
            flipper.setOutAnimation(lOutAnim);
            flipper.showNext();
            flipper.startFlipping();
        }
    };

   private DatabaseReference ProductsRef;

      RecyclerView myrv;
    RecyclerGridViewAdapter myAdapter;






    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootView= inflater.inflate(R.layout.fragment_image_flipper, container, false);


        Animation   lInAnim = AnimationUtils.loadAnimation(getActivity(), R.anim.push_left_in);
        Animation   lOutAnim = AnimationUtils.loadAnimation(getActivity(), R.anim.push_left_out);

        ViewFlipperIndicator  flipper = (ViewFlipperIndicator) rootView.findViewById(R.id.viewFlipper);

        // set values radius and margin for view flipper indicators
        flipper.setRadius(10);
        flipper.setMargin(10);

        // set colors for the indicators
        Paint paint = new Paint();
        paint.setColor(getResources().getColor(android.R.color.white));
        flipper.setPaintCurrent(paint);

        paint = new Paint();
        paint.setColor(getResources().getColor(R.color.grey_light));
        flipper.setPaintNormal(paint);

        flipper.setInAnimation(lInAnim);
        flipper.setOutAnimation(lOutAnim);
        flipper.setAutoStart(true);
        flipper.setFlipInterval(3000);

        // flipper has a previous position. we should restore it
        if (savedInstanceState != null) {
            flipper.setDisplayedChild(savedInstanceState.getInt(KEY_FLIPPER_POSITION));
        }

        flipper.startFlipping();

        detector = new GestureDetector(getActivity(), this);






        Log.d(TAG, "initRecyclerView: init recyclerview");

        LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity(), LinearLayoutManager.HORIZONTAL, false);
        RecyclerView recyclerView = rootView.findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(layoutManager);
        RecyclerViewAdapter myAdapter = new RecyclerViewAdapter(getActivity(), mNames, mImageUrls);
        recyclerView.setAdapter(myAdapter);



         myrv = (RecyclerView)rootView. findViewById(R.id.recyclerView_id);
       // RecyclerGridViewAdapter adapter = new RecyclerGridViewAdapter(getActivity());
        myrv.setLayoutManager(new GridLayoutManager(getActivity(),2));
//        myrv.setAdapter(myAdapter);

        getFimg();
        getImages();
        ProductsRef = FirebaseDatabase.getInstance().getReference().child("Products");
        return rootView;


    }







    public boolean onTouchEvent(MotionEvent event) {
        return detector.onTouchEvent(event);
    }

    @Override
    public boolean onDown(MotionEvent e) {
        return false;
    }

    @Override
    public void onShowPress(MotionEvent e) {

    }

    @Override
    public boolean onSingleTapUp(MotionEvent e) {
        return false;
    }

    @Override
    public boolean onScroll(MotionEvent e1, MotionEvent e2, float distanceX, float distanceY) {
        return false;
    }

    @Override
    public void onLongPress(MotionEvent e) {

    }

    @Override
    public boolean onFling(MotionEvent e1, MotionEvent e2, float velocityX, float velocityY) {

        // you can change this value
        float sensitivity = 50;

        if ((e1.getX() - e2.getX()) > sensitivity) {

            flipper.showNext();

            // first stops flipping
            flipper.stopFlipping();

            // then flipper restarts flipping in 3 seconds
            myHandler.postDelayed(flipController, 3000);

            return true;
        } else if ((e2.getX() - e1.getX()) > sensitivity) {
            Animation rInAnim = AnimationUtils.loadAnimation(getActivity(), R.anim.push_right_in);
            Animation rOutAnim = AnimationUtils.loadAnimation(getActivity(), R.anim.push_right_out);
            flipper.setInAnimation(rInAnim);
            flipper.setOutAnimation(rOutAnim);
            flipper.showPrevious();

            // first stops flipping
            flipper.stopFlipping();

            // then flipper restarts flipping in 3 seconds
            myHandler.postDelayed(flipController, 3000);

            return true;
        }

        return true;
    }


    private void getImages() {
        Log.d(TAG, "initImageBitmaps: preparing bitmaps.");

        mImageUrls.add("https://www.americanfreight.com/media/catalog/product/cache/1/image/9df78eab33525d08d6e5fb8d27136e95/1/7/175-factory-select-rust-red-sofa-loveseat.jpg");
        mNames.add("Sofa");

        mImageUrls.add("https://www.ulcdn.net/opt/www.ulcdn.net/images/products/27997/product/Reden_Lounge_Chair_Set_of_2_Patchwork_00_IMG_0181_LP.jpg?1427798649");
        mNames.add("Chair");

        mImageUrls.add("https://www.hometown.in/media/product/97/8053/54297/1-product_500.jpg");
        mNames.add("Bed");

        mImageUrls.add("https://rukminim1.flixcart.com/image/612/612/jgv5jm80/coffee-table/h/m/s/particle-board-ct-606-valtos-wenge-original-imaf48ss2phuagjm.jpeg?q=70");

        mNames.add("Table");


        mImageUrls.add("https://steinhoffukretailltd.scene7.com/is/image/SteinhoffUKRetailLtd/lifestyle_lindos_pdp?$lifestylepdp$");
        mNames.add("Dinning Table");

        mImageUrls.add("https://www.oakfurnitureland.co.uk/media/gbu0/resizedcache/Header_Lifestyle_1900x1069px_0005_Wardobes_86c79c7a68f6de8d13cc1d9b31c90b5a_629x354__255_255_255.jpg");
        mNames.add("Wadrobe");


        mImageUrls.add("https://s3-ap-southeast-2.amazonaws.com/sd.production.blog.rent.com.au/blog/wp-content/uploads/2017/02/pexels-photo-245208.jpeg");
        mNames.add("ShowCase");

        mImageUrls.add("https://www.easterngraphics.com/pcon/en/wp-content/uploads/2018/04/reol_lysbambus_atbo.jpg");
        mNames.add("Shelf");

        mImageUrls.add("https://www.ikea.com/gb/en/images/products/hemnes-dressing-table-with-mirror-grey__0542247_pe653927_s5.jpg");
        mNames.add("Dressing table");
    }


    private void getFimg(){

        Log.d(TAG, "initImageBitmaps: Adding data in grid.");

        mImage.add("https://www.americanfreight.com/media/catalog/product/cache/1/image/9df78eab33525d08d6e5fb8d27136e95/1/7/175-factory-select-rust-red-sofa-loveseat.jpg");
        mData.add("Sofa");
        mPrice.add("500");
        mDesc.add("Sofa");

        mImage.add("https://www.ulcdn.net/opt/www.ulcdn.net/images/products/27997/product/Reden_Lounge_Chair_Set_of_2_Patchwork_00_IMG_0181_LP.jpg?1427798649");
        mData.add("Chair");
        mPrice.add("500");
        mDesc.add("Sofa");

        mImage.add("https://www.hometown.in/media/product/97/8053/54297/1-product_500.jpg");
        mData.add("Beds");
        mPrice.add("500");
        mDesc.add("Sofa");

        mImage.add("https://rukminim1.flixcart.com/image/612/612/jgv5jm80/coffee-table/h/m/s/particle-board-ct-606-valtos-wenge-original-imaf48ss2phuagjm.jpeg?q=70");
        mData.add("Table");
        mPrice.add("500");
        mDesc.add("Sofa");


        mImage.add("https://steinhoffukretailltd.scene7.com/is/image/SteinhoffUKRetailLtd/lifestyle_lindos_pdp?$lifestylepdp$");
        mData.add("Dinning table");
        mPrice.add("500");
        mDesc.add("Sofa");

        mImage.add("https://www.oakfurnitureland.co.uk/media/gbu0/resizedcache/Header_Lifestyle_1900x1069px_0005_Wardobes_86c79c7a68f6de8d13cc1d9b31c90b5a_629x354__255_255_255.jpg");
        mData.add("Wadrobe");
        mPrice.add("500");
        mDesc.add("Sofa");

        mImage.add("https://s3-ap-southeast-2.amazonaws.com/sd.production.blog.rent.com.au/blog/wp-content/uploads/2017/02/pexels-photo-245208.jpeg");
        mData.add("Showcase");
        mPrice.add("500");
        mDesc.add("Sofa");

        mImage.add("https://www.easterngraphics.com/pcon/en/wp-content/uploads/2018/04/reol_lysbambus_atbo.jpg");
        mData.add("Shelf");
        mPrice.add("500");
        mDesc.add("Sofa");

        mImage.add("https://www.ikea.com/gb/en/images/products/hemnes-dressing-table-with-mirror-grey__0542247_pe653927_s5.jpg");
        mData.add("Dressing Table");
        mPrice.add("500");
        mDesc.add("Sofa");


    }



    @Override
    public void onStart() {
        super.onStart();

        FirebaseRecyclerOptions<Post> options =
                new FirebaseRecyclerOptions.Builder<Post>()
                        .setQuery(ProductsRef, Post.class)
                        .build();

        FirebaseRecyclerAdapter<Post, RecyclerGridViewAdapter> adapter =
                new FirebaseRecyclerAdapter<Post, RecyclerGridViewAdapter>(options) {
                    @NonNull
                    @Override
                    public RecyclerGridViewAdapter onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
                     View   view = LayoutInflater.from(parent.getContext()).inflate(R.layout.single_item, parent, false);
                        RecyclerGridViewAdapter holder = new RecyclerGridViewAdapter(view);
                        return holder;
                    }

                    @Override
                    protected void onBindViewHolder(@NonNull RecyclerGridViewAdapter holder, int position, @NonNull final Post model)
                    {
                        Context CurrentObj=getActivity();
                        holder.tv_furniture_title.setText(model.getPname());
                        holder.td.setText(model.getDescription());
                        holder.tp.setText("Price = " + model.getPrice() + "$");
                        Picasso.get().load(model.getImage()).into(holder.img_furniture_thumbnail);

                        holder.itemView.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {
                                context = getActivity();
                                Log.d(TAG, "onClick: item details");
                                Intent intent=new Intent(context, Itemdetail.class);
                                intent.putExtra("pid", model.getPid());
                                startActivity(intent);

                            }
                        });
                    }


                };

        myrv.setAdapter(adapter);
        adapter.startListening();
    }



}

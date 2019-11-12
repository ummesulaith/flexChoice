package flexchoice.com.flexchoice.Fragments;


import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.squareup.picasso.Picasso;

import flexchoice.com.flexchoice.Models.Post;
import flexchoice.com.flexchoice.R;


public class ItemdetailFragment extends AppCompatActivity {

    private String productID = "";
    TextView productName,productDescption,productPrice;
    ImageView productImage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.itemdetail_fragment);

//        productName = (TextView)findViewById(R.id.ptitle);
//
//        productDescption = (TextView)findViewById(R.id.desc);
//
//        productPrice = (TextView)findViewById(R.id.pprice);
//
//        productImage = (ImageView) findViewById(R.id.image1);
//
//        productID = getIntent().getStringExtra("pid");
//        getItemDetails(productID);
        
    }

//    private void getItemDetails(String productID) {
//
//        DatabaseReference prodRef = FirebaseDatabase.getInstance().getReference().child("Products");
//        prodRef.child(productID).addValueEventListener(new ValueEventListener() {
//            @Override
//            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
//
//                if(dataSnapshot.exists())
//                {
//                    Post post = dataSnapshot.getValue(Post.class);
//
//                    productName.setText(post.getPname());
//                    productPrice.setText(post.getPrice());
//                    productDescption.setText(post.getDescription());
//                    Picasso.get().load(post.getImage()).into(productImage);
//
//
//                }
//            }
//
//            @Override
//            public void onCancelled(@NonNull DatabaseError databaseError) {
//
//            }
//        });
//    }

}




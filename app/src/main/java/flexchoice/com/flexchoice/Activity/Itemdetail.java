package flexchoice.com.flexchoice.Activity;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.squareup.picasso.Picasso;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.HashMap;

import flexchoice.com.flexchoice.Fragments.CartFragment;
import flexchoice.com.flexchoice.Fragments.ImageFlipperFragment;
import flexchoice.com.flexchoice.Models.Post;
import flexchoice.com.flexchoice.Models.Prevalent;
import flexchoice.com.flexchoice.R;



public class Itemdetail extends AppCompatActivity {

    private static final String TAG = "itemDetail" ;
    private String productID = "";
    TextView productName, productDescption, productPrice,addtocart,buy;
    ImageView productImage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_itemdetail);

        productName = (TextView) findViewById(R.id.ptitle);

        productDescption = (TextView) findViewById(R.id.desc);

        productPrice = (TextView) findViewById(R.id.pprice);

        addtocart = (TextView) findViewById(R.id.bcart);

       // buy = (TextView) findViewById(R.id.bbuy);

        productImage = (ImageView) findViewById(R.id.image1);

        productID = getIntent().getStringExtra("pid");

        getItemDetails(productID);

        addtocart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                addingToCart();
            }
        });
//        buy.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//
//
//
//
//            }
//        });

    }

    private void addingToCart() {

        String saveCurrentTime,saveCurrentDate;
        Calendar calforDate = Calendar.getInstance();
        SimpleDateFormat currentDate = new SimpleDateFormat("MMM dd, yyyy");
        saveCurrentDate = currentDate.format(calforDate.getTime());

        SimpleDateFormat currentTime = new SimpleDateFormat("HH:mm:ss a");
        saveCurrentTime = currentTime.format(calforDate.getTime());

       final DatabaseReference cartListref = FirebaseDatabase.getInstance().getReference().child("Cart List");
        final HashMap<String, Object>cartMap = new HashMap<>();
        cartMap.put("pid",productID);
        cartMap.put("pname",productName.getText().toString());
        cartMap.put("price",productPrice.getText().toString());
    //    cartMap.put("image",productImage);
        cartMap.put("date",saveCurrentDate);
        cartMap.put("time",saveCurrentTime);
      //  cartMap.put("image",productImage);
//        cartMap.put("image", downloadImageUrl);
//        cartMap.put("quantity",productID);



cartListref.child("User View").child("Products").child(productID).updateChildren(cartMap)
.addOnCompleteListener(new OnCompleteListener<Void>() {
    @Override
    public void onComplete(@NonNull Task<Void> task) {
        if(task.isSuccessful())
        {
            cartListref.child("Admin View").child("Products").child(productID)
                    .updateChildren(cartMap)
                    .addOnCompleteListener(new OnCompleteListener<Void>() {
                        @Override
                        public void onComplete(@NonNull Task<Void> task) {

                            if(task.isSuccessful())
                            {
                                Toast.makeText(Itemdetail.this,"Added successfully in the cart",Toast.LENGTH_SHORT).show();

                                Intent i = new Intent(Itemdetail.this, HomeActivity.class);
                                startActivity(i);
                            }
                        }
                    });

        }
    }
});


    }

    private void getItemDetails(String productID) {

        DatabaseReference prodRef = FirebaseDatabase.getInstance().getReference().child("Products");
        prodRef.child(productID).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                if (dataSnapshot.exists()) {
                    Post post = dataSnapshot.getValue(Post.class);

                    productName.setText(post.getPname());
                    productPrice.setText(post.getPrice());
                    productDescption.setText(post.getDescription());
                    Picasso.get().load(post.getImage()).into(productImage);


                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }
}
//    setContentView(R.layout.activity_itemdetail);
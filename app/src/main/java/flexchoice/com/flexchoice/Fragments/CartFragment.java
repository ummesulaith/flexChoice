package flexchoice.com.flexchoice.Fragments;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import flexchoice.com.flexchoice.Adapters.CartViewHolder;
import flexchoice.com.flexchoice.Models.Cart;
import flexchoice.com.flexchoice.R;

import static android.support.constraint.Constraints.TAG;

public class CartFragment extends Fragment {

private RecyclerView recyclerView;
private RecyclerView.LayoutManager layoutManager;
private TextView totalprice;
Button buy;
    public static int totalPrice = 0;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        //returning our layout file
        //change R.layout.yourlayoutfilename for each of your fragments
        View v=  inflater.inflate(R.layout.fragment_cart, container, false);
        recyclerView = (RecyclerView)v.findViewById(R.id.cartrecycler);
        recyclerView.setHasFixedSize(true);
        layoutManager = new LinearLayoutManager(getContext());
        recyclerView.setLayoutManager(layoutManager);
//        totalprice=(TextView)v.findViewById(R.id.totalp);
//        String p = (String) totalprice.getText();
//        price = price + Integer.valueOf(totalprice);
//        Log.d(TAG, "onCreateView: price::::::" + totalprice.getText());
//        Log.d(TAG, "onCreateView: testing price" +totalprice);
//        totalprice.setText("Total Price : " + Integer.valueOf(totalPrice));

        buy= (Button)v.findViewById(R.id.itembuy);
        buy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Fragment frag = new ConfirmOrderFragment();
                FragmentTransaction ft = getFragmentManager().beginTransaction();
                ft.replace(R.id.content_frame, frag);
                ft.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE);
                ft.addToBackStack(null);
                ft.commit();
            }
        });
        return v;
    }


    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        //you can set the title for your toolbar here for different fragments different titles

    }

    @Override
    public void onStart() {
        super.onStart();

        final DatabaseReference carListRef = FirebaseDatabase.getInstance().getReference().child("Cart List");

        FirebaseRecyclerOptions<Cart> options =
                new FirebaseRecyclerOptions.Builder<Cart>()
                        .setQuery(carListRef.child("User View").child("Products"), Cart.class)
                        .build();

//        FirebaseRecyclerOptions<Cart> options = new
//               FirebaseRecyclerOptions.Builder<Cart>()
//                .setQuery(carListRef.child("User View")
//                .child("Product"),Cart.class).build();

        FirebaseRecyclerAdapter<Cart, CartViewHolder> adapter = new FirebaseRecyclerAdapter<Cart, CartViewHolder>(options) {
            @Override
            protected void onBindViewHolder(@NonNull CartViewHolder holder, int position, @NonNull final Cart model) {

                holder.txtProductname.setText(model.getPname());
                holder.txtProductprice.setText(model.getPrice());
               // Picasso.get().load(model.getImage()).into(holder.cartimg);

//                int onetyprproductprice = ((Integer.valueOf(model.getPrice())));
//                totalPrice = onetyprproductprice + Integer.valueOf(totalPrice);
//                        price = price + Integer.valueOf(totalprice);

                Log.d(TAG, "onBindViewHolder: model testing" +model.getPrice());

                holder.removeitem.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        carListRef.child("User View")
                                .child("Products")
                                .child(model.getPid())
                                .removeValue()
                                .addOnCompleteListener(new OnCompleteListener<Void>() {
                                    @Override
                                    public void onComplete(@NonNull Task<Void> task) {
                                        if (task.isSuccessful())
                                        {
                                            Toast.makeText(getActivity(),"item Removed successfully",Toast.LENGTH_SHORT).show();
                                        }
                                    }
                                });
                    }
                });

            }
            @NonNull
            @Override
            public CartViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
                View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_cart_item, parent, false);
                CartViewHolder holder = new CartViewHolder(view);
                return holder;
            }


        };
            recyclerView.setAdapter(adapter);
            adapter.startListening();
    }
}


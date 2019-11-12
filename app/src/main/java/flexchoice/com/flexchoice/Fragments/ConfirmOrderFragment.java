package flexchoice.com.flexchoice.Fragments;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.HashMap;

import flexchoice.com.flexchoice.Fragments.PaymentFragment;
import flexchoice.com.flexchoice.R;


public class ConfirmOrderFragment extends Fragment {


private EditText ename,ephone,eaddr,ecity;
private Button payment;



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v= inflater.inflate(R.layout.fragment_confirm_order, container, false);
        ename= (EditText)v.findViewById(R.id.confirmname);
        ephone= (EditText)v.findViewById(R.id.confirmphone);
        eaddr= (EditText)v.findViewById(R.id.confirmaddr);
        ecity= (EditText)v.findViewById(R.id.confirmcity);
        payment= (Button) v.findViewById(R.id.confirmpayment);

        payment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Check();
            }
        });
        return v;
    }
        private  void Check()
        {
            if(TextUtils.isEmpty(ename.getText().toString()))
            {
                Toast.makeText(getActivity(),"Please provide the name",Toast.LENGTH_SHORT).show();
            }
            else if(TextUtils.isEmpty(ephone.getText().toString()))
            {
                Toast.makeText(getActivity(),"Please provide the phone Number",Toast.LENGTH_SHORT).show();
            }
            else if(TextUtils.isEmpty(eaddr.getText().toString()))
            {
                Toast.makeText(getActivity(),"Please provide the Address",Toast.LENGTH_SHORT).show();
            }
            else if (TextUtils.isEmpty(ecity.getText().toString()))
            {
                Toast.makeText(getActivity(),"Please provide the city",Toast.LENGTH_SHORT).show();
            }
            else
            {
                ConfirmOrder();
            }
        }

        private void  ConfirmOrder()
        {
            String saveCurrentTime,saveCurrentDate;
            Calendar calforDate = Calendar.getInstance();
            SimpleDateFormat currentDate = new SimpleDateFormat("MMM dd, yyyy");
            saveCurrentDate = currentDate.format(calforDate.getTime());

            SimpleDateFormat currentTime = new SimpleDateFormat("HH:mm:ss a");
            saveCurrentTime = currentTime.format(calforDate.getTime());

            final DatabaseReference orderRef = FirebaseDatabase.getInstance().getReference().child("Orders");
            final HashMap<String, Object> orderMap = new HashMap<>();
            orderMap.put("name",ename.getText().toString());
            orderMap.put("phone",ephone.getText().toString());
            orderMap.put("address",eaddr.getText().toString());
            orderMap.put("city",ecity.getText().toString());
            orderMap.put("date",saveCurrentDate);
            orderMap.put("time",saveCurrentTime);
            orderMap.put("state","not shipped");


            orderRef.updateChildren(orderMap).addOnCompleteListener(new OnCompleteListener<Void>() {
                @Override
                public void onComplete(@NonNull Task<Void> task) {
                    if(task.isSuccessful())
                    {
                        FirebaseDatabase.getInstance()
                                .getReference().child("Cart List")
                                .child("User View")
                                .removeValue()
                                .addOnCompleteListener(new OnCompleteListener<Void>() {
                                    @Override
                                    public void onComplete(@NonNull Task<Void> task) {

                                        if (task.isSuccessful())
                                        {
                                            Toast.makeText(getActivity(),"Your order has been placed",Toast.LENGTH_SHORT).show();

                                            //add another fragment here

                                            Fragment frag = new PaymentFragment();


                                            FragmentTransaction ft = getFragmentManager().beginTransaction();
                                            ft.replace(R.id.content_frame, frag);
                                            ft.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE);
                                            ft.addToBackStack(null);
                                            ft.commit();
                                        }
                                    }
                                });
                    }
                }
            });
        }
    }

package flexchoice.com.flexchoice.Fragments;


import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;


import android.app.ProgressDialog;
import android.content.Intent;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;


import com.google.android.gms.tasks.Continuation;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.StorageTask;
import com.squareup.picasso.Picasso;
import com.theartofdev.edmodo.cropper.CropImage;

import java.util.HashMap;

import de.hdodenhof.circleimageview.CircleImageView;
import flexchoice.com.flexchoice.Models.Prevalent;
import flexchoice.com.flexchoice.R;

import static android.app.Activity.RESULT_OK;


public class ProfileFragment extends Fragment {

    private CircleImageView profileImageView;
    private EditText fullNameEditText, userPhoneEditText, addressEditText;
    Button update;


    private FirebaseAuth firebaseAuth;
    private DatabaseReference databaseReference;
    private FirebaseAuth mAuth;
    private FirebaseUser mCurrentusr;
    private DatabaseReference mDbuser;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        //returning our layout file
        //change R.layout.yourlayoutfilename for each of your fragments
        View v = inflater.inflate(R.layout.fragment_profile, container, false);

        firebaseAuth = FirebaseAuth.getInstance();
        databaseReference = FirebaseDatabase.getInstance().getReference("Profile");
        FirebaseUser user = firebaseAuth.getCurrentUser();
        fullNameEditText = (EditText) v.findViewById(R.id.settings_full_name);
        userPhoneEditText = (EditText) v.findViewById(R.id.settings_phone_number);
        addressEditText = (EditText) v.findViewById(R.id.settings_address);
        update = (Button) v.findViewById(R.id.profupdate);

        AddData();
        //display();

        mAuth = FirebaseAuth.getInstance();
        mCurrentusr = mAuth.getCurrentUser();
        mDbuser = FirebaseDatabase.getInstance().getReference().child("Profile").child(mCurrentusr.getUid());

        return v;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        //you can set the title for your toolbar here for different fragments different titles

    }


    public void AddData() {
        update.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        //Getting values from database
                        final String name = fullNameEditText.getText().toString().trim();
                        final String phone = userPhoneEditText.getText().toString().trim();
                        final String addr = addressEditText.getText().toString().trim();
                        ;
                        //creating a userinformation object
                        if (TextUtils.isEmpty(name) || TextUtils.isEmpty(phone) || TextUtils.isEmpty(addr)) {
                            Toast.makeText(getActivity(), " Fields Are Empty ", Toast.LENGTH_LONG).show();
                        }

                        DatabaseReference profile = databaseReference.push();
                        profile.child("name").setValue(name);
                        profile.child("phone").setValue(phone);
                        profile.child("address").setValue(addr);

//                                    profile.child("image").setValue(downloadUrl.toString());

                        profile.child("uid").setValue(mCurrentusr.getUid());
                        setEditingEnabled(false);

                        Toast.makeText(getActivity(), " Successful ", Toast.LENGTH_LONG).show();


                    }
                });
//
//
//    private void userInfoDisplay(final CircleImageView profileImageView, final EditText fullNameEditText, final EditText userPhoneEditText, final EditText addressEditText)
//    {
//        DatabaseReference UsersRef = FirebaseDatabase.getInstance().getReference().child("Users");
//
//        UsersRef.addValueEventListener(new ValueEventListener() {
//            @Override
//            public void onDataChange(DataSnapshot dataSnapshot)
//            {
//                if (dataSnapshot.exists())
//                {
//                    if (dataSnapshot.child("image").exists())
//                    {
//                        String image = dataSnapshot.child("image").getValue().toString();
//                        String name = dataSnapshot.child("name").getValue().toString();
//                        String phone = dataSnapshot.child("phone").getValue().toString();
//                        String address = dataSnapshot.child("address").getValue().toString();
//
//                        Picasso.get().load(image).into(profileImageView);
//                        fullNameEditText.setText(name);
//                        userPhoneEditText.setText(phone);
//                        addressEditText.setText(address);
//                    }
//                }
//            }
//
//            @Override
//            public void onCancelled(DatabaseError databaseError) {
//
//            }
//        });
//    }


    }

    private void setEditingEnabled(boolean enabled) {
        addressEditText.setEnabled(enabled);
        fullNameEditText.setEnabled(enabled);
        userPhoneEditText.setEnabled(enabled);

        if (enabled) {
            update.setVisibility(View.VISIBLE);
        } else {
            update.setVisibility(View.GONE);
        }
    }

}


package flexchoice.com.flexchoice.Activity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import org.w3c.dom.Text;

import java.util.HashMap;

import flexchoice.com.flexchoice.Models.Admin;
import flexchoice.com.flexchoice.R;

public class AdminLogin extends AppCompatActivity {
    TextView login, ptxt, pwdtxt;
    Button AdminAccountButton, AdminRegister;
    private ProgressDialog progressDialog;
    private String parentDbName = "Admin";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_login);

        ptxt = (TextView) findViewById(R.id.dedemail);
        pwdtxt = (TextView) findViewById(R.id.dedpwd);
        login = (TextView) findViewById(R.id.nadmin);
        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(AdminLogin.this, LoginActivity.class);
                startActivity(i);
                String parentDbName = "Admin";
            }
        });

        AdminAccountButton = (Button) findViewById(R.id.balogin);

        AdminAccountButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                LoginAccount();
            }
        });


        progressDialog = new ProgressDialog(this);
    }

//    private void RegisterAccount() {
//        String phone = ptxt.getText().toString();
//        String password = pwdtxt.getText().toString();
//
//        if (TextUtils.isEmpty(phone)) {
//            Toast.makeText(this, "Please write your phone number...", Toast.LENGTH_SHORT).show();
//        } else if (TextUtils.isEmpty(password)) {
//            Toast.makeText(this, "Please write your password...", Toast.LENGTH_SHORT).show();
//        } else {
//            progressDialog.setTitle("Create Account");
//            progressDialog.setMessage("Please wait, while we are checking the credentials.");
//            progressDialog.setCanceledOnTouchOutside(false);
//            progressDialog.show();
//
//            ValidatephoneNumber(phone, password);
//        }
//    }
//
//        private void ValidatephoneNumber ( final String phone, final String password)
//        {
//            final DatabaseReference RootRef;
//            RootRef = FirebaseDatabase.getInstance().getReference();
//
//            RootRef.addListenerForSingleValueEvent(new ValueEventListener() {
//                @Override
//                public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
//                    if (!(dataSnapshot.child("Admin").child(phone).exists())) {
//                        HashMap<String, Object> userdataMap = new HashMap<>();
//                        userdataMap.put("phone", phone);
//                        userdataMap.put("password", password);
//
//
//                        RootRef.child("Admin").child(phone).updateChildren(userdataMap)
//                                .addOnCompleteListener(new OnCompleteListener<Void>() {
//                                    @Override
//                                    public void onComplete(@NonNull Task<Void> task) {
//                                        if (task.isSuccessful()) {
//                                            Toast.makeText(AdminLogin.this, "Congratulations, your account has been created.", Toast.LENGTH_SHORT).show();
//                                            progressDialog.dismiss();
//
////                                        Intent intent = new Intent(AdminLogin.this, LoginActivity.class);
////                                        startActivity(intent);
//                                        } else {
//                                            progressDialog.dismiss();
//                                            Toast.makeText(AdminLogin.this, "Network Error: Please try again after some time...", Toast.LENGTH_SHORT).show();
//                                        }
//                                    }
//                                });
//                    } else {
//                        Toast.makeText(AdminLogin.this, "This " + phone + " already exists.", Toast.LENGTH_SHORT).show();
//                        progressDialog.dismiss();
//                        Toast.makeText(AdminLogin.this, "Please try again using another phone number.", Toast.LENGTH_SHORT).show();
//
//                        Intent intent = new Intent(AdminLogin.this, HomeActivity.class);
//                        startActivity(intent);
//                    }
//                }
//
//                @Override
//                public void onCancelled(@NonNull DatabaseError databaseError) {
//
//                }
//            });
//        }



    private void LoginAccount () {


        String phone = ptxt.getText().toString();
        String password = pwdtxt.getText().toString();

        if (TextUtils.isEmpty(phone)) {
            Toast.makeText(this, "Please write your phone number...", Toast.LENGTH_SHORT).show();
        } else if (TextUtils.isEmpty(password)) {
            Toast.makeText(this, "Please write your password...", Toast.LENGTH_SHORT).show();
        } else {
            progressDialog.setTitle("Create Account");
            progressDialog.setMessage("Please wait, while we are checking the credentials.");
            progressDialog.setCanceledOnTouchOutside(false);
            progressDialog.show();

            // ValidatephoneNumber(phone, password);
            AllowAccessToAccount(phone, password);
        }
    }


    private void AllowAccessToAccount ( final String phone, final String password)
    {


        final DatabaseReference RootRef;
        RootRef = FirebaseDatabase.getInstance().getReference();


        RootRef.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if (dataSnapshot.child("Admin").child(phone).exists()) {
                    Admin adminsData = dataSnapshot.child("Admin").child(phone).getValue(Admin.class);

                    if (adminsData.getPhone().equals(phone)) {

                        if (adminsData.getPassword().equals(password)) {



                            Toast.makeText(AdminLogin.this, "Welcome Admin, you are logged in Successfully...", Toast.LENGTH_SHORT).show();
                            progressDialog.dismiss();

                            Intent intent = new Intent(AdminLogin.this, AdminCategoryActivity.class);
                            startActivity(intent);


                        } else {
                            progressDialog.dismiss();
                            Toast.makeText(AdminLogin.this, "Password is incorrect.", Toast.LENGTH_SHORT).show();
                        }
                    }
                } else {
                    Toast.makeText(AdminLogin.this, "Account with this " + phone + " number do not exists.", Toast.LENGTH_SHORT).show();
                    progressDialog.dismiss();
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }


}








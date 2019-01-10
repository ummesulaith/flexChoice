package flexchoice.com.flexchoice.Activity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;

import flexchoice.com.flexchoice.R;

public class RegisterActivity extends AppCompatActivity implements View.OnClickListener {

    private EditText editTextEmail;
    private EditText editTextPassword;
    private EditText editTextRpassword;

    private Button buttonSignup;
    private ProgressDialog progressDialog;
    private FirebaseAuth firebaseAuth;
    TextView txthome;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        firebaseAuth = FirebaseAuth.getInstance();


        editTextEmail = (EditText) findViewById(R.id.edeid);
        editTextPassword = (EditText) findViewById(R.id.edpaswd);
        editTextRpassword = (EditText) findViewById(R.id.edrpaswd);
        buttonSignup = (Button) findViewById(R.id.btnreg);
        txthome = (TextView) findViewById(R.id.tlogin);
        txthome.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {

                        Intent in = new Intent(RegisterActivity.this,LoginActivity.class);
        startActivity(in);
            }

        });



        progressDialog = new ProgressDialog(this);

        //attaching listener to button
        buttonSignup.setOnClickListener(this);
    }
    private void registerUser(){

        //getting email and password from edit texts
        String email = editTextEmail.getText().toString().trim();
        String password  = editTextPassword.getText().toString().trim();
        String rpassword = editTextRpassword.getText().toString().trim();

        //checking if email and passwords are empty
        if(TextUtils.isEmpty(email)){
            Toast.makeText(this,"Please enter email",Toast.LENGTH_LONG).show();
            return;
        }

        if(TextUtils.isEmpty(password)){
            Toast.makeText(this,"Please enter password",Toast.LENGTH_LONG).show();
            return;
        }
        if(TextUtils.isEmpty(password)){
            Toast.makeText(this,"Please enter password",Toast.LENGTH_LONG).show();
            return;
        }
        if(!password.equals(rpassword)) {
            Toast.makeText(this, "Password Not matching", Toast.LENGTH_SHORT).show();
            return;
        }



        //if the email and password are not empty
        //displaying a progress dialog

        progressDialog.setMessage("Registering Please Wait...");
        progressDialog.show();

        //creating a new user
        firebaseAuth.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        //checking if success
                        if(task.isSuccessful()){
                            //display some message here
                            Toast.makeText(RegisterActivity.this,"Successfully registered",Toast.LENGTH_LONG).show();
//                            Intent i = new Intent(RegisterActivity.this,HomeActivity.class);
//                            startActivity(i);
                        }else{
                            //display some message here
                            Toast.makeText(RegisterActivity.this,"Registration Error",Toast.LENGTH_LONG).show();
                        }
                        progressDialog.dismiss();
                    }
                });


    }

    @Override
    public void onClick(View view) {
        //calling register method on click
        registerUser();
    }

}

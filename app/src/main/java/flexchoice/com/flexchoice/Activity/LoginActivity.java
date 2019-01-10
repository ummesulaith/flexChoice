package flexchoice.com.flexchoice.Activity;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

import flexchoice.com.flexchoice.R;

public class LoginActivity extends Activity {
    TextView txtreg;
    EditText eEmail,epassword;
    Button bloginn;
    private FirebaseAuth.AuthStateListener mAuthListener;
    private FirebaseAuth mAuth;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        txtreg=(TextView)findViewById(R.id.signup);
        txtreg.setOnClickListener(new View.OnClickListener() {

        @Override
        public void onClick(View view) {

        Intent i = new Intent(LoginActivity.this,RegisterActivity.class);
        startActivity(i);
        }

    });

        eEmail = (EditText) findViewById(R.id.edemail);
        epassword = (EditText) findViewById(R.id.edpwd);

        bloginn = (Button) findViewById(R.id.blogin);
        mAuth = FirebaseAuth.getInstance();
        mAuthListener = new FirebaseAuth.AuthStateListener() {
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
                if (firebaseAuth.getCurrentUser() != null) {
                    startActivity(new Intent(LoginActivity.this, HomeActivity.class));
                }
            }
        };
        bloginn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startsignin();
            }
        });

    }

    @Override
    protected void onStart() {
        super.onStart();
        mAuth.addAuthStateListener(mAuthListener);
    }

    private void startsignin() {
        String Email = eEmail.getText().toString();
        String Password = epassword.getText().toString();
        if (TextUtils.isEmpty(Email) || TextUtils.isEmpty(Password)) {
            Toast.makeText(LoginActivity.this, " Fields Are Empty ", Toast.LENGTH_LONG).show();
        }
        final ProgressDialog progressDialog = ProgressDialog.show(LoginActivity.this, "Please wait...", "Proccessing...", true);

        mAuth.signInWithEmailAndPassword(Email, Password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                progressDialog.dismiss();

                if (!task.isSuccessful()) {
                    Toast.makeText(LoginActivity.this, "Login Failed", Toast.LENGTH_LONG).show();
                }
            }
        });
    }

    }


package com.example.mylibrary;

import static android.service.controls.ControlsProviderService.TAG;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.util.Patterns;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.common.api.ApiException;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class LoginActivity extends AppCompatActivity {

    private FirebaseAuth mAuth;

    GoogleSignInOptions googleSignInOptions;
    GoogleSignInClient googleSignInClient;

    DatabaseReference database;
    String UserName,PassWord;

    long id = 0;
    TextInputEditText TextInputEditTextUserName,TextInputEditTextPassword;
    private TextView txtWarnUserName,txtWarnPassword;
    private Button btnLogin,btnRegister;
    private ImageView googleLogin,twitterLogin,fbLogin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        mAuth = FirebaseAuth.getInstance();

        googleSignInOptions = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN).requestEmail().build();
        googleSignInClient = GoogleSignIn.getClient(this,googleSignInOptions);

        GoogleSignInAccount account = GoogleSignIn.getLastSignedInAccount(this);
        if (account!=null){
            navigateToSecondActivity();

        }
        initViews();
       database = FirebaseDatabase.getInstance().getReference("Students");
        database.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
            }
            @Override
            public void onCancelled(@NonNull DatabaseError error) {
            }
        });

        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                    if (validateData()) {
                        UserName = TextInputEditTextUserName.getText().toString();
                        PassWord = TextInputEditTextPassword.getText().toString();
                        loginUser();
                        Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                        startActivity(intent);
                        Toast.makeText(getApplicationContext(), "Login Successful", Toast.LENGTH_SHORT).show();
                        showSnackBar();
                    }
            }
        });

        btnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(),RegistrationActivity.class);
                startActivity(intent);
            }
        });

        googleLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                signIn();
//                Intent intent = new Intent(LoginActivity.this, GoogleActivity.class);
//                intent.putExtra("url","https://accounts.google.com");
//                startActivity(intent);
            }


        });

        fbLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(LoginActivity.this, FacebookActivity.class);
                intent.putExtra("url","https://www.facebook.com/");
                startActivity(intent);
            }
        });

        twitterLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(LoginActivity.this, TwitterActivity.class);
                intent.putExtra("url","https://twitter.com/");
                startActivity(intent);
            }
        });
    }

    private void signIn() {

        Intent signInIntent = googleSignInClient.getSignInIntent();
        startActivityForResult(signInIntent,1000);

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 1000){
            Task<GoogleSignInAccount> task = GoogleSignIn.getSignedInAccountFromIntent(data);

            try {
                task.getResult(ApiException.class);
                navigateToSecondActivity();
            } catch (ApiException e) {
                Toast.makeText(this, "Something went wrong", Toast.LENGTH_SHORT).show();
            }
        }
    }

    private void navigateToSecondActivity() {
        finish();
        Intent intent = new Intent(getApplicationContext(),MainActivity.class);
        startActivity(intent);
    }

    private void loginUser() {

        mAuth.createUserWithEmailAndPassword(UserName, PassWord)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            // Sign in success, update UI with the signed-in user's information
                            Log.d(TAG, "createUserWithEmail:success");
                            FirebaseUser user = mAuth.getCurrentUser();

                        } else {
                            // If sign in fails, display a message to the user.
                            Log.w(TAG, "createUserWithEmail:failure", task.getException());
                            Toast.makeText(getApplicationContext(), "Authentication failed.",
                                    Toast.LENGTH_SHORT).show();

                        }
                    }
                });
    }
    private void initLogin() {}

    private void showSnackBar() {
        txtWarnUserName.setVisibility(View.GONE);
        txtWarnPassword.setVisibility(View.GONE);
        TextInputEditTextUserName.setText("");
        TextInputEditTextPassword.setText("");
    }

    private boolean validateData() {

        UserName = TextInputEditTextUserName.getText().toString();

        if (!Patterns.EMAIL_ADDRESS.matcher(UserName).matches()){
            txtWarnUserName.setVisibility(View.VISIBLE);
            txtWarnUserName.setText("Enter valid Email");
            return false;
        }

        if (TextInputEditTextPassword.getText().toString().equals("")){
            txtWarnPassword.setVisibility(View.VISIBLE);
            txtWarnPassword.setText("Enter Password");
            return false;
        }
        return true;
    }

    private void initViews() {

        TextInputEditTextUserName = findViewById(R.id.TextInputEditTextUserName);
        TextInputEditTextPassword = findViewById(R.id.TextInputEditTextPassword);
        btnLogin = findViewById(R.id.btnLogin);
        btnRegister = findViewById(R.id.btnRegister);
        txtWarnUserName = findViewById(R.id.txtWarnUserName);
        txtWarnPassword = findViewById(R.id.txtWarnPassword);
        txtWarnUserName.setVisibility(View.GONE);
        txtWarnPassword.setVisibility(View.GONE);

        googleLogin = findViewById(R.id.googleLogin);
        fbLogin = findViewById(R.id.fbLogin);
        twitterLogin = findViewById(R.id.twitterLogin);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()){
            case android.R.id.home:
                onBackPressed();
                break;
            default:
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    public void btnTwitterOnClick(View view) {

    }
}
package com.example.mylibrary;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.webkit.WebView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.Task;
import com.google.android.material.snackbar.Snackbar;
import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class LoginActivity extends AppCompatActivity {

    private FirebaseAuth mAuth;

    DatabaseReference database;
    String UserName,PassWord;
    Member member1;
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
//        database = FirebaseDatabase.getInstance().getReference("Students");
//        database.addValueEventListener(new ValueEventListener() {
//            @Override
//            public void onDataChange(@NonNull DataSnapshot snapshot) {
//                for (DataSnapshot dataSnapshot : snapshot.getChildren()){
//                    member1 = dataSnapshot.getValue(Member.class);
//
//                }
//            }
//            @Override
//            public void onCancelled(@NonNull DatabaseError error) {
//            }
//        });

        initViews();
        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                UserName = TextInputEditTextUserName.getText().toString();
                PassWord = TextInputEditTextPassword.getText().toString();
//                if ((member1.getFirstName().equals(UserName)) && (member1.getPassword().equals(PassWord)))

                    Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                    startActivity(intent);
                    Toast.makeText(getApplicationContext(), "Login Successful", Toast.LENGTH_SHORT).show();
                    showSnackBar();

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
                Intent intent = new Intent(LoginActivity.this, GoogleActivity.class);
                intent.putExtra("url","https://accounts.google.com");
                startActivity(intent);
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

    private void initLogin() {


    }

    private void showSnackBar() {
        txtWarnUserName.setVisibility(View.GONE);
        txtWarnPassword.setVisibility(View.GONE);
        TextInputEditTextUserName.setText("");
        TextInputEditTextPassword.setText("");
    }

    private boolean validateData() {
        if (TextInputEditTextUserName.getText().toString().equals("")){
            txtWarnUserName.setVisibility(View.VISIBLE);
            txtWarnUserName.setText("Enter Username or Email");
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
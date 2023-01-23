package com.example.mylibrary;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class RegistrationActivity extends AppCompatActivity {

    private FirebaseAuth mAuth;
    Member member;
    String fName,lName,email,password;
    Long mobile;
    long id = 0;
    Boolean data = Boolean.TRUE;
    TextInputEditText TextInputEditTextFirstName;
    TextInputEditText TextInputEditTextLastName;
    TextInputEditText TextInputEditTextMobileNumber;
    TextInputEditText TextInputEditTextEmail;
    TextInputEditText TextInputEditTextPassWord;
    TextInputEditText TextInputEditTextReEnterPassword;

    private Button btnReg,btnSignIn;

    private TextView txtWarnFirstName;
    private TextView txtWarnLastName;
    private TextView txtWarnMobileNumber;
    private TextView txtWarnEmail;
    private TextView txtWarnPassWord;
    private TextView txtWarnConfirmPassword;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration);
        mAuth = FirebaseAuth.getInstance();
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        initView();
        btnReg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FirebaseDatabase db = FirebaseDatabase.getInstance();
                DatabaseReference root = db.getReference().child("Students");
                root.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        if(snapshot.exists()){
                            id = (snapshot.getChildrenCount());
                        }
                    }
                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {
                    }
                });
                        fName = TextInputEditTextFirstName.getText().toString();
                        lName = TextInputEditTextLastName.getText().toString();
                        mobile = Long.parseLong(TextInputEditTextMobileNumber.getText().toString());
                        email = TextInputEditTextEmail.getText().toString();
                        password = TextInputEditTextPassWord.getText().toString();
                        member = new Member(fName, lName, mobile, email, password);
                        Toast.makeText(RegistrationActivity.this, "H", Toast.LENGTH_SHORT).show();
                        mAuth.createUserWithEmailAndPassword(email,password)
                                .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                                    @Override
                                    public void onComplete(@NonNull Task<AuthResult> task) {
                                        if (task.isSuccessful()){
                                            Toast.makeText(RegistrationActivity.this, "y", Toast.LENGTH_SHORT).show();
                                            root.child(String.valueOf(id+1)).setValue(member).addOnCompleteListener(new OnCompleteListener<Void>() {
                                                @Override
                                                public void onComplete(@NonNull Task<Void> task) {
                                                    if (task.isSuccessful()){
                                                        Intent intent = new Intent(getApplicationContext(), LoginActivity.class);
                                                        startActivity(intent);
                                                        showSnackBar();
                                                    }
                                                }
                                            });


                                        }
                                    }
                                });
                    }



            private void showSnackBar() {
                TextInputEditTextFirstName.setText("");
                TextInputEditTextLastName.setText("");
                TextInputEditTextEmail.setText("");
                TextInputEditTextMobileNumber.setText("");
                TextInputEditTextPassWord.setText("");
                TextInputEditTextReEnterPassword.setText("");

                txtWarnFirstName.setVisibility(View.GONE);
                txtWarnLastName.setVisibility(View.GONE);
                txtWarnEmail.setVisibility(View.GONE);
                txtWarnMobileNumber.setVisibility(View.GONE);
                txtWarnPassWord.setVisibility(View.GONE);
                txtWarnConfirmPassword.setVisibility(View.GONE);

                Toast.makeText(RegistrationActivity.this, "Successfully Registered", Toast.LENGTH_LONG).show();

            }

            private boolean validateData() {
                if (TextInputEditTextFirstName.getText().toString().equals("")){
                    txtWarnFirstName.setVisibility(View.VISIBLE);
                    return false;
                }
                if (TextInputEditTextLastName.getText().toString().equals("")){
                    txtWarnLastName.setVisibility(View.VISIBLE);
                    return false;
                }
                if (TextInputEditTextEmail.getText().toString().equals("")){
                    txtWarnEmail.setVisibility(View.VISIBLE);
                    return false;
                }
                if (TextInputEditTextPassWord.getText().toString().equals("")){
                    txtWarnPassWord.setVisibility(View.VISIBLE);
                    return false;
                }
                if (TextInputEditTextReEnterPassword.getText().toString().equals("")){
                    txtWarnConfirmPassword.setVisibility(View.VISIBLE);
                    return false;
                }
                if (TextInputEditTextMobileNumber.getText().length() == 0){
                    txtWarnMobileNumber.setVisibility(View.VISIBLE);
                    return false;
                }

                final int length =TextInputEditTextMobileNumber.getText().length();
                if (length<10 || length>10 ){
                    txtWarnMobileNumber.setVisibility(View.VISIBLE);
                    txtWarnMobileNumber.setText("Mobile Number Should be of 10 digits");
                    return false;
                }
                if(!TextInputEditTextPassWord.getText().toString().equals(TextInputEditTextReEnterPassword.getText().toString())){
                    Toast.makeText(RegistrationActivity.this, "Passwords do not Match", Toast.LENGTH_LONG).show();
                    return false;
                }
                return  true;
            }
        });

        btnSignIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(),LoginActivity.class);
                startActivity(intent);
            }
        });
    }

    private void initView() {
        TextInputEditTextFirstName = findViewById(R.id.TextInputEditTextFirstName);
        TextInputEditTextLastName = findViewById(R.id.TextInputEditTextLastName);
        TextInputEditTextEmail = findViewById(R.id.TextInputEditTextEmail);
        TextInputEditTextMobileNumber= findViewById(R.id.TextInputEditTextMobileNumber);
        TextInputEditTextPassWord = findViewById(R.id.TextInputEditTextPassWord);
        TextInputEditTextReEnterPassword = findViewById(R.id.TextInputEditTextReEnterPassword);
        btnReg = findViewById(R.id.btnReg);
        btnSignIn = findViewById(R.id.btnSignIn);

        txtWarnFirstName = findViewById(R.id.txtWarnFirstName);
        txtWarnLastName = findViewById(R.id.txtWarnLastName);
        txtWarnEmail = findViewById(R.id.txtWarnEmail);
        txtWarnMobileNumber = findViewById(R.id.txtWarnMobileNumber);
        txtWarnPassWord = findViewById(R.id.txtWarnPassWord);
        txtWarnConfirmPassword = findViewById(R.id.txtWarnConfirmPassword);

        txtWarnFirstName.setVisibility(View.GONE);
        txtWarnLastName.setVisibility(View.GONE);
        txtWarnEmail.setVisibility(View.GONE);
        txtWarnMobileNumber.setVisibility(View.GONE);
        txtWarnPassWord.setVisibility(View.GONE);
        txtWarnConfirmPassword.setVisibility(View.GONE);

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
}
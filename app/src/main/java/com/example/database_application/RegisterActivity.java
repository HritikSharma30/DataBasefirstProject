package com.example.database_application;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import java.util.regex.Pattern;

public class RegisterActivity extends AppCompatActivity {

    private EditText editTextregisterfullname, editTextregisteremail, editTextregisternumber, editTextregister_dob, editTextregisterpassword, editTextregisterconfirmpassword;
    private ProgressBar register_progress_bar;
    private RadioGroup register_gender;
    private RadioButton radioButtonRegisterGenderSelected;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        getSupportActionBar().setTitle("Register");

        Toast.makeText(RegisterActivity.this, "You can Start Registration", Toast.LENGTH_LONG).show();

        register_progress_bar = findViewById(R.id.register_progress_bar);
        editTextregisterfullname = findViewById(R.id.editText_register_fullname);
        editTextregisteremail = findViewById(R.id.editText_register_email);
        editTextregister_dob = findViewById(R.id.editText_register_dob);
        editTextregisternumber = findViewById(R.id.editText_register_number);
        editTextregisterpassword = findViewById(R.id.editText_register_password);
        editTextregisterconfirmpassword = findViewById(R.id.editText_register_confirm_password);

        //Radio Button for Gender
        register_gender = findViewById(R.id.register_gender);
        register_gender.clearCheck();

        Button ButtonRegister = findViewById(R.id.Button_register);
        ButtonRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                int selectedGenderId = register_gender.getCheckedRadioButtonId();
                radioButtonRegisterGenderSelected = findViewById(selectedGenderId);

                //Obtain the entered data
                String textFullName = editTextregisterfullname.getText().toString();
                String textEmail = editTextregisteremail.getText().toString();
                String textDob = editTextregister_dob.getText().toString();
                String textMobile = editTextregisternumber.getText().toString();
                String textPwd = editTextregisterpassword.getText().toString();
                String textConPwd = editTextregisterconfirmpassword.getText().toString();
                String textGender;


                if (TextUtils.isEmpty(textFullName)) {
                    Toast.makeText(RegisterActivity.this, "Please enter the Full Name", Toast.LENGTH_SHORT).show();
                    editTextregisterfullname.setError("Full Name is required");
                    editTextregisterfullname.requestFocus();
                } else if (TextUtils.isEmpty(textEmail)) {
                    Toast.makeText(RegisterActivity.this, "Email de leak karke paase kamane hai", Toast.LENGTH_SHORT).show();
                    editTextregisteremail.setError("Email is required");
                    editTextregisteremail.requestFocus();
                } else if (!Patterns.EMAIL_ADDRESS.matcher(textEmail).matches()) {
                    Toast.makeText(RegisterActivity.this, "mere se shanpanti nahi", Toast.LENGTH_LONG).show();
                    editTextregisteremail.setError("Valid email is required");
                    editTextregisteremail.requestFocus();
                } else if (TextUtils.isEmpty(textDob)) {
                    Toast.makeText(RegisterActivity.this, "Enter Correct date", Toast.LENGTH_SHORT).show();
                    editTextregister_dob.setError("Date is required");
                    editTextregister_dob.requestFocus();
                } else if (register_gender.getCheckedRadioButtonId() == -1) {
                    Toast.makeText(RegisterActivity.this, "Enter Correct date", Toast.LENGTH_SHORT).show();
                    radioButtonRegisterGenderSelected.setError("Date is required");
                    radioButtonRegisterGenderSelected.requestFocus();
                }else if (TextUtils.isEmpty(textMobile)) {
                    Toast.makeText(RegisterActivity.this, "Enter your number, beautiful", Toast.LENGTH_SHORT).show();
                    editTextregisternumber.setError("Number is required");
                    editTextregisternumber.requestFocus();
                } else if (textMobile.length() != 10) {
                    Toast.makeText(RegisterActivity.this, "10 se zyada number nahi hote", Toast.LENGTH_SHORT).show();
                    editTextregisternumber.setError("Number is required");
                    editTextregisternumber.requestFocus();
                }else if (TextUtils.isEmpty(textPwd)) {
                    Toast.makeText(RegisterActivity.this, "Please enter your password", Toast.LENGTH_SHORT).show();
                    editTextregisterpassword.setError("Number is required");
                    editTextregisterpassword.requestFocus();
                }else if (TextUtils.isEmpty(textConPwd)) {
                    Toast.makeText(RegisterActivity.this, "Please enter your password", Toast.LENGTH_SHORT).show();
                    editTextregisterconfirmpassword.setError("Number is required");
                    editTextregisterconfirmpassword.requestFocus();
                }else if (!textPwd.equals(textConPwd)) {
                    Toast.makeText(RegisterActivity.this, "brain.exe stopped cant even enter the same password twice", Toast.LENGTH_SHORT).show();
                    editTextregisterconfirmpassword.setError("Mismatched");
                    editTextregisterconfirmpassword.requestFocus();
                    //clear password
                    editTextregisterpassword.clearComposingText();
                    editTextregisterconfirmpassword.clearComposingText();
                }else {

                    textGender = radioButtonRegisterGenderSelected.getText().toString();
                    register_progress_bar.setVisibility(View.VISIBLE);

                    registerUser(textFullName, textEmail, textDob, textGender, textMobile, textPwd);
                }

            }
        });

    }
    //Register User using the data given in firebase
    private void registerUser(String textFullName, String textEmail, String textDob, String textGender, String textMobile, String textPwd) {
        FirebaseAuth auth = FirebaseAuth.getInstance();
        auth.createUserWithEmailAndPassword(textEmail, textPwd).addOnCompleteListener(RegisterActivity.this, new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if (task.isSuccessful()){
                    Toast.makeText(RegisterActivity.this, "User registered successfully", Toast.LENGTH_SHORT).show();
                    FirebaseUser firebaseUser = auth.getCurrentUser();

                    //send Verification email
                    firebaseUser.sendEmailVerification();

                    /*//open user profile after successful
                    Intent newIntent = new Intent(RegisterActivity.this, UserProfileActivity.class);
                    newIntent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);

                    startActivity(newIntent);
                    finish();*/
                }
            }
        });

    }
}
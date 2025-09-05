package com.ezzco.eduplatform;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.content.Intent;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.ezzco.eduplatform.Class.BaseActivity;
import com.github.ybq.android.spinkit.sprite.Sprite;
import com.github.ybq.android.spinkit.style.ThreeBounce;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Map;

public class SignUp_Avtivity extends BaseActivity {

    TextView logintext;

    private EditText nameEditT,emailEditT,passEditT;
    private RadioGroup accTyperadioGroup;
    private Button signUpButton;
    private FirebaseAuth mAuth;
    private DatabaseReference database;

    private TextView urlEzz;


    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_sign_up_avtivity);

        logintext = findViewById(R.id.login_text);
        logintext.setOnClickListener(view -> {

            Intent intent = new
                    Intent(SignUp_Avtivity.this,Login_Activity.class);
            startActivity(intent);
            finish();

        });

        nameEditT = findViewById(R.id.name_su);
        emailEditT = findViewById(R.id.email_su);
        passEditT = findViewById(R.id.pass_su);
        accTyperadioGroup = findViewById(R.id.radg_su);
        signUpButton = findViewById(R.id.signb_su);

        mAuth = FirebaseAuth.getInstance();
        database = FirebaseDatabase.getInstance().getReference();

        signUpButton.setOnClickListener(view -> {
            Log.d("SignUp", "Sign Up button clicked");
            if (nameEditT.getText().toString().isEmpty() ||emailEditT.getText().toString().isEmpty() ||passEditT.getText().toString().isEmpty()){
                dialogShow("Please Enter All Fields");

            }else {

                loading();

                signupUser();
            }
        });

        urlEzz = findViewById(R.id.ezzeldeenProfile);
        urlEzz.setOnClickListener(view -> {
            goToEzzeldeen();
        });
    }

    private void signupUser() {

        String name = nameEditT.getText().toString().trim();
        String email = emailEditT.getText().toString().trim();
        String password = passEditT.getText().toString().trim();
        int selectedRadioButtonId = accTyperadioGroup.getCheckedRadioButtonId();

        RadioButton selectedRadioButton = findViewById(selectedRadioButtonId);
        String accountType = selectedRadioButton.getText().toString();

       // if(selectedRadioButtonId == R.id.tea_su) {
        //    accountType = "Teacher";
      //  }else if(selectedRadioButtonId == R.id.stu_su) {
          //  accountType = "Student";
      //  }else if(selectedRadioButtonId == R.id.adm_su) {
      //      accountType = "Admin";
        //}else {

        //    Toast.makeText(this, "Please select an account type", Toast.LENGTH_SHORT).show();
       //     return;

      //  }



        mAuth.createUserWithEmailAndPassword(email, password).addOnCompleteListener(this,task -> {
                    if (task.isSuccessful()) {
                        FirebaseUser user = mAuth.getCurrentUser();
                        if (user != null) {
                            user.sendEmailVerification().addOnCompleteListener(task1 -> {
                                if (task1.isSuccessful()) {

                                    String userId = user.getUid();
                                    saveUserData(userId, name, email, accountType, password);


                                    dialogShowVemail("Verification email sent to "+ user.getEmail());


                                } else {

                                    dialogShow("Failed to send verification email");

                                }
                            });
                        }
                    }else {
                       // Toast.makeText(SignUp_Avtivity.this, "Sign up failed"+ task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                        dialogShow("Sign up failed");
                        Log.e("SignUp", "Sign up failed: "+ task.getException().getMessage());
                    }
                });

    }

    private void saveUserData(String userId, String name, String email, String accountType,String password) {

        Log.d("SignUp", "Saving user data...");
        Map<String, Object> user = new HashMap<>();
        user.put("name", name);
        user.put("email", email);
        user.put("password", password);
        user.put("accountType", accountType);


        database.child("users").child(userId).setValue(user).addOnSuccessListener(aVoid -> {

            Log.d("SignUp", "User data saved successfully");
        }).addOnFailureListener(e -> {

            Log.e("SignUp", "Failed to save user data: "+ e.getMessage());

        });

    }

    public void dialogShow(String message){
        ConstraintLayout layout = findViewById(R.id.dialogA);
        View view = LayoutInflater.from(this).inflate(R.layout.dialog_alert,layout);

        TextView messageText = view.findViewById(R.id.dialogText);
        messageText.setText(message);

        @SuppressLint({"MissingInflatedId", "LocalSuppress"}) ImageView imageViewX = view.findViewById(R.id.closeDialog);

        AlertDialog.Builder builder = new AlertDialog.Builder(SignUp_Avtivity.this);
        builder.setView(view);
        final AlertDialog alertDialog = builder.create();


        imageViewX.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                alertDialog.dismiss();

            }
        });

        if (alertDialog.getWindow() != null){
            alertDialog.getWindow().setBackgroundDrawable(new ColorDrawable(0));
        }

        alertDialog.show();


    }

    public void dialogShowVemail(String message){
        ConstraintLayout layout = findViewById(R.id.dialogA);
        View view = LayoutInflater.from(this).inflate(R.layout.dialog_alert,layout);

        TextView messageText = view.findViewById(R.id.dialogText);
        messageText.setText(message);

         @SuppressLint({"MissingInflatedId", "LocalSuppress"}) ImageView emailD= view.findViewById(R.id.emailDialog);
         @SuppressLint({"MissingInflatedId", "LocalSuppress"}) ImageView oppsEmoji = view.findViewById(R.id.oopsEmoji);
         emailD.setVisibility(View.VISIBLE);
         oppsEmoji.setVisibility(View.GONE);




        @SuppressLint({"MissingInflatedId", "LocalSuppress"}) ImageView imageViewX = view.findViewById(R.id.closeDialog);

        AlertDialog.Builder builder = new AlertDialog.Builder(SignUp_Avtivity.this);
        builder.setView(view);
        final AlertDialog alertDialog = builder.create();


        imageViewX.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                alertDialog.dismiss();

                Intent intent = new Intent(SignUp_Avtivity.this, Login_Activity.class);
                startActivity(intent);
                finish();

            }
        });

        if (alertDialog.getWindow() != null){
            alertDialog.getWindow().setBackgroundDrawable(new ColorDrawable(0));
        }

        alertDialog.show();


    }

    private void goToEzzeldeen() {

        String url = "https://www.facebook.com/Itsezzeldeen";
        Intent intent = new Intent(Intent.ACTION_VIEW);
        intent.setData(android.net.Uri.parse(url));
        startActivity(intent);

    }

    public void loading(){

        ProgressBar progressBar = (ProgressBar)findViewById(R.id.loading);
        Sprite doubleBounce = new ThreeBounce();
        progressBar.setVisibility(View.VISIBLE);
        progressBar.setIndeterminateDrawable(doubleBounce);

    }



    public void onBackPressed(){
        finishAffinity();
    }




}
package com.ezzco.eduplatform;

import android.annotation.SuppressLint;
import android.app.Dialog;
import android.content.Intent;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.util.Log;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.ezzco.eduplatform.Class.BaseActivity;
import com.github.ybq.android.spinkit.sprite.Sprite;
import com.github.ybq.android.spinkit.style.ChasingDots;
import com.github.ybq.android.spinkit.style.DoubleBounce;
import com.github.ybq.android.spinkit.style.FoldingCube;
import com.github.ybq.android.spinkit.style.ThreeBounce;
import com.github.ybq.android.spinkit.style.WanderingCubes;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.view.LayoutInflater;
import android.view.View;

public class Login_Activity extends BaseActivity {



    private Button login;
    TextView signuptext;

    private EditText emailEditT,passEditT;
    private FirebaseAuth mAuth;
    private DatabaseReference database;

    private TextView urlEzz;
    private TextView resetPass;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_login);

        login = findViewById(R.id.login_b);


        signuptext = findViewById(R.id.signup_text);
        signuptext.setOnClickListener(view -> {
            Intent intent = new
                    Intent(Login_Activity.this,SignUp_Avtivity.class);
            startActivity(intent);
            finish();
        });

        emailEditT = findViewById(R.id.email_li);
        passEditT = findViewById(R.id.pass_li);

        mAuth = FirebaseAuth.getInstance();
        database = FirebaseDatabase.getInstance().getReference();


        login.setOnClickListener(view -> {

            String email = emailEditT.getText().toString().trim();
            String password = passEditT.getText().toString().trim();
            if (email.isEmpty()||password.isEmpty()){

                dialogShow("Please Enter Email & Password");
                return;
            }

            loading();

            loginUser();

            updatePasswordInDatabase(email,password);
        });

        urlEzz = findViewById(R.id.ezzeldeenProfile);
        urlEzz.setOnClickListener(view -> {
            goToEzzeldeen();
        });

        resetPass = findViewById(R.id.forgotPass);
        resetPass.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Login_Activity.this, ResetPasswordActivity.class);
                startActivity(intent);
            }
        });
    }

    private void goToEzzeldeen() {

        String url = "https://www.facebook.com/Itsezzeldeen";
        Intent intent = new Intent(Intent.ACTION_VIEW);
        intent.setData(android.net.Uri.parse(url));
        startActivity(intent);

    }

    private void loginUser() {

        String email = emailEditT.getText().toString().trim();
        String password = passEditT.getText().toString().trim();



        mAuth.signInWithEmailAndPassword(email,password).addOnCompleteListener(task -> {

            if(task.isSuccessful()){
                FirebaseUser user = mAuth.getCurrentUser();
                if (user != null && user.isEmailVerified()){
                    checkUserType(user.getUid());
                }else {
                    dialogShow("Please verify your email");

                    if(user != null){
                        user.sendEmailVerification();

                    }

                }
            }else {
                Log.e("Login", "Login failed: "+ task.getException().getMessage());

                dialogShow("The Email or Password is Incorrect");
            }

        });

    }

    private void checkUserType(String userId) {

        Log.d("Login", "Checking user type...");

        database.child("users").child(userId).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if (dataSnapshot.exists()) {
                    String accountType = dataSnapshot.child("accountType").getValue(String.class);
                    if (accountType != null){

                        Intent intent;

                        switch (accountType){
                            case "Teacher":
                                intent = new Intent(Login_Activity.this,Teacher_Home.class);
                                break;

                            case "Student":
                                intent = new Intent(Login_Activity.this,Student_Home.class);
                                break;

                            case "Admin":
                                intent = new Intent(Login_Activity.this,Admin_Home.class);
                                break;
                            default:
                                dialogShow("Unknown account type");
                                return;

                        }
                        startActivity(intent);
                        finish();

                    }else {

                        dialogShow("Account type not found");
                        Log.e("Login", "Account type not found");
                    }
                }else {
                    dialogShow("User data not found");
                    Log.e("Login", "User data not found");
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {


                dialogShow("Failed to check user data");
                Log.e("Login", "Failed to check user data: "+ error.toException());

            }
        });

    }

    private void updatePasswordInDatabase(String email, String newPassword) {
        FirebaseAuth auth = FirebaseAuth.getInstance();
        auth.signInWithEmailAndPassword(email, newPassword).addOnCompleteListener(task -> {
            if (task.isSuccessful()) {
                // تسجيل الدخول ناجح
                FirebaseUser user = auth.getCurrentUser();
                if (user != null) {
                    String uid = user.getUid();

                    // تحديث كلمة المرور في Realtime Database
                    DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference("users").child(uid);
                    databaseReference.child("password").setValue(newPassword);

                    // عرض رسالة نجاح للمستخدم

                }
            } else {
                // التعامل مع الأخطاء عند تسجيل الدخول
                Log.e("LoginError", "Failed to sign in: " + task.getException().getMessage());
            }
        });
    }


    public void dialogShow(String message){
        ConstraintLayout layout = findViewById(R.id.dialogA);
        View view = LayoutInflater.from(this).inflate(R.layout.dialog_alert,layout);

        TextView messageText = view.findViewById(R.id.dialogText);
        messageText.setText(message);

        @SuppressLint({"MissingInflatedId", "LocalSuppress"}) ImageView imageViewX = view.findViewById(R.id.closeDialog);

        AlertDialog.Builder builder = new AlertDialog.Builder(Login_Activity.this);
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
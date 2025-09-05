package com.ezzco.eduplatform;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.content.Intent;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.ezzco.eduplatform.Class.BaseActivity;
import com.google.firebase.auth.FirebaseAuth;

public class ResetPasswordActivity extends BaseActivity {

    private EditText emailEditText;
    private Button resetPasswordButton;
    private FirebaseAuth mAuth;
    private TextView urlEzz;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_reset_password);


        emailEditText = findViewById(R.id.email_resetP);
        resetPasswordButton = findViewById(R.id.reset_PB);
        mAuth = FirebaseAuth.getInstance();

        resetPasswordButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String email = emailEditText.getText().toString().trim();
                if (TextUtils.isEmpty(email)) {
                   // Toast.makeText(ResetPasswordActivity.this, "Please enter your email", Toast.LENGTH_SHORT).show();
                    dialogShow("Please enter your email");
                    return;
                }

                mAuth.sendPasswordResetEmail(email)
                        .addOnCompleteListener(task -> {
                            if (task.isSuccessful()) {
                               // Toast.makeText(ResetPasswordActivity.this, "Password reset email sent.", Toast.LENGTH_SHORT).show();
                                dialogShowVemail("Password reset email sent.");
                            } else {
                                //Toast.makeText(ResetPasswordActivity.this, "Failed to send reset email.", Toast.LENGTH_SHORT).show();
                                dialogShow("Failed to send reset email");
                            }
                        });

            }
        });

        urlEzz = findViewById(R.id.ezzeldeenProfile);
        urlEzz.setOnClickListener(view -> {
            goToEzzeldeen();
        });



    }

    public void dialogShow(String message){
        ConstraintLayout layout = findViewById(R.id.dialogA);
        View view = LayoutInflater.from(this).inflate(R.layout.dialog_alert,layout);

        TextView messageText = view.findViewById(R.id.dialogText);
        messageText.setText(message);

        @SuppressLint({"MissingInflatedId", "LocalSuppress"}) ImageView imageViewX = view.findViewById(R.id.closeDialog);

        AlertDialog.Builder builder = new AlertDialog.Builder(ResetPasswordActivity.this);
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

        AlertDialog.Builder builder = new AlertDialog.Builder(ResetPasswordActivity.this);
        builder.setView(view);
        final AlertDialog alertDialog = builder.create();


        imageViewX.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                alertDialog.dismiss();

                Intent intent = new Intent(ResetPasswordActivity.this, Login_Activity.class);
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


}
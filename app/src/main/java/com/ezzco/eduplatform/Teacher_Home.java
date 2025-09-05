package com.ezzco.eduplatform;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.ezzco.eduplatform.Class.BaseActivity;
import com.google.firebase.auth.FirebaseAuth;

public class Teacher_Home extends BaseActivity {

    private Button LogOutB;
    private FirebaseAuth mAuth;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_teacher_home);

        LogOutB = findViewById(R.id.logout_tech);
        mAuth = FirebaseAuth.getInstance();

        LogOutB.setOnClickListener(view -> {

            mAuth.signOut();
            Intent intent = new Intent(Teacher_Home.this,Login_Activity.class);
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
            startActivity(intent);
            finish();

        });

    }
    public void onBackPressed(){
        finishAffinity();
    }
}
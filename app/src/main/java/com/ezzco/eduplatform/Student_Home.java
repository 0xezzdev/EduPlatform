package com.ezzco.eduplatform;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.animation.ScaleAnimation;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.ezzco.eduplatform.Class.BaseActivity;
import com.ezzco.eduplatform.Student.SCartFragment;
import com.ezzco.eduplatform.Student.SHomeFragment;
import com.ezzco.eduplatform.Student.SInfoFragment;
import com.ezzco.eduplatform.Student.SUserFragment;
import com.google.firebase.auth.FirebaseAuth;

public class Student_Home extends BaseActivity {

    private FirebaseAuth mAuth;

    private LinearLayout shomeLayout,suserLayout,scartLayout,sinfoLayout;
    private ImageView shomeImage,suserImage,scartImage,sinfoImage;
    private TextView shomeText,suserText,scartText,sinfoText;
    private int selectedTap = 1;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_student_home);

        mAuth = FirebaseAuth.getInstance();

        shomeLayout = findViewById(R.id.shomeLayout);
        suserLayout = findViewById(R.id.suserLayout);
        scartLayout = findViewById(R.id.scartLayout);
        sinfoLayout = findViewById(R.id.sinfoLayout);

        shomeImage = findViewById(R.id.shomeImage);
        suserImage = findViewById(R.id.suserImage);
        scartImage = findViewById(R.id.scartImage);
        sinfoImage = findViewById(R.id.sinfoImage);

        shomeText = findViewById(R.id.shomeText);
        suserText = findViewById(R.id.suserText);
        scartText = findViewById(R.id.scartText);
        sinfoText = findViewById(R.id.sinfoText);

        getSupportFragmentManager().beginTransaction()
                        .setReorderingAllowed(true)
                                .replace(R.id.fragmentContainer, SHomeFragment.class,null)
                                        .commit();

        shomeLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (selectedTap != 1){

                    getSupportFragmentManager().beginTransaction()
                            .setReorderingAllowed(true)
                            .replace(R.id.fragmentContainer, SHomeFragment.class,null)
                            .commit();

                    //unselected tap
                    suserText.setVisibility(View.GONE);
                    scartText.setVisibility(View.GONE);
                    sinfoText.setVisibility(View.GONE);

                    suserImage.setImageResource(R.drawable.userisvg);
                    scartImage.setImageResource(R.drawable.cartisvg);
                    sinfoImage.setImageResource(R.drawable.infoisvg);

                    suserLayout.setBackgroundColor(getResources().getColor(android.R.color.transparent));
                    scartLayout.setBackgroundColor(getResources().getColor(android.R.color.transparent));
                    sinfoLayout.setBackgroundColor(getResources().getColor(android.R.color.transparent));

                    //selected tap
                    shomeText.setVisibility(View.VISIBLE);
                    shomeImage.setImageResource(R.drawable.homeisvg_s);
                    shomeLayout.setBackgroundResource(R.drawable.round_back);

                    //create animation
                    ScaleAnimation scaleAnimation = new ScaleAnimation(0.8f,1.0f,1f,1f, ScaleAnimation.RELATIVE_TO_SELF,0.0f,ScaleAnimation.RELATIVE_TO_SELF,0.0f);
                    scaleAnimation.setDuration(200);
                    scaleAnimation.setFillAfter(true);
                    shomeLayout.startAnimation(scaleAnimation);

                    // set 1st tap as selected
                    selectedTap = 1;

                }

            }
        });

        suserLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (selectedTap != 2){

                    getSupportFragmentManager().beginTransaction()
                            .setReorderingAllowed(true)
                            .replace(R.id.fragmentContainer, SUserFragment.class,null)
                            .commit();

                    //unselected tap
                    shomeText.setVisibility(View.GONE);
                    scartText.setVisibility(View.GONE);
                    sinfoText.setVisibility(View.GONE);

                    shomeImage.setImageResource(R.drawable.home_isvg);
                    scartImage.setImageResource(R.drawable.cartisvg);
                    sinfoImage.setImageResource(R.drawable.infoisvg);

                    shomeLayout.setBackgroundColor(getResources().getColor(android.R.color.transparent));
                    scartLayout.setBackgroundColor(getResources().getColor(android.R.color.transparent));
                    sinfoLayout.setBackgroundColor(getResources().getColor(android.R.color.transparent));

                    //selected tap
                    suserText.setVisibility(View.VISIBLE);
                    suserImage.setImageResource(R.drawable.userisvg_s);
                    suserLayout.setBackgroundResource(R.drawable.round_back);

                    //create animation
                    ScaleAnimation scaleAnimation = new ScaleAnimation(0.8f,1.0f,1f,1f, ScaleAnimation.RELATIVE_TO_SELF,1.0f,ScaleAnimation.RELATIVE_TO_SELF,0.0f);
                    scaleAnimation.setDuration(200);
                    scaleAnimation.setFillAfter(true);
                    suserLayout.startAnimation(scaleAnimation);

                    // set 2st tap as selected
                    selectedTap = 2;

                }

            }
        });

        scartLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (selectedTap != 3){

                    getSupportFragmentManager().beginTransaction()
                            .setReorderingAllowed(true)
                            .replace(R.id.fragmentContainer, SCartFragment.class,null)
                            .commit();

                    //unselected tap
                    shomeText.setVisibility(View.GONE);
                    suserText.setVisibility(View.GONE);
                    sinfoText.setVisibility(View.GONE);

                    shomeImage.setImageResource(R.drawable.home_isvg);
                    suserImage.setImageResource(R.drawable.userisvg);
                    sinfoImage.setImageResource(R.drawable.infoisvg);

                    shomeLayout.setBackgroundColor(getResources().getColor(android.R.color.transparent));
                    suserLayout.setBackgroundColor(getResources().getColor(android.R.color.transparent));
                    sinfoLayout.setBackgroundColor(getResources().getColor(android.R.color.transparent));

                    //selected tap
                    scartText.setVisibility(View.VISIBLE);
                    scartImage.setImageResource(R.drawable.cartisvg_s);
                    scartLayout.setBackgroundResource(R.drawable.round_back);

                    //create animation
                    ScaleAnimation scaleAnimation = new ScaleAnimation(0.8f,1.0f,1f,1f, ScaleAnimation.RELATIVE_TO_SELF,1.0f,ScaleAnimation.RELATIVE_TO_SELF,0.0f);
                    scaleAnimation.setDuration(200);
                    scaleAnimation.setFillAfter(true);
                    scartLayout.startAnimation(scaleAnimation);

                    // set 3st tap as selected
                    selectedTap = 3;

                }

            }
        });

        sinfoLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (selectedTap != 4){

                    getSupportFragmentManager().beginTransaction()
                            .setReorderingAllowed(true)
                            .replace(R.id.fragmentContainer, SInfoFragment.class,null)
                            .commit();

                    //unselected tap
                    shomeText.setVisibility(View.GONE);
                    suserText.setVisibility(View.GONE);
                    scartText.setVisibility(View.GONE);

                    shomeImage.setImageResource(R.drawable.home_isvg);
                    suserImage.setImageResource(R.drawable.userisvg);
                    scartImage.setImageResource(R.drawable.cartisvg);

                    shomeLayout.setBackgroundColor(getResources().getColor(android.R.color.transparent));
                    suserLayout.setBackgroundColor(getResources().getColor(android.R.color.transparent));
                    scartLayout.setBackgroundColor(getResources().getColor(android.R.color.transparent));

                    //selected tap
                    sinfoText.setVisibility(View.VISIBLE);
                    sinfoImage.setImageResource(R.drawable.infoisvg_s);
                    sinfoLayout.setBackgroundResource(R.drawable.round_back);

                    //create animation
                    ScaleAnimation scaleAnimation = new ScaleAnimation(0.8f,1.0f,1f,1f, ScaleAnimation.RELATIVE_TO_SELF,1.0f,ScaleAnimation.RELATIVE_TO_SELF,0.0f);
                    scaleAnimation.setDuration(200);
                    scaleAnimation.setFillAfter(true);
                    sinfoLayout.startAnimation(scaleAnimation);

                    // set 4st tap as selected
                    selectedTap = 4;

                }

            }
        });



    }

    public void onBackPressed(){
        finishAffinity();
    }
}
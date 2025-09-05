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
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.ezzco.eduplatform.Admin.AInfoFragment;
import com.ezzco.eduplatform.Admin.AUserFragment;
import com.ezzco.eduplatform.Admin.AdashboardFragment;
import com.ezzco.eduplatform.Admin.AdatabaseFragment;
import com.ezzco.eduplatform.Class.BaseActivity;
import com.ezzco.eduplatform.Student.SCartFragment;
import com.ezzco.eduplatform.Student.SHomeFragment;
import com.ezzco.eduplatform.Student.SInfoFragment;
import com.ezzco.eduplatform.Student.SUserFragment;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.auth.FirebaseAuth;

public class Admin_Home extends BaseActivity {

    private FirebaseAuth mAuth;

    private LinearLayout AdashboardLayout,AuserLayout,AdatabaseLayout,AinfoLayout;
    private ImageView AdashboardImage,AuserImage,AdatabaseImage,AinfoImage;
    private TextView AdashboardText,AuserText,AdatabaseText,AinfoText;
    private int selectedTap = 1;

    FloatingActionButton men,b1,b2,b3,b4;

    boolean isOpen = false;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_admin_home);


        mAuth = FirebaseAuth.getInstance();

        men = findViewById(R.id.menuFb);
        b1 = findViewById(R.id.DashFb);
        b2 = findViewById(R.id.DataBFb);
        b3 = findViewById(R.id.UserFb);
        b4 = findViewById(R.id.InfoFb);




       AdashboardLayout = findViewById(R.id.AdashboardLayout);
       AuserLayout = findViewById(R.id.AuserLayout);
       AdatabaseLayout = findViewById(R.id.AdatabaseLayout);
       AinfoLayout = findViewById(R.id.AinfoLayout);

       AdashboardImage = findViewById(R.id.AdashboardImage);
       AuserImage = findViewById(R.id.AuserImage);
       AdatabaseImage = findViewById(R.id.AdatabaseImage);
       AinfoImage = findViewById(R.id.AinfoImage);

       AdashboardText = findViewById(R.id.AdashboardText);
       AuserText = findViewById(R.id.AuserText);
       AdatabaseText = findViewById(R.id.AdatabaseText);
       AinfoText = findViewById(R.id.AinfoText);

        getSupportFragmentManager().beginTransaction()
                .setReorderingAllowed(true)
                .replace(R.id.fragmentContainer, AdashboardFragment.class,null)
                .commit();



        if (AdashboardLayout!=null&&AuserLayout!=null&&AdatabaseLayout!=null&AinfoLayout!=null){



            AdashboardLayout.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                    if (selectedTap != 1){

                        getSupportFragmentManager().beginTransaction()
                                .setReorderingAllowed(true)
                                .replace(R.id.fragmentContainer, AdashboardFragment.class,null)
                                .commit();

                        //unselected tap
                        AuserText.setVisibility(View.GONE);
                        AdatabaseText.setVisibility(View.GONE);
                        AinfoText.setVisibility(View.GONE);

                        AuserImage.setImageResource(R.drawable.adminuser);
                        AdatabaseImage.setImageResource(R.drawable.database);
                        AinfoImage.setImageResource(R.drawable.infoisvg);

                        AuserLayout.setBackgroundColor(getResources().getColor(android.R.color.transparent));
                        AdatabaseLayout.setBackgroundColor(getResources().getColor(android.R.color.transparent));
                        AinfoLayout.setBackgroundColor(getResources().getColor(android.R.color.transparent));

                        //selected tap
                        AdashboardText.setVisibility(View.VISIBLE);
                        AdashboardImage.setImageResource(R.drawable.dashboard_s);
                        AdashboardLayout.setBackgroundResource(R.drawable.round_back);

                        //create animation
                        ScaleAnimation scaleAnimation = new ScaleAnimation(0.8f,1.0f,1f,1f, ScaleAnimation.RELATIVE_TO_SELF,0.0f,ScaleAnimation.RELATIVE_TO_SELF,0.0f);
                        scaleAnimation.setDuration(200);
                        scaleAnimation.setFillAfter(true);
                        AdashboardLayout.startAnimation(scaleAnimation);

                        // set 1st tap as selected
                        selectedTap = 1;

                    }

                }
            });

            AuserLayout.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                    if (selectedTap != 2){

                        getSupportFragmentManager().beginTransaction()
                                .setReorderingAllowed(true)
                                .replace(R.id.fragmentContainer, AUserFragment.class,null)
                                .commit();

                        //unselected tap
                        AdashboardText.setVisibility(View.GONE);
                        AdatabaseText.setVisibility(View.GONE);
                        AinfoText.setVisibility(View.GONE);

                        AdashboardImage.setImageResource(R.drawable.dashboard);
                        AdatabaseImage.setImageResource(R.drawable.database);
                        AinfoImage.setImageResource(R.drawable.infoisvg);

                        AdashboardLayout.setBackgroundColor(getResources().getColor(android.R.color.transparent));
                        AdatabaseLayout.setBackgroundColor(getResources().getColor(android.R.color.transparent));
                        AinfoLayout.setBackgroundColor(getResources().getColor(android.R.color.transparent));

                        //selected tap
                        AuserText.setVisibility(View.VISIBLE);
                        AuserImage.setImageResource(R.drawable.adminuser_s);
                        AuserLayout.setBackgroundResource(R.drawable.round_back);

                        //create animation
                        ScaleAnimation scaleAnimation = new ScaleAnimation(0.8f,1.0f,1f,1f, ScaleAnimation.RELATIVE_TO_SELF,1.0f,ScaleAnimation.RELATIVE_TO_SELF,0.0f);
                        scaleAnimation.setDuration(200);
                        scaleAnimation.setFillAfter(true);
                        AuserLayout.startAnimation(scaleAnimation);

                        // set 2st tap as selected
                        selectedTap = 2;

                    }

                }
            });

            AdatabaseLayout.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                    if (selectedTap != 3){

                        getSupportFragmentManager().beginTransaction()
                                .setReorderingAllowed(true)
                                .replace(R.id.fragmentContainer, AdatabaseFragment.class,null)
                                .commit();

                        //unselected tap
                        AdashboardText.setVisibility(View.GONE);
                        AuserText.setVisibility(View.GONE);
                        AinfoText.setVisibility(View.GONE);

                        AdashboardImage.setImageResource(R.drawable.dashboard);
                        AuserImage.setImageResource(R.drawable.adminuser);
                        AinfoImage.setImageResource(R.drawable.infoisvg);

                        AdashboardLayout.setBackgroundColor(getResources().getColor(android.R.color.transparent));
                        AuserLayout.setBackgroundColor(getResources().getColor(android.R.color.transparent));
                        AinfoLayout.setBackgroundColor(getResources().getColor(android.R.color.transparent));

                        //selected tap
                        AdatabaseText.setVisibility(View.VISIBLE);
                        AdatabaseImage.setImageResource(R.drawable.database_s);
                        AdatabaseLayout.setBackgroundResource(R.drawable.round_back);

                        //create animation
                        ScaleAnimation scaleAnimation = new ScaleAnimation(0.8f,1.0f,1f,1f, ScaleAnimation.RELATIVE_TO_SELF,1.0f,ScaleAnimation.RELATIVE_TO_SELF,0.0f);
                        scaleAnimation.setDuration(200);
                        scaleAnimation.setFillAfter(true);
                        AdatabaseLayout.startAnimation(scaleAnimation);

                        // set 3st tap as selected
                        selectedTap = 3;

                    }

                }
            });

            AinfoLayout.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                    if (selectedTap != 4){

                        getSupportFragmentManager().beginTransaction()
                                .setReorderingAllowed(true)
                                .replace(R.id.fragmentContainer, AInfoFragment.class,null)
                                .commit();

                        //unselected tap
                        AdashboardText.setVisibility(View.GONE);
                        AuserText.setVisibility(View.GONE);
                        AdatabaseText.setVisibility(View.GONE);

                        AdashboardImage.setImageResource(R.drawable.dashboard);
                        AuserImage.setImageResource(R.drawable.adminuser);
                        AdatabaseImage.setImageResource(R.drawable.database);

                        AdashboardLayout.setBackgroundColor(getResources().getColor(android.R.color.transparent));
                        AuserLayout.setBackgroundColor(getResources().getColor(android.R.color.transparent));
                        AdatabaseLayout.setBackgroundColor(getResources().getColor(android.R.color.transparent));

                        //selected tap
                        AinfoText.setVisibility(View.VISIBLE);
                        AinfoImage.setImageResource(R.drawable.infoisvg_s);
                        AinfoLayout.setBackgroundResource(R.drawable.round_back);

                        //create animation
                        ScaleAnimation scaleAnimation = new ScaleAnimation(0.8f,1.0f,1f,1f, ScaleAnimation.RELATIVE_TO_SELF,1.0f,ScaleAnimation.RELATIVE_TO_SELF,0.0f);
                        scaleAnimation.setDuration(200);
                        scaleAnimation.setFillAfter(true);
                        AinfoLayout.startAnimation(scaleAnimation);

                        // set 4st tap as selected
                        selectedTap = 4;

                    }

                }
            });

        }



        if(men != null){

            men.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {


                    if(!isOpen){

                        openMenu();

                    }else {
                        closeMenu();
                    }

                }
            });


        }





    }

    private void closeMenu() {
        isOpen = false;
        b1.animate().translationY(0);
        b2.animate().translationY(0);
        b3.animate().translationY(0);
        b4.animate().translationY(0);

    }

    private void openMenu() {
        isOpen = true;

        b1.animate().translationY(-getResources().getDimension(R.dimen.satn_60));
        b2.animate().translationY(-getResources().getDimension(R.dimen.satn_110));
        b3.animate().translationY(-getResources().getDimension(R.dimen.satn_160));
        b4.animate().translationY(-getResources().getDimension(R.dimen.satn_210));

        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getSupportFragmentManager().beginTransaction()
                        .setReorderingAllowed(true)
                        .replace(R.id.fragmentContainer, AdashboardFragment.class,null)
                        .commit();
            }
        });

        b2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getSupportFragmentManager().beginTransaction()
                        .setReorderingAllowed(true)
                        .replace(R.id.fragmentContainer, AdatabaseFragment.class,null)
                        .commit();
            }
        });

        b3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getSupportFragmentManager().beginTransaction()
                        .setReorderingAllowed(true)
                        .replace(R.id.fragmentContainer, AUserFragment.class,null)
                        .commit();
            }
        });

        b4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getSupportFragmentManager().beginTransaction()
                        .setReorderingAllowed(true)
                        .replace(R.id.fragmentContainer, AInfoFragment.class,null)
                        .commit();
            }
        });

    }

    public void onBackPressed(){
        finishAffinity();
    }
}
package com.ezzco.eduplatform;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.View;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.ezzco.eduplatform.Class.BaseActivity;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class ForEditDesign extends BaseActivity {

    FloatingActionButton men,b1,b2,b3,b4;

    boolean isOpen = false;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_for_edit_design);

        men = findViewById(R.id.menuFb);
        b1 = findViewById(R.id.DashFb);
        b2 = findViewById(R.id.DataBFb);
        b3 = findViewById(R.id.UserFb);
        b4 = findViewById(R.id.InfoFb);

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

    }
}
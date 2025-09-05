package com.ezzco.eduplatform;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.content.Intent;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
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
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class MainActivity extends BaseActivity {

    private DatabaseReference database;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);

        database = FirebaseDatabase.getInstance().getReference();


        new Handler().postDelayed(() ->
        {
            FirebaseUser currentUser = FirebaseAuth.getInstance().getCurrentUser();
            if(currentUser != null && currentUser.isEmailVerified()){
                checkUserType(currentUser.getUid());
            }else{
                startActivity(new Intent(MainActivity.this,Login_Activity.class));
            }
        },3000);


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
                                intent = new Intent(MainActivity.this,Teacher_Home.class);
                                break;

                            case "Student":
                                intent = new Intent(MainActivity.this,Student_Home.class);
                                break;

                            case "Admin":
                                intent = new Intent(MainActivity.this,Admin_Home.class);
                                break;
                            default:
                                //Toast.makeText(MainActivity.this, "Unknown account type", Toast.LENGTH_SHORT).show();
                                dialogShow("Unknown account type");
                                return;

                        }
                        startActivity(intent);
                        finish();

                    }else {
                        //Toast.makeText(MainActivity.this, "Account type not found", Toast.LENGTH_SHORT).show();
                        dialogShow("Account type not found");
                        Log.e("Login", "Account type not found");
                        startActivity(new Intent(MainActivity.this,Login_Activity.class));
                    }
                }else {
                    //Toast.makeText(MainActivity.this, "User data not found", Toast.LENGTH_SHORT).show();
                    dialogShow("User data not found");
                    Log.e("Login", "User data not found");
                    startActivity(new Intent(MainActivity.this,Login_Activity.class));
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

                //Toast.makeText(MainActivity.this, "Failed to check user data", Toast.LENGTH_SHORT).show();
                dialogShow("Failed to check user data");
                Log.e("Login", "Failed to check user data: "+ error.toException());

            }
        });

    }

    public void dialogShow(String message){
        ConstraintLayout layout = findViewById(R.id.dialogA);
        View view = LayoutInflater.from(this).inflate(R.layout.dialog_alert,layout);

        TextView messageText = view.findViewById(R.id.dialogText);
        messageText.setText(message);

        @SuppressLint({"MissingInflatedId", "LocalSuppress"}) ImageView imageViewX = view.findViewById(R.id.closeDialog);

        AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
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


}
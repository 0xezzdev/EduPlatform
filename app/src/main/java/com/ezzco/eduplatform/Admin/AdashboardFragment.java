package com.ezzco.eduplatform.Admin;

import android.annotation.SuppressLint;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import android.os.Handler;
import android.os.Looper;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.ezzco.eduplatform.R;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link AdashboardFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class AdashboardFragment extends Fragment {

    private DatabaseReference mDatabase;
    private DatabaseReference mDatabaseC;
    private FirebaseAuth mAuth;

    private TextView totalUsersTextViewC;
    private TextView totalTeachersTextViewC;
    private TextView totalStudentsTextViewC;
    private TextView totalAdminsTextViewC;
    private Handler handler = new Handler(Looper.getMainLooper());

    private TextView AccName;

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public AdashboardFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment AdashboardFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static AdashboardFragment newInstance(String param1, String param2) {
        AdashboardFragment fragment = new AdashboardFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @SuppressLint("MissingInflatedId")
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_adashboard, container, false);


        AccName = view.findViewById(R.id.AaccName);
        SetAccName();

        totalUsersTextViewC = view.findViewById(R.id.nOfTotal);
        totalTeachersTextViewC = view.findViewById(R.id.nOfTeach);
        totalStudentsTextViewC = view.findViewById(R.id.nOfStud);
        totalAdminsTextViewC = view.findViewById(R.id.nOfAdmin);

        startCheckingUsers();

        return view;
    }

    private void startCheckingUsers() {

        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                countAccs();
                handler.postDelayed(this, 1000);
            }
        },1000);

    }

    private void countAccs() {

        mAuth = FirebaseAuth.getInstance();

        mDatabaseC = FirebaseDatabase.getInstance().getReference("users");
        mDatabaseC.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                int totalUsersC = 0;
                int totalTeachersC = 0;
                int totalStudentsC = 0;
                int totalAdminsC = 0;

                for (DataSnapshot dataSnapshot : snapshot.getChildren()){
                    totalUsersC++;
                    String accountType = dataSnapshot.child("accountType").getValue(String.class);

                    if ("Admin".equals(accountType)){
                        totalAdminsC++;
                    }else if ("Teacher".equals(accountType)){
                        totalTeachersC++;
                    }else if ("Student".equals(accountType)){
                        totalStudentsC++;
                    }
                }

                totalUsersTextViewC.setText(String.valueOf(totalUsersC));
                totalTeachersTextViewC.setText(String.valueOf(totalTeachersC));
                totalStudentsTextViewC.setText(String.valueOf(totalStudentsC));
                totalAdminsTextViewC.setText(String.valueOf(totalAdminsC));

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        handler.removeCallbacksAndMessages(null);
    }

    private void SetAccName() {


        mAuth = FirebaseAuth.getInstance();
        FirebaseUser currentUser = mAuth.getCurrentUser();

        if (currentUser != null) {

            String nameId = currentUser.getUid();

            mDatabase = FirebaseDatabase.getInstance().getReference("users").child(nameId).child("name");



            mDatabase.addListenerForSingleValueEvent(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot snapshot) {

                    if (snapshot.exists()) {

                        String Name = snapshot.getValue(String.class);

                        AccName.setText(Name);

                    }

                }

                @Override
                public void onCancelled(@NonNull DatabaseError error) {

                }
            });

        }



    }





}
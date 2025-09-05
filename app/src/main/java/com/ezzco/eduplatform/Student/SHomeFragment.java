package com.ezzco.eduplatform.Student;

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
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link SHomeFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class SHomeFragment extends Fragment {

    private DatabaseReference mDatabase;
    private TextView totalUsersTextView;
    private TextView totalTeachersTextView;
    private TextView totalStudentsTextView;
    private TextView totalAdminsTextView;
    private Handler handler = new Handler(Looper.getMainLooper());

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public SHomeFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment SHomeFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static SHomeFragment newInstance(String param1, String param2) {
        SHomeFragment fragment = new SHomeFragment();
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
        View view = inflater.inflate(R.layout.fragment_s_home, container, false);

        totalUsersTextView = view.findViewById(R.id.totalN);
        totalTeachersTextView = view.findViewById(R.id.TechN);
        totalStudentsTextView = view.findViewById(R.id.StudN);
        totalAdminsTextView = view.findViewById(R.id.AdminN);

        mDatabase = FirebaseDatabase.getInstance().getReference("users");

        startCheckingUsers();

       // mDatabase.addValueEventListener(new ValueEventListener() {
          //  @Override
          //  public void onDataChange(@NonNull DataSnapshot snapshot) {
            //    int totalUsers = (int) snapshot.getChildrenCount();

//                totalUsersTextView.setText("Total: "+ totalUsers);
  //          }

    //        @Override
      //      public void onCancelled(@NonNull DatabaseError error) {

        //    }
       // });
        return view;
        
    }

    private void startCheckingUsers() {

        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                checkUsers();
                handler.postDelayed(this, 1000);
            }
        },1000);

    }

    private void checkUsers() {

        mDatabase.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                int totalUsers = 0;
                int totalTeachers = 0;
                int totalStudents = 0;
                int totalAdmins = 0;

                for (DataSnapshot snapshot : dataSnapshot.getChildren()){
                    totalUsers++;
                    String accountType = snapshot.child("accountType").getValue(String.class);

                    if ("Admin".equals(accountType)){
                        totalAdmins++;
                    }else if ("Teacher".equals(accountType)){
                        totalTeachers++;
                    }else if ("Student".equals(accountType)){
                        totalStudents++;
                    }
                }

                totalUsersTextView.setText("Total Users: "+ totalUsers);
                totalTeachersTextView.setText("Total Teachers: "+ totalTeachers);
                totalStudentsTextView.setText("Total Students: "+ totalStudents);
                totalAdminsTextView.setText("Total Admins: "+ totalAdmins);

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


}
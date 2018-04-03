package com.example.chrisantuseze.hadum;

import android.content.Context;
import android.content.Intent;
import android.graphics.Typeface;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.muddzdev.styleabletoastlibrary.StyleableToast;
import com.wang.avi.AVLoadingIndicatorView;

public class CreateAccount extends AppCompatActivity {
    private EditText mName, mEmail, mPassword, mRegNo, mDepartment, mLevel, mSchoolWebsite;
    private Button mCreateBtn;
    private UserInfo mUserInfo;
    private TextView Hadum, tvSignIn;
    private AVLoadingIndicatorView mAVL;

//    private DatabaseReference mUserDatabase, mDatabase;
//    private FirebaseAuth mAuth;
//    private FirebaseUser mCurrentUser;
//    private FirebaseAuth.AuthStateListener mAuthListener;
//
//    String uId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_account);

//        mAuth = FirebaseAuth.getInstance();
//        mUserDatabase = FirebaseDatabase.getInstance().getReference().child("Users");
//        mCurrentUser = FirebaseAuth.getInstance().getCurrentUser();


        mName = (EditText)findViewById(R.id.name);
        mEmail = (EditText)findViewById(R.id.email);
        mPassword = (EditText)findViewById(R.id.password);
        mRegNo = (EditText)findViewById(R.id.regno);
        mDepartment = (EditText)findViewById(R.id.department);
        mLevel = (EditText)findViewById(R.id.level);
        mSchoolWebsite = (EditText)findViewById(R.id.website);


        Hadum = (TextView)findViewById(R.id.hadum);
        tvSignIn = (TextView)findViewById(R.id.tvsignin);

        mCreateBtn = (Button)findViewById(R.id.create);

        mUserInfo = new UserInfo(this);

        mAVL = (AVLoadingIndicatorView)findViewById(R.id.avi);


        tvSignIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(), Login.class));
                finish();
            }
        });


        mCreateBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String name = mName.getText().toString();
                String email = mEmail.getText().toString();
                String password = mPassword.getText().toString();
                String regno = mRegNo.getText().toString();
                String department = mDepartment.getText().toString();
                String level = mLevel.getText().toString();
                String website = mSchoolWebsite.getText().toString().toLowerCase();

                mName.setFocusable(false);
                mEmail.setFocusable(false);
                mPassword.setFocusable(false);
                mRegNo.setFocusable(false);
                mDepartment.setFocusable(false);
                mLevel.setFocusable(false);
                mSchoolWebsite.setFocusable(false);

                if (!TextUtils.isEmpty(name) && !TextUtils.isEmpty(email) &&!TextUtils.isEmpty(password)
                        && !TextUtils.isEmpty(regno) && !TextUtils.isEmpty(department) &&
                        !TextUtils.isEmpty(level)){
                    if (checkInternetConnection()){
                        if (!website.contains("www")){
                            StyleableToast.makeText(getApplicationContext(), "Invalid School Website!", R.style.error).show();
                        }else{
                            mCreateBtn.setEnabled(false);
                            mAVL.setVisibility(View.VISIBLE);
                            mAVL.show();
                            mUserInfo.setKeyName(name);
                            mUserInfo.setKeyEmail(email);
                            mUserInfo.setKeyRegno(regno);
                            mUserInfo.setKeyDepartment(department);
                            mUserInfo.setKeyLevel(level);
                            if (!TextUtils.isEmpty(website)){
                                mUserInfo.setKeyWebsite(website);
                            }else{
                                mUserInfo.setKeyWebsite("www.google.com");
                            }
                            StyleableToast.makeText(getApplicationContext(),
                                    "Account created successfully", R.style.success).show();
                            //createAccount(name, regno, department, level, email, password);
                            Intent intent = new Intent(CreateAccount.this, Welcome.class);
                            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent
                                    .FLAG_ACTIVITY_CLEAR_TASK);
                            startActivity(intent);
                            finish();

                            mAVL.hide();
                        }

                    }else{
                        StyleableToast.makeText(getApplicationContext(), "No Internet Connection", R.style.error).show();
                    }

                }else{
                    StyleableToast.makeText(getApplicationContext(), "Field cannot be empty!", R.style.error).show();
                }
            }
        });

        Typeface custom_font_title = Typeface.createFromAsset(getAssets(),  "fonts/Sansation-Bold.ttf");
        Hadum.setTypeface(custom_font_title);
        tvSignIn.setTypeface(custom_font_title);

        Typeface custom_font_1 = Typeface.createFromAsset(getAssets(),  "fonts/Aller_Rg.ttf");
        mName.setTypeface(custom_font_1);
        mEmail.setTypeface(custom_font_1);
        mRegNo.setTypeface(custom_font_1);
        mDepartment.setTypeface(custom_font_1);
        mLevel.setTypeface(custom_font_1);
    }
//    private void createAccount(final String name, final String regno, final String dept,
//                               final String level, String email, String password) {
//
//        mAuth.createUserWithEmailAndPassword(email, password).addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
//            @Override
//            public void onComplete(@NonNull Task<AuthResult> task) {
//                String error = "";
//
//                try {
//                    if (task.isSuccessful()) {
//                        FirebaseUser currentUser = FirebaseAuth.getInstance().getCurrentUser();
//                        String uID = currentUser.getUid();
//                        uId = mCurrentUser.getUid();
//                        mDatabase = FirebaseDatabase.getInstance().getReference().child("Users").child(uId);
//
//                        mUserDatabase = FirebaseDatabase.getInstance().getReference().child("Users").child(uID);
//                        HashMap<String, String> userMap = new HashMap<>();
//                        userMap.put("name", name);
//                        userMap.put("regno", regno);
//                        userMap.put("department", dept);
//                        userMap.put("email", currentUser.getEmail());
//                        userMap.put("level", level);
//                        userMap.put("image", "default");
//                        userMap.put("thumb_image", "default");
//                        mUserDatabase.setValue(userMap).addOnCompleteListener(new OnCompleteListener<Void>() {
//                            @Override
//                            public void onComplete(@NonNull Task<Void> task) {
//                                if (task.isSuccessful()){
//                                    String user_id = mAuth.getCurrentUser().getUid();
//                                    String deviceToken = FirebaseInstanceId.getInstance().getToken();
//                                    mUserDatabase.child(user_id).child("device_token").setValue(deviceToken)
//                                            .addOnCompleteListener(new OnCompleteListener<Void>() {
//                                                @Override
//                                                public void onComplete(@NonNull Task<Void> task) {
//                                                    if (task.isSuccessful()){
//                                                        mUserDatabase.addValueEventListener(new ValueEventListener() {
//                                                            @Override
//                                                            public void onDataChange(DataSnapshot dataSnapshot) {
//                                                                StyleableToast.makeText(getApplicationContext(), dataSnapshot.child("name").getValue().toString(), R.style.success);
//                                                            }
//
//                                                            @Override
//                                                            public void onCancelled(DatabaseError databaseError) {
//
//                                                            }
//                                                        });
//
//                                                        StyleableToast.makeText(getApplicationContext(),
//                                                                "Registration Sucessful", R.style.success).show();
//                                                        Intent intent = new Intent(CreateAccount.this, Welcome.class);
//                                                        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent
//                                                                .FLAG_ACTIVITY_CLEAR_TASK);
//                                                        startActivity(intent);
//                                                        finish();
//                                                    }else{
//
//                                                    }
//                                                }
//                                            });
//
//                                }
//                            }
//                        });
//
//
//                    } else {
//                        StyleableToast.makeText(getApplicationContext(),
//                                "Registration failed!", R.style.error).show();
//                    }
//                    throw task.getException();
//                }catch (FirebaseAuthWeakPasswordException e){
//                    error = "Weak Password";
//                }catch (FirebaseAuthInvalidCredentialsException e){
//                    error = "Invalid Email";
//                }catch (FirebaseAuthUserCollisionException e){
//                    error = "Account already existing";
//                }catch (Exception e){
//                }
//                if (!error.equals("")){
//                    StyleableToast.makeText(getApplicationContext(),
//                            error, R.style.error).show();
//                }
//
//
//            }
//        });
//
//    }
    public boolean checkInternetConnection() {
        // get Connectivity Manager object to check connection
        ConnectivityManager connectMgr = (ConnectivityManager)
                getSystemService(Context.CONNECTIVITY_SERVICE);

        //Check for network connections
        if (connectMgr.getNetworkInfo(0).getState() ==
                NetworkInfo.State.CONNECTED ||
                connectMgr.getNetworkInfo(0).getState() ==
                        NetworkInfo.State.CONNECTING ||
                connectMgr.getNetworkInfo(1).getState() ==
                        NetworkInfo.State.CONNECTING ||
                connectMgr.getNetworkInfo(0).getState() ==
                        NetworkInfo.State.CONNECTED) {
            return true;
        } else if (
                connectMgr.getNetworkInfo(0).getState() ==
                        NetworkInfo.State.DISCONNECTED ||
                        connectMgr.getNetworkInfo(1).getState() ==
                                NetworkInfo.State.DISCONNECTED) {

            return false;
        }
        return false;
    }
    @Override
    protected void onStart() {
        super.onStart();
//        mAuth.addAuthStateListener(mAuthListener);
    }

    @Override
    protected void onStop() {
        super.onStop();
//        if (mAuthListener != null) {
//            mAuth.removeAuthStateListener(mAuthListener);
//        }
    }

}

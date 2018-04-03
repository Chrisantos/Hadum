package com.example.chrisantuseze.hadum;

import android.content.Context;
import android.content.Intent;
import android.graphics.Typeface;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.muddzdev.styleabletoastlibrary.StyleableToast;
import com.wang.avi.AVLoadingIndicatorView;

public class Login extends AppCompatActivity {
    private EditText etEmail, etPassword;
    private Button btnLogin;
    private TextView tvSignUp, tvName;
    private AVLoadingIndicatorView mAVL;
    //private FirebaseAuth mAuth;
    //private DatabaseReference mUserDatabase;
    String name, emaill, regno, dept, level;
    private UserInfo mUserInfo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
//        mAuth = FirebaseAuth.getInstance();
//        mUserDatabase = FirebaseDatabase.getInstance().getReference().child("Users");


        etEmail = (EditText)findViewById(R.id.email);
        etPassword = (EditText)findViewById(R.id.password);

        btnLogin = (Button)findViewById(R.id.signin);
        tvSignUp = (TextView)findViewById(R.id.tvsignup);

        tvName = (TextView)findViewById(R.id.hadum);
        Typeface custom_font1 = Typeface.createFromAsset(getAssets(),  "fonts/Sansation-Bold.ttf");
        tvName.setTypeface(custom_font1);
        btnLogin.setTypeface(custom_font1);
        tvSignUp.setTypeface(custom_font1);

        mAVL = (AVLoadingIndicatorView)findViewById(R.id.avi);

        Typeface custom_font2 = Typeface.createFromAsset(getAssets(),  "fonts/Aller_Rg.ttf");
        etEmail.setTypeface(custom_font2);
        etPassword.setTypeface(custom_font2);


        mUserInfo = new UserInfo(this);

        btnLogin.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.M)
            @Override
            public void onClick(View view) {

                String email = etEmail.getText().toString();
                String password = etPassword.getText().toString();

                if (!TextUtils.isEmpty(email) && !TextUtils.isEmpty(password)){
                    if (checkInternetConnection()){
                        // startAnim();
                        mAVL.setVisibility(View.VISIBLE);
                        mAVL.show();
                        etEmail.setFocusable(false);
                        etPassword.setFocusable(false);
                        btnLogin.setEnabled(false);
                        //loginUser(email, password);
                        mUserInfo.setKeyDepartment("***");
                        mUserInfo.setKeyLevel("***");
                        mUserInfo.setKeyRegno("***");
                        mUserInfo.setKeyName("***");
                        mUserInfo.setKeyEmail(email);
                        StyleableToast.makeText(getApplicationContext(), "You're now logged in..",
                                R.style.success).show();
                        Intent intent = new Intent(Login.this, Welcome.class);
                        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                        startActivity(intent);
                        finish();
                    }else{
                        StyleableToast.makeText(getApplicationContext(), "No internet connection",
                                R.style.error).show();
                    }

                }else{
                    StyleableToast.makeText(getApplicationContext(), "Please enter credentials",
                            R.style.error).show();
                }
                mAVL.setVisibility(View.INVISIBLE);
                mAVL.hide();
            }
        });

        tvSignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(Login.this, CreateAccount.class));
                finish();
            }
        });
    }

//    private void loginUser(String email, String password) {
//
//        mAuth.signInWithEmailAndPassword(email, password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
//            @Override
//            public void onComplete(@NonNull Task<AuthResult> task) {
//                if (task.isSuccessful()){
//                    mAVL.show();
//                    final String user_id = mAuth.getCurrentUser().getUid();
//                    String deviceToken = FirebaseInstanceId.getInstance().getToken();
//                    mUserDatabase.child(user_id).child("device_token").setValue(deviceToken)
//                            .addOnCompleteListener(new OnCompleteListener<Void>() {
//                                @Override
//                                public void onComplete(@NonNull Task<Void> task) {
//                            if (task.isSuccessful()){
//                                Intent intent = new Intent(Login.this, Welcome.class);
//                                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
//                                startActivity(intent);
//                                finish();
//                            }else{
//
//                            }
//                                }
//                            });
//
//
//                }else{
//                    StyleableToast.makeText(getApplicationContext(), "Signing in failed!",
//                            R.style.error).show();
//                }
//            }
//        });
//        UserInfo mUserInfo = new UserInfo(this);
//        mUserInfo.setKeyDepartment(dept);
//        mUserInfo.setKeyLevel(level);
//        mUserInfo.setKeyRegno(regno);
//        mUserInfo.setKeyName(name);
//        mUserInfo.setKeyEmail(emaill);
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

}

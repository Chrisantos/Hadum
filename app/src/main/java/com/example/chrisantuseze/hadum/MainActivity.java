package com.example.chrisantuseze.hadum;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.graphics.Typeface;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.RequiresApi;
import android.support.design.widget.NavigationView;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import com.example.chrisantuseze.hadum.Nav.Assistant;
import com.example.chrisantuseze.hadum.Nav.Feedback;
import com.example.chrisantuseze.hadum.Nav.Settings;
import com.example.chrisantuseze.hadum.Nav.Website;
import com.example.chrisantuseze.hadum.Quiz.QuizSplashScreen;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;
import com.muddzdev.styleabletoastlibrary.StyleableToast;
import com.squareup.picasso.Callback;
import com.squareup.picasso.NetworkPolicy;
import com.squareup.picasso.Picasso;
import com.theartofdev.edmodo.cropper.CropImage;
import com.wang.avi.AVLoadingIndicatorView;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.util.HashMap;
import java.util.Map;

import de.hdodenhof.circleimageview.CircleImageView;
import id.zelory.compressor.Compressor;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    private Toolbar toolbar;
    private NavigationView navigationView;
    private TextView mName, mEmail, mRegNo, mDepartment, mLevel;
    private TextView mTagRegNo, mTagDepartment, mTagLevel;
    private UserInfo mUserInfo;
    private SessionManager sessionManager;
    private CircleImageView mProfile;

    private AVLoadingIndicatorView mAVL;

    private DatabaseReference mDatabase;
    private FirebaseUser mCurrentUser;
    private StorageReference mImageStorage;
    private static final int GALLERY_PICK = 1;
    String uId;
    String name, email, regno, dept, level;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        setTitle("Assistant");
        Assistant notifications = new Assistant();
        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        fragmentTransaction.replace(R.id.fragment_container, notifications).commit();


        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        mCurrentUser = FirebaseAuth.getInstance().getCurrentUser();

        View header = navigationView.getHeaderView(0);
        mName = (TextView)header.findViewById(R.id.firstname);
        mEmail = (TextView)header.findViewById(R.id.lastname);
        mRegNo = (TextView)header.findViewById(R.id.regno);
        mDepartment = (TextView)header.findViewById(R.id.department);
        mLevel = (TextView)header.findViewById(R.id.level);
        mProfile = (CircleImageView)header.findViewById(R.id.circle_image);

        mTagRegNo = (TextView)header.findViewById(R.id.tag_regno);
        mTagDepartment = (TextView)header.findViewById(R.id.tag_dept);
        mTagLevel = (TextView)header.findViewById(R.id.tag_level);

        mAVL = (AVLoadingIndicatorView)header.findViewById(R.id.avi);

        mProfile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.setType("image/*");
                intent.setAction(Intent.ACTION_GET_CONTENT);
                startActivityForResult(Intent.createChooser(intent, "Profile Picture"), GALLERY_PICK);
            }
        });


        mUserInfo = new UserInfo(this);

        Typeface custom_font_1 = Typeface.createFromAsset(getAssets(),  "fonts/Aller_Rg.ttf");
        mName.setTypeface(custom_font_1);
        mEmail.setTypeface(custom_font_1);
        mRegNo.setTypeface(custom_font_1);
        mDepartment.setTypeface(custom_font_1);
        mLevel.setTypeface(custom_font_1);

        mTagRegNo.setTypeface(custom_font_1);
        mTagDepartment.setTypeface(custom_font_1);
        mTagLevel.setTypeface(custom_font_1);

        // session manager
        sessionManager = new SessionManager(getApplicationContext());

        if (!sessionManager.isLoggedIn()) {
            sessionManager.setLogin(true);
        }

        if (mCurrentUser != null){

            String uid = mCurrentUser.getUid();
            mDatabase = FirebaseDatabase.getInstance().getReference().child("Users").child(uid);
            mDatabase.keepSynced(true);
            mImageStorage = FirebaseStorage.getInstance().getReference();

            mDatabase.addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(DataSnapshot dataSnapshot) {
                    if (dataSnapshot != null){
                        name = dataSnapshot.child("name").getValue().toString();
                        regno = dataSnapshot.child("regno").getValue().toString();
                        email = dataSnapshot.child("email").getValue().toString();
                        final String image = dataSnapshot.child("image").getValue().toString();
                        dept = dataSnapshot.child("department").getValue().toString();
                        level = dataSnapshot.child("level").getValue().toString();
                        final String thumb_image = dataSnapshot.child("thumb_image").getValue().toString();

                        if (!image.equals("default")){

                            Picasso.with(MainActivity.this).load(thumb_image).networkPolicy(NetworkPolicy.OFFLINE)
                                    .placeholder(R.drawable.user_avatar1)
                                    .into(mProfile, new Callback() {
                                        @Override
                                        public void onSuccess() {

                                        }

                                        @Override
                                        public void onError() {

                                            Picasso.with(MainActivity.this).load(thumb_image)
                                                    .placeholder(R.drawable.user_avatar1)
                                                    .into(mProfile);

                                        }
                                    });
                        }
                    }
                }

                @Override
                public void onCancelled(DatabaseError databaseError) {

                }
            });
            if (mUserInfo.getKeyDepartment() != null){
                mName.setText(mUserInfo.getKeyName());
                mEmail.setText(mUserInfo.getKeyEmail());
                mRegNo.setText(mUserInfo.getKeyRegno());
                mDepartment.setText(mUserInfo.getKeyDepartment());
                mLevel.setText(mUserInfo.getKeyLevel());
            }else{
                mName.setText(name);
                mLevel.setText(level);
                mDepartment.setText(dept);
                mRegNo.setText(regno);
                mEmail.setText(email);
            }
        }



    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.assistant) {
            setTitle("Assistant");
            Assistant assistant = new Assistant();
            FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
            fragmentTransaction.replace(R.id.fragment_container, assistant).commit();

//        } else if (id == R.id.academia) {
//
//            setTitle("Academia");
//            Academia academia = new Academia();
//            FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
//            fragmentTransaction.replace(R.id.fragment_container, academia).commit();

        }else if (id == R.id.website){
            startActivity(new Intent(this, Website.class));
//        }
//        else if (id == R.id.place) {
//            startActivity(new Intent(MainActivity.this, Places.class));

        } else if (id == R.id.quiz) {
            startActivity(new Intent(this, QuizSplashScreen.class));

        } else if (id == R.id.settings) {
            setTitle("Settings");
            Settings settings = new Settings();
            FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
            fragmentTransaction.replace(R.id.fragment_container, settings).commit();

        } else if (id == R.id.feedback) {
            setTitle("Help and Feedback");
            Feedback feedback = new Feedback();
            FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
            fragmentTransaction.replace(R.id.fragment_container, feedback).commit();

        } else if (id == R.id.logout) {
            FirebaseAuth.getInstance().signOut();
            startActivity(new Intent(getApplicationContext(), Splash.class));
            finish();

        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == GALLERY_PICK && resultCode == RESULT_OK) {
            Uri imageUri = data.getData();
            CropImage.activity(imageUri).setAspectRatio(1, 1).start(MainActivity.this);
        }
        if (requestCode == CropImage.CROP_IMAGE_ACTIVITY_REQUEST_CODE) {
            CropImage.ActivityResult result = CropImage.getActivityResult(data);

            if (resultCode == RESULT_OK) {
                mAVL.setVisibility(View.VISIBLE);
                mAVL.show();

                StyleableToast.makeText(this, "Uploading image, please wait...", R.style.success).show();

                Uri resultUri = result.getUri();
                //Getting the file
                File thumb_filePath = new File(resultUri.getPath());
                String currentUserID = mCurrentUser.getUid();

                //Compressing the image
                Bitmap thumb_bitmap = new Compressor(this)
                        .setMaxWidth(400)
                        .setMaxHeight(350)
                        .setQuality(75)
                        .compressToBitmap(thumb_filePath);

                //To store byte array into firebase
                ByteArrayOutputStream baos = new ByteArrayOutputStream();
                thumb_bitmap.compress(Bitmap.CompressFormat.JPEG, 100, baos);
                final byte[] thumb_byte = baos.toByteArray();

                uId = mCurrentUser.getUid();
                mDatabase = FirebaseDatabase.getInstance().getReference().child("Users").child(uId);


                StorageReference filepath = mImageStorage.child("profile_image").child(currentUserID
                        + ".jpg");
                final StorageReference thumb_filepath = mImageStorage.child("profile_image")
                        .child("thumb").child(currentUserID + ".jpg");


                filepath.putFile(resultUri).addOnCompleteListener(new OnCompleteListener<UploadTask.TaskSnapshot>() {
                    @RequiresApi(api = Build.VERSION_CODES.M)
                    @SuppressWarnings("VisibleForTests")
                    @Override
                    public void onComplete(@NonNull Task<UploadTask.TaskSnapshot> task) {
                        if (task.isSuccessful()) {

                            //The task gets the values that are provided in firebase

                            //we get the download url and store it the the database
                            final String download_url = task.getResult().getDownloadUrl().toString();

                            UploadTask uploadTask = thumb_filepath.putBytes(thumb_byte);
                            uploadTask.addOnCompleteListener(new OnCompleteListener<UploadTask.TaskSnapshot>() {
                                @RequiresApi(api = Build.VERSION_CODES.M)
                                @Override
                                public void onComplete(@NonNull Task<UploadTask.TaskSnapshot> thumb_task) {

                                    String thumb_downloaadUrl = thumb_task.getResult().getDownloadUrl().toString();
                                    if (thumb_task.isSuccessful()) {

//                                        Map<String, Object> update_map = new HashMap();
                                        Map update_map = new HashMap();
                                        update_map.put("image", download_url);
                                        update_map.put("thumb_image", thumb_downloaadUrl);

                                        mDatabase.updateChildren(update_map)
                                                .addOnCompleteListener(new OnCompleteListener<Void>() {
                                                    @RequiresApi(api = Build.VERSION_CODES.M)
                                                    @Override
                                                    public void onComplete(@NonNull Task<Void> task) {

                                                        if (task.isSuccessful()) {

                                                            //dialog.dismiss();
                                                            new StyleableToast.Builder(getApplicationContext())
                                                                    .text("Uploaded successfully").textColor(getColor
                                                                    (R.color.colorPrimaryDark))
                                                                    .backgroundColor(getColor(R.color.milk)).show();
                                                            mAVL.hide();
                                                            mAVL.setVisibility(View.INVISIBLE);
                                                        }
                                                    }
                                                });
                                    } else {
                                        // dialog.dismiss();
                                        new StyleableToast.Builder(getApplicationContext())
                                                .text("Error occurred").textColor(Color.WHITE)
                                                .backgroundColor(getColor(R.color.red)).show();
                                    }
                                }
                            });

                            mDatabase.child("image").setValue(download_url).addOnCompleteListener(new OnCompleteListener<Void>() {
                                @Override
                                public void onComplete(@NonNull Task<Void> task) {
                                    if (task.isSuccessful()) {
                                        // dialog.dismiss();
                                    } else {

                                    }
                                }
                            });
                        } else {

                            new StyleableToast.Builder(getApplicationContext())
                                    .text("Signing in failed!").textColor(Color.WHITE)
                                    .backgroundColor(getColor(R.color.red)).show();
                            // dialog.dismiss();
                        }
                    }
                });
                mAVL.hide();
                mAVL.setVisibility(View.INVISIBLE);

            } else if (resultCode == CropImage.CROP_IMAGE_ACTIVITY_RESULT_ERROR_CODE) {
                Exception error = result.getError();
            }
        }

    }
    @Override
    public void onStart() {
        super.onStart();
        if (mCurrentUser == null){
            startActivity(new Intent(getApplicationContext(), Splash.class));
            finish();
        }
    }
}

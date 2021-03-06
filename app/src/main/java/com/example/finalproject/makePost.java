package com.example.finalproject;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

import com.airbnb.lottie.LottieAnimationView;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class makePost extends AppCompatActivity {
    EditText TitleView;
    EditText PostView;
    DatabaseReference databasePosts;
    boolean clicked = false;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_make_post);
        TitleView = findViewById(R.id.TitleView);
        PostView = findViewById(R.id.PostView);
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        databasePosts = database.getReference().child("Posts");

        final LottieAnimationView lottieClickFloat = findViewById(R.id.lottieClickSend);
        lottieClickFloat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (clicked = true){         String title = TitleView.getText().toString();
                    TitleView.setText("");
                    String post = PostView.getText().toString();
                    PostView.setText("");
                    Intent intent = new Intent( makePost.this, Forum.class);
                    startActivity(intent);

                    forumPosts postMessage  = new forumPosts(title,post);
                    databasePosts.push().setValue(postMessage);
                    closeKeyboard();
                    clicked = false;
                }
            }
        });

    }
    public void closeKeyboard(){
        View view = this.getCurrentFocus();
        if (view != null){
            InputMethodManager imm = (InputMethodManager)getSystemService(Context.INPUT_METHOD_SERVICE);
            imm.hideSoftInputFromWindow(view.getWindowToken(),0);
        }
    }

    /*public void sendMessage(View view) {
        //get the string from the edittext field
        //push the message as a child to the messages object in the db
         String title = TitleView.getText().toString();
         TitleView.setText("");
         String post = PostView.getText().toString();
         PostView.setText("");
        Intent intent = new Intent( this, Forum.class);
        startActivity(intent);



         //TODO: push the message as a child to the messages object in the db
        forumPosts postMessage  = new forumPosts(title,post);
         databasePosts.push().setValue(postMessage);

    }*/
}

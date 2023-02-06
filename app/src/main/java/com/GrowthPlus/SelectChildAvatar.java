package com.GrowthPlus;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import java.io.ByteArrayOutputStream;

public class SelectChildAvatar extends AppCompatActivity implements View.OnClickListener{
    private ImageView bunny, elephant, bird, camel, giraffe, squirrel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_select_child_avatar);
        init();

        ImageView topLeft = (ImageView) findViewById(R.id.bunnyOption);
        topLeft.setOnClickListener(this);

        ImageView topRight = (ImageView) findViewById(R.id.elephantOption);
        topRight.setOnClickListener(this);

        ImageView middleLeft = (ImageView) findViewById(R.id.birdOption);
        middleLeft.setOnClickListener(this);

        ImageView middleRight = (ImageView) findViewById(R.id.camelOption);
        middleRight.setOnClickListener(this);

        ImageView bottomLeft = (ImageView) findViewById(R.id.giraffeOption);
        bottomLeft.setOnClickListener(this);

        ImageView bottomRight = (ImageView) findViewById(R.id.squirrelOption);
        bottomRight.setOnClickListener(this);
    }

    public void init(){
        bunny = findViewById(R.id.bunnyOption);
        elephant = findViewById(R.id.elephantOption);
        bird = findViewById(R.id.birdOption);
        camel = findViewById(R.id.camelOption);
        giraffe = findViewById(R.id.giraffeOption);
        squirrel = findViewById(R.id.squirrelOption);
    }

    @Override
    public void onClick(View v) {
        ByteArrayOutputStream bs = new ByteArrayOutputStream();
        Bitmap bitmap;
        byte[] b;

        switch(v.getId()){
            case R.id.bunnyOption:
                startNextScreen("blue", "bunny");

                break;

            case R.id.elephantOption:
                startNextScreen("orange", "elephant");
                break;

            case R.id.birdOption:
                startNextScreen("light_green", "guinea_fowl");
                break;
            case R.id.camelOption:
                startNextScreen("red", "camel");
                break;
            case R.id.giraffeOption:
                startNextScreen("blue", "giraffe");
                break;
            case R.id.squirrelOption:
                startNextScreen("yellow", "squirrel");
                break;
            default:
        }
    }

    public void startNextScreen(String colorName, String animalName){
        Intent myIntent = new Intent(SelectChildAvatar.this, CreateAccount.class);
        myIntent.putExtra("selectColor", colorName);
        myIntent.putExtra("selectAnimal", animalName);
        startActivity(myIntent);
    }
}
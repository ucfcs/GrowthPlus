package com.GrowthPlus;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.animation.AlphaAnimation;
import android.widget.Button;
import android.widget.ImageView;

import java.io.ByteArrayOutputStream;

public class SelectChildAvatar extends AppCompatActivity implements View.OnClickListener{
    private ImageView bunny, elephant, bird, camel, giraffe, squirrel;
    private Button backSelect;
    private AlphaAnimation buttonClick = new AlphaAnimation(1F, 0.8F);

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

        backSelect.setOnClickListener(this);
    }

    public void init(){
        bunny = findViewById(R.id.bunnyOption);
        elephant = findViewById(R.id.elephantOption);
        bird = findViewById(R.id.birdOption);
        camel = findViewById(R.id.camelOption);
        giraffe = findViewById(R.id.giraffeOption);
        squirrel = findViewById(R.id.squirrelOption);
        backSelect = findViewById(R.id.backSelectChild);
    }

    @Override
    public void onClick(View v) {
        v.startAnimation(buttonClick);

        if(v.getId() == R.id.backSelectChild){
            startActivity(new Intent(SelectChildAvatar.this, MainActivity.class));
        }

        ByteArrayOutputStream bs = new ByteArrayOutputStream();
        Bitmap bitmap;
        byte[] b;

        switch(v.getId()){
            case R.id.bunnyOption:
                startNextScreen("yellow", "bunny");
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
                startNextScreen("yellow", "giraffe");
                break;
            case R.id.squirrelOption:
                startNextScreen("orange", "squirrel");
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
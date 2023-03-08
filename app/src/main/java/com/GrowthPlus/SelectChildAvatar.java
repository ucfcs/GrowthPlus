package com.GrowthPlus;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.animation.AlphaAnimation;
import android.widget.Button;
import android.widget.ImageView;

public class SelectChildAvatar extends AppCompatActivity implements View.OnClickListener{
    private ImageView bunny, elephant, bird, camel, giraffe, squirrel;
    private Button backSelect;
    private String goBackTo;
    private final AlphaAnimation buttonClick = new AlphaAnimation(1F, 0.8F);
    private Intent goBack;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_select_child_avatar);
        init();

        bunny.setOnClickListener(this);

        elephant.setOnClickListener(this);

        bird.setOnClickListener(this);

        camel.setOnClickListener(this);

        giraffe.setOnClickListener(this);

        squirrel.setOnClickListener(this);

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
        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            goBackTo = extras.getString("comingFrom");
        }
        if(goBackTo.equals("parentPortal")){
            goBack = new Intent(SelectChildAvatar.this, ParentPortal.class);
        }else{
            goBack = new Intent(SelectChildAvatar.this, MainActivity.class);
        }
    }

    @Override
    public void onClick(View v) {
        v.startAnimation(buttonClick);
        int viewId = v.getId();

        if(viewId == backSelect.getId()){
            backToScreen();
            this.finish();
        }

        if(viewId == bunny.getId()){
            startNextScreen("yellow", "bunny");
        }

        if(viewId == elephant.getId()){
            startNextScreen("orange", "elephant");
        }

        if(viewId == bird.getId()){
            startNextScreen("light_green", "guinea_fowl");
        }

        if(viewId == camel.getId()){
            startNextScreen("red", "camel");
        }

        if(viewId == giraffe.getId()){
            startNextScreen("yellow", "giraffe");
        }

        if(viewId == squirrel.getId()){
            startNextScreen("orange", "squirrel");
        }
    }

    public void startNextScreen(String colorName, String animalName){
        Intent myIntent = new Intent(SelectChildAvatar.this, CreateAccount.class);
        myIntent.putExtra("selectColor", colorName);
        myIntent.putExtra("selectAnimal", animalName);
        myIntent.putExtra("comingFrom", goBackTo);
        startActivity(myIntent);
        this.finish();
    }

    private void backToScreen(){
        startActivity(goBack);
        this.finish();
    }
}
package com.GrowthPlus;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import android.content.DialogInterface;
import android.content.Intent;
import android.content.res.ColorStateList;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.GrowthPlus.customViews.ChildAvatarComponent;
import com.GrowthPlus.dataAccessLayer.child.ChildSchemaService;
import com.GrowthPlus.utilities.ColorIdentifier;
import com.GrowthPlus.utilities.ImageSrcIdentifier;

import org.bson.types.ObjectId;

import io.realm.Realm;

public class CreateAccount extends AppCompatActivity {
    Button backButton, loginButton;
    EditText nameInput;
    ColorIdentifier colorIdentifier;
    ImageSrcIdentifier imageSrcIdentifier;
    String colorName, animalName;
    ChildAvatarComponent childAvatar;
    ColorStateList color;
    ChildSchemaService newChild;
    Realm realm;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_account);
        init();

        // Go to main page with update new child
        View.OnClickListener goNext = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                newChild = new ChildSchemaService(realm, nameInput.getText().toString(), null, animalName, colorName, 0);
                ObjectId childId = new ObjectId();
                newChild.createChildSchema(String.valueOf(childId)); // Create new child in realm database
                startActivity(new Intent(CreateAccount.this, MainActivity.class));
            }
        };
        loginButton.setOnClickListener(goNext);

        // Go back to select child avatar
        View.OnClickListener goBack = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(CreateAccount.this, SelectChildAvatar.class));
                finish();
            }
        };
        backButton.setOnClickListener(goBack);
    }

    public void init(){
        realm = Realm.getDefaultInstance();
        backButton = findViewById(R.id.backCreateAccount);
        loginButton = findViewById(R.id.loginBtn);
        nameInput = findViewById(R.id.nameInput);
        colorIdentifier = new ColorIdentifier();
        imageSrcIdentifier = new ImageSrcIdentifier();
        childAvatar = findViewById(R.id.childAvatar);

        Bundle extras = getIntent().getExtras();
        colorName = extras.getString("selectColor"); // Get color
        animalName = extras.getString("selectAnimal"); // Get animal
        color = ContextCompat.getColorStateList(this, colorIdentifier.getColorIdentifier(colorName));

        backButton.setBackgroundTintList(color); // Set color
        loginButton.setBackgroundTintList(color);

        setChildAvatar();
    }

    // Change custom view animal and color
    public void setChildAvatar(){
        childAvatar.setImageResource(imageSrcIdentifier.getImageSrcId(animalName));
        childAvatar.setBackgroundTintList(color);
    }
}
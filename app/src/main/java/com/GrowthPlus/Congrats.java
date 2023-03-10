package com.GrowthPlus;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.animation.AlphaAnimation;
import android.widget.Button;
import androidx.appcompat.app.AppCompatActivity;
import com.GrowthPlus.customViews.TopBar;
import com.GrowthPlus.dataAccessLayer.child.ChildSchema;
import io.realm.Realm;

public class Congrats extends AppCompatActivity implements View.OnClickListener{
    private TopBar topBar;
    private Button next, back;
    private ChildSchema child;
    private String childId;
    private Realm realm;
    private final AlphaAnimation buttonClick = new AlphaAnimation(1F, 0.8F);


    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_congrats);
        init();
        setTopBar();
        back.setVisibility(View.INVISIBLE);
        next.setOnClickListener(this);
    }

    private void init(){
        Bundle extras = getIntent().getExtras();
        if(extras != null){
            childId = extras.getString("childIdentify");
        }
        realm = Realm.getDefaultInstance();
        child = realm.where(ChildSchema.class).equalTo("childId", childId).findFirst();
        topBar = findViewById(R.id.topBar);
        next = findViewById(R.id.nextButton);
        back = topBar.findViewById(R.id.goBackBtn);

    }

    @Override
    public void onClick(View view) {
        view.startAnimation(buttonClick);
        int id = view.getId();

        if(id == R.id.nextButton){
            Intent mainActivity = new Intent(Congrats.this, MainActivity.class);
            startActivity(mainActivity);
            this.finish();
        }
        //(there are no other buttons that can be clicked on this page)

    }

    private void setTopBar(){
        topBar.setPoints(String.valueOf(child.getScore()));
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        // Close the Realm instance.
        realm.close();
    }

}

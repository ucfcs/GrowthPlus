package com.GrowthPlus;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;

import com.GrowthPlus.customViews.TopBar;
import com.GrowthPlus.dataAccessLayer.ChildRoadMap.ChildRoadMap;
import com.GrowthPlus.dataAccessLayer.Lesson.LessonSchema;
import com.GrowthPlus.dataAccessLayer.child.ChildSchema;
import com.GrowthPlus.dataAccessLayer.child.ChildSchemaService;
import com.GrowthPlus.fragment.WordImage;
import com.GrowthPlus.roadMapActivity.RoadMapOne;
import com.GrowthPlus.utilities.ImageSrcIdentifier;

import io.realm.Realm;

public class location_intro extends AppCompatActivity {
    private String childId;
    private Realm realm;
    private ChildSchema child;
    private ChildRoadMap childRoadMap;
    private TopBar topBar;
    private String dataBaseLessonId;
    private LessonSchema lesson;
    private String lessonName;
    private String image;
    private ImageSrcIdentifier imageSrcIdentifier;
    private Button nextButton;

    private Button introBackBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_location_intro);
        init();
        setTopBar();

        Log.i("Lesson Name", String.valueOf(lessonName));
        Log.i("Lesson image", String.valueOf(image));

        nextButton.setOnClickListener( view -> {
            Intent lessonIntent = new Intent(this, lesson.class);
            lessonIntent.putExtra("dataBaseLessonId", dataBaseLessonId);
            lessonIntent.putExtra("childId", childId);
            startActivity(lessonIntent);
            }
        );

        if (savedInstanceState == null) {
            Bundle bundle = new Bundle();
            bundle.putString("locationIntroText", lessonName);
            bundle.putString("locationIntroImage", image);

            getSupportFragmentManager().beginTransaction()
                    .setReorderingAllowed(true)
                    .add(R.id.frame_layout, WordImage.class, bundle)
                    .commit();
        }

        introBackBtn.setOnClickListener(view -> {
            Intent lessonIntent = new Intent(location_intro.this, RoadMapOne.class);
            // TODO: Dynamically change return address
            lessonIntent.putExtra("childIdentify", childId);
            startActivity(lessonIntent);
        });
    }

    private void init(){
        Bundle extras = getIntent().getExtras();
        if(extras != null){
            childId = extras.getString("childId");
            dataBaseLessonId = extras.getString("dataBaseLessonId");
        }
         realm = Realm.getDefaultInstance();
         child = realm.where(ChildSchema.class).equalTo("childId", childId).findFirst();
         childRoadMap = child.getRoadMapOne();
         topBar = findViewById(R.id.topBar);
         introBackBtn = topBar.findViewById(R.id.goBackBtn);
         lesson = realm.where(LessonSchema.class).equalTo("lessonId", dataBaseLessonId).findFirst();

         lessonName = lesson.getLessonName();
         image = lesson.getImage();

         imageSrcIdentifier = new ImageSrcIdentifier();
         nextButton = findViewById(R.id.next_button);
    }

    private void setTopBar(){
        topBar.setPoints(String.valueOf(child.getScore()));
    }
}
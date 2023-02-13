package com.GrowthPlus;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;

import com.GrowthPlus.customViews.TopBar;
import com.GrowthPlus.dataAccessLayer.ChildRoadMap.ChildRoadMap;
import com.GrowthPlus.dataAccessLayer.Lesson.LessonSchema;
import com.GrowthPlus.dataAccessLayer.child.ChildSchema;
import com.GrowthPlus.dataAccessLayer.child.ChildSchemaService;
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
    private ImageView introImg;
    private TextView introText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_location_intro);
        init();
        setTopBar();

        Log.i("Lesson Name", String.valueOf(lessonName));
        Log.i("Lesson image", String.valueOf(image));

        introText.setText(lessonName);
        introImg.setImageResource(imageSrcIdentifier.getImageSrcId(image));

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
         lesson = realm.where(LessonSchema.class).equalTo("lessonId", dataBaseLessonId).findFirst();

         lessonName = lesson.getLessonName();
         image = lesson.getImage();

         imageSrcIdentifier = new ImageSrcIdentifier();

         introText = findViewById(R.id.introText);
         introImg = findViewById(R.id.introImg);

    }

    private void setTopBar(){
        topBar.setPoints(String.valueOf(child.getScore()));
    }
}
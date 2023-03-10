package com.GrowthPlus;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.ColorStateList;
import android.content.res.Resources;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.view.animation.AlphaAnimation;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import com.GrowthPlus.dataAccessLayer.child.ChildSchema;
import com.GrowthPlus.dataAccessLayer.Language.LanguageSchema;
import com.GrowthPlus.dataAccessLayer.Language.LanguageSchemaService;
import com.GrowthPlus.dataAccessLayer.parent.ParentSchema;
import com.GrowthPlus.dataAccessLayer.parent.ParentSchemaService;
import io.realm.Realm;
import io.realm.RealmChangeListener;
import io.realm.RealmList;
import org.bson.types.ObjectId;

import java.util.Objects;

public class ParentSignup extends AppCompatActivity implements View.OnClickListener{

    Realm realm;
    Resources resources;
    private Button signupButton;
    private Button signupBackButton;
    private EditText enterPinInput;
    private EditText confirmPinInput;
    private EditText phoneNumberInput;
    private TextView createText;
    private TextView confirmText;
    private TextView phoneNumberText;
    Integer enterPinInputInteger;
    Integer confirmPinInputInteger;
    String phoneNumString;
    private RealmChangeListener<Realm> realmListener;
    private ObjectId parentId;
    private String parentIdString;
    private ParentSchemaService signupParentService;
    private final AlphaAnimation buttonClick = new AlphaAnimation(1F, 0.8F);
    ConstraintLayout layout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_parent_signup);
        init();

        signupButton.setOnClickListener(this);
        signupBackButton.setOnClickListener(this);

    }

    private void init(){
        realm = Realm.getDefaultInstance();
        resources = getResources();
        signupButton = findViewById(R.id.parentSignupBtn);
        signupBackButton = findViewById(R.id.backParentSU);

        enterPinInput = findViewById(R.id.enterPin);
        enterPinInput.setOnFocusChangeListener(this::hideKeyboard);

        confirmPinInput = findViewById(R.id.confirmPinInput);
        confirmPinInput.setOnFocusChangeListener(this::hideKeyboard);

        phoneNumberInput = findViewById(R.id.phoneNumberInput);
        phoneNumberInput.setOnFocusChangeListener(this::hideKeyboard);

        createText = findViewById(R.id.createPinText);
        confirmText = findViewById(R.id.confirmPinText);
        phoneNumberText = findViewById(R.id.phoneNumberText);
        parentId = new ObjectId();
        parentIdString = parentId.toString();
        realmListener = realm -> startLoginActivity();
    }

    @Override
    protected void onResume() {
        super.onResume();

        // create instance of shared preferences
        SharedPreferences langPrefs = getSharedPreferences("LangPreferences", MODE_PRIVATE);
        // create language schema service and set strings
        LanguageSchemaService langSchemaService = new LanguageSchemaService(realm, langPrefs.getString("languageId", "frenchZero"));
        LanguageSchema lang = langSchemaService.getLanguageSchemaById();

        createText.setText(lang.getCreate());
        confirmText.setText(lang.getConfirm());
        phoneNumberText.setText(lang.getPhoneNumber());
        enterPinInput.setHint(lang.getPin());
        confirmPinInput.setHint(lang.getPin());
        phoneNumberInput.setHint(lang.getPhoneNumber());
    }

    @Override
    public void onClick(View view) {

        view.startAnimation(buttonClick);
        int id = view.getId();

        //if they click the signup button
        if(id == R.id.parentSignupBtn){

            //check for null input or input that is too short
            boolean inputPINsValid = validInput(enterPinInput, confirmPinInput);
            boolean phoneNumValid = validPhoneNum(phoneNumberInput);

            //CHECK PHONE NUMBER IS VALID
            if(phoneNumValid){
                //change the input box back to grey (in case it has been earlier changed to red)
                phoneNumberInput.setBackgroundTintList(ColorStateList.valueOf(Color.rgb(204, 204, 204)));
            }
            else { //input was not valid -> change input box to red to indicate the phone number is wrong
                phoneNumberInput.setBackgroundTintList(ColorStateList.valueOf(Color.rgb(221, 97, 87)));
                phoneNumberInput.setText("");
            }

            //CHECK PINS ARE VALID AND MATCHING
            if(inputPINsValid){
                //change the input boxes back to grey (in case they have been earlier changed to red)
                enterPinInput.setBackgroundTintList(ColorStateList.valueOf(Color.rgb(204, 204, 204)));
                confirmPinInput.setBackgroundTintList(ColorStateList.valueOf(Color.rgb(204, 204, 204)));

                //track what the user entered
                Integer enterPinInputIntegerTemp = Integer.parseInt(enterPinInput.getText().toString());
                Integer confirmPinInputIntegerTemp = Integer.parseInt(confirmPinInput.getText().toString());

                //if the PIN's match and the phone number is valid start the parent portal activity
                if(confirmPinMatch(enterPinInputIntegerTemp, confirmPinInputIntegerTemp)){

                    if(phoneNumValid) {//make sure the phoneNumber is valid before moving on

                        //track the phone number that was entered
                        phoneNumString = phoneNumberInput.getText().toString();
                        enterPinInputInteger = enterPinInputIntegerTemp;
                        confirmPinInputInteger = confirmPinInputIntegerTemp;

                        //create a parent with all the necessary fields
                        createParent();

                    }
                }

                else{//PIN's don't match -> change input boxes to red to indicate the PIN's are wrong
                    enterPinInput.setBackgroundTintList(ColorStateList.valueOf(Color.rgb(221, 97, 87)));
                    confirmPinInput.setBackgroundTintList(ColorStateList.valueOf(Color.rgb(221, 97, 87)));
                    enterPinInput.setText("");
                    confirmPinInput.setText("");
                }
            }

            else { //input was not valid -> change input boxes to red to indicate the PIN's are wrong
                enterPinInput.setBackgroundTintList(ColorStateList.valueOf(Color.rgb(221, 97, 87)));
                confirmPinInput.setBackgroundTintList(ColorStateList.valueOf(Color.rgb(221, 97, 87)));
                enterPinInput.setText("");
                confirmPinInput.setText("");
            }

        }//end if id == sign up button

        //if they click the back button, go to the main landing page
        if(id == R.id.backParentSU){
            startMainActivity();
        }

        enterPinInput.setText("");
        confirmPinInput.setText("");
        phoneNumberInput.setText("");//clears the EditText(s)

    }

    private boolean validInput(EditText pin1, EditText pin2) {
        String pin1String = pin1.getText().toString();
        String pin2String = pin2.getText().toString();

        return pin1String.length() == 4 && pin2String.length() == 4;
    }

    private boolean validPhoneNum(EditText phoneNum){
        String phoneNumStr = phoneNum.getText().toString();
        return phoneNumStr.length() >= 8;
    }

    //simple method to see if two pins match
    private boolean confirmPinMatch(Integer pin1, Integer pin2){
        return pin1.equals(pin2);
    }

    //moves to the MainActivity page
    public void startMainActivity(){
        Intent mainActivity = new Intent(ParentSignup.this, MainActivity.class);
        startActivity(mainActivity);
        this.finish();
    }

    //moves to the login page, passing over the parentId
    public void startLoginActivity(){
        Intent parentLogin = new Intent(ParentSignup.this, ParentLogin.class);
        startActivity(parentLogin);
        this.finish();
    }

    //create a parent with all the necessary fields including the id, pin, phone number, etc.
    private void createParent(){
        realm.addChangeListener(realmListener);
        RealmList <ChildSchema> children = new RealmList<>();
        signupParentService = new ParentSchemaService(realm, parentIdString, confirmPinInputInteger, phoneNumString, children);
        signupParentService.createParentSchema();
    }

    private void hideKeyboard(View view, boolean hasFocus) {
        if (!hasFocus) {
            InputMethodManager inputMethodManager =(InputMethodManager)getSystemService(INPUT_METHOD_SERVICE);
            inputMethodManager.hideSoftInputFromWindow(view.getWindowToken(), 0);
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        // Close the Realm instance.
        realm.removeChangeListener(realmListener);
        realm.removeAllChangeListeners();
        realm.close();
    }
}
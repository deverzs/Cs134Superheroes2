package edu.miracosta.cs134.cs134superheroes;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.Menu;
import android.view.MenuItem;

import edu.miracostacollege.cs134.cs134superheroes.R;

public class MainActivity extends AppCompatActivity {


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_settings, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        Intent settingsIntent = new Intent(this, SettingsActivity.class);
        startActivity(settingsIntent);
        return super.onOptionsItemSelected(item);

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        PreferenceManager.setDefaultValues(this, R.xml.preferences, false);
        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(this) ;
        sharedPreferences.registerOnSharedPreferenceChangeListener(mSharedPreferenceChangeListener);
        setContentView(R.layout.activity_quiz);
    }



    public void updateBasedOnQuizType()
    {

    }


    public static final String PREF_QUIZ_TYPE = "pref_quizType";

    SharedPreferences.OnSharedPreferenceChangeListener mSharedPreferenceChangeListener =
            new SharedPreferences.OnSharedPreferenceChangeListener() {

        @Override
        public void onSharedPreferenceChanged(SharedPreferences sharedPreferences, String key) {
            switch (key)
            { case PREF_QUIZ_TYPE: // constant that stores "pref_quizType" to match preferences.xml
                mQuizType = sharedPreferences.getString(PREF_QUIZ_TYPE, mQuizType);
                updateBasedOnQuizType();
                break;
            }
        }
    };




}

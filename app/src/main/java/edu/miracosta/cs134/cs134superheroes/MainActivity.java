package edu.miracosta.cs134.cs134superheroes;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.AssetManager;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.os.Handler;
import android.preference.PreferenceManager;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import java.io.IOException;
import java.io.InputStream;
import java.security.SecureRandom;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

import edu.miracosta.cs134.cs134superheroes.Model.JSONLoader;
import edu.miracosta.cs134.cs134superheroes.Model.Superheroes;
import edu.miracostacollege.cs134.cs134superheroes.R;

public class MainActivity extends AppCompatActivity {


    public static final String TAG = "SUPER";
    public static final int IN_QUIZ = 10;
    public static final String QUIZ_DEFAULT = "Superhero Name" ;
    String mQuizType;
    Button mButtons[] = new Button[4];
    List<Superheroes> mAllHeroesList;
    List<Superheroes> mQuizHeroesList;
    Superheroes mCorrectHero;
    int mTotalGuesses;
    int mCorrectGuesses;
    SecureRandom rng;
    Handler handler;
    TextView mQuestionNumberTextView;
    ImageView mSuperheroImageView;
    TextView mAnswerTextView;


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
        setContentView(R.layout.activity_quiz);

        mQuizHeroesList = new ArrayList<>(IN_QUIZ);
        rng = new SecureRandom();
        handler = new Handler();
        mQuizType = QUIZ_DEFAULT;


        mQuestionNumberTextView = findViewById(R.id.quesionNumberTextView);
        mSuperheroImageView = findViewById(R.id.studentImageView);
        mAnswerTextView = findViewById(R.id.answerTextView);
        mButtons[0] = findViewById(R.id.button1);
        mButtons[1] = findViewById(R.id.button2);
        mButtons[2] = findViewById(R.id.button3);
        mButtons[3] = findViewById(R.id.button4);
        mQuestionNumberTextView.setText(getString(R.string.question, 1, IN_QUIZ));

        try {
            mAllHeroesList = JSONLoader.loadJSONFromAsset(this);
           // PreferenceManager.getDefaultSharedPreferences(this)
            Log.e(TAG, "Size of mAllHeoresList: " + mAllHeroesList.size());
                //    .registerOnSharedPreferenceChangeListener(mSharedPreferenceChangeListener);

        } catch (IOException e) {
            Log.e(TAG, "Error loading from JSON", e); //right way, tag is name of application
            e.printStackTrace();
        }

        PreferenceManager.setDefaultValues(this, R.xml.preferences, false);
        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(this);
        sharedPreferences.registerOnSharedPreferenceChangeListener(mSharedPreferenceChangeListener);
        resetQuiz();
    }

    public void resetQuiz()
    {
            mCorrectGuesses = 0;
            mTotalGuesses = 0;
            mQuizHeroesList.clear();

            Superheroes  randomHero;

            while(mQuizHeroesList.size() < IN_QUIZ)
            {
                Log.e(TAG, "Error in resetQuiz()");
              //random between 0 and 194
                randomHero = mAllHeroesList.get(rng.nextInt(mAllHeroesList.size()));

                //check for dupes and if random region matches object
                if(!mQuizHeroesList.contains(randomHero))
                {
                    mQuizHeroesList.add(randomHero);
                }
            }
            loadNextHero();

    }

    public void loadNextHero()
    {
        // DONE: Initialize the mCorrectCountry by removing the item at position 0 in the mQuizCountries
        //we have 10 random, first quest is first country
        //now list is size 9, remove takes it off
        mCorrectHero = mQuizHeroesList.remove(0);

        // DONE: Clear the mAnswerTextView so that it doesn't show text from the previous question
        //assume still playing
        mAnswerTextView.setText("");


        // DONE: Display current question number in the mQuestionNumberTextView
        //code wrote before up there
        mQuestionNumberTextView.setText(getString(R.string.question, IN_QUIZ - mQuizHeroesList.size(),IN_QUIZ));

        // DONE: Use AssetManager to load next image from assets folder
        AssetManager am = getAssets(); //load image from asset and put in image view

        // DONE: Get an InputStream to the asset representing the next flag
        try {
            InputStream stream = am.open(mCorrectHero.get_mFileName());
            Drawable image = Drawable.createFromStream(stream, mCorrectHero.get_mName() );  //name of the image for blind
            mSuperheroImageView.setImageDrawable(image);
        } catch (IOException e) {
            Log.e(TAG, "ERROR from loadNextFlag" + mCorrectHero.get_mFileName(), e);
        }
        String temp;

        // DONE: and try to use the InputStream to create a Drawable
        // DONE: The file name can be retrieved from the correct country's file name.
        // DONE: Set the image drawable to the correct flag.

        // DONE: Shuffle the order of all the countries (use Collections.shuffle)

        //method called shuffle, like arrays.sort()
        Collections.shuffle(mAllHeroesList);
        //fill all with random and then replace 1 with correct
        // DONE: Loop through all 4 buttons, enable them all and set them to the first 4 countries
        // DONE: in the all countries list
        /*
        for (int i = 0; i < mButtons.length ; i++) {
            mButtons[i].setEnabled(true); //allows clicking - need to make sure enables
            temp = mAllHeroesList.get(i).get_mName() ;
            if(!temp.equals(mCorrectHero.get_mName()))
            { mButtons[i].setText(mAllHeroesList.get(i).get_mName());}
            else
                {
                    Collections.shuffle(mAllHeroesList);
                    i--;
                }
        }

        // DONE: After the loop, randomly replace one of the 4 buttons with the name of the correct country
        mButtons[rng.nextInt(mButtons.length)].setText(mCorrectHero.get_mName());

         */
        updateBasedOnQuizType();
    }
    public void updateBasedOnQuizType()
    {
        String temp;

        for (int i = 0; i < mButtons.length ; i++) {
            mButtons[i].setEnabled(true); //allows clicking - need to make sure enables
            temp = mAllHeroesList.get(i).get_mName() ;
            if(!temp.equals(mCorrectHero.get_mName()))
            {
                if(mQuizType.equals("Superhero Name")) {
                    mButtons[i].setText(mAllHeroesList.get(i).get_mName());
                }
                else if(mQuizType.equals("One Thing")){
                    mButtons[i].setText(mAllHeroesList.get(i).get_mOneThing());
                }
                else  if(mQuizType.equals("Superpower")) {
                    mButtons[i].setText(mAllHeroesList.get(i).get_mSuperpower());
                }
            }
            else
            {
                Collections.shuffle(mAllHeroesList);
                i--;
            }
        }

        // DONE: After the loop, randomly replace one of the 4 buttons with the name of the correct country
        if(mQuizType.equals("Superhero Name")) {
            mButtons[rng.nextInt(mButtons.length)].setText(mCorrectHero.get_mName());
        }
        else if(mQuizType.equals("One Thing")){
            mButtons[rng.nextInt(mButtons.length)].setText(mCorrectHero.get_mOneThing());
        }
        else  if(mQuizType.equals("Superpower")) {
            mButtons[rng.nextInt(mButtons.length)].setText(mCorrectHero.get_mSuperpower());
        }
    }


    public void makeGuess(View v)
    {
        Button clickedButton = (Button) v;
        String guess = clickedButton.getText().toString();
        mTotalGuesses++;
        if(guess.equals(mCorrectHero.get_mName()) || guess.equals(mCorrectHero.get_mOneThing()) || guess.equals(mCorrectHero.get_mSuperpower()))
        {
            mCorrectGuesses++;
            for (int i = 0; i < mButtons.length ; i++) {
                mButtons[i].setEnabled(false);
            }

            mAnswerTextView.setTextColor(getResources().getColor(R.color.correct_answer));

            mAnswerTextView.setText(guess.toUpperCase());

            if(mCorrectGuesses < IN_QUIZ)
            {
                //code a delay 2000 ms = 2 sec using handler to load next flag
                //if more flags t guess, and they get it right, wait and load
                handler.postDelayed(new Runnable() {
                    @Override
                    public void run() {

                        loadNextHero();
                    }
                }, 2000) ; //build object on fly and need the time delay in the parameter
            }
            else //if wrong
            {
                //disable the clicked button


                //show incorrect in red
                //alert dialog
                //after all 10 flags tells percent and number of guesses and option to reset the quiz
                AlertDialog.Builder builder = new AlertDialog.Builder(this);
                builder.setMessage(getString(R.string.results, mTotalGuesses, ((double)mCorrectGuesses /mTotalGuesses) *100   )); // this is wrong

                //set the positive button of the dialog
                //positive button means reset quiz - looks like a hyperlink
                //new OnClick and the Dialog comes up
                builder.setPositiveButton(getString(R.string.reset_quiz), new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        resetQuiz();
                    }
                });
                //don't want them to be able to cancel the dialog cuz we'll be stuck at the game
                builder.setCancelable(false);
                builder.create();
                builder.show();


                mAnswerTextView.setTextColor(getResources().getColor(R.color.correct_answer));



            }




        }
        else
        {
            mAnswerTextView.setTextColor(getResources().getColor(R.color.incorrect_answer));
            mAnswerTextView.setText(getString(R.string.incorrect_answer));
            clickedButton.setEnabled(false);

            //wait and remove incorrect message
            handler.postDelayed(new Runnable() {
                @Override
                public void run() {
                    mAnswerTextView.setText("");
                }
            }, 1000) ;

        }


    }



    public static final String PREF_QUIZ_TYPE = "pref_quizType";

    SharedPreferences.OnSharedPreferenceChangeListener mSharedPreferenceChangeListener =
            new SharedPreferences.OnSharedPreferenceChangeListener() {

        @Override
        public void onSharedPreferenceChanged(SharedPreferences sharedPreferences, String key) {
            switch (key)
            { case PREF_QUIZ_TYPE:                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                               // constant that stores "pref_quizType" to match preferences.xml
                mQuizType = sharedPreferences.getString(PREF_QUIZ_TYPE, mQuizType);
                updateBasedOnQuizType();
                break;
            }
        }
    };




}

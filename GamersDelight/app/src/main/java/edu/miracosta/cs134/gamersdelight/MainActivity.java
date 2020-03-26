package edu.miracosta.cs134.gamersdelight;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.RatingBar;

import androidx.appcompat.app.AppCompatActivity;

import org.json.JSONObject;

import java.io.IOException;
import java.util.List;

import edu.miracosta.cs134.gamersdelight.model.Game;
import edu.miracosta.cs134.gamersdelight.model.JSONLoader;

public class MainActivity extends AppCompatActivity {


    private List<Game> gamesList;
    private GameListAdapter gamesListAdapter;
    private ListView gamesListView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        try {
            gamesList = JSONLoader.loadJSONFromAsset(this);
        } catch (IOException e) {
            e.printStackTrace();
        }

        gamesListView = findViewById(R.id.gameListView) ;
        gamesListAdapter = new GameListAdapter(this, R.layout.game_list_item, gamesList);
        // DONE: Connect the ListView with the layout
        // DONE:  Populate all games list using the JSONLoader
        // DONE:  Create a new ListAdapter connected to the correct layout file and list
        // DONE:  Connect the ListView with the ListAdapter
        gamesListView.setAdapter(gamesListAdapter);
    }

    public void viewGameDetails(View view) {

        Game clicked = (Game) view.getTag();

        Intent intent = new Intent(this, GameDetailsActivity.class);

        intent.putExtra("ImageName", clicked.getImageName());
        intent.putExtra("Name", clicked.getName());
        intent.putExtra("Rating", clicked.getRating());
        intent.putExtra("Description", clicked.getDescription());

        startActivity(intent);
        // DONE: Use an Intent to start the GameDetailsActivity with the data it needs to correctly inflate its views.
    }

    public void addGame(View view)
    {
        EditText nameEditText = findViewById(R.id.nameEditText);
        EditText descriptionEditText = findViewById(R.id.descriptionEditText) ;
        RatingBar gameRatingBar = findViewById(R.id.gameRatingBar);

        Game addMe = new Game();
        addMe.setDescription(descriptionEditText.getText().toString());
        addMe.setRating(gameRatingBar.getRating());
        addMe.setName(nameEditText.getText().toString());
        addMe.setImageName("controller.png");

        gamesListAdapter.add(addMe);

        nameEditText.setText("");
        descriptionEditText.setText("");
        gameRatingBar.setRating(0.0f);
        // DONE:  Read information from EditTexts and RatingBar,
        // DONE:  Create a new Game object then add it to the list
        // DONE:  Make sure the list adapter is notified
        // DONE:  Clear all entries the user made (edit text and rating bar)
    }



}

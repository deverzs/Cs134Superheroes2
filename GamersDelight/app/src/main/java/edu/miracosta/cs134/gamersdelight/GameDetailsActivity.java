package edu.miracosta.cs134.gamersdelight;

import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.io.IOException;
import java.io.InputStream;

public class GameDetailsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game_details);

        TextView gameDetailsNameTextView = findViewById(R.id.gameDetailsNameTextView);
        TextView gameDetailsDescriptionTextView = findViewById(R.id.gameDetailsDescriptionTextView);
        RatingBar gameDetailsRatingBar = findViewById(R.id.gameDetailsRatingBar);
        ImageView gameDetailsImageView = findViewById(R.id.gameDetailsImageView) ;

        Intent intent = getIntent();
        String imageView = intent.getStringExtra("ImageName");
        String nameText = intent.getStringExtra("Name");
        Float rating = intent.getFloatExtra("Rating", 0.0f);
        String description = intent.getStringExtra("Description");

        gameDetailsDescriptionTextView.setText(description);
        gameDetailsNameTextView.setText(nameText);
        gameDetailsRatingBar.setRating(rating);

        try {
            InputStream stream = getAssets().open(imageView);
            Drawable image = Drawable.createFromStream(stream, imageView);
            gameDetailsImageView.setImageDrawable(image);

        } catch (IOException e) {
            e.printStackTrace();
        }

        // TODO:  Use the Intent object sent from MainActivity to populate the Views on
        // TODO:  this layout, including the TextViews, RatingBar and ImageView with the Game details.
    }
}

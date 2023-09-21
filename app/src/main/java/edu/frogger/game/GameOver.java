package edu.frogger.game;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class GameOver extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game_over);

        TextView score = (TextView) findViewById(R.id.score);
        String scoreText = "Highest Score: " + getIntent().getStringExtra("score");
        score.setText(scoreText);
    }

    public void restart(View view) {
        Intent intent = new Intent(this, InitActivity.class);
        startActivity(intent);
    }

    public void exit(View view) {
        finishAffinity();
        System.exit(0);
    }
}
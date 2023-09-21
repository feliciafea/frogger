package edu.frogger.game;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.WindowManager;

import java.util.Objects;

public class GameActivity extends AppCompatActivity {

    private Game game;
    private Player player;

    private SwipeListener swipeListener;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);
        Objects.requireNonNull(getSupportActionBar()).hide();
        Intent intent = getIntent();
        String name = intent.getStringExtra("name");
        String diff = intent.getStringExtra("difficulty");
        int sprite = intent.getIntExtra("character", 0);
        game = new Game(this, name, diff, sprite);
        swipeListener = new SwipeListener(findViewById(android.R.id.content).getRootView(), game);
        setContentView(game);
    }

    protected void onPause() {
        super.onPause();
        game.getThread().stopLoop();
    }

    public void onBackPressed() {
        //super.onBackPressed();
    }
}
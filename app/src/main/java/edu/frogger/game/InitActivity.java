package edu.frogger.game;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
public class InitActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener {

    private String difficultySelection = "easy";
    private int characterSelection = 1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_init);

        //Sets up difficulty selections
        Spinner spinner = (Spinner) findViewById(R.id.diffculty_spinner);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                R.array.difficulty_array, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);
        spinner.setOnItemSelectedListener(this);

        //Set default character
        ImageView characterImage = findViewById(R.id.imageView);
        characterImage.setImageResource(R.drawable.character1);
        characterImage.getDrawable().setFilterBitmap(false);

    }

    public void goToGameActivity(View view) {
        EditText input = findViewById(R.id.inputname);
        TextView screenMessage = findViewById(R.id.screenmessage);
        if (isEmpty(input.getText().toString())) {
            screenMessage.setText("Please input a name with at least one character");
        } else {
            Intent intent = new Intent(this, GameActivity.class);
            System.out.println(characterSelection);
            intent.putExtra("name", input.getText().toString());
            intent.putExtra("difficulty", difficultySelection);
            intent.putExtra("character", characterSelection);
            startActivity(intent);
        }
    }

    public void selectChar1(View view) {
        ImageView characterImage = findViewById(R.id.imageView);
        characterImage.setImageResource(R.drawable.character1);
        characterImage.getDrawable().setFilterBitmap(false);
        characterSelection = 1;
    }

    public void selectChar2(View view) {
        ImageView characterImage = findViewById(R.id.imageView);
        characterImage.setImageResource(R.drawable.character2);
        characterImage.getDrawable().setFilterBitmap(false);
        characterSelection = 2;
    }


    public void selectChar3(View view) {
        ImageView characterImage = findViewById(R.id.imageView);
        characterImage.setImageResource(R.drawable.character3);
        characterImage.getDrawable().setFilterBitmap(false);
        characterSelection = 3;
    }


    /**
     * Checks if a string is only whitespace. This includes tabs, new lines, and spaces
     * @param s a string that will be checked for whitespace
     * @return true or false
     */
    public static boolean isEmpty(String s) {
        if (s == null || s.length() == 0) {
            return true;
        }
        for (int i = 0; i < s.length(); i++) {
            if (!Character.isWhitespace(s.charAt(i))) {
                return false;
            }
        }
        return true;
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        difficultySelection = parent.getItemAtPosition(position).toString();
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }
}
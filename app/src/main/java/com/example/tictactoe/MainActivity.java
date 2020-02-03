package com.example.tictactoe;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import org.w3c.dom.Text;

public class MainActivity extends AppCompatActivity {

    EditText player1;
    EditText player2;
    Button start;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        player1 = findViewById(R.id.editText);
        player2 = findViewById(R.id.editText2);

        start = findViewById(R.id.start);
        start.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                if(TextUtils.isEmpty(player1.getText().toString())){
                    Toast.makeText(MainActivity.this,"Please enter player name",Toast.LENGTH_SHORT).show();
                }
                else if(TextUtils.isEmpty(player2.getText().toString())){
                    Toast.makeText(MainActivity.this,"Please enter player name",Toast.LENGTH_SHORT).show();
                }
                else {
                    String  player1_name = player1.getText().toString();
                    String  player2_name = player2.getText().toString();
                    Intent intent = new Intent(MainActivity.this, GamePage.class);
                    intent.putExtra("Player Name 1",player1_name);
                    intent.putExtra("Player Name 2",player2_name);
                    startActivity(intent);
                    Toast.makeText(MainActivity.this,"Hold Tight Game Starts Now",Toast.LENGTH_SHORT).show();

                }






            }
        });

    }
}

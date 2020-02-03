package com.example.tictactoe;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class GamePage extends AppCompatActivity implements View.OnClickListener {



    private Button[][] buttons = new Button[3][3];

    private boolean player1turn = true;

    private int roundcount;

    private int player1point;
    private int player2point;

    private TextView textviewplayer1;
    private TextView textviewplayer2;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game_page);

        textviewplayer1 = findViewById(R.id.text_view_p1);
        textviewplayer2 = findViewById(R.id.text_view_p2);

        textviewplayer1.setText(getIntent().getStringExtra("Player Name 1")+" Won : ");
        textviewplayer2.setText(getIntent().getStringExtra("Player Name 2")+" Won : ");


        for(int i=0; i<3; i++)
        {
            for(int j=0; j<3; j++)
            {
                String buttonid = "button_" + i + j;
                int resid = getResources().getIdentifier(buttonid,"id", getPackageName());
                buttons[i][j] = findViewById(resid);
                buttons[i][j].setOnClickListener(this);

            }
        }

        Button buttonreset = findViewById(R.id.button_reset);
        buttonreset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
                resetGame();
            }
        });

        /*
        Button buttonreset3 = findViewById(R.id.button_reset3);
        buttonreset3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                playagain();
            }
        });*/

        Button buttonreset2 = findViewById(R.id.button_reset2);
        buttonreset2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(GamePage.this,MainActivity.class );
                startActivity(intent);
            }
        });


    }

    @Override
    public void onClick(View v) {

        if(!((Button) v).getText().toString().equals(""))
        {
            return;
        }

        if(player1turn)
        {
            ((Button) v).setText("X");
        }
        else
        {
            ((Button) v).setText("O");
        }

        roundcount++;

        if (checkforwin())
        {
            if(player1turn)
            {
                player1wins();
            }
            else
            {
                player2wins();
            }
        }
        else if (roundcount == 9)
        {
            draw();
        }
        else
        {
            player1turn = !player1turn;
        }
    }

    private boolean checkforwin()
    {
        String[][] field = new String[3][3];

        for(int i=0; i<3; i++)
        {
            for (int j = 0; j < 3; j++)
            {

                field[i][j] =buttons[i][j].getText().toString();

            }
        }

        for (int i=0; i<3; i++)
        {
            if (field[i][0].equals(field[i][1]) && field[i][0].equals(field[i][2]) && !field[i][0].equals(""))
            {
                return true;
            }
        }

        for (int i=0; i<3; i++)
        {
            if (field[0][i].equals(field[1][i]) && field[0][i].equals(field[2][i]) && !field[0][i].equals(""))
            {
                return true;
            }
        }

        if (field[0][0].equals(field[1][1]) && field[0][0].equals(field[2][2]) && !field[0][0].equals(""))
        {
            return true;
        }

        if (field[0][2].equals(field[1][1]) && field[0][2].equals(field[2][0]) && !field[0][2].equals(""))
        {
            return true;
        }

        return false;
    }

    private void player1wins()
    {
        player1point++;
        Toast.makeText(this,getIntent().getStringExtra("Player Name 1"), Toast.LENGTH_LONG).show();
        updatepointstext();
        resetboard();
    }

    private void player2wins()
    {
        player2point++;
        Toast.makeText(this,getIntent().getStringExtra("Player Name 2"), Toast.LENGTH_LONG).show();
        updatepointstext();
        resetboard();
    }

    private void draw()
    {
        Toast.makeText(this, "Draw!", Toast.LENGTH_LONG).show();
        resetboard();
    }

    private void updatepointstext()
    {
        textviewplayer1.setText(getIntent().getStringExtra("Player Name 1")+" Won : " + player1point);
        textviewplayer2.setText(getIntent().getStringExtra("Player Name 2")+" Won : " + player2point);
    }

    private void resetboard()
    {
        for (int i=0; i<3; i++)
        {
            for(int j=0; j<3; j++)
            {
                buttons[i][j].setText("");
            }
        }

        roundcount = 0;
        player1turn = true;
    }

    private void playagain()
    {
        for (int i=0; i<3; i++)
        {
            for(int j=0; j<3; j++)
            {
                buttons[i][j].setText("");
            }
        }

        player1turn = true;
    }

    private void resetGame()
    {
        player1point = 0;
        player2point = 0;
        updatepointstext();
        resetboard();
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putInt("roundcount",roundcount);
        outState.putInt("player1points",player1point);
        outState.putInt("player2points",player2point);
        outState.putBoolean("player1turn",player1turn);
    }

    @Override
    protected void onRestoreInstanceState(@NonNull Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);

        roundcount = savedInstanceState.getInt("roundcount");
        player1point = savedInstanceState.getInt("player1points");
        player2point = savedInstanceState.getInt("player2points");
        player1turn = savedInstanceState.getBoolean("player1turn");

    }
}

package com.ibsanju.tictactoe;
/*

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public
class tictactoe extends AppCompatActivity {

    @Override
    protected
    void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tictactoe);
    }
}
*/


import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public
class tictactoe extends AppCompatActivity implements View.OnClickListener {

    private Button[][] buttons = new Button[3][3];

    private boolean p1Turn = true;
    private int roundCount;

    private int p1Score;
    private int p2Score;

    private TextView tvp1;
    private TextView tvp2;

    @Override
    protected
    void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tictactoe);

        tvp1 = findViewById(R.id.tv_p1);
        tvp2 = findViewById(R.id.tv_p2);

        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                String buttonID = "btn_" + i + j;
                int    resID    = getResources().getIdentifier(buttonID, "id", getPackageName());
                buttons[i][j] = findViewById(resID);
                buttons[i][j].setOnClickListener(this);
            }
        }
        Button btnReset = findViewById(R.id.btn_reset);
        btnReset.setOnClickListener(new View.OnClickListener() {
            @Override
            public
            void onClick(View v) {
                resetGame();
            }
        });
    }

    @Override
    public
    void onClick(View v) {
        if (!((Button) v).getText().toString().equals("")) {
            return;
        }
        if (p1Turn) {
            ((Button) v).setText("X");
        } else {
            ((Button) v).setText("O");
        }
        roundCount++;
        if (checkForWin()) {
            if (p1Turn) {
                p1Wins();
            } else {
                p2Wins();
            }
        } else if (roundCount == 9) {
            draw();
        } else {
            p1Turn = !p1Turn;
        }
    }

    private
    boolean checkForWin() {
        String[][] field = new String[3][3];
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                field[i][j] = buttons[i][j].getText().toString();
            }
        }
        for (int i = 0; i < 3; i++) {
            if (field[i][0].equals(field[i][1])
                    && field[i][0].equals(field[i][2])
                    && !field[i][0].equals(""))
                return true;
        }
        for (int i = 0; i < 3; i++) {
            if (field[0][i].equals(field[1][i])
                    && field[0][i].equals(field[2][i])
                    && !field[0][i].equals(""))
                return true;
        }
        if (field[0][0].equals(field[1][1])
                && field[0][0].equals(field[2][2])
                && !field[0][0].equals(""))
            return true;
        if (field[0][2].equals(field[1][1])
                && field[0][2].equals(field[2][0])
                && !field[0][2].equals(""))
            return true;

        return false;
    }

    private
    void p1Wins() {
        p1Score++;
        Toast.makeText(this, "Player 1 Wins!", Toast.LENGTH_LONG).show();
        updatePointsText();
        resetBoard();
    }

    private
    void p2Wins() {
        p2Score++;
        Toast.makeText(this, "Player 2 Wins!", Toast.LENGTH_LONG).show();
        updatePointsText();
        resetBoard();
    }

    private
    void draw() {
        Toast.makeText(this, "Draw!", Toast.LENGTH_LONG).show();
        resetBoard();
    }

    private
    void updatePointsText() {
        tvp1.setText("Player 1: " + p1Score);
        tvp2.setText("Player 2: " + p2Score);
    }

    private
    void resetBoard() {
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                buttons[i][j].setText("");
            }
        }
        roundCount = 0;
        p1Turn     = true;
    }

    private
    void resetGame() {
        p1Score = 0;
        p2Score = 0;
        updatePointsText();
        resetBoard();
    }

    @Override
    protected
    void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putInt("roundCount", roundCount);
        outState.putInt("p1Score", p1Score);
        outState.putInt("p2Score", p2Score);
        outState.putBoolean("p1Turn", p1Turn);
    }

    @Override
    protected
    void onRestoreInstanceState(@NonNull Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        roundCount = savedInstanceState.getInt("roundCount");
        p1Score    = savedInstanceState.getInt("p1Score");
        p2Score    = savedInstanceState.getInt("p2Score");
        p1Turn     = savedInstanceState.getBoolean("p1Turn");
    }
}
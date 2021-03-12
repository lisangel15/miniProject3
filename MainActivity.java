package com.example.teamscorecounter;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    public static final String TAG= "MainActivity";
    //declare your variables for the widgets
    TextView hrocketsScore;
    TextView gstateScore;
    Button hrocketsButton;
    Button gstateButton;

    //declare the variables for the calculations
    static int houstonScore = 0;
    static int goldenstateScore = 0;
    int wonBy = 0;
    String winningTeam;

   public static final String KEY_WINNING_TEAM = "com.teamScoreCount.KEY_WINNING_TEAM";
   public static final String KEY_WON_BY = "com.teamScoreCount.KEY_WON_BY";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //add Listeners to Widgets
        hrocketsScore = (TextView) findViewById(R.id.hrScore);
        gstateScore = (TextView) findViewById(R.id.gsScore);
        hrocketsButton = (Button) findViewById(R.id.hrButton);
        gstateButton = (Button) findViewById(R.id.gsButton);

        hrocketsScore.setText("Score: " + String.valueOf(houstonScore));
        gstateScore.setText("Score: " + String.valueOf(goldenstateScore));


        hrocketsButton.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            houstonScore++;
            hrocketsScore.setText("Score: " + houstonScore);
            gameOver();
        }
    });

        gstateButton.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            goldenstateScore++;
            gstateScore.setText("Score: " + goldenstateScore);
            gameOver();
        }
    });
}

    @Override
    protected void onStart() {
        super.onStart();
    }

    @Override
    protected void onStop() {
        super.onStop();
    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }

    @Override
    protected void onRestart() {
        super.onRestart();
    }

    @Override
    protected void onPause() {
        super.onPause();
    }

    public void gameOver() {
        Log.d(TAG,"inside gameOver");


        if (houstonScore == 5) {
            winningTeam = "Houston Rockets";
            wonBy = houstonScore - goldenstateScore;
        } else if (goldenstateScore == 5){
            winningTeam = "Golden State";
            wonBy = goldenstateScore - houstonScore;
        }
        if (houstonScore==5 || goldenstateScore== 5) {
            Intent intent = new Intent(this, WinnerActivity.class);
            intent.putExtra(KEY_WINNING_TEAM, winningTeam);
            intent.putExtra(KEY_WON_BY, wonBy);
            startActivity(intent);
        }

Log.d(TAG, "winning Team, won by");

    }

}

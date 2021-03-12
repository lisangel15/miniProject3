package com.example.teamscorecounter;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class WinnerActivity extends AppCompatActivity {
    private TextView textView_win;
    private Button button_startActivity;
    private String winningTeam;
    private int wonBy;
    private Intent shareActivity;

    public static final String KEY_WINNING_TEAM = "com.teamScoreCount.KEY_WINNING_TEAM";
    public static final String KEY_WON_BY = "com.teamScoreCount.KEY_WON_BY";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.winner_activity);

        textView_win = (TextView)findViewById(R.id.winner_textView);

        button_startActivity = (Button)findViewById(R.id.shareActivity_Button);         
        shareActivity = new Intent(this, ShareActivity.class);             

        button_startActivity.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                shareActivity.putExtra(KEY_WINNING_TEAM, winningTeam);
                shareActivity.putExtra(KEY_WON_BY, wonBy);

                startActivity(shareActivity);
            }
        });

        Intent intent = getIntent();
        int wonBy = intent.getIntExtra(WinnerActivity.KEY_WON_BY, 0);
        String winningTeam = intent.getStringExtra(WinnerActivity.KEY_WINNING_TEAM);

        textView_win.setText(winningTeam + " won by: " + wonBy);
    }
}

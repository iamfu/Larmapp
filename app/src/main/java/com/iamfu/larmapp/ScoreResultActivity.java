package com.iamfu.larmapp;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

/**
 * Created by nattha on 11/6/16 AD.
 */
public class ScoreResultActivity extends Activity {

    private int lessonid;
    private int scorePoint;

    private TextView score;
    private TextView status_score;

    private ImageView imgStatus;

    private Button buttonRestart;
    private Button buttonGoToHome;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.score_result);

        score = (TextView) findViewById(R.id.score);
        status_score = (TextView) findViewById(R.id.status_score);
        imgStatus = (ImageView) findViewById(R.id.imgStatus);
        buttonRestart = (Button) findViewById(R.id.restartLesson);
        buttonGoToHome = (Button) findViewById(R.id.goToHome);

        Intent intent = getIntent();
        lessonid = Integer.parseInt(intent.getStringExtra("lessonid"));
        scorePoint = Integer.parseInt(intent.getStringExtra("score"));

        setEmotion();
        setScore();
        setStatus();

        setOnclick();
    }

    private void setEmotion() {
        if (scorePoint <= 1) {
            imgStatus.setImageResource(R.drawable.tryagain);
        } else if (scorePoint >= 2 && scorePoint <= 3) {
            imgStatus.setImageResource(R.drawable.cheerup);
        } else {
            imgStatus.setImageResource(R.drawable.verygood);
        }

    }

    private void setScore() {
        score.setText(scorePoint + " / " + 5);
    }

    private void setStatus() {
        if (scorePoint <= 1) {
            status_score.setText("พยายามใหม่อีกครั้งนะ-");
        } else if (scorePoint >= 2 && scorePoint <= 3) {
            status_score.setText("อีกนิดเดียว พยายามเข้า!");
        } else {
            status_score.setText("ยินดีด้วย คุณทำได้ดีมาก!!");
        }
    }

    private void setOnclick() {
        buttonRestart.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent intent = new Intent(ScoreResultActivity.this, ContentActivity.class);
                intent.putExtra("lessonid", lessonid + "");
                startActivity(intent);
            }
        });

        buttonGoToHome.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent intent = new Intent(ScoreResultActivity.this, HomeActivity.class);
                startActivity(intent);
            }
        });
    }
}

package com.boss.tictactoewithfriends;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class playActivity extends AppCompatActivity implements View.OnClickListener {
    Button play, reset;
    int count = 0;
    ImageView fir, sec, thi, fou, fiv, six, sev, eig, nin;
    boolean isCross = true;
    boolean isGameEnd = false;
    boolean isBotPlaying = false , isBot = false;
    static int[] game;
    TextView result;

    public ImageView getInstanceByPosition(int pos) {
        switch (pos) {
            case 0:
                return fir;
            case 1:
                return sec;
            case 2:
                return thi;
            case 3:
                return fou;
            case 4:
                return fiv;
            case 5:
                return six;
            case 6:
                return sev;
            case 7:
                return eig;
            case 8:
                return nin;
        }
        return null;
    }

    public int isWin(){
        if (((game[0] == -1 && game[1] == -1 && game[2] == -1)
                || (game[3] == -1 && game[4] == -1 && game[5] == -1)
                || (game[6] == -1 && game[7] == -1 && game[8] == -1)
                || (game[0] == -1 && game[3] == -1 && game[6] == -1)
                || (game[1] == -1 && game[4] == -1 && game[7] == -1)
                || (game[2] == -1 && game[5] == -1 && game[8] == -1)
                || (game[0] == -1 && game[4] == -1 && game[8] == -1)
                || (game[2] == -1 && game[4] == -1 && game[6] == -1))) {
            return -1;
        }

        if ((game[0] == 1 && game[1] == 1 && game[2] == 1)
                || (game[3] == 1 && game[4] == 1 && game[5] == 1)
                || (game[6] == 1 && game[7] == 1 && game[8] == 1)
                || (game[0] == 1 && game[3] == 1 && game[6] == 1)
                || (game[1] == 1 && game[4] == 1 && game[7] == 1)
                || (game[2] == 1 && game[5] == 1 && game[8] == 1)
                || (game[0] == 1 && game[4] == 1 && game[8] == 1)
                || (game[2] == 1 && game[4] == 1 && game[6] == 1)) {

            return 1;
        }
        boolean t = true;
        for (int i = 0; i < 9; i++) {
            if (game[i] == 0) {
                t = false;
                break;
            }
        }
        if (t) return 0;
        return 2;
    }

    public void setGame(int pos) {
        if(isGameEnd){
            Toast.makeText(this, "GAME OVER", Toast.LENGTH_SHORT).show();
            return;
        }
        if (game[pos] != 0) return;
        if (isCross) {
            getInstanceByPosition(pos).setBackgroundResource(R.drawable.ic_x);
            game[pos] = -1;
            isCross = false;
            count++;
        } else {
            getInstanceByPosition(pos).setBackgroundResource(R.drawable.ic_circlefortictactoe);
            game[pos] = 1;
            isCross = true;
            count++;
        }
        if (count >= 9) isGameEnd = true;
        if(isWin() == 1) {
            if(isBot) Toast.makeText(this, "BOT HAS WON", Toast.LENGTH_LONG).show();
            else Toast.makeText(this, "O player has won", Toast.LENGTH_SHORT).show();
            isGameEnd = true;
            return;
        }
        else if(isWin() == -1){
            if(isBot) Toast.makeText(this, "YOU HAVE WON", Toast.LENGTH_LONG).show();
            else Toast.makeText(this, "X player has won", Toast.LENGTH_SHORT).show();
            isGameEnd = true;
            return;
        }
        else if(isWin() == 0){
            Toast.makeText(this, "GAME DRAW", Toast.LENGTH_LONG).show();
            isGameEnd = true;
            return;
        }
        if(isBotPlaying) {
            isBotPlaying = false;
            playBot();
        }

    }

    public int botGame(int bot) {
        if(isWin() == 1) return 1;
        else if(isWin() == -1) return -1;
        else if(isWin() == 0) return 0;
        if (bot == 1) {
            int ans = -1;
            for (int i = 0; i < 9; i++) {
                if (game[i] == 0) {
                    game[i] = 1;
                    int temp = botGame(0); // Recursive call
                    game[i] = 0; // Undo the move
                    ans = Math.max(temp, ans); // Compare with current best move
                }
            }
            return ans;
        } else {
            int ans = 1;
            for (int i = 0; i < 9; i++) {
                if (game[i] == 0) {
                    game[i] = -1;
                    int temp = botGame(1); // Recursive call
                    game[i] = 0; // Undo the move
                    ans = Math.min(temp, ans); // Compare with current best move
                }
            }
            return ans;
        }
    }

    public void playBot() {
        if (isGameEnd) return;
        int temp = -2, idx = -1;
        for (int i = 0; i < 9; i++) {
            if (game[i] == 0) {
                game[i] = 1;
                int bot = botGame(0);
                game[i] = 0;
                Log.d("BOT bot", String.valueOf(bot));
                if (bot > temp) {
                    temp = bot;
                    idx = i;
                }
            }
        }
        Log.d("BOT TURN", String.valueOf(idx));
        setGame(idx);
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        game = new int[]{0, 0, 0, 0, 0, 0, 0, 0, 0};
        super.onCreate(savedInstanceState);
        getSupportActionBar().hide();
        setContentView(R.layout.activity_play);
        fir = findViewById(R.id.container1);
        sec = findViewById(R.id.container2);
        thi = findViewById(R.id.container3);
        fou = findViewById(R.id.container4);
        fiv = findViewById(R.id.container5);
        six = findViewById(R.id.container6);
        sev = findViewById(R.id.container7);
        eig = findViewById(R.id.container8);
        nin = findViewById(R.id.container9);
        reset = findViewById(R.id.reset);
        result = findViewById(R.id.gameResult);
        result.setVisibility(View.GONE);
        fir.setOnClickListener(this);
        sec.setOnClickListener(this);
        thi.setOnClickListener(this);
        fou.setOnClickListener(this);
        fiv.setOnClickListener(this);
        six.setOnClickListener(this);
        sev.setOnClickListener(this);
        eig.setOnClickListener(this);
        nin.setOnClickListener(this);
        Intent intent = getIntent();
        isBot = intent.getBooleanExtra("isBot" , true);
        reset.setOnClickListener(this);
    }

    public void reset() {
        fir.setBackgroundResource(R.drawable.nobackground);
        sec.setBackgroundResource(R.drawable.nobackground);
        thi.setBackgroundResource(R.drawable.nobackground);
        fou.setBackgroundResource(R.drawable.nobackground);
        fiv.setBackgroundResource(R.drawable.nobackground);
        six.setBackgroundResource(R.drawable.nobackground);
        sev.setBackgroundResource(R.drawable.nobackground);
        eig.setBackgroundResource(R.drawable.nobackground);
        nin.setBackgroundResource(R.drawable.nobackground);
        count = 0;
        for (int i = 0; i < 9; i++) {
            game[i] = 0;
        }
        isGameEnd = false;
        isCross = true;
        result.setVisibility(View.GONE);
//      reset.setVisibility(View.GONE);

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.container1:
                if (game[0] == 0 && !isGameEnd) {
                    if(isBot) isBotPlaying = true;
                    setGame(0);
                }
                break;
            case R.id.container2:
                if (game[1] == 0 && !isGameEnd) {
                    if(isBot) isBotPlaying = true;
                    setGame(1);
                }
                break;
            case R.id.container3:
                if (game[2] == 0 && !isGameEnd) {
                    if(isBot) isBotPlaying = true;
                    setGame(2);
                }
                break;
            case R.id.container4:
                if (game[3] == 0 && !isGameEnd) {
                    if(isBot) isBotPlaying = true;
                    setGame(3);
                }
                break;
            case R.id.container5:
                if (game[4] == 0 && !isGameEnd) {
                    if(isBot) isBotPlaying = true;
                    setGame(4);
                }
                break;
            case R.id.container6:
                if (game[5] == 0 && !isGameEnd) {
                    if(isBot) isBotPlaying = true;
                    setGame(5);
                }
                break;
            case R.id.container7:
                if (game[6] == 0 && !isGameEnd) {
                    if(isBot) isBotPlaying = true;
                    setGame(6);
                }
                break;
            case R.id.container8:
                if (game[7] == 0 && !isGameEnd) {
                    if(isBot) isBotPlaying = true;
                    setGame(7);
                }
                break;
            case R.id.container9:
                if (game[8] == 0 && !isGameEnd) {
                    if(isBot) isBotPlaying = true;
                    setGame(8);
                }
                break;
            case R.id.reset:
                reset();
                break;
        }
    }
}
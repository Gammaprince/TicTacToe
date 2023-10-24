package com.boss.tictactoewithfriends;

import android.content.Context;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

public class logic {
   Context context;
    Button play,reset;
   ImageView fir,sec,thi,fou,fiv,six,sev,eig,nin;
    boolean isCross=true;
    boolean isGameEnd=false;
    int[] game=new int[9];
    TextView result;


    public logic(Context context ){
        this.context=context;
    }

    public  void pop(){


        //This will ensure if game is end or not
        if (!isGameEnd) {

            if (game[0] == 0) {


//This will check either clickable item Cross or not
                if (isCross) {
                    fir.setBackgroundResource(R.drawable.close);
                    isCross = false;
                } else {
                    fir.setBackgroundResource(R.drawable.ic_circlefortictactoe);
                    isCross = true;
                }

//This will check either someone win or not
                if ((
                        //this is O's for position  0,1,2
                        (game[0] == -1 && game[1] == 10 && game[2] == 20) ||
                                //this is for position 3,4,5
                                (game[3] == 30 && game[4] == 40 && game[5] == 50) ||
                                //this is for position 6,7,8
                                (game[6] == 60 && game[7] == 70 && game[8] == 80) ||
                                //this is for position 0,3,6
                                (game[0] == -1 && game[3] == 30 && game[6] == 60) ||
                                //this is for position 1,4,7
                                (game[1] == 10 && game[4] == 40 && game[7] == 70) ||
                                //this is for position 2,5,8
                                (game[2] == 20 && game[5] == 50 && game[8] == 80) ||
                                //this is for position 0,4,8
                                (game[0] == -1 && game[4] == 40 && game[8] == 80) ||
                                //this is for position 2,4,6
                                (game[2] == 20 && game[4] == 40 && game[6] == 60)


                )) {

                    //Gameend will update in true
                    isGameEnd = true;

                    //Result will update

                    reset.setVisibility(View.VISIBLE);
                    result.setVisibility(View.VISIBLE);
                    result.setText("O has won");


                }

                if ( //this is for X's position 0,1,2
                        (game[0] == 01 && game[1] == 11 && game[2] == 21) ||
                                //this is for position 3,4,5
                                (game[3] == 31 && game[4] == 41 && game[5] == 51) ||
                                //this is for position 6,7,8
                                (game[6] == 61 && game[7] == 71 && game[8] == 81) ||
                                //this is for position 0,3,6
                                (game[0] == 01 && game[3] == 31 && game[6] == 61) ||
                                //this is for position 1,4,7
                                (game[1] == 11 && game[4] == 41 && game[7] == 71) ||
                                //this is for position 2,5,8
                                (game[2] == 21 && game[5] == 51 && game[8] == 81) ||
                                //this is for position 0,4,8
                                (game[0] == 01 && game[4] == 41 && game[8] == 81) ||
                                //this is for position 2,4,6
                                (game[2] == 21 && game[4] == 41 && game[6] == 61)
                ) {
                    //Gameend will update in true
                    isGameEnd = true;

                    //Result will update
                    reset.setVisibility(View.VISIBLE);
                    result.setVisibility(View.VISIBLE);
                    result.setText("X has won");


                }


                if (game[0] == 0 && game[1] == 0 && game[2] == 0 && game[3] == 0 && game[4] == 0 && game[5] == 0 && game[6] == 0 && game[7] == 7 && game[8] == 8) {

                } else {
                    reset.setVisibility(View.VISIBLE);
                }
            }

            if (isCross) {
                game[0] = 01;
            } else {
                game[0] = -1;
            }



        }
    }
}

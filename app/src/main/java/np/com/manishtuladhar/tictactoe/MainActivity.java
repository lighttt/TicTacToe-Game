package np.com.manishtuladhar.tictactoe;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayout;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    // 0 = circle 1 = cross
    int activePlayer = 0;

    // game memory state
    // 2 = unplayed state
    int[] gameState = {2,2,2,2,2,2,2,2,2};

    //jitne position haru
    int[][] winningPositions = {{0,1,2},{3,4,5},{6,7,8},{0,3,6},{1,4,7},{2,5,8},{0,4,8},{2,4,6}};

    //game active or not
    boolean gameIsActive = true;


    public void dropIn(View view){

        ImageView counter  = (ImageView)view;

        //image set according to player

        int tappedCounter = Integer.parseInt(counter.getTag().toString());


        /**
         * ---------------------------  Placing image accroding to active player ----------------------
         */

        if(gameState[tappedCounter] == 2 && gameIsActive )
        {
            gameState[tappedCounter] = activePlayer;
            //image lai hatauna
            counter.setTranslationY(-1000f);

            if(activePlayer == 0)
            {
                counter.setImageResource(R.drawable.circle);
                activePlayer =1;

            }
            else {
                counter.setImageResource(R.drawable.cross);
                activePlayer = 0;
            }

            //lyauna lai
            counter.animate().translationYBy(1000f).rotation(360).setDuration(300);

            /**
             * --------------------------- Checking for winning positions ----------------------
             */

            for(int[] winningPosition : winningPositions)
            {
                if((gameState[winningPosition[0]] == gameState[winningPosition[1]])
                    && (gameState[winningPosition[1]] == gameState[winningPosition[2]])
                    && (gameState[winningPosition[0]]!=2))
                {
                    gameIsActive = false;

                    //declaring who is the winner
                    String winner;

                    if (gameState[winningPosition[0]] == 0) {
                        winner = "Circle";
                    }

                    else{
                        winner = "Cross";
                    }

                    TextView winnerMessage = (TextView) findViewById(R.id.winnerMessage);
                    winnerMessage.setText(winner + " is the winner!! ");

                    // making the linear layout visible again after the game has won
                    LinearLayout linearLayout = (LinearLayout) findViewById(R.id.winnerLayout);
                    linearLayout.setVisibility(View.VISIBLE);
                }
                // ------------------ Draw Case ----------------
                else
                {
                    boolean gameIsOver = true;

                    for(int counterState : gameState)
                    {
                        if (counterState == 2) {
                            gameIsOver = false;
                        }
                    }
                    if(gameIsOver){
                            TextView winnerMessage = (TextView)findViewById(R.id.winnerMessage);
                            winnerMessage.setText("It is a draw!!! ");

                            // making the linear layout visible again after the game has won
                            LinearLayout linearLayout = (LinearLayout)findViewById(R.id.winnerLayout);
                            linearLayout.setVisibility(View.VISIBLE);

                        }
                    }
                }
            }
        }

/*
 ------------------------------- Play Again ko code -----------------
 */
    public void playAgain(View view){

        gameIsActive = true;

        LinearLayout layout = (LinearLayout)findViewById(R.id.winnerLayout);
        layout.setVisibility(View.INVISIBLE);

        //reseting the game status
        activePlayer = 0;

        for(int i = 0;i<gameState.length;i++)
        {
            gameState[i] = 2;
        }

        //gridlayout ko image haru lai sabai reset gareko

        GridLayout gridLayout = (GridLayout)findViewById(R.id.gridLayout);

        for(int i = 0;i<gridLayout.getChildCount();i++)
        {
            ((ImageView)gridLayout.getChildAt(i)).setImageResource(0);
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
}

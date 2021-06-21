package com.ayush.tictactoe;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    // 0 -X
    // 1 -O
    // Player Representation
    int activePlayer = 0;
    int[] gameState = {2,2,2,2,2,2,2,2,2};
    //State meanings
    // 0 - X
    // 1 - O
    // 2 - Null
    int[][] winPositions = {{0,1,2},
                            {3,4,5},
                            {6,7,8},
                            {0,3,6},
                            {1,4,7},
                            {2,5,8},
                            {0,4,8},
                            {2,4,6},};
    boolean gameActive = true;
    public void playerTap(View view) throws InterruptedException {
        ImageView img = (ImageView) view;
        int tappedImage = Integer.parseInt(img.getTag().toString());

        if(gameDraw(gameState)){
            gameReset(view);
        }
        if(!gameActive){
            gameReset(view);
        }
        if (gameState[tappedImage] == 2) {
            gameState[tappedImage] = activePlayer;
            img.setTranslationY(-1000f);
            if (activePlayer == 0) {
                img.setImageResource(R.drawable.x);
                activePlayer = 1;
                TextView status = findViewById(R.id.status);
                status.setText("O's Turn - Tap To Play");
            } else {
                img.setImageResource(R.drawable.o);
                activePlayer = 0;
                TextView status = findViewById(R.id.status);
                status.setText("X's Turn - Tap To Play");
            }
        img.animate().translationYBy(1000f).setDuration(300);
      }
        // Check If Any Player Has Won
        for(int[] winPosition : winPositions){
            if(gameState[winPosition[0]] == gameState[winPosition[1]] &&
                    gameState[winPosition[1]] == gameState[winPosition[2]] &&
                     gameState[winPosition[0]] != 2){
                // SomeBody Has Won - Now Who Won
                String winnerStr;
                gameActive = false;
                if(gameState[winPosition[0]] == 0){
                    winnerStr = "X WON THE GAME";
                }
                else{
                    winnerStr = "O WON THE GAME ";
                }
                // Update The Status Bar
                TextView status = findViewById(R.id.status);
                status.setText(winnerStr);
            }
        }
    }
    public boolean gameDraw(int[] toCheck) throws InterruptedException {
        for(int i = 0;i<toCheck.length;i++){
            if(toCheck[i] == 2)
                return false;
        }
        Toast.makeText(this,"That Was a TIE ...........",Toast.LENGTH_SHORT).show();
        Thread.sleep(1000);
        return true;
    }
    public void gameReset(View view){
        gameActive = true;
        activePlayer = 0;
        for(int i = 0;i<gameState.length;i++){
            gameState[i] = 2;
        }
        ((ImageView)findViewById(R.id.imageView0)).setImageResource(0);
        ((ImageView)findViewById(R.id.imageView1)).setImageResource(0);
        ((ImageView)findViewById(R.id.imageView2)).setImageResource(0);
        ((ImageView)findViewById(R.id.imageView3)).setImageResource(0);
        ((ImageView)findViewById(R.id.imageView4)).setImageResource(0);
        ((ImageView)findViewById(R.id.imageView5)).setImageResource(0);
        ((ImageView)findViewById(R.id.imageView6)).setImageResource(0);
        ((ImageView)findViewById(R.id.imageView7)).setImageResource(0);
        ((ImageView)findViewById(R.id.imageView8)).setImageResource(0);
        TextView status = findViewById(R.id.status);
        status.setText("X's Turn Tap To Play");
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
}
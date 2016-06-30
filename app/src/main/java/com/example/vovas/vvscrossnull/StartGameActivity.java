package com.example.vovas.vvscrossnull;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

/**
 * Created by Vovas on 27.06.2016.
 */
public class StartGameActivity extends Activity {

    private Game game;
//    private Button [][]buttons;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game_new);
        goGame();
    }

    private void goGame(){

        game = new Game();
        final Button [][]  buttons = new Button[3][3];
        buttons[0][0] = (Button) findViewById(R.id.button0);
        buttons[0][1] = (Button) findViewById(R.id.button1);
        buttons[0][2] = (Button) findViewById(R.id.button2);
        buttons[1][0] = (Button) findViewById(R.id.button3);
        buttons[1][1] = (Button) findViewById(R.id.button4);
        buttons[1][2] = (Button) findViewById(R.id.button5);
        buttons[2][0] = (Button) findViewById(R.id.button6);
        buttons[2][1] = (Button) findViewById(R.id.button7);
        buttons[2][2] = (Button) findViewById(R.id.button8);

        final TextView textViewState = (TextView) findViewById(R.id.textState);

        for (int i = 0; i < 3; i++)
            for (int j = 0; j < 3; j++) {
                final int I = i;
                final int J = j;
                buttons[I][J].setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        String str = game.step(I, J);
                        if (str != " ") {
                            buttons[I][J].setText(str);
                            textViewState.setText(game.currentPlayer());
                            switch (game.currentGameState()) {
                                case CONTINUE:
                                    break;
                                case WON_X:
                                    textViewState.setText("Player X is won");
                                    break;
                                case WON_O:
                                    textViewState.setText("Player O is won");
                                    break;
                                case DRAW:
                                    textViewState.setText("This is a Draw");
                                    break;
                            }
                        }
                    }
                });
            }

    }
    public void gameRestart(View view){
        game = new Game();
        ((Button) findViewById(R.id.button0)).setText("");
        ((Button) findViewById(R.id.button1)).setText("");
        ((Button) findViewById(R.id.button2)).setText("");
        ((Button) findViewById(R.id.button3)).setText("");
        ((Button) findViewById(R.id.button4)).setText("");
        ((Button) findViewById(R.id.button5)).setText("");
        ((Button) findViewById(R.id.button6)).setText("");
        ((Button) findViewById(R.id.button7)).setText("");
        ((Button) findViewById(R.id.button8)).setText("");
        ((TextView) findViewById(R.id.textState)).setText(game.currentPlayer());
    }
}

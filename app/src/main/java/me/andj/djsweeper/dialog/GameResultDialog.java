package me.andj.djsweeper.dialog;

import android.app.Dialog;
import android.content.Context;
import android.support.annotation.NonNull;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import me.andj.djsweeper.R;
import me.andj.djsweeper.activity.GameActivity;

/**
 * @program: GameResultDialog
 *
 * @description: The dialog which shows the result of current game.
 *
 * @author: AnDJ
 *
 * @date: 2018/5/1
 */

public class GameResultDialog extends Dialog {
    private Button againButton;
    private Button stayButton;
    private TextView resultTextView;
    public GameResultDialog(@NonNull final Context context,boolean result) {
        super(context);
        setContentView(R.layout.dialog_game_result);
        againButton=findViewById(R.id.dialog_game_result_again_button);
        stayButton=findViewById(R.id.dialog_game_result_stay_button);
        resultTextView=findViewById(R.id.dialog_game_result_result_text_view);
        if (result)
            resultTextView.setText("Successful");
        else
            resultTextView.setText("Failed");

        againButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                GameActivity activity=(GameActivity)context;
                activity.startGame();
                cancel();
            }
        });
        stayButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                cancel();
            }
        });
    }
}

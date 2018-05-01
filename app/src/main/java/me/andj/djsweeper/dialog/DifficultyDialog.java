package me.andj.djsweeper.dialog;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import me.andj.djsweeper.R;
import me.andj.djsweeper.activity.GameActivity;

/**
 * @program: DifficultyDialog
 *
 * @description: The dialog used to choose grade by given four mode.
 *
 * @author: AnDJ
 *
 * @date: 2018/5/1
 */

public class DifficultyDialog extends Dialog implements View.OnClickListener{
    private Button easyButton;
    private Button mediumButton;
    private Button hardButton;
    private Button dIYButton;

    public DifficultyDialog(@NonNull Context context) {
        super(context);
        setContentView(R.layout.dialog_difficulty);
        initial();
    }
    private void initial(){
        easyButton=findViewById(R.id.dialog_difficulty_easy_button);
        mediumButton=findViewById(R.id.dialog_difficulty_medium_button);
        hardButton=findViewById(R.id.dialog_difficulty_hard_button);
        dIYButton=findViewById(R.id.dialog_difficulty_diy_button);
        easyButton.setOnClickListener(this);
        mediumButton.setOnClickListener(this);
        hardButton.setOnClickListener(this);
        dIYButton.setOnClickListener(this);
    }
    @Override
    public void onClick(View view) {
        Intent intent=new Intent(getContext(), GameActivity.class);
        switch (view.getId()){
            case R.id.dialog_difficulty_easy_button:
                intent.putExtra("row",9);
                intent.putExtra("column",9);
                intent.putExtra("mines",10);
                break;
            case R.id.dialog_difficulty_medium_button:
                intent.putExtra("row",16);
                intent.putExtra("column",16);
                intent.putExtra("mines",30);
                break;
            case R.id.dialog_difficulty_hard_button:
                intent.putExtra("row",25);
                intent.putExtra("column",25);
                intent.putExtra("mines",100);
                break;
            case R.id.dialog_difficulty_diy_button:
                GradeChooseDialog dialog=new GradeChooseDialog(getContext());
                cancel();
                dialog.show();
                return;
                default:
        }
        getContext().startActivity(intent);
        this.cancel();
    }

    public static class Builder {
        private Context context;

        public Builder(Context context) {
            this.context = context;
        }

        public DifficultyDialog create() {
            DifficultyDialog dialog=new DifficultyDialog(context);
            return dialog;
        }
    }
}

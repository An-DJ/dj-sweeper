package me.andj.djsweeper.dialog;

import android.annotation.SuppressLint;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.view.View;
import android.widget.Button;
import android.widget.SeekBar;
import android.widget.TextView;

import me.andj.djsweeper.R;
import me.andj.djsweeper.activity.GameActivity;

/**
 * @program: GradeChooseDialog
 *
 * @description: the dialog which is used to choose grade by self.
 *
 * @author: AnDJ
 *
 * @date: 2018/5/1
 */

public class GradeChooseDialog extends Dialog {
    private SeekBar columnSeekBar;
    private SeekBar rowSeekBar;
    private SeekBar minesSeekBar;
    private TextView columnTextView;
    private TextView rowTextView;
    private TextView minesTextView;
    private Button StartButton;
    private Button CancelButton;
    private int columns = 9;
    private int rows=9;
    private int mines=5;


    public GradeChooseDialog(@NonNull final Context context) {
        super(context);
        setContentView(R.layout.dialog_grade_choose);
        columnSeekBar=findViewById(R.id.seekbar_column);
        rowSeekBar=findViewById(R.id.seekbar_row);
        minesSeekBar=findViewById(R.id.seekbar_mines);
        columnTextView=findViewById(R.id.text_view_column);
        rowTextView=findViewById(R.id.text_view_row);
        minesTextView=findViewById(R.id.text_view_mines);
        StartButton=findViewById(R.id.dialog_grade_choose_start_button);
        CancelButton=findViewById(R.id.dialog_grade_choose_cancel_button);


        columnSeekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @SuppressLint("SetTextI18n")
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                columnTextView.setText("Value:" + Integer.toString(progress+9));
                columns=progress+9;
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });
        rowSeekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @SuppressLint("SetTextI18n")
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                rowTextView.setText("Value:" + Integer.toString(progress+9));
                rows=progress+9;
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });
        minesSeekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @SuppressLint("SetTextI18n")
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                minesTextView.setText("Value:" + Integer.toString(progress+5));
                mines=progress+5;
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });

        StartButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(getContext(), GameActivity.class);
                intent.putExtra("row",rows);
                intent.putExtra("column",columns);
                intent.putExtra("mines",mines);
                getContext().startActivity(intent);
                cancel();
            }
        });
        CancelButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                columns=0;
                cancel();
            }
        });
    }
}

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
    private TextView minesRangeTextView;
    private Button StartButton;
    private Button CancelButton;
    private int columns = 9;
    private int rows=9;
    private int maxMines=(int)(columns*rows*0.37);
    private int minMines=(int)(columns*rows*0.07);
    private int mines=minMines;



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
        minesRangeTextView=findViewById(R.id.dialog_grade_text_view_range_mines);
        minesRangeTextView.setText("mines["+String.valueOf(minMines)+":"+String.valueOf(maxMines)+"]:");


        columnSeekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @SuppressLint("SetTextI18n")
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                columnTextView.setText("Value:" + Integer.toString(progress+9));
                columns=progress+9;
                maxMines=(int)(columns*rows*0.37);
                minMines=(int)(columns*rows*0.07);
                minesSeekBar.setMax(maxMines-minMines);
                minesRangeTextView.setText("mines["+String.valueOf(minMines)+":"+String.valueOf(maxMines)+"]:");
                maxMines=(int)(columns*rows*0.37);
                minMines=(int)(columns*rows*0.07);
                minesRangeTextView.setText("mines["+String.valueOf(minMines)+":"+String.valueOf(maxMines)+"]:");
                mines=minMines;
                minesTextView.setText("Value:" + Integer.toString(mines));
                minesSeekBar.setProgress(0);
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
                maxMines=(int)(columns*rows*0.37);
                minMines=(int)(columns*rows*0.07);
                minesSeekBar.setMax(maxMines-minMines);
                minesRangeTextView.setText("mines["+String.valueOf(minMines)+":"+String.valueOf(maxMines)+"]:");
                maxMines=(int)(columns*rows*0.37);
                minMines=(int)(columns*rows*0.07);
                minesRangeTextView.setText("mines["+String.valueOf(minMines)+":"+String.valueOf(maxMines)+"]:");
                mines=minMines;
                minesTextView.setText("Value:" + Integer.toString(mines));
                minesSeekBar.setProgress(0);
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
                minesTextView.setText("Value:" + Integer.toString(progress+minMines));
                mines=progress+minMines;
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
                intent.putExtra("raw",rows);
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

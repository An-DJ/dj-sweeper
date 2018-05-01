package me.andj.djsweeper.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.GridLayout;
import me.andj.djsweeper.R;
import me.andj.djsweeper.dialog.GameResultDialog;
import me.andj.djsweeper.view.GameBlockButton;

/**
 * @program: GameActivity
 *
 * @description: The main class for game of mine sweeper.
 *
 * @author: AnDJ
 *
 * @date: 2018/5/1
 */

public class GameActivity extends AppCompatActivity implements View.OnClickListener,View.OnLongClickListener{
    private GridLayout gameAreaGridLayout;
    private Context mContext;
    private boolean [][] data;
    private int row=0;
    private int column=0;
    private int minesNum=0;
    private int unFindMines=0;
    private int unCheckedBlocks=0;
    private boolean isStarted=false;
    private Thread checkResultThread=null;
    private Thread flushTimeThread=null;
    private long startTime=0;
    private Button progressButton;
    private Button timeButton;
    private Button minesButton;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);
        mContext=this;
        initial();
        Intent intent=getIntent();
        row=intent.getIntExtra("row",10);
        column=intent.getIntExtra("column",10);
        minesNum=intent.getIntExtra("mines",10);

        startGame();
    }

    private void initial(){
        gameAreaGridLayout=(GridLayout)findViewById(R.id.game_area_grid_layout);
        progressButton=findViewById(R.id.activity_game_progress_button);
        timeButton=findViewById(R.id.activity_game_time_button);
        minesButton=findViewById(R.id.activity_game_mines_button);
    }

    public void startGame(){
        //stop the thread of checking the game result.
        if (checkResultThread!=null){
            //if (checkResultThread.isAlive())
            checkResultThread.interrupt();
            checkResultThread=null;
        }
        //stop the thread of flushing the time.
        if (flushTimeThread!=null){
            flushTimeThread.interrupt();
            flushTimeThread=null;
        }
        //clear grid layout.
        gameAreaGridLayout.removeAllViews();
        //give the base value for unFindMines and unCheckedBlocks.
        unFindMines=minesNum;
        unCheckedBlocks=row*column;

        //initialize the text of progress button and mines button.
        progressButton.setText(String.valueOf(unCheckedBlocks)+"/"+String.valueOf(unCheckedBlocks));
        minesButton.setText(String.valueOf(minesNum)+"/"+String.valueOf(minesNum));


        //create mines randomly.
        data=new boolean[row][column];
        int temp=0;
        while (temp!=minesNum){
            for(int i=0;i<row;i++){
                for (int j=0;j<column;j++){
                    if(temp==minesNum) break;
                    if(!data[i][j]){
                        data[i][j]=(int)(Math.random()*10)==0;
                        if (data[i][j])
                            temp++;
                    }
                }
            }
        }

        for (int i=0;i<row;i++){
            for(int j=0;j<column;j++){

                int surroundMines=0;
                if(!data[i][j])
                    for(int s=i-1;s<=i+1;s++){
                        for (int t=j-1;t<=j+1;t++){
                            if(s<0||s>row-1||t<0||t>column-1) continue;
                            if(data[s][t]) surroundMines++;
                        }
                    }
                else
                    surroundMines=9;
                GameBlockButton button=new GameBlockButton(this,i,j,surroundMines);
                button.setOnClickListener(this);
                button.setOnLongClickListener(this);
                gameAreaGridLayout.addView(button);
            }
        }

        //start check result thread waiting for success.
        checkResultThread=new Thread(new Runnable() {
            @Override
            public void run() {
                while (!Thread.interrupted()){
                    if(!isStarted) continue;
                    if(unCheckedBlocks==minesNum && isStarted){
                        isStarted=false;
                        runOnUiThread(new Runnable() {
                            public void run() {
                                GameResultDialog dialog=new GameResultDialog(mContext,true);
                                dialog.show();
                            }
                        });
                    }
                    Thread.interrupted();
                }
            }
        });
        checkResultThread.start();
        //start flush time thread.
        flushTimeThread=new Thread(new Runnable() {
            @Override
            public void run() {

                int last=(int)System.currentTimeMillis()/1000;
                while (!Thread.interrupted()){
                    if(!isStarted) continue;

                    if(System.currentTimeMillis()/1000-last>1){
                        runOnUiThread(new Runnable() {
                            public void run() {
                                Button button=findViewById(R.id.activity_game_time_button);
                                button.setText(String.valueOf(System.currentTimeMillis()/1000-startTime)+"s");
                            }
                        });
                        last=(int) System.currentTimeMillis()/1000;
                    }
                    try {
                        Thread.sleep(500);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                Thread.interrupted();
            }
        });
        flushTimeThread.start();
    }

    private void expandAll(){
        GameBlockButton button;
        for (int i=0;i<gameAreaGridLayout.getChildCount();i++){
            button=(GameBlockButton) gameAreaGridLayout.getChildAt(i);
            button.openBlock();
        }
    }

    @Override
    public boolean onLongClick(View view) {
        GameBlockButton button=(GameBlockButton)view;
        if(button.isFlag()){
            button.cleanFlag();
            //increase the un-find mines by one step and flush.
            unFindMines++;
            minesButton.setText(String.valueOf(unFindMines)+"/"+String.valueOf(minesNum));
        }

        else if (button.isUnchecked()){
            button.setFlag();
            //decrease the un-find mines by one step and flush.
            unFindMines--;
            minesButton.setText(String.valueOf(unFindMines)+"/"+String.valueOf(minesNum));
        }
        return true;
    }

    @Override
    public void onClick(View view) {
        GameBlockButton button=(GameBlockButton)view;
        if(!isStarted) {
            isStarted=true;
            startTime=System.currentTimeMillis()/1000;
        }

        if (!button.isClickEnable) return;
        if(button.isMine()){
            expandAll();

            isStarted=false;

            button.setBackground(getResources().getDrawable(R.drawable.wrong));

            GameResultDialog dialog=new GameResultDialog(mContext,false);
            dialog.show();
            return;
        }
        floodFill(button.getRow(),button.getColumn());
    }
    private void floodFill(int x, int y){
        if (x >= 0 && y >= 0 && x < row && y < column)
        {
            GameBlockButton button=(GameBlockButton) gameAreaGridLayout.getChildAt(x*column+y);
            if (button.isFlag()||button.isMine()||button.isOpened()) return;

            if(floodFillIt(button)){
                floodFill(x, y - 1);
                floodFill(x - 1, y);
                floodFill(x + 1, y);
                floodFill(x, y + 1);
                //8-direction flood fill
                floodFill(x-1,y-1);
                floodFill(x+1,y+1);
                floodFill(x-1,y+1);
                floodFill(x+1,y-1);
            }
        }
    }
    private boolean floodFillIt(GameBlockButton button){
        button.openBlock();
        //decrease the progress by one step and flush it.
        unCheckedBlocks--;
        progressButton.setText(String.valueOf(unCheckedBlocks)+"/"+String.valueOf(row*column));

        int x=button.getRow();
        int y=button.getColumn();

        for (int i=x-1;i<=x+1;i++){
            for (int j=y-1;j<=y+1;j++){
                if (i < 0 || j < 0 || i >= row || j >= column) continue;
                GameBlockButton tempbutton=(GameBlockButton) gameAreaGridLayout.getChildAt(i*column+j);
                if(tempbutton.isMine()||tempbutton.isFlag()){
                    return false;
                }
            }
        }

        return true;
    }

    @Override
    protected void onDestroy() {

        //stop the thread of checking the game result.
        if (checkResultThread!=null){
            //if (checkResultThread.isAlive())
            checkResultThread.interrupt();
            checkResultThread=null;
        }

        //stop the thread of flushing the time.
        if (flushTimeThread!=null){
            flushTimeThread.interrupt();
            flushTimeThread=null;
        }
        super.onDestroy();
    }
}

package me.andj.djsweeper.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import me.andj.djsweeper.R;

/**
 * @program: MoreActivity
 *
 * @description: The activity which shows more information about this app.
 *
 * @author: AnDJ
 *
 * @date: 2018/5/1
 */

public class MoreActivity extends AppCompatActivity implements View.OnClickListener{
    Button DJPuzleButton;
    Button developerButton;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_more);
        DJPuzleButton=findViewById(R.id.activity_more_dj_puzzle);
        developerButton=findViewById(R.id.activity_more_developer);
        DJPuzleButton.setOnClickListener(this);
        developerButton.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        Intent intent;
        switch (view.getId()){
            case R.id.activity_more_dj_puzzle:
                intent=new Intent(MoreActivity.this, WebviewActivity.class);
                intent.putExtra("website","https://www.coolapk.com/game/me.andj.djpuzzle");
                break;
            case R.id.activity_more_developer:
                intent=new Intent(MoreActivity.this, WebviewActivity.class);
                intent.putExtra("website","https://www.coolapk.com/u/1498861");
                break;
                default:
                    intent=new Intent(MoreActivity.this, WebviewActivity.class);
                    intent.putExtra("website","https://www.coolapk.com/u/1498861");
        }
        startActivity(intent);
    }
}

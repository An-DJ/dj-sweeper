package me.andj.djsweeper.activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import me.andj.djsweeper.R;
import me.andj.djsweeper.dialog.DifficultyDialog;

public class StartActivity extends AppCompatActivity implements View.OnClickListener{

    Button PlayButton;
    Button StatisticsButton;
    Button MoreButton;
    Button AboutButton;
    Button SettingButton;
    DifficultyDialog difficultyDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start);
        initialize();
    }

    private void initialize(){
        PlayButton=(findViewById(R.id.start_activity_play_button));
        StatisticsButton=(findViewById(R.id.start_activity_statistics_button));
        MoreButton=(findViewById(R.id.start_activity_more_button));
        AboutButton=(findViewById(R.id.start_activity_about_button));
        SettingButton=findViewById(R.id.start_activity_setting_button);
        PlayButton.setOnClickListener(this);
        StatisticsButton.setOnClickListener(this);
        MoreButton.setOnClickListener(this);
        AboutButton.setOnClickListener(this);
        SettingButton.setOnClickListener(this);

        difficultyDialog=new DifficultyDialog.Builder(this).create();
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.start_activity_play_button:
                difficultyDialog.show();
                break;
            case R.id.start_activity_statistics_button:
                Intent intent=new Intent(StartActivity.this,GameActivity.class);
                break;
            case R.id.start_activity_more_button:
                intent=new Intent(StartActivity.this,MoreActivity.class);
                startActivity(intent);
                break;
            case R.id.start_activity_about_button:
                intent=new Intent(StartActivity.this,AboutActivity.class);
                startActivity(intent);
                break;
            case R.id.start_activity_setting_button:
                intent = new Intent(StartActivity.this, SettingActivity.class);
                startActivity(intent);

                break;

                default:
        }
    }
}

package me.andj.djsweeper.activity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Vibrator;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.widget.CompoundButton;
import android.widget.Switch;

import me.andj.djsweeper.MyApplication;
import me.andj.djsweeper.R;
import me.andj.djsweeper.service.MusicService;

/**
 * @program: SettingActivity
 *
 * @description: The activity which offers the approaches about music and shake.
 *
 * @author: AnDJ
 *
 * @date: 2018/5/1
 */

public class SettingActivity extends AppCompatActivity {

    private Switch musicSwitch;
    private Switch shakeSwitch;
    private Vibrator vibrator;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setting);
        vibrator=(Vibrator) getSystemService(VIBRATOR_SERVICE);
        musicSwitch=findViewById(R.id.setting_activity_music_switch);
        shakeSwitch=findViewById(R.id.setting_activity_shake_switch);

        //SharedPreferences sharedPrefs = getSharedPreferences("setting", MODE_PRIVATE);
        musicSwitch.setChecked(MyApplication.musicAble);
        shakeSwitch.setChecked(MyApplication.shakeAble);

        shakeSwitch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                SharedPreferences sharedPrefs = getSharedPreferences("setting", MODE_PRIVATE);
                if(b){
                    vibrator.vibrate(50);
                    MyApplication.shakeAble=true;
                }

                else
                    MyApplication.shakeAble=false;
                SharedPreferences.Editor ed=sharedPrefs.edit();
                ed.putBoolean("shake",MyApplication.shakeAble);
                ed.commit();
            }
        });
        musicSwitch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                SharedPreferences sharedPrefs = getSharedPreferences("setting", MODE_PRIVATE);
                if(b){
                    if(!MyApplication.musicAble){
                        MyApplication.musicAble=true;
                        Intent intent = new Intent(getApplicationContext(),MusicService.class);
                        getApplication().startService(intent);
                    }
                }

                else{
                    if(MyApplication.musicAble){
                        MyApplication.musicAble=false;
                        Intent intent = new Intent(getApplicationContext(),MusicService.class);
                        getApplication().stopService(intent);
                    }
                }

                SharedPreferences.Editor ed=sharedPrefs.edit();
                ed.putBoolean("music",MyApplication.musicAble);
                ed.commit();
            }
        });
    }
}

package me.andj.djsweeper;

import android.app.Application;
import android.content.Intent;
import android.content.SharedPreferences;

import java.util.ArrayList;
import java.util.List;

import me.andj.djsweeper.database.DataBase;
import me.andj.djsweeper.database.bean.Recode;
import me.andj.djsweeper.service.MusicService;

/**
 * Created by AnDJ on 2018/5/1.
 */

public class MyApplication extends Application {
    public static boolean musicAble=false;
    public static boolean shakeAble=false;

    @Override
    public void onCreate() {
        super.onCreate();
        DataBase.initDataBase(this);
        setSetting();
        //insertToDataBase();
    }
    //test
    private void setSetting(){
        SharedPreferences sharedPrefs = getSharedPreferences("setting", MODE_PRIVATE);
        SharedPreferences.Editor ed;
        if(!sharedPrefs.contains("initialized")) {
            ed = sharedPrefs.edit();
            //Indicate that the default shared prefs have been set
            ed.putBoolean("initialized", true);

            ed.putBoolean("music", false);
            ed.putBoolean("shake", false);
            ed.commit();
        }
        musicAble=sharedPrefs.getBoolean("music",false);
        shakeAble=sharedPrefs.getBoolean("shake",false);
    }
    private void insertToDataBase(){
        List<Recode> list=new ArrayList<>();
//        list.add(new Recode(null,2016,3,8,15,10,8,4654,false));
//        list.add(new Recode(null,2016,3,9,13,9,54,4465,true));
//        list.add(new Recode(null,2016,4,3,14,14,321,4654,false));
//        list.add(new Recode(null,2016,4,1,8,10,125,1328,false));
//        list.add(new Recode(null,2016,4,4,9,12,15,78987,true));
//        list.add(new Recode(null,2016,7,20,10,10,213,12,true));
//        list.add(new Recode(null,2016,8,24,11,13,154,1231,false));
//        list.add(new Recode(null,2017,1,30,12,14,17,4565,true));
//        list.add(new Recode(null,2017,8,7,12,10,31,231,false));
//        list.add(new Recode(null,2017,9,8,14,10,241,545,true));
//        list.add(new Recode(null,2017,9,14,11,15,47,456,false));
//        list.add(new Recode(null,2017,10,4,10,15,78,3,true));
//        list.add(new Recode(null,2017,11,8,10,11,8,123,false));
//        list.add(new Recode(null,2018,3,4,10,9,9,48,true));
//        list.add(new Recode(null,2018,5,8,11,10,15,489,true));
//        list.add(new Recode(null,2018,8,24,11,10,151,483,true));
//        list.add(new Recode(null,2018,9,30,12,10,161,41,false));
//        list.add(new Recode(null,2018,8,18,14,11,14,40,false));
//        list.add(new Recode(null,2018,3,19,10,12,151,233,true));
//        list.add(new Recode(null,2018,8,20,13,14,156,40,false));
//        list.add(new Recode(null,2018,4,21,9,10,23,40,true));
//        list.add(new Recode(null,2018,10,22,15,10,331,486,false));
//        list.add(new Recode(null,2018,11,23,14,14,48,222,false));
//        list.add(new Recode(null,2018,1,22,9,10,8,40,true));
//        list.add(new Recode(null,2018,11,11,10,15,44,300,true));
        list.add(new Recode(null,2018,7,1,10,15,8,40,false));
        list.add(new Recode(null,2018,7,1,10,15,78,3,true));
        list.add(new Recode(null,2018,7,1,10,11,8,123,false));
        list.add(new Recode(null,2018,7,1,10,9,9,48,true));
        list.add(new Recode(null,2018,7,1,11,10,15,489,true));
        list.add(new Recode(null,2018,7,1,11,10,151,483,true));
        list.add(new Recode(null,2018,7,1,12,10,161,41,false));
        list.add(new Recode(null,2018,7,1,14,11,14,40,false));
        list.add(new Recode(null,2018,7,1,10,12,151,233,true));
        list.add(new Recode(null,2018,7,1,13,14,156,40,false));
        list.add(new Recode(null,2018,7,1,9,10,23,40,true));
        list.add(new Recode(null,2018,7,1,15,10,331,486,false));
        list.add(new Recode(null,2018,7,1,14,14,48,222,false));
        list.add(new Recode(null,2018,7,1,9,10,8,40,true));
        list.add(new Recode(null,2018,7,1,10,15,44,300,true));
        for (Recode r:list){
            DataBase.insertRecode(r);
        }
    }
}

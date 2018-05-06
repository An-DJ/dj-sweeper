package me.andj.djsweeper.database;

import android.content.Context;

import org.greenrobot.greendao.database.Database;

import java.util.List;

import me.andj.djsweeper.database.bean.Recode;
import me.andj.djsweeper.database.db.DaoMaster;
import me.andj.djsweeper.database.db.DaoSession;
import me.andj.djsweeper.database.db.RecodeDao;

/**
 * Created by AnDJ on 2018/5/5.
 */

public class DataBase {
    private static final String DATA_BASE_NAME="db_game_recode";
    private static DaoSession mDaoSession;
    private DataBase(){

    }
    public static void initDataBase(Context context){
        DaoMaster.DevOpenHelper openHelper=new DaoMaster.DevOpenHelper(context,DATA_BASE_NAME);
        Database database=openHelper.getWritableDb();
        DaoMaster daoMaster=new DaoMaster(database);
        mDaoSession=daoMaster.newSession();
    }
    public static DaoSession getDaoSession(){
        if (mDaoSession!=null){
            return mDaoSession;
        }else {
            throw new IllegalStateException("DaoSession not initialized");
        }
    }

    public static void insertRecode(Recode recode) {
        RecodeDao recodeDao = mDaoSession.getRecodeDao();
        recodeDao.insert(recode);
    }

    public static void deleteRecode(long id) {
        RecodeDao recodeDao = mDaoSession.getRecodeDao();
        recodeDao.deleteByKey(id);
    }

//    private void updateData(long id, int age) {
//        PlayerDao playerDao = mDaoSession.getPlayerDao();
//        Player player = playerDao.queryBuilder()
//                .where(PlayerDao.Properties.Id.eq(id))
//                .build()
//                .unique();
//        player.setAge(age);
//        playerDao.update(player);
//    }

    public static List<Recode> getAllData() {
        RecodeDao recodeDao = mDaoSession.getRecodeDao();
        return recodeDao.queryBuilder()
                .orderAsc(RecodeDao.Properties.Id)
                .build()
                .list();
    }
}

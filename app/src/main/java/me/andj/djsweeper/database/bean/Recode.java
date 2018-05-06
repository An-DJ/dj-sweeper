package me.andj.djsweeper.database.bean;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.NotNull;

import java.util.Date;
import org.greenrobot.greendao.annotation.Generated;

/**
 * Created by AnDJ on 2018/5/5.
 */
@Entity
public class Recode {
    @Id(autoincrement = true)
    Long id;

    @NotNull
    int year;

    @NotNull
    int month;

    @NotNull
    int day;

    @NotNull
    int column;

    @NotNull
    int row;

    @NotNull
    int mines;

    @NotNull
    int second;

    @NotNull
    boolean result;

    @Generated(hash = 1546335502)
    public Recode(Long id, int year, int month, int day, int column, int row,
            int mines, int second, boolean result) {
        this.id = id;
        this.year = year;
        this.month = month;
        this.day = day;
        this.column = column;
        this.row = row;
        this.mines = mines;
        this.second = second;
        this.result = result;
    }

    @Generated(hash = 149180596)
    public Recode() {
    }

    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public int getYear() {
        return this.year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public int getMonth() {
        return this.month;
    }

    public void setMonth(int month) {
        this.month = month;
    }

    public int getDay() {
        return this.day;
    }

    public void setDay(int day) {
        this.day = day;
    }

    public int getColumn() {
        return this.column;
    }

    public void setColumn(int column) {
        this.column = column;
    }

    public int getRow() {
        return this.row;
    }

    public void setRow(int row) {
        this.row = row;
    }

    public int getMines() {
        return this.mines;
    }

    public void setMines(int mines) {
        this.mines = mines;
    }

    public int getSecond() {
        return this.second;
    }

    public void setSecond(int second) {
        this.second = second;
    }

    public boolean getResult() {
        return this.result;
    }

    public void setResult(boolean result) {
        this.result = result;
    }

    
}

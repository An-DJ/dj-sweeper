package me.andj.djsweeper.activity;

import android.graphics.Color;
import android.graphics.Matrix;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.charts.RadarChart;
import com.github.mikephil.charting.components.Description;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.components.YAxis;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;
import com.github.mikephil.charting.data.PieData;
import com.github.mikephil.charting.data.PieDataSet;
import com.github.mikephil.charting.data.PieEntry;
import com.github.mikephil.charting.data.RadarData;
import com.github.mikephil.charting.data.RadarDataSet;
import com.github.mikephil.charting.data.RadarEntry;
import com.github.mikephil.charting.interfaces.datasets.ILineDataSet;
import com.github.mikephil.charting.utils.ColorTemplate;

import org.greenrobot.greendao.query.Query;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import me.andj.djsweeper.R;
import me.andj.djsweeper.database.DataBase;
import me.andj.djsweeper.database.bean.Recode;
import me.andj.djsweeper.database.db.RecodeDao;

/**
 * Created by AnDJ on 2018/5/5.
 */

public class StatisticsActivity extends AppCompatActivity {
    LineChart timePerDayLineChart;
    LineChart resultsPerMonthLineChart;
    BarChart cRMPerDayBarChart;
    PieChart gradePerMonthPieChart;
    RadarChart sweeperRadarChart;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_statistics);
        initialize();
        setTimePerDayLineChart();
        setcRMPerDayBarChart();
        setResultsPerDayLineChart();
        setGradePerMonthPieChart();
        setSweeperRadarChart();
    }
    private void initialize(){
        timePerDayLineChart=findViewById(R.id.time_per_day_line_chart);
        resultsPerMonthLineChart =findViewById(R.id.results_per_day_line_chart);
        cRMPerDayBarChart=findViewById(R.id.c_r_m_per_day_bar_chart);
        gradePerMonthPieChart=findViewById(R.id.grade_per_month_pie_chart);
        sweeperRadarChart=findViewById(R.id.sweeper_radar_chart);
    }
    private void setTimePerDayLineChart(){

        Calendar calendar=Calendar.getInstance();
        Query query = DataBase.getDaoSession().getRecodeDao().queryBuilder()
                .where(
                        RecodeDao.Properties.Year.eq(calendar.get(Calendar.YEAR)),
                        RecodeDao.Properties.Month.eq(calendar.get(Calendar.MONTH)+1),
                        RecodeDao.Properties.Day.eq(calendar.get(Calendar.DAY_OF_MONTH))
                ).build();

        List<Entry> valsComp = new ArrayList<Entry>();
        List dates = query.list();
        if(dates.size()==0) return;
        for (int i=0;i<dates.size();i++){
            Recode r=(Recode)dates.get(i);
            //Log.d("tag", r.getId().toString());
            valsComp.add(new Entry((float)i,(float)r.getSecond()));
        }

        LineDataSet setComp = new LineDataSet(valsComp, "seconds costs today");
        setComp.setAxisDependency(YAxis.AxisDependency.LEFT);

        // use the interface ILineDataSet
        List<ILineDataSet> dataSets = new ArrayList<ILineDataSet>();
        setComp.setDrawCircles(false);
        setComp.setCubicIntensity(0.6f);
        setComp.setDrawFilled(true);
        setComp.setFillColor(0xa5d6a7);
        //setComp.setMode(LineDataSet.Mode.CUBIC_BEZIER);
        dataSets.add(setComp);
        LineData data = new LineData(dataSets);
        timePerDayLineChart.setData(data);

        Description description=new Description();
        description.setText("#time costs today#");
        description.setTextSize(15f);
        timePerDayLineChart.setDescription(description);
        timePerDayLineChart.invalidate(); // refresh
    }
    private void setResultsPerDayLineChart() {
        Calendar calendar=Calendar.getInstance();
        Query query = DataBase.getDaoSession().getRecodeDao().queryBuilder()
                .where(
                        RecodeDao.Properties.Year.eq(calendar.get(Calendar.YEAR)),
                        RecodeDao.Properties.Month.eq(calendar.get(Calendar.MONTH)+1)
                ).build();
        List dates = query.list();
        if(dates.size()==0) return;

        int[] suc=new int[31];
        int[] fal=new int[31];
        for (int i=0;i<dates.size();i++){
            Recode r=(Recode)dates.get(i);
            if(r.getResult())
                suc[r.getDay()]++;
            else
                fal[r.getDay()]++;
        }
        List<Entry> valsComp = new ArrayList<Entry>(31);
        List<Entry> valsCompfal = new ArrayList<Entry>(31);
        for (int i=0;i<suc.length;i++){
            //Log.d("tag", r.getId().toString());
            valsComp.add(new Entry((float)i,(float)suc[i]));
            valsCompfal.add(new Entry(i,fal[i]));
        }

        LineDataSet setComp = new LineDataSet(valsComp, "sucs this month");
        LineDataSet setCompfal = new LineDataSet(valsCompfal, "fals this month");
        setCompfal.setColor(Color.rgb(244,67,54));
        setComp.setColor(Color.rgb(33,150,243));

        setComp.setAxisDependency(YAxis.AxisDependency.LEFT);
        setCompfal.setAxisDependency(YAxis.AxisDependency.LEFT);

        // use the interface ILineDataSet
        List<ILineDataSet> dataSets = new ArrayList<ILineDataSet>();
        setComp.setDrawCircles(false);
        setComp.setCubicIntensity(0.6f);
        setComp.setDrawFilled(true);
        setComp.setFillColor(0x90caf9);

        setCompfal.setDrawCircles(false);
        setCompfal.setCubicIntensity(0.6f);
        setCompfal.setDrawFilled(true);
        setCompfal.setFillColor(0xef9a9a);
        //setComp.setMode(LineDataSet.Mode.CUBIC_BEZIER);
        dataSets.add(setComp);
        dataSets.add(setCompfal);
        LineData data = new LineData(dataSets);
        resultsPerMonthLineChart.setData(data);
        Description description=new Description();
        description.setTextSize(15f);
        description.setText("#results this month#");
        resultsPerMonthLineChart.setDescription(description);
        resultsPerMonthLineChart.invalidate(); // refresh
    }

    private void setcRMPerDayBarChart() {
        Calendar calendar=Calendar.getInstance();
        Query query = DataBase.getDaoSession().getRecodeDao().queryBuilder()
                .where(
                        RecodeDao.Properties.Year.eq(calendar.get(Calendar.YEAR)),
                        RecodeDao.Properties.Month.eq(calendar.get(Calendar.MONTH)+1),
                        RecodeDao.Properties.Day.eq(calendar.get(Calendar.DAY_OF_MONTH))
                ).build();

        List datas = query.list();
        if(datas.size()==0) return;

        List<BarEntry> columnEntriesGroup = new ArrayList<>();
        List<BarEntry> rowEntriesGroup = new ArrayList<>();
        List<BarEntry> minesEntriesGroup=new ArrayList<>();


        int i = 0;
        for(; i < datas.size()&&i<10; i++) {
            Recode r=(Recode) datas.get(i);
            columnEntriesGroup.add(new BarEntry(i, r.getColumn()));
            rowEntriesGroup.add(new BarEntry(i,r.getRow()));
            minesEntriesGroup.add(new BarEntry(i,r.getMines()));
        }
        // set an empty value for guaranteeing right displayed right.
        columnEntriesGroup.add(new BarEntry(i,0));
        rowEntriesGroup.add(new BarEntry(i,0));
        minesEntriesGroup.add(new BarEntry(i,0));

        BarDataSet columnSet = new BarDataSet(columnEntriesGroup, "column set");
        BarDataSet rowSet = new BarDataSet(rowEntriesGroup, "row set");
        BarDataSet mineSet=new BarDataSet(minesEntriesGroup,"mine set");

        float groupSpace = 0.04f;
        float barSpace = 0.02f; // x2 dataset
        float barWidth = 0.3f; // x2 dataset
        // (0.02 + 0.45) * 2 + 0.06 = 1.00 -> interval per "group"

        columnSet.setColor(Color.rgb(76,175,80));
        rowSet.setColor(Color.rgb(255,152,0));
        mineSet.setColor(Color.rgb(244,67,54));

        BarData data = new BarData(columnSet,rowSet,mineSet);
        data.setBarWidth(barWidth); // set the width of each bar
        cRMPerDayBarChart.setData(data);
        cRMPerDayBarChart.groupBars(0, groupSpace, barSpace); // perform the "explicit" grouping
        XAxis xAxis = cRMPerDayBarChart.getXAxis();
        xAxis.setAxisMinimum(0);

        Matrix m=new Matrix();
        m.postScale(1.5f, 1f);//两个参数分别是x,y轴的缩放比例。例如：将x轴的数据放大为之前的1.5倍
        cRMPerDayBarChart.getViewPortHandler().refresh(m, cRMPerDayBarChart, false);//将图表动画显示之前进行缩放

        cRMPerDayBarChart.animateX(1000); // 立即执行的动画,x轴
        Description description=new Description();
        description.setTextSize(15f);
        description.setText("#details of games today#");
        cRMPerDayBarChart.setDescription(description);
        cRMPerDayBarChart.invalidate(); // refresh
    }

    private void setGradePerMonthPieChart() {
        Calendar calendar=Calendar.getInstance();
        Query query = DataBase.getDaoSession().getRecodeDao().queryBuilder()
                .where(
                        RecodeDao.Properties.Year.eq(calendar.get(Calendar.YEAR)),
                        RecodeDao.Properties.Month.eq(calendar.get(Calendar.MONTH)+1)
                ).build();

        List datas = query.list();
        if(datas.size()==0) return;
        int grade1=0,grade2=0,grade3=0,grade4=0;
        for (int i=0;i<datas.size();i++){
            Recode r=(Recode) datas.get(i);
            if (r.getMines()<100) grade1++;
            else if(r.getMines()<200) grade2++;
            else if(r.getMines()<300) grade3++;
            else grade4++;
        }
        List<PieEntry> entries = new ArrayList<>();

        int sum=grade1+grade2+grade3+grade4;
        entries.add(new PieEntry((float) grade1/sum*100, "5~100"));
        entries.add(new PieEntry((float)grade2/sum*100, "100~200"));
        entries.add(new PieEntry((float)grade3/sum*100, "200~300"));
        entries.add(new PieEntry((float)grade4/sum*100, ">300"));

        List<Integer> colors=new ArrayList<>();
        colors.add(Color.rgb(76,175,80));
        colors.add(Color.rgb(255,152,0));
        colors.add(Color.rgb(244,67,54));
        colors.add(Color.rgb(33,150,243));

        PieDataSet set = new PieDataSet(entries, "Mines number");
        set.setColors(colors);
        //set.setColors(0x4caf50);
        PieData data = new PieData(set);
        gradePerMonthPieChart.setData(data);
        Description description=new Description();
        description.setTextSize(15f);
        description.setText("#level this month#");
        gradePerMonthPieChart.setDescription(description);
        gradePerMonthPieChart.invalidate(); // refresh
    }

    private void setSweeperRadarChart() {
        Calendar calendar=Calendar.getInstance();
        Query query = DataBase.getDaoSession().getRecodeDao().queryBuilder()
                .where(
                        RecodeDao.Properties.Year.eq(calendar.get(Calendar.YEAR)),
                        RecodeDao.Properties.Month.eq(calendar.get(Calendar.MONTH)+1)
                ).build();

        List datas = query.list();
        if(datas.size()==0) return;

        List<RadarEntry> columnEntries=new ArrayList<>();
        List<RadarEntry> rowEntires=new ArrayList<>();

        int []columnGrade=new int[5];
        int []rowGrade=new int[5];
        for (int i=0;i<datas.size();i++){
            Recode recode=(Recode)datas.get(i);
            if(recode.getColumn()<13) columnGrade[0]++;
            else if (recode.getColumn()<18) columnGrade[1]++;
            else if(recode.getColumn()<23) columnGrade[2]++;
            else if(recode.getColumn()<27) columnGrade[3]++;
            else columnGrade[4]++;

            if(recode.getRow()<13) rowGrade[0]++;
            else if (recode.getRow()<18) rowGrade[1]++;
            else if(recode.getRow()<23) rowGrade[2]++;
            else if(recode.getRow()<27) rowGrade[3]++;
            else rowGrade[4]++;
        }
        for (int i=0;i<5;i++){
            columnEntries.add(new RadarEntry(columnGrade[i],i));
            rowEntires.add(new RadarEntry(rowGrade[i],i));
        }
        RadarDataSet columnsSet = new RadarDataSet(columnEntries, "columns prefer");
        RadarDataSet rowsSet=new RadarDataSet(rowEntires,"rows prefer");
        // 数据颜色设置
        columnsSet.setColor(ColorTemplate.VORDIPLOM_COLORS[0]);
        rowsSet.setColor(ColorTemplate.VORDIPLOM_COLORS[1]);
        // 实心填充区域颜色
        columnsSet.setFillColor(ColorTemplate.VORDIPLOM_COLORS[0]);
        rowsSet.setFillColor(ColorTemplate.VORDIPLOM_COLORS[1]);
        // 是否实心填充区域
        columnsSet.setDrawFilled(true);
        rowsSet.setDrawFilled(true);
        // 数据线条宽度
        columnsSet.setLineWidth(2f);
        rowsSet.setLineWidth(2f);

        RadarData data=new RadarData(columnsSet,rowsSet);
        sweeperRadarChart.setData(data);
        data.setDrawValues(true);
        Description description=new Description();
        description.setTextSize(15f);
        description.setText("#grade you prefer#");
        sweeperRadarChart.setDescription(description);
        sweeperRadarChart.invalidate();
    }

}

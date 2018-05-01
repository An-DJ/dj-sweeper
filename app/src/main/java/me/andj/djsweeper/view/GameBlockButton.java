package me.andj.djsweeper.view;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.view.View;
import android.widget.GridLayout;

import me.andj.djsweeper.R;
import me.andj.djsweeper.util.DpPxUtil;

/**
 * @program: GameBlockButton
 *
 * @description: the block(base widget) for mine sweeper.
 *
 * @author: AnDJ
 *
 * @date: 2018/5/1
 */

enum BlockStatus {
    IS_UNCHECKED,
    IS_OPENED,
    IS_FLAGD
}
public class GameBlockButton extends android.support.v7.widget.AppCompatButton{
    private final int marginDp=3;
    private final int blockWidthDp=40;
    private int backgroundWhich=-1;
    private BlockStatus status;
    public boolean isClickEnable=true;
    private int row=0;
    private int column=0;

    public GameBlockButton(Context context,int i,int j, int backgroundWhich) {
        super(context);
        initial(i,j,backgroundWhich);
    }
    private void initial(int i,int j,int backgroundWhich){
        this.backgroundWhich=backgroundWhich;
        this.status = BlockStatus.IS_UNCHECKED;

        row=i;
        column=j;
        GridLayout.LayoutParams param=new GridLayout.LayoutParams();
        param.columnSpec=GridLayout.spec(i,1);
        param.rowSpec=GridLayout.spec(j,1);
        setLayoutParams(param);
        param.width= DpPxUtil.dp2px(getContext(),blockWidthDp);
        param.height=DpPxUtil.dp2px(getContext(),blockWidthDp);
        param.leftMargin=DpPxUtil.dp2px(getContext(),marginDp);
        param.rightMargin=DpPxUtil.dp2px(getContext(),marginDp);
        param.topMargin=DpPxUtil.dp2px(getContext(),marginDp);
        param.bottomMargin=DpPxUtil.dp2px(getContext(),marginDp);
        setBackground(getResources().getDrawable(R.drawable.ic_launcher_background));
        //setBackground(getShouldBackground());

//        setOnLongClickListener(new OnLongClickListener() {
//            @Override
//            public boolean onLongClick(View view) {
//                if(getStatus()==BlockStatus.IS_FLAGD)
//                    cleanFlag();
//                else if (getStatus()==BlockStatus.IS_UNCHECKED)
//                    setFlag();
//                return true;
//            }
//        });
    }
    private Drawable getShouldBackground(){
        switch (backgroundWhich){
            case 0:
                return getResources().getDrawable(R.drawable.none);
            case 1:
                return getResources().getDrawable(R.drawable.num_1);
            case 2:
                return getResources().getDrawable(R.drawable.num_2);
            case 3:
                return getResources().getDrawable(R.drawable.num_3);
            case 4:
                return getResources().getDrawable(R.drawable.num_4);
            case 5:
                return getResources().getDrawable(R.drawable.num_5);
            case 6:
                return getResources().getDrawable(R.drawable.num_6);
            case 7:
                return getResources().getDrawable(R.drawable.num_7);
            case 8:
                return getResources().getDrawable(R.drawable.num_8);
            case 9:
                return getResources().getDrawable(R.drawable.mine);
            default:
                return getResources().getDrawable(R.drawable.none);
        }
    }
    public void setFlag(){
        setStatus(BlockStatus.IS_FLAGD);
        setBackground(getResources().getDrawable(R.drawable.flag));
        isClickEnable=false;
    }
    public void cleanFlag(){
        setStatus(BlockStatus.IS_UNCHECKED);
        setBackground(getResources().getDrawable(R.drawable.ic_launcher_background));
        isClickEnable=true;
    }

    public void setStatus(BlockStatus status){
        this.status = status;
    }
    public BlockStatus getStatus(){
        return status;
    }
    public void click(){
        if(!isClickEnable) return;
        setBackground(getShouldBackground());
        status=BlockStatus.IS_OPENED;
    }
    public void openBlock(){
        if(getStatus()==BlockStatus.IS_UNCHECKED){
            setStatus(BlockStatus.IS_OPENED);
            setBackground(getShouldBackground());
        }
    }

    public int getRow() {
        return row;
    }

    public int getColumn() {
        return column;
    }
    public boolean isOpened(){
        return status==BlockStatus.IS_OPENED;
    }
    public boolean isFlag(){
        return status==BlockStatus.IS_FLAGD;
    }
    public boolean isUnchecked(){
        return status==BlockStatus.IS_UNCHECKED;
    }
    public boolean isMine(){
        return backgroundWhich==9;
    }
}

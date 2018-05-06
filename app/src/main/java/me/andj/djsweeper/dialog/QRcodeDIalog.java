package me.andj.djsweeper.dialog;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.widget.ImageView;

import me.andj.djsweeper.R;

/**
 * Created by AnDJ on 2018/5/6.
 */

public class QRcodeDIalog extends Dialog {
    public QRcodeDIalog(@NonNull Context context,int resource) {
        super(context);
        setContentView(R.layout.dialog_qr_code);
        ImageView imageView=findViewById(R.id.dialog_qr_image_view);
        imageView.setImageDrawable(context.getResources().getDrawable(resource));
    }
}

package me.andj.djsweeper.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import me.andj.djsweeper.R;
import me.andj.djsweeper.dialog.QRcodeDIalog;

/**
 * @program: AboutActivity
 *
 * @description: The activity which shows about information.
 *
 * @author: AnDJ
 *
 * @date: 2018/5/1
 */

public class AboutActivity extends AppCompatActivity implements View.OnClickListener{
    Button facebookButton;
    Button twitterButton;
    Button qqButton;
    Button wechatButton;
    Button openGithubButton;
    Button openBlogButton;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about);
        initialize();
    }

    private void initialize(){
        facebookButton=findViewById(R.id.activity_about_facebook);
        twitterButton=findViewById(R.id.activity_about_twitter);
        qqButton=findViewById(R.id.activity_about_qq);
        wechatButton=findViewById(R.id.activity_about_wechat);
        openBlogButton=findViewById(R.id.activity_about_open_blog);
        openGithubButton=findViewById(R.id.activity_about_open_github);
        openGithubButton.setOnClickListener(this);
        openBlogButton.setOnClickListener(this);
        facebookButton.setOnClickListener(this);
        twitterButton.setOnClickListener(this);
        qqButton.setOnClickListener(this);
        wechatButton.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.activity_about_qq:
                QRcodeDIalog qRcodeDIalog=new QRcodeDIalog(this,R.drawable.qqcode);
                qRcodeDIalog.show();
                break;
            case R.id.activity_about_facebook:
                break;
            case R.id.activity_about_twitter:
                break;
            case R.id.activity_about_wechat:
                QRcodeDIalog qRcodeDIalog1=new QRcodeDIalog(this,R.mipmap.wechatcode);
                qRcodeDIalog1.show();
                break;
            case R.id.activity_about_open_github:
                Intent intent=new Intent(AboutActivity.this,WebviewActivity.class);
                intent.putExtra("website","https://github.com/An-DJ/DJSweeper");
                startActivity(intent);
                break;
            case R.id.activity_about_open_blog:
                intent=new Intent(AboutActivity.this,WebviewActivity.class);
                intent.putExtra("website","http://andj.me/");
                startActivity(intent);
                break;
                default:
                    break;
        }
    }
}

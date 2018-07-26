package com.example.administrator.ocrdemo;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.googlecode.tesseract.android.TessBaseAPI;

import java.io.File;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    static final String TESSBASE_PATH = Environment.getExternalStorageDirectory() + File.separator + "Download" + File.separator;

    //简体中文
    static final String CHINESE_LANGUAGE = "eng";
    private TessBaseAPI tessBaseAPI;
    private TextView tvDisplay;
    private ImageView ivImg;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        TextView tvBtn = findViewById(R.id.tv_btn);
        tvBtn.setOnClickListener(this);
        tvDisplay = findViewById(R.id.tv_display);
        ivImg = findViewById(R.id.iv_img);
        tessBaseAPI = new TessBaseAPI();

        tessBaseAPI.init(TESSBASE_PATH, CHINESE_LANGUAGE);



    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.tv_btn:
                Bitmap bitmap = BitmapFactory.decodeResource(this.getResources(), R.mipmap.demo);
                tessBaseAPI.setPageSegMode(TessBaseAPI.PageSegMode.PSM_AUTO);
                tessBaseAPI.setImage(bitmap);

                String result = tessBaseAPI.getUTF8Text();


                tvDisplay.setText("结果为：" + result);


                ivImg.setImageBitmap(bitmap);

                break;
        }
    }
}

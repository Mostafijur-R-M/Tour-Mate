package com.example.tourmate.mostafijur.tourmate;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class TakePhotoActivity extends AppCompatActivity {


    ImageView iv;
    Button b1;
    Bitmap b;
    int flag = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_take_photo);

        iv = (ImageView) findViewById(R.id.my_image);
        b1 = (Button) findViewById(R.id.capture);

        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (flag == 0){
                    Intent i = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                    startActivityForResult(i, 99);
                }else if (flag == 1){
                    savePhotoToMySdCard(b);
                    Toast.makeText(getApplicationContext(), "Photo Saved Successfully!", Toast.LENGTH_SHORT).show();
                    flag = 0;
                    b1.setText("Take Photo");
                }
            }
        });
    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if(requestCode == 99 && resultCode == RESULT_OK && data != null){

            b = (Bitmap) data.getExtras().get("data");
            iv.setImageBitmap(b);

            flag = 1;
            b1.setText("Save Photo");
        }
    }
    private void savePhotoToMySdCard(Bitmap bit){

        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd_HH_mm_ss");
        String pname = sdf.format(new Date());

        String root = Environment.getExternalStorageDirectory().toString();
        File folder = new File(root+"/Event_Memory");
        folder.mkdirs();



        File my_file = new File(folder, pname+" .png");
        try{
            FileOutputStream stream = new FileOutputStream(my_file);
            bit.compress(Bitmap.CompressFormat.PNG, 100, stream);
            stream.flush();
            stream.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

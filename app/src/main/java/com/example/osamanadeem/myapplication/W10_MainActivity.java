package com.example.osamanadeem.myapplication;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Environment;
import android.support.design.widget.Snackbar;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.concurrent.ExecutionException;

public class W10_MainActivity extends AppCompatActivity {

    int modi;
    EditText l1,l2,l3,l4,l5,l6;
    Button download,show;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_w10__main);
        init();
        set_value();
        show_img();
        download_img();
    }
    void set_value()
    {
        l1.setText("https://blogginghelper.files.wordpress.com/2014/07/blogger-favicon.png");
        l2.setText("https://www.shareicon.net/download/2015/08/24/89964_f119_384x512.png");
        l3.setText("https://cdn3.iconfinder.com/data/icons/emoticon-18/32/Wink_emoji_emoticon_emoticons_react_2-512.png");
        l4.setText("http://c1940652.r52.cf0.rackcdn.com/518aa307896ad848040006bc/Screen-Shot-2013-05-08-at-12.09.24-PM.png");
        l5.setText("https://www.softwarecrew.com/wp-content/uploads/2017/06/kkrieger.200.175.png");
        l6.setText("https://www.aggrix.com/images/users/Belt-36893.jpg");
    }
    void init()
    {
        l1 = findViewById(R.id.editText);
        l2 = findViewById(R.id.editText2);
        l3 = findViewById(R.id.editText3);
        l4 = findViewById(R.id.editText4);
        l5 = findViewById(R.id.editText5);
        l6 = findViewById(R.id.editText6);
        download = findViewById(R.id.dwnld);
        show = findViewById(R.id.show);
    }

    void show_img(){
        show.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(W10_MainActivity.this,W10_SecondActivity.class));
            }
        });
    }

    void download_img(){
        modi = 1;
        download.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
            DownloadWebContent obj = new DownloadWebContent();
            Bitmap bitmap = null;
                try {
                    bitmap = obj.execute(l2.getText().toString()).get();

                } catch (InterruptedException e) {
                    e.printStackTrace();
                } catch (ExecutionException e) {
                    e.printStackTrace();
                }
                /////DOWNLOAD IMAGE TO INTERNAL STORAGE
                if (ContextCompat.checkSelfPermission(W10_MainActivity.this,
                        Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED)
                {
                    ActivityCompat.requestPermissions(W10_MainActivity.this,new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE},10);
                    save_img(bitmap,modi);
                    modi++;
                }
                save_img(bitmap,modi);
                modi++;

            }
        });
    }

    class DownloadWebContent extends AsyncTask<String,Void,Bitmap> {
        ImageView imageView = findViewById(R.id.imageView);

        @Override
        protected Bitmap doInBackground(String... WebAddress) {
            URL url = null;
            int responseCode = -1;
            InputStream in;
            Bitmap bitmap = null;

            try {

                url = new URL(WebAddress[0]);

                HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
                httpURLConnection.setDoOutput(true);
                httpURLConnection.connect();
                responseCode = httpURLConnection.getResponseCode();
                if (responseCode == HttpURLConnection.HTTP_OK) {
                    in = httpURLConnection.getInputStream();
                    bitmap = BitmapFactory.decodeStream(in);
                    in.close();
                }

            } catch(MalformedURLException e){
                e.printStackTrace();
            } catch(IOException e){
                e.printStackTrace();
            }
            return bitmap;
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults)
    {
        switch (requestCode)
        {
            case 10:
                if (grantResults.length>0 && grantResults[0] == PackageManager.PERMISSION_GRANTED)
                {
                    Toast.makeText(this, "Your Permission is granted", Toast.LENGTH_SHORT).show();
                }
        }
    }

    void save_img(Bitmap webContent, int modifier){
        File file;
        String path = Environment.getExternalStorageDirectory().toString();
        path = path + "/UniqueFileName" + modifier + ".jpg";
        file = new File(path, "UniqueFileName" +modifier+".jpg");
        try{
            OutputStream stream = null;
            stream = new FileOutputStream(file);
            webContent.compress(Bitmap.CompressFormat.JPEG,100,stream);
            stream.close();

        }catch (IOException e) // Catch the exception
        {
            e.printStackTrace();

        }
        Uri savedImageURI = Uri.parse(file.getAbsolutePath());
    }



}

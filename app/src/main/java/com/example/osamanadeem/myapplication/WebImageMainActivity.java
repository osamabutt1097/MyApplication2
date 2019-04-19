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
import android.widget.ImageView;
import android.widget.Toast;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.concurrent.ExecutionException;

public class WebImageMainActivity extends AppCompatActivity {
    ImageView imageView, imageView1;
    Button btn;
    String str = "https://www.aggrix.com/images/users/Belt-36893.jpg";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_web_image_main);
        imageView = findViewById(R.id.imageView);
        btn = findViewById(R.id.dwnldimg);
    }

    public void fetch_img(View view) {
        Snackbar.make(view,"hello",Snackbar.LENGTH_LONG).show();
        DownloadWebContent obj = new DownloadWebContent();
        Bitmap webContent = null;
        try {
            String arr = str;
            webContent = obj.execute(arr).get();
            imageView.setImageBitmap(webContent);
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }

        if (ContextCompat.checkSelfPermission(this,
                Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED)
        {
            ActivityCompat.requestPermissions(this,new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE},10);
            save_img(webContent);
        }
        else
             Toast.makeText(this, "Permission already granted!", Toast.LENGTH_SHORT).show();
        save_img(webContent);
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

    void save_img(Bitmap webContent){
        File file;
        String path = Environment.getExternalStorageDirectory().toString();
        file = new File(path, "UniqueFileName1"+".jpg");
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

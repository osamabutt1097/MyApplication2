package com.example.osamanadeem.myapplication;

import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.concurrent.ExecutionException;

public class Web2MainActivity extends AppCompatActivity {



    class DownloadWebContent extends AsyncTask<String,Void,String>
    {

        @Override
        protected String doInBackground(String... WebAddress) {
            try {
                URL url = new URL( WebAddress[0]);
                HttpURLConnection connection = (HttpURLConnection) url.openConnection();
                connection.connect();
                InputStream inputStream = connection.getInputStream();
                InputStreamReader reader = new InputStreamReader(inputStream);
                int data = reader.read();
                String htmlContent="";
                while (data !=-1)
                {
                    char currentchar = (char) data;
                    htmlContent += currentchar;
                    data = reader.read();
                }
                return htmlContent;
            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
            return null;
        }
    }
    EditText editText;
    Button button;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_web2_main);
        editText = findViewById(R.id.editline);
        button = findViewById(R.id.clickbtn);
    }
    public void fetch_web_result(View view) {
    DownloadWebContent obj = new DownloadWebContent();
        try {
            String webContent = obj.execute("https://www.nu.edu.pk").get();
            editText.setText(webContent);
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
    }
}

package ateam.dialogue;

import android.content.Intent;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;

public class DetailedThreadActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detailed_thread);


        Bundle extras = getIntent().getExtras();
        String username = extras.getString("USERNAME");
        String title = extras.getString("TITLE");

        int threadid = extras.getInt("THREADID");

        String link = "http://10.168.79.15/getcontent.php?threadid=" + threadid;

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.replybutton);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent i = new Intent(getApplicationContext(), CreateThread.class);
                //i.putExtra("USERNAME", NAME);
                startActivity(i);
            }

        });

        try {

            HttpClient client = new DefaultHttpClient();
            HttpGet request = new HttpGet();
            request.setURI(new URI(link));

            HttpResponse response = client.execute(request);
            String json = EntityUtils.toString(response.getEntity());

            System.out.println("HTTP RESPONSE: " + json);

            JSONObject jsonResponse = new JSONObject(json.substring(1, json.length() - 1));

            String content = jsonResponse.getString("Content");


            TextView textView_title = (TextView) findViewById(R.id.textview_title);
            TextView textView_content = (TextView) findViewById(R.id.textview_content);
            TextView textView_author = (TextView) findViewById(R.id.textview_author);

            System.out.println("CONTENT: " + content);
            textView_title.setText(title);
            textView_content.setText(content);
            textView_author.setText(username);

        } catch (MalformedURLException murle) {
            System.out.println("Malformed URL detected.");
        } catch (URISyntaxException e) {
            e.printStackTrace();
        } catch (IOException ioe) {
            System.out.println("An IOException Occured.");
        } catch (JSONException jsone) {
            jsone.printStackTrace();
        }


    }
}

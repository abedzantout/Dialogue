package ateam.dialogue;

import android.app.Activity;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.InputFilter;
import android.text.TextWatcher;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;

public class CreateThread extends Activity{

    TextView charLimit;
    boolean current_is_backspace;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_thread);


        final EditText threadContent = (EditText) findViewById(R.id.threadContent);
        Button createThreadButton = (Button) findViewById(R.id.createThreadButton);
        final EditText titleField = (EditText) findViewById(R.id.titleField);

        titleField.setHint("Title");
        threadContent.setHint("Content");

        charLimit = (TextView) findViewById(R.id.charLimit);
        charLimit.setText("140");

        threadContent.setOnKeyListener(new View.OnKeyListener() {
            @Override
            public boolean onKey(View v, int keyCode, KeyEvent event) {

                if(keyCode == KeyEvent.KEYCODE_DEL){
                    current_is_backspace = true;
                }else{
                    current_is_backspace = false;
                }

                return false;
            }
        });

        threadContent.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {


                if(!current_is_backspace) {
                    int x = Integer.parseInt(charLimit.getText().toString());
                    x = x - 1;
                    charLimit.setText(String.valueOf(x));
                }else{

                    int x = Integer.parseInt(charLimit.getText().toString());
                    x = x + 1;
                    charLimit.setText(String.valueOf(x));

                }


            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });


        InputFilter[] fa= new InputFilter[1];
        fa[0] = new InputFilter.LengthFilter(140);
        threadContent.setFilters(fa);



        createThreadButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                System.out.println("CUM ON ME DADDY");

                Bundle extras = getIntent().getExtras();

                String username = extras.getString("USERNAME");
                String title = titleField.getText().toString();
                String content = threadContent.getText().toString();
                int numberOfLines = threadContent.getLineCount();

                title = title.replace(" ","%20");
                content = content.replace(" ", "%20");

                String link = "http://10.168.79.15/createthread.php?username=" + username + "&title=" + title + "&content=" + content + "&numoflines=" + numberOfLines;

                try {

                    HttpClient client = new DefaultHttpClient();
                    HttpGet request = new HttpGet();
                    request.setURI(new URI(link));

                    HttpResponse response = client.execute(request);
                    String json = EntityUtils.toString(response.getEntity());

                    System.out.println("HTTP RESPONSE: " + json);

                    if(json.equals("ssss")){

                        // successfully created thread

                        System.out.println("Thread created!");
                        Toast.makeText(CreateThread.this, "Your thread has been created!", Toast.LENGTH_LONG).show();

                    }




                } catch (MalformedURLException murle) {
                    System.out.println("Malformed URL detected.");
                } catch (URISyntaxException e) {
                    e.printStackTrace();
                } catch (IOException ioe) {
                    System.out.println("An IOException Occured.");
                }

            }
        });

    }



}

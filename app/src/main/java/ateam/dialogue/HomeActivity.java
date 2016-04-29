package ateam.dialogue;

import android.app.Activity;
import android.os.Bundle;
import android.os.StrictMode;
import android.support.v4.app.FragmentActivity;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.ArrayList;

public class HomeActivity extends Activity {


    public String[] convertToStringArray(String jsonData){
        String x = jsonData.substring(1, jsonData.length()-1);
        x = x.replace("\"", "");
        x = x.replace("TITLE", "");
        x = x.replace(":", "");
        x = x.replace("{", "");
        x = x.replace("}", "");
        System.out.println(x);
        String[] ret = x.split(",");
        return ret;
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        int SDK_INT = android.os.Build.VERSION.SDK_INT;
        if (SDK_INT > 8) {
            StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder()
                    .permitAll().build();
            StrictMode.setThreadPolicy(policy);
        }


        String link = "http://192.168.0.184/getthreads.php";

        String responseString = "";

        try {

            URL url = new URL(link);
            HttpClient client = new DefaultHttpClient();
            HttpGet request = new HttpGet();
            request.setURI(new URI(link));

            HttpResponse response = client.execute(request);
            BufferedReader in = new BufferedReader(new InputStreamReader(response.getEntity().getContent()));

            String line = "";

            while((line = in.readLine()) != null){
                responseString += line;
            }

            System.out.println("HTTP RESPONSE: "+responseString);

        /*
            responseString = responseString.substring(1, responseString.length()-1);

            JSONObject jsonResponse = null;

            try {
                 jsonResponse = new JSONObject(responseString);
                System.out.println(jsonResponse.getString("Title"));
            }catch(JSONException jsone){
                jsone.printStackTrace();
            }
            */


            ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, convertToStringArray(responseString));

            ListView threads = (ListView) findViewById(R.id.threadView);
            threads.setAdapter(adapter);



        }catch(MalformedURLException murle){
            System.out.println("Malformed URL detected.");
        } catch (URISyntaxException e) {
            e.printStackTrace();
        }catch(IOException ioe){
            System.out.println("An IOException occured.");
        }


    }


}

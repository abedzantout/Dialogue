package ateam.dialogue;

import android.app.Activity;
import android.os.Bundle;

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

public class ThreadActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_thread);

        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            int tid = extras.getInt("THREADID");
            String title = extras.getString("TITLE");

            String link = "http://192.168.0.184/getcontent.php?threadid=" + tid;

            try {

                URL url = new URL(link);
                HttpClient client = new DefaultHttpClient();
                HttpGet request = new HttpGet();
                request.setURI(new URI(link));

                HttpResponse response = client.execute(request);
                String json = EntityUtils.toString(response.getEntity());

                System.out.println("HTTP RESPONSE: " + json);

                JSONObject jsonResponse = new JSONObject(json.substring(1,json.length()-1));

                String content = jsonResponse.getString("Content");
                System.out.println("CONTENT: "+content);



            } catch (MalformedURLException murle) {
                murle.printStackTrace();
            } catch (IOException ioe) {
                ioe.printStackTrace();
            } catch (URISyntaxException urisyntax) {
                System.out.println("Check URI syntax");
            }catch(JSONException jsone){
                jsone.printStackTrace();
            }

        }
    }

}


















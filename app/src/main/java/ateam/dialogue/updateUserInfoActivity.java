package ateam.dialogue;

import android.os.StrictMode;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

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
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class updateUserInfoActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_user_info);

        // To allow networking on main thread
        int SDK_INT = android.os.Build.VERSION.SDK_INT;
        if (SDK_INT > 8) {
            StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder()
                    .permitAll().build();
            StrictMode.setThreadPolicy(policy);
        }



        final EditText usernameET = (EditText) findViewById(R.id.editText_username);
        final EditText passwordET = (EditText) findViewById(R.id.editText_password);
        final EditText emailET = (EditText) findViewById(R.id.editText_email);
        final EditText firstnameET = (EditText) findViewById(R.id.editText_FirstName);
        final EditText lastnameET = (EditText) findViewById(R.id.editText_LastName);
        TextView statusInfo = (TextView) findViewById(R.id.textView_BadgePoints);



        Button updateButton = (Button) findViewById(R.id.button_submit);

        String link = "http://10.168.79.15/getuserinfo.php?username=aaz15";


        try {

            HttpClient client = new DefaultHttpClient();
            HttpGet request = new HttpGet();
            request.setURI(new URI(link));

            HttpResponse response = client.execute(request);
            String json = EntityUtils.toString(response.getEntity());

            System.out.println("HTTP RESPONSE: " + json);

            JSONObject jsonResponse = new JSONObject(json.substring(1, json.length()-1));

            final String username = jsonResponse.getString("Username");
            final String password = jsonResponse.getString("Password");
            final String email = jsonResponse.getString("Email");
            final String firstname = jsonResponse.getString("Firstname");
            final String lastname = jsonResponse.getString("Lastname");
            final String badgetitle = jsonResponse.getString("BadgeTitle");
            final int points = jsonResponse.getInt("Points");

            usernameET.setText(username);
            passwordET.setText(password);
            emailET.setText(email);
            firstnameET.setText(firstname);
            lastnameET.setText(lastname);
            statusInfo.setText(badgetitle+"\n "+points);



            if(firstnameET.getText().equals("null")){firstnameET.setText("");}
            if(lastnameET.getText().equals("null")){lastnameET.setText("");}

            usernameET.setKeyListener(null);

            updateButton.setOnClickListener(new View.OnClickListener() {

                @Override
                public void onClick(View v) {

                    try {

                        String link = "http://10.168.79.15/updateuserinfo.php?oldusername="+username+"&username="+usernameET.getText().toString()+"&email="+emailET.getText().toString()+"&points="+points+"&password="+passwordET.getText().toString()+"&firstname="+firstnameET.getText().toString()+"&lastname="+lastnameET.getText().toString()+"&badgetitle="+badgetitle;


                        HttpClient client = new DefaultHttpClient();
                        HttpGet request = new HttpGet();
                        request.setURI(new URI(link));

                        HttpResponse response = client.execute(request);
                        String json = EntityUtils.toString(response.getEntity());

                        System.out.println("HTTP RESPONSE: " + json);

                        if(json.equals("s")){Toast.makeText(getApplicationContext(), "Information Updated", Toast.LENGTH_LONG).show();}else{Toast.makeText(getApplicationContext(), "Failed in updating information", Toast.LENGTH_LONG).show();}



                    }catch (MalformedURLException murle) {
                        System.out.println("Malformed URL detected.");
                    } catch (URISyntaxException e) {
                        e.printStackTrace();
                    } catch (IOException ioe) {
                        System.out.println("An IOException occurred.");
                    }


                }

            });



        } catch (MalformedURLException murle) {
            System.out.println("Malformed URL detected.");
        } catch (URISyntaxException e) {
            e.printStackTrace();
        } catch (IOException ioe) {
            System.out.println("An IOException occurred.");
        }catch(JSONException jsone){
            jsone.printStackTrace();
        }

    }


}

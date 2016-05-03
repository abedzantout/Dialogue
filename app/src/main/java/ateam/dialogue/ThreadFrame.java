package ateam.dialogue;

import android.app.Fragment;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;

public class ThreadFrame extends Fragment {

    public ThreadFrame(){}

    @Override
    public View onCreateView (LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        System.out.println("ENTERED VIEW");

        View rootView = inflater.inflate(R.layout.activity_thread, container, false);

            super.onCreate(savedInstanceState);

                String link = "http://192.168.0.184/getthreads.php";

                try {

                    URL url = new URL(link);
                    HttpClient client = new DefaultHttpClient();
                    HttpGet request = new HttpGet();
                    request.setURI(new URI(link));

                    HttpResponse response = client.execute(request);
                    String json = EntityUtils.toString(response.getEntity());


                    System.out.println("HTTP RESPONSE: " + json);

                    String[] jsonObjectsStringArray = json.substring(1, json.length()-1).split(",");

                    JSONObject[] objectArray = new JSONObject[jsonObjectsStringArray.length];

                    String[] titlesArray = new String[jsonObjectsStringArray.length];

                    for(int i=0;i<jsonObjectsStringArray.length;i++){

                        objectArray[i] = new JSONObject(jsonObjectsStringArray[i]);

                        String title = objectArray[i].getString("Title");

                        titlesArray[i] = title;
                        System.out.println("Title "+i+" is "+title);

                    }

                    RecyclerView recyclerView = (RecyclerView) rootView.findViewById(R.id.my_recycler_view);
                    recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));

                    int icons[] = {R.drawable.ic_search, R.drawable.ic_home, R.drawable.ic_user};

                    MyAdapter mAdapter = new MyAdapter(titlesArray, icons, "Abbas Baydoun", "abbasbaydoun717@gmail.com", 2, getActivity());

                    recyclerView.setAdapter(mAdapter);

                } catch (MalformedURLException murle) {
                    murle.printStackTrace();
                } catch (IOException ioe) {
                    ioe.printStackTrace();
                } catch (URISyntaxException urisyntax) {
                    System.out.println("Check URI syntax");
                }catch(JSONException jsone){
                    jsone.printStackTrace();
                }



        return rootView;
    }

}


















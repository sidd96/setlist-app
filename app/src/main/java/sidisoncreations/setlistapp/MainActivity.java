package sidisoncreations.setlistapp;

import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;

import org.json.JSONObject;

public class MainActivity extends AppCompatActivity {

    private static String SEARCH_API_URL = "http://api.setlist.fm/rest/0.1/search/artists.json?artistName=";
    private EditText artist;
    private JSONObject setlistSearchResults;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Caching the search query
        artist = (EditText) findViewById(R.id.artistName);

    }

    public void searchSetlist(View view) {
        String searchQuery = artist.getText().toString();
        String searchUrl = SEARCH_API_URL + searchQuery;
        SearchSetlist task = new SearchSetlist();
        task.execute(searchUrl);
    }


    private class SearchSetlist extends AsyncTask<String, Void, JSONObject> {

        @Override
        protected JSONObject doInBackground(String... searchUrl) {
            JSONObject searchResults = Json.getJSON(searchUrl[0]);
            return searchResults;
        }

        @Override
        protected void onPostExecute(JSONObject searchResults) {
            setlistSearchResults = searchResults;
            System.out.println(setlistSearchResults.toString());
        }
    }

}

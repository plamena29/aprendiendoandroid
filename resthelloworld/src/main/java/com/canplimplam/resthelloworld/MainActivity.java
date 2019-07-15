package com.canplimplam.resthelloworld;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import com.canplimplam.resthelloworld.modelo.Post;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {

    private TextView textViewResult;
    private JsonPlaceHolderApi jsonPlaceHolderApi;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        textViewResult = (TextView) findViewById(R.id.idResult);

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://jsonplaceholder.typicode.com/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        jsonPlaceHolderApi = retrofit.create(JsonPlaceHolderApi.class);

        getComments();

        Log.d("*********", "en Oncreate");

    }

    private void getComments(){
        Log.d("*********", "antes");
        Call<List<Post>> call = jsonPlaceHolderApi.getPosts();
        Log.d("*********", "despues");

        call.enqueue(new Callback<List<Post>>() {

            @Override
            public void onResponse(Call<List<Post>> call, Response<List<Post>> response) {
                Log.d("*********", "en Onresponse");
                if(!response.isSuccessful()){
                    textViewResult.setText("Code: " + response.code());
                    Log.d("*********", "Error");
                    return;
                }

                List<Post> posts = response.body();
                Log.d("*********", "obtenemos body");
                for(Post post: posts){
                    String content = "";
                    content += "ID: " + post.getId() + "\n";
                    content += "UserID: " + post.getUserId() + "\n";
                    content += "Title: " + post.getTitle() + "\n";
                    content += "Text: " + post.getText() + "\n\n";

                    textViewResult.append(content);
                    Log.d("*********", content);
                }
            }

            @Override
            public void onFailure(Call<List<Post>> call, Throwable t) {
                textViewResult.setText(t.getMessage());
            }
        });
    }
}

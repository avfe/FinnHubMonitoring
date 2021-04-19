package tech.fedorov.retrofit;


import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {
    TextView tv;
    Button btn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        tv = (TextView) findViewById(R.id.tv);
        btn = (Button) findViewById(R.id.button);
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://finnhub.io/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        FinnhubService service = retrofit.create(FinnhubService.class);
        Call<List<Symbol>> symbolsListCall = service.listSymbols();

        symbolsListCall.enqueue(new Callback<List<Symbol>>() {
            @Override
            public void onResponse(Call<List<Symbol>> call, Response<List<Symbol>> response) {
                List<Symbol> symbols = response.body();
                tv.setText(symbols.toString());
            }

            @Override
            public void onFailure(Call<List<Symbol>> call, Throwable t) {
                tv.setText("ERROR!");
            }
        });
    }
}
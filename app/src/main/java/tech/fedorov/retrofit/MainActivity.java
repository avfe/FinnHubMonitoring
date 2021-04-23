package tech.fedorov.retrofit;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
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

        ArrayList<Symbol> symbols = new ArrayList<>();


        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://finnhub.io/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        FinnhubService service = retrofit.create(FinnhubService.class);
        Call<List<Symbol>> symbolsListCall = service.listSymbols(getString(R.string.FinnhubToken), "US");
        // set up the RecyclerView
        RecyclerView recyclerView = findViewById(R.id.SymbolsList);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        SymbolsListAdapter adapter = new SymbolsListAdapter(this, symbols, service);
        recyclerView.setAdapter(adapter);
        symbolsListCall.enqueue(new Callback<List<Symbol>>() {
            @Override
            public void onResponse(Call<List<Symbol>> call, Response<List<Symbol>> response) {
                List<Symbol> smbols = response.body();

                for (Symbol s: smbols) {
                    symbols.add(new Symbol(s.getDescription(), s.getSymbol()));
                }
                adapter.updateStocks(symbols);
            }

            @Override
            public void onFailure(Call<List<Symbol>> call, Throwable t) {
                //tv.setText(t.getLocalizedMessage());
            }
        });
    }

    private boolean findInArray(String str, String[] strs) {
        for (int i = 0; i < strs.length; i++) {
            if (str.equals(strs[i])) {
                return true;
            }
        }
        return false;
    }

    private void writeObjectToPrivateFile(String filename, Object obj) {
        FileOutputStream fileOut = null;
        try {
            fileOut = getApplicationContext().openFileOutput(filename, Context.MODE_PRIVATE);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        ObjectOutputStream objectOut = null;
        try {
            objectOut = new ObjectOutputStream(fileOut);
        } catch (IOException e) {
            e.printStackTrace();
        }

        try {
            objectOut.writeObject(obj);
        } catch (IOException e) {
            e.printStackTrace();
        }

        try {
            objectOut.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private Object readObjectFromPrivateFile(String filename) {
        FileInputStream flis = null;
        try {
            flis = getApplicationContext().openFileInput(filename);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        ObjectInputStream objis = null;
        try {
            objis = new ObjectInputStream(flis);
        } catch (IOException e) {
            e.printStackTrace();
        }
        Object keyPair = null;
        try {
            keyPair = objis.readObject();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return keyPair;
    }
}
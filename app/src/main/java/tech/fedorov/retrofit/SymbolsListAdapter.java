package tech.fedorov.retrofit;
import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SymbolsListAdapter extends RecyclerView.Adapter<SymbolsListAdapter.ViewHolder>{

    private ArrayList<Symbol> mData;

    private LayoutInflater mInflater;

    private FinnhubService service;

    // data is passed into the constructor
    SymbolsListAdapter(Context context, ArrayList<Symbol> data, FinnhubService service) {
        this.mInflater = LayoutInflater.from(context);
        this.mData = data;
        this.service = service;
    }

    // inflates the row layout from xml when needed
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = mInflater.inflate(R.layout.symbol_item, parent, false);
        return new ViewHolder(view);
    }

    // binds the data to the TextView in each row
    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        Symbol sumbol = mData.get(position);
        String smb = sumbol.getSymbol();
        String dcrp = sumbol.getDescription();
        holder.symbol.setText(smb);
        holder.description.setText(dcrp);

        if (mData.get(position).price < 0) {
            holder.price.setText("...");
            Call<Price> priceCall = service.getPrice(sumbol.getSymbol(),"c1gdjqn48v6p69n8t8og");
            priceCall.enqueue(new Callback<Price>() {
                @Override
                public void onResponse(Call<Price> call, Response<Price> response) {
                    Price price =  response.body();
                    if (price != null) {
                        mData.get(position).setPrice(price.getCurrent());
                        holder.price.setText(String.valueOf(price.getCurrent()));
                    } else {
                        Log.d("Price", "NullPrice");
                        holder.price.setText("Failed");
                    }
                }

                @Override
                public void onFailure(Call<Price> call, Throwable t) {
                    Log.d("PriceCallDebug", t.getLocalizedMessage());
                    holder.price.setText("Failed");
                }

            });
        }
    }

    public void updateStocks(ArrayList<Symbol> mData) {
        this.mData = mData;
        notifyDataSetChanged();
    }

    // total number of rows
    @Override
    public int getItemCount() {
        return mData.size();
    }

    // stores and recycles views as they are scrolled off screen
    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView symbol;
        TextView description;
        TextView price;

        ViewHolder(View itemView) {
            super(itemView);
            symbol = itemView.findViewById(R.id.symbol);
            description = itemView.findViewById(R.id.description);
            price = itemView.findViewById(R.id.price);
        }
    }
}
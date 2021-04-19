package tech.fedorov.retrofit;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class SymbolsListAdapter extends RecyclerView.Adapter<SymbolsListAdapter.ViewHolder>{

    private ArrayList<Symbol> mData;

    private LayoutInflater mInflater;

    // data is passed into the constructor
    SymbolsListAdapter(Context context, ArrayList<Symbol> data) {
        this.mInflater = LayoutInflater.from(context);
        this.mData = data;
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

        ViewHolder(View itemView) {
            super(itemView);
            symbol = itemView.findViewById(R.id.symbol);
            description = itemView.findViewById(R.id.description);
        }
    }
}

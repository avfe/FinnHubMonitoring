package tech.fedorov.retrofit;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Symbol {

    @SerializedName("currency")
    @Expose
    public String currency;
    @SerializedName("description")
    @Expose
    public String description;
    @SerializedName("displaySymbol")
    @Expose
    public String displaySymbol;
    @SerializedName("figi")
    @Expose
    public String figi;
    @SerializedName("mic")
    @Expose
    public String mic;
    @SerializedName("symbol")
    @Expose
    public String symbol;
    @SerializedName("type")
    @Expose
    public String type;

    @Override
    public String toString() {
        return "Symbol{" +
                "currency='" + currency + '\'' +
                ", description='" + description + '\'' +
                ", displaySymbol='" + displaySymbol + '\'' +
                ", figi='" + figi + '\'' +
                ", mic='" + mic + '\'' +
                ", symbol='" + symbol + '\'' +
                ", type='" + type + '\'' +
                '}';
    }
}
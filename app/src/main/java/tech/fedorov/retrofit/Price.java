package tech.fedorov.retrofit;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Price {

    @SerializedName("c")
    @Expose
    public Float c;
    @SerializedName("h")
    @Expose
    public Float h;
    @SerializedName("l")
    @Expose
    public Float l;
    @SerializedName("o")
    @Expose
    public Float o;
    @SerializedName("pc")
    @Expose
    public Float pc;
    @SerializedName("t")
    @Expose
    public Integer t;

    public Float getCurrent() {
        return c;
    }
}
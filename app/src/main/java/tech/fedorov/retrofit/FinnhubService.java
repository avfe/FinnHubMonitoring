package tech.fedorov.retrofit;
import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface FinnhubService {
    @GET("api/v1/stock/symbol?exchange=US&token=c1gdjqn48v6p69n8t8og")
    Call<List<Symbol>> listSymbols();
}
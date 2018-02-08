package acdat.jroldan.json;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

/**
 * Created by usuario on 8/02/18.
 */

public interface ApiService {
    @GET("/users/{user}/repos")
    Call<List<Git>> reposForUser(@Path("user") String user);
}

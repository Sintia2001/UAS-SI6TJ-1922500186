package ac.id.atmaluhur.mhs.uassi6tj1922500186;

import java.util.List;
import java.util.Map;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.QueryMap;

public interface LecturerJsonPlaceHolderAPI {
    @GET("lecture.php")
    Call<List<LecturerPost>> getPosts(

    );
    @GET("lecture.php")
    Call<List<LecturerPost>> getPosts(@QueryMap Map<String, String> parameters);
}

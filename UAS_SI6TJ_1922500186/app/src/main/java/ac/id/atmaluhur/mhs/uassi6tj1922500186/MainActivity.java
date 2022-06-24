package ac.id.atmaluhur.mhs.uassi6tj1922500186;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {
    private TextView textViewResult;
    private FloatingActionButton btn_tambah;
    private LecturerJsonPlaceHolderAPI JsonPlaceHolderAPI;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        btn_tambah = findViewById(R.id.btn_tambah);
        btn_tambah.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View View) {
                Intent i = new Intent(MainActivity.this,
                        EdosenActivity.class);
                startActivity(i);
            }
        });
        textViewResult = findViewById(R.id.text_lecture_result);
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://192.168.129.79/UAS_1922500186/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        JsonPlaceHolderAPI = retrofit.create(LecturerJsonPlaceHolderAPI.class);
        getPosts();
    }

    private void getPosts() {
        Map<String, String> parameters = new HashMap<>();
        Call<List<LecturerPost>> call = JsonPlaceHolderAPI.getPosts();
        call.enqueue(new Callback<List<LecturerPost>>() {
            @Override
            public void onResponse(Call<List<LecturerPost>> call, Response<List<LecturerPost>> response) {
                if (!response.isSuccessful()) {
                    textViewResult.setText("code: " + response.code());
                    return;
                }
                List<LecturerPost> posts = response.body();
                for (LecturerPost post : posts) {
                    String content = "";
                    content += "NIDN: " + post.getNidn() + "\n";
                    content += "nama_dosen: " + post.getNama_dosen() + "\n";
                    content += "jabatan: " + post.getJabatan() + "\n";
                    content += "gol_pang: " + post.getGol_pang() + "\n";
                    content += "keahlian: " + post.getKeahlian() + "\n";
                    content += "program_studi: " + post.getProgram_studi() + "\n\n";
                    textViewResult.append(content);
                }
            }

            @Override
            public void onFailure(Call<List<LecturerPost>> call, Throwable t) {
                textViewResult.setText(t.getMessage());
            }
        });
    }
}
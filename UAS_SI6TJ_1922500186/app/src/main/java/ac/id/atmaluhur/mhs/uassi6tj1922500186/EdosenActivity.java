package ac.id.atmaluhur.mhs.uassi6tj1922500186;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.util.HashMap;

public class EdosenActivity extends AppCompatActivity {
    private EditText txtnidn ,txtnmdosen, txtjabatan, txtgolpang, txtkeahlian, txtpordi;
    private Button Dashboard;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edosen);
        Dashboard = findViewById(R.id.Dashboard);
        Dashboard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View View) {
                Intent i = new Intent(EdosenActivity.this,
                        MainActivity.class);
                startActivity(i);
            }
        });

        txtnidn = (EditText) findViewById(R.id.et_nidn);
        txtnmdosen = (EditText) findViewById(R.id.et_nama_dosen);
        txtjabatan = (EditText) findViewById(R.id.et_jabatan);
        txtgolpang = (EditText) findViewById(R.id.et_golpang);
        txtkeahlian = (EditText) findViewById(R.id.et_keahlian);
        txtpordi = (EditText) findViewById(R.id.et_pordi);
    }

    public void tambahlecture(View View) {
        final String nidn = txtnidn.getText().toString().trim();
        final String nama_dosen = txtnmdosen.getText().toString().trim();
        final String jabatan = txtjabatan.getText().toString().trim();
        final String golpang = txtgolpang.getText().toString().trim();
        final String keahlian = txtkeahlian.getText().toString().trim();
        final String pordi = txtpordi.getText().toString().trim();

        class tambahlecture extends AsyncTask<Void, Void, String> {
            ProgressDialog load;

            @Override
            protected void onPreExecute() {
                super.onPreExecute();
                load = ProgressDialog.show(
                        EdosenActivity.this, "Add...", "Wait..",
                        false, false);
            }

            @Override
            protected String doInBackground(Void... v) {
                HashMap<String,String> params = new HashMap<>();
                params.put("nidn", nidn);
                params.put("nama_dosen", nama_dosen);
                params.put("jabatan", jabatan);
                params.put("gol_pang", golpang);
                params.put("keahlian", keahlian);
                params.put("program_studi", pordi);

                RequestHandler rh = new RequestHandler();
                String res = rh.sendPostRequest("http://192.168.129.79/UAS_1922500186/tambah_lecture.php", params);
                return res;
            }

            @Override
            protected void onPostExecute(String s) {
                super.onPostExecute(s);
                load.dismiss();
                Toast.makeText(EdosenActivity.this, s, Toast.LENGTH_LONG).show();
            }
        }
        tambahlecture tb = new tambahlecture();
        tb.execute();
    }
}

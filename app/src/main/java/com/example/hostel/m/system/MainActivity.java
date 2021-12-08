package com.example.hostel.m.system;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class MainActivity extends AppCompatActivity {
    EditText Name, Date, Block, RoomNo,Residence,Disability,Period;
    Button Save;

    //Request Queue
    RequestQueue queue;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Name = (EditText) findViewById(R.id.Name);
        Date = (EditText) findViewById(R.id.Date);
        Block = (EditText) findViewById(R.id.Blocks);
        RoomNo = (EditText) findViewById(R.id.Room);
        Residence = (EditText) findViewById(R.id.Reside);
        Disability = (EditText) findViewById(R.id.disable);
        Period = (EditText) findViewById(R.id.period);
        Save = (Button) findViewById(R.id.Save);

        queue = Volley.newRequestQueue(this);
    }
    public void SaveData(View view) {
        String name = Name.getText().toString();
        String date=Date.getText().toString();
        String block = Block.getText().toString();
        int room = Integer.parseInt(RoomNo.getText().toString());
        String residence = Residence.getText().toString();
        String disability = Disability.getText().toString();
        int period = Integer.parseInt(Period.getText().toString());

        final Map data = new HashMap();

        data.put("name", name);
        data.put("date", date);
        data.put("blocks", block);
        data.put("room", room);
        data.put("residence", residence);
        data.put("disability", disability);
        data.put("period", period);
        Name.setText(" ");
        Date.setText(" ");
        Block.setText(" ");
        RoomNo.setText(" ");
        Residence.setText(" ");
        Disability.setText(" ");
        Period.setText(" ");
        System.out.println(data);

        String url = "http://192.168.43.10:3030/Hostel_M_System/api/orders";
        JsonObjectRequest request = new JsonObjectRequest(Request.Method.POST, url, new JSONObject(data), new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                Toast.makeText(MainActivity.this, "Data Submited", Toast.LENGTH_SHORT).show();
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                error.printStackTrace();
            }
        });
        queue.add(request);
    }
}
package com.example.aula13;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Color;
import android.os.Bundle;
import android.widget.Toast;

import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

import br.utils.Utils;
import cz.msebera.android.httpclient.Header;
import lecho.lib.hellocharts.gesture.ZoomType;
import lecho.lib.hellocharts.listener.LineChartOnValueSelectListener;
import lecho.lib.hellocharts.model.Axis;
import lecho.lib.hellocharts.model.Line;
import lecho.lib.hellocharts.model.LineChartData;
import lecho.lib.hellocharts.model.PointValue;
import lecho.lib.hellocharts.model.Viewport;
import lecho.lib.hellocharts.view.LineChartView;

public class ExercicioActivity extends AppCompatActivity {

    LineChartView chart;

    /*
    private ArrayList<PointValue> mPointValues = new ArrayList<PointValue>();
    private ArrayList<AxisValue> mAxisXValues = new ArrayList<AxisValue>();*/

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_exercicio);

        chart = findViewById(R.id.chart);
        chart.setOnValueTouchListener(new TouchListener());

        AsyncHttpClient client = new AsyncHttpClient();
        client.get(Utils.enderecoWeb(), new AsyncHttpResponseHandler() {
            @Override
            public void onSuccess(int statusCode, Header[] headers, byte[] response) {
                String data = new String(response);
                ArrayList<Line> listaLinhas = new ArrayList<>();
                ArrayList<PointValue> listaTemp = new ArrayList<>();
                try {
                    JSONObject obj = new JSONObject(data);
                    JSONArray array = obj.getJSONArray("weather");
                    for(int i=0; i<array.length(); i++) {
                        JSONObject json = array.getJSONObject(i);
                        String temperatura = json.get("temperature").toString();
                        listaTemp.add(new PointValue((i+1), Float.parseFloat(temperatura)));
                    }
                    listaLinhas.add(new Line(listaTemp).setColor(Color.BLACK).setCubic(true));
                }
                catch (JSONException e) {
                    e.printStackTrace();
                }
                chart.setInteractive(true); // set the chart is interactive (drag, zoom and other effects premise)
                chart.setZoomType(ZoomType.HORIZONTAL_AND_VERTICAL);//Set the zoom direction
                LineChartData lineData = new LineChartData();
                Axis axisX = new Axis();//x axis
                axisX.setTextColor(Color.BLACK);
                axisX.setName("Dia");
                Axis axisY = new Axis();//y axis
                axisY.setName("Temperatura");
                axisY.setTextColor(Color.BLACK);
                lineData.setAxisXBottom(axisX);
                lineData.setAxisYLeft(axisY);
                lineData.setLines(listaLinhas);
                chart.setLineChartData(lineData);

            }

            @Override
            public void onFailure(int statusCode, Header[] headers, byte[] errorResponse, Throwable e) {
                Toast.makeText(getApplicationContext(), e.getMessage(), Toast.LENGTH_SHORT);
            }
        });
    }

    private class TouchListener implements LineChartOnValueSelectListener {
        @Override
        public void onValueSelected(int i, int i1, PointValue pointValue) {
            String str = String.format("Temperatura: %.1fºC | Dia: %.0f", pointValue.getY(), pointValue.getX());
            Toast.makeText(getApplicationContext(), str, Toast.LENGTH_SHORT).show();;
        }

        @Override
        public void onValueDeselected() {

        }
    }
}

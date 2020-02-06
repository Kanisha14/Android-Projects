package ca.yorku.eecs.mcalcpro;

import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.speech.tts.TextToSpeech;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;



import java.util.Locale;

import ca.roumani.i2c.MPro;

public class MCalcProActivity extends AppCompatActivity implements TextToSpeech.OnInitListener{

    private TextToSpeech tts;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.mcalcpro_layout);
        this.tts = new TextToSpeech(this, this);
        SensorManager sm = (SensorManager) getSystemService(SENSOR_SERVICE);
        sm.registerListener((SensorEventListener) this, sm.getDefaultSensor(Sensor.TYPE_ACCELEROMETER), SensorManager.SENSOR_DELAY_NORMAL);

    }

    public void onInit(int initStatus){

        this.tts.setLanguage(Locale.US);
    }

    public void onAccuracyChanged(Sensor arg0, int arg1){

    }

    public void onSensorChanged(SensorEvent event){

        double ax = event.values[0];
        double ay = event.values[1];
        double az = event.values[2];
        double a = Math.sqrt(ax * ax + ay * ay + az * az);
        if (a > 20){
            ((EditText) findViewById(R.id.pbox)).setText("");
            ((EditText) findViewById(R.id.abox)).setText("");
            ((EditText) findViewById(R.id.ibox)).setText("");
            ((TextView) findViewById(R.id.output)).setText("");

        }
    }


    public void onClick(View v){
        MPro mp = new MPro();

        try{
            mp.setPrinciple(((EditText) findViewById(R.id.pbox)).getText().toString());
            mp.setAmortization(((EditText) findViewById(R.id.abox)).getText().toString());
            mp.setInterest(((EditText) findViewById(R.id.ibox)).getText().toString());
            System.out.println(mp.computePayment("%,.2f"));
            System.out.println(mp.outstandingAfter(2, "%,16.0f"));

            String s = "Monthly Payment = " + mp.computePayment("%,.2f");
            s += "\n\n";
            s += "By making this monthly for 20 years, the mortgage will be paid in full. But if you terminate the mortgage on its nth anniversary, the balance still owing depends on n as shown below:";

            int y = 0;
            for (;y < 10; y++){
                s += String.format("%8d", y) + mp.outstandingAfter(y, "%,16.0f");
                s += "\n\n";
            }

            for (y=10 ; y < 21; y += 5){
                s += String.format("%8d", y) + mp.outstandingAfter(y, "%,16.0f");
                s += "\n\n";
            }

            ((TextView) findViewById(R.id.output)).setText(s);
            tts.speak(s, TextToSpeech.QUEUE_FLUSH, null);
        }
        catch (Exception e){

            Toast label = Toast.makeText(this, e.getMessage(), Toast.LENGTH_SHORT);
            label.show();
        }

    }

}

package archana.manjunath.optionscalculator;

import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.TextView;

import org.apache.commons.math3.distribution.NormalDistribution;


public class MainActivity extends ActionBarActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
      Log.v("main","In main thread");
    }

    public void Calculate(View view)
    {
        Log.v("Clicked","here safely");
        TextView result = (TextView) findViewById(R.id.result);
        try {
            EditText e = (EditText) findViewById(R.id.volatility);
            float v = Float.parseFloat(e.getText().toString());
            Log.v("Vol", "v" + v);
            e = (EditText) findViewById(R.id.rate);
            float r = Float.parseFloat(e.getText().toString()) / 100;
            Log.v("Vol", "r" + v);
            e = (EditText) findViewById(R.id.strike);
            float K = Float.parseFloat(e.getText().toString());
            Log.v("Vol", "K" + K);
            e = (EditText) findViewById(R.id.stock);
            float S = Float.parseFloat(e.getText().toString());
            Log.v("Vol", "S" + S);
            e = (EditText) findViewById(R.id.dividend);
            float d = Float.parseFloat(e.getText().toString());
            Log.v("Vol", "D" + d);
            Spinner time = (Spinner) findViewById(R.id.spinner);
            e = (EditText) findViewById(R.id.time);
            float n = Float.parseFloat(e.getText().toString());
            float t = time.getSelectedItemPosition() == 0 ? n : time.getSelectedItemPosition() == 1 ? n / 12 : n / 365;
            Log.v("Vol", "T" + t);
            RadioGroup radioButtonGroup = (RadioGroup) findViewById(R.id.rg);
            int radioButtonID = radioButtonGroup.getCheckedRadioButtonId();
            View radioButton = radioButtonGroup.findViewById(radioButtonID);
            int idx = radioButtonGroup.indexOfChild(radioButton);
            Log.v("Index", "it is " + idx);
            float price = 0;

            double d1 = (Math.log(S / K) + (r - d + 0.5 * v * v) * t) / (v * Math.sqrt(t));
            double d2 = d1 - v * Math.sqrt(t);
            NormalDistribution x = new NormalDistribution();
            if (idx == 0)
                price = (float) (K * Math.exp(-1 * r * t) * x.cumulativeProbability(-1 * d2) - S * Math.exp(-1 * d * t) * x.cumulativeProbability(-1 * d1));
            else
                price = (float) (S * Math.exp(-1 * d * t) * x.cumulativeProbability(d1) - K * Math.exp(-1 * r * t) * x.cumulativeProbability(d2));
            result.setText(Float.toString(price));
        }
        catch(Exception e)
        {
            result.setTextSize(14);
            result.setText("Some values may be missing or in incorrect format");
        }

    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}

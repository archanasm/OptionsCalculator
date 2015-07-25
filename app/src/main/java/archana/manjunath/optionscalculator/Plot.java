package archana.manjunath.optionscalculator;

import android.content.Intent;
import android.graphics.Color;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;

import com.androidplot.xy.LineAndPointFormatter;
import com.androidplot.xy.PointLabelFormatter;
import com.androidplot.xy.SimpleXYSeries;
import com.androidplot.xy.XYPlot;
import com.androidplot.xy.XYSeries;
import com.androidplot.xy.XYStepMode;


import java.util.ArrayList;


public class Plot extends ActionBarActivity {
    private XYPlot plot;
    private ArrayList<Option> portfolio;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_plot);
       // plot = (XYPlot) findViewById(R.id.mySimpleXYPlot);
        plot = (XYPlot) findViewById(R.id.mySimpleXYPlot);
        Intent intent = getIntent();
        float f ,t ,shares, price;
        f = intent.getFloatExtra("from",-1);
        t = intent.getFloatExtra("to",-1);
        boolean s  = intent.getBooleanExtra("Stock",false);
        if(s)
        {
          shares = intent.getFloatExtra("Stock_shares",-1);
          price = intent.getFloatExtra("Stock_price",-1);

        }
        else
        {
            shares = 0f;
            price = 0f;
        }

        portfolio = intent.getParcelableArrayListExtra("portfolio");
        ArrayList<Float> stock = new ArrayList<>();
        ArrayList<Float> payoff = new ArrayList<>();
        int j = 0;
        int a = intent.getIntExtra("choice",0);
        if(a==0) {
            for (int i = 0; i < 21; ++i) {
                stock.add(i, f + i * (t - f) / 20);
                payoff.add(0f);
                while (j < portfolio.size()) {
                    //  Log.e("WHILE LOOP", "value"+ j);
                    //   Log.e("payoff","payoff "+ i + portfolio.get(j).Payoff(f + i * (t - f) / 20));
                    payoff.set(i, payoff.get(i) + portfolio.get(j).Payoff(f + i * (t - f) / 20) + shares * (f + i * (t - f) / 20));
                    j = j + 1;
                }
                j = 0;
            }
        }
        else {
            for (int i = 0; i < 21; ++i) {
                stock.add(i, f + i * (t - f) / 20);
                payoff.add(0f);
                while (j < portfolio.size()) {
                    //  Log.e("WHILE LOOP", "value"+ j);
                    //   Log.e("payoff","payoff "+ i + portfolio.get(j).Payoff(f + i * (t - f) / 20));
                    payoff.set(i, payoff.get(i) + portfolio.get(j).Payoff(f + i * (t - f) / 20) + shares * (f + i * (t - f) / 20)-portfolio.get(j).getPremium());
                    j = j + 1;
                }
                j = 0;
            }

        }
        Log.e("SIZE","x "+ stock.size() + " y"+ payoff.size());
        XYSeries x_axis = new SimpleXYSeries(stock, payoff , "Stock");
         plot.setDomainStep(XYStepMode.INCREMENT_BY_VAL, (t-f)/20);
        // XYSeries y_axis = new SimpleXYSeries(payoff, SimpleXYSeries.ArrayFormat.Y_VALS_ONLY, "Payoff");
        LineAndPointFormatter series1Format = new LineAndPointFormatter();
        series1Format.setPointLabelFormatter(new PointLabelFormatter());
        series1Format.configure(getApplicationContext(),
                R.xml.line_point_formatter_with_plf1);

        // add a new series' to the xyplot:
        plot.addSeries(x_axis, series1Format);

        // same as above:
        LineAndPointFormatter series2Format = new LineAndPointFormatter();
        series2Format.setPointLabelFormatter(new PointLabelFormatter());
        series2Format.configure(getApplicationContext(),
                R.xml.line_point_formatter_with_plf2);
       // LineAndPointFormatter series1Format = new LineAndPointFormatter(Color.RED, Color.GREEN, Color.BLUE, null);

       // plot.addSeries(y_axis, series2Format);

        // reduce the number of range labels
        plot.setTicksPerRangeLabel(3);
        plot.getGraphWidget().setDomainLabelOrientation(-45);

    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_plot, menu);
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

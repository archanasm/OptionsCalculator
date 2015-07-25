package archana.manjunath.optionscalculator;

import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;

import java.util.ArrayList;

public class OptionsPortfolio extends ActionBarActivity  {
   private ArrayList<Option> portfolio;
   EditText Number, Strike, Premium,From,To ;
    Float n,d,p,f,t;
    int a ;
    Intent i;
    Boolean stock = false;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_options_portfolio);
       // d = 0f ;
        //n = 0f;
        //p = 0f;
        i = new Intent(OptionsPortfolio.this,Plot.class);
        f = 0f;
        t = 0f;
        ListView yourListView = (ListView) findViewById(R.id.list);
        portfolio = new ArrayList<>();
        myAdapter customAdapter = new myAdapter(this, R.layout.list_row,portfolio);
        yourListView.setAdapter(customAdapter);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_options_portfolio, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        Log.e("app", "in Menu");
        //noinspection SimplifiableIfStatement
        if (id == R.id.add_option) {
            //  showOptionDialog();
          add_option();
            return true;
        }


        else if (id == R.id.add_stock)
        {
          add_stock();
          return true;
        }

        else if(id==R.id.plot_graph)
        {
            a = 0;
            plot(a);
            return true;
        }
        else if (id==R.id.profit_diagram)
        {   a = 1;
            plot(1);
            return true;
        }

        return super.onOptionsItemSelected(item);

    }

    public void add_stock()
    {
     final Dialog dialog = new Dialog(this);
     dialog.setContentView(R.layout.add_stock);
     dialog.setTitle("Add Stock");
     DisplayMetrics metrics = new DisplayMetrics();
     getWindowManager().getDefaultDisplay().getMetrics(metrics);
     int width1 = metrics.widthPixels;
     //    int height1 = metrics.heightPixels;
     dialog.getWindow().setLayout(width1, ViewGroup.LayoutParams.WRAP_CONTENT);
     // set the custom dialog components - text, image and button


     Number = (EditText) dialog.findViewById(R.id.shares);
     Premium = (EditText) dialog.findViewById(R.id.price);
     Button b = (Button) dialog.findViewById(R.id.add_S);

     // if button is clicked, close the custom dialog
     b.setOnClickListener(new View.OnClickListener() {
         @Override
         public void onClick(View v) {
             float n,p;
             try{
              n = Float.parseFloat(Number.getText().toString());
              p = Float.parseFloat(Premium.getText().toString());
             }
             catch(Exception e)
             {
                  n = 0f;
                  p = 0f;

             }
             Log.e("VALUES","n =" + n +"p=" + p);
             stock = true;
             TextView sp = (TextView)findViewById(R.id.sp);
             TextView ns = (TextView)findViewById(R.id.ns);
             sp.setText("Price per share = $"+Float.toString(p));
             ns.setText("Shares = " + Float.toString(n));
             i.putExtra("Stock_price",p);
             i.putExtra("Stock_shares",n);
             dialog.dismiss();
         }
     });
     dialog.show();
     return ;
 }
    public void add_option()
    {
        final Dialog dialog = new Dialog(this);
        dialog.setContentView(R.layout.add_option);
        dialog.setTitle("Add Option");
        DisplayMetrics metrics = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(metrics);
        int width1 = metrics.widthPixels;
        //    int height1 = metrics.heightPixels;
        dialog.getWindow().setLayout(width1, ViewGroup.LayoutParams.WRAP_CONTENT);
        // set the custom dialog components - text, image and button
        Strike = (EditText) dialog.findViewById(R.id.strike);
        Number = (EditText) dialog.findViewById(R.id.number);
        Premium = (EditText) dialog.findViewById(R.id.cost);
        final Spinner selected = (Spinner) dialog.findViewById(R.id.select_option);
        Button b = (Button) dialog.findViewById(R.id.add);
        // if button is clicked, close the custom dialog
        boolean purchased = true, call = true;
        b.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                boolean purchased = true, call = true;
                switch (selected.getSelectedItemPosition()) {
                    case 0:
                        purchased = true;
                        call = true;
                        break;
                    case 1:
                        purchased = false;
                        call = true;
                        break;
                    case 2:
                        purchased = true;
                        call = false;
                        break;
                    case 3:
                        purchased = false;
                        call = false;
                        break;
                }
                float n,k,p;
                try {
                     n = Float.parseFloat(Number.getText().toString());
                     k = Float.parseFloat(Strike.getText().toString());
                     p = Float.parseFloat(Premium.getText().toString());

                }
                catch (Exception e)
                {
                    n = 3f;
                    k = 1f;
                    p = 2f;
                    Log.e("EXCEPTION","values"+n+"hello\n"+p);
                    Log.e("EXCEPTION","here");
                }
                Option x = new Option(k, p, purchased, call, n);
               // Log.e("Put", "Payoff"+x.Payoff(2));
                portfolio.add(x);
                dialog.dismiss();
            }
        });
        dialog.show();
        return ;
    }

    public void plot(int a)
    {
        final Dialog dialog = new Dialog(this);
        dialog.setContentView(R.layout.stock_range);
        Button b = (Button) dialog.findViewById(R.id.plot);
        if(a==0) {
            dialog.setTitle("Payoff Diagram");
            b.setText("Plot Payoff");
        }
        else
        {
            dialog.setTitle("Profit Diagram");
            b.setText("Plot Profit");
        }
        DisplayMetrics metrics = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(metrics);
        int width1 = metrics.widthPixels;
        int height1 = metrics.heightPixels;
        dialog.getWindow().setLayout(width1, height1);
        From = (EditText) dialog.findViewById(R.id.from);
        To = (EditText) dialog.findViewById(R.id.to);


        i.putExtra("choice",a);
        // if button is clicked, close the custom dialog
        b.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                f = Float.parseFloat(From.getText().toString());
                t = Float.parseFloat(To.getText().toString());
                Log.e("VALUES","n =" + f +"d=" + t);

                i.putExtra("from",f);
                i.putExtra("to",t);
                i.putParcelableArrayListExtra("portfolio", portfolio);
                i.putExtra("Stock", stock);

                startActivity(i);
                dialog.dismiss();


                //  plot(1);
            }
        });
        dialog.show();


    }
}



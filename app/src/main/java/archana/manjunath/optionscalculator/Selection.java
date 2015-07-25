package archana.manjunath.optionscalculator;

import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;


public class Selection extends ActionBarActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_selection);
        Button selection1 = (Button) findViewById(R.id.selection1);
        Button selection2 = (Button) findViewById(R.id.selection2);
        selection1.setOnClickListener(new View.OnClickListener()
        {
            public void onClick(View v) {
                Intent i = new Intent(Selection.this, MainActivity.class);
                startActivity(i);

            }
        });

        selection2.setOnClickListener(new View.OnClickListener()
        {
            public void onClick(View v) {
                Intent i = new Intent(Selection.this, OptionsPortfolio.class);
                startActivity(i);

            }
        });

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_selection, menu);
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

package archana.manjunath.optionscalculator;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.List;

/**
 * Created by archana on 3/25/15.
 */
public class myAdapter extends ArrayAdapter<Option> {


    public myAdapter(Context context, int resource, List<Option> items) {
        super(context, resource, items);
    }

    public View getView(int position, View convertView, ViewGroup parent) {

        View v = convertView;

        if (v == null) {

            LayoutInflater vi;
            vi = LayoutInflater.from(getContext());
            v = vi.inflate(R.layout.list_row, null);

        }

       Option p = getItem(position);

        if (p != null) {

            TextView type = (TextView) v.findViewById(R.id.type);
            TextView str_pr = (TextView) v.findViewById(R.id.str_pr);
            TextView num = (TextView) v.findViewById(R.id.number);
            TextView premium = (TextView) v.findViewById(R.id.premium);

            if (type != null) {
                switch (p.getType()) {
                    case 1 :
                        type.setText("Purchased Call");
                        break;
                    case 2 :
                        type.setText("Written Call");
                        break;
                    case 3:
                        type.setText("Purchased Put");
                        break;
                    case 4:
                        type.setText("Written Put");
                }
            }
                if (str_pr != null) {
                    str_pr.setText(Float.toString(p.getStrike()));
                }
                if (premium != null) {

                    premium.setText(Float.toString(p.getPremium()));
                }
                if (num != null) {
                    num.setText(Float.toString(p.getNumber()));
                }

            }

            return v;

        }
    }


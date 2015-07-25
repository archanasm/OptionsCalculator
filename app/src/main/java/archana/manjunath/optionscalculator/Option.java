package archana.manjunath.optionscalculator;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by archana on 3/25/15.
 */
public class Option implements Parcelable
{

    private float strike ;
    private float premium;
    private boolean bought ;
    final float PRECISION_LEVEL = 0.001f;
    private boolean call;
    private float number;

  Option (float K, float p, boolean purchased, boolean ifCall,float n )
    {
        strike = K;
        premium = p;
        bought = purchased;
        call = ifCall;
        number =n;

    }

    public float Payoff (float stock_price)
    {
        float payoff ;
        if(call) {
            if (strike > stock_price || Math.abs(strike - stock_price) < PRECISION_LEVEL)
                payoff = 0;
            else
                payoff = stock_price - strike;
        }
        else
        {
            if(strike<stock_price||Math.abs(strike-stock_price)<PRECISION_LEVEL)
                payoff = 0;
            else
                payoff = strike-stock_price;
        }
        if(bought)
            return number*payoff;
        else return -1*number*payoff;


    }

    public float getPremium()
    {
        if(bought)
            return number*premium;
        else {
            return -1*number * premium;
        }
    }

    public float getStrike()
    {
        return strike;
    }
    public float getNumber()
    {
        return number ;
    }
    public int getType()
    {

        if(call)
        {
            if(bought)
                return 1;
            else
                return 2;
        }
        else
        {
            if(bought)
                return 3;
            else
                return 4;
        }

    }
    private Option(Parcel in) {
        // This order must match the order in writeToParcel()
        strike = in.readFloat();
        premium = in.readFloat();
        number = in.readFloat();
        bought = in.readByte() != 0;
        call = in.readByte() != 0;
        // Continue doing this for the rest of your member data
    }

    public void writeToParcel(Parcel out, int flags) {
        // Again this order must match the Question(Parcel) constructor
        out.writeFloat(strike);
        out.writeFloat(premium);
        out.writeFloat(number);
        out.writeByte((byte) (bought ? 1 : 0));
        out.writeByte((byte)(call ? 1:0));

        // Again continue doing this for the rest of your member data
    }

    // Just cut and paste this for now
    public int describeContents() {
        return 0;
    }

    // Just cut and paste this for now
    public static final Parcelable.Creator<Option> CREATOR = new Parcelable.Creator<Option>() {
        public Option createFromParcel(Parcel in) {
            return new Option(in);
        }

        public Option[] newArray(int size) {
            return new Option[size];
        }
    };
}


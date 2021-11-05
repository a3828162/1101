package com.cornez.shippingcalculator;

/**
 * Created by trishcornez on 6/29/14.
 */
public class ShipItem {


    // DATA MEMBERS
    private boolean mGender;
    private double stdweight;
    private double range1;
    private double range2;
    private double K;
    private boolean intype;

    public ShipItem() { mGender = true; intype=true;}

    public void Compute(double H,double W, int A) {
        if (mGender) stdweight = (H - 80) * 0.7;
        else stdweight = (H - 70) * 0.6;
        range1 = stdweight * 0.9;
        range2 = stdweight * 1.1;
        if (A == 1) {
            if (W < range1) K = stdweight * 35;
            else if (W > range2) K = stdweight * 25;
            else K = stdweight * 30;
        }
        else if (A == 2) {
            if (W < range1) K = stdweight * 40;
            else if (W > range2) K = stdweight * 30;
            else K = stdweight * 35;
        }
        else {
            if (W < range1) K = stdweight * 45;
            else if (W > range2) K = stdweight * 35;
            else K = stdweight * 40;
        }
    }

    public void Reset() { mGender = true; intype=true;}

    public void ChangeGender() {
        if(mGender) mGender = false;
        else mGender = true;
    }

    public void Changeintype() {
        if(intype) intype = false;
        else intype = true;
    }


    public double getStdweight() { return stdweight; }
    public double getRange1() { return range1; }
    public double getRange2() { return range2; }
    public double getK() { return K; }
    public boolean getintype() { return intype; }
    public boolean getGender() { return mGender; }
}

package com.cornez.shippingcalculator;

/**
 * Created by trishcornez on 6/29/14.
 */
public class ShipItem {


    // DATA MEMBERS
    private int mGender;
    private double stdweight;
    private double range1;
    private double range2;
    private double K;
    private int in;

    public ShipItem() {
        in = 0;
        mGender = 1;
    }

    public void Compute(double H,double W, int A) {
        if (mGender==1) stdweight = (H - 80) * 0.7;
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

    public void Reset() {
        in = 0;
        mGender = 1;
    }

    public void ChangeGender() {
        if(mGender==1) mGender = 0;
        else mGender = 1;
    }

    public void ChangeInput() { in ^= 1; };

    public void setG(int g)
    {
        mGender=g;
    }

    public void setI(int i) {in=i;}

    public double getStdweight() { return stdweight; }
    public double getRange1() { return range1; }
    public double getRange2() { return range2; }
    public double getK() { return K; }
    public int getGender() { return mGender; }
    public int getIn() { return in; }
}

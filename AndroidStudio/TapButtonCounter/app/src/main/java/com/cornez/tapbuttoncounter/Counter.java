package com.cornez.tapbuttoncounter;

class Counter {
    private double mCount;

    public Counter(){
        mCount = 0;
    }

    public void addCount(){
        mCount++;
    }

    public void subCount(){
        mCount--;
    }

    public void setCount(double m) { mCount = m;}

    public double Comp(double T,double R)
    {
        mCount = -8.78469475556+1.61139411*T+2.33854883889*R
                +-0.14611605*T*R+-0.012308094*T*T
                +-0.0164248277778*R*R+0.002211732*T*T*R+ 0.00072546*T*R*R+-0.000003582*T*T*R*R;
        mCount = Math.round(mCount*10.0)/10.0;
        return mCount;
    }

    public Double getCount() {
        return mCount;
    }

    public Integer getICount() { return (int)mCount; }
}

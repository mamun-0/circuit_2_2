package sample;

public class Complex {
    private final double re;
    private final double im;
    public Complex(double real, double imag){
        this.re = real;
        this.im = imag;
    }


    public double abs(){
        return Math.hypot(this.re, this.im);
    }
    public double phase(){
        return  Math.atan2(this.im,this.re);
    }
    public Complex plus(Complex b){
        Complex a = this;
        double real = a.re + b.re;
        double imag = a.im + b.im;
        return new Complex(real, imag);
    }

    @Override
    public String toString() {
        return "Complex{" +
                "re=" + re +
                ", im=" + im +
                '}';
    }
}

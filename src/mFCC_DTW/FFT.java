package mFCC_DTW;


public class FFT 
{
    public static Complex[] fft(Complex[] x) {
        int N = x.length; //on suppose que N est une puissance de 2
        
        if (N == 0)  throw new RuntimeException("x est vide."); 

        if (N == 1) return new Complex[] { x[0] };

        if (N % 2 != 0) { throw new RuntimeException("N n'est pas une puissance de 2."); }

        // termes pairs
        Complex[] pair = new Complex[N/2];
        for (int k = 0; k < N/2; k++)
            pair[k] = x[2*k];
        
        Complex[] q = fft(pair);

        // termes impairs
        Complex[] impair  = new Complex[N/2];
        for (int k = 0; k < N/2; k++)
            impair[k] = x[2*k + 1];
        
        Complex[] r = fft(impair);

        Complex[] y = new Complex[N];
        for (int k = 0; k < N/2; k++)
        {
            double arg = -2 * k * Math.PI / N;
            Complex wk = new Complex(Math.cos(arg), Math.sin(arg));
            y[k] = q[k].plus(wk.fois(r[k]));
            y[k + N/2] = q[k].moins(wk.fois(r[k]));
        }
        
        return y;
    }


    public static Complex[] fftinverse(Complex[] x) {
        int N = x.length;
        Complex[] y = new Complex[N];

        for (int i = 0; i < N; i++) // on prend le conjugué
            y[i] = x[i].conjugate();

        y = fft(y);
       
        for (int i = 0; i < N; i++)
            y[i] = y[i].conjugate();

        for (int i = 0; i < N; i++) {
            y[i] = y[i].fois(1.0 / N);
        }

        return y;

    }

    public static void afficher(Complex[] x, String title) {
        System.out.println(title);
        System.out.println("-------------------");
        for (int i = 0; i < x.length; i++) {
            System.out.println(x[i]);
        }
        System.out.println();
    }
}

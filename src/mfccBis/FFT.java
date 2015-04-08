package mfccBis;


public class FFT 
{
    public static Complex[] fft(Complex[] x) //!!!peut-être pb de longuer > 512 à cause de getFormat
    {
    	int l = x.length;
    	int N = l;
    	//System.out.println(N+" "+Math.pow(2,(int) (Math.log(N)/0.6931)));
    	int pow =(int) Math.pow(2,(int) (Math.log(N)/0.6931));
    	l=pow;N=pow;
    	System.out.println(pow);
		//zero-padding pour avoir une longueur égale à une puissance de deux
    	//512 est la plus petite puissance de deux au-dessus de la longueur d'une trame
		Complex[] temp = new Complex[N];
		Complex zero = new Complex(0,0);
		for(int n = 0; n < N; n++)
			if(n<l)
				temp[n] = x[n];
			else
				temp[n] = zero;
		 
        if (N == 0)  throw new RuntimeException("x est vide."); 

        if (N == 1) return new Complex[] { x[0] };

        if (N % 2 != 0) { throw new RuntimeException("N n'est pas une puissance de 2."); }

        // termes pairs
        Complex[] pair = new Complex[N/2];
        for (int k = 0; k < N/2; k++)
            pair[k] = temp[2*k];
        
        Complex[] q = fft(pair);

        // termes impairs
        Complex[] impair  = new Complex[N/2];
        for (int k = 0; k < N/2; k++)
            impair[k] = temp[2*k + 1];
        
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
    
    public static double[] dct(double[] u) //on s'aide d'une technique vue en OASIS
    {
    	int N = u.length;
    	Complex[] x = new Complex[2*N];
    	for(int n=0; n<2*N; n++) //on symétrise x
    	{
    		if(n<N)
    			x[n] = new Complex(u[n],0);
    		else
    			x[n] = new Complex(u[2*N-1-n],0);
    	}
    	
    	double[] v = new double[N]; //dct de u
    	v[0] = fft(x)[0].re(); //de toute façon ce nombre était réel
    	
    	Complex i = new Complex(0,1);
    	for(int k=1; k<N; k++)
    		v[k] = ((i.fois(-k*Math.PI/(2*N)).exp()).fois(fft(x)[k].divide(2*N))).re();//idem
    	
    	return v;
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

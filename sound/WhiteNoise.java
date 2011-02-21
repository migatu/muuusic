package sound;
import java.util.Random;


/**
 * A class that provides a source of pink noise with a power spectrum
 * density (PSD) proportional to 1/f^alpha.  "Regular" pink noise has a
 * PSD proportional to 1/f, i.e. alpha=1.  However, many natural
 * systems may require a different PSD proportionality.  The value of
 * alpha may be from 0 to 2, inclusive.  The special case alpha=0
 * results in white noise (directly generated random numbers) and
 * alpha=2 results in brown noise (integrated white noise).
 * <p>
 * The values are computed by applying an IIR filter to generated
 * Gaussian random numbers.  The number of poles used in the filter
 * may be specified.  For each number of poles there is a limiting
 * frequency below which the PSD becomes constant.  Values as low as
 * 1-3 poles produce relatively good results, however these values
 * will be concentrated near zero.  Using a larger number of poles
 * will allow more low frequency components to be included, leading to
 * more variation from zero.  However, the sequence is stationary,
 * that is, it will always return to zero even with a large number of
 * poles.
 * <p>
 * The distribution of values is very close to Gaussian with mean
 * zero, but the variance depends on the number of poles used.  The
 * algorithm can be made faster by changing the method call
 *     <code> rnd.nextGaussian() </code>    to
 *     <code> rnd.nextDouble()-0.5 </code>
 * in the method {@link #nextValue()}.  The resulting distribution is
 * almost Gaussian, but has a relatively larger amount of large
 * values.
 * <p>
 * The IIR filter used by this class is presented by N. Jeremy Kasdin,
 * Proceedings of the IEEE, Vol. 83, No. 5, May 1995, p. 822.
 * 
 * @author Sampo Niskanen <sampo.niskanen@iki.fi>
 */
public class WhiteNoise {
    
    private static Random rnd;
    /**
     * A main method to demonstrate the functionality.  The program is
     * used as:
     * <pre>
     *     java PinkNoise samples [alpha [poles]]
     *
     *     samples  =  number of samples to output
     *     alpha    =  PSD distribution exponent, 1/f^alpha (default 1.0)
     *     poles    =  number of IIR poles to use (default 5)
     * </pre>
     */
    public static double[] go(int length) {
        WhiteNoise source;
        rnd = new Random();

	// Generate values:
        double [] result = new double[length];
	source = new WhiteNoise();
	for (int i=0; i < length; i++) {
	    result[i] = rnd.nextGaussian();
	}
        return result;
    }
}

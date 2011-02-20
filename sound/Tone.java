package sound;

import javax.sound.sampled.*;

/**
 * Generate a tone mathematically.
 *
 * @author Knute Johnson
 */
public class Tone
   {
   /**
   * Generate a tone
   *
   * @param args not used
   */
   public static void main ( String[] args )
      {
      int length = 4;
      short[] pcm_linear = new short[ 8000 * length ];
      int seconds = 2;
      int sampleRate = 8000;
      double frequency = 130.0;
      double RAD = 2.0 * Math.PI;
     /* for ( int i = 0; i < pcm_linear.length; i++ )
                {
                //  more interesting noise
                // pcm_linear[i] = (short)(16000*Math.sin((double)(i*i)/10000.0 * (2*3.1416)));
                // bump up the volume so range between -16,000 .. +16,000
                // make a sine wave of 120 Hz.
                // Since you are only sampling at 8000 samples per second,
                // can't expect this to work above 4000 Hz.
                // You will get a stroboscopic beat effect.
                pcm_linear[ i ] =
                        ( short ) ( 16000 * Math.sin( i * ( 120. * 2 * Math.PI
                                                            / 8000. ) ) );
                }
           */

      try
         {
         AudioFormat af = new
                          AudioFormat( (float)sampleRate, 8, 1, true, true );
         DataLine.Info info = new
                              DataLine.Info ( SourceDataLine.class, af );
         SourceDataLine source =
         (SourceDataLine) AudioSystem.getLine( info );
         source.open( af );
         source.start();
         byte[] buf = new byte[sampleRate * seconds];
         for ( int i=0; i<buf.length; i++ )
            {
            buf[i] =
            (byte)( Math.sin( RAD * frequency / sampleRate * i ) * 127.0 );
            }
         source.write( buf, 0, buf.length );
         source.drain();
         source.stop();
         source.close();
         }
      catch ( Exception e )
         {
         System.out.println( e );
         }
      System.exit( 0 );
      }
   }
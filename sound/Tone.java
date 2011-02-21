/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package sound;

/**
 *
 * @author Michal
 */
public class Tone {
      static int length = 4;
      static int samplerate = 8000;
      static short[]  pcm_linear = new short[ samplerate * length ];
      static byte [] buf = new byte [samplerate * length];
      static double frequency = 130.0;
      static double RAD = 2.0 * Math.PI;

      public static short[] pcmwave(){
      for ( int i = 0; i < pcm_linear.length; i++ )
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
      return pcm_linear;
      }

  public static byte[] sineWave(){

       for ( int i=0; i<buf.length; i++ )
            {
            buf[i] =
            (byte)( Math.sin( RAD * frequency / samplerate * i ) * 127.0 );
           


            }
       return buf;
  }
}

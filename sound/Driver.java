package sound;

import javax.sound.sampled.*;

/**
 * Generate a tone mathematically.
 *
 * @author Knute Johnson
 */
public class Driver
   {
   /**
   * Generate a tone
   *
   * @param args not used
   */
  static int sampleRate = 8000;
  static int seconds = 2;
   public static void main ( String[] args )
      {

           

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
         double[] bufd = new double [buf.length];
         short [] bufs = new short [buf.length];
         //bufd = PinkNoise.go(buf.length, 1.0, 5);
         //bufd = WhiteNoise.go(bufd.length);
         //buf = Tone.sineWave();
         bufs = Tone.pcmwave();
         for ( int i=0; i<buf.length; i++ )
            {
            buf[i] = (byte) bufs [i];
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
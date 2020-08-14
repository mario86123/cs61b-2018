import synthesizer.GuitarString;

public class GuitarHero {
    private static double[] CONCERT = new double[37];

    public static void main(String[] args) {
        /* create two guitar strings, for concert A and C */
        String keyboard = "q2we4r5ty7u8i9op-[=zxdcfvgbnjmk,.;/' ";
        GuitarString[] stringArray = new GuitarString[37];

        for (int i = 0; i < CONCERT.length; i += 1) {
            CONCERT[i] = 440.0 * Math.pow(2, (i - 24) / 12.0);
            stringArray[i] = new GuitarString(CONCERT[i]);
        }

        while (true) {
            /* check if the user has typed a key; if so, process it */
            if (StdDraw.hasNextKeyTyped()) {
                char key = StdDraw.nextKeyTyped();
                stringArray[keyboard.indexOf(key)].pluck();
            }

            /* compute the superposition of samples */
            double sample = 0.0;
            for (int i = 0; i < CONCERT.length; i += 1) {
                sample += stringArray[i].sample();
            }

            /* play the sample on standard audio */
            StdAudio.play(sample);

            /* advance the simulation of each guitar string by one step */
            for (int i = 0; i < CONCERT.length; i += 1) {
                stringArray[i].tic();
            }
        }
    }
}

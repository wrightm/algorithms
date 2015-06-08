package com.github.wrightm.tutorial.algorithms;
import java.util.Random;


public class Helpers {

	/**
	 * Returns a pseudo-random number between min and max, inclusive.
	 * The difference between min and max can be at most
	 * <code>Integer.MAX_VALUE - 1</code>.
	 *
	 * @param min Minimum value
	 * @param max Maximum value.  Must be greater than min.
	 * @return Integer between min and max, inclusive.
	 * @see java.util.Random#nextInt(int)
	 */
	public static int randInt(int min, int max) {

	    // NOTE: Usually this should be a field rather than a method
	    // variable so that it is not re-seeded every call.
	    Random rand = new Random();

	    // nextInt is normally exclusive of the top value,
	    // so add 1 to make it inclusive
	    int randomNum = rand.nextInt((max - min) + 1) + min;

	    return randomNum;
	}
	
	public static int[][] sample(final int sampleSize){
		final int[][] sample = new int[sampleSize][2];
		for(int index = 0; index < sampleSize-1;++index){
			sample[index] = new int[2];
			sample[index][0] = randInt(0,9);
			sample[index][1] = randInt(0,9);
		}
		return sample;
	}
	
}

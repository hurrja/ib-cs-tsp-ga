import java.util.Random;

public class RandomUtils
{
  // generate a pair of distinct random integers in range
  // [0,...,upper-1], returning them in 2nd and 3rd parameter
  static public void randomIntegerPair (int upper, int[] ints)
  {
    ints [0] = randomGenerator.nextInt (upper);
    int r = randomGenerator.nextInt (upper - 1);
    ints [1] = (r + ints [0] + 1) % upper;
  }

  // same as above, but returned indices are in increasing order
  static public void randomSortedIntegerPair (int upper, int[] ints)
  {
    int[] pair = new int [2];
    randomIntegerPair (upper, pair);
    ints [0] = Math.min (pair [0], pair [1]);
    ints [1] = Math.max (pair [0], pair [1]);
  }

  public static Random randomGenerator = new Random ();
}

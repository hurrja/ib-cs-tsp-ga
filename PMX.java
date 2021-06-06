public class PMX extends Crossover
{
  char[] crossover (char[] parentA, char[] parentB)
  {
    // indices of subsequence copied directly from parent A
    int[] ASubInds = new int [2];
    RandomUtils.randomSortedIntegerPair (parentA.length, ASubInds);
    int AStartInd = ASubInds [0], AEndInd = ASubInds [1];

    char[] offspring = new char [parentA.length];
    int bInd = 0; // index when proceeding from left to right in B
    for (int i = 0; i < offspring.length; i++)
    {
      // if inside A subsequence, copy directly
      if (i >= AStartInd && i <= AEndInd)
        offspring [i] = parentA [i];
      else
      {
        // not inside A subsequence, copy first gene from B not inside
        // A subsequence
        boolean BGeneInA;
        do
        {
          BGeneInA = false;
          for (int j = AStartInd; !BGeneInA && j <= AEndInd; j++)
            if (parentB [bInd] == parentA [j])
              BGeneInA = true;
          if (BGeneInA)
            bInd++;
        } while (BGeneInA);

        offspring [i] = parentB [bInd++];
      }
    }
    
    return offspring;
  }
}

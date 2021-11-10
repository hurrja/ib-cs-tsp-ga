// Copyright (C) 2021 Jarmo Hurri, Diana Ginzburg, Gabriel Dearden,
// Askar Tuiushev

// This program is free software: you can redistribute it and/or modify
// it under the terms of the GNU General Public License as published by
// the Free Software Foundation, either version 3 of the License, or
// (at your option) any later version.

// This program is distributed in the hope that it will be useful,
// but WITHOUT ANY WARRANTY; without even the implied warranty of
// MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
// GNU General Public License for more details.

// You should have received a copy of the GNU General Public License
// along with this program.  If not, see <https://www.gnu.org/licenses/>.

public class OX extends Crossover
{
  char[] crossover (char[] parentA, char[] parentB)
  {
    // indices of subsequence copied directly from parent A
    int[] ASubInds = new int [2];
    RandomUtils.randomSortedIntegerPair (parentA.length, ASubInds);
    int aStartInd = ASubInds [0], aEndInd = ASubInds [1];

    char[] offspring = new char [parentA.length];

    int numAssigned = 0;
    int offspringInd;

    // copy subsequence from parent A
    for (offspringInd = aStartInd; offspringInd <= aEndInd; offspringInd++)
    {
      offspring [offspringInd] = parentA [offspringInd];
      numAssigned++;
    }

    int bInd = aEndInd + 1; // index in parent B
    while (numAssigned < parentA.length)
    {
      // if past end of offspring array, cycle to beginning
      offspringInd %= parentA.length;
      bInd %= parentA.length;
      
      boolean bGeneUsed;
      do
      {
        bGeneUsed = false;
        for (int j = aStartInd; !bGeneUsed && j <= aEndInd; j++)
          if (parentB [bInd] == parentA [j])
            bGeneUsed = true;
        if (bGeneUsed)
        {
          bInd++;
          bInd %= parentA.length;
        }
      } while (bGeneUsed);

      offspring [offspringInd] = parentB [bInd];
      numAssigned++;
      bInd++;
      offspringInd++;
    }

    return offspring;
  }
}

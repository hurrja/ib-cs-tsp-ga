// Copyright (C) 2021 Askar Tuiushev, Jarmo Hurri

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

import java.util.Random;

public class RouletteWheelSelection extends Selection
{
  public RouletteWheelSelection (int numSelected)
  {
    this.numSelected = numSelected;
  }

  public char[][] select (char[][] population, int[] fitnesses)
  {
    // convert fitnesses into nonnegative values by subtracting the
    // minimum fitness (these values are here called "positive" even
    // though zero is included)

    // population is in order of fitnesses, so last individual has min
    // fitness
    int minFitness = fitnesses [fitnesses.length - 1];
    int[] positiveFitnesses = new int [fitnesses.length];
    for (int i = 0; i < fitnesses.length; i++)
      positiveFitnesses [i] = fitnesses [i] - minFitness;
    
    // convert "positive" fitnesses into corresponding probabilities
    int sumOfFitnesses = 0;
    for (int f : positiveFitnesses)
      sumOfFitnesses += f;

    // an array of cumulative probabilities (to be selected)
    double[] cumulativeProbs = new double [positiveFitnesses.length];

    double sumOfProb = 0;
    for (int i = 0; i < positiveFitnesses.length; i++)
    {
      sumOfProb += ((double) positiveFitnesses [i]) / sumOfFitnesses;
      cumulativeProbs [i] = sumOfProb;
    }

    char[][] selected = new char [numSelected][];
    Integer[] selectedFitnesses = new Integer [numSelected];
    for (int i = 0; i < numSelected; i++)
    {
      double p = RandomUtils.randomUniform ();
      int s = 0; // index of selected individual
      while (cumulativeProbs [s] < p)
        s++;
      
      selected [i] = population [s];
      selectedFitnesses [i] = fitnesses [s];
    }
    
    char[][] sortedSelected = new char [numSelected][];
    int[] sortedSelectedFitnesses = new int [numSelected];
    TSPGeneticAlgorithm.sort (selected, selectedFitnesses, sortedSelected, sortedSelectedFitnesses);
    return sortedSelected;
  }

  private int numSelected;
}

// Copyright (C) 2021 Askar Tuiushev, Jarmo Hurri

// This program is free software: you can redistribute it and/or
// modify it under the terms of the GNU General Public License as
// published by the Free Software Foundation, either version 3 of the
// License, or (at your option) any later version.

// This program is distributed in the hope that it will be useful, but
// WITHOUT ANY WARRANTY; without even the implied warranty of
// MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the GNU
// General Public License for more details.

// You should have received a copy of the GNU General Public License
// along with this program.  If not, see <https://www.gnu.org/licenses/>.


import java.util.Arrays;
import java.util.Random;

public class TournamentSelection extends Selection
{
  public TournamentSelection (int numSelected, int numParticipants)
  {
    this.numSelected = numSelected;
    this.numParticipants = numParticipants;
  }

  public char[][] select (char[][] population, int[] fitnesses)
  {
    char[][] selected = new char [numSelected][];
    Integer[] selectedFitnesses = new Integer [numSelected];

    for (int s = 0; s < numSelected; s++)
    {
      // because we know that fitnesses[] stores fitnesses from
      // highest to lowest, the smallest index gets selected
      int min = population.length;
      for (int p = 0; p < numParticipants; p++)
        min = Math.min (min, RandomUtils.randomGenerator.nextInt (numSelected));

      selected [s] = population [min];
      selectedFitnesses [s] = fitnesses [min];
    }

    char[][] sortedSelected = new char [numSelected][];
    int[] sortedSelectedFitnesses = new int [numSelected];
    TSPGeneticAlgorithm.sort (selected, selectedFitnesses, sortedSelected, sortedSelectedFitnesses);

    return sortedSelected;
  }

  private int numSelected;
  private int numParticipants;
}

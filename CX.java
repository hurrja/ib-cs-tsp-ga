// Copyright (C) 2021 Jarmo Hurri, Diana Ginzburg, Gabriel Dearden,

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

public class CX extends Crossover
{
  public char[] crossover (char[] parentA, char[] parentB)
  {
    char[] offspring = new char [parentA.length];
    boolean[] assigned = new boolean [parentA.length];
    offspring [0] = parentA [0];
    assigned [0] = true;
    char c = parentB [0];

    int i;
    while ((i = find (c, parentA)) != 0)
    {
      offspring [i] = c;
      assigned [i] = true;
      c = parentB [i];
    }

    // copy remaining from parentB
    for (int j = 0; j < offspring.length; j++)
      if (!assigned [j])
        offspring [j] = parentB [j];

    return offspring;
  }

  private int find (char c, char[] parent)
  {
    for (int i = 0; i < parent.length; i++)
      if (parent [i] == c)
        return i;

    return (-1);
  }
  

}

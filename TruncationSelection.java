public class TruncationSelection extends Selection
{
  public TruncationSelection (int proportion)
  {
    this.proportion = proportion;
  }
  
  public char[][] doSelection (char[][] population,
                               Integer[] fitnesses,
                               Integer[] sortIndices)
  {
    final int POOL_SIZE = population.length / proportion;
    char[][] selected = new char[POOL_SIZE][];
    for (int i = 0; i < POOL_SIZE; i++)
      selected [i] = population [sortIndices [i]];

    return selected;
  }

  private int proportion;
}

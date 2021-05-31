public class TruncationSelection extends Selection
{
  public TruncationSelection (int proportion)
  {
    this.proportion = proportion;
  }
  
  public char[][] select (char[][] population, int[] fitnesses)
  {
    return population;
  }

  private int proportion;
}

// class for selecting parents for next generation
public abstract class Selection
{
  // selection from population using fitnesses, with population sorted
  // according to fitnesses; needs to be implemented by all concrete
  // classes
  protected abstract char[][] select (char[][] population,
                                      int[] fitnesses);

}

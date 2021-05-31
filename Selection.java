import java.util.Comparator;
import java.util.Arrays;

// class for selecting parents for next generation
public abstract class Selection
{
  // returns array of parents for next generation
  public char[][] select (char[][] population, Integer[] fitnesses)
  {
    Integer[] sortIndices = sortIndices (fitnesses);
    return doSelection (population, fitnesses, sortIndices);
  }
  
  // selection from population using fitnesses, with indices
  // containing sort order based on fitnesses; needs to be implemented
  // by all concrete classes
  protected abstract char[][] doSelection (char[][] population,
                                           Integer[] fitnesses,
                                           Integer[] sortIndices);

  // returns an array ind of indices which sorts the fitness array so
  // that fitnesses[ind[0]] > fitnesses[ind[1]] > fitnesses[ind[2]]
  // etc.
  private Integer[] sortIndices (Integer[] fitnesses)
  {
    ArrayIndexComparator comparator = new ArrayIndexComparator (fitnesses);
    Integer[] indices = comparator.createIndexArray ();

    // sort in descending order
    Arrays.sort (indices, comparator.reversed ()); 

    return indices;
  }
}

// comparator for array of indices based on values from the array the
// indices refer to (for index sorting based on array contents)
class ArrayIndexComparator implements Comparator<Integer>
{
  public ArrayIndexComparator (Integer[] array)
  {
    this.array = array;
  }

  public Integer[] createIndexArray ()
  {
    Integer[] indices = new Integer [array.length];
    for (int i = 0; i < array.length; i++)
      indices [i] = i;
    return indices;
  }

  @Override
  public int compare (Integer index1, Integer index2)
  {
    return array [index1].compareTo (array [index2]);
  }

  private final Integer[] array;
}

import java.util.ArrayList;
import java.util.List;
import java.util.Collections;

public class TspGa
{
  public TspGa (Selection selection,
                Evaluation evaluation,
                Crossover crossover,
                Mutation mutation,
                Termination termination)
  {
    this.selection = selection;
    this.evaluation = evaluation;
    this.crossover = crossover;
    this.mutation = mutation;
    this.termination = termination;

    POPULATION_SIZE = 10;
    STARTCITY = 'X';
    VISITED_CITIES = "ABCDEFGHIJKLMNOPQRST".toCharArray ();
    NUM_CITIES = VISITED_CITIES.length + 1;
    population = new char [POPULATION_SIZE][];
    initialize ();

    for (char[] r : population)
      System.out.println (java.util.Arrays.toString (r));
  }
  
  void run ()
  {
    // List<Integer> fitnesses = evaluation.evaluate (population);

    // boolean terminate = false;
    // while (!terminate)
    // {
    //   population = selection.select (population, fitnesses);
    //   population = crossover.crossover (population);
    //   population = mutation.mutate (population);
    //   List<Integer> newFitnesses = evaluation.evaluate (population);
    //   if (Termination.terminate (newFitnesses, fitnesses))
    //     terminate = true;
    //   else
    //     fitnesses = newFitnesses;
    // }
  }

  private void initialize ()
  {
    List<Character> visitedCities = new ArrayList<> ();
    for (char c : VISITED_CITIES)
      visitedCities.add (c);
    
    for (int i = 0; i < POPULATION_SIZE; i++)
    {
      Collections.shuffle (visitedCities);
      population [i] = new char [NUM_CITIES + 1];
      population [i][0] = STARTCITY;
      population [i][NUM_CITIES] = STARTCITY;
      int j = 1;
      for (char c : visitedCities)
        population [i][j++] = c;
    }
  }
  
  private char[][] population;
  private Selection selection;
  private Evaluation evaluation;
  private Crossover crossover;
  private Mutation mutation;
  private Termination termination;
  final int POPULATION_SIZE;
  final char STARTCITY;
  final char[] VISITED_CITIES;
  final int NUM_CITIES;
}

class Selection {}
class Evaluation {}
class Crossover {}
class Mutation {}
class Termination {}

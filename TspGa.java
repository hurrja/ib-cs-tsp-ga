import java.util.ArrayList;
import java.util.List;
import java.util.Collections;
import java.util.Map;
import java.util.HashMap;

public class TspGa
{
  public TspGa (Selection selection,
                Crossover crossover,
                Mutation mutation,
                Termination termination)
  {
    this.selection = selection;
    this.crossover = crossover;
    this.mutation = mutation;
    this.termination = termination;

    // TSP constants
    STARTCITY = 'X';
    VISITED_CITIES = "ABCDEFGHIJKLMNOPQRST".toCharArray ();
    CITIES = new char [VISITED_CITIES.length + 1];
    CITIES [0] = STARTCITY;
    for (int i = 1; i < CITIES.length; i++)
      CITIES [i] = VISITED_CITIES [i - 1];
    NUM_CITIES = CITIES.length;

    // distances from IB document
    final int[][] DISTS =
      {{0, 94, 76, 141, 91, 60, 120, 145, 91, 74, 90, 55, 145, 108, 41, 49, 33, 151, 69, 111, 24},
       {94, 0, 156, 231, 64, 93, 108, 68, 37, 150, 130, 57, 233, 26, 62, 140, 61, 229, 120, 57, 109},
       {76, 156, 0, 80, 167, 133, 124, 216, 137, 114, 154, 100, 141, 161, 116, 37, 100, 169, 49, 185, 84},
       {141, 231, 80, 0, 229, 185, 201, 286, 216, 139, 192, 178, 113, 239, 182, 92, 171, 155, 128, 251, 137},
       {91, 64, 167, 229, 0, 49, 163, 65, 96, 114, 76, 93, 200, 91, 51, 139, 72, 185, 148, 26, 92},
       {60, 93, 133, 185, 49, 0, 165, 115, 112, 65, 39, 91, 151, 117, 39, 99, 61, 139, 128, 75, 49},
       {120, 108, 124, 201, 163, 165, 0, 173, 71, 194, 203, 74, 254, 90, 127, 136, 104, 269, 75, 163, 144},
       {145, 68, 216, 286, 65, 115, 173, 0, 103, 179, 139, 123, 265, 83, 104, 194, 116, 250, 186, 39, 152},
       {91, 37, 137, 216, 96, 112, 71, 103, 0, 160, 151, 39, 236, 25, 75, 130, 61, 239, 95, 93, 112},
       {74, 150, 114, 139, 114, 65, 194, 179, 160, 0, 54, 127, 86, 171, 89, 77, 99, 80, 134, 140, 50},
       {90, 130, 154, 192, 76, 39, 203, 139, 151, 54, 0, 129, 133, 155, 78, 117, 99, 111, 159, 101, 71},
       {55, 57, 100, 178, 93, 91, 74, 123, 39, 127, 129, 0, 199, 61, 53, 91, 30, 206, 63, 101, 78},
       {145, 233, 141, 113, 200, 151, 254, 265, 236, 86, 133, 199, 0, 251, 171, 118, 176, 46, 182, 226, 125},
       {108, 26, 161, 239, 91, 117, 90, 83, 25, 171, 155, 61, 251, 0, 83, 151, 75, 251, 119, 81, 127},
       {41, 62, 116, 182, 51, 39, 127, 104, 75, 89, 78, 53, 171, 83, 0, 90, 24, 168, 99, 69, 49},
       {49, 140, 37, 92, 139, 99, 136, 194, 130, 77, 117, 91, 118, 151, 90, 0, 80, 139, 65, 159, 50},
       {33, 61, 100, 171, 72, 61, 104, 116, 61, 99, 99, 30, 176, 75, 24, 80, 0, 179, 76, 86, 52},
       {151, 229, 169, 155, 185, 139, 269, 250, 239, 80, 111, 206, 46, 251, 168, 139, 179, 0, 202, 211, 128},
       {69, 120, 49, 128, 148, 128, 75, 186, 95, 134, 159, 63, 182, 119, 99, 65, 76, 202, 0, 161, 90},
       {111, 57, 185, 251, 26, 75, 163, 39, 93, 140, 101, 101, 226, 81, 69, 159, 86, 211, 161, 0, 115},
       {24, 109, 84, 137, 92, 49, 144, 152, 112, 50, 71, 78, 125, 127, 49, 50, 52, 128, 90, 115, 0}};

    // associative map of maps for easy access to city-city distances
    // using city ids
    distances = new HashMap <> ();
    for (int i = 0; i < NUM_CITIES; i++)
    {
      Map<Character, Integer> cityDistances = new HashMap<> ();
      for (int j = 0; j < NUM_CITIES; j++)
        cityDistances.put (CITIES [j], DISTS [i][j]);
      distances.put (CITIES [i], cityDistances);
    }

    // GA constant
    POPULATION_SIZE = 10;
  }
  
  void run ()
  {
    char[][] population = initializePopulation ();
    int[] fitnesses = evaluate (population);

    boolean terminate = false;
    while (!terminate)
    {
      char[][] parents = selection.select (population, fitnesses);
      terminate = true;
      //   population = crossover.crossover (population);
      //   population = mutation.mutate (population);
      //   List<Integer> newFitnesses = evaluation.evaluate (population);
      //   if (Termination.terminate (newFitnesses, fitnesses))
      //     terminate = true;
      //   else
      //     fitnesses = newFitnesses;
    }
  }

  // initialize population with random permutations of visited cities
  private char[][] initializePopulation ()
  {
    char[][] population = new char [POPULATION_SIZE][];
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

    return population;
  }

  // evaluate and return fitnesses of routes in the population; note
  // that fitness is the opposite of route length (negative); to
  // minimize route length, we can maximize this fitness measure
  private int[] evaluate (char[][] population)
  {
    int[] fitnesses = new int [population.length];
    
    for (int i = 0; i < population.length; i++)
    {
      char[] route = population [i];
      for (int j = 0; j < route.length - 1; j++)
        fitnesses [i] -= distances.get (route [j]).get (route [j + 1]);
    }

    return fitnesses;
  }

  private Selection selection;
  private Crossover crossover;
  private Mutation mutation;
  private Termination termination;
  final int POPULATION_SIZE;
  final char STARTCITY;
  final char[] VISITED_CITIES;
  final char[] CITIES;
  final int NUM_CITIES;
  Map<Character, Map<Character, Integer>> distances;
}

class Crossover {}
class Mutation {}
class Termination {}

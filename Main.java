public class Main
{
  public static void main (String[] args)
  {
    final int POPULATION_SIZE = 10000;
    final int NUM_ELITES = 5;
    final Selection selection = new TruncationSelection (5); // select fittest 1/param
    final Crossover crossover = new PMX (); // partially mapped crossover

    // switch two random cities on the average in in 1/param individuals
    final Mutation mutation = new SwitchMutation (20); 

    TspGa ga = new TspGa (POPULATION_SIZE,
                          NUM_ELITES,
                          selection,
                          crossover,
                          mutation);
    ga.run ();
  }
}

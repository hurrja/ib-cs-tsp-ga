public class GeneticAlgorithm
{
  public GeneticAlgorithm (Selection selection,
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
    CITIES = "XABCDEFGHIJKLMNOPQRST".toCharArray ();
    NUM_CITIES = CITIES.length;
    population = new char[POPULATION_SIZE][]
    initialize ();
  }
  
  Individual run ()
  {
    List<Integer> fitnesses = evaluation.evaluate (population);

    boolean terminate = false;
    while (!terminate)
    {
      population = selection.select (population, fitnesses);
      population = crossover.crossover (population);
      population = mutation.mutate (population);
      List<Integer> newFitnesses = evaluation.evaluate (population);
      if (Termination.terminate (newFitnesses, fitnesses))
        terminate = true;
      else
        fitnesses = newFitnesses;
    }
  }

  private void initialize ()
  {
    
  }
  
  private char[][] population;
  private Selection selection;
  private Evaluation evaluation;
  private Crossover crossover;
  private Mutation mutation;
  private Termination termination;
  final int POPULATION_SIZE = 10;
  final char[] CITIES; = "XABCDEFGHIJKLMNOPQRST".toCharArray ();
  final int NUM_CITIES;
}

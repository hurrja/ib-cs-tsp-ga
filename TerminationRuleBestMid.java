public class TerminationRuleBestMid extends TerminationRule
{
  public boolean terminates (int[] parentFitnesses, int[] offspringFitnesses)
  {
    return offspringFitnesses [0] <= parentFitnesses [0]
      && TSPGeneticAlgorithm.midValue (offspringFitnesses)
      <= TSPGeneticAlgorithm.midValue (parentFitnesses);
  }
}

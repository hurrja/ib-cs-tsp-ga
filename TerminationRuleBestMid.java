// terminates if no increase in fitness of best and no increase in
// "median" fitness; assumes fitness arrays are in decreasing order
public class TerminationRuleBestMid extends TerminationRule
{
  public boolean terminates (int[] parentFitnesses, int[] offspringFitnesses)
  {
    return offspringFitnesses [0] <= parentFitnesses [0]
      && TSPGeneticAlgorithm.midValue (offspringFitnesses)
      <= TSPGeneticAlgorithm.midValue (parentFitnesses);
  }
}

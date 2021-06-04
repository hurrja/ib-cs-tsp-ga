public class SwitchMutation extends Mutation
{
  SwitchMutation (int frequency)
  {
    this.frequency = frequency;
  }

  public void mutate (char[] individual)
  {
    if (RandomUtils.randomGenerator.nextInt (frequency) == frequency - 1)
    {
      int[] switchedIndices = new int [2];
      RandomUtils.randomIntegerPair (individual.length, switchedIndices);
      char tmp = individual [switchedIndices [0]];
      individual [switchedIndices [0]] = individual [switchedIndices [1]];
      individual [switchedIndices [1]] = tmp;
    }
  }
  
  private int frequency;
}

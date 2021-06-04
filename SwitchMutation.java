public class SwitchMutation extends Mutation
{
  SwitchMutation (int frequency)
  {
    assert frequency > 0;
    this.frequency = frequency;
  }

  public void mutate (char[] individual)
  {
    // we mutate on the average in a properion of 1/frequency of
    // individuals; here a random int in [0,...,frequency-1] is taken,
    // and mutation happens if the int equals 0 (the checked value
    // could be anything, 0 is selected arbitrarily here)
    if (RandomUtils.randomGenerator.nextInt (frequency) == 0)
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

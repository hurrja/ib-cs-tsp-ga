public class Main
{
  public static void main (String[] args)
  {
    TspGa ga = new TspGa (new TruncationSelection (2), null, null, null);
    ga.run ();
  }
}

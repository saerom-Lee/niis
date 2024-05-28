package CKNB;

public class Callback {
	public void ProcessStatusEmb(int Pos)
	{
		System.out.println("Embedding " + String.format("%3d%%", Pos));
	}
	
	public void ProcessStatusDet(int Pos)
	{
		System.out.println("Detecting " + String.format("%3d%%", Pos));
	}
}

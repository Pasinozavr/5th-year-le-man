
public class Damier {
	private Case[][] cases;
	public Case donneTaCase(int i, int j)
	{
		return cases[i][j];
	}
	public Case toGlark(int i, int j, Glark gl)
	{
		cases[i][j]=new Case(i,j, gl, this);
		return cases[i][j];
	}
	public void toObstacle(int i, int j)
	{
		cases[i][j]=new Case(i,j,'o', this);
	}
	public void toFood(int i, int j)
	{
		cases[i][j]=new Case(i,j,'b', this);
	}
	public Damier()
	{
		cases=new Case[10][10];
		for(int i=0;i<10;i++)
		{
			for(int j=0;j<10;j++)
			{
				if(i==0 || i==9 || j==0 || j==9)cases[i][j]=new Case(i,j,'o',this);
				else cases[i][j]=new Case(i,j, this);
			}
		}
	}
	
}

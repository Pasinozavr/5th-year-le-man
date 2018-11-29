import java.util.*;

public class Jeu {
	private Damier monDamier;
	public static Set<Glark> listeDesGlarks;
	private Random rnd=new Random();
	public void initialiseToi()
	{
		monDamier=new Damier();
		listeDesGlarks=new LinkedSet<Glark>();
		for(int k=0;k<3;k++)
		{
			listeDesGlarks.add(new Borne());
			listeDesGlarks.add(new Glouton());
			listeDesGlarks.add(new Pirate());
		}
		for(Glark g : listeDesGlarks)
		{
			do
			{
		int i=rnd.nextInt(9)+1, j=rnd.nextInt(9)+1;
		if(!monDamier.donneTaCase(i, j).glark && !monDamier.donneTaCase(i, j).obstacle)
			{
			monDamier.toGlark(i, j, g);
			g.caseCourante=monDamier.donneTaCase(i, j);
			}
			}while(g.caseCourante==null );
		}
		
		int food=0;
		do {
			int i=rnd.nextInt(10), j=rnd.nextInt(10);
			if(!monDamier.donneTaCase(i, j).glark && !monDamier.donneTaCase(i, j).obstacle)
				{
				monDamier.toFood(i,j);
				food++;
				}
		}while(food<5);
		
		int stop=0;
		do{
			int i=rnd.nextInt(10), j=rnd.nextInt(10);
			if(!monDamier.donneTaCase(i, j).glark && !monDamier.donneTaCase(i, j).obstacle && !monDamier.donneTaCase(i, j).blurf)
				{
				monDamier.toObstacle(i, j);
				stop++;
				}
		}while(stop<5);
		
		
		
	}
	public void lanceToi()
	{
		do
		{
			showTable();
			for(Glark g : listeDesGlarks)
				{
				listeDesGlarks.stream().filter(v -> !v.alive).forEach(listeDesGlarks::remove);
				if(g.alive)g.move(g.caseCourante, g.trouveDestination());
				listeDesGlarks.stream().filter(v -> !v.alive).forEach(listeDesGlarks::remove);
				}

		}while(listeDesGlarks.size()>1);
		showTable();
	}
	public void showTable()
	{
		for(int i=0;i<10;i++)
		{
			for(int j=0;j<10;j++)
			{
				System.out.print(monDamier.donneTaCase(i, j).toString()+" ");
			}
			System.out.println("");
		}
		System.out.println("");
		int p=1;
		for(Glark g : listeDesGlarks)
		{
			System.out.println(p+" "+g.toString()+" "+g.caseCourante.i+" "+g.caseCourante.j+" "+g.energie);
			p++;
		}
	}
}

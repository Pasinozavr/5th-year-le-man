public class Droite {
	Point point1, point2;
	double a, b;
	public Droite()
	{
		a=0;
		b=1;
	}
	public Droite(double a, double b)
	{
		this.a=a;
		this.b=b;
	}
	public Droite(Point p1, Point p2)
	{
		point1=p1;
		point2=p2;
		a=(p2.gety()-p1.gety())/(p2.getx()-p1.getx());
		b=p1.gety()-((p2.gety()-p1.gety())/(p2.getx()-p1.getx()))*p1.getx();
	}
	public double geta()
	{ return a;}
	public double getb()
	{
		return b;
	}
	public void seta(double p)
    {
		a=p;
    }
	public void setb(double p)
    {
		b=p;
    }
	public String equation()
	{
		return "y="+a+"x+"+b;
	}
	public boolean estParallele(Droite b)
	{
		if(this.a==b.geta())return true;
		else return false;
	}
	public boolean estPerpenduculaire(Droite b)
	{
		if(this.a*b.geta()==-1)return true;
		else return false;
	}
	
	public Point intersection(Droite b)
	{
		if(this.estParallele(b))return new Point(-1111,1111);
		else
		{
		double px=(b.getb()-this.getb())/(this.geta()-b.geta()),
				py=this.a*px+this.b;
		return new Point(px,py);
		}
	}
}


public class Triangle {
	private Point a,b,c;
	private double sideab, sidebc, sideca;
	public Point geta()
	{ return a;}
	public Point getb()
	{return b;}
	public Point getc()
	{return c;}
	public void seta(Point p)
    {
		a=p;
		sideab=a.distance(b);
		sideca=c.distance(a);
    }
	public void setb(Point p)
    {
		b=p;
		sideab=a.distance(b);
		sidebc=b.distance(c);
    }
	public void setc(Point p)
    {
		c=p;
		sidebc=b.distance(c);
		sideca=c.distance(a);
    }
	public Triangle()
	{
		a=new Point(0,0);
		b=new Point(1,0);
		c=new Point(1,1);
		sideab=a.distance(b);
		sidebc=b.distance(c);
		sideca=c.distance(a);
	}
	public Triangle(Point p1, Point p2, Point p3)
	{
		a=p1;
		b=p2;
		c=p3;
		sideab=a.distance(b);
		sidebc=b.distance(c);
		sideca=c.distance(a);
	}
	public Triangle(Droite d1, Droite d2, Droite d3)
	{
		if(!d1.estParallele(d2) && d2.estParallele(d3))
		{
			a=d1.intersection(d2);
			b=d2.intersection(d3);
			c=d3.intersection(d1);
			sideab=a.distance(b);
			sidebc=b.distance(c);
			sideca=c.distance(a);
		}
		else System.out.print("Les droites sont paralleles");
	}
	public double perimetre() {
		return sideab+sidebc+sideca;
	}
	public double surface()
	{
		double k=(sideab+sidebc+sideca)/2.;
		return Math.sqrt(k*(k-sideab)*(k-sidebc)*(k-sideca));
	}
}

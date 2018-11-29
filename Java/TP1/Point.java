import java.lang.Math;

public class Point {
private double x, y;
public Point()
{
	x=0;
	y=0;
}
public Point(double x, double y)
{
	this.x=x;
	this.y=y;
}
public double getx()
{
	return x;
}
public double gety()
{
	return y;
}
public void setx(double p)
{
	x=p;
}
public void sety(double p)
{
	y=p;
}
public boolean equals(Point b)
{
	if(b.getx()==this.x && b.gety()==this.y)
	{
		return true;
	}
	else return false;
}
public boolean equals(Point a, Point b)
{
	if(b.getx()==a.getx() && b.gety()==a.gety())
	{
		return true;
	}
	else return false;
}
public double distance(Point b)
{
	double distx=(b.getx()-this.x)*(b.getx()-this.x),
			disty=(b.gety()-this.y)*(b.gety()-this.y);
	return Math.sqrt(distx+disty);
}
public double distance(Point a, Point b)
{
	double distx=(b.getx()-a.getx())*(b.getx()-a.getx()),
			disty=(b.gety()-a.gety())*(b.gety()-a.gety());
	return Math.sqrt(distx+disty);
}
public boolean estSurDroite(Droite dr)
{
	if(this.y==dr.geta()*this.x+dr.getb())return true;
	else return false;
}

}

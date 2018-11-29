public class ArbreBinaireRecherche<T extends Comparable<T>> implements Comparable<ArbreBinaireRecherche<T>>{
	private boolean root=false;
	private T el;
	private ArbreBinaireRecherche<T> left, right;
	public ArbreBinaireRecherche(T racine)
	{
		el=racine;
		left=right=null;
	}
	public ArbreBinaireRecherche(T racine, ArbreBinaireRecherche<T>l, ArbreBinaireRecherche<T>r)
	{
		el=racine;
		left=l;
		right=r;
		root=true;
	}
	@Override
	public int compareTo(ArbreBinaireRecherche<T> o) {
	    return el.compareTo(o.el);
	}
	public T value()
{
	return el;
}
	public void setvalue(T value)
	{
		el=value;
	}
	public ArbreBinaireRecherche<T> leftchild()
	{
		return left;
	}
	public ArbreBinaireRecherche<T> rightchild()
	{
		return right;
	}
	public void setleftchild(ArbreBinaireRecherche<T>l)
	{
		left=l;
	}
	public void setrightchild(ArbreBinaireRecherche<T>r)
	{
		right=r;
	}
	public boolean existerightchild()
	{
		return right!=null;
	}
	public boolean existeleftchild()
	{
		return left!=null;
	}
	public boolean isroot()
	{
		return root==true;
	}
	public boolean searching(T s, ArbreBinaireRecherche<T>node)
	{
		if(node!=null)
		{
		if(el==s)
		{
			return true;
		}
		searching(s, left);
		searching(s, right);
		System.out.println("Element didn't find");	
		}
		System.out.println("Tree is empty\n");
		return false;
	}
	public void showtree()
	{
		show(this);
	}
	private void show(ArbreBinaireRecherche<T>node)
	{
		if(node!=null)
		{
		show(node.left);
		System.out.println(node.el.toString());
		show(node.right);
		}
}
	public void addel(T newel)
	{
		AddLeaf(newel, this);
	}
	private void AddLeaf(T newelement, ArbreBinaireRecherche<T> MyTree)
	{
		if(MyTree==null)
		{
			MyTree=new ArbreBinaireRecherche<T>(newelement);
		}
		else
		{
			if(MyTree.el.compareTo(newelement)>0)
			{
				if(MyTree.left!=null) AddLeaf(newelement, MyTree.left);
				else
			{
				MyTree.left=new ArbreBinaireRecherche<T>(newelement);
			}
			}
			if(MyTree.el.compareTo(newelement)<0)
			{
				if(MyTree.right!=null)	AddLeaf(newelement, MyTree.right);
				else
			{
				MyTree.right=new ArbreBinaireRecherche<T>(newelement);
			}
			}
		}
		
	}
}

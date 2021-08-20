package bisection;

import net.objecthunter.exp4j.Expression;
import net.objecthunter.exp4j.ExpressionBuilder;


public class Fx {
	private  double tol=0.0;
	private double nOfI;//number of iteration 
	private int n=1;//counter for iterations
	private double a,b,c,fa,fb,fc;
	private double result;
	private boolean cond0=true;//fa*fb<0
	private boolean cond1;//| FC < tol |
	private boolean cond2;//N=4
	private String  root;

	
		//getters and setters
	public double getT()
	{
		return tol;
	}
	public void setT(double tolerance)
	{
		tol=tolerance;
	}
	public int getnOfI()
	{
		
		return (int)nOfI;
	}
	public void setnOfI(double i)
	{
		nOfI=i;

	}

	public int getN()
	{
		return n;
	}
	public void setN(int newN)
	{
		if(n<=newN)
		n++;
	}
	
	public double getA()
	{
		return a;
	}
	public void setA(double newA)
	{
		a=newA;
	}
	
	public double getB()
	{
		return b;
	}
	public void setB(double newB)
	{
		b=newB;
	}
	
	public double getc()
	{
		return c;
	}
	public void setC(double newA,double newB)
	{		
			c=(newA+newB)/2d;
	}
	
	public double getFa()
	{
		return fa;
	}
	public void setFa(double newFa)
	{
		fa=newFa;
	}
	
	public double getFb()
	{
		return fb;
	}
	public void setFb(double newFb)
	{
		fb=newFb;
	}
	
	public double getFc()
	{
		return fc;
	}
	public void setFc(double newFc)
	{
		fc=newFc;
	}
	
	public	boolean getCond1()
	{
		return cond1;
	}
	public void isCond1(boolean newCond1)
	{
		cond1=newCond1;
	}
	
	public boolean getCond2()
	{
		return cond2;
	}
	public void iSCond2(boolean newcond2)
	{
		cond2=newcond2;
	}
	public String getRoot()
	{
		return root;
	}
	public String setRoot(double FA,double FB,double FC,int noI)
	{
		if(getN()==getnOfI()||checkCond1(FC))
			root="---";
		else if(FA>0 && FC<0)
			root="C&A";
		else if(FB>0 && FC<0)	
			root="C&B";
		else if(FA<0 && FC>0)
			root="C&A";
		else
			root="C&B";	
		return root; 
	}
		// method take string double num ( A or B from user )
	public double fOfX(String equation ,double num)
	{
		net.objecthunter.exp4j.Expression exp = new ExpressionBuilder(equation).variables("x").build().setVariable("x", num);
		result = exp.evaluate();
		
		
		return result;
	}
	//check con0 fa*fb<0
	public boolean check(double fa,double fb)
	{
		if((fa*fb)>0)
			cond0=false;
		return cond0;
	}
	public boolean checkCond1(double fc)
	{
		if(Math.abs(fc)>tol)
			cond1=false;
		if(Math.abs(fc)==tol || Math.abs(fc)<tol)
			cond1=true;
		return cond1;	
	}
	public boolean checkCond2(int newI)
	{
		if(n<newI)
		 cond2=false;
		else
			cond2=true;
		return cond2;
	}
	public void updateAbc(double FA,double FB,double FC)
	{
		if(FA<0 & FC<0)
		{
			setA(getc());
			setFa(getFc());
		}
		else if(FA>0 && FC>0)
		{
			setA(getc());
			setFa(getFc());
		}
		else
		{
			setFb(getFc());
			setB(getc());
				
		}	
	}
	public void biSection(String eq)
	{
		//number of iteration
		int nI=(int)((Math.log((getB()-getA())/getT()))/Math.log(2)); 
		setnOfI((Math.log((getB()-getA())/getT()))/Math.log(2));
		System.out.println(" Number of Iterations : "+getnOfI());
		// calc C >> mid point
		setC(getA(),getB());
		//calc  fa , fb and fc
		setFa(fOfX(eq,getA()));
		setFb(fOfX(eq,getB()));
		setFc(fOfX(eq,getc()));
		
		if(check(getFa(),getFb()))
		{
			//print  table header 
			System.out.printf(" %-3s%-12s%-12s%-12s%-12s%-12s%-12s%-12s%-12s%-12s\n","i","A","B","C","F(a)","F(b)","F(c)","cond1","cond2","Root");
			
			for(int i=0;i<nI;i++)
			{			
				
				
					//print roots of each iteration
				setRoot(getFa(),getB(),getFc(),getnOfI());
				
				//print value of i,a,b,c,fa,fb,fc,cond1,cond2,roots
				System.out.printf(" %-3d%-12.4f%-12.4f%-12.4f%-12.4f%-12.4f%-12.4f%-12b%-12b%-12.4s\n",getN(),getA(),getB(),getc(),getFa(),getFb(),getFc(),checkCond1(getFc()),checkCond2(getnOfI()),getRoot());
					// send fixed number of iteration to n to update it 
				setN(getnOfI());
				//update new fa,fc,fb based on conditions
				updateAbc(getFa(), getFb(), getFc());
				//update values of a,b,c to the next print 
				setFa(fOfX(eq,getA()));
				setFb(fOfX(eq, getB()));
				// place after print cause last iteration row will not be printed 
				//check if we found the root ..check cond1 ..True then break 
				if(checkCond1(getFc()))
				{
					System.out.printf(" Root =%-12.4f",getc());
					break;
				}
				//get new Mid point
				setC(getA(),getB());
				setFc(fOfX(eq,getc()));	
				
			}
		}
		else 
			System.out.println(" This equation has no root Cannot be solved using bisection");	
	}
}

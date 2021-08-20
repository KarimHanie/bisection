package bisection;
import java.awt.desktop.PrintFilesEvent;
import java.util.Scanner;
import net.objecthunter.exp4j.Expression;
import net.objecthunter.exp4j.ExpressionBuilder;
public class Bisection {
	public static void main(String[] args) {		
		Scanner sc=new Scanner(System.in);
		Fx fx=new Fx();
		//  take equation ,a,b
		System.out.print("Enter equation: ");
		String equation=sc.nextLine();
		System.out.print("Enter value of A: ");
		fx.setA(sc.nextDouble());
		System.out.print("Enter Value of B: ");
		fx.setB(sc.nextDouble());
		System.out.print("Enter tolerance value :");
		fx.setT(sc.nextDouble());
	 //invoke method bisection send equation to it 
		fx.biSection(equation);
		
		//Try this equation>> pow(x,2)-3
			//a=1 b=2 tol=0.01
			
		/* 
			 any equation will work but to show the result of 
	     	 root at the end 
		 */
		
	}
	
}
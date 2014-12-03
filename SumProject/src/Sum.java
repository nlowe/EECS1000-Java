import java.util.Scanner;

/*
 * Simple program to calculate two sums
 * 
 * Example from the Intro to Java segment of the EECS1000 class
 */
public class Sum
{
	public static void main(String[] args){
		
		Scanner in = new Scanner(System.in);
		
		System.out.print("Enter the first number: ");
		int a = in.nextInt();
		
		System.out.print("Enter the second number: ");
		int b = in.nextInt();
		
		System.out.println("The sum of " + a + " and " + b + " is " + (a+b));
		
		in.close();
		
	}
}

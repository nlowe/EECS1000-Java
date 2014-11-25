/*
 * TravelPlanner.java
 * 
 * Computes and outputs travel time given speed and distance
 * Nathan Lowe - 2014/11/25
 * 
 * Taken from example presented in EECS1000
 */

import java.util.*;
public class TravelPlanner
{
	public static void main(String[] args)
	{
		int speed;
		double distance;
		double time;
		Scanner inp = new Scanner(System.in);
		
		System.out.print("Enter your speed in mph: ");
		speed = inp.nextInt();
		
		System.out.print("Enter your distance in miles: ");
		distance = inp.nextDouble();
		
		time = distance / speed;
		
		System.out.println("At " + speed + " mph, it will take " + time);
		System.out.println(" hours to travel " + distance + " miles.");
		
		inp.close(); //Fix Warning: Resource Leak: 'inp' is never closed
	}
}

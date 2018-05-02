package com;

public class Multiples {
	public static void main(String[] args) {
		// TODO Auto-generated method stub
System.out.println("Sum of  all multiples of 3 or 5 below 1000 is " +sumOfmultiples(1000));
	}
public static int sumOfmultiples(int limit)
{
	int i,sum=0;
	for(i=3;i<limit;i++)
	{
		if(i%3==0 || i%5==0)
		{
			sum+=i;
		}
	}
	return sum;


	}

}

package com;
import java.io.*;
public class primess {
	public static void main(String args[])
	{
		long i=0;
		 long n=600851475143L;
		for(i=n;i>=1;i--)
		{
			if(n%i==0)
			{
				if(isPrime(i))
				{
					System.out.println(i);
					break;
				}
			}
		}
		
	}
	public static boolean isPrime(long n){
		boolean p=true;
		long i;
		for(i=2;i<=Math.sqrt(n);i++)
		{
			if(n%i==0)
			{
				p=false;
				break;
			}
			
		}
		return p;
	}
}

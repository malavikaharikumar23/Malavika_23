package com;

import java.util.Scanner;

public class Demo {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
Scanner sc=new Scanner(System.in);
//System.out.println("Enter limit");
//int limit=sc.nextInt();
System.out.println("sum of even valued terms of fibonacci series"+sumofEven());
sc.close();
	}
public static int sumofEven()
{
int a=0,b=1,c=0,sum=0;
while(c<4000000)
{
c=a+b;
a=b;
b=c;
if(c%2==0)
{
	sum+=c;
}
}
return sum;
}
}

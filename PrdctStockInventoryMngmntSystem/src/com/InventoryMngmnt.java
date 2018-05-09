package com;

//import java.awt.List;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

public class InventoryMngmnt {

	public static void main(String[] args) throws IOException {
		System.out.println("\n\n           Welcome            ");
		System.out.println("\n Enter Username");
		Scanner sc = new Scanner(System.in);
		String username = sc.next();
		System.out.println("Enter Password");
		String password = sc.next();
		String loginFile = "C:\\Users\\user\\Desktop\\login.txt";
		if (login(username, password, loginFile) == 1) {
			System.out.println("Select your Choice : 1.View Items 2.Display Stock 3.Quality Rating");
			int a = sc.nextInt();
			String fileName = "C:\\Users\\user\\Desktop\\items.txt";
			switch (a) {
			case 1:
				System.out.println("Item Details");
				readItems(fileName);
				break;
			case 2:
				System.out.println("stock details");
				Display(fileName);
				break;
			case 3:Rating(fileName);
			break;
			default:
				System.out.println("Invalid choice");

			}

		} else if (login(username, password, loginFile) == 2) {
			System.out.println("admin");
			System.out.println("Select your Choice : 1.Add Items 2.Display Stock 3.Quality Rating");
			int a = sc.nextInt();
			String fileName = "C:\\Users\\user\\Desktop\\items.txt";
			String fileName2="C:\\Users\\user\\Desktop\\rating.txt";
			switch (a) {
			case 1:
				System.out.println("write details");
				writeToTextFile(fileName);
				break;
			case 2:
				System.out.println("stock details");
				Display(fileName);
				break;
			case 3:viewRating(fileName2);
			break;
			
			default:
				System.out.println("ok");

			}
			}

		 else {
			System.out.println("invalid user");

		}
		sc.close();
	}

	private static void viewRating(String fileName) throws IOException{
		
		try (BufferedReader br = new BufferedReader(new FileReader(fileName))) {

			String line;
			while ((line = br.readLine()) != null) {
				System.out.println(line);
			}
		

		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	private static void Rating(String fileName) {
		String filename2="C:\\Users\\user\\Desktop\\rating.txt";
		System.out.println("Enter item whose rating is to submit");
		Scanner sc=new Scanner(System.in);
		String name=sc.next();
		System.out.println("enter rating");
		String rating=sc.next();
		try (BufferedWriter buffer = new BufferedWriter(new FileWriter(filename2, true))) {
			buffer.write(name + "\t"+rating+"\n");

		} catch (IOException e) {
			e.printStackTrace();
		}
		
		
	}

	private static int login(String username, String password, String loginFile) {
		try (BufferedReader br = new BufferedReader(new FileReader(loginFile))) {

			String line;
			while ((line = br.readLine()) != null) {
				 //System.out.println(line);
				String[] pair = line.split(" ");
				if (pair[0].equals(username) && pair[1].equals(password)) {
					if(pair[2].equalsIgnoreCase("user")) {
						return 1;
					}
					else {
						return 2;
			}
				}
		}
			}catch (IOException e) {
			e.printStackTrace();
		}

		return 0;
		
	}

	

	private static void writeToTextFile(String fileName) throws IOException {
		// Files.write(Paths.get(fileName), content.getBytes(),

		System.out.println("enter item no.s");
		Scanner sc1 = new Scanner(System.in);
		int num_choices = sc1.nextInt();
		String[] array = new String[20];
		System.out.println("Enter the name of the product:");
		Scanner sc = new Scanner(System.in);
		// String items=sc.nextLine();
		// String pname = sc1.nextLine();
		for (int i = 0; i < num_choices; i++) {
			System.out.println("Enter choice " + (i + 1) + ":");
			array[i] = sc.nextLine();
			try (BufferedWriter buffer = new BufferedWriter(new FileWriter(fileName, true))) {
				buffer.write(array[i] + "\n");

			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		sc1.close();
		sc.close();
	}

	private static void readItems(String fileName) throws IOException {

		try (BufferedReader br = new BufferedReader(new FileReader(fileName))) {

			String line;
			while ((line = br.readLine()) != null) {
				System.out.println(line);
			}
			Display(fileName);

		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	private static String Display(String fileName) {
		Scanner sc = new Scanner(System.in);
		ArrayList<String> templist= new ArrayList<String>();
		System.out.println("enter item you want to search");
		String name = sc.nextLine();
		System.out.println("enter quantity");
		String quantity = sc.next();
		int qty=Integer.parseInt(quantity);
		try (Stream<String> stream = Files.lines(Paths.get(fileName))) {
		List<String> l=new ArrayList();
		boolean flag1=false;
		boolean flag2=false;
		stream.forEach(s -> templist.add(s));
		 for(String s:templist)
		 {
			 l=split(s);
			 for(int i=0;i<l.size()-1;i++)
			 {
				
				 System.out.println(l.size());
				 if(l.get(i+1).equals(name) && Integer.parseInt(l.get(i + 2)) > 0) {
						flag1 = true;

						if (l.get(i + 2).length() < 2 ) {
	                        System.out.println("< alert on the screen>");
							JFrame f1 = new JFrame();
							JOptionPane.showMessageDialog(f1,
									"This product is running out of stock(less than 10 items left!!)",
									"Alert", JOptionPane.WARNING_MESSAGE);
						}
						else {
							flag2=true;
						}
						
		 
	}
           }
			 }
			 
		 
		 if(flag2==true)
		 {
			 order(name,quantity,fileName);
		 }
		 if(flag1==false)
		 {
			 System.out.println("item not in stock!");
		 }

	}catch (IOException e) {
		e.printStackTrace();
	}
		sc.close();
		return null;
		 }
		
		


	private static void order(String name, String quantity, String fileName) throws IOException {
		try (BufferedReader br = new BufferedReader(new FileReader(fileName))) {

			int newQty = Integer.parseInt(quantity);
			String line;
			while ((line = br.readLine()) != null) {
				// System.out.println(line);
				String[] pair = line.split(" ");

				if (pair[1].equals(name)) {
					int qty = Integer.parseInt(pair[2]);
					int Qty = qty - newQty;
					String Qty1 = String.valueOf(Qty);
					String newContent = pair[2].replace(pair[2], Qty1);
					try (BufferedWriter buffer = new BufferedWriter(new FileWriter(fileName, true))) {
						buffer.write(newContent);

					} catch (IOException e) {
						e.printStackTrace();
					}
				}

			}

		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	public static List<String> split(String str){
	    return Stream.of(str.split(" "))
	      .map (elem -> new String(elem))
	      .collect(Collectors.toList());
	}
}


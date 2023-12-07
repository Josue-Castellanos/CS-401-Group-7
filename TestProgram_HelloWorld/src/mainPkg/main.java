package mainPkg;

import testPackage.*;


public class main {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		System.out.println("Hello World!");
		System.out.println("First New Line");
		System.out.println("Second New Line");
		System.out.println("Third New Line");
		System.out.println("Fourth New Line");
		printStatement();
		System.out.println("My name is Josue");        
		System.exit(0);
		System.out.println("Fifth New Line");
		
		Printer p = new Printer();
		p.newPrint();
		System.out.println("omid");
		
	}
	
	public static void printStatement() {
		System.out.println("Printed from function"); //Nabil's new update.
	}

}

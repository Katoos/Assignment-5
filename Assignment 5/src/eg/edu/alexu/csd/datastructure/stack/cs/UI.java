package eg.edu.alexu.csd.datastructure.stack.cs;
import java.util.Scanner;
public class UI {

	@SuppressWarnings("resource")
	public static void main(String[] args) {
		LinkedListedStack stack = new LinkedListedStack();
		while(true) {
			System.out.println("1: Push\n2: Pop\n3: Peek\n4: Get size\n5: Check if empty");
			Scanner y = new Scanner(System.in);
			int x = y.nextInt();
			System.out.println();
			switch (x) {
			case 1:
				System.out.println("Enter what you want you push");
				Scanner f = new Scanner(System.in);
				String z = f.nextLine();
				stack.push(z);
				System.out.println();
				break;
			case 2:
				System.out.println("Top Element before poping = " + stack.pop());
				System.out.println();
				break;
			case 3:
				System.out.println("Top Element = " + stack.peek());
				System.out.println();
				break;
			case 4:
				System.out.println("Size = " + stack.size());			
				System.out.println();
				break;
			case 5:
				System.out.println("Is Empty = " + stack.isEmpty());
				System.out.println();
				break;
			default:
				System.out.println("Invalid Input");
				System.out.println();
			}
		}
		
	}

}

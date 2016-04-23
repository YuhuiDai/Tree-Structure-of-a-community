import java.io.File;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Scanner;

// I MODIFIED THE COMMUNITY DATA SLIGHTLY, AND YOU CAN CHANGE IT WHICHEVER WAY YOU WANT


public class Main4 {

	public static RelationshipTree tree = new RelationshipTree();

	public static void main(String[] args) {

		try{
			// ########## get info from customer file #############
			Scanner input_community = new Scanner(new File(args[0]));
			ArrayList<Integer> mylist = new ArrayList<Integer>();
			
			while (input_community.hasNext()) {
				String first_name = input_community.nextLine().substring(12);
				//System.out.println(first_name);
				String last_name = input_community.nextLine().substring(11);
				int ssn = Integer.parseInt(input_community.nextLine().substring(5));
				mylist.add(ssn);
				int father_ssn = Integer.parseInt(input_community.nextLine().substring(8));
				int mother_ssn = Integer.parseInt(input_community.nextLine().substring(8));
				String str_friend = input_community.nextLine().substring(9);
				String[] friendlist = str_friend.split(",");
				if (input_community.hasNext())
					input_community.nextLine();
				Person p = new Person(first_name, last_name, ssn, father_ssn, mother_ssn, friendlist);
				tree.insert(p);
			}	
			for (int i= 0; i < mylist.size(); i++) {
				tree.find_mutual_friend(mylist.get(i));
			}
			//System.out.println("found");
			
			Node temp = tree.root;
			
			tree.inOrder(temp);
			//tree.callEverything(9);
			
			
			// ######### get questions from queries file ##############
			Scanner input_queries = new Scanner(new File(args[1]));
			
			// *****output.txt is professor's file***my result is going to be printed in output2*** just to check
			PrintWriter j = new PrintWriter(new File("output2.txt"));
			while (input_queries.hasNextLine()) {
				
				String str = input_queries.nextLine();
				String[] ask = str.split(" ");
				String strr = ask[0];
				String replacedStr = strr.toLowerCase().replaceAll("-", "_");
				if (ask.length == 2 ) {
					int ssn = Integer.parseInt(ask[1]);
					System.out.println(replacedStr);
					if (replacedStr.equals("name_of")) {
						j.print(str + ":" + tree.getFullName(ssn) + "\n");
					} else if (replacedStr.equals("mother_of")) {
						j.print(str + ":" +  tree.getMother(ssn) + "\n");
					} else if (replacedStr.equals("father_of")) {
						j.print(str + ":" + tree.getFather(ssn) + "\n");
					} else if (replacedStr.equals("half_siblings_of")) {
						j.print(str + ":" + tree.find_half_sibling(ssn) + "\n");
					} else if (replacedStr.equals("full_siblings_of")) {
						j.print(str + ":" + tree.find_full_sibling(ssn) + "\n");
					} else if (replacedStr.equals("children_of")) {
						j.print(str + ":" + tree.find_children(ssn) + "\n");
					} else if (replacedStr.equals("mutual_friends_of")) {
						j.print(str + ":" + tree.find_mutual_friend(ssn) + "\n");
					} else if (replacedStr.equals("inverse_friends_of")) {
						j.print(str + ":" + tree.find_inverse_friend(ssn) + "\n");
					} else {
						System.out.println(">.<");
					}
					
				} else if (ask.length == 1) {
			
					String name = tree.getFullName(tree.most_popular.ssn);
					j.print(str + ":" + name + "\n");
				} else {
					System.out.println("Error");
				}
				
				
			}
			 
			
			j.close();
			
		}
		catch(Exception e){
			e.printStackTrace();
		}
	}

}

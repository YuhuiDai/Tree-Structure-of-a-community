import java.util.ArrayList;

public class RelationshipTree {
	
	   public Node root;
	   public static Person most_popular;

	   public RelationshipTree()    
	   
	      { 
		  root = null;
	      most_popular = null;
	      }  
	   
	   public void sameParents(int ssn, Node cur, ArrayList<Person> same){

		   if (cur == null)
				   return;
		   sameParents(ssn, cur.leftChild, same);
//		   System.out.println("hi");
		   
		   if (cur.member.father_ssn == ssn || cur.member.mother_ssn == ssn) {
			   same.add(cur.member);
			   
		   }
	       sameParents(ssn, cur.rightChild, same);
	   }
	   
	   public String find_full_sibling(int ssn){
		   Person p = find(ssn); 
		   String final_string = "";
		   
		   ArrayList<Person> father_children = new ArrayList<Person>();
		   ArrayList<Person> mother_children = new ArrayList<Person>();
		   sameParents(p.father_ssn, root, father_children);
		   sameParents(p.mother_ssn, root, mother_children);
		   
		   for (int i= 0; i< father_children.size(); i++){
			   if (mother_children.contains(father_children.get(i))){
				   // eliminate yourself from the sibling list
				   if (father_children.get(i).ssn != ssn){
					   final_string += getFullName(father_children.get(i).ssn) + ", ";
				   }
			   }
		   }
		   
		   if (final_string.length() == 0) {
			   final_string += "UNAVAILABLE  ";
		   }

		   final_string = final_string.substring(0, final_string.length()-2);
		   return final_string;
	   }
	   
	   
	   public String find_half_sibling(int ssn){
		   Person p = find(ssn);
		   String final_string = "";
		   
		   ArrayList<Person> father_children = new ArrayList<Person>();
		   ArrayList<Person> mother_children = new ArrayList<Person>();
		   sameParents(p.father_ssn, root, father_children);
		   sameParents(p.mother_ssn, root, mother_children);

		   
		   // check both from father's side and mother's side
			   for (int i= 0; i< father_children.size(); i++){
				   if (mother_children.contains(father_children.get(i)) != true){
					   final_string += getFullName(father_children.get(i).ssn) + ", ";
				   }
			   }
		   
			   for (int i= 0; i< mother_children.size(); i++){
				   if (father_children.contains(mother_children.get(i)) != true){
					   final_string += getFullName(mother_children.get(i).ssn) + ", ";
				   }
			   }
		   
		   
		   if (final_string.length() == 0) {
			   final_string += "UNAVAILABLE ";
		   }

		   final_string = final_string.substring(0, final_string.length()-2);
		   return final_string;
	   }
	   
	   public String find_children(int ssn){
		   
		   String final_string = "";
		   ArrayList<Person> children = new ArrayList<Person>();
		   sameParents(ssn, root, children);
		   for (int i=0; i< children.size(); i++) {
			   final_string += getFullName(children.get(i).ssn) + ", ";
		   }
		   
		   if (final_string.length() == 0) {
			   final_string += "UNAVAILABLE  ";
		   }
		   final_string = final_string.substring(0, final_string.length()-2);
		   return final_string;
	   }
	   
	   
	   public String getFather(int ssn) {
		   	Person p = find(ssn);
			int father_ssn = p.father_ssn;
			
			String father_name = getFullName(father_ssn);
			return father_name;
		}
	   
	   public String getMother(int ssn) {
		   	Person p = find(ssn);
			int mother_ssn = p.mother_ssn;
			
			String mother_name = getFullName(mother_ssn);
			return mother_name;
		}
	   
	   
	   public boolean isYourFriend (Person me, Person p) {
		   boolean answer = false;
		   
		   int find_ssn = p.ssn;
		   for (int j = 0; j < me.friendlist.length; j++) {
			   if (Integer.parseInt(me.friendlist[j]) == find_ssn){
				   answer = true;
				   break;
			   }   
		   }
		   return answer;
			
	   }
	   
	   public String find_mutual_friend (int ssn) {
		   Person me = find(ssn);
		   String final_str = "";
		   String[] myfriendlist= me.friendlist;
		   for (int i = 0; i < myfriendlist.length; i++) {
			   Person p = find(Integer.parseInt(myfriendlist[i]));
			   if (isYourFriend(p, me)) {
				   final_str += getFullName(p.ssn) + ", ";
				   me.mutual_friends ++;
			   }
		   }
		   
		   if (final_str.length() == 0) {
			   final_str += "UNAVAILABLE  ";
		   }
		   final_str = final_str.substring(0, final_str.length()-2);
		   return final_str;
	   }
	   
	   public void find_inverseF(int ssn, Node cur, ArrayList<Person> inv_friend){
		   Person me = find(ssn);
		   if (cur == null)
				   return;
		   find_inverseF(ssn, cur.leftChild, inv_friend);
		   if (isYourFriend(cur.member, me) == true) {
			   inv_friend.add(cur.member);
		   }
	       find_inverseF(ssn, cur.rightChild, inv_friend);
	   }
	   
	   public String find_inverse_friend (int ssn) {
		   
		   String final_str = "";
		   ArrayList<Person> inv_friend = new ArrayList<Person>();
		   find_inverseF(ssn, root, inv_friend);
		   for (int i=0; i< inv_friend.size(); i++) {
			   final_str += getFullName(inv_friend.get(i).ssn) + ", ";
		   }
		   
		   if (final_str.length() == 0) {
			   final_str += "UNAVAILABLE  ";
		   }
		   final_str = final_str.substring(0, final_str.length()-2);
		   return final_str;
	   }

	   public Person find(int key)      
	      {                           
	      Node current = root;              
	      while(current.member.getSsn() != key)       
	         {
	         if(key < current.member.getSsn())        
	            current = current.leftChild;
	         else                            
	            current = current.rightChild;
	         if(current == null)             
	            return null;                 
	         }
	      return current.member;                    
	      }  

	   public void insert(Person p)
	      {
	      Node newNode = new Node();
	      newNode.member = p;
	      int id = p.ssn;
	      
	      if(root==null)                
	         root = newNode;
	      else                          
	         {
	         Node current = root;       
	         Node parent;
	         while(true)                
	            {
	            parent = current;
	            if(id < current.member.getSsn())  
	               {
	               current = current.leftChild;
	               if(current == null)  
	                  {                
	                  parent.leftChild = newNode;
	                  return;
	                  }
	               }  
	            else                    
	               {
	               current = current.rightChild;
	               if(current == null)  
	                  {                 
	                  parent.rightChild = newNode;
	                  return;
	                  }
	               }  
	            }  
	         }  
	      } 
	   
	   public String getFullName(int ssn) {
		   	Person p = find(ssn);
			String full = p.first_name + " " + p.last_name;
			return full;
		}
	   
	   void inOrder(Node localRoot)
	      {
	      if(localRoot != null)
	         {
	         inOrder(localRoot.leftChild);
	         if (most_popular == null){
	        	 most_popular = localRoot.member;
	        	 //System.out.println("gere");
	         } else {
	        	 if (most_popular.mutual_friends < localRoot.member.mutual_friends){
	        		 most_popular = localRoot.member;
	        		 //System.out.println("hello");
	        	 }
	         } 
	         
	         //System.out.println(getFullName(most_popular.ssn));
	         
	         inOrder(localRoot.rightChild);
	         }
	      }
	   
	   public void callEverything(int ssn) {
		   //System.out.println(find_full_sibling (ssn));
		   System.out.println(find_inverse_friend(ssn));
		   
	   }
}


public class Person {
	public String first_name;
	public String last_name;
	public int ssn;
	public int father_ssn;
	public int mother_ssn;
	public String[] friendlist;
	public int mutual_friends;
	
	

	public int getSsn() {
		return ssn;
	}

	public Person(String first_name, String last_name, int ssn, int father_ssn, int mother_ssn, String[] friendlist) {
		this.first_name = first_name;
		this.last_name = last_name;
		this.ssn = ssn;
		this.father_ssn = father_ssn;
		this.mother_ssn = mother_ssn;
		this.friendlist= friendlist;
		this.mutual_friends = 0;
	}
	
	
}

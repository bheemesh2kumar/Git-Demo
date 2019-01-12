package Com.API.Postdata;

public class Users {
	
	//pojo- plain old java object 
	
	String name;
	String leader;
	
	
	
	public Users()
	{
		
	}
	
	
	public Users(String name,String leader)
	{
		this.name=name;
		this.leader=leader;
	}


	public String getName() {
		return name;
	}


	public void setName(String name) {
		this.name = name;
	}


	public String getLeader() {
		return leader;
	}


	public void setLeader(String leader) {
		this.leader = leader;
	}

}

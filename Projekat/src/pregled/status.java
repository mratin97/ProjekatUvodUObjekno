package pregled;

public enum status {

	a("Zakazan"),
	b("Otkazan"),
	c("Zavrsen");
	
	private final String Status;
	

	status(String TrenStatus){
		
		Status=TrenStatus;
		
		
	}


	public String getStatus() {
		return Status;
	}
	
}

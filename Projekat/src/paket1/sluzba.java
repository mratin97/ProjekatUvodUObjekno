package paket1;

public enum sluzba {
	
	a("Sluzba opste medicine","1"),
	b("Sluzba zdravstvene zastite dece","2"),
	c("Stomatoloska sluzba","3"),
	d("Sluzba zdravstvene zaštite radnika","4"),
	e("Sluzba za pravne i ekonomske poslove","5"),
	f("Sluzba za tehnicke poslove","6");
	
	private final String Sluzbe;
	private final String broj;

	sluzba(String sluzbe,String brojevi){
		
		Sluzbe= sluzbe;
		broj = brojevi;
		
	}

	public String getSluzbe() {
		return Sluzbe;
	}

	public String getBroj() {
		return broj;
	}
	
}

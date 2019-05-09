package pregled;

public class Racun {

	
	protected Pregled pacijent;
	protected Pregled idpregleda;
	protected int cena;
	protected boolean placeno;
	
	
	public Racun(Pregled pacijent, Pregled idpregleda, int cena, boolean placeno) {
		super();
		this.pacijent = pacijent;
		this.idpregleda = idpregleda;
		this.cena = cena;
		this.placeno = placeno;
	}


	public Pregled getPacijent() {
		return pacijent;
	}


	public void setPacijent(Pregled pacijent) {
		this.pacijent = pacijent;
	}


	public Pregled getIdpregleda() {
		return idpregleda;
	}


	public void setIdpregleda(Pregled idpregleda) {
		this.idpregleda = idpregleda;
	}


	public int getCena() {
		return cena;
	}


	public void setCena(int cena) {
		this.cena = cena;
	}


	public boolean isPlaceno() {
		return placeno;
	}


	public void setPlaceno(boolean placeno) {
		this.placeno = placeno;
	}


	@Override
	public String toString() {
		return "Racun [pacijent=" + pacijent + ", idpregleda=" + idpregleda + ", cena=" + cena + ", placeno=" + placeno
				+ "]";
	}

	
	
}

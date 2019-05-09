package paket1;



public class Lekar extends Zaposleni {
	
	protected String specijalizacija;
	sluzba ImeSluzbe;
	public Lekar() {
		
		this.specijalizacija="";
		
	}

	public Lekar(String ime, String prezime, String jmbg, String adresa, String brojTelefona, String korisnickoIme,
			String lozinka, String pol,sluzba imesluzbe, int plata,String specijalizacija) {
		super(ime, prezime, jmbg, adresa, brojTelefona, korisnickoIme, lozinka, pol, plata);
		this.specijalizacija= specijalizacija;
		this.ImeSluzbe=imesluzbe;
	}

	public String getSpecijalizacija() {
		return specijalizacija;
	}

	public void setSpecijalizacija(String specijalizacija) {
		this.specijalizacija = specijalizacija;
	}

	@Override
	public String toString() {
		return "Lekar [specijalizacija=" + specijalizacija + ", plata=" + plata + ", ime=" + ime + ", prezime="
				+ prezime + ", jmbg=" + jmbg + ", adresa=" + adresa + ", brojTelefona=" + brojTelefona
				+ ", korisnickoIme=" + korisnickoIme + ", lozinka=" + lozinka + ", pol=" + pol + "]";
	}
	



	
}

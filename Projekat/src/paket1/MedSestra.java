package paket1;

public class MedSestra extends Zaposleni {

	public MedSestra(String ime, String prezime, String jmbg, String adresa, String brojTelefona, String korisnickoIme,
			String lozinka, String pol, int plata) {
		super(ime, prezime, jmbg, adresa, brojTelefona, korisnickoIme, lozinka, pol, plata);
		
	}

	public MedSestra() {
		this.ime="";
		this.prezime="";
		this.jmbg="";
		this.adresa="";
		this.brojTelefona="";
		this.korisnickoIme="";
		this.lozinka="";
		this.pol="";
		this.plata=0;
		
	}

	@Override
	public String toString() {
		return "Zaposleni   ime=" + ime + ", prezime=" + prezime + ", jmbg=" + jmbg + ", adresa="
				+ adresa + ", brojTelefona=" + brojTelefona + ", korisnickoIme=" + korisnickoIme + ", lozinka="
				+ lozinka + ", pol=" + pol + " plata=" + plata +"";
	}

	
	
	
	
}

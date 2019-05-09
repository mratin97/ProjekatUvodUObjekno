package paket1;

public class Zaposleni extends Korisnik {
	
	protected int plata;

	public Zaposleni() {
		
		
	}

	public Zaposleni(String ime, String prezime, String jmbg, String adresa, String brojTelefona, String korisnickoIme,
			String lozinka, String pol, int plate) {
		super(ime, prezime, jmbg, adresa, brojTelefona, korisnickoIme, lozinka, pol);
		this.plata=plate;
		
	}

	public int getPlata() {
		return plata;
	}

	public void setPlata(int plata) {
		this.plata = plata;
	}

	@Override
	public String toString() {
		return "Zaposleni   ime=" + ime + ", prezime=" + prezime + ", jmbg=" + jmbg + ", adresa="
				+ adresa + ", brojTelefona=" + brojTelefona + ", korisnickoIme=" + korisnickoIme + ", lozinka="
				+ lozinka + ", pol=" + pol + "plata=" + plata +"]";
	}

	

}

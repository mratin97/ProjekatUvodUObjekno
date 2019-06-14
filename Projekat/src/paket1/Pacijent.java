package paket1;

public class Pacijent extends Korisnik{
	
	
	Knjizica podaciOKnjizici;
	String izabraniLekar;
	public Pacijent() {
	
			this.izabraniLekar= "";
	
		
		}
	
	public Pacijent(String ime, String prezime, String jmbg, String adresa, String brojTelefona, String korisnickoIme,
			String lozinka, String pol,String izabranlekar,Knjizica podaciOKnjizici) {
		super(ime, prezime, jmbg, adresa, brojTelefona, korisnickoIme, lozinka, pol);
			this.izabraniLekar=izabranlekar;
			this.podaciOKnjizici= podaciOKnjizici;
	}
	

	public String getIzabraniLekar() {
		return izabraniLekar;
	}

	public void setIzabraniLekar(String izabranilekar) {
		this.izabraniLekar = izabranilekar;
	}
	public Knjizica getPodaciOKnjizici() {
		return podaciOKnjizici;
	}
	@Override
	public String toString() {
		return "Pacijent [izabraniLekar=" + izabraniLekar + ", ime=" + ime + ", prezime=" + prezime + ", jmbg=" + jmbg
				+ ", adresa=" + adresa + ", brojTelefona=" + brojTelefona + ", korisnickoIme=" + korisnickoIme
				+ ", lozinka=" + lozinka + ", pol=" + pol + "]";
	}


	
	
	
	
	
}

package paket1;

public class Pacijent extends Korisnik{
	
	

	String izabraniLekar;
	public Pacijent() {
	
			this.izabraniLekar= "";
	
		
		}
	
	public Pacijent(String ime, String prezime, String jmbg, String adresa, String brojTelefona, String korisnickoIme,
			String lozinka, String pol,String izabranlekar) {
		super(ime, prezime, jmbg, adresa, brojTelefona, korisnickoIme, lozinka, pol);
			this.izabraniLekar=izabranlekar;
		
	}
	

	public String getIzabraniLekar() {
		return izabraniLekar;
	}

	public void setIzabraniLekar(String izabranilekar) {
		this.izabraniLekar = izabranilekar;
	}

	@Override
	public String toString() {
		return "Pacijent [izabraniLekar=" + izabraniLekar + ", ime=" + ime + ", prezime=" + prezime + ", jmbg=" + jmbg
				+ ", adresa=" + adresa + ", brojTelefona=" + brojTelefona + ", korisnickoIme=" + korisnickoIme
				+ ", lozinka=" + lozinka + ", pol=" + pol + "]";
	}


	
	
	
	
	
}

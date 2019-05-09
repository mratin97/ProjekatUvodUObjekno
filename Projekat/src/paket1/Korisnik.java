package paket1;
//import java.io.BufferedWriter;
//import java.io.FileWriter;
//import java.io.IOException;

public abstract class Korisnik {
	
	protected String ime;
	protected String prezime;
	protected String jmbg;
	protected String adresa;
	protected String brojTelefona;
	protected String korisnickoIme;
	protected String lozinka;
	protected String pol;
	
	
	public Korisnik() {
		this.ime="";
		this.prezime="";
		this.jmbg="";
		this.adresa="";
		this.brojTelefona="";
		this.korisnickoIme="";
		this.lozinka="";
		this.pol="";
		
		
	}


	public Korisnik(String ime, String prezime, String jmbg, String adresa, String brojTelefona, String korisnickoIme,
			String lozinka, String pol) {
		super();
		this.ime = ime;
		this.prezime = prezime;
		this.jmbg = jmbg;
		this.adresa = adresa;
		this.brojTelefona = brojTelefona;
		this.korisnickoIme = korisnickoIme;
		this.lozinka = lozinka;
		this.pol = pol;
	}


	public String getIme() {
		return ime;
	}


	public void setIme(String ime) {
		this.ime = ime;
	}


	public String getPrezime() {
		return prezime;
	}


	public void setPrezime(String prezime) {
		this.prezime = prezime;
	}


	public String getJmbg() {
		return jmbg;
	}


	public void setJmbg(String jmbg) {
		this.jmbg = jmbg;
	}


	public String getAdresa() {
		return adresa;
	}


	public void setAdresa(String adresa) {
		this.adresa = adresa;
	}


	public String getBrojTelefona() {
		return brojTelefona;
	}


	public void setBrojTelefona(String brojTelefona) {
		this.brojTelefona = brojTelefona;
	}


	public String getKorisnickoIme() {
		return korisnickoIme;
	}


	public void setKorisnickoIme(String korisnickoIme) {
		this.korisnickoIme = korisnickoIme;
	}


	public String getLozinka() {
		return lozinka;
	}


	public void setLozinka(String lozinka) {
		this.lozinka = lozinka;
	}


	public String getPol() {
		return pol;
	}


	public void setPol(String pol) {
		this.pol = pol;
	}


	@Override
	public String toString() {
		return "Korisnik [ime=" + ime + ", prezime=" + prezime + ", jmbg=" + jmbg + ", adresa=" + adresa
				+ ", brojTelefona=" + brojTelefona + ", korisnickoIme=" + korisnickoIme + ", lozinka=" + lozinka
				+ ", pol=" + pol + "]";
	}

	
}


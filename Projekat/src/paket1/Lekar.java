package paket1;

import java.util.ArrayList;

import pregled.Pregled;

public class Lekar extends Zaposleni {
	private ArrayList<Pregled> pregledi;
	protected String specijalizacija;
	sluzba ImeSluzbe;
	public Lekar() {
		
		this.specijalizacija="";
		
	}

	public Lekar(String ime, String prezime, String jmbg, String adresa, String brojTelefona, String korisnickoIme,
			String lozinka, String pol,String imesluzbe, int plata,String specijalizacija) {
		super(ime, prezime, jmbg, adresa, brojTelefona, korisnickoIme, lozinka, pol, plata);
		this.specijalizacija= specijalizacija;
		
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
	
	
	public String listaPregleda(){
		
		return pregledi.toString();
	}

	
}

package domZdravlja;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;


import paket1.Lekar;
import paket1.Korisnik;
import paket1.MedSestra;
import paket1.Pacijent;
import paket1.Zaposleni;
import pregled.Pregled;

public class DomZdravlja {

	private ArrayList<MedSestra> sestre;
	private ArrayList<Pacijent> pacijent;
	private ArrayList<Lekar> lekar;
	
	public DomZdravlja(){
		
		this.sestre=new ArrayList<MedSestra>();
	}
	public ArrayList<MedSestra> getSestre() {
		return sestre;
	}

	public void dodajProdavca(MedSestra sestra) {
		this.sestre.add(sestra);
	}

	public void obrisiProdavca(MedSestra sestra) {
		this.sestre.remove(sestra);
	}

	public MedSestra nadjiProdavca(String korisnickoIme) {
		for (MedSestra sestra : sestre) {
			if (sestra.getKorisnickoIme().equals(korisnickoIme)) {
				return sestra;
			}
		}
		return null;
	}
	public void ucitajZaposlene(String medsestra) {
		try {
			File sestreFile = new File("src/fajlovi/" + medsestra);
			BufferedReader br = new BufferedReader(new FileReader(sestreFile));
			String line = null;
			while ((line = br.readLine()) != null) {
				String[] split = line.split("\\|");
				String ime = split[0];
				String prezime = split[1];
				String jmbg = split[2];
				String adresa = split[3];
				String brojTelefona = split[4];
				String korisnickoIme = split[5];
				String lozinka = split[6];
				String pol = split[7];
				String platastring=split[8];
				int plata=Integer.parseInt(platastring);
				MedSestra sestra = new MedSestra(ime, prezime, jmbg, adresa, brojTelefona, korisnickoIme, lozinka, pol, plata);
				sestre.add(sestra);
			}
			br.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void snimiZaposlene(String medsestra) {
		try {
			File file = new File("src/fajlovi/" + medsestra);
			BufferedWriter br = new BufferedWriter(new FileWriter(file));
			String sadrzaj = "";
			for (MedSestra sestra : sestre) {
				sadrzaj += sestra.getIme() + "|" + sestra.getPrezime() + "|" + sestra.getJmbg() + "|"
						+ sestra.getAdresa() + "|" + sestra.getBrojTelefona()+"|" + sestra.getKorisnickoIme()+"|" + sestra.getLozinka()+"|" + sestra.getPol()+
						"|"+sestra.getPlata()+"\n";
			}
			br.write(sadrzaj);
			br.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
}

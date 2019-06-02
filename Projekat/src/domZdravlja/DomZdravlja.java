package domZdravlja;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.sql.Date;
import java.util.ArrayList;


import paket1.Lekar;
import paket1.Knjizica;
import paket1.Korisnik;
import paket1.MedSestra;
import paket1.Pacijent;
import paket1.Zaposleni;
import paket1.sluzba;
import pregled.Pregled;
import pregled.status;

import java.util.*;
public class DomZdravlja {

	private ArrayList<MedSestra> sestre;
	private ArrayList<Pacijent> pacijenti;
	private ArrayList<Lekar> lekar;
	private ArrayList<Pregled> pregledi;
	private ArrayList<Knjizica> knjizica;
	
	public DomZdravlja(){
		this.pregledi=new ArrayList<Pregled>();
		this.sestre=new ArrayList<MedSestra>();
		this.lekar=new ArrayList<Lekar>();
		this.pacijenti=new ArrayList<Pacijent>();
		this.knjizica=new ArrayList<Knjizica>();
		//Lekar ime= lekar.get(0);
		//System.out.println (ime.getIme());
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
	public ArrayList<Lekar> getLekar() {
		return lekar;
	}



	public void obrisiLekara(Lekar lekari) {
		this.lekar.remove(lekari);
	}

	public MedSestra nadjiProdavca(String korisnickoIme) {
		for (MedSestra sestra : sestre) {
			if (sestra.getKorisnickoIme().equals(korisnickoIme)) {
				return sestra;
			}
		}
		return null;
	}
	public Lekar nadjiLekara(String korisnickoIme) {
		for (Lekar lekar : lekar) {
			if (lekar.getKorisnickoIme().equals(korisnickoIme)) {
				return lekar;
			}
		}
		return null;
	}
	public Pacijent nadjiPacijenta(String korisnickoIme) {
		for (Pacijent pacijent : pacijenti) {
			if (pacijent.getKorisnickoIme().equals(korisnickoIme)) {
				return pacijent;
			}
		}
		return null;
	}
	public Knjizica nadjiKnjizicu(String broj) {
		for (Knjizica knjizice : knjizica) {
			if (knjizice.getBroj().equals(broj)) {
				return knjizice;
			}
		}
		return null;
	}
	public void ucitajLekare(String medsestra) {
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
				String sluzba= split[8];
				String platastring=split[9];
				String specijalizacija=split[10];
				int plata=Integer.parseInt(platastring);
				Lekar lekari = new Lekar(ime, prezime, jmbg, adresa, brojTelefona, korisnickoIme, lozinka, pol,sluzba, plata,specijalizacija);
				lekar.add(lekari);
			}
			br.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	public void ucitajPacijente(String medsestra) {
		try {
			File sestreFile = new File("src/fajlovi/" + medsestra);
			BufferedReader br = new BufferedReader(new FileReader(sestreFile));
			String line = null;
			Lekar imelekara= lekar.get(0);
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
				String izabraniLekar=imelekara.getKorisnickoIme();
				String knji=split[8];
				Knjizica x=nadjiKnjizicu(knji);
				
				Pacijent pacijent = new Pacijent(ime, prezime, jmbg, adresa, brojTelefona, korisnickoIme, lozinka, pol,izabraniLekar,x);
				pacijenti.add(pacijent);
			}
			br.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	public void ucitajPregled(String medsestra) {
		try {
			File sestreFile = new File("src/fajlovi/" + medsestra);
			BufferedReader br = new BufferedReader(new FileReader(sestreFile));
			String line = null;
			Pacijent ime= pacijenti.get(0);
			Lekar imelekara= lekar.get(0);
			while ((line = br.readLine()) != null) {
				String[] split = line.split("\\|");
				String pacijentime = split[0];
				Pacijent x=nadjiPacijenta(pacijentime);
				String opis = split[1];
				String status = split[2];
				String idPregleda = split[3];
				String termin = split[4];
				String doktorime= split[5];
				Lekar y=nadjiLekara(doktorime);
				
				
				Pregled pregled = new Pregled(x, opis, status, idPregleda, termin,y );
				pregledi.add(pregled);
			}
			br.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	public void ucitajKnjizice(String medsestra) {
		try {
			File sestreFile = new File("src/fajlovi/" + medsestra);
			BufferedReader br = new BufferedReader(new FileReader(sestreFile));
			String line = null;
			Pacijent ime= pacijenti.get(0);
			Lekar imelekara= lekar.get(0);
			while ((line = br.readLine()) != null) {
				String[] split = line.split("\\|");
				String pacijentime = split[3];
				Pacijent x=nadjiPacijenta(pacijentime);
				String broj = split[0];
				String datum = split[1];
				String kategorija = split[2];
				
				
				
				
				
				Knjizica knjizice = new Knjizica(broj, datum, kategorija, x );
				knjizica.add(knjizice);
			}
			br.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	public void ucitajSestre(String medsestra) {
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
				String sluzba= split[8];
				String platastring=split[9];
				int plata=Integer.parseInt(platastring);
				MedSestra sestra = new MedSestra(ime, prezime, jmbg, adresa, brojTelefona, korisnickoIme, lozinka, pol,sluzba, plata);
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
	
	
	public void snimiLekare(String medsestra) {
		try {
			File file = new File("src/fajlovi/" + medsestra);
			BufferedWriter br = new BufferedWriter(new FileWriter(file));
			String sadrzaj = "";
			for (Lekar lekari : lekar) {
				sadrzaj += lekari.getIme() + "|" + lekari.getPrezime() + "|" + lekari.getJmbg() + "|"
						+ lekari.getAdresa() + "|" + lekari.getBrojTelefona()+"|" + lekari.getKorisnickoIme()+"|" + lekari.getLozinka()+"|" + lekari.getPol()+
						"|"+lekari.getPlata()+"|"+lekari.getSpecijalizacija()+"\n";
			}
			br.write(sadrzaj);
			br.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	 public void DodajSestru() throws IOException {
 		
 		BufferedWriter bw = new BufferedWriter( new FileWriter("src/fajlovi/medicinskasestra.txt",true) );
 		Scanner strInput = new Scanner(System.in);
 		
 		String ime, prezime, jmbg, adresa, brojTelefona, korisnickoIme, lozinka, pol, sluzba,plata;
 		
 		System.out.print("Enter the Employee ime: ");
 		ime= strInput.nextLine();
 		System.out.print("Enter the Employee prezime: ");
 		prezime = strInput.nextLine();
 		System.out.print("Enter the Employee jmbg: ");
 		jmbg = strInput.nextLine();
 		System.out.print("Enter the Employee adresa: ");
 		adresa = strInput.nextLine();    
 		System.out.print("Enter the Employee brojTelefona: ");
 		brojTelefona = strInput.nextLine();   
 		System.out.print("Enter the Employee korisnickoIme : ");
 		korisnickoIme = strInput.nextLine();   
 		System.out.print("Enter the Employee lozinka:");
 		lozinka = strInput.nextLine(); 
 		System.out.print("Enter the Employee pol:");
 		pol = strInput.nextLine(); 
 		System.out.print("Enter the Employee sluzba:");
 		sluzba=strInput.nextLine();
 		System.out.print("Enter the Employee plata:");
 		plata = strInput.nextLine(); 
 		   		
 		bw.write(ime+"|"+prezime+"|"+jmbg+"|"+adresa+"|"+brojTelefona+"|"+korisnickoIme+"|"+lozinka+"|"+pol+"|"+sluzba+"|"+plata);
 		bw.flush();
 		bw.newLine();
 		bw.close();		
 	
 }
	 public void DodajLekara() throws IOException {
	 		
	 		BufferedWriter bw = new BufferedWriter( new FileWriter("src/fajlovi/lekar.txt",true) );
	 		Scanner strInput = new Scanner(System.in);
	 		
	 		String ime, prezime, jmbg, adresa, brojTelefona, korisnickoIme, lozinka, pol, sluzba,plata,spec;
	 		
	 		System.out.print("Unesite ime: ");
	 		ime= strInput.nextLine();
	 		System.out.print("Unesite prezime: ");
	 		prezime = strInput.nextLine();
	 		System.out.print("Unesite jmbg: ");
	 		jmbg = strInput.nextLine();
	 		System.out.print("Unesite adresa: ");
	 		adresa = strInput.nextLine();    
	 		System.out.print("Unesite brojTelefona: ");
	 		brojTelefona = strInput.nextLine();   
	 		System.out.print("Unesite korisnickoIme : ");
	 		korisnickoIme = strInput.nextLine();   
	 		System.out.print("Unesite lozinka:");
	 		lozinka = strInput.nextLine(); 
	 		System.out.print("Unesite pol:");
	 		pol = strInput.nextLine(); 
	 		System.out.print("Unesite sluzba:");
	 		sluzba=strInput.nextLine();
	 		System.out.print("EUnesite plata:");
	 		plata = strInput.nextLine(); 
	 		System.out.print("Unesite specijalizaciju:");
	 		spec = strInput.nextLine(); 
	 		   		
	 		bw.write(ime+"|"+prezime+"|"+jmbg+"|"+adresa+"|"+brojTelefona+"|"+korisnickoIme+"|"+lozinka+"|"+pol+"|"+sluzba+"|"+plata+"|"+spec);
	 		bw.flush();
	 		bw.newLine();
	 		bw.close();		
	 	
	 }
	 
	 public void DodajPacijenta() throws IOException {
	 		
	 		BufferedWriter bw = new BufferedWriter( new FileWriter("src/fajlovi/Pacijent.txt",true) );
	 		Scanner strInput = new Scanner(System.in);
	 		Lekar imelekara= lekar.get(0);
	 		String ime, prezime, jmbg, adresa, brojTelefona, korisnickoIme, lozinka, pol;
	 		Knjizica broj= knjizica.get(0);
	 		System.out.print("Unesite ime: ");
	 		ime= strInput.nextLine();
	 		System.out.print("Unesite prezime: ");
	 		prezime = strInput.nextLine();
	 		System.out.print("Unesite jmbg: ");
	 		jmbg = strInput.nextLine();
	 		System.out.print("Unesite adresa: ");
	 		adresa = strInput.nextLine();    
	 		System.out.print("Unesite brojTelefona: ");
	 		brojTelefona = strInput.nextLine();   
	 		System.out.print("Unesite korisnickoIme : ");
	 		korisnickoIme = strInput.nextLine();   
	 		System.out.print("Unesite lozinka:");
	 		lozinka = strInput.nextLine(); 
	 		System.out.print("Unesite pol:");
	 		pol = strInput.nextLine(); 
	 		
	 		
	 		bw.write(ime+"|"+prezime+"|"+jmbg+"|"+adresa+"|"+brojTelefona+"|"+korisnickoIme+"|"+lozinka+"|"+pol+"|"+imelekara.getIme()+"|"+broj.getBroj());
	 		bw.flush();
	 		bw.newLine();
	 		bw.close();		
	 	
	 }
	 public void DeleteRecordByID() throws IOException {
 		Scanner strInput =  new Scanner(System.in);
 		String korisnickoIme, record;
 		
 		
 		File tempDB = new File("src/fajlovi/medicinskasestraTemp.txt");
 		File db = new File("src/fajlovi/medicinskasestra.txt");
 		
 		
 		BufferedReader br = new BufferedReader( new FileReader( db ) );
 		BufferedWriter bw = new BufferedWriter( new FileWriter( tempDB ) );
 		
 		
 		System.out.println("\t\t Delete Employee Record\n");
 		
 		System.out.println("Enter the Employee ID: ");
 		korisnickoIme =  strInput.nextLine();
 		
 		
 		while( ( record = br.readLine() ) != null ) {
 			
 			
 			if( record.contains(korisnickoIme) ) 
 				continue;

 			bw.write(record);
 			bw.flush();
 			bw.newLine();

 		}
 		
 		br.close();
 		bw.close();
 		
 		db.delete();
 		
 		tempDB.renameTo(db);

 }
	 
	 public void izmeniSestru() throws IOException {
 		String newIme, newPrezime, newJmbg, newAdresa, newBrojTelefona, newKorisnickoIme, newLozinka, newPol, newPlata,newsluzba, record, korisnickoIme,record2;
 		
 		File tempDB = new File("src/fajlovi/medicinskasestraTemp.txt");
 		File db = new File("src/fajlovi/medicinskasestra.txt");
 		
 		BufferedReader br = new BufferedReader( new FileReader(db) );
 		BufferedWriter bw = new BufferedWriter( new FileWriter(tempDB) );
 		    		
 		Scanner strInput = new Scanner(System.in);
 		
 		System.out.println("\t\t Update Employee Record\n\n");   
		/****/		
			System.out.println("Enter the Employee ID: ");
			korisnickoIme = strInput.nextLine();	    		
	    		System.out.println(" ------------------------------------------------------------------------------------------------------- ");
	    		System.out.println("|	ime		newPrezime 	newJmbg		Address		 newBrojTelefona"
	    				+ "     newKorisnickoIme 	newLozinka		newPol 	 newsluzba	newPlata|");
	    		System.out.println(" ------------------------------------------------------------------------------------------------------- ");
	    		
	    		while( ( record = br.readLine() ) != null ) {
	    			
	    			StringTokenizer st = new StringTokenizer(record,"|");
	    			if( record.contains(korisnickoIme) ) {
	    				System.out.println("|	"+st.nextToken()+"		"+st.nextToken()+"     	"+st.nextToken()+"		"+st.nextToken()+"  	  "+st.nextToken()+"     "+st.nextToken()+" 	"
	    						+ " 	     	"+st.nextToken()+" 		"+st.nextToken()+"       	"+st.nextToken()+" |");
	    			}
	    			
	    		}	    		
	    		System.out.println("|	                                            	          |");
	    		System.out.println(" ------------------------------------------------------------- ");
	    		
	    	br.close();
		/****/    	   
	    	System.out.print("Enter the Employee ime: ");
	    	newIme= strInput.nextLine();
	 		System.out.print("Enter the Employee prezime: ");
	 		newPrezime = strInput.nextLine();
	 		System.out.print("Enter the Employee jmbg: ");
	 		newJmbg = strInput.nextLine();
	 		System.out.print("Enter the Employee adresa: ");
	 		newAdresa = strInput.nextLine();    
	 		System.out.print("Enter the Employee brojTelefona: ");
	 		newBrojTelefona = strInput.nextLine();   
	 		System.out.print("Enter the Employee korisnickoIme : ");
	 		newKorisnickoIme = strInput.nextLine();   
	 		System.out.print("Enter the Employee lozinka:");
	 		newLozinka = strInput.nextLine(); 
	 		System.out.print("Enter the Employee pol:");
	 		newPol = strInput.nextLine(); 
	 		System.out.print("Enter the Employee sluzba:");
	 		newsluzba=strInput.nextLine();
	 		System.out.print("Enter the Employee plata:");
	 		newPlata = strInput.nextLine(); 
 		
 		BufferedReader br2 = new BufferedReader( new FileReader(db) );
 			
 		while( (record2 = br2.readLine() ) != null ) {    			
 			if(record2.contains(korisnickoIme)) {
 				bw.write(newIme+"|"+newPrezime+"|"+newJmbg+"|"+newAdresa+"|"+newBrojTelefona+"|"+newKorisnickoIme+"|"+newLozinka+"|"+newPol+"|"+newsluzba+"|"+newPlata);
 			} else {
 			
 				bw.write(record2);	
 			}    			
 			bw.flush();
 			bw.newLine();
 		}
 		
 		bw.close();
 		br2.close();    		
 		db.delete();    		
 		boolean success = tempDB.renameTo(db);    		
		System.out.println(success);    		
 		    		
 		
 }
	 public void DodajPregled() throws IOException {
	 		
	 		BufferedWriter bw = new BufferedWriter( new FileWriter("src/fajlovi/pregledi.txt",true) );
	 		Scanner strInput = new Scanner(System.in);
	 		Pacijent ime= pacijenti.get(0);
	 		Lekar imelekara= lekar.get(0);
			System.out.println (ime.getPrezime());
	 		String opis,idPregleda,status,termin;
	 		System.out.print("Unesite ID pregelda: ");
	 		idPregleda= strInput.nextLine();
	 		System.out.print("Unesite opis ");
	 		opis = strInput.nextLine();
	 		status = "Zakazan" ;
	 		System.out.print("Unesite termin: ");
	 		termin = strInput.nextLine();   
	 		bw.write(ime.getKorisnickoIme()+"|"+opis+"|"+status+"|"+idPregleda+"|"+termin+"|"+imelekara.getKorisnickoIme()+"|");
	 		bw.flush();
	 		bw.newLine();
	 		bw.close();		
	 	
	 }
	 
	 public void DodajKnjizicu() throws IOException {
	 		
	 		BufferedWriter bw = new BufferedWriter( new FileWriter("src/fajlovi/knjizica.txt",true) );
	 		Scanner strInput = new Scanner(System.in);
	 		Pacijent ime= pacijenti.get(0);
	 		String datumIsteka,kategorija, broj;
	 		System.out.print("Unesite broj knjizice: ");
	 		broj= strInput.nextLine();
	 		System.out.print("Unesite datum isteka ");
	 		datumIsteka = strInput.nextLine();
	 		System.out.print("Unesite kategoriju: ");
	 		kategorija = strInput.nextLine();   
	 		bw.write(broj+"|"+datumIsteka+"|"+kategorija+"|"+ime.getKorisnickoIme());
	 		bw.flush();
	 		bw.newLine();
	 		bw.close();		
	 	
	 }
	 public void BrisanjePregleda() throws IOException {
	 		Scanner strInput =  new Scanner(System.in);
	 		String idPregleda, record;
	 		
	 		
	 		File tempDB = new File("src/fajlovi/preglediTemp.txt");
	 		File db = new File("src/fajlovi/pregledi.txt");
	 		
	 		
	 		BufferedReader br = new BufferedReader( new FileReader( db ) );
	 		BufferedWriter bw = new BufferedWriter( new FileWriter( tempDB ) );
	 		
	 		
	 		System.out.println("\t\tBrisanje\n");
	 		
	 		System.out.println("Unesite ID: ");
	 		idPregleda =  strInput.nextLine();
	 		
	 		
	 		while( ( record = br.readLine() ) != null ) {
	 			
	 			
	 			if( record.contains(idPregleda) ) 
	 				continue;

	 			bw.write(record);
	 			bw.flush();
	 			bw.newLine();

	 		}
	 		
	 		br.close();
	 		bw.close();
	 		
	 		db.delete();
	 		
	 		tempDB.renameTo(db);

	 }
	 
}

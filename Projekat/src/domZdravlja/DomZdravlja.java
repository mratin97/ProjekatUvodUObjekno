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
import java.util.*;
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
	 public void DodajSestru() throws IOException {
 		
 		BufferedWriter bw = new BufferedWriter( new FileWriter("src/fajlovi/medicinskasestra.txt",true) );
 		Scanner strInput = new Scanner(System.in);
 		
 		String ime, prezime, jmbg, adresa, brojTelefona, korisnickoIme, lozinka, pol, plata;
 		
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
 		System.out.print("Enter the Employee plata:");
 		plata = strInput.nextLine(); 
 		   		
 		bw.write(ime+"|"+prezime+"|"+jmbg+"|"+adresa+"|"+brojTelefona+"|"+korisnickoIme+"|"+lozinka+"|"+pol+"|"+plata);
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
 		String newIme, newPrezime, newJmbg, newAdresa, newBrojTelefona, newKorisnickoIme, newLozinka, newPol, newPlata, record, korisnickoIme,record2;
 		
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
	    				+ "     newKorisnickoIme 	newLozinka		newPol 		newPlata|");
	    		System.out.println(" ------------------------------------------------------------------------------------------------------- ");
	    		
	    		while( ( record = br.readLine() ) != null ) {
	    			
	    			StringTokenizer st = new StringTokenizer(record,"|");
	    			if( record.contains(korisnickoIme) ) {
	    				System.out.println("|	"+st.nextToken()+"		"+st.nextToken()+"     	"+st.nextToken()+"		"+st.nextToken()+"  	  "+st.nextToken()+"     "+st.nextToken()+" 	"
	    						+ " 	     	"+st.nextToken()+" 		"+st.nextToken()+"        " +st.nextToken()+"	 |");
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
	 		System.out.print("Enter the Employee plata:");
	 		newPlata = strInput.nextLine(); 
 		
 		BufferedReader br2 = new BufferedReader( new FileReader(db) );
 			
 		while( (record2 = br2.readLine() ) != null ) {    			
 			if(record2.contains(korisnickoIme)) {
 				bw.write(newIme+"|"+newPrezime+"|"+newJmbg+"|"+newAdresa+"|"+newBrojTelefona+"|"+newKorisnickoIme+"|"+newLozinka+"|"+newPol+"|"+newPlata);
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
	 
	 
}

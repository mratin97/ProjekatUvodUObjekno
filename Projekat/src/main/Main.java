package main;
import java.io.IOException;

import domZdravlja.DomZdravlja;
import paket1.Lekar;
import paket1.MedSestra;

public class Main {

	public static void main(String[] args)  {
		DomZdravlja dom=new DomZdravlja();
		Lekar lekar=new Lekar();
		dom.ucitajSestre("medicinskasestra.txt");
		dom.ucitajLekare("lekar.txt");
		dom.ucitajPacijente("Pacijent.txt");
		dom.ucitajKnjizice("knjizica.txt");
		dom.ucitajPregled("pregledi.txt");
		//MedSestra testsestra = new MedSestra("Dusan", "Mratinkovic", "4144564456", "Adtresa", "06325458", "admin", "admin", "M","sluzba",123);
		//dom.dodajProdavca(testsestra);
		//dom.snimiZaposlene("medicinskasestra.txt");
		try 
        {
            dom.DodajPacijenta();
        }
        catch (IOException ioe)  {
        	}
		
		
		try 
        {
            dom.DodajKnjizicu();
        }
        catch (IOException ioe)  {
        }	
		try 
        {
            dom.DodajPregled();
        }
        catch (IOException ioe)  {
        }	
		try 
        {
            dom.DodajSestru();
        }
        catch (IOException ioe)  {
        }	
		try 
        {
            dom.DodajLekara();
        }
        catch (IOException ioe)  {
        }	
		try 
        {
            dom.DodajPacijenta();
        }
        catch (IOException ioe)  {
        	}
		
		
        }
	
        
	

	public static void ispisiSvePodatke(DomZdravlja dom) {

		
		for(MedSestra sestre : dom.getSestre()) {
			System.out.println(sestre + "\n");
		}
	}

}
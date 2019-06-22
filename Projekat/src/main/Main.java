package main;
import java.io.IOException;

import domZdravlja.DomZdravlja;
import gui.formeZaPrikaz.PrikazPacijenata;
import gui.formeZaPrikaz.PrikazPregleda;
import paket1.Lekar;
import paket1.*;
import paket1.MedSestra;
import gui1.GlavniProzor;
import gui1.LoginProzor;
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
		
		LoginProzor prozor=new LoginProzor(dom);
		prozor.setVisible(true);
	}
        
	

	public static void ispisiSvePodatke(DomZdravlja dom) {

		
		for(MedSestra sestre : dom.getSestre()) {
			System.out.println(sestre + "\n");
		}
	}

}
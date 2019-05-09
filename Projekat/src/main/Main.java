package main;

import java.util.ArrayList;
import domZdravlja.DomZdravlja;
import paket1.MedSestra;


public class Main {

	public static void main(String[] args) {
		DomZdravlja dom=new DomZdravlja();
		dom.ucitajZaposlene("medicinskasestra.txt");
		MedSestra testsestra = new MedSestra("Dusan", "Mratinkovic", "4144564456", "Adtresa", "06325458", "admin", "admin", "M",123);
		dom.dodajProdavca(testsestra);
		dom.snimiZaposlene("medicinskasestra.txt");
		ispisiSvePodatke(dom);
		
		
	}

	public static void ispisiSvePodatke(DomZdravlja dom) {

		
		for(MedSestra sestre : dom.getSestre()) {
			System.out.println(sestre + "\n");
		}
	}

}
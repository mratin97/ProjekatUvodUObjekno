
package gui1;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JToolBar;

import gui.formeZaDodavanje.SestreForme;
import gui.formeZaIzmenuIDodavanje.ZaposleniForma;
import gui.formeZaPrikaz.KompozicijeProzor;
import gui.formeZaPrikaz.PrikazDoktora;
import gui.formeZaPrikaz.PrikazKnjizica;
import gui.formeZaPrikaz.PrikazPacijenata;
import gui.formeZaPrikaz.PrikazPregleda;
import gui.formeZaPrikaz.SestrePrikaz;
import gui.formeZaPrikaz.PrikazRacuna;
import paket1.MedSestra;
import paket1.Pacijent;
import domZdravlja.*;
import paket1.kategorija;

public class SestreProzor extends JFrame {
	private JToolBar mainToolbar = new JToolBar();
	private JButton btnAdd = new JButton();
	private JButton btnEdit = new JButton();
	private JButton btnDelete = new JButton();

	private JMenuBar mainMenu;
	private JMenu	Meni;
	private JMenuItem PrikazSestara;
	private JMenuItem PrikazPregleda;
	private JMenuItem PrikazDoktora;
	private JMenuItem PrikazKnjizica;
	private JMenuItem PrikazPacijenata;
	private JMenuItem PrikazRacuna;
	private DomZdravlja dom;
	
	public SestreProzor(DomZdravlja dom,MedSestra sestra ) {
		this.dom=dom;
		setTitle("Prodavnica - ");
		setSize(500, 500);
		setLocationRelativeTo(null);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setResizable(false);
		initMenu();
		initActions();
	}
	
	private void initMenu() {
		this.mainMenu = new JMenuBar();
		this.Meni = new JMenu("Meni");
		this.PrikazSestara = new JMenuItem("Prikazi Sestara");
		this.PrikazPregleda = new JMenuItem("Prikaz Pregleda");
		this.PrikazDoktora =new JMenuItem("Prikaz Doktora");
		this.PrikazKnjizica =new JMenuItem("Prikaz Knjizica");
		this.PrikazPacijenata =new JMenuItem("Prikaz Pacijenata");
		this.PrikazRacuna =new JMenuItem("PrikazRacuna");
		
		this.Meni.add(PrikazSestara);
		this.Meni.add(PrikazPregleda);
		this.Meni.add(PrikazDoktora);
		this.Meni.add(PrikazKnjizica);
		this.Meni.add(PrikazPacijenata);
		this.Meni.add(PrikazRacuna);
		
		this.mainMenu.add(Meni);
		
		
		setJMenuBar(this.mainMenu);
	}
	
	private void initActions() {

		PrikazSestara.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				SestrePrikaz sf = new SestrePrikaz(dom);
				sf.setVisible(true);
			}
		});
		PrikazPregleda.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				PrikazPregleda pp = new PrikazPregleda(dom);
				pp.setVisible(true);
			}
		});
		PrikazDoktora.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				PrikazDoktora pp = new PrikazDoktora(dom);
				pp.setVisible(true);
			}
		});
		PrikazKnjizica.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				PrikazKnjizica pp = new PrikazKnjizica(dom);
				pp.setVisible(true);
			}
		});
		PrikazPacijenata.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				PrikazPacijenata pp = new PrikazPacijenata(dom);
				pp.setVisible(true);
			}
		});
		PrikazRacuna.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
			PrikazRacuna pp = new PrikazRacuna(dom);
				pp.setVisible(true);
			}
		});
	}
}
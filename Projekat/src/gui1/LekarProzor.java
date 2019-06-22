
package gui1;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;

import gui.formeZaIzmenuIDodavanje.ZaposleniForma;
import gui.formeZaPrikaz.KompozicijeProzor;
import gui.formeZaPrikaz.PrikazPregleda;
import paket1.Lekar;
import paket1.Pacijent;
import domZdravlja.*;


public class LekarProzor extends JFrame {

	private JMenuBar mainMenu;
	private JMenu Meni;
	private JMenuItem PrikazPregleda;
	public String korime;
	
	

	private DomZdravlja dom;
	
	public  LekarProzor(DomZdravlja dom,Lekar prijavljenikorisnik ) {
		this.dom=dom;
		setTitle("Pacijenti - ");
		setSize(500, 500);
		setLocationRelativeTo(null);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setResizable(false);
		initMenu();
		initActions(prijavljenikorisnik);
		
	}
	
	private void initMenu() {
		this.mainMenu = new JMenuBar();
		this.Meni = new JMenu("Meni");
		this.PrikazPregleda = new JMenuItem("Prikazi pregleda");
		
		
		this.Meni.add(PrikazPregleda);
		
		
		this.mainMenu.add(Meni);
		
		
		setJMenuBar(this.mainMenu);
	}
	
	private void initActions(Lekar prijavljenikorisnik) {
		this.dom=dom;
		PrikazPregleda.addActionListener(new ActionListener( ) {
			@Override
			public void actionPerformed(ActionEvent e) {
				PrikazPregleda pp = new PrikazPregleda(dom,prijavljenikorisnik);
				pp.setVisible(true);
			}
		});
	
	}
}

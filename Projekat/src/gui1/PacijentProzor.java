
package gui1;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import gui.formeZaPrikaz.PrikazPregleda;
import domZdravlja.*;

/*
 * 	Glavni prozor aplikacije. 
 * 	Prikazuje se nakon uspesne prijave i sadrzi meni za pristup formama za rukovanje entitetima.
 */
public class PacijentProzor extends JFrame {

	private JMenuBar mainMenu;
	private JMenu artikliMenu;
	private JMenuItem artikliItem;
	private JMenuItem kompozicijeItem;
	private JMenu osobljeMenu;
	private JMenuItem prodavciItem;
	
	DomZdravlja dom=new DomZdravlja();
	
	public PacijentProzor() {
		
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
		this.artikliMenu = new JMenu("Pacijent");
		this.artikliItem = new JMenuItem("Prikazi pregleda");
		this.kompozicijeItem = new JMenuItem("Zakazivanje");
		this.osobljeMenu = new JMenu("Osoblje");
		this.prodavciItem = new JMenuItem("123a");
		
		this.artikliMenu.add(artikliItem);
		this.artikliMenu.add(kompozicijeItem);
		this.osobljeMenu.add(prodavciItem);
		
		this.mainMenu.add(artikliMenu);
		this.mainMenu.add(osobljeMenu);
		
		setJMenuBar(this.mainMenu);
	}
	
	private void initActions() {
		// Klikom na stavke menija otvaraju se odgovarajuce forme za prikaz
		artikliItem.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				PrikazPregleda pk= new PrikazPregleda(dom);
				pk.setVisible(true);
			}
		});
		
	}
}

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


/*
 * 	Glavni prozor aplikacije. 
 * 	Prikazuje se nakon uspesne prijave i sadrzi meni za pristup formama za rukovanje entitetima.
 */
public class GlavniProzor extends JFrame {

	private JMenuBar mainMenu;
	private JMenu artikliMenu;
	private JMenuItem artikliItem;
	private JMenuItem kompozicijeItem;
	private JMenu osobljeMenu;
	private JMenuItem prodavciItem;
	private JToolBar mainToolbar = new JToolBar();
	private JButton btnAdd = new JButton();
	private JButton btnEdit = new JButton();
	private JButton btnDelete = new JButton();

	
	public GlavniProzor() {
		
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
		this.artikliMenu = new JMenu("Sestre");
		this.artikliItem = new JMenuItem("Prikazi sestre");
		this.kompozicijeItem = new JMenuItem("Prikaži kompozicije");
		this.osobljeMenu = new JMenu("Osoblje");
		this.prodavciItem = new JMenuItem("Prikaži prodavce");
		
		this.artikliMenu.add(artikliItem);
		this.artikliMenu.add(kompozicijeItem);
		this.osobljeMenu.add(prodavciItem);
		
		this.mainMenu.add(artikliMenu);
		this.mainMenu.add(osobljeMenu);
		
		setJMenuBar(this.mainMenu);
	}
	
	private void initActions() {
		// Klikom na stavke menija otvaraju se odgovarajuce forme za prikaz
		kompozicijeItem.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				
				setVisible(true);
			}
		});
	
		
	}
}
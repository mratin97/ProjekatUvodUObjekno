package gui1;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;
import javax.swing.JRootPane;
import javax.swing.JTextField;

import domZdravlja.DomZdravlja;
import net.miginfocom.swing.MigLayout;
import paket1.*;
import domZdravlja.*;;

/*
 * 	Forma za prijavu.
 * 	Prvi prozor koji se prikazuje kada se aplikacija pokrene.
 */
public class LoginProzor extends JFrame {

	private JLabel lblPoruka;
	private JLabel lblKorisnickoIme;
	private JTextField txtKorisnickoIme;
	private JLabel lblSifra;
	private JPasswordField pfSifra;
	private JButton btnOK;
	private JButton btnCancel;
	
	private DomZdravlja dom;
	
	public LoginProzor(DomZdravlja dom) {
		this.dom = dom;
		setTitle("Prijava");
		setLocationRelativeTo(null);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setResizable(false);
		initGUI();
		initActions();
		pack();
	}
	
	private void initGUI() {
		/*
		 * 	Malo detaljnije podesavanje MigLayout-a:
		 * 	Drugi parametar (string) sadrzi 2 prazne uglaste zagrade jer imamo 2 kolone (ovde nista nismo podesili)
		 *  Treci parametar ima onoliko uglastih zagrada koliko imamo redova (u nasem slucaju 4)
		 *  Unutar zagrada mozemo detaljnije podesavati kolone i redove, dok vrednosti izmedju njih predstavljaju
		 *  razmake u pikselima.
		 *  Ovde smo postavili razmak od 20px izmedju 1. i 2. i izmedju 3. i 4. reda.
		 */
		MigLayout layout = new MigLayout("wrap 2", "[][]", "[]20[][]20[]");
		setLayout(layout);
		
		this.lblPoruka = new JLabel("Dobrodošli. Molimo da se prijavite.");
		this.lblKorisnickoIme = new JLabel("Korisničko ime");
		this.txtKorisnickoIme = new JTextField(20);
		this.lblSifra = new JLabel("Šifra");
		this.pfSifra = new JPasswordField(20);
		this.btnOK = new JButton("OK");
		this.btnCancel = new JButton("Cancel");
		
		// Ako postavimo dugme 'btnOK' kao defaul button, onda ce svaki pritisak tastera Enter na tastaturi
		// Izazvati klik na njega
		this.getRootPane().setDefaultButton(btnOK);
		
		add(lblPoruka, "span 2");
		add(lblKorisnickoIme);
		add(txtKorisnickoIme);
		add(lblSifra);
		add(pfSifra);
		add(new JLabel());
		add(btnOK, "split 2");
		add(btnCancel);
	}
	
	private void initActions() {
		// Klik na Login dugme
		btnOK.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				String korisnickoIme = txtKorisnickoIme.getText().trim();
				String sifra = new String(pfSifra.getPassword()).trim();
				// Ukoliko nesto nije uneseno, obavestimo korisnika
				if(korisnickoIme.equals("") || sifra.equals("")) {
					JOptionPane.showMessageDialog(null, "Niste uneli sve podatke.");
				}else  {
					Lekar lekar= dom.loginLekar(korisnickoIme, sifra);
					Pacijent pacijent=dom.loginPacijent(korisnickoIme, sifra);
					MedSestra sestra = dom.login(korisnickoIme, sifra);
					if(sestra != null) {
						// Uspesno logovanje:
						// 1. Sakrijemo login prozor
						LoginProzor.this.setVisible(false);
						LoginProzor.this.dispose();
						// 2. Prikazemo glavni prozor i prosledimo mu prodavnicu i prijavljenog korisnika
						GlavniProzor glavni = new GlavniProzor();
						glavni.setVisible(true);
					}else if(lekar!= null){
						// Uspesno logovanje:
						// 1. Sakrijemo login prozor
						LoginProzor.this.setVisible(false);
						LoginProzor.this.dispose();
						// 2. Prikazemo glavni prozor i prosledimo mu prodavnicu i prijavljenog korisnika
						GlavniProzor glavni = new GlavniProzor();
						glavni.setVisible(true);
					}
					else if(pacijent!= null){
						// Uspesno logovanje:
						// 1. Sakrijemo login prozor
						LoginProzor.this.setVisible(false);
						LoginProzor.this.dispose();
						// 2. Prikazemo glavni prozor i prosledimo mu prodavnicu i prijavljenog korisnika
						PacijentProzor glavni = new PacijentProzor();
						glavni.setVisible(true);
					}
					else {
						
						JOptionPane.showMessageDialog(null, "Pogrešni login podaci!");
					}
				}
			}
		});
		// Cancel dugme samo sakriva trenutni prozor
		btnCancel.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				LoginProzor.this.setVisible(false);
				LoginProzor.this.dispose();
			}
		});
		
	}
}
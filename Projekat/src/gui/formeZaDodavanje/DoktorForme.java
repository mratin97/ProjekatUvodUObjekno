package gui.formeZaDodavanje;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import domZdravlja.DomZdravlja;
import javafx.css.ParsedValue;
import net.miginfocom.swing.MigLayout;
import paket1.Pol;
import paket1.sluzba;
import paket1.MedSestra;
import paket1.*;

public class DoktorForme extends JFrame {

	private JLabel lblIme = new JLabel("Ime");
	private JTextField txtIme = new JTextField(20);
	private JLabel lblPrezime = new JLabel("Prezime");
	private JTextField txtPrezime = new JTextField(20);
	private JLabel lblJmbg = new JLabel("Jmbg");
	private JTextField txtJmbg = new JTextField(20);
	private JLabel lblAdresa = new JLabel("Adresa");
	private JTextField txtAdresa = new JTextField(20);
	private JLabel lblBrojTelefona = new JLabel("Broj Telefona");
	private JTextField txtBrojTelefona = new JTextField(20);
	private JLabel lblKorisnickoIme = new JLabel("Korisnicko ime");
	private JTextField txtKorisnickoIme = new JTextField(20);
	private JLabel lblSifra = new JLabel("Sifra");
	private JPasswordField pfSifra = new JPasswordField(20);
	private JLabel lblPol = new JLabel("Pol");
	private JLabel lblSluzba= new JLabel("Sluzba");
	private JComboBox<sluzba> cbsluzba = new JComboBox<sluzba>(sluzba.values());
	private JLabel lblPlata = new JLabel("Plata");
	private JTextField txtPlata = new JTextField(20);
	private JLabel lblSpec = new JLabel("Specijalizacija");
	private JTextField txtSpec = new JTextField(20);
	private JComboBox<Pol> cbPol = new JComboBox<Pol>(Pol.values());
	private JButton btnOk = new JButton("OK");
	private JButton btnCancel = new JButton("Cancel");
	
	private DomZdravlja dom;
	private Lekar doktor;
	
	public DoktorForme(DomZdravlja dom, Lekar doktor) {
		this.dom = dom;
		this.doktor = doktor;
		if(this.doktor == null) {
			setTitle("Dodavanje Doktora");
		}else {
			setTitle("Izmena podataka - " + this.doktor.getKorisnickoIme());
		}
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setLocationRelativeTo(null);
		initGUI();
		initActions();
		setResizable(false);
		pack();
	}
	
	private void initGUI() {
		MigLayout layout = new MigLayout("wrap 2");
		setLayout(layout);
		
		if(this.doktor != null) {
			popuniPolja();
		}
		
		add(lblIme);
		add(txtIme);
		add(lblPrezime);
		add(txtPrezime);
		add(lblJmbg);
		add(txtJmbg);
		add(lblAdresa);
		add(txtAdresa);
		add(lblBrojTelefona);
		add(txtBrojTelefona);
		add(lblKorisnickoIme);
		add(txtKorisnickoIme);
		add(lblSifra);
		add(pfSifra);
		add(lblPol);
		add(cbPol);
		add(lblSluzba);
		add(cbsluzba);
		add(lblPlata);
		add(txtPlata);
		add(lblSpec);
		add(txtSpec);
		add(new JLabel());
		add(btnOk, "split 2");
		add(btnCancel);
	}
	
	private void initActions() {
		btnOk.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if(validacija() == true) {
					String ime = txtIme.getText().trim();
					String prezime = txtPrezime.getText().trim();
					String korisnickoIme = txtKorisnickoIme.getText().trim();
					String sifra = new String(pfSifra.getPassword()).trim();
					String jmbg =txtJmbg.getText().trim();
					String adresa =txtAdresa.getText().trim();
					String brojTelefona =txtBrojTelefona.getText().trim();
					sluzba imeSluzbe =(sluzba)cbsluzba.getSelectedItem();
					int plata=Integer.parseInt(txtPlata.getText().trim());
					String spec =txtSpec.getText().trim();
					Pol Pol = (Pol) cbPol.getSelectedItem();
					if(doktor == null) {
						doktor = new Lekar(ime, prezime, jmbg, adresa, brojTelefona,korisnickoIme,sifra,Pol,imeSluzbe,plata,spec);
						dom.getLekar().add(doktor);
					}else {
						doktor.setIme(ime);
						doktor.setPrezime(prezime);
						doktor.setKorisnickoIme(korisnickoIme);
						doktor.setLozinka(sifra);
						doktor.setPol(Pol);
						doktor.setSluzba(imeSluzbe);
						doktor.setBrojTelefona(brojTelefona);
						doktor.setJmbg(jmbg);
						doktor.setPlata(plata);
						doktor.setAdresa(adresa);
						
					}
					dom.snimiLekare();
					DoktorForme.this.dispose();
					DoktorForme.this.setVisible(false);
				}
			}
		});
	}
	
	private void popuniPolja() {
		txtIme.setText(this.doktor.getIme());
		txtPrezime.setText(this.doktor.getPrezime());
		txtKorisnickoIme.setText(this.doktor.getKorisnickoIme());
		pfSifra.setText(this.doktor.getLozinka());
		cbPol.setSelectedItem(this.doktor.getPol());
		txtJmbg.setText(this.doktor.getJmbg());
		txtAdresa.setText(this.doktor.getAdresa());
		cbsluzba.setSelectedItem(this.doktor.getSluzba());
		txtSpec.setText(this.doktor.getSpecijalizacija());
	}
	
	private boolean validacija() {
		boolean ok = true;
		String poruka = "Molimo popravite sledece greske u unosu:\n";
		if(txtIme.getText().trim().equals("")) {
			poruka += "- Unesite ime\n";
			ok = false;
		}
		if(txtPrezime.getText().trim().equals("")) {
			poruka += "- Unesite prezime\n";
			ok = false;
		}
		if(txtKorisnickoIme.getText().trim().equals("")) {
			poruka += "- Unesite korisnicko ime\n";
			ok = false;
		}
		String sifra = new String(pfSifra.getPassword()).trim();
		if(sifra.trim().equals("")) {
			poruka += "- Unesite lozinku\n";
			ok = false;
		}
		if(txtJmbg.getText().trim().equals("")) {
			poruka += "- Unesite Jmbg\n";
			ok = false;
		}
		
		if(txtAdresa.getText().trim().equals("")) {
			poruka += "- Unesite Adresu\n";
			ok = false;
		}
	
		
		if(ok == false) {
			JOptionPane.showMessageDialog(null, poruka, "Neispravni podaci", JOptionPane.WARNING_MESSAGE);
		}
		return ok;
	}
}

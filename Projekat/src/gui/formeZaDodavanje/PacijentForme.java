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
import paket1.Pacijent;
import paket1.*;



public class PacijentForme extends JFrame {

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
	private JLabel lblLekar = new JLabel("Lekar");
	private JTextField txtLekar = new JTextField(20);
	private JLabel lblKnjizica = new JLabel("Knjizica ");
	private JTextField TxtKnjizica = new JTextField(20);

	private JComboBox<Pol> cbPol = new JComboBox<Pol>(Pol.values());
	private JButton btnOk = new JButton("OK");
	private JButton btnCancel = new JButton("Cancel");
	private Pacijent pacijent;
	private DomZdravlja dom;
	private MedSestra sestra;
	
	public PacijentForme(DomZdravlja dom,MedSestra sestra) {
		this.dom = dom;
		this.pacijent= pacijent;
		this.sestra = sestra;
		if(this.sestra == null) {
			setTitle("Dodavanje Pacijenta");
		}else {
			setTitle("Izmena podataka - " + this.sestra.getKorisnickoIme());
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
		
		if(this.sestra != null) {
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
		add(lblLekar);
		add(txtLekar);
		add(lblKnjizica);
		add(TxtKnjizica);
		
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
					String lekar =txtLekar.getText().trim();
					Lekar x=dom.nadjiLekara(lekar);
					String knjizica=TxtKnjizica.getText().trim();
					Knjizica z=dom.nadjiKnjizicu(knjizica);
					Pol Pol = (Pol) cbPol.getSelectedItem();
					
					if(pacijent == null) {
					 pacijent = new Pacijent(ime, prezime, jmbg, adresa, brojTelefona,korisnickoIme,sifra,Pol,x,z);
						dom.getSestre().add(sestra);
					}else {
						pacijent.setIme(ime);
						pacijent.setPrezime(prezime);
						pacijent.setKorisnickoIme(korisnickoIme);
						pacijent.setLozinka(sifra);
						pacijent.setPol(Pol);
						pacijent.setIzabraniLekar(x);
						pacijent.setBrojTelefona(brojTelefona);
						pacijent.setJmbg(jmbg);
						pacijent.setPodaciOKnjizici(z);
						pacijent.setAdresa(adresa);
						
					}
					dom.snimiPacijenta();
					PacijentForme.this.dispose();
					PacijentForme.this.setVisible(false);
				}
			}
		});
	}
	
	private void popuniPolja() {
		txtIme.setText(this.pacijent.getIme());
		txtPrezime.setText(this.pacijent.getPrezime());
		txtKorisnickoIme.setText(this.pacijent.getKorisnickoIme());
		pfSifra.setText(this.pacijent.getLozinka());
		cbPol.setSelectedItem(this.pacijent.getPol());
		txtJmbg.setText(this.pacijent.getJmbg());
		txtAdresa.setText(this.pacijent.getAdresa());
		//txtLekar.setSelectedItem(this.pacijent.getIzabraniLekar());
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

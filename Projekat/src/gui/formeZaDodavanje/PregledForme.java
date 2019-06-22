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
import pregled.Pregled;
import paket1.Lekar;
import paket1.MedSestra;
import paket1.Pacijent;
import pregled.*;

public class PregledForme extends JFrame {
	
	private JLabel lblPacijent = new JLabel("Pacijent");
	private JTextField txtPacijent = new JTextField(20);
	private JLabel lblOpise = new JLabel("Opis");
	private JTextField txtOpis = new JTextField(20);
	private JLabel lblidPregleda = new JLabel("idPregleda");
	private JTextField txtidPregleda = new JTextField(20);
	private JLabel lblTermin = new JLabel("Termin");
	private JTextField txtTermin = new JTextField(20);
	private JLabel lblDoktor = new JLabel("Doktor");
	private JTextField txtDoktor = new JTextField(20);
	private JLabel lblStatus= new JLabel("Status");
	private JComboBox<status> cbStatus = new JComboBox<status>(status.values());
	private JButton btnOk = new JButton("OK");
	private JButton btnCancel = new JButton("Cancel");
	
	private DomZdravlja dom;
	private MedSestra sestra;
	private Pregled pregled;
	private Pacijent pacijent;
	private Lekar lekar;
	public PregledForme(DomZdravlja dom, Pregled pregled) {
		this.dom = dom;
		this.sestra = sestra;
		this.pregled= pregled;
		if(this.pregled == null) {
			setTitle("Novi Pregled");
		}else {
			setTitle("Izmena podataka - " + this.pregled.getIdPregleda());
		}
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setLocationRelativeTo(null);
		initGUI();
		initActions();
		setResizable(false);
		pack();
	}
	public PregledForme(DomZdravlja dom, Pregled pregled,Pacijent paciejnt) {
		this.dom = dom;
		this.sestra = sestra;
		this.pacijent=paciejnt;
		this.pregled= pregled;
		if(this.pregled == null) {
			setTitle("Novi Pregled");
		}else {
			setTitle("Izmena podataka - " + this.pregled.getIdPregleda());
		}
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setLocationRelativeTo(null);
		initGUI(pacijent);
		initActions(pacijent);
		setResizable(false);
		pack();
	}
	public PregledForme(DomZdravlja dom, Pregled pregled,Lekar lekar) {
		this.dom = dom;
		
		this.lekar=lekar;
		this.pregled= pregled;
		if(this.pregled == null) {
			setTitle("Novi Pregled");
		}else {
			setTitle("Izmena podataka - " + this.pregled.getIdPregleda());
		}
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setLocationRelativeTo(null);
		initGUI(lekar);
		initActions(lekar,pregled);
		setResizable(false);
		pack();
	}
	private void initGUI() {
		MigLayout layout = new MigLayout("wrap 2");
		setLayout(layout);
		
		if(this.pregled != null) {
			popuniPolja();
		}
		
		add(lblPacijent);
		add(txtPacijent);
		add(lblOpise);
		add(txtOpis);
		add(lblidPregleda);
		add(txtidPregleda);
		add(lblTermin);
		add(txtTermin);
		add(lblDoktor);
		add(txtDoktor);
		add(lblStatus);
		add(cbStatus);
	
		
		add(new JLabel());
		add(btnOk, "split 2");
		add(btnCancel);
	}
	private void initGUI(Pacijent pacijent) {
		MigLayout layout = new MigLayout("wrap 2");
		setLayout(layout);
		
		if(this.pregled != null) {
			popuniPolja();
		}
		
		add(lblOpise);
		add(txtOpis);
		add(lblTermin);
		add(txtTermin);
		
		
	
		
		add(new JLabel());
		add(btnOk, "split 2");
		add(btnCancel);
	}
	private void initGUI(Lekar lekar) {
		MigLayout layout = new MigLayout("wrap 2");
		setLayout(layout);
		
		
			popuniPolja(lekar);
		
		
	
		add(lblStatus);
		add(cbStatus);
		
	
		
		add(new JLabel());
		add(btnOk, "split 2");
		add(btnCancel);
	}
	private void initActions(Lekar lekar,Pregled pregled) {
		btnOk.addActionListener(new ActionListener() {
			private Pregled pregled;

			@Override
			public void actionPerformed(ActionEvent e) {
				this.pregled=pregled;
				
				String Doktorstr=lekar.getKorisnickoIme();
				String Opis = pregled.getOpis();
				int IdPregleda1 =dom.getRandomNumberInRange(1, 1000);
				String IdPregleda=Integer.toString(IdPregleda1);
				String Termin =pregled.getTermin();
				Pacijent pacijent=pregled.getPacijent();
			
				Lekar Doktor=dom.nadjiLekara(Doktorstr);
					status status =(status)cbStatus.getSelectedItem();
					
					
					
					
					
	
					if(pregled == null) {
						pregled = new Pregled(pacijent,Opis,status,IdPregleda,Termin, Doktor);
						dom.getPregled().add(pregled);
					}else {
						pregled.setPacijent(pacijent);
						pregled.setOpis(Opis);
						pregled.setIdPregleda(IdPregleda);
						pregled.setTermin(Termin);
						pregled.setDoktor(Doktor);
				
						
					}
					dom.snimiPreglede();
					PregledForme.this.dispose();
					PregledForme.this.setVisible(false);
				
			}
		});
	}
	private void initActions(Pacijent pacijent) {
		btnOk.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if(validacija(pacijent) == true) {
					final status status1 =status.Zatrazen;
					String Doktorstr=pacijent.getIzabraniLekar();
					String Opis = txtOpis.getText().trim();
					int IdPregleda1 =dom.getRandomNumberInRange(1, 1000);
					String IdPregleda=Integer.toString(IdPregleda1);
					String Termin =txtTermin.getText().trim();
					
					//status status =(status)cbStatus.getSelectedItem();
					Lekar Doktor=dom.nadjiLekara(Doktorstr);
					
					
					
					
	
					if(pregled == null) {
						pregled = new Pregled(pacijent,Opis,status1,IdPregleda,Termin, Doktor);
						dom.getPregled().add(pregled);
					}else {
						pregled.setPacijent(pacijent);
						pregled.setOpis(Opis);
						pregled.setIdPregleda(IdPregleda);
						pregled.setTermin(Termin);
						pregled.setDoktor(Doktor);
				
						
					}
					dom.snimiPreglede();
					PregledForme.this.dispose();
					PregledForme.this.setVisible(false);
				}
			}
		});
	}
	private void initActions() {
		btnOk.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if(validacija() == true) {
					String pacijenttxt = txtPacijent.getText().trim();
					String Opis = txtOpis.getText().trim();
					String IdPregleda = txtidPregleda.getText().trim();
					String Termin =txtTermin.getText().trim();
					String Doktortxt =txtDoktor.getText().trim();
					status status =(status)cbStatus.getSelectedItem();
					Lekar Doktor=dom.nadjiLekara(Doktortxt);
					Pacijent pacijent=dom.nadjiPacijenta(pacijenttxt);
					
					
					
	
					if(pregled == null) {
						pregled = new Pregled(pacijent,Opis,status,IdPregleda,Termin, Doktor);
						dom.getPregled().add(pregled);
					}else {
						pregled.setPacijent(pacijent);
						pregled.setOpis(Opis);
						pregled.setIdPregleda(IdPregleda);
						pregled.setTermin(Termin);
						pregled.setDoktor(Doktor);
				
						
					}
					dom.snimiPreglede();
					PregledForme.this.dispose();
					PregledForme.this.setVisible(false);
				}
			}
		});
	}
	
	private void popuniPolja() {
		
		txtPacijent.setText(this.pregled.getPacijent().getKorisnickoIme());
		txtOpis.setText(this.pregled.getOpis());
		txtidPregleda.setText(this.pregled.getIdPregleda());
		cbStatus.setSelectedItem(this.pregled.getStatus());
		txtTermin.setText(this.pregled.getTermin());
		txtDoktor.setText(this.pregled.getDoktor().getIme());
		
	}
private void popuniPolja(Lekar lekar) {
		
		txtPacijent.setText(this.pregled.getPacijent().getKorisnickoIme());
		txtOpis.setText(this.pregled.getOpis());
		txtidPregleda.setText(this.pregled.getIdPregleda());
		cbStatus.setSelectedItem(this.pregled.getStatus());
		txtTermin.setText(this.pregled.getTermin());
		txtDoktor.setText(this.pregled.getDoktor().getIme());
		
	}
	
	private boolean validacija() {
		boolean ok = true;
		String poruka = "Molimo popravite sledece greske u unosu:\n";
		if(txtPacijent.getText().trim().equals("")) {
			poruka += "- Unesite ime\n";
			ok = false;
		}
		if(txtOpis.getText().trim().equals("")) {
			poruka += "- Unesite Opis\n";
			ok = false;
		}
		if(txtidPregleda.getText().trim().equals("")) {
			poruka += "- Unesite Id Pregleda\n";
			ok = false;
		}
		
		if(txtDoktor.getText().trim().equals("")) {
			poruka += "- Unesite Doktora\n";
			ok = false;
		}
		
		if(txtTermin.getText().trim().equals("")) {
			poruka += "- Unesite Termin\n";
			ok = false;
		}
	
		
		if(ok == false) {
			JOptionPane.showMessageDialog(null, poruka, "Neispravni podaci", JOptionPane.WARNING_MESSAGE);
		}
		return ok;
	}

	private boolean validacija(Pacijent paciejnt) {
		boolean ok = true;
		String poruka = "Molimo popravite sledece greske u unosu:\n";
	
		if(txtOpis.getText().trim().equals("")) {
			poruka += "- Unesite Opis\n";
			ok = false;
		}

	
		if(txtTermin.getText().trim().equals("")) {
			poruka += "- Unesite Termin\n";
			ok = false;
		}
	
		
		if(ok == false) {
			JOptionPane.showMessageDialog(null, poruka, "Neispravni podaci", JOptionPane.WARNING_MESSAGE);
		}
		return ok;
	}
	
}
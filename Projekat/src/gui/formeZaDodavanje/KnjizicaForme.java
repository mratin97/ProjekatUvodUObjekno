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
import paket1.Knjizica;
import paket1.Lekar;
import paket1.MedSestra;
import paket1.Pacijent;
import pregled.*;
import paket1.kategorija;

public class KnjizicaForme extends JFrame {
	
	private JLabel lblBroj = new JLabel("Broj knjizice");
	private JTextField txtBroj= new JTextField(20);
	private JLabel lblDatumIsteka = new JLabel("Datum Isteka");
	private JTextField txtDatumIsteka = new JTextField(20);
	private JLabel lblPacijent = new JLabel("Pacijent");
	private JTextField txtPacijent = new JTextField(20);
	private JLabel lblKategorija= new JLabel("Kategorija");
	private JComboBox<kategorija> cbKategorija = new JComboBox<kategorija>(kategorija.values());
	private JButton btnOk = new JButton("OK");
	private JButton btnCancel = new JButton("Cancel");
	
	private DomZdravlja dom;
	
	private Knjizica knjizica;
	public KnjizicaForme(DomZdravlja dom, Knjizica knjizica) {
		this.dom = dom;
		
		this.knjizica= knjizica;
		if(this.knjizica == null) {
			setTitle("Novi Pregled");
		}else {
			setTitle("Izmena podataka - " + this.knjizica.getBroj());
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
		
		if(this.knjizica != null) {
			popuniPolja();
		}
		
		add(lblBroj);
		add(txtBroj);
		add(lblDatumIsteka);
		add(txtDatumIsteka);
		add(lblPacijent);
		add(txtPacijent);
		add(lblKategorija);
		add(cbKategorija);
		
	
		
		add(new JLabel());
		add(btnOk, "split 2");
		add(btnCancel);
	}
	
	private void initActions() {
		btnOk.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if(validacija() == true) {
					String broj = txtBroj.getText().trim();
					String datum = txtDatumIsteka.getText().trim();
					String pacijentime =txtPacijent.getText().trim();
					Pacijent pacijent=dom.nadjiPacijenta(pacijentime);
					kategorija kategorija =(kategorija)cbKategorija.getSelectedItem();
				
					
					
					
	
					if(knjizica == null) {
						knjizica = new Knjizica(broj,datum,kategorija,pacijent);
						dom.getKnjizica().add(knjizica);
					}else {
						knjizica.setBroj(broj);
						knjizica.setDatumIsteka(datum);
						knjizica.setPacijent(pacijent);
						knjizica.setKategorija(kategorija);
						
				
						
					}
					dom.snimiKnjizice();
					KnjizicaForme.this.dispose();
					KnjizicaForme.this.setVisible(false);
				}
			}
		});
	}
	
	private void popuniPolja() {
		
		
		txtBroj.setText(this.knjizica.getBroj());
		txtDatumIsteka.setText(this.knjizica.getDatumIsteka());
		cbKategorija.setSelectedItem(this.knjizica.getKategorija());
		
	}
	
	private boolean validacija() {
		boolean ok = true;
		String poruka = "Molimo popravite sledece greske u unosu:\n";
		if(txtPacijent.getText().trim().equals("")) {
			poruka += "- Unesite ime\n";
			ok = false;
		}
		if(txtBroj.getText().trim().equals("")) {
			poruka += "- Unesite Broj\n";
			ok = false;
		}
		if(txtDatumIsteka.getText().trim().equals("")) {
			poruka += "- Unesite Datum Isketa\n";
			ok = false;
		}
		
		if(txtPacijent.getText().trim().equals("")) {
			poruka += "- Unesite Pacijenta\n";
			ok = false;
		}
	
		
		if(ok == false) {
			JOptionPane.showMessageDialog(null, poruka, "Neispravni podaci", JOptionPane.WARNING_MESSAGE);
		}
		return ok;
	}
}

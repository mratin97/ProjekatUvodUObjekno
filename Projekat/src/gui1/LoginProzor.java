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
		MigLayout layout = new MigLayout("wrap 2", "[][]", "[]20[][]20[]");
		setLayout(layout);
		
		this.lblPoruka = new JLabel("DobrodoÅ¡li. Molimo da se prijavite.");
		this.lblKorisnickoIme = new JLabel("KorisniÄ�ko ime");
		this.txtKorisnickoIme = new JTextField(20);
		this.lblSifra = new JLabel("Å ifra");
		this.pfSifra = new JPasswordField(20);
		this.btnOK = new JButton("OK");
		this.btnCancel = new JButton("Cancel");
		
		
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
		
		btnOK.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				String korisnickoIme = txtKorisnickoIme.getText().trim();
				String sifra = new String(pfSifra.getPassword()).trim();
				
				if(korisnickoIme.equals("") || sifra.equals("")) {
					JOptionPane.showMessageDialog(null, "Niste uneli sve podatke.");
				}else  {
					Lekar lekar= dom.loginLekar(korisnickoIme, sifra);
					Pacijent pacijent=dom.loginPacijent(korisnickoIme, sifra);
					MedSestra sestra = dom.login(korisnickoIme, sifra);
					if(sestra != null) {
						LoginProzor.this.setVisible(false);
						LoginProzor.this.dispose();
						SestreProzor glavni = new SestreProzor(dom, sestra);
						
						glavni.setVisible(true);
					}else if(lekar!= null){
						LoginProzor.this.setVisible(false);
						LoginProzor.this.dispose();
						LekarProzor glavni = new LekarProzor(dom,lekar);
						glavni.setVisible(true);
					}
					else if(pacijent!= null){
						LoginProzor.this.setVisible(false);
						LoginProzor.this.dispose();
						PacijentProzor glavni = new PacijentProzor(dom,pacijent);
						glavni.setVisible(true);
					}
					else {
						
						JOptionPane.showMessageDialog(null, "PogreÅ¡ni login podaci!");
					}
				}
			}
			
		});
	
		btnCancel.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				LoginProzor.this.setVisible(false);
				LoginProzor.this.dispose();
			}
		});
		
	}
}
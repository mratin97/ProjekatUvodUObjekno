package gui.formeZaPrikaz;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JToolBar;
import javax.swing.ListSelectionModel;
import javax.swing.table.DefaultTableModel;

import pregled.Pregled;
import domZdravlja.*;
import gui.formeZaDodavanje.PacijentForme;
import gui.formeZaDodavanje.SestreForme;
import gui.formeZaIzmenuIDodavanje.ZaposleniForma;
import paket1.MedSestra;
import paket1.Pacijent;


public class PrikazPacijenata extends JFrame {

	private JToolBar mainToolbar = new JToolBar();
	private JButton btnAdd = new JButton();
	private JButton btnEdit = new JButton();
	private JButton btnDelete = new JButton();
	
	private DefaultTableModel tableModel;
	private JTable PacijentiTabela;
	
	private DomZdravlja dom;
	
	public PrikazPacijenata(DomZdravlja dom) {
		this.dom = dom;
		setTitle("Pacijenti");
		setSize(500, 300);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setLocationRelativeTo(null);
		initGUI();
		
	}
	
	private void initGUI() {
		ImageIcon addIcon = new ImageIcon(getClass().getResource("/slike/add.gif"));
		btnAdd.setIcon(addIcon);
		mainToolbar.add(btnAdd);
		ImageIcon editIcon = new ImageIcon(getClass().getResource("/slike/edit.gif"));
		btnEdit.setIcon(editIcon);
		mainToolbar.add(btnEdit);
		ImageIcon deleteIcon = new ImageIcon(getClass().getResource("/slike/remove.gif"));
		btnDelete.setIcon(deleteIcon);
		mainToolbar.add(btnDelete);
		
		add(mainToolbar, BorderLayout.NORTH);
		
		String[] zaglavlje = new String[] {"Ime", "Prezime", "Jmbg","Adresa","Broj Telefona", "Korisnicko ime", "Sifra","pol","izabrani lekar","Podaci o knjizici"};
		Object[][] podaci = new Object[this.dom.getPacijent().size()][zaglavlje.length];
		
		for(int i=0; i<this.dom.getPacijent().size(); i++) {
			Pacijent pacijent = this.dom.getPacijent().get(i);
			podaci[i][0] = pacijent.getIme();
			podaci[i][1] = pacijent.getPrezime();
			podaci[i][2] = pacijent.getJmbg();
			podaci[i][3] = pacijent.getAdresa();
			podaci[i][4] = pacijent.getBrojTelefona();
			podaci[i][5] = pacijent.getKorisnickoIme();
			podaci[i][6] = pacijent.getLozinka();
			podaci[i][7] = pacijent.getPol();
			podaci[i][8] = pacijent.getIzabraniLekar();
			podaci[i][9] = pacijent.getPodaciOKnjizici();
		}
		
		tableModel = new DefaultTableModel(podaci, zaglavlje);
		PacijentiTabela = new JTable(tableModel);
		PacijentiTabela = new JTable(tableModel);
		PacijentiTabela.setRowSelectionAllowed(true);
		PacijentiTabela.setColumnSelectionAllowed(false);
		PacijentiTabela.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		PacijentiTabela.setDefaultEditor(Object.class, null);
		
		JScrollPane scrollPane = new JScrollPane(PacijentiTabela);
		add(scrollPane, BorderLayout.CENTER);
	
	btnAdd.addActionListener(new ActionListener() {
		
		@Override
		public void actionPerformed(ActionEvent e) {
			PacijentForme sf = new PacijentForme(dom);
			sf.setVisible(true);
		}
	});
	
	btnEdit.addActionListener(new ActionListener() {
		@Override
		public void actionPerformed(ActionEvent e) {
			int red = PacijentiTabela.getSelectedRow();
			if(red == -1) {
				JOptionPane.showMessageDialog(null, "Morate odabrati red u tabeli.", "Greska", JOptionPane.WARNING_MESSAGE);
			}else {
				DefaultTableModel model = (DefaultTableModel)PacijentiTabela.getModel();
				String korisnickoIme = model.getValueAt(red, 5).toString();
				Pacijent pacijent = dom.nadjiPacijenta(korisnickoIme);
				if(pacijent != null) {
					PacijentForme zf = new PacijentForme(dom);
					zf.setVisible(true);
				}else {
					JOptionPane.showMessageDialog(null, "Nije moguce pronaci odabranog prodavca!", "Greska", JOptionPane.ERROR_MESSAGE);
				}
			}
		}
	});
	btnDelete.addActionListener(new ActionListener() {
		@Override
		public void actionPerformed(ActionEvent e) {
			int red = PacijentiTabela.getSelectedRow();
			if(red == -1) {
				JOptionPane.showMessageDialog(null, "Morate odabrati red u tabeli.", "Greska", JOptionPane.WARNING_MESSAGE);
			}else {
				DefaultTableModel model = (DefaultTableModel)PacijentiTabela.getModel();
				String korisnickoIme = model.getValueAt(red, 5).toString();
				Pacijent pacijent = dom.nadjiPacijenta(korisnickoIme);
				if(pacijent != null) {
					int izbor = JOptionPane.showConfirmDialog(null, "Da li ste sigurni da zelite da obrisete zaposlenog?", pacijent.getKorisnickoIme() + " - Potvrda brisanja", JOptionPane.YES_NO_OPTION);
					if(izbor == JOptionPane.YES_OPTION) {
						dom.getPacijent().remove(pacijent);
						model.removeRow(red);
						dom.snimiZaposlene("Pacijent.txt");
					}
				}else {
					JOptionPane.showMessageDialog(null, "Nije moguce pronaci odabranog prodavca!", "Greska", JOptionPane.ERROR_MESSAGE);
				}
			}
		}
	});
	}
}
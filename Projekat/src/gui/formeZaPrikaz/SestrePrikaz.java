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
import gui.formeZaDodavanje.SestreForme;
import gui.formeZaIzmenuIDodavanje.ZaposleniForma;
import osobe.Prodavac;
import paket1.MedSestra;
import paket1.Pacijent;


public class SestrePrikaz extends JFrame {

	private JToolBar mainToolbar = new JToolBar();
	private JButton btnAdd = new JButton();
	private JButton btnEdit = new JButton();
	private JButton btnDelete = new JButton();
	
	private DefaultTableModel tableModel;
	private JTable SestreTabela;
	
	private DomZdravlja dom;
	
	public SestrePrikaz(DomZdravlja dom) {
		this.dom = dom;
		setTitle("Sestre");
		setSize(500, 300);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setLocationRelativeTo(null);
		initGUI();
		initActions();
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
		
		String[] zaglavlje = new String[] {"Ime", "Prezime", "Jmbg","Adresa","brojTelefona", "Korisnicko ime", "Sifra","pol","ime Sluzber","plata"};
		Object[][] podaci = new Object[this.dom.getSestre().size()][zaglavlje.length];
		
		for(int i=0; i<this.dom.getSestre().size(); i++) {
			MedSestra sestra = this.dom.getSestre().get(i);
			podaci[i][0] = sestra.getIme();
			podaci[i][1] = sestra.getPrezime();
			podaci[i][2] = sestra.getJmbg();
			podaci[i][3] = sestra.getAdresa();
			podaci[i][4] = sestra.getBrojTelefona();
			podaci[i][5] = sestra.getKorisnickoIme();
			podaci[i][6] = sestra.getLozinka();
			podaci[i][7] = sestra.getPol();
			podaci[i][8] = sestra.getsluzba();
			podaci[i][9] = sestra.getPlata();
		}
		
		tableModel = new DefaultTableModel(podaci, zaglavlje);
		SestreTabela = new JTable(tableModel);
		SestreTabela = new JTable(tableModel);
		SestreTabela.setRowSelectionAllowed(true);
		SestreTabela.setColumnSelectionAllowed(false);
		SestreTabela.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		SestreTabela.setDefaultEditor(Object.class, null);
		
		JScrollPane scrollPane = new JScrollPane(SestreTabela);
		add(scrollPane, BorderLayout.CENTER);
	}
	private void initActions() {
		btnAdd.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				SestreForme sf = new SestreForme(dom, null);
				sf.setVisible(true);
			}
		});
		
		btnEdit.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				int red = SestreTabela.getSelectedRow();
				if(red == -1) {
					JOptionPane.showMessageDialog(null, "Morate odabrati red u tabeli.", "Greska", JOptionPane.WARNING_MESSAGE);
				}else {
					DefaultTableModel model = (DefaultTableModel)SestreTabela.getModel();
					String korisnickoIme = model.getValueAt(red, 6).toString();
					MedSestra sestra = dom.nadjiSestru(korisnickoIme);
					if(sestra != null) {
						SestreForme zf = new SestreForme(dom, sestra);
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
				int red = SestreTabela.getSelectedRow();
				if(red == -1) {
					JOptionPane.showMessageDialog(null, "Morate odabrati red u tabeli.", "Greska", JOptionPane.WARNING_MESSAGE);
				}else {
					DefaultTableModel model = (DefaultTableModel)SestreTabela.getModel();
					String korisnickoIme = model.getValueAt(red, 6).toString();
					MedSestra sestra = dom.nadjiSestru(korisnickoIme);
					if(sestra != null) {
						int izbor = JOptionPane.showConfirmDialog(null, "Da li ste sigurni da zelite da obrisete zaposlenog?", sestra.getKorisnickoIme() + " - Potvrda brisanja", JOptionPane.YES_NO_OPTION);
						if(izbor == JOptionPane.YES_OPTION) {
							dom.getSestre().remove(sestra);
							model.removeRow(red);
							dom.snimiZaposlene("medicinskasestra.txt");
						}
					}else {
						JOptionPane.showMessageDialog(null, "Nije moguce pronaci odabranog prodavca!", "Greska", JOptionPane.ERROR_MESSAGE);
					}
				}
			}
		});
		
}
}
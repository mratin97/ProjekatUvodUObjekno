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
import gui.formeZaDodavanje.DoktorForme;
import gui.formeZaDodavanje.SestreForme;
import gui.formeZaIzmenuIDodavanje.ZaposleniForma;
import osobe.Prodavac;
import paket1.Lekar;
import paket1.MedSestra;
import paket1.Pacijent;


public class PrikazDoktora extends JFrame {

	private JToolBar mainToolbar = new JToolBar();
	private JButton btnAdd = new JButton();
	private JButton btnEdit = new JButton();
	private JButton btnDelete = new JButton();
	
	private DefaultTableModel tableModel;
	private JTable LekarTabela;
	
	private DomZdravlja dom;
	
	public PrikazDoktora(DomZdravlja dom) {
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
		
		String[] zaglavlje = new String[] {"Ime", "Prezime", "Jmbg","Adresa","brojTelefona", "Korisnicko ime", "Sifra","pol","ime Sluzber","plata","Specijalizacija"};
		Object[][] podaci = new Object[this.dom.getLekar().size()][zaglavlje.length];
		
		for(int i=0; i<this.dom.getLekar().size(); i++) {
			Lekar doktor= this.dom.getLekar().get(i);
			podaci[i][0] = doktor.getIme();
			podaci[i][1] = doktor.getPrezime();
			podaci[i][2] = doktor.getJmbg();
			podaci[i][3] = doktor.getAdresa();
			podaci[i][4] = doktor.getBrojTelefona();
			podaci[i][5] = doktor.getKorisnickoIme();
			podaci[i][6] = doktor.getLozinka();
			podaci[i][7] = doktor.getPol();
			podaci[i][8] = doktor.getSluzba();
			podaci[i][9] = doktor.getPlata();
			podaci[i][10]= doktor.getSpecijalizacija();
		}
		
		tableModel = new DefaultTableModel(podaci, zaglavlje);
		LekarTabela = new JTable(tableModel);
		LekarTabela = new JTable(tableModel);
		LekarTabela.setRowSelectionAllowed(true);
		LekarTabela.setColumnSelectionAllowed(false);
		LekarTabela.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		LekarTabela.setDefaultEditor(Object.class, null);
		
		JScrollPane scrollPane = new JScrollPane(LekarTabela);
		add(scrollPane, BorderLayout.CENTER);
	}
		private void initActions() {
			btnAdd.addActionListener(new ActionListener() {
				
				@Override
				public void actionPerformed(ActionEvent e) {
					DoktorForme df = new DoktorForme(dom, null);
					df.setVisible(true);
				}
			});
			
			btnEdit.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e) {
					int red = LekarTabela.getSelectedRow();
					if(red == -1) {
						JOptionPane.showMessageDialog(null, "Morate odabrati red u tabeli.", "Greska", JOptionPane.WARNING_MESSAGE);
					}else {
						DefaultTableModel model = (DefaultTableModel)LekarTabela.getModel();
						String korisnickoIme = model.getValueAt(red, 6).toString();
						Lekar doktor = dom.nadjiLekara(korisnickoIme);
						if(doktor != null) {
							DoktorForme df = new DoktorForme(dom, null);
							df.setVisible(true);
						}else {
							JOptionPane.showMessageDialog(null, "Nije moguce pronaci odabranog prodavca!", "Greska", JOptionPane.ERROR_MESSAGE);
						}
					}
				}
			});
			btnDelete.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e) {
					int red = LekarTabela.getSelectedRow();
					if(red == -1) {
						JOptionPane.showMessageDialog(null, "Morate odabrati red u tabeli.", "Greska", JOptionPane.WARNING_MESSAGE);
					}else {
						DefaultTableModel model = (DefaultTableModel)LekarTabela.getModel();
						String korisnickoIme = model.getValueAt(red, 6).toString();
						Lekar doktor = dom.nadjiLekara(korisnickoIme);
						if(doktor != null) {
							int izbor = JOptionPane.showConfirmDialog(null, "Da li ste sigurni da zelite da obrisete zaposlenog?", doktor.getKorisnickoIme() + " - Potvrda brisanja", JOptionPane.YES_NO_OPTION);
							if(izbor == JOptionPane.YES_OPTION) {
								dom.getLekar().remove(doktor);
								model.removeRow(red);
								dom.snimiLekare();
							}
						}else {
							JOptionPane.showMessageDialog(null, "Nije moguce pronaci odabranog prodavca!", "Greska", JOptionPane.ERROR_MESSAGE);
						}
					}
				}
			});
			
		}}
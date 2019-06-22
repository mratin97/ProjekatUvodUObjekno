package gui.formeZaPrikaz;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

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
import gui.formeZaDodavanje.PregledForme;
import gui.formeZaDodavanje.SestreForme;
import gui1.PacijentProzor;
import paket1.*;
import pregled.*;


public class PrikazPregleda extends JFrame {

	private JToolBar mainToolbar = new JToolBar();
	private JButton btnAdd = new JButton();
	private JButton btnEdit = new JButton();
	private JButton btnDelete = new JButton();
	
	private DefaultTableModel tableModel;
	private JTable PacijentiTabela;
	
	private DomZdravlja dom;

	public PrikazPregleda(DomZdravlja dom,Pacijent prijavljenikorisnik) {
		this.dom = dom;
		
		setTitle("Pregledi");
		setSize(500, 300);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setLocationRelativeTo(null);
		initGUI(prijavljenikorisnik);
		initActions(prijavljenikorisnik);
	}
	public PrikazPregleda(DomZdravlja dom,Lekar prijavljenikorisnik) {
		this.dom = dom;
		
		setTitle("Pregledi");
		setSize(500, 300);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setLocationRelativeTo(null);
		initGUI(prijavljenikorisnik);
		initActions(prijavljenikorisnik);
	}
	public PrikazPregleda(DomZdravlja dom) {
		this.dom = dom;
		
		setTitle("Pregledi");
		setSize(500, 300);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setLocationRelativeTo(null);
		initGUI();
		initActions();
	}

	private void initGUI(Pacijent prijavljenikorisnik) {
		ImageIcon addIcon = new ImageIcon(getClass().getResource("/slike/add.gif"));
		btnAdd.setIcon(addIcon);
		mainToolbar.add(btnAdd);
		ImageIcon editIcon = new ImageIcon(getClass().getResource("/slike/edit.gif"));
		btnEdit.setIcon(editIcon);
		mainToolbar.add(btnEdit);
		ImageIcon deleteIcon = new ImageIcon(getClass().getResource("/slike/remove.gif"));
		btnDelete.setIcon(deleteIcon);
		mainToolbar.add(btnDelete);
		int brojac=0;
		add(mainToolbar, BorderLayout.NORTH);
		for(int j =0; j<this.dom.getPregled().size(); j++) {
			Pregled pregled = this.dom.getPregled().get(j);
			String pacijentime=prijavljenikorisnik.getKorisnickoIme();
			String provera=pregled.getPacijent().getKorisnickoIme();
			if (pacijentime==provera) {
				 brojac=+1;
			}
		}
		String[] zaglavlje = new String[] {"Pacijent", "opis", "status","id pregleda", "termin", "doktor"};
		Object[][] podaci = new Object[this.dom.getPregled().size()][zaglavlje.length];
		
		for(int i=0; i<this.dom.getPregled().size(); i++) {
			Pregled pregled = this.dom.getPregled().get(i);
			
			String x=pregled.getPacijent().getKorisnickoIme();
			if (prijavljenikorisnik.getKorisnickoIme()==x) {
			podaci[i][0] = x;
			podaci[i][1] = pregled.getOpis();
			podaci[i][2] = pregled.getStatus();
			podaci[i][3] = pregled.getIdPregleda();
			podaci[i][4] = pregled.getTermin();
			podaci[i][5] = pregled.getDoktor().getIme();
			}
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
	}
	private void initGUI(Lekar prijavljenikorisnik) {
		
		ImageIcon editIcon = new ImageIcon(getClass().getResource("/slike/edit.gif"));
		btnEdit.setIcon(editIcon);
		mainToolbar.add(btnEdit);
		
		
		add(mainToolbar, BorderLayout.NORTH);
		
		String[] zaglavlje = new String[] {"Pacijent", "opis", "status","id pregleda", "termin", "doktor"};
		Object[][] podaci = new Object[this.dom.getPregled().size()][zaglavlje.length];
		
		for(int i=0; i<this.dom.getPregled().size(); i++) {
			Pregled pregled = this.dom.getPregled().get(i);
			
			String x=pregled.getDoktor().getKorisnickoIme();
			if (prijavljenikorisnik.getKorisnickoIme()==x) {
			podaci[i][0] = pregled.getPacijent().getKorisnickoIme();
			podaci[i][1] = pregled.getOpis();
			podaci[i][2] = pregled.getStatus();
			podaci[i][3] = pregled.getIdPregleda();
			podaci[i][4] = pregled.getTermin();
			podaci[i][5] = pregled.getDoktor().getIme();
			}
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
		
		String[] zaglavlje = new String[] {"Pacijent", "opis", "status","id pregleda", "termin", "doktor"};
		Object[][] podaci = new Object[this.dom.getPregled().size()][zaglavlje.length];
		
		for(int i=0; i<this.dom.getPregled().size(); i++) {
			Pregled pregled = this.dom.getPregled().get(i);
			
			podaci[i][0] = pregled.getPacijent().getKorisnickoIme();
			podaci[i][1] = pregled.getOpis();
			podaci[i][2] = pregled.getStatus();
			podaci[i][3] = pregled.getIdPregleda();
			podaci[i][4] = pregled.getTermin();
			podaci[i][5] = pregled.getDoktor().getIme();
			
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
	}
	private void initActions() {
		btnAdd.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				PregledForme sf = new PregledForme(dom, null);
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
					String korisnickoIme = model.getValueAt(red, 3).toString();
					Pregled pregled = dom.nadjiPregled(korisnickoIme);
					if(pregled != null) {
						PregledForme pf = new PregledForme(dom, null);
						pf.setVisible(true);
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
					String korisnickoIme = model.getValueAt(red, 3).toString();
					Pregled pregled = dom.nadjiPregled(korisnickoIme);
					if(pregled != null) {
						int izbor = JOptionPane.showConfirmDialog(null, "Da li ste sigurni da zelite da obrisete zaposlenog?", pregled.getIdPregleda() + " - Potvrda brisanja", JOptionPane.YES_NO_OPTION);
						if(izbor == JOptionPane.YES_OPTION) {
							dom.getPregled().remove(pregled);
							model.removeRow(red);
							dom.snimiZaposlene("pregledi.txt");
						}
					}else {
						JOptionPane.showMessageDialog(null, "Nije moguce pronaci odabranog prodavca!", "Greska", JOptionPane.ERROR_MESSAGE);
					}
				}
			}
		});
	}
	private void initActions(Lekar lekar) {
		
		
		btnEdit.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				int red = PacijentiTabela.getSelectedRow();
				if(red == -1) {
					JOptionPane.showMessageDialog(null, "Morate odabrati red u tabeli.", "Greska", JOptionPane.WARNING_MESSAGE);
				}else {
					DefaultTableModel model = (DefaultTableModel)PacijentiTabela.getModel();
					String korisnickoIme = model.getValueAt(red, 3).toString();
					Pregled pregled = dom.nadjiPregled(korisnickoIme);
					
					
					if(pregled != null) {
						status x=pregled.getStatus();
						status status1=status.Zatrazen;
						if(x==status1) {
						PregledForme pf = new PregledForme(dom, pregled,lekar);
						pf.setVisible(true);
						}else {JOptionPane.showMessageDialog(null, "Pregled je vec Zakazan", "Greska", JOptionPane.ERROR_MESSAGE);
						}
						}
					else {
						JOptionPane.showMessageDialog(null, "Nije moguce pronaci odabranog prodavca!", "Greska", JOptionPane.ERROR_MESSAGE);
					}
				}
			}
		});
		
	}
		private void initActions(Pacijent pacijent) {
			btnAdd.addActionListener(new ActionListener() {
				
				@Override
				public void actionPerformed(ActionEvent e) {
					PregledForme sf = new PregledForme(dom, null,pacijent);
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
						String korisnickoIme = model.getValueAt(red, 3).toString();
						Pregled pregled = dom.nadjiPregled(korisnickoIme);
						
						
						if(pregled != null) {
							status x=pregled.getStatus();
							status status1=status.Zatrazen;
							if(x==status1) {
							PregledForme pf = new PregledForme(dom, null,pacijent);
							pf.setVisible(true);
							}else {JOptionPane.showMessageDialog(null, "Pregled je vec Zakazan", "Greska", JOptionPane.ERROR_MESSAGE);
							}
							}
						else {
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
						String korisnickoIme = model.getValueAt(red, 3).toString();
						Pregled pregled = dom.nadjiPregled(korisnickoIme);
						if(pregled != null) {
							int izbor = JOptionPane.showConfirmDialog(null, "Da li ste sigurni da zelite da obrisete zaposlenog?", pregled.getIdPregleda() + " - Potvrda brisanja", JOptionPane.YES_NO_OPTION);
							if(izbor == JOptionPane.YES_OPTION) {
								dom.getPregled().remove(pregled);
								model.removeRow(red);
								dom.snimiZaposlene("pregledi.txt");
							}
						}else {
							JOptionPane.showMessageDialog(null, "Nije moguce pronaci odabranog prodavca!", "Greska", JOptionPane.ERROR_MESSAGE);
						}
					}
				}
			});
}}
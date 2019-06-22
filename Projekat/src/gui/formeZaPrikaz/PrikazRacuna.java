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


public class PrikazRacuna extends JFrame {
	
	private JToolBar mainToolbar = new JToolBar();
	private JButton btnAdd = new JButton();
	private JButton btnEdit = new JButton();
	private JButton btnDelete = new JButton();
	
	private DefaultTableModel tableModel;
	private JTable PacijentiTabela;
	
	private DomZdravlja dom;

	
	
	public PrikazRacuna(DomZdravlja dom) {
		this.dom = dom;
		
		setTitle("Pregledi");
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
	
		String[] zaglavlje = new String[] {"Pacijent", "opis", "status","id pregleda", "termin", "doktor","Iznos"};
		Object[][] podaci = new Object[this.dom.getPregled().size()][zaglavlje.length];
		int iznos=0;
		for(int i=0; i<this.dom.getPregled().size(); i++) {
			Pregled pregled = this.dom.getPregled().get(i);
			status statusprovera=pregled.getStatus();
			String pac=pregled.getPacijent().getKorisnickoIme();
			ArrayList<Knjizica> knjizicaa=this.dom.getKnjizica();
			for(int j=0;j<this.dom.getKnjizica().size();j++) {
				
			Knjizica knjizica=this.dom.getKnjizica().get(j);
			if(knjizica.getPacijent().getKorisnickoIme()==pac) {
				String kategorija=knjizica.getKategorija();
					if(kategorija=="KATEGORIJA1") {
						iznos=300;
					}else if( kategorija=="KATEGORIJA2")
					{
						iznos=50;
					}
					else {
						iznos=0;
					}
			}
				
			}
			
			if (statusprovera==status.Zavrsen) {
			podaci[i][0] = pregled.getPacijent().getKorisnickoIme();
			podaci[i][1] = pregled.getOpis();
			podaci[i][2] = pregled.getStatus();
			podaci[i][3] = pregled.getIdPregleda();
			podaci[i][4] = pregled.getTermin();
			podaci[i][5] = pregled.getDoktor().getIme();
			podaci[i][6] = iznos;
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
	
	
	
}
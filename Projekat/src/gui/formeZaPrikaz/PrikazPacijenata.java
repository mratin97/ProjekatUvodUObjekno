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
		
		String[] zaglavlje = new String[] {"Ime", "Prezime", "Jmbg","Adresa", "Korisnicko ime", "Sifra","pol","izabrani lekar","Podaci o knjizici"};
		Object[][] podaci = new Object[this.dom.getPacijent().size()][zaglavlje.length];
		
		for(int i=0; i<this.dom.getPacijent().size(); i++) {
			Pacijent pacijent = this.dom.getPacijent().get(i);
			podaci[i][0] = pacijent.getIme();
			podaci[i][1] = pacijent.getPrezime();
			podaci[i][2] = pacijent.getJmbg();
			podaci[i][3] = pacijent.getAdresa();
			podaci[i][4] = pacijent.getKorisnickoIme();
			podaci[i][5] = pacijent.getLozinka();
			podaci[i][6] = pacijent.getPol();
			podaci[i][7] = pacijent.getIzabraniLekar();
			podaci[i][8] = pacijent.getPodaciOKnjizici();
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
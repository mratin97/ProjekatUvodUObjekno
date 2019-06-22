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

import pregled.*;
import domZdravlja.*;
import gui.formeZaDodavanje.KnjizicaForme;
import gui.formeZaDodavanje.PregledForme;
import gui.formeZaDodavanje.SestreForme;

import paket1.*;


public class PrikazKnjizica extends JFrame {

	private JToolBar mainToolbar = new JToolBar();
	private JButton btnAdd = new JButton();
	private JButton btnEdit = new JButton();
	private JButton btnDelete = new JButton();
	
	private DefaultTableModel tableModel;
	private JTable KnjiziceTabela;
	
	private DomZdravlja dom;
	
	public PrikazKnjizica(DomZdravlja dom) {
		this.dom = dom;
		setTitle("Knjizice");
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
		
		String[] zaglavlje = new String[] {"Broj", "Datum isteka", "Kategorija","Pacijent"};
		Object[][] podaci = new Object[this.dom.getKnjizica().size()][zaglavlje.length];
		
		for(int i=0; i<this.dom.getKnjizica().size(); i++) {
			Knjizica knjizica = this.dom.getKnjizica().get(i);
			podaci[i][0] = knjizica.getBroj();
			podaci[i][1] = knjizica.getDatumIsteka();
			
			podaci[i][2] = knjizica.getKategorija();
			podaci[i][3] = knjizica.getPacijent().toStringp();
			
		
			
		}
		
		tableModel = new DefaultTableModel(podaci, zaglavlje);
		KnjiziceTabela = new JTable(tableModel);
		KnjiziceTabela = new JTable(tableModel);
		KnjiziceTabela.setRowSelectionAllowed(true);
		KnjiziceTabela.setColumnSelectionAllowed(false);
		KnjiziceTabela.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		KnjiziceTabela.setDefaultEditor(Object.class, null);
		
		JScrollPane scrollPane = new JScrollPane(KnjiziceTabela);
		add(scrollPane, BorderLayout.CENTER);
	}
	private void initActions() {
		btnAdd.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				KnjizicaForme sf = new KnjizicaForme(dom, null);
				sf.setVisible(true);
			}
		});
		
		btnEdit.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				int red = KnjiziceTabela.getSelectedRow();
				if(red == -1) {
					JOptionPane.showMessageDialog(null, "Morate odabrati red u tabeli.", "Greska", JOptionPane.WARNING_MESSAGE);
				}else {
					DefaultTableModel model = (DefaultTableModel)KnjiziceTabela.getModel();
					String korisnickoIme = model.getValueAt(red, 1).toString();
					Knjizica knjizica = dom.nadjiKnjizicu(korisnickoIme);
					if(knjizica != null) {
						KnjizicaForme pf = new KnjizicaForme(dom, null);
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
				int red = KnjiziceTabela.getSelectedRow();
				if(red == -1) {
					JOptionPane.showMessageDialog(null, "Morate odabrati red u tabeli.", "Greska", JOptionPane.WARNING_MESSAGE);
				}else {
					DefaultTableModel model = (DefaultTableModel)KnjiziceTabela.getModel();
					String korisnickoIme = model.getValueAt(red, 1).toString();
					Knjizica knjizica = dom.nadjiKnjizicu(korisnickoIme);
					if(knjizica != null) {
						int izbor = JOptionPane.showConfirmDialog(null, "Da li ste sigurni da zelite da obrisete zaposlenog?", knjizica.getBroj() + " - Potvrda brisanja", JOptionPane.YES_NO_OPTION);
						if(izbor == JOptionPane.YES_OPTION) {
							dom.getKnjizica().remove(knjizica);
							model.removeRow(red);
							dom.snimiKnjizice();}
					}else {
						JOptionPane.showMessageDialog(null, "Nije moguce pronaci odabranog prodavca!", "Greska", JOptionPane.ERROR_MESSAGE);
					}
				}
			}
		});
	}
}
package view;

import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.DefaultCellEditor;
import javax.swing.JMenuItem;
import javax.swing.JPopupMenu;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.JTextPane;
import javax.swing.event.CellEditorListener;
import javax.swing.event.ChangeEvent;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellEditor;

import Model.Risk;
import javax.swing.JLabel;

public class Activity1002 extends JTabbedPane {

	public Activity1002(Risk risk) {
		DefaultTableModel model;
		DefaultTableModel model2;
		
		String[] colName= {"Name","Probability","Significance","Weight"};
		String[] colName2= {"Name","Plan"};
		Object[][] rowData= {{risk.getName(0),risk.getPro(0),risk.getSig(0),risk.getWeight(0)}};
		Object[][] rowData2= {{risk.getName(0),risk.getPlan(0)}};		
		model=new DefaultTableModel(rowData,colName) {
			/**
			 * 
			 */
			private static final long serialVersionUID = 1L;
			Class[] columnTypes = new Class[] {
					String.class, Integer.class, Integer.class, Integer.class
			};
			public Class getColumnClass(int columnIndex) {
				return columnTypes[columnIndex];
			}
		}; 

		model2=new DefaultTableModel(rowData2,colName2);
		
		for(int i=1; i<risk.get_length();i++) {
			Object[] add= {risk.getName(i),risk.getPro(i),risk.getSig(i),risk.getWeight(i)};
			Object[] add2= {risk.getName(i),risk.getPlan(i)};
			model.addRow(add);
			model2.addRow(add2);
		}
			
		JTable table = new JTable(model);
		JTable table2 = new JTable(model2);
		JTextField tf = new JTextField();
		JTextField tf2 = new JTextField();
		TableCellEditor editor = new DefaultCellEditor(tf);
		TableCellEditor editor2 = new DefaultCellEditor(tf2);
		table.getColumnModel().getColumn(0).setCellEditor(editor);
		table.getColumnModel().getColumn(1).setCellEditor(editor);
		table.getColumnModel().getColumn(2).setCellEditor(editor);
		table.getColumnModel().getColumn(3).setCellEditor(editor);
		table2.getColumnModel().getColumn(0).setCellEditor(editor2);
		table2.getColumnModel().getColumn(1).setCellEditor(editor2);
		
		editor.addCellEditorListener(new CellEditorListener() {

			@Override
			public void editingCanceled(ChangeEvent arg0) {
				// TODO Auto-generated method stub
			}

			@Override
			public void editingStopped(ChangeEvent arg0) {
				// TODO Auto-generated method stub
				table.setValueAt(table.getValueAt(table.getSelectedRow(), table.getSelectedColumn()), table.getSelectedRow(), table.getSelectedColumn());
				if(table.getSelectedColumn()==0) {
					risk.setName((String)table.getValueAt(table.getSelectedRow(), 0),table.getSelectedRow());
				}
				else if(table.getSelectedColumn()==1) {
					risk.setPro(Integer.parseInt((String) table.getValueAt(table.getSelectedRow(), 1)),table.getSelectedRow());
					risk.setWeight((Integer)risk.getPro(table.getSelectedRow()), (Integer)risk.getSig(table.getSelectedRow()), table.getSelectedRow());
				}
				else if(table.getSelectedColumn()==2) {
					risk.setSig(Integer.parseInt((String) table.getValueAt(table.getSelectedRow(), 2)),table.getSelectedRow());
					risk.setWeight((Integer)risk.getPro(table.getSelectedRow()), (Integer)risk.getSig(table.getSelectedRow()), table.getSelectedRow());
				}
				for(int i=0; i<risk.get_length(); i++) {
					model.removeRow(0);
					model2.removeRow(0);
				}
				for(int i=0; i<risk.get_length(); i++) {
					Object[] add= {risk.getName(i),risk.getPro(i),risk.getSig(i),risk.getWeight(i)};
					Object[] add2= {risk.getName(i),risk.getPlan(i)};
					model.addRow(add);
					model2.addRow(add2);
				}
			}	
		});
		
		editor2.addCellEditorListener(new CellEditorListener() {

			@Override
			public void editingCanceled(ChangeEvent arg0) {
				// TODO Auto-generated method stub
			}

			@Override
			public void editingStopped(ChangeEvent arg0) {
				// TODO Auto-generated method stub
				table2.setValueAt(table2.getValueAt(table2.getSelectedRow(), table2.getSelectedColumn()), table2.getSelectedRow(), table2.getSelectedColumn());
				if(table2.getSelectedColumn()==0) {
					risk.setName((String)table2.getValueAt(table2.getSelectedRow(), 0), table2.getSelectedRow());
				}
				else if(table2.getSelectedColumn()==1) {
					risk.setPlan((String)table2.getValueAt(table2.getSelectedRow(), 1), table2.getSelectedRow());
				}
				
				for(int i=0; i<risk.get_length() ;i++) {
					model.removeRow(0);
					model2.removeRow(0);
				}
				for(int i=0; i<risk.get_length();i++) {
					Object[] add= {risk.getName(i),risk.getPro(i),risk.getSig(i),risk.getWeight(i)};
					Object[] add2= {risk.getName(i),risk.getPlan(i)};
					model.addRow(add);
					model2.addRow(add2);
				}
			}
		});	
		
		JScrollPane panel = new JScrollPane(table);
		JScrollPane panel2 = new JScrollPane(table2);
		JPopupMenu popupMenu = new JPopupMenu();
		addPopup(table, popupMenu);
		
		JMenuItem mntmNewMenuItem = new JMenuItem("add row");
		mntmNewMenuItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				risk.add_row();
				Object[] add= {"",1,1,1};
				Object[] add2= {"",""};
				model.addRow(add);
				model2.addRow(add2);
			}
		});
		popupMenu.add(mntmNewMenuItem);
		
		JMenuItem mntmNewMenuItem_1 = new JMenuItem("del row");
		mntmNewMenuItem_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				int row = table.getSelectedRow();
				if(row!=-1) {
					risk.del_row(row);
					model.removeRow(row);
					model2.removeRow(row);
				}
			}
		});
		popupMenu.add(mntmNewMenuItem_1);

		JScrollPane ScrollPane = new JScrollPane();
		this.addTab("Alternative Solution", null, ScrollPane, null);
		
		JTextPane textPane_4 = new JTextPane();
		ScrollPane.setViewportView(textPane_4);
		
		JLabel lblNewLabel = new JLabel("<html>example(Library Management System)<br>"
	            + "- Purchasing such a library managing software, if available<br>"
	            + "- Outsourcing<br>"
	            + "- Other Options<br>"
	            + "</html>");
		ScrollPane.setColumnHeaderView(lblNewLabel);
		
		JScrollPane ScrollPane_1 = new JScrollPane();
		this.addTab("Project Justification", null, ScrollPane_1, null);
		
		JTextPane textPane_5 = new JTextPane();
		ScrollPane_1.setViewportView(textPane_5);
		
		JLabel lblNewLabel_1 = new JLabel("<html>example(Library Management System)<br>"
	            + "- Cost<br>"
	            + "- Duration<br>"
	            + "- Risk<br>"
	            + "- Effect<br>"
	            + "</html>");
		ScrollPane_1.setColumnHeaderView(lblNewLabel_1);
		
		JScrollPane ScrollPane_3 = new JScrollPane();
		this.addTab("Managerial Issues", null, ScrollPane_3, null);
		
		JTextPane textPane_6 = new JTextPane();
		ScrollPane_3.setViewportView(textPane_6);
		
		JLabel lblNewLabel_2 = new JLabel("<html>example(Library Management System)<br>"
	            + "- The project should be compeleted by June, 2008(Plan to participate in a SW exhibition<br>"
	            + "</html>");
		ScrollPane_3.setColumnHeaderView(lblNewLabel_2);

		this.addTab("Risk Management", null, panel, null);

		this.addTab("Risk Reduction Plan", null, panel2, null);
		
		JScrollPane ScrollPane_2 = new JScrollPane();
		this.addTab("Analyze business Plan", null, ScrollPane_2, null);
		
		JTextPane textPane_7 = new JTextPane();
		ScrollPane_2.setViewportView(textPane_7);
		
		JLabel lblNewLabel_3 = new JLabel("<html>example(Library Management System)<br>"
	            + "- A few generic packages are available, however too expensive<br>"
	            + "- May be able to market the software to other similar-scaled libraries<br>"
	            + "</html>");
		ScrollPane_2.setColumnHeaderView(lblNewLabel_3);
		

	}
	private static void addPopup(Component component, final JPopupMenu popup) {
		component.addMouseListener(new MouseAdapter() {
			public void mousePressed(MouseEvent e) {
				if (e.isPopupTrigger()) {
					showMenu(e);
				}
			}
			public void mouseReleased(MouseEvent e) {
				if (e.isPopupTrigger()) {
					showMenu(e);
				}
			}
			private void showMenu(MouseEvent e) {
				popup.show(e.getComponent(), e.getX(), e.getY());
			}
		});
	}
				
}



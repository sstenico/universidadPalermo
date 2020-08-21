package sis.logistica.UX;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.table.DefaultTableModel;


import sis.logistica.general.Handler;

public class InventarioABMUX extends JFrame implements ActionListener{

	private static final long serialVersionUID = 1L;
	MenuBarPane menubar = new MenuBarPane();
	Handler handler = new Handler();
	
	DefaultTableModel model;
	
	public InventarioABMUX (String titulo, Handler handler) {
		super(titulo);
		this.handler  = handler;
		initializateUX(handler);
	}
	
	private void addMenubar(Handler handler) {
		setJMenuBar(menubar.generarMenuHerramientas(this.handler));
		menubar.generarMenuHerramientas(handler)
	}
	
	private void initializateUX( Handler handler) {
		addMenubar(handler);
		setSize(640, 480);
		setLocationRelativeTo(null);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		
	}
	
}

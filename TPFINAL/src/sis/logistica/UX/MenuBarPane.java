package sis.logistica.UX;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JSeparator;

import sis.logistica.general.Handler;

public class MenuBarPane extends JFrame{
	
	private static final long serialVersionUID = 1L;
	
	public JMenuBar generarMenuHerramientas(Handler handler) {
		JMenu menuInventario = new JMenu("Inventario");
		JMenu menuGeneral = new JMenu("Archivo");
		JMenu menuCliente = new JMenu("Cliente");
		
		/**/
		
		/*BARRA DE MENU INVENTARIO*/
		JMenu mnAbm = new JMenu("ABM");
		menuInventario.add(mnAbm);
		
				JMenuItem menuItemAlta = new JMenuItem("Alta");
				mnAbm.add(menuItemAlta);
				menuItemAlta.addActionListener(new ActionListener() {
					@Override
					public void actionPerformed(ActionEvent arg0) {
						changePanel(new PanelInventario(handler, PanelInventario.PanelMode.CREATE));
					}

				});
				
				
				JMenuItem menuItemModificacion = new JMenuItem("Modificación");
				mnAbm.add(menuItemModificacion);
				menuItemModificacion.addActionListener(new ActionListener() {
					@Override
					public void actionPerformed(ActionEvent arg0) {
						changePanel(new PanelInventario(handler, PanelInventario.PanelMode.UPDATE));
						}
					});
				
				JMenuItem menuItemBaja = new JMenuItem("Baja");
				mnAbm.add(menuItemBaja);
				menuItemBaja.addActionListener(new ActionListener() {
						@Override
						public void actionPerformed(ActionEvent arg0) {
							changePanel(new PanelInventario(handler, PanelInventario.PanelMode.DELETE));
						}
				});
		

		JMenuItem menuItemListar = new JMenuItem("Listar");
		menuItemListar.addActionListener(new ActionListener() {
			@Override
		public void actionPerformed(ActionEvent arg0) {
				changePanel(new ListarPanelInventario(handler));
					}
		});
						
				
		/*BARRA DE MENU ARCHIVO*/
		menuGeneral.add("Exportar");
				
		JSeparator separator = new JSeparator();
		menuInventario.add(separator);
		menuInventario.add(menuItemListar);

		JMenuBar menuBar = new JMenuBar();
		menuBar.add(menuGeneral);
		menuBar.add(menuInventario);
		menuBar.add(menuCliente);
		return menuBar;
	}
	
	public void changePanel(JPanel panel) {
		getContentPane().removeAll();
		getContentPane().add(panel);
		getContentPane().validate();
	}
}

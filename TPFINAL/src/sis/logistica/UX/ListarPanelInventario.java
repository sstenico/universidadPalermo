package sis.logistica.UX;
import java.util.List;

import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;

import sis.logistica.Backend.Inventario;
import sis.logistica.general.Handler;

public class ListarPanelInventario extends JPanel {

	private static final long serialVersionUID = 1L;

	Handler handler;

	public ListarPanelInventario(Handler handler) {
		this.handler = handler;
		initUI();
	}

	private void initUI() {
		TablaInventario invTable = GetInventario();
		JTable tabla = new JTable(invTable);
		JScrollPane scroll = new JScrollPane(tabla);
		add(scroll);
	}

	private TablaInventario GetInventario() {
		List<Inventario> inventory = handler.GetInventario();
		return new TablaInventario(inventory);
	}
}
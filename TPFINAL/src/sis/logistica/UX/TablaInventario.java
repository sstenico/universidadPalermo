package sis.logistica.UX;
import java.util.ArrayList;
import java.util.List;

import javax.swing.table.AbstractTableModel;

import sis.logistica.Backend.Inventario;

public class TablaInventario extends AbstractTableModel {

		private static final long serialVersionUID = 1L;

		List<Inventario> inventory = new ArrayList<Inventario>();
		private String[] columnsName = { "ID", "Cod.Articulo", "Descripcion", "Stock", "UOM", "Precio Unitario"};

		private static final int ID 			 = 0;
		private static final int codigo_articulo = 1;
		private static final int descripcion     = 2;
		private static final int stock_cantidad  = 3;
		private static final int unidad_medida   = 4;
		private static final int precio_unitario = 5;

		public TablaInventario(List<Inventario> inventory) {
			this.inventory = inventory;
		}

		@Override
		public int getColumnCount() {
			return columnsName.length;
		}

		@Override
		public int getRowCount() {
			return inventory.size();
		}

		@Override
		public Object getValueAt(int row, int col) {
			Inventario inv = inventory.get(row);
			switch (col) {
			case ID:
				return inv.getInventory_id();
			case codigo_articulo:
				return inv.getCodigoarticulo();
			case descripcion:
				return inv.getDescripcion();
			case stock_cantidad:
				return inv.getStockcantidad();
			case unidad_medida:
				return inv.getUnidadmedida();
			case precio_unitario:
				return inv.getPreciounitario();
			default:
				return null;
			}
		}

		public Inventario getSelectedElement(int row) {
			return inventory.get(row);
		}

		public String getColumnName(int col) {
			return columnsName[col];
		}
	}
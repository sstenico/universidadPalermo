package sis.logistica.UX;


import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.Box;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import sis.logistica.Backend.Inventario;
import sis.logistica.Exception.BOCustomExceptions;
import sis.logistica.general.Handler;

public class PanelInventario extends JPanel  {

		private static final long serialVersionUID = 1L;
		private PanelMode mode;
		private Handler   handler;

		public static enum PanelMode {
			CREATE, UPDATE, DELETE
		}

		public PanelInventario(Handler handler, PanelMode action) {
			mode = action;
			this.handler = handler;
			initUI();
		}

		private void initUI()  {
			final int columnSize = 30;
			final int verticalStructureSize = 20;
			final int horizontalStructureSize = 10;

			Inventario inventario = new Inventario();
		
			JTextField txtcodigoarticulo = new JTextField(columnSize);
			Box boxCodigoArticulo = crearCombo(horizontalStructureSize, "Codigo Articulo", txtcodigoarticulo);

			JTextField txtdescripcion = new JTextField(columnSize);
			Box boxDescripcion = crearCombo(horizontalStructureSize, "Descripcion", txtdescripcion);

			JTextField txtStock = new JTextField(columnSize);
			Box boxStock = crearCombo(horizontalStructureSize, "Stock", txtStock);

			JComboBox<String> comboUnidadMedida = new JComboBox<String>();
			comboUnidadMedida.addItem("Unidad");
			comboUnidadMedida.addItem("Bandeja");
			comboUnidadMedida.addItem("Kilo");
			comboUnidadMedida.addItem("Metro");
			Box boxUnidadMedida = crearCombo(horizontalStructureSize, "Unidad Medida", comboUnidadMedida);

			JTextField txtPrecioUnitario = new JTextField(columnSize);
			Box boxPrecioUnitario = crearCombo(horizontalStructureSize, "Precio Unitario", txtPrecioUnitario);

			Box botonera = Box.createHorizontalBox();
			botonera.add(Box.createHorizontalGlue());
			JButton btnOk = new JButton("Ok");
			btnOk.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent arg0) {
					switch (mode) {
					case CREATE:
						try {
							inventario.setCodigoarticulo(txtcodigoarticulo.getText());
							inventario.setDescripcion(txtdescripcion.getText());
							inventario.setStockcantidad(Integer.parseInt(txtStock.getText()));
							inventario.setUnidadmedida(String.valueOf(comboUnidadMedida.getSelectedItem()));
							inventario.setPreciounitario(Integer.parseInt(txtPrecioUnitario.getText()));
							handler.crearinventario(inventario);
						   } catch (NumberFormatException  ee) {
								JOptionPane.showMessageDialog(null, "Stock/Precio Unitario no pueden ser nulos", "Error", JOptionPane.ERROR_MESSAGE);
						 }
						txtcodigoarticulo.setText(null);
						txtdescripcion.setText(null);
						txtStock.setText(null);
						comboUnidadMedida.setSelectedItem(null);
						txtPrecioUnitario.setText(null);
						break;
					case UPDATE:
						try {
							inventario.setCodigoarticulo(txtcodigoarticulo.getText().trim());
							inventario.setDescripcion(txtdescripcion.getText());
							inventario.setStockcantidad(Integer.parseInt(txtStock.getText()));
							inventario.setUnidadmedida(String.valueOf(comboUnidadMedida.getSelectedItem()));
							inventario.setPreciounitario(Integer.parseInt(txtPrecioUnitario.getText()));
							handler.actualizarinventario(inventario);
							throw new BOCustomExceptions("Error al actualizar inventario");
						} catch (BOCustomExceptions excepinventario) {
							//excepinventario.printStackTrace();
						}
						break;
					case DELETE:
						try {
							handler.eliminarregistroinventario(txtcodigoarticulo.getText());
							throw new BOCustomExceptions("Error al eliminar inventario");
						} catch (BOCustomExceptions excepinventario) {
							//excepinventario.printStackTrace();
						}
						break;
					default:
						break;
					}
				}
			});
			botonera.add(btnOk);

			JButton btnFind = new JButton("Buscar");
			btnFind.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent arg0) {
					Inventario inventory = new Inventario();
					inventory.setCodigoarticulo(txtcodigoarticulo.getText());
				try {
						inventory = handler.getInventario(inventory);
						throw new BOCustomExceptions("Error al buscar articulo");
					} catch (BOCustomExceptions e) {
						//e.printStackTrace();
					}

					if (inventory != null) {
						txtcodigoarticulo.setText(inventory.getCodigoarticulo());
						txtdescripcion.setText(inventory.getDescripcion());
						txtStock.setText(String.valueOf(inventory.getStockcantidad()));
						comboUnidadMedida.setSelectedItem(inventory.getUnidadmedida());
						txtPrecioUnitario.setText(String.valueOf(inventory.getPreciounitario()));
						txtcodigoarticulo.setEnabled(true);
						txtStock.setEnabled(true);
						txtPrecioUnitario.setEnabled(true);
						btnOk.setEnabled(true);
					} else {
						CustomOptionPane.showInformationMessage("Articulo no encontrado.");
					}

				}
			});
			botonera.add(btnFind);
			botonera.add(Box.createHorizontalStrut(horizontalStructureSize));
			botonera.add(new JButton("Cancel"));

			switch (mode) {
			case CREATE:
				btnFind.setVisible(false);
				break;
			case UPDATE:
				txtcodigoarticulo.setVisible(true);
				txtdescripcion.setEnabled(false);
				txtStock.setEnabled(false);
				comboUnidadMedida.setEnabled(false);
				txtPrecioUnitario.setEnabled(false);
				btnOk.setEnabled(false);
				break;
			case DELETE:
				txtdescripcion.setVisible(false);
				boxDescripcion.setVisible(false);
				boxStock.setVisible(false);
				boxUnidadMedida.setVisible(false);
				boxPrecioUnitario.setVisible(false);
				btnOk.setEnabled(false);
				break;
			default:
				break;
			}

			Box panel = Box.createVerticalBox();
			panel.add(boxCodigoArticulo);
			panel.add(Box.createVerticalStrut(verticalStructureSize));
			panel.add(boxDescripcion);
			panel.add(Box.createVerticalStrut(verticalStructureSize));
			panel.add(boxStock);
			panel.add(Box.createVerticalStrut(verticalStructureSize));
			panel.add(boxUnidadMedida);
			panel.add(Box.createVerticalStrut(verticalStructureSize));
			panel.add(boxPrecioUnitario);
			panel.add(Box.createVerticalStrut(verticalStructureSize));
			panel.add(botonera);

			add(panel);
		}

		private Box crearCombo(final int horizontalStructureSize, String labelText, JComponent component) {
			Box boxUsername 	= Box.createHorizontalBox();
			JLabel lblUserName  = new JLabel(labelText);
			boxUsername.add(lblUserName);
			boxUsername.add(Box.createHorizontalStrut(horizontalStructureSize));
			boxUsername.add(component);
			return boxUsername;
		}
	}
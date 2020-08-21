package sis.logistica.UX;
import javax.swing.JOptionPane;

public class CustomOptionPane  extends JOptionPane {

	private static final long serialVersionUID = 1L;

	public static void showInformationMessage(String msg) {
		JOptionPane.showMessageDialog(null, msg, "Atención", JOptionPane.INFORMATION_MESSAGE);
	}

	public static void showErrorMessage(String msg) {
		JOptionPane.showMessageDialog(null, msg, "Error", JOptionPane.ERROR_MESSAGE);
	}
}

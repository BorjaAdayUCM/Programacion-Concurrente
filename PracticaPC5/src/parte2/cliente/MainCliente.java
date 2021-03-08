package parte2.cliente;

import javax.swing.JOptionPane;

public class MainCliente {

	/**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
            	try {
            		if(args.length < 5) {
            			JOptionPane.showMessageDialog(null, "Son necesarias la ip y puerto del servidor y el cliente.\nTambien el numero"
            					+ " de puertos consecutivos que dispone el cliente.", "Error", JOptionPane.ERROR_MESSAGE);
                		return;
                	}
                	else if (args.length == 5) {
                		new Cliente(args, false);
                	}
                	else {
                		new Cliente(args, true);
                	}
            	}
            	catch(Exception e) {
            		e.printStackTrace();
            	}
            
                
            }
        });
    }

}

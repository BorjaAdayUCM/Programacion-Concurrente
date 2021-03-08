package parte2.cliente.swing;

import java.io.File;
import java.util.ArrayList;
import java.awt.event.WindowEvent;
import java.awt.event.WindowAdapter;
import javax.swing.LayoutStyle;
import javax.swing.GroupLayout;
import java.io.IOException;
import javax.swing.JOptionPane;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.Font;
import javax.swing.JFileChooser;
import javax.swing.JTable;
import javax.swing.JScrollPane;
import javax.swing.JLabel;
import javax.swing.JButton;
import parte2.cliente.Cliente;
import javax.swing.JFrame;

@SuppressWarnings("serial")
public class VentanaPrincipal extends JFrame
{
    private Cliente cliente;
    private JButton jButtonActualizar;
    private JButton jButtonAgregar;
    private JButton jButtonDescargar;
    private JButton jButtonEliminar;
    private JLabel jLabelFicherosLocal;
    private JLabel jLabelFicherosServidor;
    private JScrollPane jScrollPane2;
    private JScrollPane jScrollPane1;
    private JTable local;
    private JTable servidor;
    private JFileChooser fc;
    
    public VentanaPrincipal(Cliente cliente) throws InterruptedException {
        this.cliente = cliente;
        this.initComponents();
        this.setLocationRelativeTo(null);
    }
    
    private void initComponents() {
        final String[] columnas = { "" };
        (this.local = new JTable(new ModeloTablaFicherosLocal(columnas, this.cliente.getOyenteServidor()))).setTableHeader(null);
        (this.servidor = new JTable(new ModeloTablaFicherosServidor(columnas, this.cliente.getOyenteServidor()))).setTableHeader(null);
        this.jLabelFicherosLocal = new JLabel();
        this.jLabelFicherosServidor = new JLabel();
        this.jButtonAgregar = new JButton();
        this.jButtonEliminar = new JButton();
        this.jButtonDescargar = new JButton();
        this.jButtonActualizar = new JButton();
        this.jScrollPane2 = new JScrollPane(this.local);
        this.jScrollPane1 = new JScrollPane(this.servidor);
        (this.fc = new JFileChooser(System.getProperty("user.home"))).setMultiSelectionEnabled(true);
        this.setDefaultCloseOperation(3);
        this.jLabelFicherosLocal.setFont(new Font("Times New Roman", 0, 14));
        this.jLabelFicherosLocal.setText("Ficheros Local");
        this.jLabelFicherosServidor.setFont(new Font("Times New Roman", 0, 14));
        this.jLabelFicherosServidor.setText("Ficheros Servidor");
        this.jButtonAgregar.setFont(new Font("Times New Roman", 0, 12));
        this.jButtonAgregar.setText("Agregar");
        this.jButtonAgregar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(final ActionEvent evt) {
                try {
                    VentanaPrincipal.this.jButtonAgregarActionPerformed(evt);
                }
                catch (IOException e) {
                    JOptionPane.showMessageDialog(null, "Error al compartir ficheros con el servidor.", "Error", 0);
                    e.printStackTrace();
                }
            }
        });
        this.jButtonEliminar.setFont(new Font("Times New Roman", 0, 12));
        this.jButtonEliminar.setText("Eliminar");
        this.jButtonEliminar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(final ActionEvent evt) {
                try {
                    VentanaPrincipal.this.jButtonEliminarActionPerformed(evt);
                }
                catch (IOException e) {
                    JOptionPane.showMessageDialog(null, "Error al dejar de compartir los ficheros con el servidor.", "Error", 0);
                    e.printStackTrace();
                }
            }
        });
        this.jButtonDescargar.setFont(new Font("Times New Roman", 0, 12));
        this.jButtonDescargar.setText("Descargar");
        this.jButtonDescargar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(final ActionEvent evt) {
                try {
                    VentanaPrincipal.this.jButtonDescargarActionPerformed(evt);
                }
                catch (IOException e) {
                    JOptionPane.showMessageDialog(null, "Error en la descarga del fichero.", "Error", 0);
                    e.printStackTrace();
                }
            }
        });
        this.jButtonActualizar.setFont(new Font("Times New Roman", 0, 12));
        this.jButtonActualizar.setText("Actualizar");
        this.jButtonActualizar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(final ActionEvent evt) {
                try {
                    VentanaPrincipal.this.jButtonActualizarActionPerformed(evt);
                }
                catch (IOException e) {
                    JOptionPane.showMessageDialog(null, "Error al actualizar la base de datos del servidor.", "Error", 0);
                    e.printStackTrace();
                }
            }
        });
        final GroupLayout layout = new GroupLayout(this.getContentPane());
        this.getContentPane().setLayout(layout);
        layout.setHorizontalGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING).addGroup(layout.createSequentialGroup().addContainerGap(129, 32767).addComponent(this.jLabelFicherosLocal).addGap(90, 90, 90).addComponent(this.jButtonActualizar).addGap(74, 74, 74).addComponent(this.jLabelFicherosServidor).addGap(135, 135, 135)).addGroup(layout.createSequentialGroup().addGap(28, 28, 28).addGroup(layout.createParallelGroup(GroupLayout.Alignment.TRAILING).addGroup(layout.createSequentialGroup().addComponent(this.jButtonAgregar, -2, 135, -2).addGap(26, 26, 26).addComponent(this.jButtonEliminar, -2, 139, -2)).addComponent(this.jScrollPane2, -2, 300, -2)).addPreferredGap(LayoutStyle.ComponentPlacement.RELATED, -1, 32767).addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING, false).addComponent(this.jButtonDescargar, -1, 300, 32767).addComponent(this.jScrollPane1, -1, 300, 32767)).addGap(36, 36, 36)));
        layout.setVerticalGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING).addGroup(layout.createSequentialGroup().addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING).addGroup(layout.createSequentialGroup().addGap(30, 30, 30).addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE).addComponent(this.jLabelFicherosServidor).addComponent(this.jButtonActualizar))).addGroup(GroupLayout.Alignment.TRAILING, layout.createSequentialGroup().addContainerGap().addComponent(this.jLabelFicherosLocal))).addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED).addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING, false).addComponent(this.jScrollPane1, -1, 375, 32767).addComponent(this.jScrollPane2)).addGap(18, 18, 18).addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING).addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE).addComponent(this.jButtonEliminar, -2, 38, -2).addComponent(this.jButtonAgregar, -2, 38, -2)).addComponent(this.jButtonDescargar, -1, -1, 32767)).addGap(40, 40, 40)));
        
        this.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(final WindowEvent windowEvent) {
                try {
                    cliente.cerrarConexion();
                }
                catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });
        
        this.setTitle("Practica PC-5");
        this.pack();
    }
    
    private void jButtonAgregarActionPerformed(final ActionEvent evt) throws IOException {
        final int returnVal = this.fc.showOpenDialog(null);
        if (returnVal == 0) {
            final File[] arrayFicherosAnadir = this.fc.getSelectedFiles();
            final ArrayList<String> ficherosAnadir = new ArrayList<String>();
            for (int i = 0; i < arrayFicherosAnadir.length; ++i) {
                ficherosAnadir.add(new String(arrayFicherosAnadir[i].getAbsolutePath()));
            }
            this.cliente.compartirFicheros(ficherosAnadir);
            this.cliente.ObtenerListaUsuarios();
        }
    }
    
    private void jButtonActualizarActionPerformed(final ActionEvent evt) throws IOException {
        this.cliente.ObtenerListaUsuarios();
    }
    
    private void jButtonEliminarActionPerformed(final ActionEvent evt) throws IOException {
        final int[] seleccionadas = this.local.getSelectedRows();
        final ArrayList<String> ficherosEliminar = new ArrayList<String>();
        int[] array;
        for (int length = (array = seleccionadas).length, j = 0; j < length; ++j) {
            final int i = array[j];
            ficherosEliminar.add(new String((String)this.local.getValueAt(i, 0)));
        }
        this.cliente.dejarCompartirFicheros(ficherosEliminar);
        this.cliente.ObtenerListaUsuarios();
    }
    
    private void jButtonDescargarActionPerformed(final ActionEvent evt) throws IOException {
    	int selectedRow = this.servidor.getSelectedRow();
        if(selectedRow != -1) this.cliente.descargarFichero((String)this.servidor.getValueAt(selectedRow, 0));
    }
}
package main;

import analisis.*;
import archivo.*;
import java.io.File;
import java.util.ArrayList;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;

public class Interfaz extends javax.swing.JFrame {

    JFileChooser seleccionado = new JFileChooser();
    File archivo;
    GestionArchivos gestion = new GestionArchivos();
    ArrayList<Token> lista_token = new ArrayList<>();
    ColorTable Cote = new ColorTable();

    /**
     * Creates new form Interfaz
     */
    public Interfaz() {
        initComponents();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane3 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        BTabrir = new javax.swing.JButton();
        BTanalizar = new javax.swing.JButton();
        BTgrafico = new javax.swing.JButton();
        BTayuda = new javax.swing.JButton();
        BTlimpiar = new javax.swing.JButton();
        jScrollPane4 = new javax.swing.JScrollPane();
        TablaToken = new javax.swing.JTable();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        Acerca_de = new javax.swing.JButton();
        jScrollPane2 = new javax.swing.JScrollPane();
        text_editor = new javax.swing.JTextPane();

        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        jScrollPane3.setViewportView(jTable1);

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        BTabrir.setText("Abrir");
        BTabrir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BTabrirActionPerformed(evt);
            }
        });

        BTanalizar.setText("Analizar");
        BTanalizar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BTanalizarActionPerformed(evt);
            }
        });

        BTgrafico.setText("Grafico");

        BTayuda.setText("Ayuda");
        BTayuda.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BTayudaActionPerformed(evt);
            }
        });

        BTlimpiar.setText("Limpiar");
        BTlimpiar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BTlimpiarActionPerformed(evt);
            }
        });

        TablaToken.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null}
            },
            new String [] {
                "Token", "Lexema", "Patron", "Linea", "Columna"
            }
        ));
        jScrollPane4.setViewportView(TablaToken);

        jLabel1.setText("Reportes");

        jLabel2.setText("Analizador Lexico");

        Acerca_de.setText("Acerca de");
        Acerca_de.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                Acerca_deActionPerformed(evt);
            }
        });

        jScrollPane2.setViewportView(text_editor);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(38, 38, 38)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(6, 6, 6)
                        .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 519, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(52, 52, 52)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(BTanalizar, javax.swing.GroupLayout.PREFERRED_SIZE, 81, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(BTabrir, javax.swing.GroupLayout.PREFERRED_SIZE, 81, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(BTgrafico, javax.swing.GroupLayout.PREFERRED_SIZE, 83, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(BTlimpiar, javax.swing.GroupLayout.PREFERRED_SIZE, 83, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(BTayuda, javax.swing.GroupLayout.PREFERRED_SIZE, 83, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(Acerca_de))
                                .addGap(0, 0, Short.MAX_VALUE))))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 710, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel1)
                            .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 106, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addContainerGap(28, Short.MAX_VALUE))))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap(9, Short.MAX_VALUE)
                .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(BTabrir)
                        .addGap(28, 28, 28)
                        .addComponent(BTanalizar)
                        .addGap(30, 30, 30)
                        .addComponent(BTgrafico)
                        .addGap(30, 30, 30)
                        .addComponent(BTlimpiar)
                        .addGap(31, 31, 31)
                        .addComponent(BTayuda)
                        .addGap(40, 40, 40)
                        .addComponent(Acerca_de))
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 308, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(24, 24, 24)
                .addComponent(jLabel1)
                .addGap(16, 16, 16)
                .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 151, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(23, 23, 23))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void BTabrirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BTabrirActionPerformed
        // TODO add your handling code here:
        if (seleccionado.showDialog(null, "Abrir Archivo") == JFileChooser.APPROVE_OPTION) {
            archivo = seleccionado.getSelectedFile();
            if (archivo.canRead()) {
                if (archivo.getName().endsWith("txt")) {
                    String contenido = gestion.AbrirATexto(archivo);
                    text_editor.setText(contenido);
                } else {
                    JOptionPane.showMessageDialog(null, "Por favor seleccione un archivo.txt");
                }
            }
        }
    }//GEN-LAST:event_BTabrirActionPerformed

    private void BTanalizarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BTanalizarActionPerformed
        // TODO add your handling code here:
        Analizador analizador = new Analizador(lista_token);
        analizador.analizar(text_editor.getText());
        
        DefaultTableModel model = (DefaultTableModel) TablaToken.getModel();
        model.setRowCount(0); // Limpia la tabla
        Cote.colorearEditorTexto(text_editor);
        //***********************************
        for (Token token : lista_token) {
            model.addRow(new Object[]{
                token.getTipo(),
                token.getLexema(),
                token.getPatron(),
                token.getFila(),
                token.getColumna(),
                token.getNumero_token()
            });
        }
    }//GEN-LAST:event_BTanalizarActionPerformed

    private void BTlimpiarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BTlimpiarActionPerformed
        // TODO add your handling code here:
        text_editor.setText("");
        DefaultTableModel model = (DefaultTableModel) TablaToken.getModel();
        model.setRowCount(0);
    }//GEN-LAST:event_BTlimpiarActionPerformed

    private void BTayudaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BTayudaActionPerformed
        // TODO add your handling code here:
        String contenidoAyuda = "Bienvenido al Analizador Lexico basado en phyton\n"
                + "\nPuedes escribir en el text area tu codigo fuente para analizar\n"
                + "\no puedes agregar tu archivo en el boton abrir\n"
                + "\nusa el boton limpiar para borrar lo agregado";

        JOptionPane.showMessageDialog(this, contenidoAyuda, "Ayuda", JOptionPane.INFORMATION_MESSAGE);
    }//GEN-LAST:event_BTayudaActionPerformed

    private void Acerca_deActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Acerca_deActionPerformed
        // TODO add your handling code here:
                String contenidoAyuda = "USCA-CUNOC\n"
                + "Marco Chiché\n"
                + "201832053\n";

        JOptionPane.showMessageDialog(this, contenidoAyuda, "Acerca de", JOptionPane.INFORMATION_MESSAGE);
    }//GEN-LAST:event_Acerca_deActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(Interfaz.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Interfaz.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Interfaz.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Interfaz.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Interfaz().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton Acerca_de;
    private javax.swing.JButton BTabrir;
    private javax.swing.JButton BTanalizar;
    private javax.swing.JButton BTayuda;
    private javax.swing.JButton BTgrafico;
    private javax.swing.JButton BTlimpiar;
    private javax.swing.JTable TablaToken;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JTable jTable1;
    private javax.swing.JTextPane text_editor;
    // End of variables declaration//GEN-END:variables
}
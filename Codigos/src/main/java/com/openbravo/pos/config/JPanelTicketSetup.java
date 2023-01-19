//    uniCenta oPOS  - Touch Friendly Point Of Sale
//    Copyright © 2009-2020 uniCenta
//    https://unicenta.com
//
//    This file is part of uniCenta oPOS
//
//    uniCenta oPOS is free software: you can redistribute it and/or modify
//    it under the terms of the GNU General Public License as published by
//    the Free Software Foundation, either version 3 of the License, or
//    (at your option) any later version.
//
//   uniCenta oPOS is distributed in the hope that it will be useful,
//    but WITHOUT ANY WARRANTY; without even the implied warranty of
//    MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
//    GNU General Public License for more details.
//
//    You should have received a copy of the GNU General Public License
//    along with uniCenta oPOS.  If not, see <http://www.gnu.org/licenses/>.

package com.openbravo.pos.config;

import com.openbravo.data.user.DirtyManager;
import com.openbravo.pos.forms.AppConfig;
import com.openbravo.pos.forms.AppLocal;
import com.openbravo.pos.util.AltEncrypter;
import lombok.extern.slf4j.Slf4j;
import javax.swing.JComboBox;
import javax.swing.*;
import java.awt.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.io.File;

/**
 *
 * @author JG uniCenta
 */
@Slf4j
public class JPanelTicketSetup extends javax.swing.JPanel implements PanelConfig {
    
    private final DirtyManager dirty = new DirtyManager();
    private String receipt="1";
    private Integer x = 0;
    private String receiptSize;
    private String pickupSize;
    private final Integer ps = 0;

    private Connection conn;
    private String sdbmanager;
    private String SQL;
    private Statement stmt;
    /**
     *
     */
    public JPanelTicketSetup() {
        
        initComponents();
        
        jReceiptSize.addChangeListener(dirty);
        jPickupSize.addChangeListener(dirty);
        jTextReceiptPrefix.getDocument().addDocumentListener(dirty);
        m_jReceiptPrintOff.addActionListener(dirty);
        combo1.addActionListener(dirty);

        jbtnReset.setVisible(true);
        
        //impresora
        combo1.addItem("No definida");
        combo1.addItem("Impresora53mm");
        combo1.addItem("Impresora80mm");
    }
    
        
    /**
     *
     * @return
     */
    @Override
    public boolean hasChanged() {
        return dirty.isDirty();
    }
    
    /**
     *
     * @return
     */
    @Override
    public Component getConfigComponent() {
        return this;
    }
   
    /**
     *
     * @param config
     */
    @Override
    public void loadProperties(AppConfig config) {

        receiptSize =(config.getProperty("till.receiptsize"));
        if (receiptSize == null || "".equals(receiptSize)){
            jReceiptSize.setModel(new SpinnerNumberModel(1,1,20,1));
        } else {            
            jReceiptSize.setModel(new SpinnerNumberModel(Integer.parseInt(receiptSize),1,20,1));
        }                

        pickupSize =(config.getProperty("till.pickupsize"));
        if (pickupSize == null || "".equals(pickupSize)){
            jPickupSize.setModel(new SpinnerNumberModel(1,1,20,1));
        } else {            
            jPickupSize.setModel(new SpinnerNumberModel(Integer.parseInt(pickupSize),1,20,1));
        }        
        
        jTextReceiptPrefix.setText(config.getProperty("till.receiptprefix"));        
// build the example receipt using the loaded details        
        receipt="";
        x=1;
        while (x < (Integer)jReceiptSize.getValue()){
            receipt += "0";
        x++; 
    }
         
        receipt += "1";
         jTicketExample.setText(jTextReceiptPrefix.getText()+receipt);  
         m_jReceiptPrintOff.setSelected(Boolean.parseBoolean(config.getProperty("till.receiptprintoff")));           
        dirty.setDirty(false);

        
    }
    
    /*
     * JG Oct 2017 
     * This block to be used for internal SETS/RESETS and external ORDERS sync's  
    */    
    public void loadUp() throws ClassNotFoundException, SQLException {    

/* Add external received order reset block here - 
 * Get connex to secondary or external system's DB + [params]
 * Pref' use is JSON/REST rather than PreparedStatement
*/        
    }
    
    /**
     *
     * @param config
     */
    @Override
    
    public void saveProperties(AppConfig config) {
        
        config.setProperty("till.receiptprefix", jTextReceiptPrefix.getText());
        config.setProperty("till.receiptsize", jReceiptSize.getValue().toString());
        config.setProperty("till.pickupsize", jPickupSize.getValue().toString());        
        config.setProperty("till.receiptprintoff",Boolean.toString(m_jReceiptPrintOff.isSelected()));        
               
        dirty.setDirty(false);
    }
    
    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jTextField2 = new javax.swing.JTextField();
        jLabel1 = new javax.swing.JLabel();
        jReceiptSize = new javax.swing.JSpinner();
        jLabel3 = new javax.swing.JLabel();
        jTextReceiptPrefix = new javax.swing.JTextField();
        jTicketExample = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        jPickupSize = new javax.swing.JSpinner();
        m_jReceiptPrintOff = new javax.swing.JCheckBox();
        jbtnReset = new javax.swing.JButton();
        combo1 = new javax.swing.JComboBox<>();
        lbl1 = new javax.swing.JLabel();

        jTextField2.setText("jTextField2");

        setBackground(new java.awt.Color(255, 255, 255));
        setOpaque(false);
        setPreferredSize(new java.awt.Dimension(700, 500));

        jLabel1.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        java.util.ResourceBundle bundle = java.util.ResourceBundle.getBundle("pos_messages"); // NOI18N
        jLabel1.setText(bundle.getString("label.ticketsetupnumber")); // NOI18N
        jLabel1.setPreferredSize(new java.awt.Dimension(190, 30));

        jReceiptSize.setFont(new java.awt.Font("Arial", 0, 18)); // NOI18N
        jReceiptSize.setModel(new javax.swing.SpinnerNumberModel(0, 0, null, 1));
        jReceiptSize.setPreferredSize(new java.awt.Dimension(50, 30));
        jReceiptSize.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                jReceiptSizeStateChanged(evt);
            }
        });

        jLabel3.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        jLabel3.setText(bundle.getString("label.ticketsetupprefix")); // NOI18N

        jTextReceiptPrefix.setFont(new java.awt.Font("Arial", 0, 18)); // NOI18N
        jTextReceiptPrefix.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        jTextReceiptPrefix.setPreferredSize(new java.awt.Dimension(100, 30));
        jTextReceiptPrefix.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                jTextReceiptPrefixKeyReleased(evt);
            }
        });

        jTicketExample.setFont(new java.awt.Font("Arial", 0, 18)); // NOI18N
        jTicketExample.setText("1");
        jTicketExample.setDisabledTextColor(new java.awt.Color(0, 0, 0));
        jTicketExample.setEnabled(false);
        jTicketExample.setPreferredSize(new java.awt.Dimension(100, 30));

        jLabel2.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        jLabel2.setText(bundle.getString("label.pickupcodesize")); // NOI18N
        jLabel2.setPreferredSize(new java.awt.Dimension(190, 30));

        jPickupSize.setFont(new java.awt.Font("Arial", 0, 18)); // NOI18N
        jPickupSize.setModel(new javax.swing.SpinnerNumberModel(0, 0, null, 1));
        jPickupSize.setToolTipText("");
        jPickupSize.setPreferredSize(new java.awt.Dimension(50, 30));
        jPickupSize.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                jPickupSizeStateChanged(evt);
            }
        });

        m_jReceiptPrintOff.setBackground(new java.awt.Color(255, 255, 255));
        m_jReceiptPrintOff.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        m_jReceiptPrintOff.setText(bundle.getString("label.receiptprint")); // NOI18N
        m_jReceiptPrintOff.setPreferredSize(new java.awt.Dimension(180, 30));
        m_jReceiptPrintOff.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                m_jReceiptPrintOffActionPerformed(evt);
            }
        });

        jbtnReset.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        jbtnReset.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/openbravo/images/reload.png"))); // NOI18N
        jbtnReset.setText(AppLocal.getIntString("label.resetpickup")); // NOI18N
        jbtnReset.setMaximumSize(new java.awt.Dimension(70, 33));
        jbtnReset.setMinimumSize(new java.awt.Dimension(70, 33));
        jbtnReset.setPreferredSize(new java.awt.Dimension(100, 45));
        jbtnReset.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbtnResetActionPerformed(evt);
            }
        });

        combo1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                combo1ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(m_jReceiptPrintOff, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                .addGroup(layout.createSequentialGroup()
                                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 160, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGap(18, 18, 18)
                                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(jReceiptSize, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGroup(layout.createSequentialGroup()
                                            .addComponent(jTextReceiptPrefix, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                            .addComponent(jTicketExample, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                                .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                                    .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGap(18, 18, 18)
                                    .addComponent(jPickupSize, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGap(18, 18, 18)
                                    .addComponent(jbtnReset, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(19, 19, 19)
                        .addComponent(combo1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(39, 39, 39)
                        .addComponent(lbl1, javax.swing.GroupLayout.PREFERRED_SIZE, 132, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(274, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jReceiptSize, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jTextReceiptPrefix, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jTicketExample, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(m_jReceiptPrintOff, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jPickupSize, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jbtnReset, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(combo1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lbl1, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(254, 254, 254))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void jTextReceiptPrefixKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextReceiptPrefixKeyReleased

        jTicketExample.setText(jTextReceiptPrefix.getText()+ receipt);
    }//GEN-LAST:event_jTextReceiptPrefixKeyReleased

    private void jReceiptSizeStateChanged(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_jReceiptSizeStateChanged

        receipt="";
        x=1;
        while (x < (Integer)jReceiptSize.getValue()){
            receipt += "0";
        x++; 
    }
        receipt += "1";
         jTicketExample.setText(jTextReceiptPrefix.getText()+receipt);
         
    }//GEN-LAST:event_jReceiptSizeStateChanged

    private void jPickupSizeStateChanged(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_jPickupSizeStateChanged

    }//GEN-LAST:event_jPickupSizeStateChanged

    private void m_jReceiptPrintOffActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_m_jReceiptPrintOffActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_m_jReceiptPrintOffActionPerformed

    private void jbtnResetActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbtnResetActionPerformed
        int response = JOptionPane.showOptionDialog(null,
            AppLocal.getIntString("message.resetpickup"),
                    "Reset",
                    JOptionPane.YES_NO_OPTION, 
                    JOptionPane.QUESTION_MESSAGE,
                    null, null, null);
        if (response == JOptionPane.YES_OPTION) {
            try {

                String db_url = (AppConfig.getInstance().getProperty("db.URL"));
                String db_schema = (AppConfig.getInstance().getProperty("db.schema"));            
                String db_user =(AppConfig.getInstance().getProperty("db.user"));            
                String db_password = (AppConfig.getInstance().getProperty("db.password"));
            
                if (db_user != null && db_password != null && db_password.startsWith("crypt:")) {
                        AltEncrypter cypher = new AltEncrypter("cypherkey" + db_user);
                    db_password = cypher.decrypt(db_password.substring(6));
                }

                String url = db_url + db_schema;            
            
                conn = DriverManager.getConnection(url,db_user,db_password);
                sdbmanager = conn.getMetaData().getDatabaseProductName();
                stmt = (Statement) conn.createStatement();
            
                if ("MySQL".equals(sdbmanager)) {
                    SQL = "UPDATE pickup_number SET id = 0";
                    try {
                        stmt.executeUpdate(SQL);
                    } catch (SQLException e){
                        System.out.println(e.getMessage()); 
                    }
                } else if ("PostgreSQL".equals(sdbmanager)) {
                    SQL = "ALTER SEQUENCE pickup_number RESTART WITH 1";
                    try {
                        stmt.executeUpdate(SQL);
                    } catch (SQLException e) {
                        System.out.println(e.getMessage());
                    }
                }
            } catch (SQLException ex) { log.error(ex.getMessage());
            }
        }        
    }//GEN-LAST:event_jbtnResetActionPerformed

   
  public void renombrar53(){

      //ticket1
        File archivo1=new File("c:/Users/USUARIO/Desktop/Configurador Punto de Venta/Proyectos/Codigos/src/main/resources/com/openbravo/pos/templates/Printer.Ticket.P1.xml");
        archivo1.renameTo(new File("c:/Users/USUARIO/Desktop/Configurador Punto de Venta/Proyectos/Codigos/src/main/resources/com/openbravo/pos/templates/Printer.Ticket.P1_88.xml"));

        File archivo2=new File("c:/Users/USUARIO/Desktop/Configurador Punto de Venta/Proyectos/Codigos/src/main/resources/com/openbravo/pos/templates/Printer.Ticket.P1_1.xml");
        archivo2.renameTo(new File("c:/Users/USUARIO/Desktop/Configurador Punto de Venta/Proyectos/Codigos/src/main/resources/com/openbravo/pos/templates/Printer.Ticket.P1.xml"));
 

      //ticket2        
        File archivo3=new File("c:/Users/USUARIO/Desktop/Configurador Punto de Venta/Proyectos/Codigos/src/main/resources/com/openbravo/pos/templates/Printer.Ticket.P2.xml");
        archivo3.renameTo(new File("c:/Users/USUARIO/Desktop/Configurador Punto de Venta/Proyectos/Codigos/src/main/resources/com/openbravo/pos/templates/Printer.Ticket.P2_88.xml"));

        File archivo4=new File("c:/Users/USUARIO/Desktop/Configurador Punto de Venta/Proyectos/Codigos/src/main/resources/com/openbravo/pos/templates/Printer.Ticket.P2_1.xml");
        archivo4.renameTo(new File("c:/Users/USUARIO/Desktop/Configurador Punto de Venta/Proyectos/Codigos/src/main/resources/com/openbravo/pos/templates/Printer.Ticket.P2.xml"));
 
        
      //ticket3
        File archivo5=new File("c:/Users/USUARIO/Desktop/Configurador Punto de Venta/Proyectos/Codigos/src/main/resources/com/openbravo/pos/templates/Printer.Ticket.P3.xml");
        archivo5.renameTo(new File("c:/Users/USUARIO/Desktop/Configurador Punto de Venta/Proyectos/Codigos/src/main/resources/com/openbravo/pos/templates/Printer.Ticket.P3_88.xml"));

        File archivo6=new File("c:/Users/USUARIO/Desktop/Configurador Punto de Venta/Proyectos/Codigos/src/main/resources/com/openbravo/pos/templates/Printer.Ticket.P3_1.xml");
        archivo6.renameTo(new File("c:/Users/USUARIO/Desktop/Configurador Punto de Venta/Proyectos/Codigos/src/main/resources/com/openbravo/pos/templates/Printer.Ticket.P3.xml"));
 
      
      //ticket4
        File archivo7=new File("c:/Users/USUARIO/Desktop/Configurador Punto de Venta/Proyectos/Codigos/src/main/resources/com/openbravo/pos/templates/Printer.Ticket.P4.xml");
        archivo7.renameTo(new File("c:/Users/USUARIO/Desktop/Configurador Punto de Venta/Proyectos/Codigos/src/main/resources/com/openbravo/pos/templates/Printer.Ticket.P4_88.xml"));

        File archivo8=new File("c:/Users/USUARIO/Desktop/Configurador Punto de Venta/Proyectos/Codigos/src/main/resources/com/openbravo/pos/templates/Printer.Ticket.P4_1.xml");
        archivo8.renameTo(new File("c:/Users/USUARIO/Desktop/Configurador Punto de Venta/Proyectos/Codigos/src/main/resources/com/openbravo/pos/templates/Printer.Ticket.P4.xml"));
 
      
      //ticket5
        File archivo9=new File("c:/Users/USUARIO/Desktop/Configurador Punto de Venta/Proyectos/Codigos/src/main/resources/com/openbravo/pos/templates/Printer.Ticket.P5.xml");
        archivo9.renameTo(new File("c:/Users/USUARIO/Desktop/Configurador Punto de Venta/Proyectos/Codigos/src/main/resources/com/openbravo/pos/templates/Printer.Ticket.P5_88.xml"));

        File archivo10=new File("c:/Users/USUARIO/Desktop/Configurador Punto de Venta/Proyectos/Codigos/src/main/resources/com/openbravo/pos/templates/Printer.Ticket.P5_1.xml");
        archivo10.renameTo(new File("c:/Users/USUARIO/Desktop/Configurador Punto de Venta/Proyectos/Codigos/src/main/resources/com/openbravo/pos/templates/Printer.Ticket.P5.xml"));
 
      
      //ticket6
        File archivo11=new File("c:/Users/USUARIO/Desktop/Configurador Punto de Venta/Proyectos/Codigos/src/main/resources/com/openbravo/pos/templates/Printer.Ticket.P6.xml");
        archivo11.renameTo(new File("c:/Users/USUARIO/Desktop/Configurador Punto de Venta/Proyectos/Codigos/src/main/resources/com/openbravo/pos/templates/Printer.Ticket.P6_88.xml"));

        File archivo12=new File("c:/Users/USUARIO/Desktop/Configurador Punto de Venta/Proyectos/Codigos/src/main/resources/com/openbravo/pos/templates/Printer.Ticket.P6_1.xml");
        archivo12.renameTo(new File("c:/Users/USUARIO/Desktop/Configurador Punto de Venta/Proyectos/Codigos/src/main/resources/com/openbravo/pos/templates/Printer.Ticket.P6.xml"));
 
        
      //ticket7
        File archivo13=new File("c:/Users/USUARIO/Desktop/Configurador Punto de Venta/Proyectos/Codigos/src/main/resources/com/openbravo/pos/templates/Printer.Ticket.xml");
        archivo13.renameTo(new File("c:/Users/USUARIO/Desktop/Configurador Punto de Venta/Proyectos/Codigos/src/main/resources/com/openbravo/pos/templates/Printer.Ticket_88.xml"));

        File archivo14=new File("c:/Users/USUARIO/Desktop/Configurador Punto de Venta/Proyectos/Codigos/src/main/resources/com/openbravo/pos/templates/Printer.Ticket1.xml");
        archivo14.renameTo(new File("c:/Users/USUARIO/Desktop/Configurador Punto de Venta/Proyectos/Codigos/src/main/resources/com/openbravo/pos/templates/Printer.Ticket.xml"));
 
      
      //ticket8
        File archivo15=new File("c:/Users/USUARIO/Desktop/Configurador Punto de Venta/Proyectos/Codigos/src/main/resources/com/openbravo/pos/templates/Printer.Ticket2.xml");
        archivo15.renameTo(new File("c:/Users/USUARIO/Desktop/Configurador Punto de Venta/Proyectos/Codigos/src/main/resources/com/openbravo/pos/templates/Printer.Ticket2_88.xml"));

        File archivo16=new File("c:/Users/USUARIO/Desktop/Configurador Punto de Venta/Proyectos/Codigos/src/main/resources/com/openbravo/pos/templates/Printer.Ticket2_1.xml");
        archivo16.renameTo(new File("c:/Users/USUARIO/Desktop/Configurador Punto de Venta/Proyectos/Codigos/src/main/resources/com/openbravo/pos/templates/Printer.Ticket2.xml"));
 
        
       //ticket9
        File archivo17=new File("c:/Users/USUARIO/Desktop/Configurador Punto de Venta/Proyectos/Codigos/src/main/resources/com/openbravo/pos/templates/Printer.TicketClose.xml");
        archivo17.renameTo(new File("c:/Users/USUARIO/Desktop/Configurador Punto de Venta/Proyectos/Codigos/src/main/resources/com/openbravo/pos/templates/Printer.TicketClose_88.xml"));

        File archivo18=new File("c:/Users/USUARIO/Desktop/Configurador Punto de Venta/Proyectos/Codigos/src/main/resources/com/openbravo/pos/templates/Printer.TicketClose_1.xml");
        archivo18.renameTo(new File("c:/Users/USUARIO/Desktop/Configurador Punto de Venta/Proyectos/Codigos/src/main/resources/com/openbravo/pos/templates/Printer.TicketClose.xml"));
 
        
       //ticket10 
        File archivo19=new File("c:/Users/USUARIO/Desktop/Configurador Punto de Venta/Proyectos/Codigos/src/main/resources/com/openbravo/pos/templates/Printer.TicketLine.xml");
        archivo19.renameTo(new File("c:/Users/USUARIO/Desktop/Configurador Punto de Venta/Proyectos/Codigos/src/main/resources/com/openbravo/pos/templates/Printer.TicketLine_88.xml"));

        File archivo20=new File("c:/Users/USUARIO/Desktop/Configurador Punto de Venta/Proyectos/Codigos/src/main/resources/com/openbravo/pos/templates/Printer.TicketLine_1.xml");
        archivo20.renameTo(new File("c:/Users/USUARIO/Desktop/Configurador Punto de Venta/Proyectos/Codigos/src/main/resources/com/openbravo/pos/templates/Printer.TicketLine.xml"));
 
        
       //ticket11
        File archivo21=new File("c:/Users/USUARIO/Desktop/Configurador Punto de Venta/Proyectos/Codigos/src/main/resources/com/openbravo/pos/templates/Printer.TicketPreview.xml");
        archivo21.renameTo(new File("c:/Users/USUARIO/Desktop/Configurador Punto de Venta/Proyectos/Codigos/src/main/resources/com/openbravo/pos/templates/Printer.TicketPreview_88.xml"));

        File archivo22=new File("c:/Users/USUARIO/Desktop/Configurador Punto de Venta/Proyectos/Codigos/src/main/resources/com/openbravo/pos/templates/Printer.TicketPreview_1.xml");
        archivo22.renameTo(new File("c:/Users/USUARIO/Desktop/Configurador Punto de Venta/Proyectos/Codigos/src/main/resources/com/openbravo/pos/templates/Printer.TicketPreview.xml"));
 
        
       //ticket12
        File archivo23=new File("c:/Users/USUARIO/Desktop/Configurador Punto de Venta/Proyectos/Codigos/src/main/resources/com/openbravo/pos/templates/Printer.TicketPreview_A4.xml");
        archivo23.renameTo(new File("c:/Users/USUARIO/Desktop/Configurador Punto de Venta/Proyectos/Codigos/src/main/resources/com/openbravo/pos/templates/Printer.TicketPreview_A4_88.xml"));

        File archivo24=new File("c:/Users/USUARIO/Desktop/Configurador Punto de Venta/Proyectos/Codigos/src/main/resources/com/openbravo/pos/templates/Printer.TicketPreview_A4_1.xml");
        archivo24.renameTo(new File("c:/Users/USUARIO/Desktop/Configurador Punto de Venta/Proyectos/Codigos/src/main/resources/com/openbravo/pos/templates/Printer.TicketPreview_A4.xml"));
 
        
       //ticket13
        File archivo25=new File("c:/Users/USUARIO/Desktop/Configurador Punto de Venta/Proyectos/Codigos/src/main/resources/com/openbravo/pos/templates/Printer.TicketRemote.xml");
        archivo25.renameTo(new File("c:/Users/USUARIO/Desktop/Configurador Punto de Venta/Proyectos/Codigos/src/main/resources/com/openbravo/pos/templates/Printer.TicketRemote_88.xml"));

        File archivo26=new File("c:/Users/USUARIO/Desktop/Configurador Punto de Venta/Proyectos/Codigos/src/main/resources/com/openbravo/pos/templates/Printer.TicketRemote_1.xml");
        archivo26.renameTo(new File("c:/Users/USUARIO/Desktop/Configurador Punto de Venta/Proyectos/Codigos/src/main/resources/com/openbravo/pos/templates/Printer.TicketRemote.xml"));
 
        
       //ticket14
        File archivo27=new File("c:/Users/USUARIO/Desktop/Configurador Punto de Venta/Proyectos/Codigos/src/main/resources/com/openbravo/pos/templates/Printer.TicketTotal.xml");
        archivo27.renameTo(new File("c:/Users/USUARIO/Desktop/Configurador Punto de Venta/Proyectos/Codigos/src/main/resources/com/openbravo/pos/templates/Printer.TicketTotal_88.xml"));

        File archivo28=new File("c:/Users/USUARIO/Desktop/Configurador Punto de Venta/Proyectos/Codigos/src/main/resources/com/openbravo/pos/templates/Printer.TicketTotal_1.xml");
        archivo28.renameTo(new File("c:/Users/USUARIO/Desktop/Configurador Punto de Venta/Proyectos/Codigos/src/main/resources/com/openbravo/pos/templates/Printer.TicketTotal.xml"));

        
       //ticket15
        File archivo29=new File("c:/Users/USUARIO/Desktop/Configurador Punto de Venta/Proyectos/Codigos/src/main/resources/com/openbravo/pos/templates/Printer.Ticket_A4.xml");
        archivo29.renameTo(new File("c:/Users/USUARIO/Desktop/Configurador Punto de Venta/Proyectos/Codigos/src/main/resources/com/openbravo/pos/templates/Printer.Ticket_A4_88.xml"));

        File archivo30=new File("c:/Users/USUARIO/Desktop/Configurador Punto de Venta/Proyectos/Codigos/src/main/resources/com/openbravo/pos/templates/Printer.Ticket_A4_1.xml");
        archivo30.renameTo(new File("c:/Users/USUARIO/Desktop/Configurador Punto de Venta/Proyectos/Codigos/src/main/resources/com/openbravo/pos/templates/Printer.Ticket_A4.xml"));
         
  }
    
  public void renombrar80(){
      
       //ticket1
        File archivo1=new File("c:/Users/USUARIO/Desktop/Configurador Punto de Venta/Proyectos/Codigos/src/main/resources/com/openbravo/pos/templates/Printer.Ticket.P1.xml");
        archivo1.renameTo(new File("c:/Users/USUARIO/Desktop/Configurador Punto de Venta/Proyectos/Codigos/src/main/resources/com/openbravo/pos/templates/Printer.Ticket.P1_1.xml"));

        File archivo2=new File("c:/Users/USUARIO/Desktop/Configurador Punto de Venta/Proyectos/Codigos/src/main/resources/com/openbravo/pos/templates/Printer.Ticket.P1_88.xml");
        archivo2.renameTo(new File("c:/Users/USUARIO/Desktop/Configurador Punto de Venta/Proyectos/Codigos/src/main/resources/com/openbravo/pos/templates/Printer.Ticket.P1.xml"));
    
        
       //ticket2
        File archivo3=new File("c:/Users/USUARIO/Desktop/Configurador Punto de Venta/Proyectos/Codigos/src/main/resources/com/openbravo/pos/templates/Printer.Ticket.P2.xml");
        archivo3.renameTo(new File("c:/Users/USUARIO/Desktop/Configurador Punto de Venta/Proyectos/Codigos/src/main/resources/com/openbravo/pos/templates/Printer.Ticket.P2_1.xml"));

        File archivo4=new File("c:/Users/USUARIO/Desktop/Configurador Punto de Venta/Proyectos/Codigos/src/main/resources/com/openbravo/pos/templates/Printer.Ticket.P2_88.xml");
        archivo4.renameTo(new File("c:/Users/USUARIO/Desktop/Configurador Punto de Venta/Proyectos/Codigos/src/main/resources/com/openbravo/pos/templates/Printer.Ticket.P2.xml"));

       
        //ticket3
        File archivo5=new File("c:/Users/USUARIO/Desktop/Configurador Punto de Venta/Proyectos/Codigos/src/main/resources/com/openbravo/pos/templates/Printer.Ticket.P3.xml");
        archivo5.renameTo(new File("c:/Users/USUARIO/Desktop/Configurador Punto de Venta/Proyectos/Codigos/src/main/resources/com/openbravo/pos/templates/Printer.Ticket.P3_1.xml"));

        File archivo6=new File("c:/Users/USUARIO/Desktop/Configurador Punto de Venta/Proyectos/Codigos/src/main/resources/com/openbravo/pos/templates/Printer.Ticket.P3_88.xml");
        archivo6.renameTo(new File("c:/Users/USUARIO/Desktop/Configurador Punto de Venta/Proyectos/Codigos/src/main/resources/com/openbravo/pos/templates/Printer.Ticket.P3.xml"));

        
       //ticket4
        File archivo7=new File("c:/Users/USUARIO/Desktop/Configurador Punto de Venta/Proyectos/Codigos/src/main/resources/com/openbravo/pos/templates/Printer.Ticket.P4.xml");
        archivo7.renameTo(new File("c:/Users/USUARIO/Desktop/Configurador Punto de Venta/Proyectos/Codigos/src/main/resources/com/openbravo/pos/templates/Printer.Ticket.P4_1.xml"));

        File archivo8=new File("c:/Users/USUARIO/Desktop/Configurador Punto de Venta/Proyectos/Codigos/src/main/resources/com/openbravo/pos/templates/Printer.Ticket.P4_88.xml");
        archivo8.renameTo(new File("c:/Users/USUARIO/Desktop/Configurador Punto de Venta/Proyectos/Codigos/src/main/resources/com/openbravo/pos/templates/Printer.Ticket.P4.xml"));
    
       
       //ticket5
        File archivo9=new File("c:/Users/USUARIO/Desktop/Configurador Punto de Venta/Proyectos/Codigos/src/main/resources/com/openbravo/pos/templates/Printer.Ticket.P5.xml");
        archivo9.renameTo(new File("c:/Users/USUARIO/Desktop/Configurador Punto de Venta/Proyectos/Codigos/src/main/resources/com/openbravo/pos/templates/Printer.Ticket.P5_1.xml"));

        File archivo10=new File("c:/Users/USUARIO/Desktop/Configurador Punto de Venta/Proyectos/Codigos/src/main/resources/com/openbravo/pos/templates/Printer.Ticket.P5_88.xml");
        archivo10.renameTo(new File("c:/Users/USUARIO/Desktop/Configurador Punto de Venta/Proyectos/Codigos/src/main/resources/com/openbravo/pos/templates/Printer.Ticket.P5.xml"));
    
       
       //ticket6
        File archivo11=new File("c:/Users/USUARIO/Desktop/Configurador Punto de Venta/Proyectos/Codigos/src/main/resources/com/openbravo/pos/templates/Printer.Ticket.P6.xml");
        archivo11.renameTo(new File("c:/Users/USUARIO/Desktop/Configurador Punto de Venta/Proyectos/Codigos/src/main/resources/com/openbravo/pos/templates/Printer.Ticket.P6_1.xml"));

        File archivo12=new File("c:/Users/USUARIO/Desktop/Configurador Punto de Venta/Proyectos/Codigos/src/main/resources/com/openbravo/pos/templates/Printer.Ticket.P6_88.xml");
        archivo12.renameTo(new File("c:/Users/USUARIO/Desktop/Configurador Punto de Venta/Proyectos/Codigos/src/main/resources/com/openbravo/pos/templates/Printer.Ticket.P6.xml"));
    
       
       //ticket7
        File archivo13=new File("c:/Users/USUARIO/Desktop/Configurador Punto de Venta/Proyectos/Codigos/src/main/resources/com/openbravo/pos/templates/Printer.Ticket.xml");
        archivo13.renameTo(new File("c:/Users/USUARIO/Desktop/Configurador Punto de Venta/Proyectos/Codigos/src/main/resources/com/openbravo/pos/templates/Printer.Ticket1.xml"));

        File archivo14=new File("c:/Users/USUARIO/Desktop/Configurador Punto de Venta/Proyectos/Codigos/src/main/resources/com/openbravo/pos/templates/Printer.Ticket_88.xml");
        archivo14.renameTo(new File("c:/Users/USUARIO/Desktop/Configurador Punto de Venta/Proyectos/Codigos/src/main/resources/com/openbravo/pos/templates/Printer.Ticket.xml"));
    
        
       //ticket8
        File archivo15=new File("c:/Users/USUARIO/Desktop/Configurador Punto de Venta/Proyectos/Codigos/src/main/resources/com/openbravo/pos/templates/Printer.Ticket2.xml");
        archivo15.renameTo(new File("c:/Users/USUARIO/Desktop/Configurador Punto de Venta/Proyectos/Codigos/src/main/resources/com/openbravo/pos/templates/Printer.Ticket2_1.xml"));

        File archivo16=new File("c:/Users/USUARIO/Desktop/Configurador Punto de Venta/Proyectos/Codigos/src/main/resources/com/openbravo/pos/templates/Printer.Ticket2_88.xml");
        archivo16.renameTo(new File("c:/Users/USUARIO/Desktop/Configurador Punto de Venta/Proyectos/Codigos/src/main/resources/com/openbravo/pos/templates/Printer.Ticket2.xml"));
    
       
       //ticket9
        File archivo17=new File("c:/Users/USUARIO/Desktop/Configurador Punto de Venta/Proyectos/Codigos/src/main/resources/com/openbravo/pos/templates/Printer.TicketClose.xml");
        archivo17.renameTo(new File("c:/Users/USUARIO/Desktop/Configurador Punto de Venta/Proyectos/Codigos/src/main/resources/com/openbravo/pos/templates/Printer.TicketClose_1.xml"));

        File archivo18=new File("c:/Users/USUARIO/Desktop/Configurador Punto de Venta/Proyectos/Codigos/src/main/resources/com/openbravo/pos/templates/Printer.Ticketclose_88.xml");
        archivo18.renameTo(new File("c:/Users/USUARIO/Desktop/Configurador Punto de Venta/Proyectos/Codigos/src/main/resources/com/openbravo/pos/templates/Printer.TicketClose.xml"));
    
        
       //ticket10
        File archivo19=new File("c:/Users/USUARIO/Desktop/Configurador Punto de Venta/Proyectos/Codigos/src/main/resources/com/openbravo/pos/templates/Printer.TicketLine.xml");
        archivo19.renameTo(new File("c:/Users/USUARIO/Desktop/Configurador Punto de Venta/Proyectos/Codigos/src/main/resources/com/openbravo/pos/templates/Printer.TicketLine_1.xml"));

        File archivo20=new File("c:/Users/USUARIO/Desktop/Configurador Punto de Venta/Proyectos/Codigos/src/main/resources/com/openbravo/pos/templates/Printer.TicketLine_88.xml");
        archivo20.renameTo(new File("c:/Users/USUARIO/Desktop/Configurador Punto de Venta/Proyectos/Codigos/src/main/resources/com/openbravo/pos/templates/Printer.TicketLine.xml"));
    
        
       //ticket11
        File archivo21=new File("c:/Users/USUARIO/Desktop/Configurador Punto de Venta/Proyectos/Codigos/src/main/resources/com/openbravo/pos/templates/Printer.TicketPreview.xml");
        archivo21.renameTo(new File("c:/Users/USUARIO/Desktop/Configurador Punto de Venta/Proyectos/Codigos/src/main/resources/com/openbravo/pos/templates/Printer.TicketPreview_1.xml"));

        File archivo22=new File("c:/Users/USUARIO/Desktop/Configurador Punto de Venta/Proyectos/Codigos/src/main/resources/com/openbravo/pos/templates/Printer.TicketPreview_88.xml");
        archivo22.renameTo(new File("c:/Users/USUARIO/Desktop/Configurador Punto de Venta/Proyectos/Codigos/src/main/resources/com/openbravo/pos/templates/Printer.TicketPreview.xml"));
    
       
       //ticket12
        File archivo23=new File("c:/Users/USUARIO/Desktop/Configurador Punto de Venta/Proyectos/Codigos/src/main/resources/com/openbravo/pos/templates/Printer.TicketPreview_A4.xml");
        archivo23.renameTo(new File("c:/Users/USUARIO/Desktop/Configurador Punto de Venta/Proyectos/Codigos/src/main/resources/com/openbravo/pos/templates/Printer.TicketPreview_A4_1.xml"));

        File archivo24=new File("c:/Users/USUARIO/Desktop/Configurador Punto de Venta/Proyectos/Codigos/src/main/resources/com/openbravo/pos/templates/Printer.TicketPreview_A4_88.xml");
        archivo24.renameTo(new File("c:/Users/USUARIO/Desktop/Configurador Punto de Venta/Proyectos/Codigos/src/main/resources/com/openbravo/pos/templates/Printer.TicketPreview_A4.xml"));
    
       
       //ticket13
        File archivo25=new File("c:/Users/USUARIO/Desktop/Configurador Punto de Venta/Proyectos/Codigos/src/main/resources/com/openbravo/pos/templates/Printer.TicketRemote.xml");
        archivo25.renameTo(new File("c:/Users/USUARIO/Desktop/Configurador Punto de Venta/Proyectos/Codigos/src/main/resources/com/openbravo/pos/templates/Printer.TicketRemote_1.xml"));

        File archivo26=new File("c:/Users/USUARIO/Desktop/Configurador Punto de Venta/Proyectos/Codigos/src/main/resources/com/openbravo/pos/templates/Printer.TicketRemote_88.xml");
        archivo26.renameTo(new File("c:/Users/USUARIO/Desktop/Configurador Punto de Venta/Proyectos/Codigos/src/main/resources/com/openbravo/pos/templates/Printer.TicketRemote.xml"));
    
       
       //ticket14
        File archivo27=new File("c:/Users/USUARIO/Desktop/Configurador Punto de Venta/Proyectos/Codigos/src/main/resources/com/openbravo/pos/templates/Printer.TicketTotal.xml");
        archivo27.renameTo(new File("c:/Users/USUARIO/Desktop/Configurador Punto de Venta/Proyectos/Codigos/src/main/resources/com/openbravo/pos/templates/Printer.TicketTotal_1.xml"));

        File archivo28=new File("c:/Users/USUARIO/Desktop/Configurador Punto de Venta/Proyectos/Codigos/src/main/resources/com/openbravo/pos/templates/Printer.TicketTotal_88.xml");
        archivo28.renameTo(new File("c:/Users/USUARIO/Desktop/Configurador Punto de Venta/Proyectos/Codigos/src/main/resources/com/openbravo/pos/templates/Printer.TicketTotal.xml"));

        
       //ticket15
        File archivo29=new File("c:/Users/USUARIO/Desktop/Configurador Punto de Venta/Proyectos/Codigos/src/main/resources/com/openbravo/pos/templates/Printer.Ticket_A4.xml");
        archivo29.renameTo(new File("c:/Users/USUARIO/Desktop/Configurador Punto de Venta/Proyectos/Codigos/src/main/resources/com/openbravo/pos/templates/Printer.Ticket_A4_1.xml"));

        File archivo30=new File("c:/Users/USUARIO/Desktop/Configurador Punto de Venta/Proyectos/Codigos/src/main/resources/com/openbravo/pos/templates/Printer.Ticket_A4_88.xml");
        archivo30.renameTo(new File("c:/Users/USUARIO/Desktop/Configurador Punto de Venta/Proyectos/Codigos/src/main/resources/com/openbravo/pos/templates/Printer.Ticket_A4.xml"));

  }
  //boton53mm
//    if(JButton53.isSelected());{
//        renombrar53();
////      JButton53.setBackground(new Color(255, 251, 32));
//        JButton53.setFont(new Font(Font.SERIF,Font.BOLD,13));
//        JButton53.setText("Impresora 53mm seleccionada");
//        
//      JButton80.setFont(new Font(Font.SERIF,Font.BOLD,13));
//        JButton80.setText("Impresora 88mm no seleccionada");        
//       }
         
//boton80mm
//            if(JButton80.isSelected());{
//        renombrar80();
////      JButton53.setBackground(new Color(255, 251, 32));
//        JButton80.setFont(new Font(Font.SERIF,Font.BOLD,13));
//        JButton80.setText("Impresora 88mm seleccionada");
//        
//        JButton53.setFont(new Font(Font.SERIF,Font.BOLD,13));
//        JButton53.setText("Impresora 53mm no seleccionada");        
//       }
    private void combo1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_combo1ActionPerformed
        // TODO add your handling code here:
        int num=combo1.getSelectedIndex();        
//        if(combo1.getSelectedIndex()>=1){       
        if(combo1.getSelectedIndex()>=1){
        renombrar53();
        lbl1.setText("Impresora53mm");
        }
        if(combo1.getSelectedIndex()>=2){
        renombrar80();
        lbl1.setText("Impresora80mm");
        }
    }//GEN-LAST:event_combo1ActionPerformed
    
    
           
    
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JComboBox<String> combo1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JSpinner jPickupSize;
    private javax.swing.JSpinner jReceiptSize;
    private javax.swing.JTextField jTextField2;
    private javax.swing.JTextField jTextReceiptPrefix;
    private javax.swing.JTextField jTicketExample;
    private javax.swing.JButton jbtnReset;
    private javax.swing.JLabel lbl1;
    private javax.swing.JCheckBox m_jReceiptPrintOff;
    // End of variables declaration//GEN-END:variables

    private String comboValue(Object selectedItem) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    
}

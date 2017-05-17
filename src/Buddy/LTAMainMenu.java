/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Buddy;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;

/**
 *
 * @author User
 */
public class LTAMainMenu extends javax.swing.JFrame {

    /**
     * Creates new form LTAMainMenu
     */
    public LTAMainMenu() {
        initComponents();
        homeLabel.setOpaque(true);
        
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        busButton = new javax.swing.JButton();
        jButton1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        homeLabel = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setBackground(new java.awt.Color(255, 255, 255));
        setForeground(new java.awt.Color(255, 255, 255));

        busButton.setIcon(new javax.swing.ImageIcon(getClass().getResource("/LTAResources/busIcon.png"))); // NOI18N
        busButton.setToolTipText("Show all buses");
        busButton.setBorderPainted(false);
        busButton.setMinimumSize(new java.awt.Dimension(0, 0));
        busButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                busButtonActionPerformed(evt);
            }
        });

        jButton1.setText("jButton1");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jButton2.setText("jButton2");

        homeLabel.setBackground(new java.awt.Color(204, 0, 153));
        homeLabel.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N
        homeLabel.setForeground(new java.awt.Color(255, 255, 255));
        homeLabel.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        homeLabel.setText("Home");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(42, Short.MAX_VALUE)
                .addComponent(busButton, javax.swing.GroupLayout.PREFERRED_SIZE, 149, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(39, 39, 39)
                .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 151, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(35, 35, 35)
                .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 153, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(35, 35, 35))
            .addComponent(homeLabel, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(homeLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 58, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(28, 28, 28)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 137, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 137, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(busButton, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(37, Short.MAX_VALUE))
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void busButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_busButtonActionPerformed
        // TODO add your handling code here:
        LTADisplayMenu displayMenu = new LTADisplayMenu();
        displayMenu.PopulateBusplayList();
        displayMenu.setVisible(true);
        
    }//GEN-LAST:event_busButtonActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        // TODO add your handling code here:
        
        LTASearchMenu searchMenu = new LTASearchMenu();
        searchMenu.setVisible(true);
//        GenericStation start = LTAManager.GetGenericStation("1012");
//        GenericStation end =  LTAManager.GetGenericStation("1013");
//        
//        
//        
//        ArrayList<StationRouteInfo> pathInfo = LTAStar.FindPath(start, end);
//
//        System.out.println(pathInfo.size());
//        for (int i = 0; i < pathInfo.size(); i++)
//        {
//            GenericStation curStation = LTAManager.GetGenericStation(pathInfo.get(i).getStationCode());
//            System.out.println(pathInfo.get(i).getServiceNo()+ " : " + pathInfo.get(i).getStationCode()+ " : " + round(curStation.distFromStartPoint, 1));
//            
//        }
        
    }//GEN-LAST:event_jButton1ActionPerformed

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
            java.util.logging.Logger.getLogger(LTAMainMenu.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(LTAMainMenu.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(LTAMainMenu.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(LTAMainMenu.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new LTAMainMenu().setVisible(true);
                
            }
        });
    }
    
    public static double round(double value, int places) 
    {
        if (places < 0) throw new IllegalArgumentException();

        BigDecimal bd = new BigDecimal(value);
        bd = bd.setScale(places, RoundingMode.HALF_UP);
        return bd.doubleValue();
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton busButton;
    private javax.swing.JLabel homeLabel;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    // End of variables declaration//GEN-END:variables
}

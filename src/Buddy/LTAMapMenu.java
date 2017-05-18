/*

 * To change this license header, choose License Headers in Project Properties.

 * To change this template file, choose Tools | Templates

 * and open the template in the editor.

 */
package Buddy;

import eu.jacquet80.minigeo.MapPanel;

import eu.jacquet80.minigeo.POI;

import eu.jacquet80.minigeo.Point;

import eu.jacquet80.minigeo.Segment;
import java.awt.BasicStroke;


import java.awt.BorderLayout;

import java.awt.Color;
import java.awt.Component;
import java.util.ArrayList;

import java.util.Collection;
import javax.swing.DefaultListCellRenderer;
import javax.swing.DefaultListModel;
import javax.swing.JLabel;
import javax.swing.JList;


/**
 *
 *
 *
 * @author User
 *
 */
public class LTAMapMenu extends javax.swing.JFrame {

    private MapPanel map;
    public PathFindFactor ptf;

    public static enum Field {
        START, DEST
    };

    private GenericStation startStation;

    private GenericStation destStation;
    

    /**
     *
     * Creates new form LTAMapMenu
     *
     */
    public LTAMapMenu() {

        initComponents();

        jComboBox1.removeAllItems();
        
        for(PathFindFactor s : PathFindFactor.values())
        {
            jComboBox1.addItem(s.name());
        }
        
        map = new MapPanel();

        mapPanel.add(map, BorderLayout.CENTER);

        map.setSize(mapPanel.getWidth(), mapPanel.getHeight());
        
        map.setViewAtPoint(new Vector2(103.7783793, 1.311225845));

      
//        addPOI(new POI(48, 2, "START"));
        PopulateMapPoint();  
        //addSegment(new Segment(new Point(1.311225845, 103.7783793), new Point(1.31168308, 103.7787018), Color.RED));

    }

    public void PopulateMapPoint() 
    {
        for (GenericStationLocationInfo gsli : LTAManager.StationLocationHashmap.values()) {

            GenericStation gs = LTAManager.GetGenericStation(gsli.stationCode);

            if (gs != null) 
            {
                POI currentPOI = new POI(gsli.position.y, gsli.position.x, LTAManager.GetGenericStation(gsli.stationCode).name);
                currentPOI.nodeType = Point.NodeType.POINT;
                currentPOI.pointColor = Color.DARK_GRAY;
                currentPOI.pointSize = 10;
                addPOI(currentPOI);
            }

        }
        
        for (Service service : LTAManager.ServiceHashMap.values())
        {
            for (Route route : service.GetRouteList())
            {
                PlotRouteGraph(route.getAllStationRouteInfo(), Color.GRAY, true);
            }
        }
//                if (previousGSLIPOI != null)
//                {
//                    addSegment(new Segment(previousGSLIPOI, currentPOI, Color.GRAY));
//                }
//                previousGSLIPOI = currentPOI;
        //addPOI(new POI(10.0, 0.8, "Center"));;

    }
    
    public void ResetMap()
    {
        map.clear();
        PopulateMapPoint();
        map.resetScale();
    }
    
    public void PlotRouteGraph(ArrayList<StationRouteInfo> allSRI, Color lineColor, boolean pEnable)
    {
        POI previousGSLIPOI = null;
        for (StationRouteInfo sri : allSRI)
        {
            GenericStationLocationInfo gsli = LTAManager.StationLocationHashmap.get(sri.getStationCode());

            POI currentPOI = new POI(gsli.position.y, gsli.position.x, LTAManager.GetGenericStation(gsli.stationCode).name);

            if (previousGSLIPOI != null)
            {
                Segment seg = new Segment(previousGSLIPOI, currentPOI, lineColor);
                seg.pEnable = pEnable;
                addSegment(seg);
            }
            previousGSLIPOI = currentPOI;
        }
    }

    public void PlotTransfer(ArrayList<StationRouteInfo> allSRI)
    {
        StationRouteInfo previousSRI = null;
        for (int i = 0; i < allSRI.size(); i++)
        {
            StationRouteInfo currentSRI = allSRI.get(i);
            
            if(previousSRI == null || !currentSRI.getServiceNo().equals(previousSRI.getServiceNo()))
            {
                String poiName = "";
                
                if(previousSRI != null)
                {
                    poiName += "Transfer: " + previousSRI.getServiceNo() + " > ";
                }
                
                poiName += (i == 0) ? "Start - " : "";
                poiName += currentSRI.getServiceNo();
                
                GenericStationLocationInfo gsli = LTAManager.StationLocationHashmap.get(currentSRI.getStationCode());
                
                POI currentPOI = new POI(gsli.position.y, gsli.position.x, poiName);
                currentPOI.nodeType = Point.NodeType.NODE;
                currentPOI.pointColor = Color.BLUE;
                currentPOI.pointSize = 20;
                addPOI(currentPOI);
            }
            
//            if(i == allSRI.size() - 1)
//            {
//                GenericStationLocationInfo gsli = LTAManager.StationLocationHashmap.get(currentSRI.getStationCode());
//                
//                POI currentPOI = new POI(gsli.position.y, gsli.position.x, "Destination");
//                currentPOI.nodeType = Point.NodeType.NODE;
//                currentPOI.pointColor = Color.BLUE;
//                currentPOI.pointSize = 30;
//                addPOI(currentPOI);
//            }
            
            previousSRI = currentSRI;
        }
    }
    
    /**
     *
     * This method is called from within the constructor to initialize the form.
     *
     * WARNING: Do NOT modify this code. The content of this method is always
     *
     * regenerated by the Form Editor.
     *
     */
    @SuppressWarnings("unchecked")

    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jPanel2 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        startField = new javax.swing.JTextField();
        jSeparator1 = new javax.swing.JSeparator();
        jLabel3 = new javax.swing.JLabel();
        destinationField = new javax.swing.JTextField();
        jSeparator3 = new javax.swing.JSeparator();
        jComboBox1 = new javax.swing.JComboBox<>();
        jPathFind = new javax.swing.JButton();
        jResetButton = new javax.swing.JButton();
        jScrollPane2 = new javax.swing.JScrollPane();
        jList1 = new javax.swing.JList<>();
        mapPanel = new javax.swing.JPanel();
        jPanel3 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        routeList = new javax.swing.JList<>();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setResizable(false);

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));

        jPanel2.setBackground(new java.awt.Color(66, 133, 244));

        jLabel1.setFont(new java.awt.Font("Tahoma", 0, 15)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 255, 255));
        jLabel1.setText("Start");
        jLabel1.setAlignmentY(0.1F);

        startField.setEditable(false);
        startField.setBackground(new java.awt.Color(66, 133, 244));
        startField.setFont(new java.awt.Font("Tahoma", 0, 15)); // NOI18N
        startField.setForeground(new java.awt.Color(255, 255, 255));
        startField.setAlignmentY(0.1F);
        startField.setBorder(null);
        startField.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                startFieldMouseClicked(evt);
            }
        });
        startField.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                startFieldKeyTyped(evt);
            }
        });

        jLabel3.setFont(new java.awt.Font("Tahoma", 0, 15)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(255, 255, 255));
        jLabel3.setText("End");

        destinationField.setEditable(false);
        destinationField.setBackground(new java.awt.Color(66, 133, 244));
        destinationField.setFont(new java.awt.Font("Tahoma", 0, 15)); // NOI18N
        destinationField.setForeground(new java.awt.Color(255, 255, 255));
        destinationField.setBorder(null);
        destinationField.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                destinationFieldMouseClicked(evt);
            }
        });

        jPathFind.setText("Search");
        jPathFind.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jPathFindActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(36, 36, 36)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel1)
                    .addComponent(jLabel3))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(jComboBox1, 0, 118, Short.MAX_VALUE)
                        .addGap(18, 18, 18)
                        .addComponent(jPathFind))
                    .addComponent(jSeparator3, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(startField)
                    .addComponent(destinationField)
                    .addComponent(jSeparator1, javax.swing.GroupLayout.Alignment.TRAILING))
                .addGap(37, 37, 37))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(65, 65, 65)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, 31, Short.MAX_VALUE)
                    .addComponent(startField))
                .addGap(5, 5, 5)
                .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jLabel3, javax.swing.GroupLayout.DEFAULT_SIZE, 31, Short.MAX_VALUE)
                    .addComponent(destinationField))
                .addGap(2, 2, 2)
                .addComponent(jSeparator3, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jComboBox1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jPathFind))
                .addContainerGap(24, Short.MAX_VALUE))
        );

        jResetButton.setText("Reset Map");
        jResetButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jResetButtonActionPerformed(evt);
            }
        });

        jList1.setModel(new javax.swing.AbstractListModel<String>() {
            String[] strings = { "Item 1", "Item 2", "Item 3", "Item 4", "Item 5" };
            public int getSize() { return strings.length; }
            public String getElementAt(int i) { return strings[i]; }
        });
        jScrollPane2.setViewportView(jList1);

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jResetButton)
                .addContainerGap())
            .addComponent(jScrollPane2)
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 310, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jResetButton)
                .addContainerGap())
        );

        mapPanel.setOpaque(false);

        javax.swing.GroupLayout mapPanelLayout = new javax.swing.GroupLayout(mapPanel);
        mapPanel.setLayout(mapPanelLayout);
        mapPanelLayout.setHorizontalGroup(
            mapPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 771, Short.MAX_VALUE)
        );
        mapPanelLayout.setVerticalGroup(
            mapPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );

        jPanel3.setBackground(new java.awt.Color(255, 255, 255));

        routeList.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jScrollPane1.setViewportView(routeList);

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 371, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane1)
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(mapPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(mapPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents


    private void startFieldKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_startFieldKeyTyped

        // TODO add your handling code here:

    }//GEN-LAST:event_startFieldKeyTyped


    private void jResetButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jResetButtonActionPerformed

        // TODO add your handling code here:
        //map.setViewAtPoint(new Vector2(103.7783793, 1.311225845));

        ResetMap();
       
    }//GEN-LAST:event_jResetButtonActionPerformed


    private void startFieldMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_startFieldMouseClicked

        // TODO add your handling code here:
        LTASearchMenu searchMenu = new LTASearchMenu(this, Field.START);

        searchMenu.setVisible(true);


    }//GEN-LAST:event_startFieldMouseClicked


    private void destinationFieldMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_destinationFieldMouseClicked

        // TODO add your handling code here:
        LTASearchMenu searchMenu = new LTASearchMenu(this, Field.DEST);

        searchMenu.setVisible(true);

    }//GEN-LAST:event_destinationFieldMouseClicked

    private void jPathFindActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jPathFindActionPerformed
        // TODO add your handling code here:
        if(startStation != null && destStation != null)
        {
            ptf = PathFindFactor.values()[jComboBox1.getSelectedIndex()];
            ArrayList<StationRouteInfo> info = LTAStar.FindPath(startStation, destStation, ptf);
            PopulateRouteList(info);

            ResetMap();
            
            map.setViewAtPoint(LTAManager.StationLocationHashmap.get(startStation.GetID()).position);

            PlotRouteGraph(info, Color.BLUE, false);
            PlotTransfer(info);
        }
    }//GEN-LAST:event_jPathFindActionPerformed

    public void UpdateGUI(GenericStation gs, Field f) {

        if (f.equals(Field.START)) {

            startStation = gs;

            startField.setText(startStation.GetName());

        } else if (f.equals(Field.DEST)) {

            destStation = gs;

            destinationField.setText(destStation.GetName());

        }

    }

    public void PopulateRouteList(ArrayList<StationRouteInfo> info)
    {   
        DefaultListModel listModel = new DefaultListModel();
        
        for (StationRouteInfo sri : info)
        {
            listModel.addElement(sri);
        }

  
        routeList.setModel(listModel);
        
        routeList.setCellRenderer(new DefaultListCellRenderer()
                {
                    @Override
                    public Component getListCellRendererComponent(JList<?> list,
                                                   Object value,
                                                   int index,
                                                   boolean isSelected,
                                                   boolean cellHasFocus)
                    {
                        Component renderer = super.getListCellRendererComponent(list, value, index, isSelected, cellHasFocus);
                        
                        if (renderer instanceof JLabel && value instanceof StationRouteInfo)
                        {
                            StationRouteInfo currentSRI = (StationRouteInfo)value;
                            GenericStation currentGS = LTAManager.GetGenericStation(currentSRI.getStationCode());
                            
                            String text = "<html><pre>" + LTAManager.round(currentGS.startCost, 1);
                            
                            switch(ptf)
                            {
                                case BESTROUTE:
                                {
                                    text += " mins";
                                    break;
                                }
                                case DISTANCE:
                                {
                                    text += " km";
                                    break;
                                }
                                case LEASTTRANSFER:
                                {
                                    text += " transfers";
                                    break;
                                }
                                case SHORTESTNOOFSTATION:
                                {
                                    text += " stations";
                                    break;
                                }
                            }
                            
                            text += "\t" + currentGS.GetName();
                            
                            text += "</pre></html>";
                            
                            ((JLabel)renderer).setText(text);
                        }
                        return renderer;
                    }
                    //@Override
                    //public Component getListCellRendererComponent(JList<?>)
                }
                );
        
    }
    
    /**
     *
     * @param args the command line arguments
     *
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

            java.util.logging.Logger.getLogger(LTAMapMenu.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);

        } catch (InstantiationException ex) {

            java.util.logging.Logger.getLogger(LTAMapMenu.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);

        } catch (IllegalAccessException ex) {

            java.util.logging.Logger.getLogger(LTAMapMenu.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);

        } catch (javax.swing.UnsupportedLookAndFeelException ex) {

            java.util.logging.Logger.getLogger(LTAMapMenu.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);

        }

        //</editor-fold>
        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {

            public void run() {

                new LTAMapMenu().setVisible(true);

            }

        });

    }

    public void addSegment(Segment segment) {

        map.addSegment(segment);

    }

    /**
     *
     * Adds a whole collection of segments to the list of segments to display
     *
     * @param segments the collection of segments to add
     *
     */
    public void addSegments(Collection<Segment> segments) {

        map.addSegments(segments);

    }

    /**
     *
     * Adds a point of interest (POI) to the list of POIs to display.
     *
     *
     * @param poi the POI to add
     *
     */
    public void addPOI(POI poi) {

        map.addPOI(poi);

    }


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTextField destinationField;
    private javax.swing.JComboBox<String> jComboBox1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JList<String> jList1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JButton jPathFind;
    private javax.swing.JButton jResetButton;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JSeparator jSeparator3;
    private javax.swing.JPanel mapPanel;
    private javax.swing.JList<String> routeList;
    private javax.swing.JTextField startField;
    // End of variables declaration//GEN-END:variables
}

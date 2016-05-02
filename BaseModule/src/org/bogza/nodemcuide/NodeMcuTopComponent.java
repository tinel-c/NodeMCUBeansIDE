/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.bogza.nodemcuide;

import java.io.IOException;
import javax.swing.text.BadLocationException;
import javax.swing.text.Document;
import javax.swing.text.Style;
import javax.swing.text.StyleConstants;
import javax.swing.text.StyleContext;
import javax.swing.text.StyledDocument;
import javax.swing.text.html.HTMLEditorKit;
import javax.swing.text.html.StyleSheet;
import nl.yourdimensions.rs232.HexBinOctUtils;
import nl.yourdimensions.rs232.api.RS232ConnectionAPI;
import nl.yourdimensions.rs232.events.ConnectedRS232Event;
import nl.yourdimensions.rs232.events.DataReceivedRS232Event;
import nl.yourdimensions.rs232.events.DisconnectedRS232Event;
import nl.yourdimensions.rs232.events.IRS232EventObserver;
import nl.yourdimensions.rs232.events.IRS232Events;
import org.netbeans.api.settings.ConvertAsProperties;
import org.openide.awt.ActionID;
import org.openide.awt.ActionReference;
import org.openide.util.Exceptions;
import org.openide.windows.TopComponent;
import org.openide.util.NbBundle.Messages;

/**
 * Top component which displays something.
 */
@ConvertAsProperties(
        dtd = "-//org.bogza.nodemcuide//NodeMcuIde//EN",
        autostore = false
)
@TopComponent.Description(
        preferredID = "NodeMcuTopComponent",
        iconBase = "org/bogza/nodemcuide/icons/logo.png",
        persistenceType = TopComponent.PERSISTENCE_ALWAYS
)
@TopComponent.Registration(mode = "leftSlidingSide", openAtStartup = true)
@ActionID(category = "Window", id = "org.bogza.nodemcuide.NodeMcuTopComponent")
@ActionReference(path = "Menu/Window/NodeMcuWindows" /*, position = 333 */)
@TopComponent.OpenActionRegistration(
        displayName = "#CTL_NodeMcuAction",
        preferredID = "NodeMcuTopComponent"
)
@Messages({
    "CTL_NodeMcuAction=NodeMcu",
    "CTL_NodeMcuTopComponent=NodeMcu Window",
    "HINT_NodeMcuTopComponent=This is a NodeMcu window"
})
public final class NodeMcuTopComponent extends TopComponent implements IRS232EventObserver {

    NodeMcuTopComponentTextProcessing outputText;
    public NodeMcuTopComponent() {
        initComponents();
        setName(Bundle.CTL_NodeMcuTopComponent());
        setToolTipText(Bundle.HINT_NodeMcuTopComponent());
        putClientProperty(TopComponent.PROP_MAXIMIZATION_DISABLED, Boolean.TRUE);
        
        // try to write topics to the text pane
        outputText = new NodeMcuTopComponentTextProcessing(jTextPaneNodeMcu);

        outputText.SendText("test");
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jButton1 = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTextPaneNodeMcu = new javax.swing.JTextPane();
        jButton2 = new javax.swing.JButton();
        jButton3 = new javax.swing.JButton();
        jButtonReset = new javax.swing.JButton();

        org.openide.awt.Mnemonics.setLocalizedText(jButton1, org.openide.util.NbBundle.getMessage(NodeMcuTopComponent.class, "NodeMcuTopComponent.jButton1.text")); // NOI18N
        jButton1.setActionCommand(org.openide.util.NbBundle.getMessage(NodeMcuTopComponent.class, "NodeMcuTopComponent.jButton1.actionCommand")); // NOI18N

        jTextPaneNodeMcu.setEditable(false);
        jScrollPane1.setViewportView(jTextPaneNodeMcu);

        org.openide.awt.Mnemonics.setLocalizedText(jButton2, org.openide.util.NbBundle.getMessage(NodeMcuTopComponent.class, "NodeMcuTopComponent.jButton2.text")); // NOI18N

        org.openide.awt.Mnemonics.setLocalizedText(jButton3, org.openide.util.NbBundle.getMessage(NodeMcuTopComponent.class, "NodeMcuTopComponent.jButton3.text")); // NOI18N
        jButton3.setActionCommand(org.openide.util.NbBundle.getMessage(NodeMcuTopComponent.class, "NodeMcuTopComponent.jButton3.actionCommand")); // NOI18N

        jButtonReset.setActionCommand(org.openide.util.NbBundle.getMessage(NodeMcuTopComponent.class, "NodeMcuTopComponent.jButtonReset.actionCommand")); // NOI18N
        jButtonReset.setLabel(org.openide.util.NbBundle.getMessage(NodeMcuTopComponent.class, "NodeMcuTopComponent.jButtonReset.label")); // NOI18N
        jButtonReset.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonResetActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jButton1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButton2)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButton3)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButtonReset)
                        .addGap(0, 102, Short.MAX_VALUE)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 246, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton1)
                    .addComponent(jButton2)
                    .addComponent(jButton3)
                    .addComponent(jButtonReset))
                .addGap(16, 16, 16))
        );

        jButtonReset.getAccessibleContext().setAccessibleName(org.openide.util.NbBundle.getMessage(NodeMcuTopComponent.class, "NodeMcuTopComponent.jButtonReset.AccessibleContext.accessibleName")); // NOI18N
    }// </editor-fold>//GEN-END:initComponents

    private void jButtonResetActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonResetActionPerformed
        // TODO add your handling code here: 
        // Send reset command to the NodeMCU
        if (RS232ConnectionAPI.getInstance().isSessionConnected()) {
            try {
                String resetCommand = "=node.restart()\r\n";
                RS232ConnectionAPI.getInstance().send(resetCommand.getBytes());
            } catch (IOException ex) {
                Exceptions.printStackTrace(ex);
                debugPrint("Code err #0001: Reset button pressed exception arrived!");
                
            }
        }
    }//GEN-LAST:event_jButtonResetActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButtonReset;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTextPane jTextPaneNodeMcu;
    // End of variables declaration//GEN-END:variables
    @Override
    public void componentOpened() {
        if (!RS232ConnectionAPI.getInstance().isObserver(this)) {
            RS232ConnectionAPI.getInstance().registerObserver(this);
        }
    }

    @Override
    public void componentClosed() {
        if (!RS232ConnectionAPI.getInstance().isSessionConnected()) {
            RS232ConnectionAPI.getInstance().unregisterObserver(this);
        }
    }

    void writeProperties(java.util.Properties p) {
        // better to version settings since initial version as advocated at
        // http://wiki.apidesign.org/wiki/PropertyFiles
        p.setProperty("version", "1.0");
        // TODO store your settings
    }

    void readProperties(java.util.Properties p) {
        String version = p.getProperty("version");
        // TODO read your settings according to their version
    }
    
    @Override
    public void eventListener(IRS232Events ev) {
        if (ev instanceof ConnectedRS232Event) {
            //TODO event connected
            outputText.SendDebugText("RS232 Connected");

        } else if (ev instanceof DisconnectedRS232Event) {
            //TODO event disconnected
            outputText.SendDebugText("RS232 Disconected");

        } else if (ev instanceof DataReceivedRS232Event) {
            DataReceivedRS232Event evt = (DataReceivedRS232Event) ev;
            String RS232data = new String(evt.getData());            

            //TODO real data received on RS232
            outputText.SendText(RS232data);


       
        }
    }
    
    void debugPrint(String debugString)
    {

    }
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package seproject;

import java.io.File;
import java.io.FileInputStream;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;

/**
 *
 * @author reticent
 */
public class UploadAssignmentGUI extends javax.swing.JFrame {
    
    //Private Member Variables
    StaffGUI gui;   //Holds StaffGUI
    Database db;    //Database db
    int sectionNo;  //Courses SectionNo
    File upload;    //File being uploaded
    File dir;   //Directory of Folder

    /**
     * Creates new form UploadAssignmentGUI
     */
    public UploadAssignmentGUI() {
        initComponents();
    }
    
    /**
     * UploadAssimentGUI(Staff GUI, Database db, and ) Constructor
     */
    public UploadAssignmentGUI(StaffGUI gui, Database db, int sectionNo) {
        this.gui = gui;
        this.db = db;
        this.sectionNo = sectionNo;
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

        jLabel1 = new javax.swing.JLabel();
        txtFileName = new javax.swing.JTextField();
        btnBrowseFile = new javax.swing.JButton();
        cbTypeOfFile = new javax.swing.JComboBox<>();
        jLabel2 = new javax.swing.JLabel();
        lblGradeWeight = new javax.swing.JLabel();
        txtGradeWeight = new javax.swing.JTextField();
        lblDueDate = new javax.swing.JLabel();
        txtDueDate = new javax.swing.JTextField();
        btnSubmit = new javax.swing.JButton();
        btnReset = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Upload Material");
        setResizable(false);
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowClosed(java.awt.event.WindowEvent evt) {
                formWindowClosed(evt);
            }
        });

        jLabel1.setText("File Name:");

        txtFileName.setEditable(false);

        btnBrowseFile.setText("Browse");
        btnBrowseFile.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnBrowseFileActionPerformed(evt);
            }
        });

        cbTypeOfFile.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Class Material", "Test", "Quiz", "Homework", "Project" }));
        cbTypeOfFile.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                cbTypeOfFileItemStateChanged(evt);
            }
        });

        jLabel2.setText("Type of File:");

        lblGradeWeight.setText("Grade Weight:");
        lblGradeWeight.setEnabled(false);

        txtGradeWeight.setEnabled(false);

        lblDueDate.setText("Due Date:");
        lblDueDate.setEnabled(false);

        txtDueDate.setEnabled(false);

        btnSubmit.setText("Submit");
        btnSubmit.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSubmitActionPerformed(evt);
            }
        });

        btnReset.setText("Reset");
        btnReset.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnResetActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel1)
                            .addComponent(jLabel2)
                            .addComponent(lblGradeWeight)
                            .addComponent(lblDueDate))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(txtFileName)
                            .addComponent(cbTypeOfFile, 0, 257, Short.MAX_VALUE)
                            .addComponent(txtGradeWeight)
                            .addComponent(txtDueDate)))
                    .addComponent(btnReset, javax.swing.GroupLayout.PREFERRED_SIZE, 75, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btnSubmit, javax.swing.GroupLayout.PREFERRED_SIZE, 98, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnBrowseFile, javax.swing.GroupLayout.PREFERRED_SIZE, 107, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtFileName, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel1)
                    .addComponent(btnBrowseFile))
                .addGap(17, 17, 17)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(cbTypeOfFile, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblGradeWeight)
                    .addComponent(txtGradeWeight, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblDueDate)
                    .addComponent(txtDueDate, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnSubmit)
                    .addComponent(btnReset))
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    /**
     * btnBrowseFile Action Performed
     * --------------------------------------
     * 
     * @param evt 
     */
    private void btnBrowseFileActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBrowseFileActionPerformed
        JFileChooser fc = new JFileChooser();
        
        try
        {
            int x = fc.showOpenDialog(null);
            
            if (x == JFileChooser.APPROVE_OPTION)
            {
                upload = fc.getSelectedFile();
                txtFileName.setText(upload.getName());
            }
            
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
       
    }//GEN-LAST:event_btnBrowseFileActionPerformed

    /**
     * cbTypeofFileItem State Changed
     * --------------------------------------------
     * Enables or disables based on whether currently selected index is "Class Material".
     * @param evt 
     */
    private void cbTypeOfFileItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_cbTypeOfFileItemStateChanged
        if ((evt.getItem().equals("Class Material")))
        {
            lblGradeWeight.setEnabled(false);
            lblDueDate.setEnabled(false);
            txtGradeWeight.setEnabled(false);
            txtDueDate.setEnabled(false);
        }
        else
        {
            lblGradeWeight.setEnabled(true);
            lblDueDate.setEnabled(true);
            txtGradeWeight.setEnabled(true);
            txtDueDate.setEnabled(true);
        }
        
    }//GEN-LAST:event_cbTypeOfFileItemStateChanged

    /**
     * btnReset Action Performed
     * -------------------------------
     * Reset every field option.
     * @param evt 
     */
    private void btnResetActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnResetActionPerformed
        txtFileName.setText("");
        txtGradeWeight.setText("");
        txtDueDate.setText("");
        cbTypeOfFile.setSelectedIndex(0);
    }//GEN-LAST:event_btnResetActionPerformed

    /**
     * btnSubmit Action Performed
     * ---------------------------------------
     * 
     * @param evt 
     */
    private void btnSubmitActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSubmitActionPerformed
        
        
        try
        {
            if (cbTypeOfFile.getSelectedIndex() == 0)
            {
                if(db.uploadFile(upload.toPath(), 
                    new File("../CourseMaterial/"+sectionNo+"/" +txtFileName.getText()).toPath(), 
                    sectionNo, "Class Material", upload.getName()))
                {
                    JOptionPane.showMessageDialog(null, "Upload was successful.","Upload Successful",
                                            JOptionPane.DEFAULT_OPTION);
                    this.dispose();
                }
                else
                {
                    throw new Exception("Error Uploading File");
                }
            }
            else
            {
                if(db.uploadFile(upload.toPath(), 
                    new File("../CourseMaterial/"+sectionNo+"/" +txtFileName.getText()).toPath(), 
                    sectionNo, (String)cbTypeOfFile.getSelectedItem(), upload.getName(), Float.parseFloat(txtGradeWeight.getText())/100, txtDueDate.getText()))
                {
                    JOptionPane.showMessageDialog(null, "Upload was successful.","Upload Successful",
                                            JOptionPane.DEFAULT_OPTION);
                    this.dispose();
                }
                else
                {
                    throw new Exception("Error Uploading File");
                }
            }
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
    }//GEN-LAST:event_btnSubmitActionPerformed

    /**
     * When window closes, it refreshed the tblClassMaterial table in the StaffGUI.
     * @param evt 
     */
    private void formWindowClosed(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowClosed
        gui.fillClassMaterialList();
    }//GEN-LAST:event_formWindowClosed
    
    
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
            java.util.logging.Logger.getLogger(UploadAssignmentGUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(UploadAssignmentGUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(UploadAssignmentGUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(UploadAssignmentGUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new UploadAssignmentGUI().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnBrowseFile;
    private javax.swing.JButton btnReset;
    private javax.swing.JButton btnSubmit;
    private javax.swing.JComboBox<String> cbTypeOfFile;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel lblDueDate;
    private javax.swing.JLabel lblGradeWeight;
    private javax.swing.JTextField txtDueDate;
    private javax.swing.JTextField txtFileName;
    private javax.swing.JTextField txtGradeWeight;
    // End of variables declaration//GEN-END:variables
}

package seproject;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;
import java.util.ArrayList;
import static javax.management.remote.JMXConnectorFactory.connect;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.SwingUtilities;
import javax.swing.table.DefaultTableModel;


public class StudentGUI extends javax.swing.JFrame {

    //Declared Member Variables
    private Student user;  //Holds User class of current User
    private Database db;    //Database db
    private int courseID;   //Holds courseID
    boolean editable = false;
    
    StudentGUI() 
    {
        initComponents();
    }
    
    StudentGUI(User user, Database db)
    {
        initComponents();
        this.db = db;
        this.user = (Student) user;
        fillCourses();
    }
    
     /**
     * fillCourses()
     * ------------------------------------
     * 
     */
    public void fillCourses()
    {
        try
        {
            //Instanced Variables
            ArrayList<ArrayList<Object>> o = db.getStudentsCourses(user.getId());   //Stores each User from DB
            DefaultTableModel model = (DefaultTableModel)tblMngClasses.getModel();
            model.setRowCount(0); //Reset Table to 0

            for (int x = 0; x < o.size(); x++)
            {
                int size = o.get(x).size();

                model.addRow(new Object[]{o.get(x).get(0),o.get(x).get(1),o.get(x).get(2),o.get(x).get(3), o.get(x).get(4), o.get(x).get(5),o.get(x).get(6)} );
            }
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
    }
    
    /**
     * Refreshes the tblClassMaterials table with updated information from database.
     */
    public void fillClassMaterialList()
    {
        try
        {
            int sectionNo = (int)tblMngClasses.getValueAt(tblMngClasses.getSelectedRow(), 0);
            ArrayList<ArrayList<Object>> o = db.getCourseMaterial(sectionNo);
            
            DefaultTableModel model = (DefaultTableModel)tblClassMaterial.getModel();
            model.setRowCount(0); //Reset Table to 0

            if (o != null)
            {
                for (int x = 0; x < o.size(); x++)
                {
                    int size = o.get(x).size();

                    model.addRow(new Object[]{o.get(x).get(0),o.get(x).get(1),o.get(x).get(2),o.get(x).get(3), o.get(x).get(4), o.get(x).get(5)} );
                }
            }
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
    }

    public void fillAssignment()
    {
        try
        {
            int sectionNo = (int)tblMngClasses.getValueAt(tblMngClasses.getSelectedRow(), 0);
            ArrayList<ArrayList<Object>> o = db.getCourseStudentAssignments(sectionNo);
            
            DefaultTableModel model = (DefaultTableModel)tblManageAssignments.getModel();
            model.setRowCount(0); //Reset Table to 0

            if (o != null)
            {
                for (int x = 0; x < o.size(); x++)
                {
                    int size = o.get(x).size();

                    model.addRow(new Object[]{o.get(x).get(0),o.get(x).get(1),o.get(x).get(2),o.get(x).get(3), o.get(x).get(4)} );
                }
            }
            
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
    }
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        tbdStudent = new javax.swing.JTabbedPane();
        pnlManageClasses = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblMngClasses = new javax.swing.JTable();
        pnlManageAssignments = new javax.swing.JPanel();
        jScrollPane3 = new javax.swing.JScrollPane();
        tblClassMaterial = new javax.swing.JTable(){
            public boolean isCellEditable(int rowIndex, int colIndex) {
                return editable;
            }};
            btnDownload = new javax.swing.JButton();
            btnRefresh = new javax.swing.JButton();
            btnTurnIn = new javax.swing.JButton();
            jPanel2 = new javax.swing.JPanel();
            jScrollPane5 = new javax.swing.JScrollPane();
            tblManageAssignments = new javax.swing.JTable(){
                public boolean isCellEditable(int rowIndex, int colIndex) {
                    return editable;
                }};
                jMenuBar1 = new javax.swing.JMenuBar();
                jMenu1 = new javax.swing.JMenu();
                jMenuItem1 = new javax.swing.JMenuItem();

                setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

                tblMngClasses.setModel(new javax.swing.table.DefaultTableModel(
                    new Object [][] {
                        {null, null, null, null, null, null, null},
                        {null, null, null, null, null, null, null},
                        {null, null, null, null, null, null, null},
                        {null, null, null, null, null, null, null}
                    },
                    new String [] {
                        "Section No.", "Course ID", "Room No.", "Begin Time", "End Time", "Class Days", "Instructor"
                    }
                ) {
                    Class[] types = new Class [] {
                        java.lang.Integer.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class
                    };
                    boolean[] canEdit = new boolean [] {
                        false, false, false, false, false, false, false
                    };

                    public Class getColumnClass(int columnIndex) {
                        return types [columnIndex];
                    }

                    public boolean isCellEditable(int rowIndex, int columnIndex) {
                        return canEdit [columnIndex];
                    }
                });
                tblMngClasses.addMouseListener(new java.awt.event.MouseAdapter() {
                    public void mouseClicked(java.awt.event.MouseEvent evt) {
                        tblMngClassesMouseClicked(evt);
                    }
                });
                jScrollPane1.setViewportView(tblMngClasses);

                javax.swing.GroupLayout pnlManageClassesLayout = new javax.swing.GroupLayout(pnlManageClasses);
                pnlManageClasses.setLayout(pnlManageClassesLayout);
                pnlManageClassesLayout.setHorizontalGroup(
                    pnlManageClassesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(pnlManageClassesLayout.createSequentialGroup()
                        .addGap(55, 55, 55)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 979, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap(124, Short.MAX_VALUE))
                );
                pnlManageClassesLayout.setVerticalGroup(
                    pnlManageClassesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(pnlManageClassesLayout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 544, Short.MAX_VALUE)
                        .addGap(54, 54, 54))
                );

                tbdStudent.addTab("Manage Classes", pnlManageClasses);

                tblClassMaterial.setModel(new javax.swing.table.DefaultTableModel(
                    new Object [][] {

                    },
                    new String [] {
                        "Document No", "Document Name", "Document Type", "SectionNo", "Due Date", "Grade Weight"
                    }
                ) {
                    Class[] types = new Class [] {
                        java.lang.Integer.class, java.lang.String.class, java.lang.String.class, java.lang.Integer.class, java.lang.Object.class, java.lang.Float.class
                    };
                    boolean[] canEdit = new boolean [] {
                        false, false, false, false, false, false
                    };

                    public Class getColumnClass(int columnIndex) {
                        return types [columnIndex];
                    }

                    public boolean isCellEditable(int rowIndex, int columnIndex) {
                        return canEdit [columnIndex];
                    }
                });
                tblClassMaterial.addMouseListener(new java.awt.event.MouseAdapter() {
                    public void mouseClicked(java.awt.event.MouseEvent evt) {
                        tblClassMaterialMouseClicked(evt);
                    }
                });
                jScrollPane3.setViewportView(tblClassMaterial);

                btnDownload.setText("Download Material");
                btnDownload.addActionListener(new java.awt.event.ActionListener() {
                    public void actionPerformed(java.awt.event.ActionEvent evt) {
                        btnDownloadActionPerformed(evt);
                    }
                });

                btnRefresh.setText("Refresh");
                btnRefresh.addActionListener(new java.awt.event.ActionListener() {
                    public void actionPerformed(java.awt.event.ActionEvent evt) {
                        btnRefreshActionPerformed(evt);
                    }
                });

                btnTurnIn.setText("Turn In");
                btnTurnIn.addActionListener(new java.awt.event.ActionListener() {
                    public void actionPerformed(java.awt.event.ActionEvent evt) {
                        btnTurnInActionPerformed(evt);
                    }
                });

                javax.swing.GroupLayout pnlManageAssignmentsLayout = new javax.swing.GroupLayout(pnlManageAssignments);
                pnlManageAssignments.setLayout(pnlManageAssignmentsLayout);
                pnlManageAssignmentsLayout.setHorizontalGroup(
                    pnlManageAssignmentsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(pnlManageAssignmentsLayout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jScrollPane3, javax.swing.GroupLayout.DEFAULT_SIZE, 950, Short.MAX_VALUE)
                        .addGap(18, 18, 18)
                        .addGroup(pnlManageAssignmentsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(btnTurnIn, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(btnDownload, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(btnRefresh, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addGap(55, 55, 55))
                );
                pnlManageAssignmentsLayout.setVerticalGroup(
                    pnlManageAssignmentsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(pnlManageAssignmentsLayout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(pnlManageAssignmentsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 586, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(pnlManageAssignmentsLayout.createSequentialGroup()
                                .addComponent(btnDownload)
                                .addGap(18, 18, 18)
                                .addComponent(btnTurnIn)
                                .addGap(18, 18, 18)
                                .addComponent(btnRefresh)))
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                );

                tbdStudent.addTab("View Class Material", pnlManageAssignments);

                tblManageAssignments.setModel(new javax.swing.table.DefaultTableModel(
                    new Object [][] {

                    },
                    new String [] {
                        "Document No", "Document Name", "Student ID", "Submitted File", "Grade"
                    }
                ) {
                    Class[] types = new Class [] {
                        java.lang.Integer.class, java.lang.String.class, java.lang.Integer.class, java.lang.String.class, java.lang.Float.class
                    };
                    boolean[] canEdit = new boolean [] {
                        false, false, false, true, true
                    };

                    public Class getColumnClass(int columnIndex) {
                        return types [columnIndex];
                    }

                    public boolean isCellEditable(int rowIndex, int columnIndex) {
                        return canEdit [columnIndex];
                    }
                });
                tblManageAssignments.addMouseListener(new java.awt.event.MouseAdapter() {
                    public void mouseClicked(java.awt.event.MouseEvent evt) {
                        tblManageAssignmentsMouseClicked(evt);
                    }
                });
                jScrollPane5.setViewportView(tblManageAssignments);

                javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
                jPanel2.setLayout(jPanel2Layout);
                jPanel2Layout.setHorizontalGroup(
                    jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jScrollPane5, javax.swing.GroupLayout.PREFERRED_SIZE, 990, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap(156, Short.MAX_VALUE))
                );
                jPanel2Layout.setVerticalGroup(
                    jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jScrollPane5, javax.swing.GroupLayout.DEFAULT_SIZE, 586, Short.MAX_VALUE)
                        .addContainerGap())
                );

                tbdStudent.addTab("Manage Assignment", jPanel2);

                jMenu1.setText("Menu");

                jMenuItem1.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_L, java.awt.event.InputEvent.CTRL_MASK));
                jMenuItem1.setText("Logout");
                jMenuItem1.addActionListener(new java.awt.event.ActionListener() {
                    public void actionPerformed(java.awt.event.ActionEvent evt) {
                        jMenuItem1ActionPerformed(evt);
                    }
                });
                jMenu1.add(jMenuItem1);

                jMenuBar1.add(jMenu1);

                setJMenuBar(jMenuBar1);

                javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
                getContentPane().setLayout(layout);
                layout.setHorizontalGroup(
                    layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(tbdStudent)
                        .addContainerGap())
                );
                layout.setVerticalGroup(
                    layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(tbdStudent)
                        .addContainerGap())
                );

                pack();
            }// </editor-fold>//GEN-END:initComponents

    private void jMenuItem1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem1ActionPerformed
        this.dispose();
        LoginMenu gui = new LoginMenu();
        gui.setVisible(true);
    }//GEN-LAST:event_jMenuItem1ActionPerformed

    private void tblManageAssignmentsMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblManageAssignmentsMouseClicked
        int column1 = tblManageAssignments.getSelectedColumn();

        if ((column1 == 4 ))
        {
            editable = true;
        }
        else
        {
            editable = false;
        }
    }//GEN-LAST:event_tblManageAssignmentsMouseClicked

    private void tblMngClassesMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblMngClassesMouseClicked
        try
        {
            fillClassMaterialList();
            fillAssignment();
            tbdStudent.setSelectedIndex(1);
        }
        catch (Exception e)
        {
            System.out.println(e);
        }
    }//GEN-LAST:event_tblMngClassesMouseClicked

    private void btnRefreshActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnRefreshActionPerformed
        fillClassMaterialList();
    }//GEN-LAST:event_btnRefreshActionPerformed

    private void btnDownloadActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDownloadActionPerformed
        if (!(tblClassMaterial.getRowCount() == 0))
        {
            try
            {
                String fileName = (String) tblClassMaterial.getValueAt(tblClassMaterial.getSelectedRow(), 1);
                File f = db.downloadMaterial(user.getId(), (String)tblClassMaterial.getValueAt(tblClassMaterial.getSelectedRow(), 1),
                (int)tblClassMaterial.getValueAt(tblClassMaterial.getSelectedRow(),0));
        
                if (!(f == null))
                {
                    String home = System.getProperty("user.home");
                    File dir = new File(home+"/Downloads/"+ fileName);
                    
                    Files.copy(f.toPath(), dir.toPath(), StandardCopyOption.REPLACE_EXISTING);
                    
                    JOptionPane.showMessageDialog(null, "Download Successful","Download Successful",
                        JOptionPane.DEFAULT_OPTION);
                }
                else
                {
                    throw new Exception();
                }
            }
            catch (Exception e)
            {
                JOptionPane.showMessageDialog(null, "Download Failed","Download Failed",
                        JOptionPane.ERROR_MESSAGE);
                e.printStackTrace();
            }
        }
    }//GEN-LAST:event_btnDownloadActionPerformed

    private void tblClassMaterialMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblClassMaterialMouseClicked

        int column1 = tblClassMaterial.getSelectedColumn();
        int column2 = tblClassMaterial.getSelectedColumn();

        if ((column1 == 5 || column2 == 4 ) && !((String)tblClassMaterial.getValueAt(tblClassMaterial.getSelectedRow(), 2)).equals("Class Material"))
        {
            editable = true;
        }
        else
        {
            editable = false;
        }
    }//GEN-LAST:event_tblClassMaterialMouseClicked

    private void btnTurnInActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnTurnInActionPerformed
        if (tblClassMaterial.getValueAt(tblClassMaterial.getSelectedRow(), 2).equals("Homework"))
        {
            SubmitGUI gui = new SubmitGUI((JFrame) SwingUtilities.getWindowAncestor(this), false, 
                    (User) user, (int)tblClassMaterial.getValueAt(tblClassMaterial.getSelectedRow(), 0),
                    (int)tblClassMaterial.getValueAt(tblClassMaterial.getSelectedRow(), 3), db, (String)
                    tblClassMaterial.getValueAt(tblClassMaterial.getSelectedRow(), 1));
            gui.setVisible(true);
        }
    }//GEN-LAST:event_btnTurnInActionPerformed

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
            java.util.logging.Logger.getLogger(StudentGUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(StudentGUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(StudentGUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(StudentGUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new StudentGUI().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnDownload;
    private javax.swing.JButton btnRefresh;
    private javax.swing.JButton btnTurnIn;
    private javax.swing.JMenu jMenu1;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JMenuItem jMenuItem1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane5;
    private javax.swing.JPanel pnlManageAssignments;
    private javax.swing.JPanel pnlManageClasses;
    private javax.swing.JTabbedPane tbdStudent;
    private javax.swing.JTable tblClassMaterial;
    private javax.swing.JTable tblManageAssignments;
    private javax.swing.JTable tblMngClasses;
    // End of variables declaration//GEN-END:variables
}

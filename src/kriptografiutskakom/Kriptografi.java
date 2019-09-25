/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package kriptografiutskakom;

/**
 *
 * @author Arinal
 */
public class Kriptografi extends javax.swing.JFrame {

    /**
     * Creates new form Kriptografi
     */
    
    public Kriptografi() {
        initComponents();
    }
    
    private String encrypt(String plain, int key) {
        String chiper = "Masukkan Key !";
            chiper = "";
            for (int i=0; i<plain.length(); i++) {
                char c = plain.charAt(i);
                
                if(c >= 'a' && c <= 'z'){
	            c = (char)(c + key);
                    
                    if(c > 'z'){
	                c = (char)(c - 'z' + 'a' - 1);
	            }
                    chiper += c;
	        }
	        else if(c >= 'A' && c <= 'Z'){
	            c = (char)(c + key);
	            
	            if(c > 'Z'){
	                c = (char)(c - 'Z' + 'A' - 1);
	            }
	            
	            chiper += c;
	        }
	        else {
	        	chiper += c;
	        }
            }System.out.println("Subtitusi : "+chiper);
            System.out.println("");
        return chiper;
        
//        return "encrypted";
    }
     
     private String decrypt(String chiper, int key) {
        String plain = "Masukkan Key !";
       
            plain = "";
            for (int i=0; i<chiper.length(); i++) {
                char c = chiper.charAt(i);
                
                if(c >= 'a' && c <= 'z'){
	            c = (char)(c - key);
	            
	            if(c < 'a'){
	                c = (char)(c + 'z' - 'a' + 1);
	            }
	            
	            plain += c;
	        }else if(c >= 'A' && c <= 'Z'){
	            c = (char)(c - key);
	            
	            if(c < 'A'){
	                c = (char)(c + 'Z' - 'A' + 1);
	            }
	            
	            plain += c;
	        }
	        else {
	        	plain += c;
	        }
            }
        return plain;
    }
     

String RFEncryptionWork(String text, int key) {
        int move = 1;
        int count = 0;
        String[][] rfp = new String[key][text.length()];

        // arrange dot fence
        for (int x = 0; x < rfp.length; x++) {
            for (int y = 0; y < rfp[x].length; y++) {
                rfp[x][y] = ".";
            }
        }

        // formatting according fence rails
        for (int i = 0; i < text.length(); i++) {
            if ((move % 2) != 0) {
                rfp[count][i] = "" + text.charAt(i);
                if (count == (key - 1)) {
                    move = 2;
                    count = (key - 2);
                } else
                    count++;
            } else if ((move % 2) == 0) {
                rfp[count][i] = "" + text.charAt(i);
                if (count == 0) {
                    move = 1;
                    count = 1;
                } else
                    count--;
            }

        }

        //replace any white space with X or random
        for (int x = 0; x < rfp.length; x++) {
            for (int y = 0; y < rfp[x].length; y++) {
                if (rfp[x][y].equals(" "))
                    rfp[x][y] = " ";
            }
        }

        // display
        System.out.println("Permutasi Zigzagnya : ");
        for (int i = 0; i < rfp.length; i++) {
            for (int u = 0; u < rfp[i].length; u++) {
                System.out.print("" + rfp[i][u] );
            }
            System.out.println();
        }
        System.out.println();

        StringBuilder cb = new StringBuilder();
        //encode string from fence
        for (int i = 0; i < rfp.length; i++) {
            for (int u = 0; u < rfp[i].length; u++) {
                if (!".".equals(rfp[i][u])) {
                    cb.append(rfp[i][u]);
                }
            }
        }

        return "" + cb;
    }

String RFDecryptionWork(String text, int key) {
        String[][] rfp = new String[key][text.length()];

        for (int x = 0; x < rfp.length; x++) {
            for (int y = 0; y < rfp[x].length; y++) {
                rfp[x][y] = ".";
            }
        }

        // arrange accroding to fence rail
        int count = 0;
        int c = 1;
        int a = 0, b = 0;
        int init = (2 * key) - 2;
        a = init - 2;
        b = 2;
        for (int i = 0; i < rfp.length; i++) {
            c = 0;
            for (int u = i; u < rfp[i].length;) {
                if (count != text.length()) {
                    if (i == 0 || i == key - 1) {
                        rfp[i][u] = "" + text.charAt(count);
                        u = u + init;
                    } else {
                        rfp[i][u] = "" + text.charAt(count);
                        if (c % 2 == 0)
                            u = u + a;
                        else if (c % 2 == 1)
                            u = u + b;
                        c++;
                    }
                    count++;
                } else
                    break;

            }
            if (i != 0 && i != key - 1) {
                a = a - 2;
                b = b + 2;
            }
        }

        //display
        //System.out.println("\n\nDecrypting..list into table");

        for (int i = 0; i < rfp.length; i++) {
            for (int u = 0; u < rfp[i].length; u++) {
                System.out.print(rfp[i][u] + " ");
            }
            System.out.println();
        }

        int move = 1;
        count = 0;
        String sb = "";
        for (int i = 0; i < text.length(); i++) {
            if ((move % 2) != 0) {
                sb = sb + rfp[count][i];
                if (count == (key - 1)) {
                    move = 2;
                    count = (key - 2);
                } else
                    count++;
            } else if ((move % 2) == 0) {
                sb = sb + rfp[count][i];
                if (count == 0) {
                    move = 1;
                    count = 1;
                } else
                    count--;
            }

        }
        
        return sb;
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
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        text1 = new javax.swing.JTextArea();
        jLabel6 = new javax.swing.JLabel();
        keyy = new javax.swing.JTextField();
        jScrollPane2 = new javax.swing.JScrollPane();
        text2 = new javax.swing.JTextArea();
        jLabel7 = new javax.swing.JLabel();
        enkripsi = new javax.swing.JButton();
        reset = new javax.swing.JButton();
        dekripsi = new javax.swing.JButton();
        jLabel8 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel13 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jLabel1.setFont(new java.awt.Font("Flower Power Personal Use", 0, 18)); // NOI18N
        jLabel1.setText("Program Super  Enkripsi");

        jLabel2.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N
        jLabel2.setText("Keamanan Komputer");

        jLabel3.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        jLabel3.setText("Sistem Informasi UNAIR 2018");

        jLabel5.setText("Plain Text ");

        text1.setColumns(20);
        text1.setRows(5);
        jScrollPane1.setViewportView(text1);

        jLabel6.setText("Key");

        text2.setColumns(20);
        text2.setRows(5);
        jScrollPane2.setViewportView(text2);

        jLabel7.setText("Chiper Text");

        enkripsi.setText("Enkripsi");
        enkripsi.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                enkripsiActionPerformed(evt);
            }
        });

        reset.setText("Reset");
        reset.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                resetActionPerformed(evt);
            }
        });

        dekripsi.setText("Dekripsi");
        dekripsi.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                dekripsiActionPerformed(evt);
            }
        });

        jLabel8.setText("Chiper Text");

        jLabel9.setText("Key");

        jLabel10.setText("Plain Text");

        jLabel11.setIcon(new javax.swing.ImageIcon("E:\\Untitled-1.png")); // NOI18N

        jLabel12.setIcon(new javax.swing.ImageIcon("E:\\Untitled-3.png")); // NOI18N

        jLabel4.setText("Arinal Izza Yudhistira (081611633027)");

        jLabel13.setText("Salsabila Devina Atmaranti (081611633029)");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel12)
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(0, 40, Short.MAX_VALUE)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addGroup(layout.createSequentialGroup()
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                            .addGroup(layout.createSequentialGroup()
                                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                    .addComponent(jLabel5, javax.swing.GroupLayout.Alignment.TRAILING)
                                                    .addComponent(jLabel6, javax.swing.GroupLayout.Alignment.TRAILING)
                                                    .addComponent(jLabel7, javax.swing.GroupLayout.Alignment.TRAILING))
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                                    .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 280, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 280, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                    .addComponent(keyy, javax.swing.GroupLayout.PREFERRED_SIZE, 280, javax.swing.GroupLayout.PREFERRED_SIZE))
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED))
                                            .addGroup(layout.createSequentialGroup()
                                                .addComponent(enkripsi)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                .addComponent(reset)
                                                .addGap(124, 124, 124)))
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(jLabel9)
                                            .addComponent(jLabel10)
                                            .addComponent(jLabel8, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(dekripsi)))
                                    .addGroup(layout.createSequentialGroup()
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                                .addComponent(jLabel3)
                                                .addGap(21, 21, 21))
                                            .addGroup(layout.createSequentialGroup()
                                                .addGap(18, 18, 18)
                                                .addComponent(jLabel2))
                                            .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 216, javax.swing.GroupLayout.PREFERRED_SIZE))
                                        .addGap(64, 64, 64)
                                        .addComponent(jLabel11))))
                            .addGroup(layout.createSequentialGroup()
                                .addGap(8, 8, 8)
                                .addComponent(jLabel4)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jLabel13)))
                        .addGap(20, 20, 20))))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(27, 27, 27))
                            .addComponent(jLabel2, javax.swing.GroupLayout.Alignment.TRAILING))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel3))
                    .addComponent(jLabel12)
                    .addComponent(jLabel11))
                .addGap(30, 30, 30)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(37, 37, 37)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel5)
                            .addComponent(jLabel8))))
                .addGap(5, 5, 5)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(keyy, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel6)
                    .addComponent(jLabel9))
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(36, 36, 36)
                                .addComponent(jLabel7))))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(46, 46, 46)
                        .addComponent(jLabel10)))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(enkripsi)
                    .addComponent(dekripsi)
                    .addComponent(reset))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 27, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4)
                    .addComponent(jLabel13))
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void enkripsiActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_enkripsiActionPerformed
        // TODO add your handling code here:
        String key = keyy.getText();
        String plain = text1.getText();
        String chiper1 = this.encrypt(plain, key.length());
        String chiper2 = this.RFEncryptionWork(chiper1, key.length());
        text2.setText(chiper2);
    }//GEN-LAST:event_enkripsiActionPerformed

    private void resetActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_resetActionPerformed
        // TODO add your handling code here:
        text1.setText("");
        text2.setText("");
        keyy.setText("");
    }//GEN-LAST:event_resetActionPerformed

    private void dekripsiActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_dekripsiActionPerformed
        // TODO add your handling code here:
        String key = keyy.getText();
        String chiper = text2.getText();
        String plain2 = this.RFDecryptionWork(chiper, key.length());
        String plain = this.decrypt(plain2, key.length());
        text1.setText(plain);
    }//GEN-LAST:event_dekripsiActionPerformed

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
            java.util.logging.Logger.getLogger(Kriptografi.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Kriptografi.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Kriptografi.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Kriptografi.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Kriptografi().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton dekripsi;
    private javax.swing.JButton enkripsi;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTextField keyy;
    private javax.swing.JButton reset;
    private javax.swing.JTextArea text1;
    private javax.swing.JTextArea text2;
    // End of variables declaration//GEN-END:variables
}
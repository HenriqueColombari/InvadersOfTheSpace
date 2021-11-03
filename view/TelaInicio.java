/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view;

import java.awt.event.KeyEvent;
import java.util.Timer;
import java.util.TimerTask;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.SwingConstants;

/**
 *
 * @author 826408
 */
public class TelaInicio extends javax.swing.JFrame {
    private Boolean music; 
    
    PlaySound trilhaSonora = new PlaySound();
    Timer timerMusic = new Timer();
    TimerTask taskMusic = new TimerTask() {
        @Override
        public void run() {
            trilhaSonora.play("Teminite  MDK - Space Invaders.wav");
        }
    };
    public TelaInicio() {
        this.setUndecorated(true);
        initComponents();
        configurarFormulario();
        music = true;
    }
    
    public TelaInicio(Boolean sound){
        this.setUndecorated(true);
        initComponents();
        configurarFormulario();
        this.music = sound;
        
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        pn = new javax.swing.JPanel();
        btnSair = new javax.swing.JButton();
        btnSingleplayer = new javax.swing.JButton();
        btnMultiplayer = new javax.swing.JButton();
        lblTitulo = new javax.swing.JLabel();
        lblImagemFundo = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowOpened(java.awt.event.WindowEvent evt) {
                formWindowOpened(evt);
            }
        });

        pn.setLayout(null);

        btnSair.setFont(new java.awt.Font("Viner Hand ITC", 0, 24)); // NOI18N
        btnSair.setText("Quit");
        btnSair.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSairActionPerformed(evt);
            }
        });
        btnSair.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                btnSairKeyPressed(evt);
            }
        });
        pn.add(btnSair);
        btnSair.setBounds(600, 420, 190, 40);

        btnSingleplayer.setFont(new java.awt.Font("Viner Hand ITC", 0, 24)); // NOI18N
        btnSingleplayer.setText("Single Player");
        btnSingleplayer.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSingleplayerActionPerformed(evt);
            }
        });
        pn.add(btnSingleplayer);
        btnSingleplayer.setBounds(600, 300, 190, 47);

        btnMultiplayer.setFont(new java.awt.Font("Viner Hand ITC", 0, 24)); // NOI18N
        btnMultiplayer.setText("Multiplayer");
        btnMultiplayer.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnMultiplayerActionPerformed(evt);
            }
        });
        pn.add(btnMultiplayer);
        btnMultiplayer.setBounds(600, 360, 190, 47);

        lblTitulo.setBackground(new java.awt.Color(0, 204, 204));
        lblTitulo.setFont(new java.awt.Font("Tempus Sans ITC", 0, 80)); // NOI18N
        lblTitulo.setForeground(new java.awt.Color(102, 255, 102));
        lblTitulo.setText("Invaders Of Space");
        pn.add(lblTitulo);
        lblTitulo.setBounds(410, 160, 600, 90);

        lblImagemFundo.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagens/space.jpg"))); // NOI18N
        pn.add(lblImagemFundo);
        lblImagemFundo.setBounds(0, 0, 1440, 900);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(pn, javax.swing.GroupLayout.DEFAULT_SIZE, 1441, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(pn, javax.swing.GroupLayout.DEFAULT_SIZE, 901, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnSairActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSairActionPerformed
        // TODO add your handling code here:
        this.dispose();
        System.exit(0);
      //  btnSair.setMnemonic('A');
        
    }//GEN-LAST:event_btnSairActionPerformed

    private void btnSingleplayerActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSingleplayerActionPerformed
        // TODO add your handling code here:
            SinglePlay fp = new SinglePlay();
            fp.setVisible(true);
            this.dispose();
    }//GEN-LAST:event_btnSingleplayerActionPerformed

    private void btnSairKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_btnSairKeyPressed
        // TODO add your handling code here:
        
    }//GEN-LAST:event_btnSairKeyPressed

    private void formWindowOpened(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowOpened
        if(this.music){
           timerMusic.schedule(taskMusic, 100, 353000); 
        }
        
    }//GEN-LAST:event_formWindowOpened

    private void btnMultiplayerActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnMultiplayerActionPerformed
        MultiPlay fp = new MultiPlay();
            fp.setVisible(true);
            this.dispose();
    }//GEN-LAST:event_btnMultiplayerActionPerformed

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
            java.util.logging.Logger.getLogger(TelaInicio.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(TelaInicio.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(TelaInicio.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(TelaInicio.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        
      
     
        
        
        
        
        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new TelaInicio().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnMultiplayer;
    private javax.swing.JButton btnSair;
    private javax.swing.JButton btnSingleplayer;
    private javax.swing.JLabel lblImagemFundo;
    private javax.swing.JLabel lblTitulo;
    private javax.swing.JPanel pn;
    // End of variables declaration//GEN-END:variables

    private void configurarFormulario(){
        
    }
}

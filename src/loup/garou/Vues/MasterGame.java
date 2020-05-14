/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package loup.garou.Vues;

import java.awt.CardLayout;
import java.awt.Color;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import javax.swing.Icon;
import javax.swing.JPanel;
import jiconfont.icons.font_awesome.FontAwesome;
import jiconfont.swing.IconFontSwing;
import loup.garou.Models.Joueur;
import loup.garou.Models.Loup_Garou;
import loup.garou.Models.Master;
import loup.garou.Models.Role;
import loup.garou.Models.Serveur;
import loup.garou.Models.Villageois;

/**
 *
 * @author FullCodex
 */
public class MasterGame extends javax.swing.JFrame {

    /**
     * Creates new form Accueil
     */
    List<JPanel> arrayJpanel;
    Integer action;
    ListJoueur listeJoueur;
    static HashMap<Joueur, Integer> listeVoteJoueur = new HashMap<Joueur, Integer>();
    static Integer tour = 1;
    static Master Master;
    static MasterGame MasterGameInstance;

    public MasterGame(Master master) {
        initComponents();
        MasterGameInstance = this;

        Master = master;
        List<Joueur> Joueurs = master.getTabJoueur();

        listeJoueur = new ListJoueur(Joueurs, tour);

        IconFontSwing.register(FontAwesome.getIconFont());
        Icon iconUser = IconFontSwing.buildIcon(FontAwesome.USER, 20, Color.BLACK);
        btListUser.setIcon(iconUser);

        arrayJpanel = new ArrayList();
        action = 0;
        String newLine = System.getProperty("line.separator");

        JpanelCustom JPanel0 = new JpanelCustom();
        JPanel0.getJlabelTitle().setText("C'est la nuit le village s'endort, les joueurs ferment leurs yeux");
        JPanel0.getJlabelImage("village_nuit", 300, 175);
        JPanel0.getJlabelDes().setText("Les joueurs ferme leur yeux et attendent vos instructions");

        arrayJpanel.add(JPanel0);
        JPanelContainer.add(JPanel0);

        JpanelCustom JPanel1 = new JpanelCustom();
        JPanel1.getJlabelTitle().setText("Que la voyante se reveille.");
        JPanel1.getJlabelImage("Voyante", 175, 175);
        JPanel1.getJlabelDes().setText("La voyante vas designer une personnes afin de scruter sa carte");
        JPanel1.getJlabelFin().setText("Que la voyante s'endort");

        arrayJpanel.add(JPanel1);
        JPanelContainer.add(JPanel1);

        JpanelCustom JPanel2 = new JpanelCustom();
        JPanel2.getJlabelTitle().setText("Que les loups se reveillent");
        JPanel2.getJlabelImage("Loup-Garou", 175, 175);
        JPanel2.getJlabelDes().setText("Les loups vont determiner un joueur à éliminer ");
        JPanel2.getJlabelFin().setText("Que les loups s'endorment");

        arrayJpanel.add(JPanel2);
        JPanelContainer.add(JPanel2);

        JpanelCustom JPanel3 = new JpanelCustom();
        JPanel3.getJlabelTitle().setText("Que la sorcière se reveille.");
        JPanel3.getJlabelComplement().setText("Vas t-elle user de la potion de guérison et/ou d'empoisonnement ?");
        JPanel3.getJlabelImage("Sorciere", 175, 175);
        JPanel3.getJlabelDes().setText("La sorcière vas pouvoir sauver et/ou condamner un joueur");
        JPanel3.getJlabelFin().setText("Que la sorcière s'endort");

        arrayJpanel.add(JPanel3);
        JPanelContainer.add(JPanel3);

        JpanelCustom JPanel4 = new JpanelCustom();
        JPanel4.getJlabelTitle().setText("Le village se reveil");
        JPanel4.getJlabelImage("village_jour", 300, 175);
        JPanel4.getJlabelDes().setText("Le village vas eliminer une personne");

        arrayJpanel.add(JPanel4);
        JPanelContainer.add(JPanel4);

//        Serveur ServeurInstance = Serveur.getInstance();
//        ServeurInstance.sendListJoueurToAllClient(Master.getTabJoueurLive(), "VoteChef");
//        jButton1.setEnabled(false);

    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        JPanelContainer = new javax.swing.JPanel();
        jButton1 = new javax.swing.JButton();
        jPanel2 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        btListUser = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Loup Garou");
        setBackground(new java.awt.Color(126, 27, 27));
        setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        setPreferredSize(new java.awt.Dimension(496, 529));
        setResizable(false);

        jPanel1.setBackground(new java.awt.Color(126, 27, 27));

        JPanelContainer.setBackground(new java.awt.Color(227, 203, 143));
        JPanelContainer.setLayout(new java.awt.CardLayout());

        jButton1.setText("Etape suivantes");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jPanel2.setBackground(new java.awt.Color(126, 27, 27));
        jPanel2.setMinimumSize(new java.awt.Dimension(0, 0));

        jLabel1.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 255, 255));
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setText("Loup Garou");
        jLabel1.setToolTipText("");
        jLabel1.setHorizontalTextPosition(javax.swing.SwingConstants.LEADING);

        btListUser.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btListUserActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap(78, Short.MAX_VALUE)
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 309, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(33, 33, 33)
                .addComponent(btListUser)
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(btListUser, javax.swing.GroupLayout.DEFAULT_SIZE, 36, Short.MAX_VALUE)
                .addContainerGap())
            .addComponent(jLabel1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jButton1, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(JPanelContainer, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(15, 15, 15)
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(JPanelContainer, javax.swing.GroupLayout.PREFERRED_SIZE, 349, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(26, 26, 26)
                .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(26, 26, 26))
        );

        JPanelContainer.getAccessibleContext().setAccessibleName("");
        JPanelContainer.getAccessibleContext().setAccessibleDescription("");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        Boolean gameFini;
        Boolean nextFrame = true;
        if (jButton1.getText().equals("Retour a l'ecrant d'accueil")) {
            Serveur ServeurInstance = Serveur.getInstance();
            System.err.println("LE SERVEUR VA ETRE FERMER !!! ");
            ServeurInstance.close();
            Master M = Master.getInstance();
            M.resetMaster();

            this.dispose();
            Accueil restartAccueil = new Accueil();
            restartAccueil.setVisible(true);
        } else {
            action++;

            switch (action) {
                case 1:
                    if (Master.roleExiste("Cupidon") == true) {
                        if (Master.amoureuxDefined() == false) {
                            action = 0;
                            nextFrame = false;
                        }
                    }
                    break;
                case 2:
                    break;
                case 4:
                    gameFini = gameFinish();
                    if (gameFini == false) {
                        Serveur ServeurInstance = Serveur.getInstance();
                        ServeurInstance.sendListJoueurToAllClient(Master.getTabJoueurLive(), "VoteVillage");
                        jButton1.setEnabled(false);
                    }
                    break;
                case 5:
                    JoueurMort JPanel5 = new JoueurMort(Master.getJoueurMortByTour(tour));

                    arrayJpanel.add(JPanel5);
                    JPanelContainer.add(JPanel5);

                    gameFini = gameFinish();
                    if (gameFini == false) {
                        tour++;
                        Master.setTour(tour);
                        listeJoueur.setTourList(tour);
                        action = 0;
                    }
                    break;
                case 1000:
                    jButton1.setText("Retour a l'ecrant d'accueil");
                    break;
            }

            if (nextFrame == true) {
                ((CardLayout) JPanelContainer.getLayout()).next(JPanelContainer);
            }
        }
    }//GEN-LAST:event_jButton1ActionPerformed

    private void btListUserActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btListUserActionPerformed
        if (listeJoueur.isVisible() == true) {
            listeJoueur.dispose();
        } else {
            listeJoueur.setVisible(true);
        }
    }//GEN-LAST:event_btListUserActionPerformed

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
            java.util.logging.Logger.getLogger(MasterGame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(MasterGame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(MasterGame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(MasterGame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new MasterGame(Master.getInstance()).setVisible(true);
            }
        });
    }

    public static MasterGame getMasterGameInstance() {
        return MasterGameInstance;
    }

    public void setEnabledEtapeSuivante() {
        jButton1.setEnabled(true);
    }

    public Boolean gameFinish() {
        String Victoire = null;
        Boolean gameFini = false;

        List<Joueur> JoueurLive = Master.getTabJoueurLive();

        for (Joueur JoueurEnVie : JoueurLive) {
            if (JoueurEnVie.getRole() instanceof Loup_Garou) {
                Victoire = "";
                break;
            } else {
                Victoire = "Les Vilageois ont gagné";
            }
        }

        if (Victoire.isEmpty()) {
            for (Joueur JoueurEnVie : JoueurLive) {
                if (JoueurEnVie.getRole() instanceof Villageois) {
                    Victoire = "";
                    break;
                } else {
                    Victoire = "Les Loups ont gagné";
                }
            }
        }

        if (Victoire.isEmpty()) {
            for (Joueur JoueurEnVie : JoueurLive) {
                if (JoueurEnVie.getAmoureux() == false) {
                    Victoire = "";
                    break;
                } else {
                    Victoire = "Les amoureux on gagner";
                }
            }
        }

        if (!Victoire.isEmpty()) {
            gameFini = true;
            JpanelCustom JPanel6 = new JpanelCustom();
            JPanel6.getJlabelTitle().setText("Partie Terminé !!");

            String imgChoose;
            if (Victoire.equals("Les Loups ont gagné")) {
                imgChoose = "Loup-Garou";
            } else if (Victoire.equals("Les amoureux on gagner")) {
                imgChoose = "Cupidon";
            } else {
                imgChoose = "Villageois";
            }

            JPanel6.getJlabelImage(imgChoose, 175, 175);
            JPanel6.getJlabelDes().setText(Victoire);
            arrayJpanel.add(JPanel6);
            JPanelContainer.add(JPanel6);
            action = 999;
        }

        return gameFini;
    }

    public static Integer getTour() {
        return tour;
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel JPanelContainer;
    private javax.swing.JButton btListUser;
    private javax.swing.JButton jButton1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    // End of variables declaration//GEN-END:variables
}

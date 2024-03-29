/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package menu;

import buttons.ConnectButton;
import server.MyServer;
import client.MyClient;
import buttons.JoinButton;
import java.awt.Color;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.IOException;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.DefaultListModel;
import javax.swing.JList;
import javax.swing.ListSelectionModel;

/**
 * Class responsible for main window, where we can create rooms or join to 
 * specified ones.
 *
 * @author Albert Podraza
 */
public final class ChatMenu extends javax.swing.JFrame {
    
    private Color color;
    private final DefaultListModel serverListModel;
    static int counter=5000;
    private int posX=0,posY=0;
    
    /**
     * Default constructor, that initializes all necessary components for 
     * main window
     * 
     * @throws UnknownHostException 
     */
    public ChatMenu() throws UnknownHostException {
        initComponents();
        
        color=new Color(153, 222, 145);
        serverListModel = new DefaultListModel();
        serverList.setModel(serverListModel);
        serverList.setSelectedIndex(0);
        serverList.setSelectionMode(ListSelectionModel.SINGLE_INTERVAL_SELECTION);
        makeDrag();
        
        checkServList(InetAddress.getLocalHost().getHostAddress());        
    }
    
    /**
     * Finds running servers(chat rooms) on specified host
     * 
     * @param host given specified host 
     */
    public void checkServList(String host){
        if(host.length()>1){
            for(int i=5000;;i++){            
                try {
                    Socket checkServer=new Socket(host,i);
                    serverListModel.addElement(host+":"+i);
                    checkServer.close();
                } catch (IOException ex) {
                    break;
                }
            }
        }
    }
  
    /**
     * Creates list of avaible servers(chat rooms)
     * 
     * @param text parameters of server(chat room) 
     */
    public void addTextToList(String text){
        serverListModel.addElement(text);       
        serverList.setLayoutOrientation(JList.HORIZONTAL_WRAP);
    }
    
    /**
     * Allows movement of window
     */
    private void makeDrag(){
        this.addMouseListener(new MouseAdapter(){
            public void mousePressed(MouseEvent e)
            {
                posX=e.getX();
                posY=e.getY();
            }
        });
            this.addMouseMotionListener(new MouseAdapter()
        {
         public void mouseDragged(MouseEvent evt)
         {			
            setLocation (evt.getXOnScreen()-posX,evt.getYOnScreen()-posY);
         }
        });
    }

    /**
     * Starts procedure of connecting to specified server(chat room)
     */
    private void joinServer(){
        String root=serverList.getSelectedValue().toString();
        try {
            System.out.println(root.substring(0,root.length()-5)+"    :   "+Integer.parseInt(root.substring(root.length()-4)));
            Socket checkAvailable=new Socket(root.substring(0,root.length()-5),Integer.parseInt(root.substring(root.length()-4)));     
            checkAvailable.close();
            new MyClient(root.substring(0,root.length()-5),Integer.parseInt(root.substring(root.length()-4))).setVisible(true);
        } catch (IOException ex) {
           new JoinButton();
        }
    }
    /**
     * Creates new server(chat room)
     */
    private void newServer(){
        try {
            ServerSocket ss=new ServerSocket(counter);
            ss.close();
            MyServer serv=new MyServer(counter );
            serverListModel.addElement(InetAddress.getLocalHost().getHostAddress()+":"+counter);
            counter++;
        } catch (Exception ex) {
            counter++;
            newServer();
        }
    }
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        serverList = new javax.swing.JList();
        jButton1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        jButton3 = new javax.swing.JButton();
        jLabel2 = new javax.swing.JLabel();
        jButton4 = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Chat");
        setUndecorated(true);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jScrollPane1.setBorder(null);
        jScrollPane1.setHorizontalScrollBarPolicy(javax.swing.ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);

        serverList.setModel(new javax.swing.AbstractListModel() {
            String[] strings = {};
            /* public addElement(String name,String host,String port){
                Strings.add(name+" : "+host+" : "+port);
            }*/
            public int getSize() { return strings.length; }
            public Object getElementAt(int i) { return strings[i]; }
        });
        serverList.setAlignmentX(0.0F);
        serverList.setAlignmentY(0.0F);
        jScrollPane1.setViewportView(serverList);

        getContentPane().add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 140, 300, 100));

        jButton1.setFont(new java.awt.Font("Impact", 0, 11)); // NOI18N
        jButton1.setText("New chatroom");
        jButton1.setBorder(null);
        jButton1.setContentAreaFilled(false);
        jButton1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jButton1MouseClicked(evt);
            }
            public void mousePressed(java.awt.event.MouseEvent evt) {
                jButton1MousePressed(evt);
            }
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                jButton1MouseReleased(evt);
            }
        });
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });
        getContentPane().add(jButton1, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 70, 136, 30));

        jButton2.setFont(new java.awt.Font("Impact", 0, 11)); // NOI18N
        jButton2.setText("Join");
        jButton2.setBorder(null);
        jButton2.setContentAreaFilled(false);
        jButton2.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                jButton2MousePressed(evt);
            }
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                jButton2MouseReleased(evt);
            }
        });
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });
        getContentPane().add(jButton2, new org.netbeans.lib.awtextra.AbsoluteConstraints(230, 70, 130, 30));

        jButton3.setFont(new java.awt.Font("Impact", 0, 11)); // NOI18N
        jButton3.setText("Connect to host");
        jButton3.setBorder(null);
        jButton3.setContentAreaFilled(false);
        jButton3.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                jButton3MousePressed(evt);
            }
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                jButton3MouseReleased(evt);
            }
        });
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });
        getContentPane().add(jButton3, new org.netbeans.lib.awtextra.AbsoluteConstraints(240, 30, 120, 20));

        jLabel2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/closeButton1.png"))); // NOI18N
        jLabel2.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jLabel2.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel2MouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                jLabel2MouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                jLabel2MouseExited(evt);
            }
        });
        getContentPane().add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(387, 0, 30, 20));

        jButton4.setFont(new java.awt.Font("Impact", 0, 11)); // NOI18N
        jButton4.setText("Manual connect");
        jButton4.setBorder(null);
        jButton4.setContentAreaFilled(false);
        jButton4.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                jButton4MousePressed(evt);
            }
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                jButton4MouseReleased(evt);
            }
        });
        jButton4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton4ActionPerformed(evt);
            }
        });
        getContentPane().add(jButton4, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 33, 130, -1));

        jLabel1.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/appMenu.png"))); // NOI18N
        getContentPane().add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, -1, 270));

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        joinServer();       
    }//GEN-LAST:event_jButton2ActionPerformed

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        new ConnectButton(this).setVisible(true);
    }//GEN-LAST:event_jButton3ActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        newServer();
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jButton1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButton1MouseClicked
 
    }//GEN-LAST:event_jButton1MouseClicked

    private void jButton1MousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButton1MousePressed
        jButton1.setForeground(color);
    }//GEN-LAST:event_jButton1MousePressed

    private void jButton1MouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButton1MouseReleased
        jButton1.setForeground(Color.black);   
    }//GEN-LAST:event_jButton1MouseReleased

    private void jButton2MousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButton2MousePressed
        jButton2.setForeground(color);
    }//GEN-LAST:event_jButton2MousePressed

    private void jButton3MousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButton3MousePressed
        jButton3.setForeground(color);
    }//GEN-LAST:event_jButton3MousePressed

    private void jButton3MouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButton3MouseReleased
        jButton3.setForeground(Color.black); 
    }//GEN-LAST:event_jButton3MouseReleased

    private void jButton2MouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButton2MouseReleased
        jButton2.setForeground(Color.black); 
    }//GEN-LAST:event_jButton2MouseReleased

    private void jLabel2MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel2MouseClicked
        System.exit(0);
    }//GEN-LAST:event_jLabel2MouseClicked

    private void jLabel2MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel2MouseEntered
        jLabel2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/closeButton2.png")));
    }//GEN-LAST:event_jLabel2MouseEntered

    private void jLabel2MouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel2MouseExited
        jLabel2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/closeButton1.png")));
    }//GEN-LAST:event_jLabel2MouseExited

    private void jButton4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton4ActionPerformed
        new JoinButton().setVisible(true);
    }//GEN-LAST:event_jButton4ActionPerformed

    private void jButton4MousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButton4MousePressed
        jButton4.setForeground(color);
    }//GEN-LAST:event_jButton4MousePressed

    private void jButton4MouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButton4MouseReleased
        jButton4.setForeground(Color.black);
    }//GEN-LAST:event_jButton4MouseReleased
    
    /**
     * main method
     * 
     * @param args external parameters 
     */
    public static void main(String args[]) {
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    new ChatMenu().setVisible(true);
                } catch (UnknownHostException ex) {
                    Logger.getLogger(ChatMenu.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButton4;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JList serverList;
    // End of variables declaration//GEN-END:variables
}

package view;

import java.awt.Color;
import java.awt.event.KeyEvent;
import java.awt.event.WindowEvent;
import java.util.ArrayList;
import primitivas.Primitivas;
import javax.swing.JFrame;
import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.ThreadLocalRandom;
import java.awt.image.BufferedImage;  
import java.io.IOException; 
import java.io.File;
import javax.swing.ImageIcon;


public class MultiPlay1 extends javax.swing.JFrame {
    private Boolean delayShoot1 = true;
    private Boolean delayMove1 = true;
    private Boolean delayShoot2 = true;
    private Boolean delayMove2 = true;
    private Boolean timerCancel = true;
    private  Primitivas png;
    private int clickCounter = 0;
    private int x1 = 0, y1 = 0;
    private int x2 = 0, y2 = 0;
    private int stage = 600;
    private int lastx1 = 0, lasty1 = 0;
    private int lastx2 = 0, lasty2 = 0;
    private int keyPressedCounter = 0;
    private int enimiesx = 0;
    private int enimiesy = 0;
    private int enimiesTurnsCounter = 0;
    private int lastEnimiesx = 0, lastEnimiesy = 0;
    private int sizexmanager = 0;
    private int sizeymanager = 0;
    private ArrayList<int[]> shootList = new ArrayList<int[]>();
    private ArrayList<int[]> barricadeList = new ArrayList<int[]>();
    private ArrayList<int[]> enimiesShootList = new ArrayList<int[]>();
    private ArrayList<ArrayList<int[]>> enimiesList = new ArrayList<ArrayList<int[]>>();
    private int score = 0;
    
    ImageIcon iconEnimies = new ImageIcon(this.getClass().getResource("/imagens/Alien3.png"));
    ImageIcon iconTank1 = new ImageIcon(this.getClass().getResource("/imagens/Tank1.png"));
    ImageIcon iconTank2 = new ImageIcon(this.getClass().getResource("/imagens/Tank2.png"));
    ImageIcon iconTrenche = new ImageIcon(this.getClass().getResource("/imagens/Trenche.png"));
    ImageIcon iconExplosion = new ImageIcon(this.getClass().getResource("/imagens/explosion.png"));
    MultiPlay1 play = this;
    
    Timer redrawTimer = new Timer();
    TimerTask redrawTask = new TimerTask() {
        @Override
        public void run() {
            if(timerCancel){
                png.limpar();
                redraws(); 
            }
            else{
                this.cancel();
            }
        }
    };
    
    Timer enimiesShoot = new Timer();
    TimerTask taskEnimiesShoot = new TimerTask() {
        @Override
        public void run() {
            if(timerCancel){
                try{
                    int randomi = 0;
                    int randomk = 0;
                    if(enimiesList.size()-1 == 0){
                        randomi = 0;
                    }
                    else{
                        randomi = ThreadLocalRandom.current().nextInt(0, enimiesList.size()-1);
                    }
                    if(enimiesList.get(randomi).size() - 1 == 0){
                        randomk = 0;
                    }
                    else{
                        randomk = ThreadLocalRandom.current().nextInt(0, enimiesList.get(randomi).size()-1);
                    }


                    int T1[] = new int[]{enimiesList.get(randomi).get(randomk)[0], enimiesList.get(randomi).get(randomk)[1],
                    enimiesList.get(randomi).get(randomk)[2], enimiesList.get(randomi).get(randomk)[3]};              
                    enimiesShootList.add(T1);
                    new PlaySound().play("LaserSound.wav");
                }
                catch(Exception e){

                } 
            }
            else{
                this.cancel();
                
            }
            
        }
    };
      
    Timer timerMoveEnimiesShoot = new Timer();
    TimerTask taskMoveEnimiesShoot = new TimerTask() {
        @Override
        public void run() {
            if(timerCancel){
                try{
                    if(!enimiesShootList.isEmpty()){
                        for(int i = 0 ; i < enimiesShootList.size(); i ++){
                            png.desenharSegmentoReta(enimiesShootList.get(i)[2]-2, enimiesShootList.get(i)[3],
                                    enimiesShootList.get(i)[2]+2, enimiesShootList.get(i)[3], Color.BLACK);
                            png.desenharSegmentoReta(enimiesShootList.get(i)[2]-2, enimiesShootList.get(i)[3]-16,
                                    enimiesShootList.get(i)[2]+2, enimiesShootList.get(i)[3]-16, Color.BLACK);
                            png.desenharSegmentoReta(enimiesShootList.get(i)[2]-2, enimiesShootList.get(i)[3],
                                    enimiesShootList.get(i)[2]-2, enimiesShootList.get(i)[3]-16, Color.BLACK);                
                            png.desenharSegmentoReta(enimiesShootList.get(i)[2]+2, enimiesShootList.get(i)[3], 
                                    enimiesShootList.get(i)[2]+2, enimiesShootList.get(i)[3]-16, Color.BLACK);                   
                            png.desenharSegmentoReta(enimiesShootList.get(i)[0]-2, enimiesShootList.get(i)[1], 
                                    enimiesShootList.get(i)[0]+2, enimiesShootList.get(i)[1], Color.GREEN);
                            png.desenharSegmentoReta(enimiesShootList.get(i)[0]-2, enimiesShootList.get(i)[1]-16,
                                    enimiesShootList.get(i)[0]+2, enimiesShootList.get(i)[1]-16, Color.GREEN);
                            png.desenharSegmentoReta(enimiesShootList.get(i)[0]-2, enimiesShootList.get(i)[1], 
                                    enimiesShootList.get(i)[0]-2, enimiesShootList.get(i)[1]-16, Color.GREEN);
                            png.desenharSegmentoReta(enimiesShootList.get(i)[0]+2, enimiesShootList.get(i)[1], 
                                    enimiesShootList.get(i)[0]+2, enimiesShootList.get(i)[1]-16, Color.GREEN);

                            
                            int T1[] = new int[]{enimiesShootList.get(i)[0], enimiesShootList.get(i)[1] + 30,
                            enimiesShootList.get(i)[0], enimiesShootList.get(i)[1]};
                            enimiesShootList.set(i, T1);                
                        }
                        int counter = 0;
                        while(counter < enimiesShootList.size()){
                            if(enimiesShootList.get(counter)[1] > sizeymanager){
                                enimiesShootList.remove(counter);
                                counter --;
                            }
                            counter ++;
                        }                        
                        counter = 0;
                        while(counter < enimiesShootList.size()){
                            if(enimiesShootList.get(counter)[0]+2 > x1 - 8&&
                               enimiesShootList.get(counter)[0]-2 < x1 + 8&&
                               enimiesShootList.get(counter)[1]>y1 - 10){
                                png.drawExplosion(iconExplosion, x1, y1);
                                TelaDerrotaM ti = new TelaDerrotaM();
                                ti.setVisible(true);
                                MultiPlay1.this.dispose();
                                timerCancel = false;
                            }
                            
                            else if(enimiesShootList.get(counter)[0]+2 > x2 - 8&&
                               enimiesShootList.get(counter)[0]-2 < x2 + 8&&
                               enimiesShootList.get(counter)[1]>y2 - 10){
                                png.drawExplosion(iconExplosion, x2, y2);
                                TelaDerrotaM ti = new TelaDerrotaM();
                                ti.setVisible(true);
                                MultiPlay1.this.dispose();
                                timerCancel = false;
                            }
                        
                            else{
                                int k = 0;
                                while(k < barricadeList.size()){
                                    int t = 60;
                                    if(enimiesShootList.get(counter)[0]+2 > barricadeList.get(k)[0] - t&&
                                        enimiesShootList.get(counter)[0]-2 < barricadeList.get(k)[0] + t&&
                                        enimiesShootList.get(counter)[1] > barricadeList.get(k)[1] - 10){
                                        
                                        enimiesShootList.remove(counter);
                                        int T1[] = new int[]{barricadeList.get(k)[0], barricadeList.get(k)[1], 
                                        barricadeList.get(k)[2]+1};
                                        barricadeList.set(k, T1);
                                        if(barricadeList.get(k)[2] == 3){
                                            barricadeList.remove(k);
                                            k--;
                                        }
                                    }  
                                    k++;
                                }
                                
                            }
                            
                            counter ++;
                        }
                        
                    }      
                }
                catch(Exception e){

                }
            }
            else{
                this.cancel();
            }
            
        }
    };
    
    Timer timerMoveShoot = new Timer();
    TimerTask taskMoveShoot = new TimerTask() {
        @Override
        public void run() {
            if(timerCancel){
                try{
                    if(!shootList.isEmpty()){
                        for(int i = 0 ; i < shootList.size(); i ++){
                            png.desenharSegmentoReta(shootList.get(i)[2]-2, shootList.get(i)[3], shootList.get(i)[2]+2, shootList.get(i)[3], Color.BLACK);
                            png.desenharSegmentoReta(shootList.get(i)[2]-2, shootList.get(i)[3]-16, shootList.get(i)[2]+2, shootList.get(i)[3]-16, Color.BLACK);
                            png.desenharSegmentoReta(shootList.get(i)[2]-2, shootList.get(i)[3], shootList.get(i)[2]-2, shootList.get(i)[3]-16, Color.BLACK);                
                            png.desenharSegmentoReta(shootList.get(i)[2]+2, shootList.get(i)[3], shootList.get(i)[2]+2, shootList.get(i)[3]-16, Color.BLACK);                   
                            png.desenharSegmentoReta(shootList.get(i)[0]-2, shootList.get(i)[1], shootList.get(i)[0]+2, shootList.get(i)[1], Color.WHITE);
                            png.desenharSegmentoReta(shootList.get(i)[0]-2, shootList.get(i)[1]-16, shootList.get(i)[0]+2, shootList.get(i)[1]-16, Color.WHITE);
                            png.desenharSegmentoReta(shootList.get(i)[0]-2, shootList.get(i)[1], shootList.get(i)[0]-2, shootList.get(i)[1]-16, Color.WHITE);
                            png.desenharSegmentoReta(shootList.get(i)[0]+2, shootList.get(i)[1], shootList.get(i)[0]+2, shootList.get(i)[1]-16, Color.WHITE);


                            int T1[] = new int[]{shootList.get(i)[0], shootList.get(i)[1] -30, shootList.get(i)[0], shootList.get(i)[1]};
                            shootList.set(i, T1);                
                        }
                        

                        int i = 0;
                        while(i < shootList.size()){
                            if(shootList.get(i)[1] < 0){
                               shootList.remove(i);
                               i--;
                            }
                            i++;
                        }
                    }
                    
                    int i = 0;
                    while(i < enimiesList.size()){
                        int k = 0;
                        while(k < enimiesList.get(i).size()){
                            int t = 0;
                            while(t < shootList.size()){
                                if(shootList.get(t)[0] < enimiesList.get(i).get(k)[0] + 17 &&
                                    shootList.get(t)[0] > enimiesList.get(i).get(k)[0] - 17 &&
                                    shootList.get(t)[1]-45 <= enimiesList.get(i).get(k)[1] &&
                                    shootList.get(t)[1] +5 >= enimiesList.get(i).get(k)[1]-24){
                                    
                                    png.drawExplosion(iconExplosion, enimiesList.get(i).get(k)[0], enimiesList.get(i).get(k)[1]);
                                    shootList.remove(t);
                                    enimiesList.get(i).remove(k);
                                    if(enimiesList.get(i).isEmpty()){
                                        enimiesList.remove(i);
                                        i--;
                                    }
                                    k--;
                                    t--;
                                    
                                }
                                t++;
                            }
                            
                            k++;
                        }
                        
                        i++;
                    }


                    int remainingEnimies = 0;
                    for(i = 0; i < enimiesList.size(); i++){
                        remainingEnimies += enimiesList.get(i).size();
                    }
                    score = 3600 + (4800 - 100*remainingEnimies);
                    
                    if(keyPressedCounter > 10){
                        lblA.setText("Score: " + Integer.toString(score));
                        lblD.setText("");
                        lblW.setText("");

                    }
                    if(remainingEnimies == 0){
                        enimiesShootList.clear();
                        TelaVitoriaM1 ti = new TelaVitoriaM1();
                        ti.setVisible(true);
                        MultiPlay1.this.dispose();
                        this.cancel();
                    }
                     
                }
                catch(Exception e){

                }  
            }
            else{
                this.cancel();
            }
            
        }
    };
     
    Timer timerShoot = new Timer();
    TimerTask taskShoot = new TimerTask() {
        @Override
        public void run() {
            if(timerCancel){
                delayShoot1 = true;
                delayShoot2 = true;
            }
            else{
                this.cancel();
            }
        }
    };
    
    Timer timerMove = new Timer();
    TimerTask taskMove = new TimerTask() {
        @Override
        public void run() {
            if(timerCancel){
                delayMove1 = true;
                delayMove2 = true;
            }
            else{
                this.cancel();
            }
        }
    };
    
    private void redraws(){
        redrawTank();
        redrawBarricades();
        redrawEnimies();
        redrawShoots();
        redrawEnimiesShoots(enimiesShootList); 
    }
    
    private void redrawShoots(){
        
        for(int i = 0; i < shootList.size(); i++){
            png.desenharSegmentoReta(shootList.get(i)[2]-2, shootList.get(i)[3], shootList.get(i)[2]+2, shootList.get(i)[3], Color.BLACK);
            png.desenharSegmentoReta(shootList.get(i)[2]-2, shootList.get(i)[3]-16, shootList.get(i)[2]+2, shootList.get(i)[3]-16, Color.BLACK);
            png.desenharSegmentoReta(shootList.get(i)[2]-2, shootList.get(i)[3], shootList.get(i)[2]-2, shootList.get(i)[3]-16, Color.BLACK);                
            png.desenharSegmentoReta(shootList.get(i)[2]+2, shootList.get(i)[3], shootList.get(i)[2]+2, shootList.get(i)[3]-16, Color.BLACK);                   
            png.desenharSegmentoReta(shootList.get(i)[0]-2, shootList.get(i)[1], shootList.get(i)[0]+2, shootList.get(i)[1], Color.WHITE);
            png.desenharSegmentoReta(shootList.get(i)[0]-2, shootList.get(i)[1]-16, shootList.get(i)[0]+2, shootList.get(i)[1]-16, Color.WHITE);
            png.desenharSegmentoReta(shootList.get(i)[0]-2, shootList.get(i)[1], shootList.get(i)[0]-2, shootList.get(i)[1]-16, Color.WHITE);
            png.desenharSegmentoReta(shootList.get(i)[0]+2, shootList.get(i)[1], shootList.get(i)[0]+2, shootList.get(i)[1]-16, Color.WHITE);
        }
        
    }
    
    private void redrawEnimiesShoots(ArrayList<int[]> c){
        try{
            for(int i = 0; i < c.size(); i++){
            
                png.desenharSegmentoReta(enimiesShootList.get(i)[2]-2, enimiesShootList.get(i)[3],
                        enimiesShootList.get(i)[2]+2, enimiesShootList.get(i)[3], Color.BLACK);
                png.desenharSegmentoReta(enimiesShootList.get(i)[2]-2, enimiesShootList.get(i)[3]-16, 
                        enimiesShootList.get(i)[2]+2, enimiesShootList.get(i)[3]-16, Color.BLACK);
                png.desenharSegmentoReta(enimiesShootList.get(i)[2]-2, enimiesShootList.get(i)[3], 
                        enimiesShootList.get(i)[2]-2, enimiesShootList.get(i)[3]-16, Color.BLACK);                
                png.desenharSegmentoReta(enimiesShootList.get(i)[2]+2, enimiesShootList.get(i)[3], 
                        enimiesShootList.get(i)[2]+2, enimiesShootList.get(i)[3]-16, Color.BLACK);                   
                png.desenharSegmentoReta(enimiesShootList.get(i)[0]-2, enimiesShootList.get(i)[1],
                        enimiesShootList.get(i)[0]+2, enimiesShootList.get(i)[1], Color.GREEN);
                png.desenharSegmentoReta(enimiesShootList.get(i)[0]-2, enimiesShootList.get(i)[1]-16, 
                        enimiesShootList.get(i)[0]+2, enimiesShootList.get(i)[1]-16, Color.GREEN);
                png.desenharSegmentoReta(enimiesShootList.get(i)[0]-2, enimiesShootList.get(i)[1], 
                        enimiesShootList.get(i)[0]-2, enimiesShootList.get(i)[1]-16, Color.GREEN);
                png.desenharSegmentoReta(enimiesShootList.get(i)[0]+2, enimiesShootList.get(i)[1], 
                        enimiesShootList.get(i)[0]+2, enimiesShootList.get(i)[1]-16, Color.GREEN);
            }
        }
        catch(Exception e){
            
        }
        
        
    }
    
    private void redrawEnimies(){
        png.DrawImageEnimies(iconEnimies, enimiesList);
    }
    
    private void redrawTank(){
        png.DrawImageTank(iconTank1, x1, y1);
        png.DrawImageTank(iconTank2, x2, y2);
    }
    
    private void redrawBarricades(){
        png.DrawImageBarricade(iconTrenche, barricadeList);
        /*
        for(int i = 0; i < barricadeList.size(); i++){
            int k = 60;
            png.desenharSegmentoReta(barricadeList.get(i)[0]-k, barricadeList.get(i)[1], 
                    barricadeList.get(i)[0]+k, barricadeList.get(i)[1], Color.RED);
            png.desenharSegmentoReta(barricadeList.get(i)[0]-k, barricadeList.get(i)[1]-16, 
                    barricadeList.get(i)[0]+k, barricadeList.get(i)[1]-16, Color.RED);
            png.desenharSegmentoReta(barricadeList.get(i)[0]-k, barricadeList.get(i)[1], 
                    barricadeList.get(i)[0]-k, barricadeList.get(i)[1]-16, Color.RED);
            png.desenharSegmentoReta(barricadeList.get(i)[0]+k, barricadeList.get(i)[1], 
                    barricadeList.get(i)[0]+k, barricadeList.get(i)[1]-16, Color.RED); 
        }*/
    }
         
    public MultiPlay1() {
        //this.setUndecorated(true);
        initComponents();
        this.setExtendedState(JFrame.MAXIMIZED_BOTH);
        pn.setPreferredSize(pn.getSize());         
    }

    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        buttonGroup1 = new javax.swing.ButtonGroup();
        buttonGroup2 = new javax.swing.ButtonGroup();
        buttonGroup3 = new javax.swing.ButtonGroup();
        pn = new javax.swing.JPanel();
        lblClick = new javax.swing.JLabel();
        lblA = new javax.swing.JLabel();
        lblD = new javax.swing.JLabel();
        lblW = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                formMouseClicked(evt);
            }
        });
        addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                formKeyPressed(evt);
            }
        });

        pn.setBackground(new java.awt.Color(0, 0, 0));
        pn.setPreferredSize(new java.awt.Dimension(1400, 1400));
        pn.setVerifyInputWhenFocusTarget(false);
        pn.addComponentListener(new java.awt.event.ComponentAdapter() {
            public void componentResized(java.awt.event.ComponentEvent evt) {
                pnComponentResized(evt);
            }
        });

        lblClick.setFont(new java.awt.Font("Tempus Sans ITC", 0, 36)); // NOI18N
        lblClick.setForeground(new java.awt.Color(240, 240, 240));
        lblClick.setText("Click to Start!");

        lblA.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        lblA.setForeground(new java.awt.Color(240, 240, 240));
        lblA.setText("a & <-: <-");

        lblD.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        lblD.setForeground(new java.awt.Color(240, 240, 240));
        lblD.setText("d & ->: ->");

        lblW.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        lblW.setForeground(new java.awt.Color(240, 240, 240));
        lblW.setText("w & Up arrow: Shoot");

        javax.swing.GroupLayout pnLayout = new javax.swing.GroupLayout(pn);
        pn.setLayout(pnLayout);
        pnLayout.setHorizontalGroup(
            pnLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnLayout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addGroup(pnLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lblA, javax.swing.GroupLayout.PREFERRED_SIZE, 829, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblD, javax.swing.GroupLayout.PREFERRED_SIZE, 783, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblW, javax.swing.GroupLayout.PREFERRED_SIZE, 693, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(37, Short.MAX_VALUE))
            .addGroup(pnLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(pnLayout.createSequentialGroup()
                    .addGap(217, 217, 217)
                    .addComponent(lblClick, javax.swing.GroupLayout.PREFERRED_SIZE, 431, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addContainerGap(238, Short.MAX_VALUE)))
        );
        pnLayout.setVerticalGroup(
            pnLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(lblA)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lblD)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lblW)
                .addContainerGap(605, Short.MAX_VALUE))
            .addGroup(pnLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(pnLayout.createSequentialGroup()
                    .addGap(235, 235, 235)
                    .addComponent(lblClick, javax.swing.GroupLayout.PREFERRED_SIZE, 202, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addContainerGap(257, Short.MAX_VALUE)))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(pn, javax.swing.GroupLayout.DEFAULT_SIZE, 886, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(pn, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 694, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents
            
    private void pnComponentResized(java.awt.event.ComponentEvent evt) {//GEN-FIRST:event_pnComponentResized
        png = new Primitivas(pn.getSize());
        pn.add(png);
        
        
    }//GEN-LAST:event_pnComponentResized

    private void formKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_formKeyPressed
        keyPressedCounter = keyPressedCounter + 1;
        lastx1 = x1;
        lasty1 = y1;
        lastx2 = x2;
        lasty2 = y2;

        if(evt.getKeyChar() == 'a'){
            if(delayMove1){
                if(x1 >= 100){
                    x1 = x1-50;
                }
                
                delayMove1 = false;
            }
            
        }

        if(evt.getKeyChar() == 'd'){
            if(delayMove1){
               if(x1 < this.getWidth()-100){
                    x1 = x1+50;
                }

                delayMove1 = false; 
            }
            
        }
        
        
        if(evt.getKeyCode() == KeyEvent.VK_LEFT){
            if(delayMove2){
               if(x2 >= 100){
                    x2 = x2-50;
                }

                delayMove2 = false; 
            }
            
        }
        
        if(evt.getKeyCode() == KeyEvent.VK_RIGHT){
            if(delayMove2){
               if(x2 < this.getWidth()-100){
                    x2 = x2+50;
                }

                delayMove2 = false; 
            }
            
        }

        if(evt.getKeyChar() == 'w'){
            if(delayShoot1){
                new PlaySound().play("Pew_Pew-DKnight556-1379997159.wav");
                int T1[] = new int[]{x1, y1, lastx1, lasty1};              
                shootList.add(T1);
                delayShoot1 = false;
            }
        }
        
        if(evt.getKeyCode() == KeyEvent.VK_UP){
            if(delayShoot2){
                new PlaySound().play("Pew_Pew-DKnight556-1379997159.wav");
                int T1[] = new int[]{x2, y2, lastx2, lasty2};              
                shootList.add(T1);
                delayShoot2 = false;
            }
        }
        
    }//GEN-LAST:event_formKeyPressed

    private void formMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_formMouseClicked
        
        if(clickCounter == 0){
            int barricadeY = this.getHeight() - 110;
            x1 = (int)(this.getWidth()/2 + 100);
            y1 = this.getHeight() - 50;
            x2 = (int)(this.getWidth()/2 - 100);
            y2 = this.getHeight() - 50;
            enimiesx = this.getWidth() - 200;
            enimiesy = 110;
            sizexmanager = this.getWidth();
            sizeymanager = this.getHeight();

            int B1[] = new int[]{this.getWidth() - 450, barricadeY, 0};
            int B2[] = new int[]{(int)(this.getWidth()/2), barricadeY, 0};
            int B3[] = new int[]{450, barricadeY, 0};
            
            //barricadeList.add(B1);
            //barricadeList.add(B2);
            //barricadeList.add(B3);
            
            ArrayList<int[]> temp = new ArrayList<int[]>();
            while(enimiesList.size() < 6){
                int T1[] = new int[]{enimiesx, enimiesy, lastEnimiesx, lastEnimiesy};
                temp.add(T1);
                enimiesx = enimiesx - 100;

                if(temp.size()==8){
                    enimiesList.add((ArrayList<int[]>) temp.clone());
                    enimiesx = this.getWidth() - 200;
                    enimiesy = enimiesy + 60;
                    temp.clear();
                }         
            }
            lblClick.setText("");
            timerMoveEnimiesShoot.schedule(taskMoveEnimiesShoot, 200, 150);
            enimiesShoot.schedule(taskEnimiesShoot, 200, 1000); 
            timerMoveShoot.schedule(taskMoveShoot, 200, 150);
            redrawTimer.schedule(redrawTask, 100, 200);
            timerShoot.schedule(taskShoot, 200, 650);
            timerMove.schedule(taskMove, 200, 300);                                 
            startTimer(); 
        }   
        
        clickCounter ++;  
        
        
    }//GEN-LAST:event_formMouseClicked
    
    public static void main(String args[]) {
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new MultiPlay1().setVisible(true);
                
            }
        });
    
    
    }
    
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.ButtonGroup buttonGroup1;
    private javax.swing.ButtonGroup buttonGroup2;
    private javax.swing.ButtonGroup buttonGroup3;
    private javax.swing.JLabel lblA;
    private javax.swing.JLabel lblClick;
    private javax.swing.JLabel lblD;
    private javax.swing.JLabel lblW;
    private javax.swing.JPanel pn;
    // End of variables declaration//GEN-END:variables
    

private void startTimer() {
    Timer timerMoveEnimies = new Timer();
    TimerTask taskMoveEnimies = new TimerTask() {
        @Override
        public void run() {
            if(timerCancel){
                try{
                    if(!enimiesList.isEmpty()){
                        int menor = enimiesList.get(0).get(0)[0];
                        int maior = -1;
                        for(int i = 0; i < enimiesList.size(); i ++){
                            if(enimiesList.get(i).isEmpty()){
                                enimiesList.remove(i);
                            }
                            for(int k = 0; k < enimiesList.get(i).size(); k++){
                                if(enimiesList.get(i).get(k)[0] > maior){
                                    maior = enimiesList.get(i).get(k)[0];
                                }
                                else if(enimiesList.get(i).get(k)[0] < menor){
                                    menor = enimiesList.get(i).get(k)[0];
                                }
                            }
                        }

                        if(menor <= 100){
                            enimiesTurnsCounter = enimiesTurnsCounter + 1;
                            for(int i = 0; i < enimiesList.size(); i++){
                                for(int m = 0; m < enimiesList.get(i).size(); m++){
                                    int T1[] = new int[]{100 + enimiesList.get(i).get(m)[0], enimiesList.get(i).get(m)[1] + 50,
                                    enimiesList.get(i).get(m)[2], enimiesList.get(i).get(m)[1]};
                                    enimiesList.get(i).set(m, T1);             
                                }
                            }
                        }

                        else if(maior >=  sizexmanager - 100){
                            enimiesTurnsCounter = enimiesTurnsCounter + 1;
                            for(int i = 0; i < enimiesList.size(); i++){
                                for(int m = 0; m < enimiesList.get(i).size(); m++){
                                    int T1[] = new int[]{enimiesList.get(i).get(m)[0] - 100, enimiesList.get(i).get(m)[1] + 50,
                                    enimiesList.get(i).get(m)[2], enimiesList.get(i).get(m)[1]};
                                    enimiesList.get(i).set(m, T1);             
                                }
                            }
                        }

                        if(enimiesTurnsCounter % 2 == 0){
                            for(int i = enimiesList.size()-1; i >= 0; i--){
                                for(int m = enimiesList.get(i).size()-1; m >= 0; m--){                                
                                    int T1[] = new int[]{enimiesList.get(i).get(m)[0] - 50, enimiesList.get(i).get(m)[1],
                                    enimiesList.get(i).get(m)[0], enimiesList.get(i).get(m)[1]};
                                    enimiesList.get(i).set(m, T1);
                                }                
                            }
                        }
                        else if(enimiesTurnsCounter % 2 != 0){
                            for(int i = 0; i < enimiesList.size(); i++){
                                for(int m = enimiesList.get(i).size()-1; m >= 0; m--){
                                    int T1[] = new int[]{enimiesList.get(i).get(m)[0] + 50, enimiesList.get(i).get(m)[1],
                                    enimiesList.get(i).get(m)[0], enimiesList.get(i).get(m)[1]};
                                    enimiesList.get(i).set(m, T1);
                                }  
                            }
                        }
                    }


                    if(enimiesList.size() == 1 && enimiesList.get(0).size() == 1){
                        timerMoveEnimies.cancel();
                        stage = 300;
                        startTimer();
                    }
                    for(int i = 0; i < enimiesList.size(); i++){
                        for(int k = 0; k < enimiesList.get(i).size(); k ++){
                            if(enimiesList.get(i).get(k)[1] > y1){


                                TelaDerrotaM ti = new TelaDerrotaM();
                                ti.setVisible(true);
                                timerCancel = false;
                                MultiPlay1.this.dispose();
                                break;
                                

                            } 
                        }

                    }
                }
                catch(Exception e){

                }
            }
            else{
                this.cancel();
            }
            
            
        }
    };
        timerMoveEnimies.schedule(taskMoveEnimies, 200, stage);
    }
}

package vistaConexion;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.Timer;

/**
 * Describe un reloj normal.
 * 
 * @author Ornelas Munguía Axel Leonardo
 * @version 11.03.2020
 */

//Se implementa el actionListener para que el contador pueda realizar la acción.
public class Reloj extends javax.swing.JPanel implements ActionListener{

    //Se necesita un contador de tiempo para el reloj.
    private final Timer tiempo;
    //Se indica las horas.
    private int horas;
    private int minutos;
    private int segundos;
            
    /**
     * Inicia el contador del reloj a cero.
     */
    public Reloj() {
        initComponents();
        horas = 0;
        minutos = 0;
        segundos = 0;
        tiempo = new Timer(1000, this);
        
    }

    // <editor-fold defaultstate="collapsed" desc="Generated Code">                          
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        hrs = new javax.swing.JLabel();
        min = new javax.swing.JLabel();
        seg = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();

        hrs.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        hrs.setText("00");

        min.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        min.setText(": 00");

        seg.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        seg.setText(": 00");

        jLabel4.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N
        jLabel4.setText("Tiempo:");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(25, 25, 25)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel4)
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(hrs, javax.swing.GroupLayout.PREFERRED_SIZE, 79, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(min, javax.swing.GroupLayout.DEFAULT_SIZE, 70, Short.MAX_VALUE)
                        .addGap(18, 18, 18)
                        .addComponent(seg, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(19, 19, 19))))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(66, 66, 66)
                .addComponent(jLabel4)
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(hrs)
                    .addComponent(min)
                    .addComponent(seg))
                .addContainerGap(131, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );
    }// </editor-fold>                        


    // Variables declaration - do not modify                     
    private javax.swing.JLabel hrs;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JLabel min;
    private javax.swing.JLabel seg;
    // End of variables declaration                   
    
    /**
     * Si el número es menor que 10 se le agrega un 0 para que se mire bien.
     */
    private String menorQueDiez(int numero){
       if(numero < 10)
           return "0" + numero;
       else
           return "" + numero;
    }
    
    /**
     * Este es el método que utiliza el timer para poder ejecutarse 
     * @param e 
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        segundos++;
        if (segundos == 60) {
            minutos++;
            segundos = 0;
        }
        if(minutos == 60){
            horas++;
            minutos = 0;
        }
        
        hrs.setText(menorQueDiez(horas) );
        min.setText(": " + menorQueDiez(minutos));
        seg.setText(": " + menorQueDiez(segundos));
    }
    /**
     * Detiene el reloj
     */
    public void detenerTiempo(){
        tiempo.stop();
    }
    /**
     * 
     */
    public void iniciarReloj(){
       tiempo.start();
    }
    /**
     * Devuelve a la información del reloj. 
     * 
     * @return Es la información 
     */
    @Override
    public String toString() {
        return menorQueDiez(horas) + ":" + menorQueDiez(minutos) + ":" + menorQueDiez(segundos);
    }
    
    
    
}

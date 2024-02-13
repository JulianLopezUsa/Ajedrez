package ajedrez;

import javax.swing.SwingUtilities;

import vista.MenuA;

public class Ajedrez {

    /**
     * @param args
     */
    public static void main(String[] args) {
       
      SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                new MenuA();
            }
        });

    }

  
    
}

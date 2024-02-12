package vista;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.RenderingHints;
import java.awt.Stroke;

public class Tablero {

    Rectangle rect = new Rectangle();

    public Tablero(Graphics g, int xy) {

        Graphics2D g2 = (Graphics2D) g;
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        g2.setColor(Color.GRAY);

        // Crea tablero
        int casillas = 8;
        int radio = xy / casillas;

        for (int j = 0; j < casillas; j++) {
            for (int i = 0; i < casillas; i++) {
                if ((j % 2) != 0) {
                    g2.fillRect(2 * radio * i, radio * j, radio, radio);
                }
                if ((i % 2 != 0)) {
                    g2.fillRect(radio * i, 2 * radio * j, radio, radio);
                }
            }
        }

        // Remarcar contorno
        Stroke pincel = new BasicStroke(2f);
        g2.setColor(Color.BLACK);
        g2.setStroke(pincel);
        g2.drawRect(0, 0, xy - 1, xy - 1);

        // Piezas
        Font font = new Font("Chess Cases", Font.TRUETYPE_FONT, 46);
        String piezas = "tmvwlvmt";

        for (int i = 0; i < casillas; i++) {

            // Inserción piezas negras
            g.setColor(Color.BLACK);
            rect.setBounds(radio * i, 0, radio, radio);
            centrarTexto(g, piezas.charAt(i) + "", rect, font);
            rect.setBounds(radio * i, radio, radio, radio);
            centrarTexto(g, "o", rect, font);

            // Inserción piezas blancas
            g.setColor(Color.WHITE);
            rect.setBounds(radio * i, radio * 7, radio, radio);
            centrarTexto(g, piezas.charAt(i) + "", rect, font);
            rect.setBounds(radio * i, radio * 6, radio, radio);
            centrarTexto(g, "o", rect, font);

        }

    }

    private void centrarTexto(Graphics g, String texto, Rectangle r, Font f) {
        FontMetrics medir = g.getFontMetrics(f);
        int x = r.x + (r.width - medir.stringWidth(texto)) / 2;
        int y = r.y + ((r.height - medir.getHeight()) / 2) + medir.getAscent();
        g.setFont(f);
        g.drawString(texto, x, y);
    }

}

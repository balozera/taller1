import edu.princeton.cs.stdlib.StdDraw;
import java.awt.Color;

public class Juego {


    public static boolean hayColision(double x1, double y1, double tam1, double x2, double y2, double tam2) {
        boolean colisionX = Math.abs(x1 - x2) < tam1 + tam2;
        boolean colisionY = Math.abs(y1 - y2) < tam1 + tam2;
        return colisionX && colisionY;
    }

    public static void main(String[] args) {

        int ancho = 800;
        int alto = 600;
        StdDraw.setCanvasSize(ancho, alto);
        StdDraw.setXscale(0, 1);
        StdDraw.setYscale(0, 1);


        double xJugador = 0.5;
        double yJugador = 0.1;
        double tamañoJugador = 0.03;



        int numBloques = 5;
        double[] xBloques = new double[numBloques];
        double[] yBloques = new double[numBloques];
        double[] dx = new double[numBloques];
        double[] dy = new double[numBloques];
        double tamBloque = 0.03;


        for (int i = 0; i < numBloques; i++) {
            xBloques[i] = Math.random();
            yBloques[i] = Math.random();
            dx[i] = (Math.random() * 0.01 + 0.005) * (Math.random() < 0.5 ? -1 : 1);
            dy[i] = (Math.random() * 0.01 + 0.005) * (Math.random() < 0.5 ? -1 : 1);
        }


        double inicio = System.currentTimeMillis();
        boolean jugando = true;

        while (jugando) {
            StdDraw.clear();


            double tiempoActual = (System.currentTimeMillis() - inicio) / 1000.0;
            double tiempoRestante = 30.0 - tiempoActual;

            StdDraw.setPenColor(Color.BLACK);
            StdDraw.text(0.9, 0.95, "Tiempo: " + (int)tiempoRestante);




            StdDraw.setPenColor(Color.BLUE);
            StdDraw.filledSquare(xJugador, yJugador, tamañoJugador);


            StdDraw.setPenColor(Color.RED);
            for (int i = 0; i < numBloques; i++) {

                xBloques[i] += dx[i];
                yBloques[i] += dy[i];


                if (xBloques[i] < tamBloque || xBloques[i] > 1 - tamBloque) {
                    dx[i] = -dx[i];
                }
                if (yBloques[i] < tamBloque || yBloques[i] > 1 - tamBloque) {
                    dy[i] = -dy[i];
                }


                StdDraw.filledSquare(xBloques[i], yBloques[i], tamBloque);


                boolean colision = hayColision(xJugador, yJugador, tamañoJugador, xBloques[i], yBloques[i], tamBloque);
                if (colision) {
                    jugando = false;
                    StdDraw.setPenColor(Color.RED);
                    StdDraw.text(0.5, 0.5, "Juego Terminado.");
                }
            }


            if (tiempoRestante <= 0) {
                jugando = false;
                StdDraw.setPenColor(Color.GREEN);
                StdDraw.text(0.5, 0.5, "Ganaste!");
            }


            StdDraw.show();
            StdDraw.pause(16);
        }
    }
}

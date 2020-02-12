// TODO - migrate to javafx
// TODO - add sticky collisions
// TODO - add controls
// TODO - add 3D


import javax.swing.*;
import java.awt.*;

public class Application {

    public static void main(String[] args) {
        new Application().run();
    }

    public class MyFrame extends JFrame {

        @Override
        public void paint(Graphics graphics) {
            super.paint(graphics);
            graphics.setColor(Color.BLACK);

            // temporary
            final int numberOfSteps = 10000;
            final int numbeoOfParticles = 2;
            int[][] xCoord = new int[numberOfSteps][numbeoOfParticles];
            int[][] yCoord = new int[numberOfSteps][numbeoOfParticles];

            final long time1 = System.nanoTime();
            new Simulation().run(xCoord, yCoord);
            final long time2 = System.nanoTime();
            System.out.println("Time elapsed: " + (time2 - time1));

            for (int i = 0; i < xCoord.length; i++) {
                for (int j = 0; j < xCoord[0].length; j++) {
                    // TODO - replace with pixels
                    graphics.drawRect(xCoord[i][j] + 250, yCoord[i][j] + 250, 1, 1);
                }
            }
        }
    }

    private void run() {
        JFrame frame = new MyFrame();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(500,500);
        frame.setLocationRelativeTo(null);
        frame.setBackground(Color.WHITE);
        frame.setTitle("Gravity simulation");
        frame.setLayout(null);
        frame.setVisible(true);
    }
}

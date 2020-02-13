
// TODO - add sticky collisions
// TODO - add controls
// TODO - add 3D

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.image.ImageView;
import javafx.scene.image.PixelWriter;
import javafx.scene.image.WritableImage;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

public class Gravity extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage stage) {
        stage.setTitle("Gravity simulation");

        // temporary
        final int numberOfSteps = 10000;
        final int numberOfParticles = 2;
        int[][] xCoord = new int[numberOfSteps][numberOfParticles];
        int[][] yCoord = new int[numberOfSteps][numberOfParticles];

        final long time1 = System.nanoTime();
        new Simulation().run(xCoord, yCoord);
        final long time2 = System.nanoTime();
        System.out.println("Time elapsed: " + (time2 - time1));

        int width = 800;
        int height = 600;
        WritableImage writableImage = new WritableImage(width, height);
        PixelWriter pixelWriter = writableImage.getPixelWriter();

        for (int i = 0; i < xCoord.length; i++) {
            for (int j = 0; j < xCoord[0].length; j++) {
                pixelWriter.setColor(xCoord[i][j] + 250, yCoord[i][j] + 250, Color.BLACK);
            }
        }

        GridPane gridPane = new GridPane();
        Scene scene = new Scene(gridPane, width, height, Color.WHITE);
        ImageView imageView = new ImageView(writableImage);
        gridPane.add(imageView, 0, 0);
        stage.setScene(scene);
        stage.show();

    }
}

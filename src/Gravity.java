// TODO - add sticky collisions
// TODO - add controls
// TODO - add 3D

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.image.ImageView;
import javafx.scene.image.PixelWriter;
import javafx.scene.image.WritableImage;
import javafx.scene.layout.BorderPane;
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
        int[][] xCoord;
        int[][] yCoord;

        Simulation simulation = new Simulation(50000.0, 10000, 2);
        final long time1 = System.nanoTime();
        simulation.run();
        final long time2 = System.nanoTime();
        System.out.println("Simulation elapsed: " + (time2 - time1) / 1000000 + " ms.");

        xCoord = simulation.getxCoord();
        yCoord = simulation.getyCoord();

        int width = 800;
        int height = 600;
        WritableImage writableImage = new WritableImage(width, height);
        PixelWriter pixelWriter = writableImage.getPixelWriter();

        // todo move to a separate method
        for (int i = 0; i < xCoord.length; i++) {
            for (int j = 0; j < xCoord[0].length; j++) {
                pixelWriter.setColor(xCoord[i][j] + 250, yCoord[i][j] + 250, Color.BLACK);
            }
        }

        BorderPane borderPane = new BorderPane();
        Scene scene = new Scene(borderPane);
        ImageView imageView = new ImageView(writableImage);
        borderPane.setCenter(imageView);
        stage.setScene(scene);
        stage.show();
    }
}

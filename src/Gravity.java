// TODO - add sticky collisions
// TODO - add controls
// TODO - add 3D

import javafx.application.Application;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.ImageView;
import javafx.scene.image.PixelWriter;
import javafx.scene.image.WritableImage;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;

public class Gravity extends Application {

    Simulation simulation;

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage stage) {
        stage.setTitle("Gravity simulation");
        stage.setOnCloseRequest(new EventHandler<WindowEvent>() {
            @Override
            public void handle(WindowEvent e) {
                Platform.exit();
            }
        });

        Button runButton = new Button("Run");
        runButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                new Runnable() {
                    @Override
                    public void run() {
                        // todo
                    }
                }.run();
            }
        });


        // temporary
        int[][][] coordinates;
        Color[] colors = new Color[7];
        colors[0] = Color.BLUE;
        colors[1] = Color.RED;
        colors[2] = Color.GREEN;
        colors[3] = Color.BLACK;
        colors[4] = Color.VIOLET;
        colors[5] = Color.GRAY;
        colors[6] = Color.ORANGE;

        simulation = new Simulation(50000.0, 14500, 2);
        final long time1 = System.nanoTime();
        simulation.run();
        final long time2 = System.nanoTime();
        System.out.println("Simulation elapsed: " + (time2 - time1) / 1000000 + " ms.");

        coordinates = simulation.getCoordinates();

        int width = 800;
        int height = 600;
        WritableImage writableImage = new WritableImage(width, height);
        PixelWriter pixelWriter = writableImage.getPixelWriter();

        System.out.println(coordinates.length);
        System.out.println(coordinates[0].length);

        // todo move to a separate method
        for (int i = 0; i < coordinates.length; i++) {
            for (int j = 0; j < coordinates[0].length; j++) {
                pixelWriter.setColor(coordinates[i][j][0] + 250, coordinates[i][j][1] + 250, colors[j]);
            }
        }

        HBox hBox = new HBox();
        hBox.setSpacing(10);
        hBox.setAlignment(Pos.CENTER_LEFT);
        hBox.getChildren().add(runButton);

        BorderPane borderPane = new BorderPane();
        Scene scene = new Scene(borderPane);
        ImageView imageView = new ImageView(writableImage);
        borderPane.setCenter(imageView);
        borderPane.setBottom(hBox);
        stage.setScene(scene);
        stage.show();
    }
}

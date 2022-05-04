package helloworld;

import javafx.animation.*;
import javafx.application.*;
import javafx.event.*;
import javafx.scene.*;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.scene.paint.*;
import javafx.scene.shape.*;
import javafx.stage.*;
import javafx.util.*;

public class HelloWorld extends Application {

    @Override
    public void start(Stage primaryStage) {
        javafx.scene.control.
        Button btn = new Button();
        btn.setText("Say 'Hello World'");

        StackPane root = new StackPane();

        Circle circle = new Circle(50, 150, 50, Color.RED);

        // change circle.translateXProperty from it's current value to 200
        KeyValue keyValue = new KeyValue(circle.translateXProperty(), 800);
        KeyValue keyValue2 = new KeyValue(circle.translateYProperty(), 800);

        // over the course of 5 seconds
        KeyFrame keyFrame = new KeyFrame(Duration.seconds(2), keyValue);
        KeyFrame keyFrame2 = new KeyFrame(Duration.seconds(2), keyValue2);
        Timeline timeline = new Timeline(keyFrame);
        Timeline timeline2 = new Timeline(keyFrame2);

        root.getChildren().add(btn);
        root.getChildren().add(new Group(circle));
        Scene scene = new Scene(root, 1042, 1042);
        primaryStage.setScene(scene);
        primaryStage.show();

        timeline.play();




        primaryStage.setTitle("Tanks");
        primaryStage.setScene(scene);
        primaryStage.show();


        btn.setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent event) {
                System.out.println("Hello World!");
                timeline2.play();
                primaryStage.setScene(scene);
                primaryStage.show();
            }
        });


    }
    public static void main(String[] args) {
        launch(args);
    }
}
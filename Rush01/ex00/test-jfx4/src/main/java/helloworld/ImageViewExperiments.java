package helloworld;

import javafx.animation.AnimationTimer;
import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.ProgressBar;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.util.Duration;
import sun.misc.URLClassPath;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.FileInputStream;
import java.util.ArrayList;
import java.util.Objects;

public class ImageViewExperiments extends Application  {
    Stage stage;
    Scene scene;
    Pane pane;
    ImageView player;
    ImageView enemy;
    ProgressBar playerHP;
    ProgressBar enemyHP;
    ArrayList<ImageView> playerBullet = new ArrayList<>();
    Timeline timeline = new Timeline();
    boolean isLeft = false;
    boolean isRight = false;
    boolean isSpace = false;
    boolean isPlaying = true;
    int move = 0;


    @Override
    public void start(Stage primaryStage) throws Exception {
        stage = primaryStage;
        primaryStage.setTitle("ImageView Experiment 1");

//        ImageView background = new ImageView(new Image("/field.png"));
//        pane = new Pane(background);
        pane = new Pane();

        player = new ImageView(new Image("/player.png"));
        player.setX(521 - 20);
        player.setY(1042 - 105 - 100);
        pane.getChildren().add(player);

        ProgressBar playerHP = new ProgressBar(1);
        playerHP.setTranslateX(20);
        playerHP.setTranslateY(1042 - 40);
        pane.getChildren().add(playerHP);

        enemyHP = new ProgressBar(1);
        enemyHP.setTranslateX(1042 - 120);
        enemyHP.setTranslateY(20);
        pane.getChildren().add(enemyHP);

        enemy = new ImageView(new Image("/enemy.png"));
        enemy.setX(521 - 20);
        enemy.setY(100);
        pane.getChildren().add(enemy);

//        pane.getChildren().add(new Pane(new Text("ASDASDASDASDASDASDASDASD")));
        scene = new Scene(pane, 1042, 1042);
        primaryStage.setScene(scene);
        primaryStage.show();


        scene.setOnKeyPressed(new EventHandler<KeyEvent>() {
              @Override
              public void handle(KeyEvent event) {
                  if (event.getCode() == KeyCode.RIGHT || event.getCode() == KeyCode.LEFT) {

                      if (event.getCode() == KeyCode.RIGHT) {
                          isLeft = false;
                          isRight = true;
                      } else {
                          isLeft = true;
                          isRight = false;
                      }

                  } else if (event.getCode() == KeyCode.SPACE && !isSpace) {
                      isSpace = true;
                      playerBullet.add(new ImageView(new Image("/playerBullet.png")));
                      ImageView bullet = playerBullet.get(playerBullet.size() - 1);
                      bullet.setX(player.getX() + player.getTranslateX() + 40);
                      bullet.setY(player.getY());
                      pane.getChildren().add(bullet);

                      KeyValue keyValue = new KeyValue(bullet.translateYProperty(), player.getTranslateY() - 700);
                      timeline = new Timeline(new KeyFrame(Duration.seconds(1), keyValue));
                      timeline.play();
                      timeline.setOnFinished(e -> deleteImageView(bullet));
                  }
              }
          }
        );


        scene.setOnKeyReleased(new EventHandler<KeyEvent>() {
                                  @Override
                                  public void handle(KeyEvent event) {
                                      if (event.getCode() == KeyCode.RIGHT || event.getCode() == KeyCode.LEFT) {
                                          if (event.getCode() == KeyCode.RIGHT) {
                                              isRight = false;
                                          } else {
                                              isLeft = false;
                                          }
                                      } else if (event.getCode() == KeyCode.SPACE) {
                                          isSpace = false;
                                      }
                                  }
                              }
        );

        AnimationTimer timer = new AnimationTimer() {
            @Override
            public void handle(long now) {

                if (isRight && player.getTranslateX() < 370) {
                    move = 25;
                } else if (isLeft && player.getTranslateX() > -400) {
                    move = -25;
                }

                if (move != 0) {
                    KeyValue keyValue;

                    if (move > 0) {
                        move--;
                    } else {
                        move++;
                    }

                    keyValue = new KeyValue(player.translateXProperty(), player.getTranslateX() + move);
                    timeline = new Timeline(new KeyFrame(Duration.seconds(0.1), keyValue));
                    timeline.play();
                }
            }
        };
        timer.start();

    }

    public static void main(String[] args) {
        Application.launch(args);
    }

    void deleteImageView(ImageView imageView) {
//        System.out.println("x1 " + imageView.getX());
//        System.out.println("x2 " + enemy.getX());
        if (imageView.getX() > enemy.getX() && imageView.getX() < enemy.getX() + 80) {
            enemyHP.setProgress(enemyHP.getProgress() - 0.05);
            if (isPlaying && enemyHP.getProgress() < 0.025) {
                enemyHP.setProgress(0);
                isPlaying = false;
//                endGameVictory();
            }
        }
        imageView.setImage(null);
        playerBullet.remove(imageView);
    }

//    private void endGameVictory() {
//        Scene scn = new Scene()
//        pane.getChildren().add(new Pane(new Text("VICTORY")));
//    }
}
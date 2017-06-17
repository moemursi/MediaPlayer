package sample;

import com.sun.media.jfxmedia.track.VideoResolution;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.paint.Color;
import javafx.stage.FileChooser;
import javafx.stage.Screen;
import javafx.stage.Stage;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import jdk.management.resource.internal.inst.FileChannelImplRMHooks;


import javax.swing.*;


import java.awt.*;
import java.io.File;
import java.net.MalformedURLException;


public class Main extends Application {

    Player player;
    FileChooser fileChooser;

    public void start(Stage primaryStage) {

        /*
            JMenuItem open = new JMenuItem("Open");
            JMenu file = new JMenu("File");
            JMenuBar menu = new JMenuBar();

            file.add(open);
            menu.add(file);
            return menu;
       */



        MenuItem ope = new MenuItem("Open");
        MenuItem exit = new MenuItem("Exit");

        Menu file = new Menu("File");

       MenuBar menuBar = new MenuBar();

       file.getItems().add(ope);
       file.getItems().add(exit);
       menuBar.getMenus().add(file);

       ope.setOnAction(new EventHandler<ActionEvent>() {
           @Override
           public void handle(ActionEvent event) {
               player.player.pause();
               File file = new FileChooser().showOpenDialog(primaryStage);
               if (file != null){
                  try {
                      player = new Player(file.toURI().toURL().toExternalForm());
                      Scene scene = new Scene(player, 1310,800, Color.BLACK);
                      primaryStage.setScene(scene);
                      primaryStage.show();
                  }catch (MalformedURLException e1){
                      e1.printStackTrace();
                  }

               }
           }
       });







        player = new Player("file:///d:/gotg.mp4");
        player.setTop(menuBar);
        Scene sc = new Scene(player, 1350,800, Color.BLACK);
        primaryStage.setScene(sc);
        primaryStage.show();


    }



    public static void main(String[] args) {
        launch(args);
    }
}

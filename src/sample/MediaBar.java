package sample;

import javafx.application.Platform;
import javafx.beans.InvalidationListener;
import javafx.beans.Observable;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Slider;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.media.MediaPlayer;

import java.lang.management.PlatformLoggingMXBean;


/**
 * Created by ja on 6/17/2017.
 */
public class MediaBar extends HBox{

    Slider time = new Slider();
    Slider vol = new Slider();
    Button playButton = new Button("||");
    Label volume = new Label("Volume: ");;
    MediaPlayer player;



    public MediaBar (MediaPlayer play){
        player = play;
        setAlignment(Pos.CENTER);;
        setPadding(new Insets(5,10,5,10));;

        vol.setPrefWidth(70);
        vol.setMinWidth(30);
        vol.setValue(100);

        HBox.setHgrow(time, Priority.ALWAYS);

        playButton.setPrefWidth(30);

        getChildren().add(playButton);
        getChildren().add(time);
        getChildren().add(volume);
        getChildren().add(vol);

        playButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                MediaPlayer.Status status = player.getStatus();

                switch (status)

                {

                    case PLAYING:
                        if(player.getCurrentTime().greaterThanOrEqualTo(player.getTotalDuration())){
                            player.seek(player.getStartTime());
                            player.play();
                            break;
                        }
                        else {
                            player.pause();
                            playButton.setText(">");
                            break;
                        }

                    case READY:
                        player.play();
                        playButton.setText("||");
                        break;


                    case HALTED :
                        player.play();
                        playButton.setText("||");
                        break;


                    case STALLED:
                        player.play();
                        playButton.setText("||");
                        break;



                    case STOPPED:
                        player.play();
                        playButton.setText("||");
                        break;


                    case UNKNOWN:
                        player.play();
                        playButton.setText("||");
                        break;

                    case DISPOSED:
                        player.play();
                        playButton.setText("||");
                        break;


                        default:

                            player.play();
                            playButton.setText("||");
                            break;

                }



              /*  if (status == MediaPlayer.Status.PLAYING){
                    if(player.getCurrentTime().greaterThanOrEqualTo(player.getTotalDuration())){
                        player.seek(player.getStartTime());
                        player.play();
                    }
                    else {
                        player.pause();
                        playButton.setText(">");
                    }

               }
                if (status == MediaPlayer.Status.PAUSED||status== MediaPlayer.Status.HALTED||status== MediaPlayer.Status.STOPPED||status== MediaPlayer.Status.DISPOSED||status== MediaPlayer.Status.READY){
                    player.play();
                    playButton.setText("||");
                }*/
            }
        });

        player.currentTimeProperty().addListener(new InvalidationListener() {
            @Override
            public void invalidated(Observable observable) {
                updatesValues();
            }
        });
        time.valueProperty().addListener(new InvalidationListener() {
            @Override
            public void invalidated(Observable observable) {
                if(time.isPressed()){
                    player.seek(player.getMedia().getDuration().multiply(time.getValue()/100));
                }
            }
        });

        vol.valueProperty().addListener(new InvalidationListener() {
            @Override
            public void invalidated(Observable observable) {
                if (vol.isPressed()){

                    player.setVolume(vol.getValue()/100);
                }
            }
        });


    }
    protected void updatesValues (){
        Platform.runLater(new Runnable() {
            @Override
            public void run() {
                time.setValue(player.getCurrentTime().toMillis()/player.getTotalDuration().toMillis()*100);
            }
        });
    }

}

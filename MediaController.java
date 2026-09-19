// MediaController Class
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.util.Duration; 
import java.io.File;

public class MediaController {
    //Instance variables
    private Media media;
    private MediaPlayer mediaPlayer;
    private boolean isPlaying;
    private double volume;
    

    //Constructors
    public MediaController(){
        this.isPlaying = false;
        this.volume = 0.8;
    }

    //Methods
    private Runnable endHandler;

    public void setOnEndOfMedia(Runnable handler) {
        this.endHandler = handler;
        if (mediaPlayer != null) {
            mediaPlayer.setOnEndOfMedia(handler);
        }
    }

    public boolean load(String filePath) {
        try {
            this.media = new Media(new File(filePath).toURI().toString());
            this.mediaPlayer = new MediaPlayer(media);
            mediaPlayer.setVolume(volume);
            if (endHandler != null) {
                mediaPlayer.setOnEndOfMedia(endHandler); // ✅ reapply handler
            }
            return true;
        } catch (Exception e) {
            System.out.println("Error loading file: " + e.getMessage());
            return false;
        }
    }
    public boolean play(){
        if (mediaPlayer != null){
            mediaPlayer.play();
            isPlaying = true;
            return true;
        } else {
            System.out.println("Error: No media loaded. Call load() first.");
            return false;
        }
    }
    public void pause(){
        if (mediaPlayer != null && isPlaying){
            mediaPlayer.pause();
            isPlaying = false;
        }
    }
    public void stop(){
        if (mediaPlayer != null){
            mediaPlayer.stop();
            isPlaying = false;
        }
    }
    public void setVolume(double level){
        if (level < 0.0){level = 0.0;}
        else if(level >1.0){level = 1.0;}
        this.volume = level;
        if (mediaPlayer != null){ mediaPlayer.setVolume(level); }
    }
    public double getCurrentTime(){
        if (mediaPlayer != null){
            Duration currentTime = mediaPlayer.getCurrentTime();
            return currentTime.toSeconds();
        } return 0.0;
    }
    public double getDuration(){
        if (mediaPlayer != null){
            Duration duration = media.getDuration();
            return duration.toSeconds();
        } return 0.0;
    }
    public void seek(double seconds) {
        if (mediaPlayer != null) {
            mediaPlayer.seek(Duration.seconds(seconds));
        }
    }
}

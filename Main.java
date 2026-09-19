//MAIN
import javafx.application.Application;
import javafx.stage.Stage;
import java.io.File;

public class Main extends Application {
    public static void main(String[] args) {
        launch(args);}
    @Override
    public void start(Stage primaryStage) {

        File musicFolder = new File("music");
        File[] musicFiles = musicFolder.listFiles();

        Playlist playlist = new Playlist("My Music");

        if (musicFiles != null) {
            for (File file : musicFiles) {
                if (file.isFile() && file.getName().toLowerCase().endsWith(".mp3")) {

                    String fileName = file.getName();
                    String title = fileName.substring(0, fileName.length() - 4);

                    Song song = new Song(
                        title,
                        "Unknown Artist",
                        file.getPath(),
                        "images/default.jpg"
                    );

                    playlist.addSong(song);
                }
            }
        }

        playlist.sortSongs();
        
        MusicPlayer player = new MusicPlayer(playlist);
        MusicPlayerGUI gui = new MusicPlayerGUI(primaryStage, player);
        gui.start();         
    }
}
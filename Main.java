//MAIN
import javafx.application.Application;
import javafx.stage.Stage;

public class Main extends Application {
    public static void main(String[] args) {
        launch(args);}
    @Override
    public void start(Stage primaryStage) {

        Song song1 = new Song("Forever", "Chris Brown", "music/Chris Brown - Forever.mp3","images/Forever.jpg");
        Song song2 = new Song("Never Be Far","Alex Warren","music/Alex Warren - Never Be Far.mp3","images/You'll be Alright Kid.png");
        Song song3 = new Song("Holy","Justin Bieber","music/Justin Bieber - Holy.mp3","images/Holy_-_Justin_Bieber.png");
        Song song4 = new Song("her","JVKE","music/JVKE - her.mp3","images/her.jpg");
        Song song5 = new Song("Feel It","d4vd","music/d4vd - Feel It.mp3","images/feel it.jpg");
        Song song6 = new Song("Summer Uptown","d4vd & jasontheween","music/JasonTheWeen & d4vd - Summer Uptown.mp3","images/summer uptown.jpg");
        Song song7 = new Song("Love Me Not", "Ravyn Lenae", "music/Ravyn Lenae - Love Me Not.mp3","images/Love Me Not.jpg");
        Song song8 = new Song("Everything","Alex Warren","music/Alex Warren - Everything.mp3","images/You'll be Alright Kid.png");
        Song song9 = new Song("Eternity","Alex Warren","music/Alex Warren - Eternity.mp3","images/You'll be Alright Kid.png");
        Song song10 = new Song("Sweater Weather","The Neighbourhood","music/The Neighbourhood - Sweater Weather.mp3","images/sweater weather.jpg");
        Song song11 = new Song("Mirrors","Justin Timberlake","music/Justin Timberlake - Mirrors.mp3","images/mirrors.jpg");
        Song song12 = new Song("This City","Sam Fisher","music/Sam Fischer - This City.mp3","images/this city.jpg");
        Song song13 = new Song("Indecision","Sampha","music/Sampha - Indecision.mp3","images/indecision.jpg");
        Song song14 = new Song("No Emotion","Tays","music/No Emotion - Tays.mp3","images/boys get sad too.jpg");
        Song song15 = new Song("Crying over you","Tays","music/Tays - Crying over you.mp3","images/boys get sad too.jpg");
        Song song16 = new Song("Promise","JDAB","music/JDAB - Promise.mp3","images/promise.jpg");
        
        Playlist playlist = new Playlist("My Music");
        Song[] songs = {song1,song2,song3,song4,song5,song6,song7,song8,song9,song10,song11,song12,song13,song14,song15,song16};
        for (Song s : songs){
            playlist.addSong(s);
        }
        playlist.sortSongs();
        
        MusicPlayer player = new MusicPlayer(playlist);
        MusicPlayerGUI gui = new MusicPlayerGUI(primaryStage, player);
        gui.start();         
    }
}
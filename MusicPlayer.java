// MusicPlayer Class
// 0:stopped   1:playing  2:paused
import java.util.ArrayList;
import java.util.List;

public class MusicPlayer {
    //Instance variables
    private Playlist playlist;
    private int state;
    private MediaController mediaController;
    private List<SongChangeListener> listeners = new ArrayList<>();
    
    //Constructors
    public MusicPlayer(){}

    public MusicPlayer(Playlist playlist){
        this.playlist = playlist;
        this.state = 0;
        this.mediaController  = new MediaController();
        this.mediaController.setOnEndOfMedia(() -> { Song newSong = this.next(); });
    }
    public MusicPlayer(MusicPlayer other){
        this.playlist = new Playlist(other.playlist);
        this.state= other.state;
        this.mediaController = new MediaController();
        this.mediaController.setOnEndOfMedia(() ->  this.next());
    }

    //Methods
    public void play(){
        if (playlist.getCurrentSong()==null){ System.out.println("No song to play."); return; }
        if (state == 2){ mediaController.play(); }
        else if (state == 0){
            boolean loaded = mediaController.load(playlist.getCurrentSong().getFilePath());
            if (loaded){mediaController.play();
            notifySongChanged(playlist.getCurrentSong());}
        }
        state = 1; 
        }
    public void pause(){
        if (state == 1){ mediaController.pause(); 
        state = 2;
    }
    }
    public void stop(){
        if (state!=0){ mediaController.stop(); 
        state = 0;
    }
    }
    public Song next(){
        Song nextSong = playlist.getNextSong();
        if (nextSong != null){
            mediaController.stop();
            mediaController.load(nextSong.getFilePath()); 
            mediaController.play();                       
            state = 1;
            notifySongChanged(nextSong);
            return nextSong;
        } else { System.out.println("No song available"); } return null;
    } 
    public Song previous(){
        Song previousSong = playlist.getPreviousSong();
        if (previousSong != null){
            mediaController.stop();
            mediaController.load(previousSong.getFilePath()); 
            mediaController.play();                       
            state = 1;
            notifySongChanged(previousSong);
            return previousSong;
        } else { System.out.println("No song available"); }return null;
    }
    public void loadPlaylist(Playlist p){ playlist = new Playlist(p);
    state = 0; }
    public int getState(){ return state; }
    public Song getCurrentSong(){
        if (playlist != null) {
        return playlist.getCurrentSong(); 
    }
    return null;
    }
    public void addSongChangeListener(SongChangeListener listener){
        listeners.add(listener);
    }
    public void removeSongChangeListener(SongChangeListener listener) {
        listeners.remove(listener);
    }
    
    private void notifySongChanged(Song newSong) {
        for (SongChangeListener listener : listeners) {
            listener.onSongChanged(newSong);
        }
    }
    public String getPlaylistName() {
        if (playlist != null) {
            return playlist.getName();
        }
        return "No Playlist";
    }
    public double getCurrentTime() {
        return mediaController.getCurrentTime();
    }
    public double getDuration() {
        return mediaController.getDuration();
    }
    public void seek(double position) {
        if (mediaController != null && playlist.getCurrentSong() != null) {
            double duration = mediaController.getDuration();
            double seekSeconds = (position / 100.0) * duration;
            mediaController.seek(seekSeconds);
        }
    }
}

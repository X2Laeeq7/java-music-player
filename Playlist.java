// Playlist Class
import java.util.Collections;
import java.util.ArrayList;
public class Playlist {
    //Instance variables
    private String name;
    private ArrayList<Song> songs;
    private int currentIndex;

    //Contructors
    public Playlist(){
        this.name = "Untitled Playlist";
        this.songs = new ArrayList<>();
        this.currentIndex = 0;
    }
    
    public Playlist(String name){
        this.name = name;
        this.songs = new ArrayList<>();
        this.currentIndex = 0;
    }

    public Playlist(Playlist other){
        this.name = other.name;
        this.songs = new ArrayList<>();
        for (Song song : other.songs){
            this.songs.add(new Song(song));
        }
        this.currentIndex = other.currentIndex;
    }

    //Methods
    public void addSong(Song s){ songs.add(s); }
    public void removeSong(Song s){ songs.remove(s); }
    public Song getCurrentSong(){
    if (songs.isEmpty()){ return null;}
    else{ return songs.get(currentIndex); }}
    public Song getNextSong(){
    if (songs.isEmpty()){ return null; }
    if (currentIndex<songs.size()-1){
        currentIndex++;
        return songs.get(currentIndex);}
    else{
        currentIndex = 0;
        return songs.get(currentIndex); }}
    public Song getPreviousSong(){
    if (songs.isEmpty()){ return null; }
    if (currentIndex>0){
        currentIndex--;
        return songs.get(currentIndex); }
    else{
        currentIndex = songs.size()-1;
        return songs.get(currentIndex); }
    }
    public String toString(){ return "Playlist: "+name+ " | Songs: "+songs.size(); }
    public int getSize(){ return songs.size(); }
    public String getName(){ return name; }
    public void sortSongs(){ Collections.sort(songs); }
}
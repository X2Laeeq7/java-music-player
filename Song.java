// Song Class
public class Song implements Comparable<Song> {
    // Instance variables
    private String title;
    private String artist;
    private String filePath;
    private String path;

    //Constructors
    public Song(){}

    public Song(String title,String artist,String filePath,String path){
        this.title = title;
        this.artist = artist;
        this.filePath = filePath;
        this.path = path;
    }

    public Song(Song other){
        this.title= other.title;
        this.artist = other.artist;
        this.filePath = other.filePath;
        this.path = other.path;
    }

    //Methods
    public String getFilePath(){ return filePath; }
    public String getTitle(){ return title; }
    public String getArtist(){ return artist; }
    public String toString(){ return title + " by " + artist; }
    public boolean equals(Object obj){ return (obj instanceof Song) && this.filePath.equals(((Song) obj).filePath); }
    public String getPath(){ return path; }
    public int compareTo(Song other){ return this.title.compareToIgnoreCase(other.title); }
}

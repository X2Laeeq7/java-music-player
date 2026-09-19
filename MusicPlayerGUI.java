//MusicPlayerGUI Class
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.application.Platform;
import javafx.geometry.Pos;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.control.Slider;
import javafx.animation.Timeline;
import javafx.animation.KeyFrame;
import javafx.util.Duration;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import java.io.File;
import javafx.scene.layout.StackPane;




public class MusicPlayerGUI implements SongChangeListener {
    //Instance variables
    private Stage stage;
    private MusicPlayer player;
    private Label songTitleLabel;
    private Label songArtistLabel;
    private Button playPauseButton;
    private Slider progressBar;
    private Timeline progressUpdater;
    private boolean userInteracting = false;
    private Label currentTimeLabel;
    private Label totalTimeLabel;
    private ImageView coverArtView;
    private ImageView spotify;

    //Constructors
    public MusicPlayerGUI(Stage stage,MusicPlayer player){
        this.stage = stage;
        this.player = player;
    }

    @Override
    public void onSongChanged(Song newSong) {
        Platform.runLater(() -> {
            songTitleLabel.setText(newSong.getTitle());
            songArtistLabel.setText(newSong.getArtist());
            playPauseButton.setText("⏸"); 
            progressBar.setValue(0);
            currentTimeLabel.setText("0:00"); 
            totalTimeLabel.setText("0:00");
            loadCoverArt(newSong);
            startProgressUpdates();
        });
    }

    //GUI
    public void start(){
        //INTERFACE
        player.addSongChangeListener(this); 

        //LOGO
        spotify = new ImageView();
        spotify.setFitWidth(45); 
        spotify.setFitHeight(45);
        spotify.setPreserveRatio(true);
        Image spotifyImage = new Image(new File("images/spotify.png").toURI().toString());
        spotify.setImage(spotifyImage);
        BorderPane topBar = new BorderPane();
        topBar.setLeft(spotify);
        BorderPane.setMargin(spotify, new Insets(0, 0, 0, 0));

        //PLAYLIST LABEL
        Label playlistLabel1 = new Label("PLAYING FROM PLAYLIST");
        playlistLabel1.setStyle("-fx-text-fill: white; -fx-font-size: 16px;");
        Label playlistLabel2 = new Label(player.getPlaylistName());
        playlistLabel2.setStyle("-fx-text-fill: white; -fx-font-size: 16px; -fx-font-weight: bold;");
        VBox playlistContainer = new VBox();
        playlistContainer.setAlignment(Pos.CENTER);
        playlistContainer.getChildren().addAll(playlistLabel1,playlistLabel2);

        //SONG LABEL
        songTitleLabel = new Label();
        songTitleLabel.setStyle("-fx-text-fill: white;-fx-font-size: 16px;-fx-font-weight: bold; -fx-alignment: center-left; -fx-padding: 0 0 0 0;");
        songArtistLabel = new Label();
        songArtistLabel.setStyle("-fx-text-fill: white;-fx-font-size: 13px; -fx-alignment: center-left; -fx-padding: 0 0 0 0;");
        VBox titleBox = new VBox(0);
        titleBox.setAlignment(Pos.CENTER_LEFT); 
        titleBox.getChildren().addAll(songTitleLabel, songArtistLabel);

        //IMAGE
        coverArtView = new ImageView();
        coverArtView.setFitHeight(360); 
        coverArtView.setFitWidth(360);
        coverArtView.setPreserveRatio(true);
        StackPane imageContainer = new StackPane();
        imageContainer.setAlignment(Pos.CENTER); 
        imageContainer.getChildren().add(coverArtView);

        //PROGRESS BAR
        progressBar = new Slider();
        progressBar.setStyle("-fx-control-inner-background: #1a4ccbff;");
        progressBar.setMinWidth(300);
        progressBar.setOnMouseReleased(e -> {
            userInteracting = false;
            double seekPosition = progressBar.getValue();
            player.seek(seekPosition);
        });
        progressBar.setOnMousePressed(e -> {
            userInteracting = true;

        });

        //TIME LABEL
        currentTimeLabel = new Label("0:00");
        currentTimeLabel.setStyle("-fx-text-fill: #f1e9e9ff; -fx-font-size: 12px;");
        totalTimeLabel = new Label("0:00"); 
        totalTimeLabel.setStyle("-fx-text-fill: #cccccc; -fx-font-size: 12px;");
        BorderPane timePane = new BorderPane();
        timePane.setLeft(currentTimeLabel);
        timePane.setRight(totalTimeLabel);
        timePane.setMinWidth(300);

        //BUTTONS
        String buttonStyle = "-fx-background-color: #000000ff; -fx-text-fill: #1a4ccbff; -fx-font-size: 20px; -fx-padding: 10px;";
        String circleButtonStyle = "-fx-background-color: #1a4ccbff; -fx-text-fill: #ffffffff; -fx-font-size: 32px; " +
                          "-fx-padding: 10px; -fx-background-radius: 50%; -fx-min-width: 65px; -fx-min-height: 65px;";
        playPauseButton = new Button("▶");
        playPauseButton.setStyle(circleButtonStyle);
        playPauseButton.setOnAction(e -> {
            if (player.getState() == 1) { 
                player.pause();
                playPauseButton.setText("▶");
            } else { 
                player.play();
                playPauseButton.setText("⏸"); 
            }
        });
        Button nextButton = new Button("⏭");
        Button prevButton = new Button("⏮");
        nextButton.setStyle(buttonStyle);
        prevButton.setStyle(buttonStyle);

        nextButton.setOnAction(e -> player.next());
        prevButton.setOnAction(e -> player.previous());

        HBox buttonBox = new HBox(10);
        buttonBox.getChildren().addAll(prevButton, playPauseButton, nextButton);
        buttonBox.setAlignment(Pos.CENTER);
        
        //MAIN SCENE
        VBox layout = new VBox(20);
        layout.getChildren().addAll(topBar,playlistContainer,imageContainer, titleBox,progressBar, timePane,buttonBox);
        layout.setStyle("-fx-background-color: #000000ff;");
        layout.setPadding(new Insets(20));
        Scene scene = new Scene(layout,400,750);
        stage.setTitle("My Media Player");
        stage.setScene(scene);
        stage.show();

        javafx.application.Platform.runLater(() -> {
            if (player != null) {
                Song current = player.getCurrentSong();
                if (current != null) {
                    songTitleLabel.setText( current.getTitle());
                    songArtistLabel.setText(current.getArtist());
                    loadCoverArt(current);
                }
                playPauseButton.setText("▶");

            }
        });
    }
    
    //Methods
    private void startProgressUpdates() {
        if (progressUpdater != null) {
            progressUpdater.stop();
        }
        
        progressUpdater = new Timeline(
            new KeyFrame(Duration.millis(100), e -> updateProgress())
        );
        progressUpdater.setCycleCount(Timeline.INDEFINITE);
        progressUpdater.play();
    }

    private void updateProgress() {
        if (userInteracting) return; 
        if (player != null && player.getState() == 1) {
            double currentTime = player.getCurrentTime(); 
            double duration = player.getDuration();      
            if (duration > 0) {
                double progress = (currentTime / duration) * 100;
                progressBar.setValue(progress);
                currentTimeLabel.setText(formatTime(currentTime));
                totalTimeLabel.setText(formatTime(duration));
            }
        }
    }
    private String formatTime(double seconds) {
        int minutes = (int) seconds / 60;
        int secs = (int) seconds % 60;
        return String.format("%d:%02d", minutes, secs);
    }
    private void loadCoverArt(Song song) {

            Image coverImage = new Image(new File(song.getPath()).toURI().toString());
            coverArtView.setImage(coverImage);
            
    }
}

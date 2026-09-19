# 🎵 Java Music Player

A desktop music player built with **Java and JavaFX** while learning object-oriented programming, GUI development, and audio playback.

## Features

* **Audio Playback** — Play, pause, stop, next, and previous track controls
* **Auto-play** — Automatically advances to the next song when one finishes
* **Playlist Management** — Create playlists and add or remove songs
* **Progress Bar** — Track playback progress and seek through songs
* **Time Display** — Shows current playback time and total duration
* **Cover Art** — Displays artwork for the current song
* **Dark Theme UI with blue icons** — JavaFX graphical interface

## Project Structure

```text
MUSIC/
├── images/                 # Local cover artwork
├── lib/                    # JavaFX libraries
├── music/                  # Local music files
├── Main.java               # Application entry point
├── Song.java               # Song data model
├── Playlist.java           # Playlist management
├── MusicPlayer.java        # Playback logic
├── MediaController.java    # JavaFX audio controller
├── MusicPlayerGUI.java     # Graphical interface
└── SongChangeListener.java # Song change listener
```

## Architecture

The application uses an object-oriented design where different classes are responsible for different parts of the player.

| Class                | Responsibility                                  |
| -------------------- | ----------------------------------------------- |
| `Song`               | Stores information about a song                 |
| `Playlist`           | Manages the collection and order of songs       |
| `MediaController`    | Handles JavaFX audio playback                   |
| `MusicPlayer`        | Controls playback state and playlist navigation |
| `MusicPlayerGUI`     | Provides the graphical user interface           |
| `SongChangeListener` | Notifies the GUI when the current song changes  |

### Design Principles

* **Separation of concerns** — different classes handle different responsibilities
* **Listener pattern** — the GUI can respond when the current song changes
* **Delegation** — `MusicPlayer` delegates audio playback to `MediaController`

## Requirements

* **Java JDK 17+**
* **JavaFX**
* **VS Code** or another Java IDE

## Music & Artwork

The repository intentionally **does not include the music files or cover artwork** used by the application.

The project uses locally stored `.mp3` files and album/cover artwork. These files are copyrighted, so they are excluded from the public GitHub repository.

To run the player with your own music:

1. Place your own `.mp3` files in the local `music/` folder.
2. Add the corresponding songs in `Main.java`.
3. Add your own artwork to the local `images/` folder if required by the application.

The `.gitignore` file prevents these local files from being uploaded to GitHub.

## Usage

1. Add your own music files to the local `music/` folder.
2. The songs get automatically added to the player with default image and unknown artist
   however u can configure the songs manually for custom album covers in `Main.java`.
4. Run `Main.java`.
5. Use the player controls to play and navigate through the playlist.

### Controls

* **▶ / ⏸** — Play or pause
* **⏭** — Next song
* **⏮** — Previous song
* **Progress bar** — Seek through the current song

## How It Works

1. `Main` creates `Song` objects and adds them to a `Playlist`.
2. `MusicPlayer` manages the playlist and playback state.
3. `MediaController` handles audio playback using JavaFX.
4. `MusicPlayerGUI` displays the player interface.
5. `SongChangeListener` allows the GUI to respond when the current song changes.
6. When a song finishes, the player automatically moves to the next track.

## Limitations

* Supports local audio files only
* Playlists are not saved between sessions
* Audio format support depends on JavaFX
* Music and artwork are not included in the repository

## Future Improvements

* Save and load playlists
* Volume control
* Shuffle and repeat modes
* Search and filter songs
* Support for additional audio formats
* Load cover artwork from song metadata

## Author

**Laeeq Geduld**

Built as a personal learning project while learning **Java, object-oriented programming, and JavaFX**.

## License

This project is for educational and personal use.

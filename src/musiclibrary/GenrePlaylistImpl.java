package musiclibrary;

import java.util.ArrayList;
import java.util.Iterator;
import javax.swing.JOptionPane;

/**
 *
 * @author Eskandar Atrakchi
 */

class GenrePlaylistImpl implements GenrePlaylistInterface {

    //two arraylist for the both genres 
    private final ArrayList<Node> hiphopPlaylist;
    private final ArrayList<Node> pianoPlaylist;
    //variable to keep track of repeat 
    private boolean repeat;

    private static GenrePlaylistImpl instance;

    // Private constructor to prevent instantiation
    private GenrePlaylistImpl() {
        this.hiphopPlaylist = new ArrayList<>();
        this.pianoPlaylist = new ArrayList<>();
        this.repeat = false;
    }

    // Method to get the singleton instance
    public static GenrePlaylistImpl getInstance() {
        if (instance == null) {
            instance = new GenrePlaylistImpl();
        }
        return instance;
    }
    
    // Add song to the Hip-Hop playlist
    @Override
    public void addHipHopSong(Song song) {
        //Node class instance 
        Node newNode = new Node(song);
        //adding the new song
        hiphopPlaylist.add(newNode);
        JOptionPane.showMessageDialog(null, "Adding song to Hip-Hop playlist was successful.");
        //if it is repeat
        if (repeat && hiphopPlaylist.size() > 1) {
            // Link the last song to the first song to make it circular
            hiphopPlaylist.get(hiphopPlaylist.size() - 2).setNext(newNode);
            newNode.setNext(hiphopPlaylist.get(0));
        }
    }

    // Add song to the Piano playlist
    @Override
    public void addPianoSong(Song song) {
        //Node class instance 
        Node newNode = new Node(song);
        //adding the new song
        pianoPlaylist.add(newNode);
        JOptionPane.showMessageDialog(null, "Adding song to Piano playlist was successful.");
        //if it is repeat
        if (repeat && pianoPlaylist.size() > 1) {
            // Link the last song to the first song to make it circular
            pianoPlaylist.get(pianoPlaylist.size() - 2).setNext(newNode);
            newNode.setNext(pianoPlaylist.get(0));
        }
    }

    // Delete song from the Hip-Hop playlist
    @Override
    public void deleteHipHopSong(Song songToDelete) {
        // Check if the list is empty
        if (hiphopPlaylist.isEmpty()) {
            JOptionPane.showMessageDialog(null, "Hip-Hop playlist is empty. No song to delete.");
            return;
        }

        // variable to check if the song is found 
        boolean songFound = false;

        // Iterate through the playlist to find and delete the song
        Iterator<Node> iterator = hiphopPlaylist.iterator();
        while (iterator.hasNext()) {
            Node currentNode = iterator.next();
            if (currentNode.getSong().equals(songToDelete)) {
                iterator.remove(); // Remove the song from the playlist
                songFound = true;
                break; // Exit the loop once the song is deleted
            }
        }

        // If the song was found and deleted, notify the user
        if (songFound) {
            JOptionPane.showMessageDialog(null, "Song deleted from Hip-Hop playlist.");
        } else {
            JOptionPane.showMessageDialog(null, "Song not found in Hip-Hop playlist.");
        }
    }
    
    // Delete song from the piano playlist
    @Override
    public void deletePianoSong(Song songToDelete) {
        // Check if the list is empty
        if (pianoPlaylist.isEmpty()) {
            JOptionPane.showMessageDialog(null, "Piano playlist is empty. No song to delete.");
            return;
        }

        // variable to check if the song is found 
        boolean songFound = false;

        // Iterate through the playlist to find and delete the song
        Iterator<Node> iterator = pianoPlaylist.iterator();
        while (iterator.hasNext()) {
            Node currentNode = iterator.next();
            if (currentNode.getSong().equals(songToDelete)) {
                iterator.remove(); // Remove the song from the playlist
                songFound = true;
                break; // Exit the loop once the song is deleted
            }
        }

        // If the song was found and deleted, notify the user
        if (songFound) {
            JOptionPane.showMessageDialog(null, "Song deleted from Piano playlist.");
        } else {
            JOptionPane.showMessageDialog(null, "Song not found in Piano playlist.");
        }
    }

    // Print the Hip-Hop playlist
    @Override
    public void printHipHopPlaylist() {
        StringBuilder playlistContent = new StringBuilder("Hip-Hop Playlist:\n");

        // Check if the playlist is empty
        if (hiphopPlaylist.isEmpty()) {
            JOptionPane.showMessageDialog(null, "The Hip-Hop playlist is empty.");
            return;
        }

        // Iterate over each Node in the playlist and append the song information to the StringBuilder
        for (Node node : hiphopPlaylist) {
            // Retrieve the Song object associated with the current Node
            Song song = node.getSong();
            // Append the title and artist of the song to the StringBuilder
            playlistContent.append(song.getTitle()).append(" - ").append(song.getArtist()).append("\n");
        }

        // Show the playlist content in a JOptionPane
        JOptionPane.showMessageDialog(null, playlistContent.toString());
    }

    
    // Print the Piano playlist
    @Override
    public void printPianoPlaylist() {
        StringBuilder playlistContent = new StringBuilder("Piano Playlist:\n");

        // Check if the playlist is empty
        if (pianoPlaylist.isEmpty()) {
            JOptionPane.showMessageDialog(null, "The Piano playlist is empty.");
            return;
        }

        // Iterate over each Node in the playlist and append the song information to the StringBuilder
        for (Node node : pianoPlaylist) {
            // Retrieve the Song object associated with the current Node
            Song song = node.getSong();
            // Append the title and artist of the song to the StringBuilder
            playlistContent.append(song.getTitle()).append(" - ").append(song.getArtist()).append("\n");
        }

        // Show the playlist content using JOptionPane
        JOptionPane.showMessageDialog(null, playlistContent.toString());
    }


    // Count total songs in HIP-HOP
    @Override
    public int countHiphopSongs() {
        
        JOptionPane.showMessageDialog(null , "The size is: " + hiphopPlaylist.size() );
        return hiphopPlaylist.size();
        
    }

    // Count total songs in Piano
    @Override
    public int countPianoSongs() {
        
        JOptionPane.showMessageDialog(null , "The size is: " + pianoPlaylist.size() );
        return pianoPlaylist.size();
    }

    // Set repeat mode for hiphop
    @Override
    public void setRepeat(boolean repeat) {
        this.repeat = repeat;
        
        if (repeat) {
            // Ensure the last song is linked to the first song for both playlists
            if (!hiphopPlaylist.isEmpty()) {
                Node lastHipHopNode = hiphopPlaylist.get(hiphopPlaylist.size() - 1);
                lastHipHopNode.setNext(hiphopPlaylist.get(0));
            }
            if (!pianoPlaylist.isEmpty()) {
                Node lastPianoNode = pianoPlaylist.get(pianoPlaylist.size() - 1);
                lastPianoNode.setNext(pianoPlaylist.get(0));
            }
        }
    }
    
    // Search for a song in the Hip-Hop playlist by title or artist
    @Override
    public void searchHipHopSong(String title, String artist) {
        if (hiphopPlaylist.isEmpty()) {
            JOptionPane.showMessageDialog(null, "Hip-Hop playlist is empty. No songs to search.");
            return;
        }

        boolean songFound = false;

        // Iterate through the playlist to find the song matching the search term
        for (Node currentNode : hiphopPlaylist) {
            Song currentSong = currentNode.getSong();
            if (currentSong.getTitle().equalsIgnoreCase(title) || currentSong.getArtist().equalsIgnoreCase(artist)) {
                JOptionPane.showMessageDialog(null, "Song found in Hip-Hop playlist: " + currentSong.getTitle() + " - " + currentSong.getArtist());
                songFound = true;
                break;
            }
        }

        if (!songFound) {
            JOptionPane.showMessageDialog(null, "Song not found in Hip-Hop playlist.");
        }
    }

    // Search for a song in the Piano playlist by title or artist
    @Override
    public void searchPianoSong(String title, String artist) {
        if (pianoPlaylist.isEmpty()) {
            JOptionPane.showMessageDialog(null, "Piano playlist is empty. No songs to search.");
            return;
        }

        boolean songFound = false;

        // Iterate through the playlist to find the song matching the search term
        for (Node currentNode : pianoPlaylist) {
            Song currentSong = currentNode.getSong();
            if (currentSong.getTitle().equalsIgnoreCase(title) || currentSong.getArtist().equalsIgnoreCase(artist)) {
                JOptionPane.showMessageDialog(null, "Song found in Piano playlist: " + currentSong.getTitle() + " - " + currentSong.getArtist());
                songFound = true;
                break;
            }
        }

        if (!songFound) {
            JOptionPane.showMessageDialog(null, "Song not found in Piano playlist.");
        }
    }
}
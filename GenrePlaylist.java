package musiclibrary;

import java.util.ArrayList;
import javax.swing.JOptionPane;

/**
 *
 * @author Eskandar Atrakchi
 */


class GenrePlaylistImpl implements GenrePlaylistInterface {

    private final ArrayList<Node> hiphopPlaylist;
    private final ArrayList<Node> pianoPlaylist;
    private boolean repeat;

    public GenrePlaylistImpl() {
        this.hiphopPlaylist = new ArrayList<>();
        this.pianoPlaylist = new ArrayList<>();
        this.repeat = false;
    }

    // Add song to the Hip-Hop playlist
    @Override
    public void addHipHopSong(Song song) {
        Node newNode = new Node(song);
        hiphopPlaylist.add(newNode);
        if (repeat && hiphopPlaylist.size() > 1) {
            // Link the last song to the first song to make it circular
            hiphopPlaylist.get(hiphopPlaylist.size() - 2).setNext(newNode);
            newNode.setNext(hiphopPlaylist.get(0));
        }
    }

    // Add song to the Piano playlist
    @Override
    public void addPianoSong(Song song) {
        Node newNode = new Node(song);
        pianoPlaylist.add(newNode);
        if (repeat && pianoPlaylist.size() > 1) {
            // Link the last song to the first song to make it circular
            pianoPlaylist.get(pianoPlaylist.size() - 2).setNext(newNode);
            newNode.setNext(pianoPlaylist.get(0));
        }
    }

    // Delete song from the Hip-Hop playlist
    @Override
    public void deleteHipHopSong(Song song) {
        for (int i = 0; i < hiphopPlaylist.size(); i++) {
            if (hiphopPlaylist.get(i).getSong() == song) {
                hiphopPlaylist.remove(i);
                break;
            }
        }
    }

    // Delete song from the Piano playlist
    @Override
    public void deletePianoSong(Song song) {
        for (int i = 0; i < pianoPlaylist.size(); i++) {
            if (pianoPlaylist.get(i).getSong() == song) {
                pianoPlaylist.remove(i);
                break;
            }
        }
    }

    // Print the Hip-Hop playlist
    @Override
    public void printHipHopPlaylist() {
        System.out.println("Hip-Hop Playlist:");
        for (Node node : hiphopPlaylist) {
            System.out.println(node.getSong().getTitle() + " - " + node.getSong().getArtist());
        }
    }

    // Print the Piano playlist
    @Override
    public void printPianoPlaylist() {
        System.out.println("Piano Playlist:");
        for (Node node : pianoPlaylist) {
            System.out.println(node.getSong().getTitle() + " - " + node.getSong().getArtist());
        }
    }

    // Count total songs in both playlists
    @Override
    public int countTotalSongs() {
        return hiphopPlaylist.size() + pianoPlaylist.size();
    }

    // Set repeat mode for both playlists
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

    // Search for a song in both playlists
    @Override
    public void searchSong(String title, String artist) {
        boolean found = false;

        for (Node node : hiphopPlaylist) {
            if (node.getSong().getTitle().equalsIgnoreCase(title) && node.getSong().getArtist().equalsIgnoreCase(artist)) {
                JOptionPane.showMessageDialog(null, "Song found in Hip-Hop playlist.");
                found = true;
                break;
            }
        }

        if (!found) {
            for (Node node : pianoPlaylist) {
                if (node.getSong().getTitle().equalsIgnoreCase(title) && node.getSong().getArtist().equalsIgnoreCase(artist)) {
                    JOptionPane.showMessageDialog(null, "Song found in Piano playlist.");
                    found = true;
                    break;
                }
            }
        }

        if (!found) {
            JOptionPane.showMessageDialog(null, "Song not found in any playlist.");
        }
    }
}
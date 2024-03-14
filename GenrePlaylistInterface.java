package musiclibrary;

/**
 *
 * @author Eskandar Atrakchi
 */
public interface GenrePlaylistInterface {
    
    void addHipHopSong(Song song);
    void addPianoSong(Song song);
    void deleteHipHopSong(Song song);
    void deletePianoSong(Song song);
    void printHipHopPlaylist();
    void printPianoPlaylist();
    int countTotalSongs();
    void setRepeat(boolean repeat);
    void searchSong(String title, String artist);
    
}

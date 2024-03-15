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
    int countHiphopSongs();
    int countPianoSongs();
    void setRepeat(boolean repeat);
    void searchHipHopSong(String title, String artist);
    void searchPianoSong(String title, String artist);
    
}

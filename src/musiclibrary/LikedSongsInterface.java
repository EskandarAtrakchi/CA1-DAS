package musiclibrary;

/**
 *
 * @author Eskandar Atrakchi
 */
public interface LikedSongsInterface {
    
    void addSong();
    void deleteSong();
    void printPlaylist();
    int countSongs();
//    void setRepeat(boolean repeat);
    void searchSong(String title, String artist);
    void setRepeat();
    void setNotRepeat();
    void addLastSongToGenre();
}

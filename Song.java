package musiclibrary;

/**
 *
 * @author Eskandar Atrakchi
 */
public class Song {
    
    private final String title;
    private final String artist;

    public Song(String title, String artist) {
        this.title = title;
        this.artist = artist;
    }

    // Getters for title and artist
    public String getTitle() {
        return title;
    }

    public String getArtist() {
        return artist;
    }

    @Override
    public String toString() {
        return "{" + "title= " + title + ", artist= " + artist + '}';
    }
    
}

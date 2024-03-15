package musiclibrary;

import java.util.Objects;

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
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        Song other = (Song) obj;
        return Objects.equals(this.title, other.title) && Objects.equals(this.artist, other.artist);
    }

    @Override
    public int hashCode() {
        return Objects.hash(title, artist);
    }
    
    @Override
    public String toString() {
        return "{" + "title= " + title + ", artist= " + artist + '}';
    }
    
}

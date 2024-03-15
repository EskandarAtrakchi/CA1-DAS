package musiclibrary;

/**
 *
 * @author Eskandar Atrakchi
 */
public class Node {
    
    private final Song song;
    private Node next;

    public Node(Song song) {
        this.song = song;
        this.next = null;
    }

    //we do not nned a setter for the song 
    public Song getSong() {
        return song;
    }

    public Node getNext() {
        return next;
    }

    public void setNext(Node next) {
        this.next = next;
    }
}

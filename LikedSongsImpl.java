package musiclibrary;

import javax.swing.JOptionPane;
import static musiclibrary.MusicLibraryGUI.artistTF;
import static musiclibrary.MusicLibraryGUI.titleTF;

/**
 *
 * @author Eskandar Atrakchi
 */
class LikedSongsImpl implements LikedSongsInterface {
    
    private Node head;
    private boolean repeat;

    public LikedSongsImpl() {
        this.head = null;
        this.repeat = false;
    }

    @Override
    public void addSong() {
        String title = titleTF.getText();
        String artist = artistTF.getText();

        // Check if title or artist is empty
        if (title.isEmpty() || artist.isEmpty()) {
            JOptionPane.showMessageDialog(null, "Please enter both title and artist.");
            return;
        }

        Song newSong = new Song(title, artist);
        Node newNode = new Node(newSong);

        if (head == null) {
            head = newNode;
            JOptionPane.showMessageDialog(null, "First Song added successfully!");
            MusicLibraryGUI.repeatTOGGLE2.setEnabled(true);
            if (repeat) {
                head.setNext(head); // Pointing to itself to indicate a circular list
            }
        } else {
            Node last = getLastNode();
            if (last != null) {
                last.setNext(newNode);
                JOptionPane.showMessageDialog(null, "Added to the list of liked songs.");
                MusicLibraryGUI.repeatTOGGLE2.setEnabled(true);
                if (repeat) {
                    newNode.setNext(head); // Pointing the last node to the head to create a circular list
                    head = newNode; // Update the head to the newly added node to maintain circularity
                }
            } else {
                // Handle the case where last node is null
                JOptionPane.showMessageDialog(null, "Error: Last node is null.");
            }
        }
    }

    @Override
    public void printPlaylist() {
        if (head == null) {
            JOptionPane.showMessageDialog(null, "There is nothing to print");
            return;
        }

        Node current = head;
        do {
            System.out.println(current.getSong().getTitle() + " - " + current.getSong().getArtist());
            current = current.getNext();
            if (current.getNext() == null ) {
                System.out.println(getLastNode().getSong().getTitle() + " - " + getLastNode().getSong().getArtist());
                return;
            }
        } while (current != head);
    }

    
    @Override
    public void deleteSong() {
        if (head == null) {
            JOptionPane.showMessageDialog(null, "The liked list is empty");
            return;
        }

        String title = titleTF.getText();
        String artist = artistTF.getText();

        Node current = head;
        Node prev = null;
        boolean deletionSuccess = false;

        do {
            if (current.getSong().getTitle().equalsIgnoreCase(artist) || current.getSong().getTitle().equalsIgnoreCase(title)) {
                if (prev == null) {
                    head = head.getNext();
                    JOptionPane.showMessageDialog(null, "Removed successfully.");
                    deletionSuccess = true;
                    if (repeat) {
                        Node last = getLastNode();
                        last.setNext(head); // Updating the last node's next reference
                    }
                } else {
                    prev.setNext(current.getNext());
                    JOptionPane.showMessageDialog(null, "Removed successfully.");
                    deletionSuccess = true;
                }
                break; // Exit the loop once deletion is successful
            }
            prev = current;
            current = current.getNext();
        } while (current != head && current != null); // Stop when we reach the head again or if current is null (end of list)

        if (!deletionSuccess) {
            JOptionPane.showMessageDialog(null, "Deletion failed! Check the spellings!");
        }
    }
    
    @Override
    public int countSongs() {
        if (head == null) {
            JOptionPane.showMessageDialog(null, "The list is empty.");
            return 0;
        }

        int count = 0;
        Node current = head;
        do {
            count++;
            current = current.getNext();
            if (current.getNext() == null ) {
                count = count + 1;
                break;
            }
        } while (current != head);
        JOptionPane.showMessageDialog(null, "Number of liked songs are: " + count);
        return count;
    }

    @Override
    public void setRepeat() {
        
        Node last = connectingLastNodeToFirst();
        last.setNext(head); // Updating the last node's next reference to make the list circular
        JOptionPane.showMessageDialog(null, "The repeat mode is activated.");
    
    }
    
    private Node connectingLastNodeToFirst() {
        int count = 0;
       if (head == null) {
           System.out.println("List is empty!");
           return null; // Return null if the list is empty
       }

       Node current = head;
       while (current.getNext() != null && current.getNext() != head) {
           current = current.getNext();
           System.out.println("Repeating " + count + " songs");
       }
        System.out.println("Last song point to first song");
       return current;
   }
    
    @Override
    public void setNotRepeat () {
        
        // Disable repeat mode
        Node head = getOffRepeating();
        head.setNext(null); // Update the last node's next reference to null
        JOptionPane.showMessageDialog(null, "The repeat mode is deactivated.");
            
    }
    
    //getting of repeat 
    private Node getOffRepeating() {
        int count = 0;
        
        if (head == null) {
            System.out.println("List is empty!");
            return null; // Return null if the list is empty
        }

        Node current = head;
        while (current.getNext() != head && current.getNext().getNext() != head) {
            count ++ ;
            current = current.getNext();
            System.out.println(count);
        }
        System.out.println("last node point to null");
        return current;
    }
    
    @Override
    public void searchSong(String title, String artist) {
        if (head == null) {
            JOptionPane.showMessageDialog(null, "The liked list is empty.");
            return;
        }

        boolean found = false;
        StringBuilder result = new StringBuilder();

        Node current = head;
        do {
            if (current != null && current.getSong() != null) {
                Song currentSong = current.getSong();
                if (currentSong.getTitle().equalsIgnoreCase(title) || currentSong.getArtist().equalsIgnoreCase(artist)) {
                    result.append("Title: ").append(currentSong.getTitle()).append(", Artist: ").append(currentSong.getArtist()).append("\n");
                    found = true;
                }
                current = current.getNext();
            }
        } while (current != head && current != null); // Stop when we reach the head again or if current is null (end of list)

        if (found) {
            JOptionPane.showMessageDialog(null, "Search results:\n" + result.toString());
        } else {
            JOptionPane.showMessageDialog(null, "No songs found matching the search criteria.");
        }
    }

    private Node getLastNode() {
        if (head == null) {
            return null; // Return null if the list is empty
        }

        Node current = head;
        while (current.getNext() != null) {
            current = current.getNext();
        }
        return current;
    }
}

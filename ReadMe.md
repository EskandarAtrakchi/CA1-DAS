# LikedSongsImpl.java

This class implements the LikedSongsInterface and manages a playlist of liked songs.

### addSong()
Adds a new song to the liked songs playlist. It prompts the user to input the title and artist of the song and handles cases where the input is empty or repeat mode is activated.

```java

@Override
    public void addSong() {
        
        if (repeat) {
            JOptionPane.showMessageDialog(null, "repeat is activated, deactivate the repeat first.");
            System.out.println("repeat is activated");
            return;
        } else {
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
    }

```

### printPlaylist()
Prints the liked songs playlist. It iterates over the playlist and displays the title and artist of each song.

```java

@Override
    public void printPlaylist() {
        if (repeat) {
            JOptionPane.showMessageDialog(null, "repeat is activated, deactivate the repeat first.");
            System.out.println("repeat is activated");
            return;
        } else {
            if (head == null) {
                JOptionPane.showMessageDialog(null, "There is nothing to print");
                return;
            }

            Node current = head;
            StringBuilder playlistContent = new StringBuilder();
            do {
                playlistContent.append("Title: ").append(current.getSong().getTitle()).append(" - Artist: ").append(current.getSong().getArtist()).append("\n");
                current = current.getNext();
                if (current == head) {
                    break;
                }
            } while (current != null);

            JOptionPane.showMessageDialog(null, playlistContent.toString());
        }
    }

```
### deleteSong()
Deletes a song from the liked songs playlist. It allows the user to specify the title and artist of the song to delete and handles cases where the song is not found or repeat mode is activated.

```java

@Override
    public void deleteSong() {
        if (repeat) {
            JOptionPane.showMessageDialog(null, "repeat is activated, deactivate the repeat first.");
            System.out.println("repeat is activated");
            return;
        } else {
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
    }

```


### countSongs()
Counts the number of songs in the liked songs playlist and displays the count to the user. It handles cases where the playlist is empty or repeat mode is activated.

```java

@Override
   public int countSongs() {
       if (repeat) {
           JOptionPane.showMessageDialog(null, "repeat is activated, deactivate the repeat first.");
           System.out.println("repeat is activated");
           return 0;
       } else {
           if (head == null) {
               JOptionPane.showMessageDialog(null, "The list is empty.");
               return 0;
           }

           int count = 0;
           Node current = head;
           do {
               count++;
               current = current.getNext();
               if (current == null) { // Add a null check here
                   break; // Exit the loop if current is null
               }
           } while (current != head);

           JOptionPane.showMessageDialog(null, "Number of liked songs are: " + count);
           return count;
       }
   }

```
### setRepeat()
Activates the repeat mode, making the liked songs playlist circular. It updates the next reference of the last node to point back to the head node.


```java

@Override
    public void setRepeat() {
        
        Node last = connectingLastNodeToFirst();
        last.setNext(head); // Updating the last node's next reference to make the list circular
        JOptionPane.showMessageDialog(null, "The repeat mode is activated.");
        this.repeat = true;
        
    }

```
### setNotRepeat()
Deactivates the repeat mode, making the liked songs playlist linear. It updates the next reference of the last node to null.

### searchSong(String title, String artist)
Searches for a song in the liked songs playlist by title or artist. It prompts the user to input the title and artist of the song to search for and displays the search results.

# GenrePlaylistImpl.java

This class implements the GenrePlaylistInterface and manages playlists for different genres.

### addHipHopSong(Song song)
Adds a Hip-Hop song to the playlist. It adds the song to the Hip-Hop playlist and handles cases where repeat mode is activated.

### addPianoSong(Song song)
Adds a Piano song to the playlist. It adds the song to the Piano playlist and handles cases where repeat mode is activated.

### deleteHipHopSong(Song songToDelete)
Deletes a Hip-Hop song from the playlist. It allows the user to specify the song to delete and handles cases where the song is not found.

### deletePianoSong(Song songToDelete)
Deletes a Piano song from the playlist. It allows the user to specify the song to delete and handles cases where the song is not found.

### printHipHopPlaylist()
Prints the Hip-Hop playlist. It iterates over the playlist and displays the title and artist of each song.

### printPianoPlaylist()
Prints the Piano playlist. It iterates over the playlist and displays the title and artist of each song.

### countHiphopSongs()
Counts the number of songs in the Hip-Hop playlist and displays the count to the user.

### countPianoSongs()
Counts the number of songs in the Piano playlist and displays the count to the user.

### setRepeat(boolean repeat)
Sets the repeat mode for both playlists. It updates the next reference of the last node to point back to the head node if repeat mode is activated.

### searchHipHopSong(String title, String artist)
Searches for a Hip-Hop song in the playlist by title or artist. It prompts the user to input the title and artist of the song to search for and displays the search results.

### searchPianoSong(String title, String artist)
Searches for a Piano song in the playlist by title or artist. It prompts the user to input the title and artist of the song to search for and displays the search results.

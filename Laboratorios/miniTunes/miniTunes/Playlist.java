import java.util.ArrayList;
import java.util.Arrays;

/** Playlist's class.
 * Each song is described by its title, artist, genre, duration, and rating.
 */
//The title and artist are mandatory. The genre, duration, and rating may be unknown.
//The combination (title, artist) must be unique. Two songs cannot have the same title and artist.
//The duration (minutes) must be between 1 and 9.
//The rating must be between * and *****.

public class Playlist {
    
    private String[][] songs;
    private int size;
    
    public Playlist(String [][] songs){
        ArrayList<String[]> validSongs = getValidSongs(songs);
        int sizeValidSongs = validSongs.size();
        this.songs = new String[sizeValidSongs][5];
        for (int i = 0; i < sizeValidSongs; i++) {
            this.songs[i] = validSongs.get(i);
        }
        size = this.songs.length;
    }
    
    private ArrayList<String[]> getValidSongs(String [][] songsToVerify) {
    ArrayList<String[]> validSong = new ArrayList<>(); 
    boolean canAddSong; String currentString;
    for (int i = 0; i < songsToVerify.length; i++) {
        canAddSong = false;
        if (isDuplicateInList(songsToVerify[i], validSong)) continue;
        for (int j = 0; j < songsToVerify[i].length; j++) {
            currentString = songsToVerify[i][j];
            if (j == 0) {
                if (currentString == null) {
                    canAddSong = true;
                    continue;
                }
            } else if (j == 1) {
                if (currentString == null) {
                    canAddSong = true;
                    continue;
                }
            } else if (j == 3) {
                if (currentString != null) {
                    boolean isDurationANumber = currentString.matches("\\d+");
                    if (isDurationANumber) {
                        int durationNumber = Integer.parseInt(currentString);
                        if (durationNumber > 9 || durationNumber < 1) canAddSong = true;
                    }
                    
                }
            } else if (j == 4) {
                if (currentString != null) {
                    if (currentString.length() > 5 || currentString.length() < 1) canAddSong = true;
                }
            }
        }
        if (!canAddSong) {
            validSong.add(songsToVerify[i]);
        }
    }
    return validSong;
}

    // Usado SOLO dentro del constructor, compara contra la lista que se está armando
    private boolean isDuplicateInList(String[] songToCheck, ArrayList<String[]> list) {
        if (songToCheck[0] == null || songToCheck[1] == null) return false;
        for (String[] existing : list) {
            if (songToCheck[0].trim().equalsIgnoreCase(existing[0].trim()) &&
                songToCheck[1].trim().equalsIgnoreCase(existing[1].trim())) {
                return true;
            }
        }
        return false;
    }


    private boolean isDuplicateInSongs(String[] songToCheck) {
        if (songToCheck[0] == null || songToCheck[1] == null) return false;
        for (String[] existing : songs) {
            if (songToCheck[0].trim().equalsIgnoreCase(existing[0].trim()) &&
                songToCheck[1].trim().equalsIgnoreCase(existing[1].trim())) {
                return true;
            }
        }
        return false;
    }
    
    public Playlist add(String [] song){
        boolean isTitle = true, isArtist = true;
        String durationSong = song[3];
        if (song[0]==null || song[1]==null) return this;
        if (durationSong != null) {
            boolean durationIsInteger = song[3].matches("\\d+");
            if (durationIsInteger) if ((Integer.parseInt(song[3]) > 9 || Integer.parseInt(song[3]) < 1) && song[3] != null) return this;
        }
        if (isDuplicateInSongs(song)) return this; 
        String[][] songs = new String[this.songs.length+1][5];
        for (int i = 0; i < this.songs.length; i++) {
            songs[i] = this.songs[i];
        }
        
        songs[this.songs.length] = song;
        this.songs = songs;
        size += 1;
        return this;
    }
    
    public Playlist delete(String [] song){
        String[] fileSong;
        boolean areTheSameSong=false;
        
        for (int i = 0; i < songs.length; i++) {
            fileSong = songs[i];
            areTheSameSong = Arrays.equals(fileSong, song);
            if (areTheSameSong) {
                return deleting(song);
            }
        }
        return this;
    }
    
    private Playlist deleting(String[] song) {
        String[][] newSongs = new String[songs.length - 1][5];
        int newIndex = 0;
        boolean isTheSongToDelete;
        for (int i = 0; i < songs.length; i++) {
            isTheSongToDelete = Arrays.equals(songs[i], song);
            if (isTheSongToDelete) {
                continue; // se salta la canción a eliminar
            }
            newSongs[newIndex] = songs[i];
            newIndex++;
        }
        this.songs = newSongs;
        this.size = newSongs.length;
        return this;
    }
    
    public Playlist select(String [] values){
        return null;
    }      

    public int size(){
        return size;
    }    
    
   
    /** Songs are in uppercase with unnecessary spaces removed.
     *Columns are aligned and separated by three spaces.
     *TITLE    ARTIST          GENRE   DURATION   RATING
     *ONE      U2              ROCK           4   *****
     *NUMB     LINKIN PARK     ROCK           3
     *ALIVE    PEARL JAM       ROCK           5   ****
     *CREEP    RADIOHEAD       ROCK               *****
     *DREAMS   FLEETWOOD MAC   .              4   ****
     *@return 
      */
    public String toString() {
        String messageToShow = String.format("%-3s   %-3s   %-3s   %-3s   %-3s",
            "TITLE", "ARTIST", "GENRE", "DURATION", "RATING")+"\n";
            
        String title, artist, genre, duration, rating;
        boolean isDurationANumber, isRatingANumber;
        for (int i = 0; i < songs.length; i++) {
            isDurationANumber = true; isRatingANumber = false;
            title = songs[i][0]; artist = songs[i][1]; genre = songs[i][2]; 
            title = prepareString(title);
            artist = prepareString(artist);
            genre = prepareString(genre);
            
            duration = songs[i][3]; rating = songs[i][4];
            if (duration != null) {
                isDurationANumber = duration.matches("\\d+");
                if (isDurationANumber) {
                    int numberDuration = Integer.parseInt(duration);
                    if (numberDuration <= 0 && numberDuration >= 10) {
                        duration = null;
                    }
                }
            }
            if (rating != null) {
                isRatingANumber = rating.matches("\\d+");
                rating = rating.trim().replaceAll("\\s+" , "");
                if (rating.length() > 5) {
                    rating = "*****";
                } 
                if (rating.length() < 1) {
                    rating = "*";
                }
            }
            
            if (!isDurationANumber) duration = null;
            if (isRatingANumber) rating = convertNumberToRating(rating);
                messageToShow+=String.format("%-3s   %-3s   %-3s   %-3s   %-3s",
                title, artist, genre, duration, rating)+"\n";
        }
        return messageToShow;
    }
    
    /**
     * Verify if is the same playList
     */
    public boolean equals(Playlist pl){
        String[][] songsToCompare = pl.getSongs();
        if (this.size != pl.size()) return false;
        String nameSong, nameSongToCompare;
        boolean isSameSong;
        for (int i = 0; i < songs.length; i++) {
            for (int j = 0; j < songs[i].length; j++) {
                isSameSong = true;
                if ((songs[i][j]==null && songsToCompare[i][j]!=null) || 
                    (songs[i][j]!=null && songsToCompare[i][j]==null)) return false; // Si alguna de las dos es nula
                if (songs[i][j] !=null && songsToCompare[i][j]!=null) {
                    nameSong = songs[i][j].trim().replaceAll("\\s+", " ");
                    if (j == 4) {
                        nameSongToCompare = songsToCompare[i][j].trim().replaceAll("\\s+", "");
                    } else {
                        nameSongToCompare = songsToCompare[i][j].trim().replaceAll("\\s+", " ");
                    }
                    isSameSong = nameSong.equalsIgnoreCase(nameSongToCompare);
                }
                if (!isSameSong) {
                    return false;
                }
            }
        }
        return true;
    }
    
    public boolean equals(Object o){
        return equals((Playlist)o);
    }
    
    public String[][] getSongs() {
        return songs;
    }
    
    private String prepareString(String word) {
        if (word != null) {
            word = word.trim().replaceAll("\\s+", " ");
            word = word.substring(0, 1).toUpperCase()+word.substring(1).toLowerCase();
        }
        return word;
    }
    
    private String convertNumberToRating(String rating) {
        switch (rating) {
            case "0":
                return "";
            case "1":
                return "*";
            case "2":
                return "**";
            case "3":
                return "***";
            case "4":
                return "****";
            case "5":
                return "*****";
            default:
                return null;
        }
    }
}

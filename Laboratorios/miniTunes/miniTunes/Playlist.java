import java.util.ArrayList;

/**
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
        boolean isThereSomeNull;
        String currentSong;
        for (int i = 0; i < songsToVerify.length; i++) {
            isThereSomeNull = false;
            for (int j = 0; j < songsToVerify[i].length; j++) {
                currentSong = songsToVerify[i][j];
                if (j == 0) {
                    if (currentSong == null) {
                        isThereSomeNull = true;
                        continue;
                    }
                } else if (j == 1) {
                    if (currentSong == null) {
                        isThereSomeNull = true;
                        continue;
                    }
                }
            }
            if (!isThereSomeNull) {
                validSong.add(songsToVerify[i]);
            }
        }
        return validSong;
    }
    
    public Playlist add(String [] song){
        return null;
    }
    
    public Playlist delete(String [] song){
        return null;
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
            if (duration != null) isDurationANumber = duration.matches("\\d+");
            if (rating != null) {
                isRatingANumber = rating.matches("\\d+");
                rating = rating.trim().replaceAll("\\s+" , "");
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
                if (songs[i][j]!=null && songsToCompare[i][j]!=null) {
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

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
    private String[][] songsSelected;
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
    
    /**Add a new song at the playlist
     * @param song song is the file of playlist that going to be added
     * @return Playlist This playlist
     */
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
    
    /**Delete an specific song og this playlist.
     * @param song song is the file that going to be deleted of this playlist.
     * @return Playlist This playlist
     */
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
    
    
    
    public String[][] select(String [] values){
        int numberCoincide = 0, totalCoincideValues = 0;
        for (int k = 0; k < values.length; k++) {
            if (values[k] != null) {
                totalCoincideValues += 1;
            }
        }
        String[] fileSong;
        boolean isCoincideSong=false;
        ArrayList<String[]> coincideSong = new ArrayList<>();
        int songLength = songs.length;
        for (int i = 0; i < songLength; i++) {
            fileSong = songs[i];
            for (int j = 0; j < fileSong.length; j++) {
                if (values[j] != null) {
                    if (values[j].equals(fileSong[j])) numberCoincide++;
                    isCoincideSong = totalCoincideValues == numberCoincide;
                    if (isCoincideSong) {
                        coincideSong.add(fileSong);
                        numberCoincide = 0;
                    }
                }
            }
        }
        if (totalCoincideValues == 0) addEverySongToArrayList(values, coincideSong);
        makeListSongSelected(coincideSong);
        return songsSelected;
    }      

    /**
     * Give size of playlist
     */
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
     *@return string An String defining like a table the playlist 
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
     * @param true if every song of this playlist are the same song of the other playlist
     *      false otherwise
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
    
    /*
     * Make a string with just their content, quit every spaces at the string
     * @param word word is the string that going to quit the spaces
     * @return string It's the word without spaces
     */
    private String prepareString(String word) {
        if (word != null) {
            word = word.trim().replaceAll("\\s+", " ");
            word = word.substring(0, 1).toUpperCase()+word.substring(1).toLowerCase();
        }
        return word;
    }
    
    /*
     * Convert a number to start, just can be "", "*", "**", "***", "****" y "*****"
     * @return String An string with the starts of rating if this is an number
     */
    private String convertNumberToRating(String rating) {
        return switch (rating) {
            case "0" -> "";
            case "1" -> "*";
            case "2" -> "**";
            case "3" -> "***";
            case "4" -> "****";
            case "5" -> "*****";
            default -> null;
        };
    }
    
    /*Give the songs that can be added follow the invariant of this class Playlist.
     * @param songsToVerify songsToVerify are a matrix of string content the songs that will be verify if they satisfy Playlist's invariant
     * @return ArrayList<String[]> with the valid songs.
     */
    private ArrayList<String[]> getValidSongs(String [][] songsToVerify) {
        ArrayList<String[]> validSong = new ArrayList<>(); 
        boolean canAddSong; String currentString;
        if (songsToVerify == null) return validSong;
        
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
                        currentString = currentString.replaceAll("\\s+", "");
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

    /* Verify if there is some duplicated song between two arrays.
     * @param songToCheck songToCheck are the songs to verify if there is some duplicated song.
     * @param list list are the list that will be comparated to verify the repetition of this song.
     * @return true if there is some duplicated song at the list, false otherwise.
     */
    private boolean isDuplicateInList(String[] songToCheck, ArrayList<String[]> list) {
        // Usado SOLO dentro del constructor, compara contra la lista que se está armando
        if (songToCheck[0] == null || songToCheck[1] == null) return false;
        for (String[] existing : list) {
            if (songToCheck[0].trim().equalsIgnoreCase(existing[0].trim()) &&
                songToCheck[1].trim().equalsIgnoreCase(existing[1].trim())) {
                return true;
            }
        }
        return false;
    }

    /* Verify if there is some duplicated song between two arrays.
     * @param songToCheck songToCheck are the songs to verify if there is some duplicated song comparated with songs of this playlist.
     * @return true if there is some duplicated song comparated with songs of this playlist, false otherwise.
     */
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
    
    /* Make the modification of songs
     * @param song song is the file that going to be delete of this playlist.
     * @param Playlist This playlist.
     */
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
    
    private void makeListSongSelected(ArrayList<String[]> coincideSong) {
        int sizeCoincideSong = coincideSong.size();
        songsSelected = new String[sizeCoincideSong][5];
        int indexSong = 0;
        for (String[] song : coincideSong) {
            songsSelected[indexSong] = song;
            indexSong++;
        }
    }
    
    private ArrayList<String[]> addEverySongToArrayList(String[] values, ArrayList<String[]> coincideSong) {
        int i = 0;
        do {
            coincideSong.add(songs[i]);
            i++;
        } while (i <  songs.length);
        return coincideSong;
    }
}

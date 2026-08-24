//Each song is described by its title, artist, genre, duration, and rating.
//The title and artist are mandatory. The genre, duration, and rating may be unknown.
//The combination (title, artist) must be unique. Two songs cannot have the same title and artist.
//The duration (minutes) must be between 1 and 9.
//The rating must be between * and *****.

public class Playlist {
    
    private String[][] songs;
    private int size;
    
    public Playlist(String [][] songs){
        this.songs = songs;
        size = this.songs.length;
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
      return "";
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
                if  (songs[i][j]==null || songsToCompare[i][j]==null) continue;
                if ((songs[i][j]==null && songsToCompare[i][j]!=null) || 
                    (songs[i][j]!=null || songsToCompare[i][j]==null)) return false; // Si alguna de las dos es nula
                nameSong = songs[i][j].trim();
                nameSongToCompare = songsToCompare[i][j].trim();
                isSameSong = nameSong.equalsIgnoreCase(nameSongToCompare);
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
}

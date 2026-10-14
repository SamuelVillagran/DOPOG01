package domain;  
 
import java.util.ArrayList;

public class Series extends Content{
   
    private int year;
    private ArrayList<Episode> episodes;
    
    /**
     *  Constructor series, title and year
     */
    public Series(String title, int year){
        super(title);
        this.year=year;
        episodes= new ArrayList<Episode>();
    }

    
    /**
     * add episode in serie
     */
    public void addEpisode(Episode e){
        episodes.add(e);
    }
       
    
    /**
    * Returns the calculated rating
    * @return
    * @throws NeoFlixException, CONTENT_EMPTY if the series has no episodes
    *                         , VALUE_UNKNOWN if the rating for any episode is unknown
    *                         , DATA_ERROR if the rating for any episode cannot be calculated due to a data error
    */
    @Override
    public int rating() throws NeoFlixException{
       int sum = 0;
       int value;
       for (Audiovisual e :  episodes) {
           value = e.rating(); 
           if(value == 0) throw new NeoFlixException(NeoFlixException.VALUE_UNKNOWN); 
           sum = sum + value;
       }
       sum = sum / episodes.size();
       return sum;
    }

   /**
   * Returns the calculated rating, using the default value for episodes with an unknown rating and ignoring those with errors.
   * @return
   * @throws NeoFlixException, UNKNOWN_VALUE if the rating cannot be calculated.
   */
   public int rating(int default_) throws NeoFlixException{
       if (episodes.isEmpty()) throw new NeoFlixException(NeoFlixException.CONTENT_EMPTY);
       int sum = 0, value, total_episodes = episodes.size(), result;
       for (Episode e : episodes) {
           try {
               value = e.rating();
               if (value == 0) throw new NeoFlixException(NeoFlixException.VALUE_UNKNOWN);
               sum += value; 
           } catch (NeoFlixException nfe) {
               String messageException = nfe.getMessage();
               if (messageException.equals(NeoFlixException.VALUE_UNKNOWN)) {
                   sum += default_;
               } else if (messageException.equals(NeoFlixException.DATA_ERROR)) {
                   total_episodes--;
               }
           }
       }
       result = sum/total_episodes;
       return result;
   }
 
   /**
     * @return calculate rating
    */
   public int rating(boolean previous) throws NeoFlixException{
       int sum = 0;
       int value;
       
       for (Audiovisual e :  episodes) {
           value = e.rating(); 
           if(value == 0) throw new NeoFlixException(NeoFlixException.VALUE_UNKNOWN); 
           sum = sum + value;
       }
       sum = sum / episodes.size();
       return sum;
       
   }
    
   /**
    * @return calculate popularity
    */
   public int popularity() throws NeoFlixException{
       return 0;
   }
    
    /**
     * @return title + year + popularity + rating + data episodes 
     */
    @Override
    public String data(boolean withIndicators) throws NeoFlixException{
        StringBuffer answer=new StringBuffer();
        answer.append(title+": "+year+ ( withIndicators ? " ( " +popularity()+" - "+ rating()+" )":""));
        for(Episode e: episodes) {
            answer.append("\n\t"+(e.data(withIndicators)));
        }
        return answer.toString();
    } 
    

}

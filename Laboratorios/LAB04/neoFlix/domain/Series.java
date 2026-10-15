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
       if (total_episodes <= 0) throw new NeoFlixException(NeoFlixException.CONTENT_EMPTY);
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
    
   /** Calculates popularity as ((completed) / attempts) plus the popularity of the different episodes, 
    * divided by the total number of episodes, all of this multiplied by 100.
    * @return calculate popularity; return the popularity [0..100]
    * @throws VALUE_UNKNOWN - When some data of episode is zero
    * @throws DATA_ERROR - When completed episodes is more than its attempts.
    */
   public double popularity() throws NeoFlixException {
       if (episodes.isEmpty()) throw new NeoFlixException(NeoFlixException.CONTENT_EMPTY);
       int total_episodes = episodes.size();
       double sum = 0, value, result = 0;
       String messageException = null;
       for (Audiovisual e : episodes) {
           try {
           value = e.popularity();
           if(value == 0) throw new NeoFlixException(NeoFlixException.VALUE_UNKNOWN);
           sum += value;
           } catch (NeoFlixException nfe) {
               messageException = nfe.getMessage();
               if (messageException.equals(NeoFlixException.VALUE_UNKNOWN) || 
                    messageException.equals(NeoFlixException.DATA_ERROR)) {
                   total_episodes--;
               }
           }
       }
       if (total_episodes <= 0) throw new NeoFlixException(NeoFlixException.CONTENT_EMPTY);
       result = (sum/total_episodes)*100;
       return Math.round(result * 1000.0) / 1000.0;// To Aproximate to three decimals
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

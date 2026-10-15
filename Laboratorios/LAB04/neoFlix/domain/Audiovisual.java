package domain;  

public abstract class Audiovisual extends Content{
    
    private int attempts;
    private int completed;
    private int votes;
    private int sumVotes; 
    //INV: 0<=completed<=attempts and 0<=votes and 0<=sumVotes<=votes*10
    
    
    /**
     * Constructor, abstract element to content finals 
     */
    public Audiovisual(String title, int attempts, int completed, int votes, int sumVotes){
        super(title);
        this.attempts=attempts;
        this.completed=completed;
        this.votes=votes;
        this.sumVotes=sumVotes;
    }
    
    /**
     * Constructor, only title
     */
    public Audiovisual(String title){
        this(title,0,0,0,0);
    }
    
    /**
     * @return calculate popularity
     */
    @Override
    public int popularity() throws NeoFlixException{ //P
       if (attempts == 0) throw new NeoFlixException(NeoFlixException.VALUE_UNKNOWN); //L
       return (completed*100)/attempts;
    }    
    
    /**
     * @return calculate rating
     */
    @Override
    public int rating() throws NeoFlixException{
       if (votes == 0) throw new NeoFlixException(NeoFlixException.VALUE_UNKNOWN);
       if (sumVotes>votes*10) throw new NeoFlixException(NeoFlixException.DATA_ERROR);
       return sumVotes/votes;
    }  
    
    /**
     * @return title + popularity + rating
     */
    @Override
    public String data(boolean withIndicators){
        String theData= title;
        if (withIndicators){
            try{
                theData+=" ( " +popularity()+" - "+ rating()+" )";
            } catch (NeoFlixException e){
                theData += "*** Indicadores incompletos";
            }
        }
        return theData;
    }
}

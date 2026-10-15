package domain;
import java.util.TreeMap;

public class Collection extends Content{

    private String theme;
    private TreeMap<String, Content> contents;
    
    /**
     * Constructor Collection, title and theme 
     */
    public Collection(String title, String theme){
        super(title);
        this.theme=theme;
        contents= new TreeMap<String,Content>();
    }
    
    /**
     * @return calculate popularity
     */
    @Override
    public int popularity() throws NeoFlixException{
        throw new NeoFlixException(NeoFlixException.TO_BE_IMPLEMENTED);
    }
    
    /**
     * @return calculate rating
     */
    @Override
    public int rating() throws NeoFlixException{
        throw new NeoFlixException(NeoFlixException.TO_BE_IMPLEMENTED);
    }
    
    /**
     * @return data to colection, colection title theme and data content
     */
    @Override
    public String data(boolean withIndicators) throws NeoFlixException{
        throw new NeoFlixException(NeoFlixException.TO_BE_IMPLEMENTED);
    }
    
}

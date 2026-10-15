package test;
import domain.*;

import static org.junit.Assert.*;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;


public class SeriesTest{
   
 
    @Test
    public void shouldCalculateSeriessRating(){                              
        Series s = new Series("The Neo Ninja", 2020);
        s.addEpisode(new Episode("The Hidden Village",s,120,199,50,430));
        s.addEpisode(new Episode("The First Mission",s,140,126,60,510));
        s.addEpisode(new Episode("The Rival",s,120,199,50,430));
        s.addEpisode(new Episode("The Tournament",s,180, 162, 90, 810));
        s.addEpisode(new Episode("The Final Battle",s, 200, 190, 120, 1080));
        try {
           assertEquals(8,s.rating());
        } catch (NeoFlixException e){
            fail("Threw a exception "+e.getMessage());
        }    
    }    

    
    @Test
    public void shouldThrowExceptionIfTheSeriessHasNoEpisodes(){
        Series s = new Series("The Neo Ninja", 2020);
        try { 
           int value=s.rating();
           fail("Did not throw exception");
        } catch (NeoFlixException e) {
            assertEquals(NeoFlixException.CONTENT_EMPTY,e.getMessage());
        }    
    }    
    
    
    @Test
    public void shouldThrowExceptionWhenAnEpisodeRatingIsUnknown(){
        Series s = new Series("The Neo Ninja", 2020);
        s.addEpisode(new Episode("The Hidden Village",s,120,199,50,430));
        s.addEpisode(new Episode("The First Mission",s,140,126,60,510));
        s.addEpisode(new Episode("The Rival",s,120,199,0,0));
        s.addEpisode(new Episode("The Tournament",s,180, 162, 90, 810));
        s.addEpisode(new Episode("The Final Battle",s, 200, 190, 120, 1080));
        try { 
           int value=s.rating();
           fail("Did not throw exception");
        } catch (NeoFlixException e) {
            assertEquals(NeoFlixException.VALUE_UNKNOWN, e.getMessage());
        }    
    } 
    
   @Test
    public void shouldThrowExceptionIfAEpisodeContainsInvalidRatingData(){
        Series s = new Series("The Neo Ninja", 2020);
        s.addEpisode(new Episode("The Hidden Village",s,120,199,50,430));
        s.addEpisode(new Episode("The First Mission",s,140,126,60,610));
        s.addEpisode(new Episode("The Rival",s,120,199,0,0));
        s.addEpisode(new Episode("The Tournament",s,180, 162, 90, 810));
        s.addEpisode(new Episode("The Final Battle",s, 200, 190, 120, 1080));
        try { 
           int value=s.rating();
           fail("Did not throw exception");
        } catch (NeoFlixException e) {
            assertEquals(NeoFlixException.DATA_ERROR,e.getMessage());
        }    
    }     
    
   @Test
   public void shouldGiveMeNormalResultRatingWithDefault() {
       Series s = new Series("La Serie", 2020);
        s.addEpisode(new Episode("El uno", s, 0, 0, 100, 1000));
        s.addEpisode(new Episode("El dos",s, 0, 0, 50, 400));
        s.addEpisode(new Episode("El tres",s, 0, 0, 20, 120));
        s.addEpisode(new Episode("El cuatro",s, 0, 0, 10, 40));
       int value = 0;
       try {
           value = s.rating(10);
       } catch (NeoFlixException nfe) {
           nfe.printStackTrace();
       }
       assertEquals(7, value);
   }

   @Test
   public void shouldGiveMeResultPlusDefaultAtRating() {
       Series s = new Series("La Serie", 2020);
        s.addEpisode(new Episode("El uno", s, 0, 0, 100, 1000));
        s.addEpisode(new Episode("El dos",s, 0, 0, 50, 400));
        s.addEpisode(new Episode("El tres",s, 0, 0, 0, 0));
        s.addEpisode(new Episode("El cuatro",s, 0, 0, 10, 40));
       int value = 0;
       try {
           value = s.rating(10);
       } catch (NeoFlixException nfe) {
           nfe.printStackTrace();
       }
       assertEquals(8, value);
   }
   
   @Test
   public void shouldGiveMeResultDividedByThree() {
       Series s = new Series("La Serie", 2020);
        s.addEpisode(new Episode("El uno", s, 0, 0, 100, 1000));
        s.addEpisode(new Episode("El dos",s, 0, 0, 50, 540));
        s.addEpisode(new Episode("El tres",s, 0, 0, 20, 120));
        s.addEpisode(new Episode("El cuatro",s, 0, 0, 10, 40));
       int value = 0;
       try {
           value = s.rating(10);
       } catch (NeoFlixException nfe) {
           nfe.printStackTrace();
       }
       assertEquals(6, value);
   }
   
   @Test
   public void shouldGiveMeResultDividedByThreePlusDefault() {
       Series s = new Series("La Serie", 2020);
        s.addEpisode(new Episode("El uno", s, 0, 0, 100, 1000));
        s.addEpisode(new Episode("El dos",s, 0, 0, 50, 540));
        s.addEpisode(new Episode("El tres",s, 0, 0, 0, 0));
        s.addEpisode(new Episode("El cuatro",s, 0, 0, 10, 40));
       int value = 0;
       try {
           value = s.rating(10);
       } catch (NeoFlixException nfe) {
           nfe.printStackTrace();
       }
       assertEquals(8, value);
   }
}
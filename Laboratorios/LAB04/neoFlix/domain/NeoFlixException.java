package domain;


/**
 * Write a description of class NeoFlixException here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
public class NeoFlixException extends Exception
{
    public final static String VALUE_UNKNOWN = "";
    public final static String DATA_ERROR = "";
    public final static String TO_BE_IMPLEMENTED = "";
    public final static String CONTENT_EMPTY = "";
    /**
     * Constructor for objects of class NeoFlixException
     */
    public NeoFlixException(String m)
        {
            super(m);
        }   
    }
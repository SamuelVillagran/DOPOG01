package domain;


/**
 * Write a description of class NeoFlixException here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
public class NeoFlixException extends Exception
{
    public final static String VALUE_UNKNOWN = "Valor desconocido, no encontrado";
    public final static String DATA_ERROR = "El valor no es válido";
    public final static String TO_BE_IMPLEMENTED = "Por implementar";
    public final static String CONTENT_EMPTY = "No tiene valor, contenido nulo";
    /**
     * Constructor for objects of class NeoFlixException
     */
    public NeoFlixException(String m)
        {
            super(m);
        }   
    }
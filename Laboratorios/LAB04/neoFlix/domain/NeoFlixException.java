package domain;


/**
 * Write a description of class NeoFlixException here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
public class NeoFlixException extends Exception
{
    public static final String VALUE_UNKNOWN = "VALUE_UNKNOWN: no hay datos suficientes para calcular el indicador";
    public static final String DATA_ERROR = "DATA_ERROR: los datos violan las reglas del dominio";
    public static final String CONTENT_EMPTY = "CONTENT_EMPTY: el contenido no tiene elementos";
    public static final String TO_BE_IMPLEMENTED = "TO_BE_IMPLEMENTED: funcionalidad no implementada";
    public static final String NAME_EXISTS = "NAME_EXISTS: ya existe un contenido con ese nombre";
    public static final String INVALID_NUMBER = "INVALID_NUMBER: un campo numerico no contiene un entero valido";
    public static final String INVALID_VALUE = "INVALID_VALUE: valor fuera del rango permitido";
    public static final String TITLE_EMPTY = "TITLE_EMPTY: el titulo no puede estar vacio";
    public static final String EPISODE_NOT_FOUND = "EPISODE_NOT_FOUND: el episodio indicado no existe";
    public static final String WRONG_CONTENT_TYPE = "WRONG_CONTENT_TYPE: el contenido indicado no es una serie";

    /**
     * Constructor for objects of class NeoFlixException
     */
    public NeoFlixException(String m)
        {
            super(m);
        }   
    }
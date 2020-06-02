package dev.legrug.positionalparser.exception;

import java.text.MessageFormat;

/**
 * <i>Positional parser</i> exception's.<br>
 * Used to encapsulate miscellaneous exceptions.
 * 
 * @author Márcio Gurgel (marcio.rga@gmail.com)
 *
 */
public class PositionalParserException extends RuntimeException {

    private static final long serialVersionUID = 1L;

    /**
     * Creates exception with custom message and original exception
     * @param message Custom message
     * @param originalException Original exception (<i>caused by</i>)
     */
    public PositionalParserException(String message, Exception originalException) 
    {
        super(message, originalException);
    }

    /**
     * Parametrized message constructor
     * @param message Message <i>must have variables (eg. Hello "{0}")</i>
     * @param arguments Arguments to be applied
     */
    public PositionalParserException(String message, Object... arguments)
    {
        super(MessageFormat.format(message, arguments));
    }


}

package dev.legrug.positionaltweak.exception;

import java.text.MessageFormat;

/**
 * <i>Positional parser</i> exception's.<br>
 * Used to encapsulate miscellaneous exceptions.
 * 
 * @author Márcio Gurgel (marcio.rga@gmail.com)
 *
 */
public class PositionalTweakException extends RuntimeException {

    private static final long serialVersionUID = 1L;

    /**
     * Creates exception with custom message and original exception
     * @param message Custom message
     * @param originalException Original exception (<i>caused by</i>)
     */
    public PositionalTweakException(String message, Exception originalException, Object... arguments)
    {
        super(MessageFormat.format(message, arguments), originalException);
    }

    /**
     * Parametrized message constructor
     * @param message Message <i>must have variables (eg. Hello "{0}")</i>
     * @param arguments Arguments to be applied
     */
    public PositionalTweakException(String message, Object... arguments)
    {
        super(MessageFormat.format(message, arguments));
    }


}

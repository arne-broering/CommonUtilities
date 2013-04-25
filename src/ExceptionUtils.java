/**
 * @author <a href="mailto:broering@52north.org">Arne Broering</a>
 */
public class ExceptionUtils {

    /**
     * puts stack trace elements in String[] and returns it.
     */
    public static String[] createStringArrayFromStackTrace(StackTraceElement[] sTElements) {
        String[] errorDetails = new String[sTElements.length];
        for (int i = 0; i < sTElements.length; i++) {
            errorDetails[i] = sTElements[i].toString();
        }
        return errorDetails;
    }
    
    /**
     * puts stack trace elements of specified {@link Throwable} e in String and returns it.
     */
    public static String createStringFromStackTrace(Throwable e) {
        
        String errorDetails = "";
        
        StackTraceElement[] sTElements = e.getStackTrace();
        for (int i = 0; i < sTElements.length; i++) {
            errorDetails += sTElements[i].toString() + "\n";
        }
        
        Throwable cause = e.getCause();
        if (cause != null) {
            errorDetails += "------------ caused by:";
            errorDetails += createStringFromStackTrace(cause);
        }
        
        return errorDetails;
    }
}

import java.util.Collection;
import java.util.Iterator;

/**
 * @author <a href="mailto:broering@52north.org">Arne Broering</a>
 */  
public class ArrayUtils {

    public static String[] toArray(Collection<String> stringCollection) {
        String[] sArray = new String[stringCollection.size()];
        
        int i=0;
        for (Iterator<String> iterator = stringCollection.iterator(); iterator.hasNext();) {
            sArray[i] = (String) iterator.next();
            i++;
        }
        return sArray;
    }
    
    /**
     * transforms the oArray to a String[]
     */
    public static String[] toStringArray(Object[] oArray) {
        String[] sArray = new String[oArray.length];
        for (int i = 0; i < oArray.length; i++) {
            sArray[i] = (String) oArray[i];
        }
        return sArray;
    }

    /**
     * adds the elements of the fromArray to the toArray.
     */
    public static byte[] addArrayElements(byte[] toArray, byte[] fromArray) {

        byte[] newToArray = new byte[toArray.length + fromArray.length];

        System.arraycopy(toArray, 0, newToArray, 0, toArray.length);

        System.arraycopy(fromArray, 0, newToArray, toArray.length, fromArray.length);

        return newToArray;
    }
    
    /**
     * adds the elements of the fromArray to the toArray.
     */
    public static String[] addArrayElements(String[] toArray, String[] fromArray) {

        String[] newToArray = new String[toArray.length + fromArray.length];

        System.arraycopy(toArray, 0, newToArray, 0, toArray.length);

        System.arraycopy(fromArray, 0, newToArray, toArray.length, fromArray.length);

        return newToArray;
    }
}

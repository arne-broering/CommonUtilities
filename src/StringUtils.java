/**
 * @author <a href="mailto:broering@52north.org">Arne Broering</a>
 */
public class StringUtils {

    /**
     * 
     * @param baseString
     * @param wordToFind
     * @param wordToUse
     * @return
     */
    public static StringBuffer replaceWordInString(StringBuffer baseString, String wordToFind, String wordToUse)
    {
        int i = baseString.indexOf(wordToFind);
        while(i != -1) {
            baseString = baseString.replace(i, i + wordToFind.length(), wordToUse);
            
            int fromIndex = i + wordToFind.length() + 1;
            
            i = baseString.indexOf(wordToFind, fromIndex);
        }
        
        return baseString;
    }
    
}

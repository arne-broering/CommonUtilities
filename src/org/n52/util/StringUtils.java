package org.n52.util;
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
    public static StringBuilder replaceWordInString(StringBuilder baseString, String wordToFind, String wordToUse)
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

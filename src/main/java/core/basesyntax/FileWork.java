package core.basesyntax;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Arrays;
import java.util.Comparator;

public class FileWork {
    private static final int ZERO_INDEX = 0;
    private static final int ZERO = 0;
    private String[] content;

    public String[] readFromFile(String fileName) {
        getContent(fileName);

        boolean isEmpty = isFileEmpty();

        if (isEmpty) {
            return new String[ZERO_INDEX];
        }

        String[] rightContent = getRightContent();
        Arrays.sort(rightContent, Comparator.naturalOrder());
        return rightContent;
    }

    private void getContent(String fileName) {
        try (BufferedReader reader = new BufferedReader(new FileReader(fileName))) {
            String allContent = reader.readAllAsString();
            content = allContent.split("\\W+");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private String[] getRightContent() {
        int count = ZERO;
        char[] charArray;
        for (String word : content) {
            charArray = word.toCharArray();
            if (Character.toLowerCase(charArray[ZERO_INDEX]) == 'w') {
                count++;
            }
        }

        String[] rightContent = new String[count];

        count = ZERO;
        for (String word : content) {
            charArray = word.toCharArray();
            if (Character.toLowerCase(charArray[ZERO_INDEX]) == 'w') {
                rightContent[count] = word.toLowerCase();
                count++;
            }
        }
        return rightContent;
    }

    private boolean isFileEmpty() {
        return content == null || content.length == 0
                || (content.length == 1 && content[0].isEmpty());
    }
}

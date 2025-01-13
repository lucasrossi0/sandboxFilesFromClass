/*
package dev.pronunciationAppBack;

import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class DataInitializer implements CommandLineRunner {
    private final WordService wordService;

    public DataInitializer(WordService wordService) {
        this.wordService = wordService;
    }

    @Override
    public void run(String... args) throws Exception {
        // Generate and add 100 fake words
        wordService.createAddFakerWords(10);
    }

}
*/

package dev.pronunciationAppBack;

import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import java.util.Arrays;
import java.util.List;

@Component
public class DataInitializer implements CommandLineRunner {
    private final WordService wordService;

    public DataInitializer(WordService wordService) {
        this.wordService = wordService;
    }

    @Override
    public void run(String... args) throws Exception {
        // List of words to fetch from the API
        List<String> wordsToFetch = Arrays.asList(
                "sun", "happy", "tree", "car", "sky",
                "airplane", "hot", "cold", "coffee", "water"
        );

        wordService.createMultipleWords(wordsToFetch);
    }
}
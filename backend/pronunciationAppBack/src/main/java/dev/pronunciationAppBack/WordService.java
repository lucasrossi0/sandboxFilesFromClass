/*
package dev.pronunciationAppBack;
import com.github.javafaker.Faker;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

@Service
public class WordService {

    @Autowired
    private WordRepository wordRepository;

    private Faker faker = new Faker(new Locale("pt-BR"));

    public void createAddFakerWords(int numberWords){
        List<Word> words = new ArrayList<>();

        for (int i = 0; i < numberWords; i++) {
            // Create a new Word object with random values from Java Faker
            Word word = new Word();
            word.setWordName(faker.name().fullName());  // Random word
            word.setDefinition(faker.name().title());  // Random sentence as definition
            word.setPhoneticSpelling(faker.lorem().word());  // Random word as phonetic spelling
            word.setSentence(faker.lorem().word());  // Random sentence for example usage
            word.setActive(true);  // Set to true, or randomize if needed
            word.setLevel(faker.number().numberBetween(1, 5));  // Random level between 1 and 5
            words.add(word);
        }

        // Save the generated words to the database
        wordRepository.saveAll(words);
    }
}


package dev.pronunciationAppBack;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.http.ResponseEntity;
import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.List;
import java.util.ArrayList;

@Service
public class WordService {

    @Autowired
    private WordRepository wordRepository;
    private final RestTemplate restTemplate = new RestTemplate();
    private final String API_BASE_URL = "https://api.dictionaryapi.dev/api/v2/entries/en/";

    // Data classes to map API response
    static class DictionaryResponse {
        public String word;
        public List<Phonetic> phonetics;
        public List<Meaning> meanings;
    }

    static class Phonetic {
        public String text;
        public String audio;
    }

    static class Meaning {
        public String partOfSpeech;
        public List<Definition> definitions;
    }

    static class Definition {
        public String definition;
        public List<String> examples;
    }

    public Word createWordFromDictionaryAPI(String wordToLookup) {
        try {
            ResponseEntity<DictionaryResponse[]> response =
                    restTemplate.getForEntity(API_BASE_URL + wordToLookup, DictionaryResponse[].class);

            if (response.getBody() != null && response.getBody().length > 0) {
                DictionaryResponse dictResponse = response.getBody()[0];

                Word word = new Word();
                word.setWordName(dictResponse.word);

                // Get phonetic spelling if available
                if (dictResponse.phonetics != null && !dictResponse.phonetics.isEmpty()) {
                    word.setPhoneticSpelling(dictResponse.phonetics.get(0).text);
                }

                // Get first definition and example if available
                if (dictResponse.meanings != null && !dictResponse.meanings.isEmpty()) {
                    Meaning firstMeaning = dictResponse.meanings.get(0);
                    if (!firstMeaning.definitions.isEmpty()) {
                        Definition firstDef = firstMeaning.definitions.get(0);
                        word.setDefinition(firstDef.definition);

                        if (firstDef.examples != null && !firstDef.examples.isEmpty()) {
                            word.setSentence(firstDef.examples.get(0));
                        }
                    }
                }

                // Set default values
                word.setActive(true);
                word.setLevel(1); // You can adjust this based on your needs

                return wordRepository.save(word);
            }
        } catch (Exception e) {
            System.err.println("Error fetching word from API: " + e.getMessage());
        }
        return null;
    }

    public List<Word> createMultipleWords(List<String> words) {
        List<Word> createdWords = new ArrayList<>();
        for (String wordStr : words) {
            Word word = createWordFromDictionaryAPI(wordStr);
            if (word != null) {
                createdWords.add(word);
            }
        }
        return createdWords;
    }
}

 */

package dev.pronunciationAppBack;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.http.ResponseEntity;
import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.List;
import java.util.ArrayList;
import java.util.Optional;

@Service
public class WordService {

    @Autowired
    private WordRepository wordRepository;
    private final RestTemplate restTemplate = new RestTemplate();
    private final String API_BASE_URL = "https://api.dictionaryapi.dev/api/v2/entries/en/";

    // Updated data classes to better handle audio
    static class DictionaryResponse {
        public String word;
        public List<Phonetic> phonetics;
        public List<Meaning> meanings;
    }

    static class Phonetic {
        public String text;
        public String audio;
        public String sourceUrl;
    }

    static class Meaning {
        public String partOfSpeech;
        public List<Definition> definitions;
    }

    static class Definition {
        public String definition;
        public List<String> examples;
    }

    public Word createWordFromDictionaryAPI(String wordToLookup) {
        try {
            ResponseEntity<DictionaryResponse[]> response =
                    restTemplate.getForEntity(API_BASE_URL + wordToLookup, DictionaryResponse[].class);

            if (response.getBody() != null && response.getBody().length > 0) {
                DictionaryResponse dictResponse = response.getBody()[0];

                Word word = new Word();
                word.setWordName(dictResponse.word);

                // Enhanced phonetics handling
                if (dictResponse.phonetics != null && !dictResponse.phonetics.isEmpty()) {
                    // Find the first phonetic entry with non-empty audio
                    Optional<Phonetic> phoneticWithAudio = dictResponse.phonetics.stream()
                            .filter(p -> p.audio != null && !p.audio.isEmpty())
                            .findFirst();

                    // Set phonetic text if available
                    phoneticWithAudio.ifPresent(phonetic -> {
                        if (phonetic.text != null) {
                            word.setPhoneticSpelling(phonetic.text);
                        }
                        word.setAudioUrl(phonetic.audio);
                    });

                    // Fallback for phonetic spelling if no audio entry was found
                    if (word.getPhoneticSpelling() == null && !dictResponse.phonetics.isEmpty()) {
                        word.setPhoneticSpelling(dictResponse.phonetics.get(0).text);
                    }
                }

                // Get first definition and example if available
                if (dictResponse.meanings != null && !dictResponse.meanings.isEmpty()) {
                    Meaning firstMeaning = dictResponse.meanings.get(0);
                    if (!firstMeaning.definitions.isEmpty()) {
                        Definition firstDef = firstMeaning.definitions.get(0);
                        word.setDefinition(firstDef.definition);

                        if (firstDef.examples != null && !firstDef.examples.isEmpty()) {
                            word.setSentence(firstDef.examples.get(0));
                        }
                    }
                }

                // Set default values
                word.setActive(true);
                word.setLevel(1);

                return wordRepository.save(word);
            }
        } catch (Exception e) {
            System.err.println("Error fetching word from API: " + e.getMessage());
            e.printStackTrace();
        }
        return null;
    }

    public List<Word> createMultipleWords(List<String> words) {
        List<Word> createdWords = new ArrayList<>();
        for (String wordStr : words) {
            Word word = createWordFromDictionaryAPI(wordStr);
            if (word != null) {
                createdWords.add(word);
                // Add a small delay to avoid overwhelming the API
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                }
            }
        }
        return createdWords;
    }
}
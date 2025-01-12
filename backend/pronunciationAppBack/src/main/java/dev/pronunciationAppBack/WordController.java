package dev.pronunciationAppBack;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;


import static org.antlr.v4.runtime.tree.xpath.XPath.findAll;

@CrossOrigin
@RestController
public class WordController {


    @Autowired
    public WordRepository wordRepository;

    @Autowired
    private WordService wordService;

    @GetMapping("/hello")
    public String hello() {
        return "hello Emiliano, are you sleeping?";
    }

    @GetMapping("/words")
    public List<Word> getWords() {
            //
            List<Word> words = wordRepository.findAll();
            System.out.println("Number of words: " + words.size());
            for (Word word : words) {
                System.out.println("Word: " + word);
            }
            return words;
    }

    @PostMapping("/words/{word}")
    public Word addWord(@PathVariable String word) {
        return wordService.createWordFromDictionaryAPI(word);
    }

    @PostMapping("/words/batch")
    public List<Word> addMultipleWords(@RequestBody List<String> words) {
        return wordService.createMultipleWords(words);
    }
}
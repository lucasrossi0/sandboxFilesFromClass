/*

package dev.pronunciationAppBack;

import jakarta.persistence.*;

import java.util.UUID;

@Entity
@Table
public class Word {

    @Id
    private String id;
    @Column(name = "word_name")
    private String wordName;
    private String definition;
    private String phoneticSpelling;
    private String sentence;
    @Column(name = "is_active")
    private boolean isActive;
    private int level;

    public Word() {}

    public Word(String id, String wordName, String definition, String phoneticSpelling, String sentence, boolean isActive, int level) {
        this.id = id;
        this.wordName = wordName;
        this.definition = definition;
        this.phoneticSpelling = phoneticSpelling;
        this.sentence = sentence;
        this.isActive = isActive;
        this.level = level;
    }

    public String getId() {
        return id;
    }

    public String getWordName() {
        return wordName;
    }

    public String getDefinition() {
        return definition;
    }

    public String getPhoneticSpelling() {
        return phoneticSpelling;
    }

    public String getSentence() {
        return sentence;
    }

    public boolean isActive() {
        return isActive;
    }

    public int getLevel() {
        return level;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setWordName(String wordName) {
        this.wordName = wordName;
    }

    public void setDefinition(String definition) {
        this.definition = definition;
    }

    public void setPhoneticSpelling(String phoneticSpelling) {
        this.phoneticSpelling = phoneticSpelling;
    }

    public void setSentence(String sentence) {
        this.sentence = sentence;
    }

    public void setActive(boolean active) {
        isActive = active;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    @Override
    public String toString() {
        return "Word{" +
                "id='" + id + '\'' +
                ", wordName='" + wordName + '\'' +
                ", definition='" + definition + '\'' +
                ", phoneticSpelling='" + phoneticSpelling + '\'' +
                ", sentence='" + sentence + '\'' +
                ", isActive=" + isActive +
                ", level=" + level +
                '}';
    }

    @PrePersist
    public void generateId() {
        if (id == null) {
            id = UUID.randomUUID().toString();  // Generates a random UUID
        }
    }
}
*/

package dev.pronunciationAppBack;

import jakarta.persistence.*;
import java.util.UUID;

@Entity
@Table
public class Word {

    @Id
    private String id;
    @Column(name = "word_name")
    private String wordName;
    private String definition;
    private String phoneticSpelling;
    private String sentence;
    @Column(name = "is_active")
    private boolean isActive;
    private int level;
    @Column(name = "audio_url")
    private String audioUrl;  // New field for pronunciation audio

    public Word() {}

    public Word(String id, String wordName, String definition, String phoneticSpelling,
                String sentence, boolean isActive, int level, String audioUrl) {
        this.id = id;
        this.wordName = wordName;
        this.definition = definition;
        this.phoneticSpelling = phoneticSpelling;
        this.sentence = sentence;
        this.isActive = isActive;
        this.level = level;
        this.audioUrl = audioUrl;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getWordName() {
        return wordName;
    }

    public void setWordName(String wordName) {
        this.wordName = wordName;
    }

    public String getDefinition() {
        return definition;
    }

    public void setDefinition(String definition) {
        this.definition = definition;
    }

    public String getPhoneticSpelling() {
        return phoneticSpelling;
    }

    public void setPhoneticSpelling(String phoneticSpelling) {
        this.phoneticSpelling = phoneticSpelling;
    }

    public String getSentence() {
        return sentence;
    }

    public void setSentence(String sentence) {
        this.sentence = sentence;
    }

    public boolean isActive() {
        return isActive;
    }

    public void setActive(boolean active) {
        isActive = active;
    }

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    // Add getter and setter for audioUrl
    public String getAudioUrl() {
        return audioUrl;
    }

    public void setAudioUrl(String audioUrl) {
        this.audioUrl = audioUrl;
    }

    // Existing getters and setters remain the same...

    @Override
    public String toString() {
        return "Word{" +
                "id='" + id + '\'' +
                ", wordName='" + wordName + '\'' +
                ", definition='" + definition + '\'' +
                ", phoneticSpelling='" + phoneticSpelling + '\'' +
                ", sentence='" + sentence + '\'' +
                ", isActive=" + isActive +
                ", level=" + level +
                ", audioUrl='" + audioUrl + '\'' +
                '}';
    }

    @PrePersist
    public void generateId() {
        if (id == null) {
            id = UUID.randomUUID().toString();
        }
    }
}
package dev.pronunciationAppBack;

import org.springframework.data.jpa.repository.JpaRepository;


public interface WordRepository extends JpaRepository<Word, String> {
}

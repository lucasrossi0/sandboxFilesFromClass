
import React, { useState, useEffect } from 'react';
import axios from 'axios';

const Word = () => {
  const [words, setWords] = useState([]);
  const [isLoading, setIsLoading] = useState(true);

  useEffect(() => {
    const fetchWords = async () => {
      
        const response = await axios.get(
          'http://localhost:8080/words'
        );
        setWords(response.data);
        setIsLoading(false);
    
    };
    fetchWords();
  }, []);

  return (
    <>
      <h1>Words</h1>
      {isLoading ? (
        <p>Loading...</p>
      ) : (
        <ul>
          {words.map((word) => (
            <li key={word.id}>
              <strong>Word:</strong> {word.wordName} <br />
              <strong>Definition:</strong> {word.definition} <br />
              <strong>Phonetic Spelling:</strong> {word.phoneticSpelling} <br />
              <strong>Sentence:</strong> {word.sentence} <br />
              <strong>Level:</strong> {word.level} <br />
              <strong>Active:</strong> {word.isActive ? 'Yes' : 'No'}
            </li>
          ))}
        </ul>
      )}
    </>
  );
};

export default Word;

/*
import React, { useState, useEffect } from 'react';
import axios from 'axios';

const Word = () => {
  const [words, setWords] = useState([]);
  const [isLoading, setIsLoading] = useState(true);
  const [error, setError] = useState(null);

  useEffect(() => {
    const fetchWords = async () => {
      try {
        const response = await axios.get('http://localhost:8080/words');
        // Remove .docs since your API returns the array directly
        setWords(response.data);
        setIsLoading(false);
      } catch (error) {
        console.error('Error fetching words:', error);
        setError(error.message);
        setIsLoading(false);
      }
    };
    fetchWords();
  }, []);

  if (error) {
    return <div>Error: {error}</div>;
  }

  return (
    <>
      <h1>Words</h1>
      {isLoading ? (
        <p>Loading...</p>
      ) : (
        <ul>
          {words.map((word) => (
            <li key={word.id}>
              <strong>Word:</strong> {word.wordName} <br />
              <strong>Definition:</strong> {word.definition} <br />
              <strong>Phonetic Spelling:</strong> {word.phoneticSpelling} <br />
              <strong>Sentence:</strong> {word.sentence} <br />
              <strong>Level:</strong> {word.level} <br />
              <strong>Active:</strong> {word.isActive ? 'Yes' : 'No'}
            </li>
          ))}
        </ul>
      )}
    </>
  );
};

export default Word;
*/
/*

import Box from "@mui/material/Box";
import Card from "@mui/material/Card";
import CardActions from "@mui/material/CardActions";
import CardContent from "@mui/material/CardContent";
import Button from "@mui/material/Button";
import Typography from "@mui/material/Typography";
import CircularProgress from "@mui/material/CircularProgress";
import React, { useState, useEffect } from "react";
import axios from "axios";

const Word = () => {
  const [words, setWords] = useState([]);
  const [isLoading, setIsLoading] = useState(true);

  useEffect(() => {
    const fetchWords = async () => {
      const response = await axios.get("http://localhost:8080/words");
      setWords(response.data);
      setIsLoading(false);
    };
    fetchWords();
  }, []);

  return (
    <Box sx={{ padding: 3 }}>
      <Typography variant="h4" gutterBottom>
        Words List
      </Typography>

      {isLoading ? (
        <Box
          sx={{
            display: "flex",
            justifyContent: "center",
            alignItems: "center",
          }}
        >
          <CircularProgress />
        </Box>
      ) : (
        <Box
          sx={{
            display: "grid",
            gridTemplateColumns: "repeat(auto-fill, minmax(300px, 1fr))",
            gap: 3,
          }}
        >
          {words.map((word) => (
            <Card
              key={word.id}
              variant="outlined"
              sx={{ boxShadow: 3, borderRadius: 2 }}
            >
              <CardContent>
                <Typography
                  gutterBottom
                  variant="h6"
                  sx={{ color: "primary.main" }}
                >
                  {word.wordName}
                </Typography>
                <Typography
                  variant="body2"
                  sx={{ color: "text.secondary", mb: 2 }}
                >
                  {word.definition}
                </Typography>
                <Typography variant="body2" sx={{ color: "text.primary" }}>
                  Phonetic: {word.phoneticSpelling}
                  <br />
                  Sentence: {word.sentence}
                  <br />
                  Level: {word.level}
                </Typography>
              </CardContent>

              <CardActions>
                <Button
                  variant="contained"
                  color={word.isActive ? "success" : "error"}
                  size="small"
                  sx={{ width: "100%" }}
                >
                  {word.isActive ? "Active" : "Inactive"}
                </Button>
              </CardActions>
            </Card>
          ))}
        </Box>
      )}
    </Box>
  );
};

export default Word;



import Box from "@mui/material/Box";
import Card from "@mui/material/Card";
import CardActions from "@mui/material/CardActions";
import CardContent from "@mui/material/CardContent";
import Button from "@mui/material/Button";
import Typography from "@mui/material/Typography";

import React, { useState, useEffect } from "react";
import axios from "axios";

const Word = () => {
  const [words, setWords] = useState([]);
  const [isLoading, setIsLoading] = useState(true);

  useEffect(() => {
    const fetchWords = async () => {
      const response = await axios.get("http://localhost:8080/words");
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
            <Box sx={{ minWidth: 275 }}>
              <Card variant="outlined">
                <li key={word.id}>
                  <React.Fragment>
                    <CardContent>
                      <Typography
                        gutterBottom
                        sx={{ color: "text.secondary", fontSize: 14 }}
                      >
                        {word.wordName}
                      </Typography>
                      <Typography sx={{ color: "text.secondary", mb: 1.5 }}>
                        {word.definition}
                      </Typography>
                      <Typography variant="body2">
                        {word.phoneticSpelling}
                        <br></br>
                        {word.sentence}
                        <br></br>
                        {word.level}
                      </Typography>
                    </CardContent>
                    <CardActions>
                      <Button size="small">
                        {word.isActive ? "Yes" : "No"}
                      </Button>
                    </CardActions>
                  </React.Fragment>
                </li>
              </Card>
            </Box>
          ))}
        </ul>
      )}
    </>
  );
};

export default Word;

/*
              <strong>Word:</strong> {word.wordName} <br />
              <strong>Definition:</strong> {word.definition} <br />
              <strong>Phonetic Spelling:</strong> {word.phoneticSpelling} <br />
              <strong>Sentence:</strong> {word.sentence} <br />
              <strong>Level:</strong> {word.level} <br />
              <strong>Active:</strong> {word.isActive ? "Yes" : "No"}
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
import Box from "@mui/material/Box";
import Card from "@mui/material/Card";
import CardActions from "@mui/material/CardActions";
import CardContent from "@mui/material/CardContent";
import Button from "@mui/material/Button";
import Typography from "@mui/material/Typography";
import CircularProgress from "@mui/material/CircularProgress";
import VolumeUpIcon from "@mui/icons-material/VolumeUp";
import React, { useState, useEffect } from "react";
import axios from "axios";

const Word = () => {
  const [words, setWords] = useState([]);
  const [isLoading, setIsLoading] = useState(true);
  const [playingAudio, setPlayingAudio] = useState(null);

  useEffect(() => {
    const fetchWords = async () => {
      const response = await axios.get("http://localhost:8080/words");
      setWords(response.data);
      setIsLoading(false);
    };
    fetchWords();
  }, []);

  const handlePlayPronunciation = async (audioUrl) => {
    try {
      if (playingAudio) {
        playingAudio.pause();
        playingAudio.currentTime = 0;
      }
      const audio = new Audio(audioUrl);
      setPlayingAudio(audio);
      await audio.play();
    } catch (error) {
      console.error("Error playing pronunciation:", error);
    }
  };

  return (
    <Box sx={{ padding: 3 }}>
      <Typography variant="h4" gutterBottom>
        Words List
      </Typography>

      {isLoading ? (
        <Box
          sx={{
            display: "flex",
            justifyContent: "center",
            alignItems: "center",
          }}
        >
          <CircularProgress />
        </Box>
      ) : (
        <Box
          sx={{
            display: "grid",
            gridTemplateColumns: "repeat(auto-fill, minmax(300px, 1fr))",
            gap: 3,
          }}
        >
          {words.map((word) => (
            <Card
              key={word.id}
              variant="outlined"
              sx={{ boxShadow: 3, borderRadius: 2 }}
            >
              <CardContent>
                <Typography
                  gutterBottom
                  variant="h6"
                  sx={{ color: "primary.main" }}
                >
                  {word.wordName}
                </Typography>
                <Typography
                  variant="body2"
                  sx={{ color: "text.secondary", mb: 2 }}
                >
                  {word.definition}
                </Typography>
                <Typography variant="body2" sx={{ color: "text.primary" }}>
                  Phonetic: {word.phoneticSpelling}
                  <br />
                  Sentence: {word.sentence}
                  <br />
                  Level: {word.level}
                </Typography>
              </CardContent>

              <CardActions sx={{ display: "flex", gap: 1, px: 2, pb: 2 }}>
                <Button
                  variant="contained"
                  color={word.isActive ? "success" : "error"}
                  size="small"
                  sx={{ flexGrow: 1 }}
                >
                  {word.isActive ? "Active" : "Inactive"}
                </Button>
                <Button
                  variant="contained"
                  color="primary"
                  size="small"
                  disabled={!word.audioUrl}
                  onClick={() =>
                    word.audioUrl && handlePlayPronunciation(word.audioUrl)
                  }
                  startIcon={<VolumeUpIcon />}
                >
                  Play
                </Button>
              </CardActions>
            </Card>
          ))}
        </Box>
      )}
    </Box>
  );
};

export default Word;

CREATE TABLE artists_languages (
    id INT NOT NULL PRIMARY KEY AUTO_INCREMENT,
    artist_id INT,
    language_id INT,
    FOREIGN KEY (artist_id) REFERENCES artists(id_artists),
    FOREIGN KEY (language_id) REFERENCES languages(id_languages)
);
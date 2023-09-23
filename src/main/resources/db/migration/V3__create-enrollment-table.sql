CREATE TABLE artists_genres (
    id INT NOT NULL PRIMARY KEY AUTO_INCREMENT,
    artist_id INT,
    genre_id INT,
    FOREIGN KEY (artist_id) REFERENCES artists(id_artists),
    FOREIGN KEY (genre_id) REFERENCES genres(id_genres)
);
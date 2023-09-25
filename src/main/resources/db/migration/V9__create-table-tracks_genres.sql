CREATE TABLE tracks_genres (
    id INT NOT NULL PRIMARY KEY AUTO_INCREMENT,
    track_id INT,
    genre_id INT,
    FOREIGN KEY (track_id) REFERENCES tracks(id_tracks),
    FOREIGN KEY (genre_id) REFERENCES genres(id_genres)
);
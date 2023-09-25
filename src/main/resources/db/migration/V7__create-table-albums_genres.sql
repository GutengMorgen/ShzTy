CREATE TABLE albums_genres (
    id INT NOT NULL PRIMARY KEY AUTO_INCREMENT,
    album_id INT,
    genre_id INT,
    FOREIGN KEY (album_id) REFERENCES albums(id_albums),
    FOREIGN KEY (genre_id) REFERENCES genres(id_genres)
);
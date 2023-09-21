CREATE TABLE history(
    id_history int not null primary key auto_increment,
    entity_type varchar(255),
    id_entity int,
    action_type varchar(255),
    action_date DATE
);

CREATE TABLE languages(
    id_languages int not null primary key auto_increment,
    name varchar(255)
);

CREATE TABLE genres(
    id_genres int not null primary key auto_increment,
    name varchar(255)
);

CREATE TABLE album_formats(
    id_album_formats int not null primary key auto_increment,
    name varchar(255)
);

CREATE TABLE record_label(
    id_record_label int not null primary key auto_increment,
    name varchar(255),
    foundation_date DATE
);

CREATE TABLE playLists(
    id_playLists int not null primary key auto_increment,
    tile varchar(255)
);

-- tablas secundarias

CREATE TABLE reviews(
    id_reviews int not null primary key auto_increment,
    reviews_date DATE,
    discovery_by varchar(255),
    discovery_date DATE,
    rating_mode varchar(255),
    best_moments varchar(255),
    notes TEXT
);

CREATE TABLE credits(
    id_credits int not null primary key auto_increment,
    id_record_label int,
    FOREIGN KEY(id_record_label) REFERENCES record_label(id_record_label),
    info TEXT
);

-- tablas pricipales

CREATE TABLE artists (
    id_artists int not null primary key auto_increment,
    name varchar(255),
    born_date DATE,
    gender varchar(255),
    country varchar(255),
    biography TEXT,
    id_languages int,
    FOREIGN KEY(id_languages) REFERENCES languages(id_languages),
    id_genres int,
    FOREIGN KEY(id_genres) REFERENCES genres(id_genres)
);

CREATE TABLE albums(
    id_albums int not null primary key auto_increment,
    title varchar(255),
    release_date DATE,
    id_artists int,
    FOREIGN KEY(id_artists) REFERENCES artists(id_artists),
    id_genres int,
    FOREIGN KEY(id_genres) REFERENCES genres(id_genres),
    id_album_formats int,
    FOREIGN KEY(id_album_formats) REFERENCES album_formats(id_album_formats)
);

CREATE TABLE tracks(
    id_tracks int not null primary key auto_increment,
    title varchar(255),
    release_date DATE,
    play_time int,
    notes TEXT,
    id_genres int,
    FOREIGN KEY(id_genres) REFERENCES genres(id_genres),
    id_credits int,
    FOREIGN KEY(id_credits) REFERENCES credits(id_credits),
    id_reviews int,
    FOREIGN KEY(id_reviews) REFERENCES reviews(id_reviews),
    id_albums int,
    FOREIGN KEY(id_albums) REFERENCES albums(id_albums),
    id_playlists int,
    FOREIGN KEY(id_playlists) REFERENCES playLists(id_playlists)
);

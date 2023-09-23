ALTER TABLE artists DROP FOREIGN KEY artists_ibfk_1;
ALTER TABLE artists DROP FOREIGN KEY artists_ibfk_2;

ALTER TABLE artists DROP COLUMN id_languages, DROP COLUMN id_genres;
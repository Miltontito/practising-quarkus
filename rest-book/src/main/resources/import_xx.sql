-- Insert data into Categoria
INSERT INTO Categoria (id, name, parentCategoria_id) VALUES
(1, 'Ficción', NULL),
(2, 'No Ficción', NULL),
(3, 'Ciencia Ficción', 1),
(4, 'Fantasía', 1),
(5, 'Historia', 2),
(6, 'Biografía', 2),
(7, 'Space Opera', 3),
(8, 'High Fantasy', 4);

-- Insert data into Book
INSERT INTO Book (id, title, isbn_13, isbn_10, year_of_publication, nb_of_pages, rank, price, small_image_url, medium_image_url, description, CATEGORY_ID) VALUES
(1, 'El Juego de Ender', '9780312932080', '0312932081', 1985, 324, 9, 15.99, 'http://example.com/small1.jpg', 'http://example.com/medium1.jpg', 'Un joven prodigio es entrenado para ser un comandante militar en una guerra intergaláctica.', 3),
(2, 'Cien Años de Soledad', '9780307474728', '0307474720', 1967, 417, 10, 19.99, 'http://example.com/small2.jpg', 'http://example.com/medium2.jpg', 'La historia de la familia Buendía en el pueblo ficticio de Macondo.', 1),
(3, 'Dune', '9780441013593', '0441013597', 1965, 412, 10, 18.99, 'http://example.com/small3.jpg', 'http://example.com/medium3.jpg', 'La historia de Paul Atreides y su viaje para convertirse en el gobernante de Arrakis.', 7),
(4, 'El Señor de los Anillos', '9780544003415', '0544003411', 1954, 1216, 10, 25.99, 'http://example.com/small4.jpg', 'http://example.com/medium4.jpg', 'Una épica aventura en la Tierra Media para destruir el Anillo Único.', 8),
(5, 'Historia de la Segunda Guerra Mundial', '9780306808380', '0306808380', 1989, 600, 8, 20.99, 'http://example.com/small5.jpg', 'http://example.com/medium5.jpg', 'Un análisis detallado de los eventos de la Segunda Guerra Mundial.', 5),
(6, 'La Autobiografía de Benjamin Franklin', '9780486290737', '0486290735', 1791, 144, 7, 9.99, 'http://example.com/small6.jpg', 'http://example.com/medium6.jpg', 'La vida de uno de los padres fundadores de Estados Unidos, contada por él mismo.', 6);

-- Insert data into Autor
INSERT INTO Autor (id, nombre, apellido, nacionalidad) VALUES
(1, 'Orson', 'Scott Card', 'Americano'),
(2, 'Gabriel', 'García Márquez', 'Colombiano'),
(3, 'Frank', 'Herbert', 'Americano'),
(4, 'J.R.R.', 'Tolkien', 'Británico'),
(5, 'Winston', 'Churchill', 'Británico'),
(6, 'Benjamin', 'Franklin', 'Americano');

-- Insert data into Comentario
INSERT INTO Comentario (id, email_creador, texto, puntuacion) VALUES
(1, 'lector1@example.com', 'Una obra maestra de la ciencia ficción.', 9),
(2, 'lector2@example.com', 'Un clásico imperdible de la literatura latinoamericana.', 10),
(3, 'lector3@example.com', 'Una de las mejores novelas de ciencia ficción jamás escritas.', 10),
(4, 'lector4@example.com', 'Un mundo increíblemente detallado y personajes inolvidables.', 10),
(5, 'lector5@example.com', 'Un análisis profundo y detallado de la guerra.', 8),
(6, 'lector6@example.com', 'Inspirador y fascinante, una mirada a la mente de un genio.', 7);

-- Insert data into author_book (junction table for many-to-many relationship)
INSERT INTO author_book (author_id, book_id) VALUES
(1, 1),
(2, 2),
(3, 3),
(4, 4),
(5, 5),
(6, 6),
(4, 1); -- Si existe una coautoría, agregar como ejemplo adicional
-- Insert data into Categoria
INSERT INTO Categoria (id, name, parentCategoria_id) VALUES
(1, 'Ficción', NULL),
(2, 'No Ficción', NULL),
(3, 'Ciencia Ficción', 1),
(4, 'Fantasía', 1);

-- Insert data into Book
INSERT INTO Book (id, title, isbn_13, isbn_10, year_of_publication, nb_of_pages, rank, price, small_image_url, medium_image_url, description, category_id) VALUES
(1, 'El Juego de Ender', '9780312932080', '0312932081', 1985, 324, 9, 15.99, 'http://example.com/small1.jpg', 'http://example.com/medium1.jpg', 'Un joven prodigio es entrenado para ser un comandante militar en una guerra intergaláctica.', 3),
(2, 'Cien Años de Soledad', '9780307474728', '0307474720', 1967, 417, 10, 19.99, 'http://example.com/small2.jpg', 'http://example.com/medium2.jpg', 'La historia de la familia Buendía en el pueblo ficticio de Macondo.', 1);

-- Insert data into Autor
INSERT INTO Autor (id, nombre, apellido, nacionalidad, book_id) VALUES
(1, 'Orson', 'Scott Card', 'Americano', 1),
(2, 'Gabriel', 'García Márquez', 'Colombiano', 2);

-- Insert data into Comentario
INSERT INTO Comentario (id, email_creador, texto, puntuacion) VALUES
(1, 'lector1@example.com', 'Una obra maestra de la ciencia ficción.', 9),
(2, 'lector2@example.com', 'Un clásico imperdible de la literatura latinoamericana.', 10);

-- Relate books to authors (assumes many-to-many relation table, e.g., Book_Author)
-- INSERT INTO Book_Author (book_id, autor_id) VALUES
-- (1, 1),
-- (2, 2);






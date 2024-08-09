INSERT INTO Category (id, name, parentCategory_id) VALUES
(1, 'Ficción', NULL),
(2, 'No Ficción', NULL),
(3, 'Ciencia Ficción', 1),
(4, 'Fantasía', 1),
(5, 'Historia', 2),
(6, 'Biografía', 2),
(7, 'Space Opera', 3),
(8, 'High Fantasy', 4),
(9, 'Misterio', 1),
(10, 'Thriller', 9),
(11, 'Aventura', 1),
(12, 'Romance', 1),
(13, 'Literatura Clásica', 1),
(14, 'Poesía', 1),
(15, 'Ciencia', 2),
(16, 'Autobiografía', 6),
(17, 'Filosofía', 2),
(18, 'Psicología', 2),
(19, 'Economía', 2),
(20, 'Política', 2);
SELECT setval('Category_id_seq', 20);


INSERT INTO Author (id, name, surName, nationality) VALUES
(1, 'J.K.', 'Rowling', 'British'),
(2, 'George', 'Orwell', 'British'),
(3, 'F. Scott', 'Fitzgerald', 'American'),
(4, 'Jane', 'Austen', 'British'),
(5, 'Mark', 'Twain', 'American'),
(6, 'Ernest', 'Hemingway', 'American'),
(7, 'Charles', 'Dickens', 'British'),
(8, 'Leo', 'Tolstoy', 'Russian'),
(9, 'Fyodor', 'Dostoevsky', 'Russian'),
(10, 'Gabriel', 'García Márquez', 'Colombian'),
(11, 'Agatha', 'Christie', 'British'),
(12, 'Isaac', 'Asimov', 'American'),
(13, 'Arthur', 'C. Clarke', 'British'),
(14, 'H.G.', 'Wells', 'British'),
(15, 'Mary', 'Shelley', 'British'),
(16, 'J.R.R.', 'Tolkien', 'British'),
(17, 'Stephen', 'King', 'American'),
(18, 'Edgar', 'Allan Poe', 'American'),
(19, 'Homer', 'Ancient', 'Greek'),
(20, 'Dante', 'Alighieri', 'Italian');
SELECT setval('Author_id_seq', 20);

INSERT INTO Book (id, category_id, title, isbn_13, isbn_10, year_Of_Publication, nb_Of_Pages, rank, price, small_Image_Url, medium_Image_Url, description) VALUES
(1, 8, 'Harry Potter and the Philosophers Stone', '9780747532699', '0747532699', 1997, 223, 10, 19.99, 'http://example.com/small1.jpg', 'http://example.com/medium1.jpg', 'First book in the Harry Potter series.'),
(2, 1, '1984', '9780451524935', '0451524934', 1949, 328, 9, 14.99, 'http://example.com/small2.jpg', 'http://example.com/medium2.jpg', 'Dystopian novel set in a totalitarian society.'),
(3, 13,'The Great Gatsby', '9780743273565', '0743273567', 1925, 180, 8, 10.99, 'http://example.com/small3.jpg', 'http://example.com/medium3.jpg', 'A novel about the American dream and the roaring twenties.'),
(4, 12,'Pride and Prejudice', '9781503290563', '1503290565', 1813, 279, 9, 12.99, 'http://example.com/small4.jpg', 'http://example.com/medium4.jpg', 'A classic novel about manners and marriage in early 19th century England.'),
(5, 11,'Adventures of Huckleberry Finn', '9780486280615', '0486280616', 1884, 366, 8, 11.99, 'http://example.com/small5.jpg', 'http://example.com/medium5.jpg', 'A novel about the adventures of a young boy and a runaway slave.'),
(6, 13,'The Old Man and the Sea', '9780684801223', '0684801221', 1952, 127, 7, 9.99, 'http://example.com/small6.jpg', 'http://example.com/medium6.jpg', 'A short novel about an old fishermans struggle with a giant marlin.'),
(7, 13,'Great Expectations', '9780141439563', '0141439564', 1861, 505, 9, 15.99, 'http://example.com/small7.jpg', 'http://example.com/medium7.jpg', 'A novel about the growth and personal development of an orphan named Pip.'),
(8, 5,'War and Peace', '9781400079988', '1400079985', 1869, 1225, 10, 24.99, 'http://example.com/small8.jpg', 'http://example.com/medium8.jpg', 'A novel that intertwines the lives of private and public individuals during the time of the Napoleonic wars.'),
(9, 18,'Crime and Punishment', '9780486415871', '0486415872', 1866, 430, 9, 13.99, 'http://example.com/small9.jpg', 'http://example.com/medium9.jpg', 'A novel about the mental anguish and moral dilemmas of an impoverished ex-student.'),
(10, 17,'One Hundred Years of Solitude', '9780060883287', '0060883286', 1967, 417, 10, 18.99, 'http://example.com/small10.jpg', 'http://example.com/medium10.jpg', 'A multi-generational story of the Buendía family and the town of Macondo.'),
(11, 9, 'Murder on the Orient Express', '9780062693663', '0062693662', 1934, 256, 8, 15.99, 'http://example.com/small11.jpg', 'http://example.com/medium11.jpg', 'A mystery novel featuring Hercule Poirot.'),
(12, 2, 'Foundation', '9780553293357', '0553293354', 1951, 296, 9, 14.99, 'http://example.com/small12.jpg', 'http://example.com/medium12.jpg', 'First book in the Foundation series.'),
(13, 3, '2001: A Space Odyssey', '9780451457998', '0451457994', 1968, 297, 9, 15.99, 'http://example.com/small13.jpg', 'http://example.com/medium13.jpg', 'A science fiction novel about space exploration and evolution.'),
(14, 3, 'The War of the Worlds', '9780141441030', '0141441038', 1898, 192, 8, 10.99, 'http://example.com/small14.jpg', 'http://example.com/medium14.jpg', 'A science fiction novel about an alien invasion of Earth.'),
(15, 4, 'Frankenstein', '9780486282114', '0486282112', 1818, 280, 9, 12.99, 'http://example.com/small15.jpg', 'http://example.com/medium15.jpg', 'A novel about a scientist who creates a sapient creature.'),
(16, 8, 'The Hobbit', '9780345339683', '0345339681', 1937, 310, 10, 16.99, 'http://example.com/small16.jpg', 'http://example.com/medium16.jpg', 'A fantasy novel about the adventure of a hobbit named Bilbo Baggins.'),
(17, 10, 'The Shining', '9780307743657', '0307743659', 1977, 447, 9, 14.99, 'http://example.com/small17.jpg', 'http://example.com/medium17.jpg', 'A horror novel about a haunted hotel and its caretaker.'),
(18, 6, 'The Raven', '9780486266855', '0486266850', 1845, 64, 8, 5.99, 'http://example.com/small18.jpg', 'http://example.com/medium18.jpg', 'A narrative poem about a mysterious ravens visit to a grieving man.'),
(19, 19, 'The Iliad', '9780140445923', '0140445927', -750, 704, 10, 12.99, 'http://example.com/small19.jpg', 'http://example.com/medium19.jpg', 'An epic poem about the Trojan War.'),
(20, 16, 'The Divine Comedy', '9780140448955', '0140448950', 1320, 798, 10, 15.99, 'http://example.com/small20.jpg', 'http://example.com/medium20.jpg', 'An epic poem about the journey through Hell, Purgatory, and Paradise.');
SELECT setval('Book_id_seq', 20);

INSERT INTO Comment (id, email_creador, text, score) VALUES
(1, 'lector1@example.com', 'Una obra maestra de la ciencia ficción.', 9),
(2, 'lector2@example.com', 'Un clásico imperdible de la literatura latinoamericana.', 10),
(3, 'lector3@example.com', 'Una de las mejores novelas de ciencia ficción jamás escritas.', 10),
(4, 'lector4@example.com', 'Un mundo increíblemente detallado y personajes inolvidables.', 10),
(5, 'lector5@example.com', 'Un análisis profundo y detallado de la guerra.', 8),
(6, 'lector6@example.com', 'Inspirador y fascinante, una mirada a la mente de un genio.', 7),
(7, 'lector7@example.com', 'Un misterio intrigante con un final sorprendente.', 8),
(8, 'lector8@example.com', 'La saga de la Fundación es imprescindible para los fans de la ciencia ficción.', 9),
(9, 'lector9@example.com', 'Una obra que cambia la forma en que ves el universo.', 10),
(10, 'lector10@example.com', 'Un relato aterradoramente realista de una invasión alienígena.', 8),
(11, 'lector11@example.com', 'Una novela que explora los límites de la ciencia y la moralidad.', 9),
(12, 'lector12@example.com', 'Una aventura fantástica llena de personajes inolvidables.', 10),
(13, 'lector13@example.com', 'El horror psicológico en su máxima expresión.', 9),
(14, 'lector14@example.com', 'Una historia oscura y poética.', 8),
(15, 'lector15@example.com', 'Un poema épico que trasciende el tiempo.', 10),
(16, 'lector16@example.com', 'Un viaje literario a través de los reinos de lo espiritual.', 9),
(17, 'lector17@example.com', 'Una obra profunda y filosófica.', 8),
(18, 'lector18@example.com', 'Un relato impresionante de la condición humana.', 9),
(19, 'lector19@example.com', 'Una exploración aterradora del miedo y la locura.', 8),
(20, 'lector20@example.com', 'Un épico relato de guerra y honor.', 9);
SELECT setval('Comment_id_seq', 20);

INSERT INTO author_book (book_id, author_id) VALUES
(1, 1), -- (Harry Potter, J.K. Rowling)
(1, 17), -- (Harry Potter, Stephen King)
(2, 2), -- George Orwell
(3, 3), -- F. Scott Fitzgerald
(4, 4), -- Jane Austen
(5, 5), -- Mark Twain
(6, 6), -- Ernest Hemingway
(7, 7), -- Charles Dickens
(8, 8), -- Leo Tolstoy
(9, 9), -- Fyodor Dostoevsky
(10, 10), -- Gabriel García Márquez
(11, 11), -- Agatha Christie
(12, 12), -- Isaac Asimov
(13, 13), -- Arthur C. Clarke
(14, 14), -- H.G. Wells
(15, 15), -- Mary Shelley
(16, 16), -- J.R.R. Tolkien
(17, 17), -- Stephen King
(18, 18), -- Edgar Allan Poe
(19, 19), -- Homer
(20, 20); -- Dante Alighieri
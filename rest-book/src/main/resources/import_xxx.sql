INSERT INTO Category (id, name, parentCategory_id) VALUES
(1, 'Ficción', NULL),
(2, 'No Ficción', NULL),
(3, 'Ciencia Ficción', 1),
(4, 'Fantasía', 1),
(5, 'Historia', 2),
(6, 'Biografía', 2),
(7, 'Space Opera', 3),
(8, 'High Fantasy', 4);

-- Insertando datos en la tabla Author
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
(10, 'Gabriel', 'García Márquez', 'Colombian');

-- Insertando datos en la tabla Book
INSERT INTO Book (id, category_id, title, isbn_13, isbn_10, year_Of_Publication, nb_Of_Pages, rank, price, small_Image_Url, medium_Image_Url, description) VALUES
(1, 8, 'Harry Potter and the Philosophers Stone', '9780747532699', '0747532699', 1997, 223, 10, 19.99, 'http://example.com/small1.jpg', 'http://example.com/medium1.jpg', 'First book in the Harry Potter series.'),
(2, 1, '1984', '9780451524935', '0451524934', 1949, 328, 9, 14.99, 'http://example.com/small2.jpg', 'http://example.com/medium2.jpg', 'Dystopian novel set in a totalitarian society.'),
(3, 1,'The Great Gatsby', '9780743273565', '0743273567', 1925, 180, 8, 10.99, 'http://example.com/small3.jpg', 'http://example.com/medium3.jpg', 'A novel about the American dream and the roaring twenties.'),
(4, 1,'Pride and Prejudice', '9781503290563', '1503290565', 1813, 279, 9, 12.99, 'http://example.com/small4.jpg', 'http://example.com/medium4.jpg', 'A classic novel about manners and marriage in early 19th century England.'),
(5, 1,'Adventures of Huckleberry Finn', '9780486280615', '0486280616', 1884, 366, 8, 11.99, 'http://example.com/small5.jpg', 'http://example.com/medium5.jpg', 'A novel about the adventures of a young boy and a runaway slave.'),
(6, 1,'The Old Man and the Sea', '9780684801223', '0684801221', 1952, 127, 7, 9.99, 'http://example.com/small6.jpg', 'http://example.com/medium6.jpg', 'A short novel about an old fishermans struggle with a giant marlin.'),
(7, 1,'Great Expectations', '9780141439563', '0141439564', 1861, 505, 9, 15.99, 'http://example.com/small7.jpg', 'http://example.com/medium7.jpg', 'A novel about the growth and personal development of an orphan named Pip.'),
(8, 1,'War and Peace', '9781400079988', '1400079985', 1869, 1225, 10, 24.99, 'http://example.com/small8.jpg', 'http://example.com/medium8.jpg', 'A novel that intertwines the lives of private and public individuals during the time of the Napoleonic wars.'),
(9, 1,'Crime and Punishment', '9780486415871', '0486415872', 1866, 430, 9, 13.99, 'http://example.com/small9.jpg', 'http://example.com/medium9.jpg', 'A novel about the mental anguish and moral dilemmas of an impoverished ex-student.'),
(10, 1,'One Hundred Years of Solitude', '9780060883287', '0060883286', 1967, 417, 10, 18.99, 'http://example.com/small10.jpg', 'http://example.com/medium10.jpg', 'A multi-generational story of the Buendía family and the town of Macondo.');

-- Insertando datos en la tabla de unión author_book
INSERT INTO author_book (book_id, author_id) VALUES
(1, 1), -- J.K. Rowling
(2, 2), -- George Orwell
(3, 3), -- F. Scott Fitzgerald
(4, 4), -- Jane Austen
(5, 5), -- Mark Twain
(6, 6), -- Ernest Hemingway
(7, 7), -- Charles Dickens
(8, 8), -- Leo Tolstoy
(9, 9), -- Fyodor Dostoevsky
(10, 10), -- Gabriel García Márquez
(8, 9), -- Leo Tolstoy and Fyodor Dostoevsky (War and Peace)
(9, 8); -- Fyodor Dostoevsky (Crime and Punishment)
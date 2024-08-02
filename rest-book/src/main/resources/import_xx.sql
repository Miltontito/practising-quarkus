INSERT INTO Categorias(id,
                       name,
                       subcategorias_id)
VALUES(998,
       'Comedia romantica',
       NULL);

INSERT INTO Categorias(id,
                       name,
                       subcategorias_id)
VALUES(997,
       'Romance',
       998);


INSERT INTO Book(id,
                isbn_13,
                title,
                rank,
                small_image_url,
                medium_image_url,
                price,
                nb_of_pages,
                year_of_publication,
                description,
                subcategorias_id)
VALUES ( 997,
        '9781980399025',
        'Understanding Bean Validation',
        9,
        'https://images-na.ssl-images-amazon.com/images/I/31fHenHChZL._SL160_.jpg',
        'https://images-na.ssl-images-amazon.com/images/I/31fHenHChZL.jpg',
        9.99,
        129,
        2018,
        'Text',
        997);

INSERT INTO Autores(id,
                    nombre,
                    apellido,
                    nacionalidad,
                    book)
VALUES (997,
        'Antonio',
        'Goncalves',
        'Nacionalidad',
        997);






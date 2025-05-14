-- Insert Publishers
INSERT INTO publishers (name, address, contact_email) VALUES
                                                          ('Scholastic', '557 Broadway, New York, NY', 'contact@scholastic.com'),
                                                          ('Penguin Books', '123 Penguin Ave, London', 'info@penguin.com'),
                                                          ('HarperCollins', '195 Broadway, New York, NY', 'support@harpercollins.com');

-- Insert Authors
INSERT INTO authors (name, nationality, bio) VALUES
                                                 ('J.K. Rowling', 'British', 'Author of the Harry Potter series'),
                                                 ('George Orwell', 'British', 'Known for dystopian novels'),
                                                 ('Harper Lee', 'American', 'Author of To Kill a Mockingbird'),
                                                 ('J.R.R. Tolkien', 'British', 'Creator of Middle-earth'),
                                                 ('Agatha Christie', 'British', 'Master of detective fiction'),
                                                 ('Dan Brown', 'American', 'Thriller novelist'),
                                                 ('Stephen King', 'American', 'Master of horror'),
                                                 ('Margaret Atwood', 'Canadian', 'Dystopian fiction author');

-- Insert Categories
INSERT INTO categories (name) VALUES
                                  ('Fiction'),
                                  ('Non-Fiction'),
                                  ('Science Fiction'),
                                  ('Fantasy'),
                                  ('Mystery'),
                                  ('Thriller'),
                                  ('Horror'),
                                  ('Classic'),
                                  ('Dystopian');

-- Category Hierarchy (Fiction as parent category)
INSERT INTO category_hierarchy VALUES
                                   (4, 1), -- Fantasy under Fiction
                                   (3, 1), -- Sci-Fi under Fiction
                                   (9, 1), -- Dystopian under Fiction
                                   (8, 1); -- Classic under Fiction

-- Insert Books (10 books with different categories and publishers)
INSERT INTO books (title, isbn, year, quantity, edition, language, summary, publisher_id) VALUES
                                                                                              ('Harry Potter and the Philosopher''s Stone', '978-0747532743', 1997, 5, '1st', 'EN', 'The boy who lived starts his journey', 1),
                                                                                              ('1984', '978-0451524935', 1949, 3, 'Reissue', 'EN', 'Dystopian society under Big Brother', 2),
                                                                                              ('The Hobbit', '978-0547928227', 1937, 4, 'Illustrated', 'EN', 'Bilbo''s adventure with dwarves', 3),
                                                                                              ('Murder on the Orient Express', '978-0062073495', 1934, 2, 'Reprint', 'EN', 'Poirot solves a train mystery', 2),
                                                                                              ('The Da Vinci Code', '978-0307474278', 2003, 6, 'Special', 'EN', 'Symbologist uncovers religious secret', 3),
                                                                                              ('To Kill a Mockingbird', '978-0446310789', 1960, 4, '35th Anniversary', 'EN', 'Racial injustice in Alabama', 1),
                                                                                              ('The Shining', '978-0307743657', 1977, 3, '1st', 'EN', 'Haunted hotel drives man mad', 2),
                                                                                              ('The Handmaid''s Tale', '978-0385490818', 1985, 5, 'Movie Tie-in', 'EN', 'Dystopian patriarchal society', 3),
                                                                                              ('The Lord of the Rings', '978-0544003415', 1954, 2, 'Deluxe', 'EN', 'Epic quest to destroy the One Ring', 1),
                                                                                              ('And Then There Were None', '978-0062073488', 1939, 4, 'Reprint', 'EN', 'Ten strangers on a deadly island', 2);

-- Link Books to Authors
INSERT INTO book_authors VALUES
                             (1, 1),  -- J.K. Rowling -> Harry Potter
                             (2, 2),  -- George Orwell -> 1984
                             (4, 3),  -- J.R.R. Tolkien -> The Hobbit
                             (5, 4),  -- Agatha Christie -> Orient Express
                             (6, 5),  -- Dan Brown -> Da Vinci Code
                             (3, 6),  -- Harper Lee -> Mockingbird
                             (7, 7),  -- Stephen King -> The Shining
                             (8, 8),  -- Margaret Atwood -> Handmaid's Tale
                             (4, 9),  -- J.R.R. Tolkien -> LOTR
                             (5, 10); -- Agatha Christie -> And Then There Were None

-- Link Books to Categories
INSERT INTO book_categories VALUES
                                (1, 4),  -- Harry Potter -> Fantasy
                                (2, 9),  -- 1984 -> Dystopian
                                (3, 4),  -- The Hobbit -> Fantasy
                                (4, 5),  -- Orient Express -> Mystery
                                (5, 6),  -- Da Vinci Code -> Thriller
                                (6, 8),  -- Mockingbird -> Classic
                                (7, 7),  -- The Shining -> Horror
                                (8, 9),  -- Handmaid's Tale -> Dystopian
                                (9, 4),  -- LOTR -> Fantasy
                                (10, 5); -- And Then... -> Mystery

-- Insert Members
INSERT INTO members (full_name, email, max_borrow_limit) VALUES
                                                             ('John Smith', 'john.smith@email.com', 5),
                                                             ('Emma Wilson', 'emma.wilson@email.com', 3),
                                                             ('Michael Brown', 'm.brown@email.com', 5);

-- Insert Users (library staff)
INSERT INTO users (username, password, full_name, email, role,is_valid) VALUES
                                                                   ('libadmin', 'secure123', 'Sarah Johnson', 's.johnson@library.com', 'Admin',true),
                                                                   ('maryp', 'librarypass', 'Mary Peterson', 'mary.p@library.com', 'Librarian',true);

-- Insert Borrowing Transactions
INSERT INTO borrowing_transactions VALUES
                                       (1, 1, 1, 'libadmin', '2023-10-01', 'libadmin', '2023-10-01', '2023-10-15', '2023-10-15',null),
                                       (2, 2, 3, 'maryp', '2023-10-05', 'maryp', '2023-10-05', NULL, '2023-10-20',null);
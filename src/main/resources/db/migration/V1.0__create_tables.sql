CREATE TABLE authors
(
    author_id   INT AUTO_INCREMENT NOT NULL,
    name        VARCHAR(100) NULL,
    nationality VARCHAR(100) NULL,
    bio         VARCHAR(500) NULL,
    CONSTRAINT pk_authors PRIMARY KEY (author_id)

);

CREATE TABLE book_authors
(
    author_id INT    NOT NULL,
    book_id   BIGINT NOT NULL,
    CONSTRAINT pk_book_authors PRIMARY KEY (author_id, book_id)
);

CREATE TABLE book_categories
(
    book_id     BIGINT NOT NULL,
    category_id INT    NOT NULL,
    CONSTRAINT pk_book_categories PRIMARY KEY (book_id, category_id)
);

CREATE TABLE books
(
    book_id         BIGINT AUTO_INCREMENT NOT NULL,
    title           VARCHAR(100) NOT NULL,
    isbn            VARCHAR(17) NULL,
    year            INT          NOT NULL,
    quantity        INT          NOT NULL,
    edition         VARCHAR(255) NULL,
    cover_image_url VARCHAR(2048) NULL,
    language        VARCHAR(10)  NOT NULL,
    summary         VARCHAR(500) NULL,
    publisher_id    INT NULL,
    created_by      VARCHAR(50) NULL,
    creation_date   datetime NULL,
    modified_by     VARCHAR(50) NULL,
    modified_date   datetime NULL,
    CONSTRAINT pk_books PRIMARY KEY (book_id)
);

CREATE TABLE borrowing_transactions
(
    transaction_id BIGINT AUTO_INCREMENT NOT NULL,
    member_id      BIGINT NULL,
    book_id        BIGINT NULL,
    created_by     VARCHAR(30) NULL,
    checkout_date  datetime NULL,
    modified_by    VARCHAR(30) NULL,
    modified_date  datetime NULL,
    return_date    datetime NULL,
    due_date       datetime NULL,
    fine_amount    BIGINT Null,
    CONSTRAINT pk_borrowing_transactions PRIMARY KEY (transaction_id)
);

CREATE TABLE categories
(
    category_id INT AUTO_INCREMENT NOT NULL,
    name        VARCHAR(30) NOT NULL,
    CONSTRAINT pk_categories PRIMARY KEY (category_id)
);

CREATE TABLE category_hierarchy
(
    category_id        INT NOT NULL,
    category_parent_id INT NOT NULL,
    CONSTRAINT pk_category_hierarchy PRIMARY KEY (category_id, category_parent_id)
);

CREATE TABLE members
(
    member_id        BIGINT AUTO_INCREMENT NOT NULL,
    full_name        VARCHAR(100) NOT NULL,
    email            VARCHAR(100) NOT NULL,
    address          VARCHAR(100) NULL,
    phone            VARCHAR(15) NULL,
    created_by       VARCHAR(50) NULL,
    creation_date    datetime NULL,
    modified_by      VARCHAR(50) NULL,
    modified_date    datetime NULL,
    max_borrow_limit INT NULL,
    CONSTRAINT pk_members PRIMARY KEY (member_id)
);

CREATE TABLE publishers
(
    publisher_id  INT AUTO_INCREMENT NOT NULL,
    name          VARCHAR(50) NOT NULL,
    address       VARCHAR(100) NULL,
    contact_email VARCHAR(100) NULL,
    CONSTRAINT pk_publishers PRIMARY KEY (publisher_id)
);

CREATE TABLE users
(
    user_id   INT AUTO_INCREMENT NOT NULL,
    username  VARCHAR(30) NULL,
    password VARCHAR(100) NULL,
    full_name VARCHAR(100) NULL,
    email     VARCHAR(30) NULL,
    phone     VARCHAR(15) NULL,
    `role`    VARCHAR(30) NULL,
    address   VARCHAR(100) NULL,
    is_valid  BIT(1) NULL,
    CONSTRAINT pk_users PRIMARY KEY (user_id)
);

ALTER TABLE books
    ADD CONSTRAINT uc_books_isbn UNIQUE (isbn);

ALTER TABLE categories
    ADD CONSTRAINT uc_categories_name UNIQUE (name);

ALTER TABLE members
    ADD CONSTRAINT uc_members_email UNIQUE (email);

ALTER TABLE publishers
    ADD CONSTRAINT uc_publishers_contact_email UNIQUE (contact_email);

ALTER TABLE users
    ADD CONSTRAINT uc_users_email UNIQUE (email);

ALTER TABLE users
    ADD CONSTRAINT uc_users_username UNIQUE (username);

ALTER TABLE books
    ADD CONSTRAINT FK_BOOKS_ON_PUBLISHER FOREIGN KEY (publisher_id) REFERENCES publishers (publisher_id) ON DELETE CASCADE
;

ALTER TABLE borrowing_transactions
    ADD CONSTRAINT FK_BORROWING_TRANSACTIONS_ON_BOOK FOREIGN KEY (book_id) REFERENCES books (book_id) ON DELETE CASCADE;

ALTER TABLE borrowing_transactions
    ADD CONSTRAINT FK_BORROWING_TRANSACTIONS_ON_MEMBER FOREIGN KEY (member_id) REFERENCES members (member_id) ON DELETE CASCADE;

ALTER TABLE book_authors
    ADD CONSTRAINT fk_booaut_on_author FOREIGN KEY (author_id) REFERENCES authors (author_id) ON DELETE CASCADE;

ALTER TABLE book_authors
    ADD CONSTRAINT fk_booaut_on_book FOREIGN KEY (book_id) REFERENCES books (book_id) ON DELETE CASCADE;

ALTER TABLE book_categories
    ADD CONSTRAINT fk_boocat_on_book FOREIGN KEY (book_id) REFERENCES books (book_id) ON DELETE CASCADE;

ALTER TABLE book_categories
    ADD CONSTRAINT fk_boocat_on_category FOREIGN KEY (category_id) REFERENCES categories (category_id) ON DELETE CASCADE;

ALTER TABLE category_hierarchy
    ADD CONSTRAINT fk_cathie_on_category FOREIGN KEY (category_id) REFERENCES categories (category_id) ON DELETE CASCADE;

ALTER TABLE category_hierarchy
    ADD CONSTRAINT fk_cathie_on_category_parent FOREIGN KEY (category_parent_id) REFERENCES categories (category_id) ON DELETE CASCADE;
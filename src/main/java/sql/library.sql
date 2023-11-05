drop database if exists `library`;
create database `library`;
use `library`;

DROP TABLE IF EXISTS users;
CREATE TABLE `users` (
                         `id` int(5) NOT NULL,
                         `name` varchar(100) NOT NULL,
                         `email` varchar(100) NOT NULL,
                         `username` varchar(15) NOT NULL,
                         `password` varchar(255) NOT NULL,
                         `phone` int(10) NOT NULL,
                         `address` varchar(200) NOT NULL,
                         `city` varchar(20) NOT NULL,
                         `postcode` varchar(20) NOT NULL,
                         `user_type` int(5) NOT NULL DEFAULT 0 COMMENT '0=member, 1=staff',
                         `disable` int(5) NOT NULL DEFAULT 0 COMMENT '0=not-disable, 1=disable',
                         PRIMARY KEY  (id),
                         UNIQUE KEY (email),
                         UNIQUE KEY (username)

);

DROP TABLE IF EXISTS book;
CREATE TABLE `book` (
                        `id` int(20) NOT NULL,
                        `title` varchar(255) NOT NULL,
                        `author` varchar(255) NOT NULL,
                        `ISBN` int(255) NOT NULL,
                        `publication_date` date NOT NULL,
                        `qty` int(255) NOT NULL,
                        `description` varchar(255) NOT NULL,
                        `copy_qty` int(255) NOT NULL DEFAULT 0,
                        PRIMARY KEY  (id)
);

DROP TABLE IF EXISTS category;
CREATE TABLE `category` (
                            `id` int(11) NOT NULL,
                            `category_name` varchar(255) NOT NULL,
                            PRIMARY KEY  (id)
);

DROP TABLE IF EXISTS bookcategory;
CREATE TABLE `bookcategory` (
                                `id` int(255) NOT NULL,
                                `book_id` int(255) NOT NULL,
                                `category_id` int(255) NOT NULL,
                                PRIMARY KEY  (id),
                                FOREIGN KEY (book_id) REFERENCES book(id),
                                FOREIGN KEY (category_id) REFERENCES category(id)
);

DROP TABLE IF EXISTS borrow;
CREATE TABLE `borrow` (
                          `id` int(255) NOT NULL,
                          `user_id` int(255) NOT NULL,
                          `book_id` int(255) NOT NULL,
                          `issued_date` date NOT NULL,
                          `due_date` date NOT NULL,
                          `return_date` date DEFAULT NULL,
                          `fine` int(255) DEFAULT NULL,
                          PRIMARY KEY  (id),
                          FOREIGN KEY (book_id) REFERENCES book(id),
                          FOREIGN KEY (user_id) REFERENCES users(id)
);

DROP TABLE IF EXISTS payment;
CREATE TABLE `payment` (
                           `id` int(255) NOT NULL,
                           `user_id` int(255) NOT NULL,
                           `book_id` int(255) NOT NULL,
                           `fine` int(255) NOT NULL,
                           `card_number` varchar(255) NOT NULL,
                           `expiry_date` date NOT NULL,
                           PRIMARY KEY  (id),
                           FOREIGN KEY (book_id) REFERENCES book(id),
                           FOREIGN KEY (user_id) REFERENCES users(id)
);
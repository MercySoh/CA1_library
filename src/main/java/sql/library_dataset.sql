use `library`;

INSERT INTO `users` (`id`, `name`, `email`, `username`, `password`, `phone`, `address`, `city`, `postcode`, `user_type`, `disable`)
VALUES (1, 'Kelly Smith', 'kelly0202@gmail.com', 'Kelly', '$2a$10$ahgSm5ihnB6iruEuliMmoumx6Cd01/5oQK89vKYGvYnhXvZ3Vi8Ra', 881478956, '23 Andrew St', 'Dundalk', 'A94U1P4', 0, 0);
INSERT INTO `users` (`id`, `name`, `email`, `username`, `password`, `phone`, `address`, `city`, `postcode`, `user_type`, `disable`)
VALUES (2, 'Jenny Ryan', 'Jennyryan@gmail.com', 'Jenny', '$2a$10$gCDIGPG0YwklrNdYkzqysuwqkfjfsEkMebkD.naVrbAYGHjWf.gEO', 875123491, '8 Stephen Park', 'Dundalk', 'A97P0B6', 1, 0);
INSERT INTO `users` (`id`, `name`, `email`, `username`, `password`, `phone`, `address`, `city`, `postcode`, `user_type`, `disable`)
VALUES(3, 'John Ferris', 'John67@gmail.com', 'John67', '$2a$10$oczR6XWHI7kz8nHHoQbZxeGfsTynErnM9GAO9RkxPNDiHjlbWU5WK', 875984763, '39 Conner Road', 'Dundalk', 'A94 DU89', 0, 0);

INSERT INTO `book` (`id`, `title`, `author`, `ISBN`, `publication_date`, `qty`, `description`, `copy_qty`)
VALUES (1, 'The Bucket List: 1000 Adventures Big & Small (Bucket Lists)', ' Kath Stathers', 789332698, '2017-09-15', 5, 'With 1,000 adventures for all ages, its never too soon or too late to begin the things you have only dreamed of doing.', 0);
INSERT INTO `book` (`id`, `title`, `author`, `ISBN`, `publication_date`, `qty`, `description`, `copy_qty`)
VALUES (2, '101 Weekends in Europe, 2nd Edition (IMM Lifestyle Books)', 'Robin Barton', 1913618218, '2022-03-01', 5, 'Discover the best of what each classic European destination has to offer!', 0);
INSERT INTO `book` (`id`, `title`, `author`, `ISBN`, `publication_date`, `qty`, `description`, `copy_qty`)
VALUES (3, 'Ginger Pig Christmas Cook Book', 'Tim Wilson ', 1784729191, '2023-10-05', 3, 'More than 80 delicious recipes for the perfect Christmas from acclaimed sustainable butcher Ginger Pig.', 0);
INSERT INTO `book` (`id`, `title`, `author`, `ISBN`, `publication_date`, `qty`, `description`, `copy_qty`)
VALUES (4, 'The Little Book of Student Food', ' Alastair Williams', 1787830241, '2019-08-08', 3, 'This pocket-sized collection of helpful guidance and satisfying recipes will help students eat well and affordably', 0);
INSERT INTO `book` (`id`, `title`, `author`, `ISBN`, `publication_date`, `qty`, `description`, `copy_qty`)
VALUES (5, 'Lonely Planet Eat Japan', ' Lonely Planet Food ', 1838690514, '2021-05-14', 3, 'To help you feel prepared for the Japanese food scene we will cover how, when and where to eat, etiquette dos and do not, and what classic regional specialties are a must try. ', 1);
INSERT INTO `book` (`id`, `title`, `author`, `ISBN`, `publication_date`, `qty`, `description`, `copy_qty`)
VALUES (6, 'Look Inside Food', 'Emily Bone', 140958206, '2015-06-01', 3, 'It is important for children to understand food - where it comes from, whats in it, and how it affects our bodies. ', 3);

INSERT INTO `category` (`id`, `category_name`)
VALUES (1, 'Travel');
INSERT INTO `category` (`id`, `category_name`)
VALUES (2, 'History');
INSERT INTO `category` (`id`, `category_name`)
VALUES (3, 'Fashion');
INSERT INTO `category` (`id`, `category_name`)
VALUES (4, 'Novel');
INSERT INTO `category` (`id`, `category_name`)
VALUES (5, 'Science');
INSERT INTO `category` (`id`, `category_name`)
VALUES (6, 'Food');
INSERT INTO `category` (`id`, `category_name`)
VALUES (7, 'Storybook');
INSERT INTO `category` (`id`, `category_name`)
VALUES (8, 'Adventure');
INSERT INTO `category` (`id`, `category_name`)
VALUES (9, 'Education');

INSERT INTO `bookcategory` (`id`, `book_id`, `category_id`)
VALUES (1, 1, 1);
INSERT INTO `bookcategory` (`id`, `book_id`, `category_id`)
VALUES (2, 1, 8);
INSERT INTO `bookcategory` (`id`, `book_id`, `category_id`)
VALUES (3, 2, 1);
INSERT INTO `bookcategory` (`id`, `book_id`, `category_id`)
VALUES (4, 2, 2);
INSERT INTO `bookcategory` (`id`, `book_id`, `category_id`)
VALUES (5, 2, 8);
INSERT INTO `bookcategory` (`id`, `book_id`, `category_id`)
VALUES (6, 3, 6);
INSERT INTO `bookcategory` (`id`, `book_id`, `category_id`)
VALUES (7, 4, 9);
INSERT INTO `bookcategory` (`id`, `book_id`, `category_id`)
VALUES (8, 4, 6);
INSERT INTO `bookcategory` (`id`, `book_id`, `category_id`)
VALUES (9, 5, 1);
INSERT INTO `bookcategory` (`id`, `book_id`, `category_id`)
VALUES (10, 5, 6);
INSERT INTO `bookcategory` (`id`, `book_id`, `category_id`)
VALUES (11, 6, 6);
INSERT INTO `bookcategory` (`id`, `book_id`, `category_id`)
VALUES (12, 6, 7);

INSERT INTO `borrow` (`id`, `user_id`, `book_id`, `issued_date`, `due_date`, `return_date`, `fine`)
VALUES (1, 1, 1, '2023-04-03', '2023-04-17', '2023-04-13', NULL);
INSERT INTO `borrow` (`id`, `user_id`, `book_id`, `issued_date`, `due_date`, `return_date`, `fine`)
VALUES (2, 1, 2, '2023-04-03', '2023-04-17', '2023-04-13', NULL);
INSERT INTO `borrow` (`id`, `user_id`, `book_id`, `issued_date`, `due_date`, `return_date`, `fine`)
VALUES (3, 2, 3, '2023-10-01', '2023-10-22', '2023-10-25', 3);
INSERT INTO `borrow` (`id`, `user_id`, `book_id`, `issued_date`, `due_date`, `return_date`, `fine`)
VALUES (4, 3, 5, '2021-10-12', '2021-10-26', '2021-10-22', NULL);
INSERT INTO `borrow` (`id`, `user_id`, `book_id`, `issued_date`, `due_date`, `return_date`, `fine`)
VALUES (5, 3, 2, '2022-09-07', '2022-09-21', '2022-09-27', 6);

INSERT INTO `payment` (`id`, `user_id`, `book_id`, `fine`, `card_number`, `expiry_date`)
VALUES (1, 2, 2, 3, '1230456178942579', '2028-10-01');
INSERT INTO `payment` (`id`, `user_id`, `book_id`, `fine`, `card_number`, `expiry_date`)
VALUES (2, 3, 2, 6, '4560789612308900', '2027-01-20');




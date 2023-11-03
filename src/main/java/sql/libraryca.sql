-- phpMyAdmin SQL Dump
-- version 5.2.0
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Generation Time: Nov 03, 2023 at 05:27 PM
-- Server version: 10.4.25-MariaDB
-- PHP Version: 8.1.10

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `libraryca`
--

-- --------------------------------------------------------

--
-- Table structure for table `book`
--

CREATE TABLE `book` (
  `id` int(20) NOT NULL,
  `title` varchar(255) NOT NULL,
  `author` varchar(255) NOT NULL,
  `ISBN` int(255) NOT NULL,
  `publication_date` date NOT NULL,
  `qty` int(255) NOT NULL,
  `description` varchar(255) NOT NULL,
  `copy_qty` int(255) NOT NULL DEFAULT 0
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `book`
--

INSERT INTO `book` (`id`, `title`, `author`, `ISBN`, `publication_date`, `qty`, `description`, `copy_qty`) VALUES
(1, 'The Bucket List: 1000 Adventures Big & Small (Bucket Lists)', ' Kath Stathers', 789332698, '2017-09-15', 5, 'With 1,000 adventures for all ages, it\'s never too soon or too late to begin the things you\'ve only dreamed of doing.', 0),
(2, '101 Weekends in Europe, 2nd Edition (IMM Lifestyle Books)', 'Robin Barton', 1913618218, '2022-03-01', 5, 'Discover the best of what each classic European destination has to offer!', 0),
(3, 'Ginger Pig Christmas Cook Book', 'Tim Wilson ', 1784729191, '2023-10-05', 3, 'More than 80 delicious recipes for the perfect Christmas from acclaimed sustainable butcher Ginger Pig.', 0),
(4, 'The Little Book of Student Food', ' Alastair Williams', 1787830241, '2019-08-08', 3, 'This pocket-sized collection of helpful guidance and satisfying recipes will help students eat well and affordably', 0),
(5, 'Lonely Planet Eat Japan', ' Lonely Planet Food ', 1838690514, '2021-05-14', 3, 'To help you feel prepared for the Japanese food scene we\'ll cover how, when and where to eat, etiquette dos and don\'ts, and what classic regional specialties are a must try. ', 1),
(6, 'Look Inside Food', 'Emily Bone', 140958206, '2015-06-01', 3, 'It is important for children to understand food - where it comes from, what\'s in it, and how it affects our bodies. ', 3),
(7, 'Look Inside: Space', ' Rob Lloyd Jones', 1409523381, '2012-09-01', 7, 'This is a great fun flap book packed with interesting information about space, and the amazing things that float through it - stars, moons, comets, and the planets of our solar system. ', 0),
(8, 'See Inside Your Body', 'Katie Daynes', 746070055, '2006-01-01', 7, 'This astonishingly inventive title allows young children to discover the inner workings of the human body in a gently humorous, yet wholly accurate way.', 0),
(9, 'Look Inside Maths', 'Dickens Rosie ', 1474986307, '2021-05-10', 7, 'Lift the flaps and learn important maths skills, from adding and subtracting to fractions and shapes. ', 0),
(10, 'Trench Coat (Object Lessons) ', 'Jane Tynan ', 1501375164, '2022-06-30', 5, 'Object Lessons is a series of short, beautifully designed books about the hidden lives of ordinary things.', 0),
(11, 'How To Start Sewing', 'Assembil Books', 1700478192, '2019-11-13', 3, 'More than just How To Start Sewing, this book will help you start sewing, keep sewing, and sew better than ever before.', 0),
(12, 'How to Draw Like a Fashion Designer', 'Celia Joicey', 500650187, '2013-09-30', 3, 'This is a beginners’ step-by-step guide to drawing like a fashion designer. ', 0),
(13, 'Planning and Fitting Kitchens', 'Dennis Dixon ', 1861084986, '2007-06-15', 3, 'Every household is unique, and the kitchen is the room which reflects your familys lifestyle choices more than any other.', 0),
(14, 'The Interior Design Handbook', ' Frida Ramstedt', 24143811, '2020-10-29', 3, 'Design consultant Frida Ramstedt runs Scandinavia\'s leading interior design blog.', 0),
(15, 'Garden Design: A Book of Ideas', 'Heidi Howcroft', 1845339215, '2015-03-16', 2, 'Garden Design: A Book of Ideas is the must-have visual reference for garden owners, architects and designers.', 2),
(16, 'The Lost Bookshop', 'Evie Woods ', 8609217, '2023-06-22', 3, 'For too long, Opaline, Martha and Henry have been the side characters in their own lives.', 3),
(17, 'The Skylark\'s Secret', 'Fiona Valpy ', 1542005159, '2020-09-29', 2, 'Loch Ewe, 1940. When gamekeeper’s daughter Flora’s remote highland village finds itself the base for the Royal Navy’s Arctic convoys, life in her close-knit community changes forever. ', 3),
(18, 'The Storyteller of Casablanca', 'Fiona Valpy', 1542032105, '2021-09-21', 2, 'Morocco, 1941. With France having fallen to Nazi occupation, twelve-year-old Josie has fled with her family to Casablanca, where they await safe passage to America.', 3),
(19, 'Adventures in Time: Cleopatra, Queen of the Nile', 'Dominic Sandbrook', 24155215, '2022-06-02', 2, 'Take a journey to a vanished world with the ADVENTURES IN TIME series - stories so exciting you won\'t believe they\'re all true', 0),
(20, 'The Story of the London Underground', 'David Long', 1408889951, '2019-10-17', 2, 'When the first passengers climbed aboard the earliest ever underground train in 1863, it would have been impossible to imagine how the London Underground would change and grow over the next 150 years. ', 0),
(21, 'Roman Holiday: The Illustrated Storybook', 'Micol Ostow', 1647226856, '2023-03-07', 4, 'A princess on the run, a secret identity revealed, and international intrigue abound in the stylishly illustrated child-friendly retelling of the classic film, Roman Holiday.', 0),
(22, 'The Goodnight Book', 'Kate Staves ', 1914541049, '2021-11-30', 3, 'Snuggle up and say goodnight to all of your adorable animal friends in this sweet and soothing bedtime story.', 0),
(23, 'Timelines of Science: From Fossils to Quantum Physics', 'Leo Ball ', 241515351, '2022-10-06', 4, 'Timelines of Science takes you on an astonishing journey through history, showing how dedication, disasters, and eureka moments have brought us antibiotics, electricity, space exploration, and so much more!', 0);

-- --------------------------------------------------------

--
-- Table structure for table `bookcategory`
--

CREATE TABLE `bookcategory` (
  `id` int(255) NOT NULL,
  `book_id` int(255) NOT NULL,
  `category_id` int(255) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `bookcategory`
--

INSERT INTO `bookcategory` (`id`, `book_id`, `category_id`) VALUES
(1, 1, 1),
(2, 1, 8),
(3, 2, 1),
(4, 2, 2),
(5, 2, 8),
(6, 3, 6),
(7, 4, 9),
(8, 4, 6),
(9, 5, 1),
(10, 5, 6),
(11, 6, 6),
(12, 6, 7),
(13, 7, 5),
(14, 7, 7),
(15, 7, 8),
(16, 7, 9),
(17, 8, 5),
(18, 8, 7),
(19, 8, 9),
(20, 9, 9),
(21, 9, 7),
(22, 10, 3),
(23, 10, 10),
(24, 11, 3),
(25, 11, 9),
(26, 12, 3),
(27, 12, 9),
(28, 12, 10),
(29, 13, 10),
(30, 13, 9),
(31, 14, 10),
(32, 14, 9),
(33, 15, 10),
(34, 16, 4),
(35, 17, 4),
(36, 18, 4),
(37, 18, 8),
(38, 19, 2),
(39, 19, 8),
(40, 20, 7),
(42, 21, 7),
(43, 21, 1),
(44, 22, 7),
(45, 23, 5),
(46, 23, 9);

-- --------------------------------------------------------

--
-- Table structure for table `borrow`
--

CREATE TABLE `borrow` (
  `id` int(255) NOT NULL,
  `user_id` int(255) NOT NULL,
  `book_id` int(255) NOT NULL,
  `issued_date` date NOT NULL,
  `due_date` date NOT NULL,
  `return_date` date DEFAULT NULL,
  `fine` int(255) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `borrow`
--

INSERT INTO `borrow` (`id`, `user_id`, `book_id`, `issued_date`, `due_date`, `return_date`, `fine`) VALUES
(1, 1, 1, '2023-04-03', '2023-04-17', '2023-04-13', NULL),
(2, 1, 2, '2023-04-03', '2023-04-17', '2023-04-13', NULL),
(3, 2, 3, '2023-10-01', '2023-10-22', '2023-10-18', NULL),
(4, 4, 6, '2022-10-02', '2022-10-16', '2022-10-19', 3),
(5, 4, 9, '2023-02-13', '2023-02-27', '2023-02-22', NULL),
(6, 4, 21, '2023-04-05', '2023-04-19', '2023-04-15', NULL),
(7, 3, 13, '2021-10-12', '2021-10-26', '2021-10-22', NULL),
(8, 3, 14, '2022-09-07', '2022-09-21', '2022-09-27', 6),
(9, 5, 11, '2023-10-17', '2023-10-31', NULL, NULL),
(10, 5, 17, '2023-09-19', '2023-10-03', '2023-09-28', NULL),
(11, 5, 7, '2023-08-15', '2023-08-29', '2023-08-24', NULL),
(12, 5, 18, '2023-10-28', '2023-11-10', NULL, NULL),
(13, 5, 5, '2023-04-03', '2023-04-17', '2023-04-19', 2),
(14, 5, 6, '2023-04-03', '2023-04-17', '2023-04-10', NULL),
(15, 6, 18, '2023-10-27', '2023-11-17', NULL, NULL),
(16, 6, 16, '2023-10-27', '2023-11-17', NULL, NULL),
(17, 4, 5, '2023-11-20', '2023-12-04', NULL, NULL);

-- --------------------------------------------------------

--
-- Table structure for table `category`
--

CREATE TABLE `category` (
  `id` int(11) NOT NULL,
  `category_name` varchar(255) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `category`
--

INSERT INTO `category` (`id`, `category_name`) VALUES
(1, 'Travel'),
(2, 'History'),
(3, 'Fashion'),
(4, 'Novel'),
(5, 'Science'),
(6, 'Food'),
(7, 'Storybook'),
(8, 'Adventure'),
(9, 'Education'),
(10, 'Design');

-- --------------------------------------------------------

--
-- Table structure for table `payment`
--

CREATE TABLE `payment` (
  `id` int(255) NOT NULL,
  `user_id` int(255) NOT NULL,
  `book_id` int(255) NOT NULL,
  `fine` int(255) NOT NULL,
  `card_number` varchar(255) NOT NULL,
  `expiry_date` date NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `payment`
--

INSERT INTO `payment` (`id`, `user_id`, `book_id`, `fine`, `card_number`, `expiry_date`) VALUES
(1, 4, 6, 3, '1230456178942579', '2028-10-01'),
(2, 3, 14, 6, '4560789612308900', '2027-01-20'),
(3, 5, 5, 2, '3210654298759630', '2027-07-13');

-- --------------------------------------------------------

--
-- Table structure for table `users`
--

CREATE TABLE `users` (
  `id` int(5) NOT NULL,
  `name` varchar(100) NOT NULL,
  `email` varchar(100) NOT NULL,
  `username` varchar(15) NOT NULL,
  `password` varchar(20) NOT NULL,
  `phone` int(10) NOT NULL,
  `address` varchar(200) NOT NULL,
  `city` varchar(20) NOT NULL,
  `postcode` varchar(20) NOT NULL,
  `user_type` int(5) NOT NULL DEFAULT 0 COMMENT '0=member, 1=staff',
  `disable` int(5) NOT NULL DEFAULT 0 COMMENT '0=not-disable, 1=disable'
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `users`
--

INSERT INTO `users` (`id`, `name`, `email`, `username`, `password`, `phone`, `address`, `city`, `postcode`, `user_type`, `disable`) VALUES
(1, 'Kelly Smith', 'kelly0202@gmail.com', 'Kelly', 'kelly@123', 881478956, '23 Andrew St', 'Dundalk', 'A94U1P4', 0, 0),
(2, 'Jenny Ryan', 'Jennyryan@gmail.com', 'Jenny', '789Jenny!', 875123491, '8 Stephen Park', 'Dundalk', 'A97P0B6', 1, 0),
(3, 'John Ferris', 'John67@gmail.com', 'John67', 'John670258!', 875984763, '39 Conner Road', 'Dundalk', 'A94 DU89', 0, 0),
(4, 'Laura Lynn', 'laura123@gmail.com', 'Laura', 'laura123@lynn', 887534573, '5 Penny', 'Carlingford', 'A922W4R', 0, 0),
(5, 'Luka Smith', 'luka000@gmail.com', 'Luka', '00luka!', 848973010, '19 Lady St', 'Monaghan', 'A91 8OQ1', 0, 0),
(6, 'Olivia Munn', 'olivia98@gmail.com', 'Oliviamunn', 'Olivia98!', 83781973, '61 Lane St', 'Dundalk', 'A92H9P6', 1, 0);

--
-- Indexes for dumped tables
--

--
-- Indexes for table `book`
--
ALTER TABLE `book`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `bookcategory`
--
ALTER TABLE `bookcategory`
  ADD PRIMARY KEY (`id`),
  ADD KEY `book_id` (`book_id`),
  ADD KEY `category_id` (`category_id`);

--
-- Indexes for table `borrow`
--
ALTER TABLE `borrow`
  ADD PRIMARY KEY (`id`),
  ADD KEY `book_id` (`book_id`),
  ADD KEY `user_id` (`user_id`);

--
-- Indexes for table `category`
--
ALTER TABLE `category`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `payment`
--
ALTER TABLE `payment`
  ADD PRIMARY KEY (`id`),
  ADD KEY `user_id` (`user_id`),
  ADD KEY `book_id` (`book_id`);

--
-- Indexes for table `users`
--
ALTER TABLE `users`
  ADD PRIMARY KEY (`id`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `users`
--
ALTER TABLE `users`
  MODIFY `id` int(5) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=7;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;

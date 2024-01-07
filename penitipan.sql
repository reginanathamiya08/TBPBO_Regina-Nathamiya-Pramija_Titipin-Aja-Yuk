-- phpMyAdmin SQL Dump
-- version 5.2.1
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Generation Time: Jan 07, 2024 at 12:34 PM
-- Server version: 10.4.28-MariaDB
-- PHP Version: 8.2.4

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `penitipan_helm`
--

-- --------------------------------------------------------

--
-- Table structure for table `penitipan`
--

CREATE TABLE `penitipan` (
  `no_penitipan` int(11) NOT NULL,
  `jenis_helm` int(11) NOT NULL,
  `nama_pemilik` varchar(225) NOT NULL,
  `jam_masuk` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `penitipan`
--

INSERT INTO `penitipan` (`no_penitipan`, `jenis_helm`, `nama_pemilik`, `jam_masuk`) VALUES
(2, 2, 'Gina', 18),
(1, 2, 'Egi', 18),
(3, 1, 'Gin', 18),
(4, 1, 'ayrel', 18),
(1, 1, 'Rel', 18);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;

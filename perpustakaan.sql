-- phpMyAdmin SQL Dump
-- version 5.2.1
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Generation Time: Aug 04, 2025 at 04:15 PM
-- Server version: 10.4.32-MariaDB
-- PHP Version: 8.2.12

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `perpustakaan`
--

-- --------------------------------------------------------

--
-- Table structure for table `admin`
--

CREATE TABLE `admin` (
  `usnadmin` varchar(15) NOT NULL,
  `pwadmin` char(15) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `admin`
--

INSERT INTO `admin` (`usnadmin`, `pwadmin`) VALUES
('nasywa', '123');

-- --------------------------------------------------------

--
-- Table structure for table `buku`
--

CREATE TABLE `buku` (
  `idBuku` varchar(20) NOT NULL,
  `judul` varchar(100) DEFAULT NULL,
  `pengarang` varchar(100) DEFAULT NULL,
  `tahun` varchar(4) DEFAULT NULL,
  `stok` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `buku`
--

INSERT INTO `buku` (`idBuku`, `judul`, `pengarang`, `tahun`, `stok`) VALUES
('001', 'Laut bercerita', 'Leila S. Chudori ', '2017', 17),
('002', 'The Psychology of Money', 'Morgan Housel', '2020', 10),
('003', 'Filosofi Teras', 'Henry Manampiring', '2018', 28),
('004', 'Kami Bukan (Jongos) Berdasi', 'J.S. Khairen', '2019', 50),
('005', 'Bicara Itu Ada Seninya', 'Oh Su Hyang', '2016', 30),
('006', 'Berani Tidak Disukai', 'Ichiro Kishimi, Fumitake Koga', '2019', 18),
('007', 'Think And Grow Rich', 'Napoleon Hill', '2019', 19),
('008', 'The Power Of Habit', 'Charles Duhigg', '2019', 38),
('009', 'Jika Kita Tak Pernah Jadi Apa-Apa', 'Alvi Syahrin', '2019', 60),
('011', 'Seni Berpikir Kritis', 'Ibnu Sina', '2025', 28),
('012', 'Seni Mengelola Emosi', 'In Hyun Jin', '2025', 30),
('013', 'Di Luar Daya Manusia dan Belum Ada Apa-Apanya', 'Triana Ayuningtyas', '2024', 30),
('014', 'Good Habits for Smart Thinking', 'Diana Tara', '2025', 50),
('015', 'Berpikir Logis Bertindak Tepat', 'Dewi Indra P.', '2024', 40),
('016', 'Do It Right Now', 'Aba Mehmed Agha', '2024', 19),
('017', 'The Power of Positive Thinking', 'David Miller', '2025', 45),
('018', 'Hujan', 'Tere Liye', '2016', 38),
('019', '10 Dosa Besar Soeharto', 'orang', '1998', 9),
('020', 'Pulang', 'Tere Liye', '2020', 42),
('021', 'Sebuah Seni untuk Bersikap Bodo Amat', 'Mark Manson', '2016', 80),
('022', 'What`s So Wrong About Your Life', 'Ardhi Mohamad', '2019', 60);

-- --------------------------------------------------------

--
-- Table structure for table `peminjaman`
--

CREATE TABLE `peminjaman` (
  `idPinjam` varchar(10) NOT NULL,
  `username` varchar(50) DEFAULT NULL,
  `idBuku` varchar(20) NOT NULL,
  `tanggalPinjam` date DEFAULT NULL,
  `tanggalKembali` date DEFAULT NULL,
  `status` enum('Dipinjam','Dikembalikan') DEFAULT 'Dipinjam',
  `denda` int(10) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `peminjaman`
--

INSERT INTO `peminjaman` (`idPinjam`, `username`, `idBuku`, `tanggalPinjam`, `tanggalKembali`, `status`, `denda`) VALUES
('01', 'lutfi', '019', '2025-08-04', '2025-08-16', 'Dikembalikan', 0),
('1', 'nasywazain', '016', '2025-08-01', '2025-08-07', 'Dikembalikan', 0),
('111', 'nabila', '014', '2025-08-04', '2025-08-15', 'Dikembalikan', 0),
('112', 'nabila', '007', '2025-08-13', '2025-08-22', 'Dikembalikan', 0),
('113', 'nabila', '008', '2025-08-05', '2025-08-12', 'Dikembalikan', 0),
('2', 'nasywazain', '015', '2025-08-04', '2025-08-04', 'Dikembalikan', 0),
('3', 'nasywazain', '006', '2025-08-01', '2025-08-10', 'Dikembalikan', 0),
('4', 'nasywazain', '011', '2025-08-03', '2025-08-10', 'Dikembalikan', 0),
('5', 'nasywazain', '005', '2025-08-02', '2025-08-12', 'Dikembalikan', 0),
('6', 'nasywazain', '013', '2025-08-04', '2025-08-04', 'Dikembalikan', 0),
('7', 'nasywazain', '004', '2025-08-04', '2025-08-14', 'Dikembalikan', 0),
('8', 'nasywazain', '022', '2025-08-04', '2025-08-16', 'Dikembalikan', 0);

-- --------------------------------------------------------

--
-- Table structure for table `user`
--

CREATE TABLE `user` (
  `username` varchar(15) DEFAULT NULL,
  `password` char(15) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `user`
--

INSERT INTO `user` (`username`, `password`) VALUES
('nasywazain', 'nasywa123'),
('nabila', 'nabila123'),
('lutfi', 'lutfi123'),
('user4', '123');

--
-- Indexes for dumped tables
--

--
-- Indexes for table `buku`
--
ALTER TABLE `buku`
  ADD PRIMARY KEY (`idBuku`);

--
-- Indexes for table `peminjaman`
--
ALTER TABLE `peminjaman`
  ADD PRIMARY KEY (`idPinjam`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;

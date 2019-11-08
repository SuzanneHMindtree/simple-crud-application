-- phpMyAdmin SQL Dump
-- version 4.8.2
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Waktu pembuatan: 08 Nov 2019 pada 15.38
-- Versi server: 10.1.34-MariaDB
-- Versi PHP: 7.2.7

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET AUTOCOMMIT = 0;
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `icecream`
--

-- --------------------------------------------------------

--
-- Struktur dari tabel `detailtransaction`
--

CREATE TABLE `detailtransaction` (
  `TransactionID` char(5) NOT NULL,
  `ProductID` char(5) NOT NULL,
  `Qty` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data untuk tabel `detailtransaction`
--

INSERT INTO `detailtransaction` (`TransactionID`, `ProductID`, `Qty`) VALUES
('TR001', 'PR001', 5),
('TR002', 'PR002', 10);

-- --------------------------------------------------------

--
-- Struktur dari tabel `headertransaction`
--

CREATE TABLE `headertransaction` (
  `TransactionID` char(5) NOT NULL,
  `UserID` char(5) NOT NULL,
  `TransactionDate` date NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data untuk tabel `headertransaction`
--

INSERT INTO `headertransaction` (`TransactionID`, `UserID`, `TransactionDate`) VALUES
('TR001', 'CU001', '2019-07-23'),
('TR002', 'CU001', '2019-07-23');

-- --------------------------------------------------------

--
-- Struktur dari tabel `product`
--

CREATE TABLE `product` (
  `ProductID` char(5) NOT NULL,
  `ProductName` text NOT NULL,
  `ProductPrice` int(11) NOT NULL,
  `ProductStock` int(11) NOT NULL,
  `ProductImage` text NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data untuk tabel `product`
--

INSERT INTO `product` (`ProductID`, `ProductName`, `ProductPrice`, `ProductStock`, `ProductImage`) VALUES
('PR001', 'Choco-Mint Ice Cream', 30000, 15, '/img/mint.png'),
('PR002', 'Dark Chocolate ice cream', 25000, 10, '/img/chocolate.png');

-- --------------------------------------------------------

--
-- Struktur dari tabel `user`
--

CREATE TABLE `user` (
  `UserID` char(5) NOT NULL,
  `UserEmail` text NOT NULL,
  `UserPassword` text NOT NULL,
  `UserGender` text NOT NULL,
  `UserDOB` date NOT NULL,
  `UserPhone` text NOT NULL,
  `UserAddress` text NOT NULL,
  `Role` text NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data untuk tabel `user`
--

INSERT INTO `user` (`UserID`, `UserEmail`, `UserPassword`, `UserGender`, `UserDOB`, `UserPhone`, `UserAddress`, `Role`) VALUES
('CU001', 'danny@icecream.com', 'tes123', 'Male', '1998-12-08', '081310035524', 'Jakarta Street', 'user'),
('CU002', 'admin@admin.com', 'admin', 'Male', '1998-12-08', '081310035524', 'Jakarta Street', 'staff');

--
-- Indexes for dumped tables
--

--
-- Indeks untuk tabel `detailtransaction`
--
ALTER TABLE `detailtransaction`
  ADD KEY `ProductID` (`ProductID`),
  ADD KEY `TransactionID` (`TransactionID`,`ProductID`);

--
-- Indeks untuk tabel `headertransaction`
--
ALTER TABLE `headertransaction`
  ADD PRIMARY KEY (`TransactionID`),
  ADD KEY `UserID` (`UserID`);

--
-- Indeks untuk tabel `product`
--
ALTER TABLE `product`
  ADD PRIMARY KEY (`ProductID`);

--
-- Indeks untuk tabel `user`
--
ALTER TABLE `user`
  ADD PRIMARY KEY (`UserID`);

--
-- Ketidakleluasaan untuk tabel pelimpahan (Dumped Tables)
--

--
-- Ketidakleluasaan untuk tabel `detailtransaction`
--
ALTER TABLE `detailtransaction`
  ADD CONSTRAINT `detailtransaction_ibfk_1` FOREIGN KEY (`ProductID`) REFERENCES `product` (`ProductID`),
  ADD CONSTRAINT `detailtransaction_ibfk_2` FOREIGN KEY (`TransactionID`) REFERENCES `headertransaction` (`TransactionID`);

--
-- Ketidakleluasaan untuk tabel `headertransaction`
--
ALTER TABLE `headertransaction`
  ADD CONSTRAINT `headertransaction_ibfk_1` FOREIGN KEY (`UserID`) REFERENCES `user` (`UserID`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;

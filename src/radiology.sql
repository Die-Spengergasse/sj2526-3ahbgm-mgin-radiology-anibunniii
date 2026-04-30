-- phpMyAdmin SQL Dump
-- version 5.2.1
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Erstellungszeit: 30. Apr 2026 um 08:23
-- Server-Version: 10.4.32-MariaDB
-- PHP-Version: 8.0.30

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Datenbank: `radiology`
--
CREATE DATABASE IF NOT EXISTS `radiology` DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci;
USE `radiology`;

-- --------------------------------------------------------

--
-- Tabellenstruktur für Tabelle `m_modalities`
--

DROP TABLE IF EXISTS `m_modalities`;
CREATE TABLE IF NOT EXISTS `m_modalities` (
  `m_id` int(11) NOT NULL AUTO_INCREMENT,
  `m_roomnr` int(11) DEFAULT NULL,
  `m_type` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`m_id`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Daten für Tabelle `m_modalities`
--

INSERT INTO `m_modalities` (`m_id`, `m_roomnr`, `m_type`) VALUES
(1, 0, 'MRI'),
(2, 102, 'CT'),
(3, 103, 'X-Ray'),
(4, 104, 'Ultrasound'),
(5, 105, 'PET-Scan');

-- --------------------------------------------------------

--
-- Tabellenstruktur für Tabelle `p_patients`
--

DROP TABLE IF EXISTS `p_patients`;
CREATE TABLE IF NOT EXISTS `p_patients` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `p_birth` date DEFAULT NULL,
  `p_ssn` bigint(20) DEFAULT NULL,
  `p_firstname` varchar(255) DEFAULT NULL,
  `p_gender` varchar(255) DEFAULT NULL,
  `p_lastname` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Daten für Tabelle `p_patients`
--

INSERT INTO `p_patients` (`id`, `p_birth`, `p_ssn`, `p_firstname`, `p_gender`, `p_lastname`) VALUES
(1, '2026-04-23', 123123123, 'ad', 'F', 'asd');

-- --------------------------------------------------------

--
-- Tabellenstruktur für Tabelle `reservation`
--

DROP TABLE IF EXISTS `reservation`;
CREATE TABLE IF NOT EXISTS `reservation` (
  `modality_id` int(11) DEFAULT NULL,
  `patient_id` int(11) DEFAULT NULL,
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `reservation_time` datetime(6) DEFAULT NULL,
  `body_region` varchar(255) DEFAULT NULL,
  `comment` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Daten für Tabelle `reservation`
--

INSERT INTO `reservation` (`modality_id`, `patient_id`, `id`, `reservation_time`, `body_region`, `comment`) VALUES
(1, 1, 1, '2026-04-23 09:06:00.000000', 'Pelvis', '');
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;

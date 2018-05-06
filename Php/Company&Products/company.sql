--
-- File generated with SQLiteStudio v3.1.1 on Mon May 7 02:59:54 2018
--
-- Text encoding used: System
--
PRAGMA foreign_keys = off;
BEGIN TRANSACTION;

-- Table: company
CREATE TABLE company (comp_id integer, name TEXT, username TEXT, email TEXT, contact TEXT, status BOOLEAN DEFAULT (0), block BOOLEAN DEFAULT (0), comp_address TEXT, pw TEXT, PRIMARY KEY (comp_id));
INSERT INTO company (comp_id, name, username, email, contact, status, block, comp_address, pw) VALUES (1, 'champion', 'champ', 'champ@email.com', '12345', 0, 0, 'st. nowelle, baguio city', 'champion');
INSERT INTO company (comp_id, name, username, email, contact, status, block, comp_address, pw) VALUES (2, 'adidas', 'adi', 'adi@email.com', '23456', 0, 0, 'st. Ares,baguio city', 'adidas
');
INSERT INTO company (comp_id, name, username, email, contact, status, block, comp_address, pw) VALUES (3, 'nautica', 'nau', 'nau@email.com', '34567', 0, 0, 'st. fayps, baguio city', 'nautica');
INSERT INTO company (comp_id, name, username, email, contact, status, block, comp_address, pw) VALUES (4, 'Hiit', 'hi', 'hii@email.com', '45678', 0, 0, 'st. nabilon,baguio city', 'hiit
');
INSERT INTO company (comp_id, name, username, email, contact, status, block, comp_address, pw) VALUES (5, 'Montague Burton', 'mon', 'mon@email.com', '56789', 0, 0, 'st. tequetl, baguio city', 'montagueburton');

COMMIT TRANSACTION;
PRAGMA foreign_keys = on;

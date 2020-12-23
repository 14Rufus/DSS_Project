USE DSS_Project;

INSERT INTO Prateleira VALUES 
	(1, 5, 1),
    (2, 5, 0),
    (3, 5, 0),
    (4, 5, 2),
    (5, 5, 1),
    (6, 5, 0),
    (7, 5, 1),
    (8, 5, 0),
    (9, 5, 0),
    (10, 5, 0);

INSERT INTO Localizacao VALUES 
	(1, "Armazenamento", 1),
    (2, "Armazenamento", 4),
    (3, "Armazenamento", 4),
    (4, "Armazenamento", null),
    (5, "Armazenamento", null),
    (6, "Rececao", null),
    (7, "Rececao", null),
    (8, "Rececao", null);

INSERT INTO Palete VALUES 
	('a1', 'Parecivel', 1),
    ('a2', 'Nao Parecivel', 2),
    ('a3', 'Nao Parecivel', 3),
    ('a4', 'Parecivel', 4),
    ('a5', 'Nao Parecivel', 5),
    ('a6', 'Parecivel', 6),
    ('a7', 'Nao Parecivel', 7);

INSERT INTO InfoTransporte VALUES (1, 'a6', 2);

INSERT INTO Robot VALUES 
	(1, 0, 0, 1, 6),
    (2, 1, 1, null, 7),
    (3, 1, 1, null, 8);

INSERT INTO Gestor VALUES ('1', 'Paulo Sousa', 'root', 0);
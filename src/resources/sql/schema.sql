CREATE TABLE Modele_voiture (
                                id SERIAL PRIMARY KEY,
                                marque VARCHAR(10) CHECK (marque IN ('KIA', 'HYUNDAI', 'DAEWOO')),
                                modele VARCHAR(10) CHECK (modele IN ('GETZ', 'PRIDE', 'LACETTI'))
);
CREATE TABLE Piece_auto (
                            id SERIAL PRIMARY KEY,
                            id_modele_voiture INT,
                            numero_serie VARCHAR(50),
                            prix NUMERIC,
                            FOREIGN KEY (id_modele_voiture) REFERENCES Modele_voiture(id)
);

CREATE TABLE Vente (
                       id SERIAL PRIMARY KEY,
                       id_piece_auto INT,
                       quantite INT,
                       FOREIGN KEY (id_piece_auto) REFERENCES Piece_auto(id)
);
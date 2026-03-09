SELECT
    Modele_voiture.marque,
    SUM(Vente.quantite) AS nbre_piece
FROM Vente
         JOIN Piece_auto ON Vente.id_piece_auto = Piece_auto.id
         JOIN Modele_voiture ON Piece_auto.id_modele_voiture = Modele_voiture.id
GROUP BY Modele_voiture.marque;

SELECT
    SUM(CASE WHEN Modele_voiture.modele = 'GETZ' THEN Vente.quantite ELSE 0 END) AS GETZ,
    SUM(CASE WHEN Modele_voiture.modele = 'PRIDE' THEN Vente.quantite ELSE 0 END) AS PRIDE,
    SUM(CASE WHEN Modele_voiture.modele = 'LACETTI' THEN Vente.quantite ELSE 0 END) AS LACETTI
FROM Vente
         JOIN Piece_auto ON Vente.id_piece_auto = Piece_auto.id
         JOIN Modele_voiture ON Piece_auto.id_modele_voiture = Modele_voiture.id;

SELECT
    SUM(Piece_auto.prix * Vente.quantite) AS prix_total
FROM Vente
         JOIN Piece_auto ON Vente.id_piece_auto = Piece_auto.id
         JOIN Modele_voiture ON Piece_auto.id_modele_voiture = Modele_voiture.id
WHERE Modele_voiture.marque = 'KIA';
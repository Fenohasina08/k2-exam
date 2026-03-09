import java.sql.*;
import java.util.List;
import java.util.ArrayList;

public class DataRetriever {
    private DBConnection dbConnection;

    public DataRetriever(DBConnection dbConnection) {
        this.dbConnection = dbConnection;
    }

    public List<PieceParMarque> findPiecesParMarque() {
        List<PieceParMarque> list = new ArrayList<>();

        try (Connection connection = dbConnection.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(
                     "SELECT Modele_voiture.marque, SUM(Vente.quantite) AS nbre_piece " +
                             "FROM Vente " +
                             "JOIN Piece_auto ON Vente.id_piece_auto = Piece_auto.id " +
                             "JOIN Modele_voiture ON Piece_auto.id_modele_voiture = Modele_voiture.id " +
                             "GROUP BY Modele_voiture.marque"
             );
             ResultSet resultSet = preparedStatement.executeQuery()) {

            while (resultSet.next()) {
                PieceParMarque piece = new PieceParMarque();
                piece.setMarque(resultSet.getString("marque"));
                piece.setNbrePiece(resultSet.getInt("nbre_piece"));
                list.add(piece);
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return list;
    }
    }

public PieceParModele findPiecesParModele() {
    PieceParModele piece = new PieceParModele();

    try (Connection connection = dbConnection.getConnection();
         PreparedStatement preparedStatement = connection.prepareStatement(
                 "SELECT " +
                         "SUM(CASE WHEN Modele_voiture.modele = 'GETZ' THEN Vente.quantite ELSE 0 END) AS GETZ, " +
                         "SUM(CASE WHEN Modele_voiture.modele = 'PRIDE' THEN Vente.quantite ELSE 0 END) AS PRIDE, " +
                         "SUM(CASE WHEN Modele_voiture.modele = 'LACETTI' THEN Vente.quantite ELSE 0 END) AS LACETTI " +
                         "FROM Vente " +
                         "JOIN Piece_auto ON Vente.id_piece_auto = Piece_auto.id " +
                         "JOIN Modele_voiture ON Piece_auto.id_modele_voiture = Modele_voiture.id"
         );
         ResultSet resultSet = preparedStatement.executeQuery()) {

        if (resultSet.next()) {
            piece.setGetz(resultSet.getInt("GETZ"));
            piece.setPride(resultSet.getInt("PRIDE"));
            piece.setLacetti(resultSet.getInt("LACETTI"));
        }

    } catch (SQLException e) {
        throw new RuntimeException(e);
    }

    return piece;
}
}


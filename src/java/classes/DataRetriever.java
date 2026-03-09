import java.sql.*;
import java.util.List;
import java.util.ArrayList;

public class DataRetriever {
    private DBConnection dbConnection;

    public DataRetriever(DBConnection dbConnection) {
        this.dbConnection = dbConnection;
    }

    public List<PieceParMarque> getPiecesParMarque(Connection connection)  {

        List<PieceParMarque> resultat = new ArrayList<>();

        String sql = """
        SELECT Modele_voiture.marque,
               SUM(Vente.quantite) AS nbre_piece
        FROM Vente
        JOIN Piece_auto ON Vente.id_piece_auto = Piece_auto.id
        JOIN Modele_voiture ON Piece_auto.id_modele_voiture = Modele_voiture.id
        GROUP BY Modele_voiture.marque
    """;

        Statement statement = connection.createStatement();
        ResultSet resultSet = statement.executeQuery(sql);

        while (resultSet.next()) {

            PieceParMarque piece = new PieceParMarque();

            piece.setMarque(resultSet.getString("marque"));
            piece.setNbrePiece(resultSet.getInt("nbre_piece"));

            resultat.add(piece);
        }

        return resultat;
    }

    public List<PieceParMarque> getPiecesParMarque(Connection connection)  {

        List<PieceParMarque> resultat = new ArrayList<>();

        String sql = """
        SELECT Modele_voiture.marque,
               SUM(Vente.quantite) AS nbre_piece
        FROM Vente
        JOIN Piece_auto ON Vente.id_piece_auto = Piece_auto.id
        JOIN Modele_voiture ON Piece_auto.id_modele_voiture = Modele_voiture.id
        GROUP BY Modele_voiture.marque
    """;

        Statement statement = connection.createStatement();
        ResultSet resultSet = statement.executeQuery(sql);

        while (resultSet.next()) {

            PieceParMarque piece = new PieceParMarque();

            piece.setMarque(resultSet.getString("marque"));
            piece.setNbrePiece(resultSet.getInt("nbre_piece"));

            resultat.add(piece);
        }

        return resultat;
    }
}


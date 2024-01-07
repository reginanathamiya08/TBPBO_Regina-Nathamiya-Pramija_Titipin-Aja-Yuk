import java.sql.*;

public interface Penitipan {
    void jenisHelm()throws SQLException;
    void noPenitipan()throws SQLException;
    void namaPemilik()throws SQLException;
    void jamMasuk()throws SQLException;
    void jamKeluar()throws SQLException;
    void bayar()throws SQLException;
}

import java.sql.DriverManager
import java.sql.PreparedStatement


fun main() {

    DriverManager.getConnection(
        "jdbc:mysql://localhost:3306/world",
        "root", "qwe123"
    ).use {conn ->
        println("Connected to database")
        conn.prepareStatement("select id, name, surname from PERSONAL_DATA where id = ?").use { pstm: PreparedStatement ->
            pstm.setLong(1, 1)
            pstm.executeQuery().use { rs ->
                while (rs.next()) {
                    val id = rs.getLong("id")
                    val name = rs.getString("name")
                    val surname = rs.getString("surname")
                    println("user $id = $name $surname")
                }
            }
        }
    }
}
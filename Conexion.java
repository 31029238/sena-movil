import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class Conexion {
    public static void main(String[] args) {
        String url = "jdbc:mariadb://localhost:3306/sena_db";
        String user = "root";
        String password = ""; 

        try {
            Class.forName("org.mariadb.jdbc.Driver");
            Connection con = DriverManager.getConnection(url, user, password);
            
            // --- ESTA ES LA PARTE NUEVA ---
            // 1. Crear un mensajero (Statement)
            Statement stmt = con.createStatement();
            
            // 2. Ejecutar la consulta SQL
            ResultSet rs = stmt.executeQuery("SELECT * FROM aprendices");
            
            System.out.println("--- LISTA DE APRENDICES ---");
            
            // 3. Recorrer los resultados fila por fila
            while(rs.next()) {
                // Sacamos los datos de las columnas por su nombre
                String nombre = rs.getString("nombre");
                String programa = rs.getString("programa");
                
                System.out.println("Estudiante: " + nombre + " | Curso: " + programa);
            }
            // -----------------------------
            
            con.close();
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
    }
}


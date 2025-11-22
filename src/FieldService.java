import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class FieldService {

   // CRUD 
   public boolean addField(FutsalField field) {
      String sql = "INSERT INTO futsal_fields (field_name, open_time, close_time, price_per_session) VALUES (?, ?, ?, ?)";

      try (Connection conn = DriverManager.getConnection(
            DatabaseConfig.getUrl(),
            DatabaseConfig.getUsername(),
            DatabaseConfig.getPassword());
            PreparedStatement pstmt = conn.prepareStatement(sql)) {

         pstmt.setString(1, field.getFieldName());
         pstmt.setString(2, field.getOpenTime());
         pstmt.setString(3, field.getCloseTime());
         pstmt.setDouble(4, field.getPricePerSession());

         int affectedRows = pstmt.executeUpdate();
         return affectedRows > 0;

      } catch (SQLException e) {
         e.printStackTrace();
         return false;
      }
   }

   public List<FutsalField> getAllFields() {
      List<FutsalField> fields = new ArrayList<>();
      String sql = "SELECT * FROM futsal_fields ORDER BY field_name";

      try (Connection conn = DriverManager.getConnection(
            DatabaseConfig.getUrl(),
            DatabaseConfig.getUsername(),
            DatabaseConfig.getPassword());
            PreparedStatement pstmt = conn.prepareStatement(sql);
            ResultSet rs = pstmt.executeQuery()) {

         while (rs.next()) {
            FutsalField field = new FutsalField(
                  rs.getInt("id"),
                  rs.getString("field_name"),
                  rs.getString("open_time"),
                  rs.getString("close_time"),
                  rs.getDouble("price_per_session"),
                  rs.getBoolean("is_active"),
                  rs.getString("created_at"),
                  rs.getString("updated_at"));
            fields.add(field);
         }

      } catch (SQLException e) {
         e.printStackTrace();
      }

      return fields;
   }

   public FutsalField getFieldById(int id) {
      String sql = "SELECT * FROM futsal_fields WHERE id = ?";

      try (Connection conn = DriverManager.getConnection(
            DatabaseConfig.getUrl(),
            DatabaseConfig.getUsername(),
            DatabaseConfig.getPassword());
            PreparedStatement pstmt = conn.prepareStatement(sql)) {

         pstmt.setInt(1, id);
         ResultSet rs = pstmt.executeQuery();

         if (rs.next()) {
            return new FutsalField(
                  rs.getInt("id"),
                  rs.getString("field_name"),
                  rs.getString("open_time"),
                  rs.getString("close_time"),
                  rs.getDouble("price_per_session"),
                  rs.getBoolean("is_active"),
                  rs.getString("created_at"),
                  rs.getString("updated_at"));
         }

      } catch (SQLException e) {
         e.printStackTrace();
      }

      return null;
   }

   public boolean updateField(FutsalField field) {
      String sql = "UPDATE futsal_fields SET field_name = ?, open_time = ?, close_time = ?, " +
            "price_per_session = ?, is_active = ? WHERE id = ?";

      try (Connection conn = DriverManager.getConnection(
            DatabaseConfig.getUrl(),
            DatabaseConfig.getUsername(),
            DatabaseConfig.getPassword());
            PreparedStatement pstmt = conn.prepareStatement(sql)) {

         pstmt.setString(1, field.getFieldName());
         pstmt.setString(2, field.getOpenTime());
         pstmt.setString(3, field.getCloseTime());
         pstmt.setDouble(4, field.getPricePerSession());
         pstmt.setBoolean(5, field.isActive());
         pstmt.setInt(6, field.getId());

         int affectedRows = pstmt.executeUpdate();
         return affectedRows > 0;

      } catch (SQLException e) {
         e.printStackTrace();
         return false;
      }
   }

   public boolean deleteField(int id) {
      String sql = "DELETE FROM futsal_fields WHERE id = ?";

      try (Connection conn = DriverManager.getConnection(
            DatabaseConfig.getUrl(),
            DatabaseConfig.getUsername(),
            DatabaseConfig.getPassword());
            PreparedStatement pstmt = conn.prepareStatement(sql)) {

         pstmt.setInt(1, id);
         int affectedRows = pstmt.executeUpdate();
         return affectedRows > 0;

      } catch (SQLException e) {
         e.printStackTrace();
         return false;
      }
   }

}
import javax.swing.table.AbstractTableModel;
import java.util.ArrayList;
import java.util.List;

public class FieldsTableModel extends AbstractTableModel {
   private List<FutsalField> fields;
   private String[] columnNames = { "ID", "Field Name", "Open Time", "Close Time", "Price", "Active", "Created" };

   public FieldsTableModel() {
      this.fields = new ArrayList<>();
   }

   public void setFields(List<FutsalField> fields) {
      this.fields = fields;
   }

   public FutsalField getFieldAt(int row) {
      return fields.get(row);
   }

   @Override
   public int getRowCount() {
      return fields.size();
   }

   @Override
   public int getColumnCount() {
      return columnNames.length;
   }

   @Override
   public String getColumnName(int column) {
      return columnNames[column];
   }

   @Override
   public Object getValueAt(int row, int column) {
      FutsalField field = fields.get(row);

      switch (column) {
         case 0:
            return field.getId();
         case 1:
            return field.getFieldName();
         case 2:
            return field.getOpenTime().substring(0, 5); // Format HH:mm
         case 3:
            return field.getCloseTime().substring(0, 5);
         case 4:
            return String.format("Rp %,d", (int) field.getPricePerSession());
         case 5:
            return field.isActive() ? "Yes" : "No";
         case 6:
            return field.getCreatedAt().substring(0, 10);
         default:
            return null;
      }
   }
}
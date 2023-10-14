package archivo;

import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import java.awt.*;

public class MultiLineTableCellRenderer extends DefaultTableCellRenderer {

    @Override
    public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
        if (value != null) {
            String text = value.toString();
            // Reemplaza los saltos de línea con etiquetas HTML para mostrarlos correctamente
            text = "<html>" + text.replace("\n", "<br>") + "</html>";
            value = text;
        }
        return super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
    }
}

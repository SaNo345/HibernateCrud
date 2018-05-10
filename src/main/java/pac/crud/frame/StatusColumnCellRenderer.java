package pac.crud.frame;

import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import java.awt.*;

/**
* Created by sano on 4/12/18.
*/
class StatusColumnCellRenderer extends DefaultTableCellRenderer {
    int row1;
    int col1;

    public StatusColumnCellRenderer(int row , int col ){
        this.row1 =row;
        this.col1=col;
    }
    @Override
    public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int col) {
        //Cells are by default rendered as a JLabel.
         super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, col);


        if(table.getValueAt(row,col)!=null) {
            if(table.getValueAt(row,col).equals("dis")){
                setBackground(Color.gray);
            }else{
                setBackground(Color.green);
            }

        } else{
            setBackground(Color.red);
        }
        return this;

    }
}

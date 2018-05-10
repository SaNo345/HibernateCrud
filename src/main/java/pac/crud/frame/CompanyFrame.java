package pac.crud.frame;

import net.miginfocom.swing.MigLayout;
import pac.crud.dao.CompanyList;
import pac.crud.dao.OrderList;
import pac.crud.model.Company;
import pac.crud.model.Order;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;

public class CompanyFrame extends JFrame implements ActionListener {

	private static final long serialVersionUID = 1L;
    private JScrollPane scrollPane;
    private JScrollPane scrollPane_1;
    private JTable tableCompany;
    private JTable tableOrder;
    private static DefaultTableModel modelCompany = new DefaultTableModel();
    private static DefaultTableModel modelOrder = new DefaultTableModel();
    private String[] columnNames = { "Անուն", "ՀՎՀՀ", "Տարածքի Չափը", "Փաստացի Հասցե","ID" };
    private String[] ordercolumnNames = { "ID", "Հունվար", "Փետրվար", "Մարտ","Ապրիլ","Մայիս","Հունիս","Հուլիս","Օգոստոս","Սեպտեմբեր","Հոկտեմբեր","Նոյեմբեր","Դեկտեմբեր"};
    private Object [] optionNames={"Ջնջել","Չեղարկել"};
    private Integer[] comboOptions={2016,2017,2018};
    private JTextField textField_4;
    private JTextField textField_1;
    private JTextField textField_2;
    private JTextField textField_3;
    private JButton btnDelete;
    private JButton btnAdd;
    private JButton btnUpdate;
    private JButton btnPay;
    private JButton btnSearch;
    private JButton btnRefesh;
    private JPanel inputPanel;
    private JPanel tablePanel;
    private JComboBox comboBox;
    private CompanyList companyList = new CompanyList();

	public CompanyFrame() {
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setExtendedState(JFrame.MAXIMIZED_BOTH);
        setTitle("Կազմակաերպություն");

        getContentPane().setPreferredSize(new Dimension(600, 1000));
        getContentPane().setLayout(new MigLayout("", "[39.00][][14.00][]", "[][153.00][68.00]"));

//USer input panel****************
        inputPanel = new JPanel();
        inputPanel.setLayout(new MigLayout("align 50% 50%", "[31.00][117.00,left][274.00,grow][27.00]",
                "[35.00][35.00][35.00][35.00][35.00][35.00][35.00][31.00][31.00][][][][][]"));
        inputPanel.setPreferredSize(new Dimension(462, 507));

        JLabel label_1 = new JLabel("Անուն");
        textField_1 = new JTextField(15);

        JLabel label_2 = new JLabel("ՀՎՀՀ");
        textField_2 = new JTextField(15);

        JLabel label_3 = new JLabel("Տարածքի Չափը");
        textField_3 = new JTextField(15);

        JLabel label_4 = new JLabel("Փաստացի Հասցե");
        textField_4 = new JTextField(15);


        inputPanel.add(label_1, "cell 1 0,alignx left");
        inputPanel.add(textField_1, "cell 2 0");
        inputPanel.add(label_2, "cell 1 1,alignx left");
        inputPanel.add(textField_2, "cell 2 1");
        inputPanel.add(label_3, "cell 1 2,alignx left");
        inputPanel.add(textField_3, "cell 2 2");
        inputPanel.add(label_4, "cell 1 3,alignx left");
        inputPanel.add(textField_4, "cell 2 3");


        JLabel lblYear = new JLabel("Year");
        inputPanel.add(lblYear, "cell 1 6,alignx left");

        comboBox = new JComboBox(comboOptions);
        comboBox.setSelectedIndex(2);

        inputPanel.add(comboBox, "cell 2 6,alignx left");

        btnAdd = new JButton("Պահպանել");
        btnAdd.setPreferredSize(new Dimension(140, 10));
        btnAdd.addActionListener(this);
        inputPanel.add(btnAdd, "cell 1 7,alignx left");

        btnDelete = new JButton("Ջնջել");
        btnDelete.setPreferredSize(new Dimension(140, 10));
        btnDelete.addActionListener(this);
        inputPanel.add(btnDelete, "cell 2 7");

        btnPay = new JButton("Վճարել");
        btnPay.setPreferredSize(new Dimension(140, 10));
        btnPay.addActionListener(this);
        inputPanel.add(btnPay, "cell 1 8,alignx left");

        btnUpdate = new JButton("Փոփոխել");
        btnUpdate.setPreferredSize(new Dimension(140, 10));
        btnUpdate.addActionListener(this);
        inputPanel.add(btnUpdate, "cell 2 8");

        btnSearch = new JButton("Փնտրել");
        btnSearch.setPreferredSize(new Dimension(140, 10));
        btnSearch.addActionListener(this);
        inputPanel.add(btnSearch, "cell 1 9");

        btnRefesh = new JButton("Թարմացնել");
        btnRefesh.setPreferredSize(new Dimension(140, 10));
        btnRefesh.addActionListener(this);
        inputPanel.add(btnRefesh, "cell 2 9");
        //Table Panel ****************
        modelCompany.setColumnIdentifiers(columnNames);
        modelOrder.setColumnIdentifiers(ordercolumnNames);
        tablePanel = new JPanel();
        tablePanel.setLayout(new BorderLayout(0, 0));
        tablePanel.setPreferredSize(new Dimension(900, 400));
        JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
        tabbedPane.setTabLayoutPolicy(JTabbedPane.SCROLL_TAB_LAYOUT);
        tablePanel.add(tabbedPane);


        tableCompany = new JTable();
        tableCompany.setModel(modelCompany);
        tableCompany.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);
        tableCompany.setFillsViewportHeight(true);
        scrollPane= new JScrollPane(tableCompany);
        tabbedPane.addTab("Կազմակերպություն", null,scrollPane, null);

        tableOrder = new JTable();
        tableOrder.setModel(modelOrder);
        tableOrder.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);
        tableOrder.setFillsViewportHeight(true);
        scrollPane_1 = new JScrollPane(tableOrder);

        tabbedPane.addTab("Պարտք", null, scrollPane_1, null);
//******************
        getContentPane().add(inputPanel, "cell 1 1 1 2,grow");
        getContentPane().add(tablePanel, "cell 2 1 2 2,grow");

        fillcompanyData(companyList.getAllCompany());
        fillorderData(companyList.getAllCompany());

        comboBox.addActionListener(actionEvent -> {
                fillorderData(companyList.getAllCompany());
        });

	}

	@Override
	public void actionPerformed(ActionEvent e) {
        if (e.getSource() == btnAdd) {

            Company company = new Company();
            String name = textField_1.getText();
            String hvhh = textField_2.getText();
            double areaofsqure;
            String facAddress = textField_3.getText();

            try {
                areaofsqure = Double.parseDouble(textField_4.getText());
            } catch (NumberFormatException exc) {
                areaofsqure = 0.0;
            }
            //input validation  if not input argument show err message , do noting!
            if (name.equals("") || hvhh.equals("") || facAddress.equals("")) {
                JOptionPane.showMessageDialog(null, " Դատարկ տողի մուտքագրում չի թույլատրվում");
            } else {
                company.setName(name);
                company.setHvhh(hvhh);
                company.setAreaofspace(areaofsqure);
                company.setFactualAddress(facAddress);
                companyList.addCompany(company);

                textField_4.setText("");
                textField_1.setText("");
                textField_2.setText("");
                textField_3.setText("");

                fillcompanyData(companyList.getAllCompany());
            }

        }
        if (e.getSource() == btnDelete) {
            int[] selecttedRows = tableCompany.getSelectedRows();
            int selectedDataId;
            //if selected noting
            if (selecttedRows.length == 0) {
                JOptionPane.showMessageDialog(null, "Օբյեկտ նշված չէ");
                //if selected one object
            } else if (selecttedRows.length==1 ) {
                selectedDataId = (int) modelCompany.getValueAt(selecttedRows[0], 4);
                int optionResult = JOptionPane.showOptionDialog(null, new JPanel(), "Հեռացնել՞ նշված օբյեկտը ",
                        JOptionPane.YES_NO_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE, null, optionNames, null);
                if (optionResult == JOptionPane.YES_OPTION) {
                    companyList.deleteCompany(selectedDataId);
                    fillcompanyData(companyList.getAllCompany());
                    tableCompany.getSelectionModel().clearSelection();
                }

            } else {//if selected more object
                int optionResult = JOptionPane.showOptionDialog(null, new JPanel(), "Հեռացնել՞ նշված  " + selecttedRows.length + " օբյեկտները",
                        JOptionPane.YES_NO_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE, null, optionNames, null);
                if (optionResult == JOptionPane.YES_OPTION) {
                    for (int i = 0; i < selecttedRows.length ; i++) {
                        selectedDataId = (int) modelCompany.getValueAt(selecttedRows[i], 4);
                        companyList.deleteCompany(selectedDataId);
                    }
                    fillcompanyData(companyList.getAllCompany());
                    tableCompany.getSelectionModel().clearSelection();
                }
            }
        }
        if(e.getSource()==btnUpdate) {
            if (tableCompany.getSelectedRow() == -1) {
                JOptionPane.showMessageDialog(null, "Օբյեկտ նշված չէ");
            } else {
                if(tableCompany.isEditing()){
                    tableCompany.getCellEditor().stopCellEditing();
                }
                String comName=(String) modelCompany.getValueAt(tableCompany.getSelectedRow(), 0);
                String comHvhh=(String) modelCompany.getValueAt(tableCompany.getSelectedRow(), 1);

                //modify !!!!!!! Class cast exeption
                double comArea=Double.parseDouble( (String)modelCompany.getValueAt(tableCompany.getSelectedRow(), 2));
                String comFacaddress=(String) modelCompany.getValueAt(tableCompany.getSelectedRow(), 3);
                int selctedId = (int) modelCompany.getValueAt(tableCompany.getSelectedRow(), 4);

                Company updateCompany = new Company();
                updateCompany.setCompanyid(selctedId);
                updateCompany.setName(comName);
                updateCompany.setHvhh(comHvhh);
                updateCompany.setAreaofspace(comArea);
                updateCompany.setFactualAddress(comFacaddress);
                companyList.updateCompany(updateCompany);
                //update tableCompany
                fillcompanyData(companyList.getAllCompany());
                fillorderData(companyList.getAllCompany());
                tableCompany.getSelectionModel().clearSelection();

            }

        }if(e.getSource()==btnPay) {
            if (tableCompany.getSelectedRow() == -1 || tableCompany.getSelectedRowCount() > 1) {
                JOptionPane.showMessageDialog(null, "Օբյեկտ նշված չէ կամ նշված է մեկից ավելի ");
            } else {
                OrderList orderList = new OrderList();

                JDialog payWindow = new JDialog();
                payWindow.setSize(new Dimension(300, 300));
                payWindow.setLayout(null);
                JLabel nLabel = new JLabel("Անուն");
                nLabel.setBounds(10, 40, 100, 20);
                JLabel monyLabel = new JLabel("Գումար");
                monyLabel.setBounds(10, 80, 100, 20);
                JLabel yearLabel = new JLabel("Տարի");
                yearLabel.setBounds(10, 120, 100, 20);
                JLabel mountLabel = new JLabel("Ամիս");
                mountLabel.setBounds(10, 160, 100, 20);

                JTextField nTextField = new JTextField();
                nTextField.setBounds(110, 40, 100, 20);
                Company payCompany = companyList.findCompanyById(
                        (Integer) tableCompany.getValueAt(tableCompany.getSelectedRow(), 4));
                nTextField.setText(payCompany.getName());
                nTextField.setEnabled(false);

                JTextField monyTextField = new JTextField();
                monyTextField.setBounds(110, 80, 100, 20);
                monyTextField.setText("600");
                monyTextField.setEnabled(false);

                JComboBox yerComboBox = new JComboBox(new Integer[]{2016,2017,2018,2019});
                yerComboBox.setSelectedIndex(2);
                yerComboBox.setBounds(110,120,100,20);

                JComboBox mountComboBox = new JComboBox(new Integer[]{1,2,3,4,5,6,7,8,9,10,11,12});
                mountComboBox.setSelectedIndex(0);
                mountComboBox.setBounds(110,160,100,20);
                JButton paybtn= new JButton("Վճարել");
                paybtn.setBounds(40,200,100,20);
                paybtn.addActionListener(actionEvent -> {
                    Order order = new Order();
                    order.setCompany(payCompany);
                    order.setMount((Integer) mountComboBox.getSelectedItem());
                    order.setYear((Integer) yerComboBox.getSelectedItem());
                    order.setScore(Integer.parseInt(monyTextField.getText()));
                    orderList.addOrder(order);
                    JOptionPane.showMessageDialog(null, "Okey");


                });
                payWindow.add(nLabel);
                payWindow.add(nTextField);
                payWindow.add(monyLabel);
                payWindow.add(yearLabel);
                payWindow.add(mountLabel);
                payWindow.add(monyTextField);
                payWindow.add(yerComboBox);
                payWindow.add(mountComboBox);
                payWindow.add(paybtn);
                payWindow.setVisible(true);

                fillorderData(companyList.getAllCompany());
            }

        }
        if(e.getSource() == btnSearch){
            String name = textField_1.getText();
            String hvhh = textField_2.getText();
            double areaofsqure;
            String facAddress = textField_3.getText();

            try {
                areaofsqure = Double.parseDouble(textField_4.getText());
            } catch (NumberFormatException exc) {
                areaofsqure = -1;
            }
            List<Company> ollCompanies = companyList.getAllCompany();
            List<Company> findCompanies =new ArrayList<>();
            boolean find = false;
            for (Company company:ollCompanies) {
                if (!name.isEmpty()) {
                    if (company.getName().equals(name)) {
                        find = true;
                    } else {
                        find = false;
                    }
                }
                if (!hvhh.isEmpty()) {
                    if (company.getHvhh().equals(hvhh)) {
                        find = true;
                    } else {
                        find = false;
                    }
                }
                if (areaofsqure !=-1) {
                    if (company.getAreaofspace()==areaofsqure) {
                        find = true;
                    } else {
                        find = false;
                    }
                }if (!facAddress.isEmpty()) {
                    if (company.getFactualAddress().equals(facAddress)) {
                        find = true;
                    } else {
                        find = false;
                    }
                }

                if(find)
                    findCompanies.add(company);
            }
            fillcompanyData(findCompanies);
            fillorderData(findCompanies);
        }
        if(e.getSource() == btnRefesh){
            fillcompanyData(companyList.getAllCompany());
            fillorderData(companyList.getAllCompany());
        }
	}



//clean tableCompany and show new data
	public void fillcompanyData(List<Company> companies) {
		claenCompanyTableData();
        for (Company com : companies) {
			modelCompany.addRow(
			        new Object[] { com.getName(), com.getHvhh(), com.getAreaofspace(), com.getFactualAddress(),com.getCompanyid()});

		}
	}

	public void claenCompanyTableData() {
		int rowCount = modelCompany.getRowCount();
		// Remove rows one by one from the end of the tableCompany
		for (int i = rowCount - 1; i >= 0; i--) {
			modelCompany.removeRow(i);
		}
	}

	public void fillorderData(List<Company> companies){
	    claenOrderTableData();
	    int year= (int)comboBox.getSelectedItem();
	    for(Company company:companies){
            Object []row = new Object[13];
	           row[0]=company.getCompanyid();
	             for(Order order:company.getOrders()){
                     if(order.getYear()==year){
	                     row[order.getMount()]=order.getScore();
                     }
                 }

            modelOrder.addRow(row);

            for(int i = 1; i< tableOrder.getColumnCount(); i++) {
                tableOrder.getColumnModel().getColumn(i).setCellRenderer(new StatusColumnCellRenderer(0, 1));
            }
        }

    }
    public void claenOrderTableData() {
        int rowCount = modelOrder.getRowCount();
        // Remove rows one by one from the end of the tableCompany
        for (int i = rowCount - 1; i >= 0; i--) {
            modelOrder.removeRow(i);
        }
    }
}




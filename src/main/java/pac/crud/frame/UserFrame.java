package pac.crud.frame;

import java.awt.*;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Date;
import java.util.*;
import java.util.List;

import net.miginfocom.swing.MigLayout;
import pac.crud.dao.OrderList;
import pac.crud.dao.UserList;
import pac.crud.model.Order;
import pac.crud.model.User;

public class UserFrame extends JFrame implements ActionListener{

	private static final long serialVersionUID = 1L;
	private JTable tableUser;
	private JTable tableOrder;
	private JScrollPane scrollPane;
	private JScrollPane scrollPane_1;
	private static DefaultTableModel modelUsers = new DefaultTableModel();
	private static DefaultTableModel modelOrder = new DefaultTableModel();
	private String[] columnNames = { "Անուն", "Ազգանուն", "Հայրանուն", "Գյուղ", "Հասցե", "Մարդկանց քանակ", "ID" };
	private Object[] optionNames = { "Ջնջել", "Չեղարկել" };
	private String[] ordercolumnNames = { "ID", "Հունվար", "Փետրվար", "Մարտ","Ապրիլ","Մայիս","Հունիս","Հուլիս","Օգոստոս","Սեպտեմբեր","Հոկտեմբեր","Նոյեմբեր","Դեկտեմբեր"};
	private Integer[] comboOptions={2016,2017,2018};
	private JButton btnAdd;
	private JButton btnDelete;
	private JButton btnPay;
	private JButton btnUpdate;
	private JButton btnSearch;
	private JButton btnRefesh;
	private JButton btnActiv;
	private JTextField textField_6;
	private JTextField textField_1;
	private JTextField textField_2;
	private JTextField textField_3;
	private JTextField textField_4;
	private JTextField textField_5;
    private JPanel inputPanel;
    private JPanel tablePanel;
    private JComboBox comboBox;
    private  final int sakagin=100;

	private UserList userList = new UserList();



	public UserFrame() {

		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setExtendedState(JFrame.MAXIMIZED_BOTH);
        setTitle("Օգտագործող");

		getContentPane().setPreferredSize(new Dimension(600, 1000));
		getContentPane().setLayout(new MigLayout("", "[39.00][][14.00][]", "[][153.00][68.00]"));

//USer input panel****************
		inputPanel = new JPanel();
		inputPanel.setLayout(new MigLayout("align 50% 50%", "[31.00][117.00,left][274.00,grow][27.00]",
				"[35.00][35.00][35.00][35.00][35.00][35.00][35.00][31.00][31.00][][][][][][][]"));
		inputPanel.setPreferredSize(new Dimension(462, 507));

		JLabel label_1 = new JLabel("Անուն");
		textField_1 = new JTextField(15);

		JLabel label_2 = new JLabel("Ազգանուն");
		textField_2 = new JTextField(15);

		JLabel label_3 = new JLabel("Հայրանուն");
		textField_3 = new JTextField(15);

		JLabel label_4 = new JLabel("Գյուղ");
		textField_4 = new JTextField(15);

		JLabel label_5 = new JLabel("Հասցե");
		textField_5 = new JTextField(15);

		JLabel label_6 = new JLabel("Մարդկանց քանակ");
		textField_6 = new JTextField(15);

		inputPanel.add(label_1, "cell 1 0,alignx left");
		inputPanel.add(textField_1, "cell 2 0");
		inputPanel.add(label_2, "cell 1 1,alignx left");
		inputPanel.add(textField_2, "cell 2 1");
		inputPanel.add(label_3, "cell 1 2,alignx left");
		inputPanel.add(textField_3, "cell 2 2");
		inputPanel.add(label_4, "cell 1 3,alignx left");
		inputPanel.add(textField_4, "cell 2 3");
		inputPanel.add(label_5, "cell 1 4,alignx left");
		inputPanel.add(textField_5, "cell 2 4");
		inputPanel.add(label_6, "cell 1 5,alignx left");
		inputPanel.add(textField_6, "cell 2 5");

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

		btnActiv = new JButton("Ակտիվիություն");
		btnActiv.setPreferredSize(new Dimension(140, 10));
		btnActiv.addActionListener(this);
		inputPanel.add(btnActiv, "cell 1 10");
		//Table Panel ****************
		modelUsers.setColumnIdentifiers(columnNames);
		tablePanel = new JPanel();
		tablePanel.setLayout(new BorderLayout(0, 0));
		tablePanel.setPreferredSize(new Dimension(900, 400));
		JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		tabbedPane.setTabLayoutPolicy(JTabbedPane.SCROLL_TAB_LAYOUT);
		tablePanel.add(tabbedPane);


		tableUser = new JTable();
		tableUser.setModel(modelUsers);
		tableUser.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);
		tableUser.setFillsViewportHeight(true);
		scrollPane= new JScrollPane(tableUser);
		tabbedPane.addTab("Օգտագործող", null,scrollPane, null);

		modelOrder.setColumnIdentifiers(ordercolumnNames);
		tableOrder = new JTable();
		tableOrder.setModel(modelOrder);
		tableOrder.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);
		tableOrder.setFillsViewportHeight(true);
		scrollPane_1 = new JScrollPane(tableOrder);
		tabbedPane.addTab("Պարտք", null, scrollPane_1, null);
//******************
		getContentPane().add(inputPanel, "cell 1 1 1 2,grow");
		getContentPane().add(tablePanel, "cell 2 1 2 2,grow");

		fillUsersTableData(userList.getAllUsers());
		fillorderData(userList.getAllUsers());
		comboBox.addActionListener(actionEvent -> {
			claenOrderTableData();
			fillorderData(userList.getAllUsers());
		});

	}

	public void fillUsersTableData(List<User> users) {
		claenUsersTableData();
		for (User us : users) {
			modelUsers.addRow(new Object[] { us.getName(), us.getSrname(), us.getPatronymic(), us.getVillage(),
					us.getAddress(), us.getPepolecount(), us.getUserid() });

		}

	}

	public void claenUsersTableData() {
		int rowCount = modelUsers.getRowCount();
		// Remove rows one by one from the end of the tableUser
		for (int i = rowCount - 1; i >= 0; i--) {
			modelUsers.removeRow(i);
		}
	}


	public void fillorderData(List<User> users){
		claenOrderTableData();
		int year= (int)comboBox.getSelectedItem();
		for(User user:users){
			Object []row = new Object[13];
			row[0]=user.getUserid();
			for(Order order:user.getOrders()){
				if(order.getYear()==year){
					row[order.getMount()]=sakagin*user.getPepolecount();
				}
			}
			try {
				int styear = user.getEnable_start().getYear();
				if (styear + 1900 == year) {
					for (int i = user.getEnable_start().getMonth(); i <= user.getEnable_end().getMonth(); i++) {
						row[i]="dis";
					}
				}
			}catch (NullPointerException x){
				System.out.println("exeption usr .get date");
			}

			modelOrder.addRow(row);
			try {
				for (int i = 1; i < tableOrder.getColumnCount(); i++) {
					tableOrder.getColumnModel().getColumn(i).setCellRenderer(new StatusColumnCellRenderer(0, 1));
				}
			}catch (NullPointerException e){
				System.out.println("exption in table color renderer");
			}

		}



	}

	public void claenOrderTableData() {
		int rowCount = modelOrder.getRowCount();
		// Remove rows one by one from the end of the tableUser
		for (int i = rowCount - 1; i >= 0; i--) {
			modelOrder.removeRow(i);
		}
	}
	public void actionPerformed(ActionEvent e) {
			// temp

			if (e.getSource() == btnAdd) {
				User user = new User();
				String name = textField_1.getText();
				String srname = textField_2.getText();
				String patronymic = textField_3.getText();
				String village = textField_4.getText();
				String address = textField_5.getText();
				int pepolecount;
				try {
					pepolecount = Integer.parseInt(textField_6.getText());
				} catch (NumberFormatException exc) {
					pepolecount = 0;
				}
				// input validation if not input argument show err message , do
				// noting!
				if (name.equals("") || srname.equals("") || patronymic.equals("") || village.equals("")
						|| address.equals("")) {
					JOptionPane.showMessageDialog(null, " Դատարկ տողի մուտքագրում չի թույլատրվում");

				} else {
					user.setName(name);
					user.setSrname(srname);
					user.setPatronymic(patronymic);
					user.setVillage(village);
					user.setAddress(address);
					user.setPepolecount(pepolecount);
					userList.addUser(user);

					textField_1.setText("");
					textField_2.setText("");
					textField_3.setText("");
					textField_4.setText("");
					textField_5.setText("");
					textField_6.setText("");

					fillUsersTableData(userList.getAllUsers());
				}
			}

			if (e.getSource() == btnDelete) {
				int[] selecttedRows = tableUser.getSelectedRows();
				int selectedDataId;

				if (selecttedRows.length == 0) {
					JOptionPane.showMessageDialog(null, "Օբյեկտ նշված չէ");

				} else if (selecttedRows.length == 1) {
					int optionResult = JOptionPane.showOptionDialog(null, new JPanel(), "Հեռացնել՞ նշված օբյեկտը ",
							JOptionPane.YES_NO_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE, null, optionNames, null);
					selectedDataId = (int) tableUser.getValueAt(selecttedRows[0], 6);

					if (optionResult == JOptionPane.YES_OPTION) {
						userList.deleteUser(selectedDataId);
						fillUsersTableData(userList.getAllUsers());
					}
					tableUser.getSelectionModel().clearSelection();

				} else {
					int optionResult = JOptionPane.showOptionDialog(null, new JPanel(),
							"Հեռացնել՞ նշված  " + selecttedRows.length + " օբյեկտները",
							JOptionPane.YES_NO_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE, null, optionNames, null);
					if (optionResult == JOptionPane.YES_OPTION) {
						for (int i = 0; i < selecttedRows.length; i++) {
							selectedDataId = (int) modelUsers.getValueAt(selecttedRows[i], 6);
							userList.deleteUser(selectedDataId);
						}
						fillUsersTableData(userList.getAllUsers());
						tableUser.getSelectionModel().clearSelection();

					}
				}
			}
			if (e.getSource() == btnUpdate) {
				if (tableUser.getSelectedRow() == -1) {
					JOptionPane.showMessageDialog(null, "Օբյեկտ նշված չէ");
				} else {
					if (tableUser.isEditing()) {
						tableUser.getCellEditor().stopCellEditing();
					}
					String usrName = (String) modelUsers.getValueAt(tableUser.getSelectedRow(), 0);
					String usSrnmae = (String) modelUsers.getValueAt(tableUser.getSelectedRow(), 1);
					String usrPatr = (String) modelUsers.getValueAt(tableUser.getSelectedRow(), 2);
					String usrVilage = (String) modelUsers.getValueAt(tableUser.getSelectedRow(), 3);
					String usrAddress = (String) modelUsers.getValueAt(tableUser.getSelectedRow(), 4);
					int usrCount = Integer.parseInt((String) modelUsers.getValueAt(tableUser.getSelectedRow(), 5));
					int usrId = (int) modelUsers.getValueAt(tableUser.getSelectedRow(), 6);

					User updateUser = new User();
					updateUser.setUserid(usrId);
					updateUser.setName(usrName);
					updateUser.setSrname(usSrnmae);
					updateUser.setPatronymic(usrPatr);
					updateUser.setVillage(usrVilage);
					updateUser.setAddress(usrAddress);
					updateUser.setPepolecount(usrCount);

					userList.updateUser(updateUser);
					// update tableUser
					fillUsersTableData(userList.getAllUsers());
					tableUser.getSelectionModel().clearSelection();
				}
			}
			if(e.getSource()==btnPay) {
				if (tableUser.getSelectedRow() == -1 || tableUser.getSelectedRowCount() > 1) {
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
					User payUser = userList.findUserById((Integer) tableUser.getValueAt(tableUser.getSelectedRow(), 6));
					nTextField.setText(payUser.getName());
					nTextField.setEnabled(false);

					JTextField monyTextField = new JTextField();
					monyTextField.setBounds(110, 80, 100, 20);
					monyTextField.setText(String.valueOf(payUser.getPepolecount()*sakagin));
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
						order.setUser(payUser);
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
					//refesh data on order table
					claenOrderTableData();
					fillorderData(userList.getAllUsers());
				}
			}if(e.getSource()==btnSearch) {

				String name = textField_1.getText();
				String srname = textField_2.getText();
				String patronymic = textField_3.getText();
				String village = textField_4.getText();
				String address = textField_5.getText();
				int pepolecount;
				try {
					pepolecount = Integer.parseInt(textField_6.getText());
				} catch (NumberFormatException exc) {
					pepolecount = -1;
				}

				List<User> ollUsers = userList.getAllUsers();
				List<User> findUsers = new ArrayList<>();
				boolean find = false;
				for (User user : ollUsers) {
					if (!name.isEmpty()) {
						if (user.getName().equals(name)) {
							find = true;
						} else {
							find = false;
						}
					}
					if (!srname.isEmpty()) {
						if (user.getSrname().equals(srname)) {
							find = true;
						} else {
							find = false;
						}
					}
					if (!patronymic.isEmpty()) {
						if (user.getPatronymic().equals(patronymic)) {
							find = true;
						} else {
							find = false;
						}
					}if (!village.isEmpty()) {
						if (user.getVillage().equals(village)) {
							find = true;
						} else {
							find = false;
						}
					}
					if (!address.isEmpty()) {
						if (user.getAddress().equals(address)) {
							find = true;
						} else {
							find = false;
						}
					}
					if (pepolecount !=-1) {
						if (pepolecount == user.getPepolecount()) {
							find = true;
						} else {
							find = false;
						}
					}
					if(find)
						findUsers.add(user);

						fillUsersTableData(findUsers);
						fillorderData(findUsers);

				}

			}
			if(e.getSource() == btnRefesh){
				fillUsersTableData(userList.getAllUsers());
				fillorderData(userList.getAllUsers());

			}
			if(e.getSource() == btnActiv){
				if (tableUser.getSelectedRow() == -1|| tableUser.getSelectedRowCount() > 1) {
					JOptionPane.showMessageDialog(null, "Օբյեկտ նշված չէ");
				} else {
					if (tableUser.isEditing()) {
						tableUser.getCellEditor().stopCellEditing();
					}
					String usrName = (String) modelUsers.getValueAt(tableUser.getSelectedRow(), 0);
					String usSrnmae = (String) modelUsers.getValueAt(tableUser.getSelectedRow(), 1);
					String usrPatr = (String) modelUsers.getValueAt(tableUser.getSelectedRow(), 2);
					String usrVilage = (String) modelUsers.getValueAt(tableUser.getSelectedRow(), 3);
					String usrAddress = (String) modelUsers.getValueAt(tableUser.getSelectedRow(), 4);
					int usrCount =  (int)modelUsers.getValueAt(tableUser.getSelectedRow(), 5);
					int usrId = (int) modelUsers.getValueAt(tableUser.getSelectedRow(), 6);

					User activUser = new User();
					activUser.setUserid(usrId);
					activUser.setName(usrName);
					activUser.setSrname(usSrnmae);
					activUser.setPatronymic(usrPatr);
					activUser.setVillage(usrVilage);
					activUser.setAddress(usrAddress);
					activUser.setPepolecount(usrCount);

					JDialog activDialog = new JDialog();
					activDialog.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
					activDialog.setSize(new Dimension(300, 250));
					activDialog.setLayout(null);
					activDialog.setTitle("Ակտիվություն");

					JLabel startLabel = new JLabel("Սկիզբ");
					startLabel.setBounds(10,35,100,20);
					JLabel endLabel = new JLabel("Ավարտ");
					endLabel.setBounds(210,35,100,20);

					JLabel yearLabel = new JLabel("Տարի");
					yearLabel.setBounds(138,55,100,20);
					JLabel mountLabel = new JLabel("Ամիս");
					mountLabel.setBounds(138,95,100,20);
					JComboBox startYearComboBox = new JComboBox(new Integer[]{2016,2017,2018,2019});
					JComboBox startMountComboBox = new JComboBox(new Integer[]{1,2,3,4,5,6,7,8,9,10,11,12});
					JComboBox endYearComboBox = new JComboBox(new Integer[]{2016,2017,2018,2019});
					JComboBox endmountComboBox = new JComboBox(new Integer[]{1,2,3,4,5,6,7,8,9,10,11,12});

					startYearComboBox.setBounds(10,70,100,20);
					endYearComboBox.setBounds(190,70,100,20);
					startMountComboBox.setBounds(10,110,100,20);
					endmountComboBox.setBounds(190,110,100,20);
					JButton btnDisable = new JButton("Ապաակտիվացնել");
					btnDisable.addActionListener(actionEvent -> {
						int syear= (int)startYearComboBox.getSelectedItem() - 1900;
						int smount =(int) startMountComboBox.getSelectedItem();
						int eyear= (int)endYearComboBox.getSelectedItem() - 1900;
						int emount =(int) endmountComboBox.getSelectedItem();

                        activUser.setEnable_start(new Date(syear,smount,1));
                        activUser.setEnable_end(new Date(eyear,emount,1));
                        userList.updateUser(activUser);
                    });
					JButton btnEnable = new JButton("Ակտիվացնել");
					btnEnable.addActionListener(actionEvent -> {
						int eyear= (int)endYearComboBox.getSelectedItem();
						int emount =(int) endmountComboBox.getSelectedItem();
						activUser.setEnable_end(new Date(eyear,emount,1));
						userList.updateUser(activUser);
					});
					btnDisable.setBounds(10,140,130,30);
					btnEnable.setBounds(160,140,130,30);

						activDialog.add(startLabel);
						activDialog.add(endLabel);
						activDialog.add(yearLabel);
						activDialog.add(mountLabel);
						activDialog.add(startYearComboBox);
						activDialog.add(endYearComboBox);
						activDialog.add(startMountComboBox);
						activDialog.add(endmountComboBox);
						activDialog.add(btnDisable);
						activDialog.add(btnEnable);
						activDialog.setVisible(true);
				}
			}
	}

}



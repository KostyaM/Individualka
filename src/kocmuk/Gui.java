package kocmuk;

import java.awt.EventQueue;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.JTableHeader;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.Connection;

public class Gui extends JFrame {

	private JPanel contentPane;
	private JTable cityTable, addressTable, countryTable, regionTable;
	private static JDBCSingltone jdbcSingltone;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(() -> {
            try {
                Gui frame = new Gui();
                frame.setVisible(true);
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
		 jdbcSingltone=new JDBCSingltone();
		Connection connection=jdbcSingltone.getConnection();
	}

	
	public Gui() {
		//-------------------------------------------------------------
		Object[] CityHeaders = new Object[]{"ID", "RegionID", "NameCity"};
		//-------------------------------------------------------------
		Object cityM[][] = new Object[][]{
				{"1", "1", "���������"},

		};
		//-------------------------------------------------------------
		Object[] AddressHeaders = new Object[]{"ID", "CityID", "Person", "Street", "Building", "Office"};
		//-------------------------------------------------------------
		Object adrM[][] = new Object[][]{
				{"1", "1", "�����", "�������", "�����������", "1488"},

		};
		//-------------------------------------------------------------
		Object[] CountryHeaders = new Object[]{"ID", "FullName", "ShortName"};
		//-------------------------------------------------------------
		Object countryM[][] = new Object[][]{
				{"1", "Russia", "Rus"},

		};
		//-------------------------------------------------------------
		Object[] RegionHeaders = new Object[]{"ID", "CountryID", "NameRegion"};
		//-------------------------------------------------------------
		Object regionM[][] = new Object[][]{
				{"1", "1", "���������� ���������� ����������"},

		};

		cityTable = new JTable(cityM, CityHeaders);
		cityTable.setEnabled(false);




		cityTable.addMouseListener(new MouseAdapter()
		{
			public void mouseClicked(MouseEvent me)
			{
				if(SwingUtilities.isLeftMouseButton(me) == true)
				{
					int row = cityTable.rowAtPoint(me.getPoint());

					cityTable.clearSelection();
					cityTable.addRowSelectionInterval(row,row);

				}
			}
		});


		addressTable = new JTable(adrM, AddressHeaders);
		addressTable.setEnabled(false);

		addressTable.addMouseListener(new MouseAdapter()
		{
			public void mouseClicked(MouseEvent me)
			{
				if(SwingUtilities.isLeftMouseButton(me) == true)
				{
					int row = addressTable.rowAtPoint(me.getPoint());

					addressTable.clearSelection();
					addressTable.addRowSelectionInterval(row,row);

				}
			}
		});


		countryTable = new JTable(countryM, CountryHeaders);
		countryTable.setEnabled(false);

		countryTable.addMouseListener(new MouseAdapter()
		{
			public void mouseClicked(MouseEvent me)
			{
				if(SwingUtilities.isLeftMouseButton(me) == true)
				{
					int row = countryTable.rowAtPoint(me.getPoint());

					countryTable.clearSelection();
					countryTable.addRowSelectionInterval(row,row);

				}
			}
		});



		regionTable = new JTable(regionM, RegionHeaders);
		regionTable.setEnabled(false);
		regionTable.addMouseListener(new MouseAdapter()
		{
			public void mouseClicked(MouseEvent me)
			{
				if(SwingUtilities.isLeftMouseButton(me) == true)
				{
					int row = regionTable.rowAtPoint(me.getPoint());

					regionTable.clearSelection();
					regionTable.addRowSelectionInterval(row,row);

				}
			}
		});

		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 586, 416);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		tabbedPane.setBounds(10, 11, 550, 319);
		contentPane.add(tabbedPane);

		JScrollPane scrollPane = new JScrollPane();
		JLinearLayout mainLayout = new JLinearLayout().setChildOrientation(JLinearLayout.Orientation.VERTICAL);
		JTableHeader header = cityTable.getTableHeader();
		mainLayout.addView(header,1);
		mainLayout.addView(scrollPane,40);
		tabbedPane.addTab("City", null, mainLayout.getAsPanel(), null);





		scrollPane.setColumnHeaderView(cityTable);


		JScrollPane scrollPane_1 = new JScrollPane();
		mainLayout = new JLinearLayout().setChildOrientation(JLinearLayout.Orientation.VERTICAL);
		header = addressTable.getTableHeader();
		mainLayout.addView(header,1);
		mainLayout.addView(scrollPane_1,40);
		tabbedPane.addTab("Address", null, mainLayout.getAsPanel(), null);
		scrollPane_1.setColumnHeaderView(addressTable);

		JScrollPane scrollPane_2 = new JScrollPane();
		mainLayout = new JLinearLayout().setChildOrientation(JLinearLayout.Orientation.VERTICAL);
		header = countryTable.getTableHeader();
		mainLayout.addView(header,1);
		mainLayout.addView(scrollPane_2,40);
		tabbedPane.addTab("Country", null, mainLayout.getAsPanel(), null);
		scrollPane_2.setColumnHeaderView(countryTable);

		JScrollPane scrollPane_3 = new JScrollPane();
		mainLayout = new JLinearLayout().setChildOrientation(JLinearLayout.Orientation.VERTICAL);
		header = regionTable.getTableHeader();
		mainLayout.addView(header,1);
		mainLayout.addView(scrollPane_3,40);
		tabbedPane.addTab("Region", null, mainLayout.getAsPanel(), null);
		scrollPane_3.setColumnHeaderView(regionTable);




		JButton editButton = new JButton("\u0420\u0435\u0434\u0430\u043A\u0442\u0438\u0440\u043E\u0432\u0430\u0442\u044C");
		editButton.setBounds(10, 341, 125, 23);
		contentPane.add(editButton);





		JButton applyButton = new JButton("\u041F\u0440\u0438\u043C\u0435\u043D\u0438\u0442\u044C");
		applyButton.setEnabled(false);
		applyButton.setBounds(145, 341, 109, 23);
		contentPane.add(applyButton);



		JButton deleteButton = new JButton("\u0423\u0434\u0430\u043B\u0438\u0442\u044C");
		deleteButton.setEnabled(false);
		deleteButton.setBounds(260, 341, 89, 23);
		contentPane.add(deleteButton);




		JButton canselButton = new JButton("\u0414\u043E\u0431\u0430\u0432\u0438\u0442\u044C");
		canselButton.setBounds(456, 341, 104, 23);
		contentPane.add(canselButton);





		JButton addButton = new JButton("\u041E\u0442\u043C\u0435\u043D\u0430");
		addButton.setBounds(357, 341, 89, 23);
		contentPane.add(addButton);
	}

}

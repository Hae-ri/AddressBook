import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import java.awt.Toolkit;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Vector;

public class WinAllShow_2 extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private Vector data;
	private JTable table;
	private JScrollPane scrollPane;

	/**
	 * Create the dialog.
	 * @throws SQLException 
	 * @throws ClassNotFoundException 
	 */
	public WinAllShow_2() throws ClassNotFoundException, SQLException {
		setIconImage(Toolkit.getDefaultToolkit().getImage("C:\\workspace\\JAVA\\AddressBook\\image\\viewall.png"));
		setTitle("��âȸ�� ��� ����");
		setBounds(100, 100, 628, 428);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(new BorderLayout(0, 0));
		

		scrollPane = new JScrollPane();
		contentPanel.add(scrollPane);
		
		
		Vector<String> columnNames = new Vector<>();
		columnNames.add("�й�");
		columnNames.add("�̸�");
		columnNames.add("��ȭ��ȣ");
		columnNames.add("�ּ�");
		columnNames.add("���г⵵");
						
		data = new Vector<>(); // ��ȸ ����� ������ ����
				
		String sql = "select * from addressbook;";
		SearchAll(sql);
		
		DefaultTableModel dtm = new DefaultTableModel(data, columnNames);
		table = new JTable(dtm);
		scrollPane.setViewportView(table);
		
	
	}

	public void SearchAll(String sql) throws ClassNotFoundException, SQLException {
		Class.forName("com.mysql.cj.jdbc.Driver");
		String strCon = "jdbc:mysql://localhost/sampledb?user=root&password=1234";
		Connection con = DriverManager.getConnection(strCon);
		Statement stmt = con.createStatement();
		ResultSet rs = stmt.executeQuery(sql);

		while (rs.next()) {
			Vector<String> row = new Vector<>();

			row.add(rs.getString("id"));
			row.add(rs.getString("name"));
			row.add(rs.getString("phone"));
			row.add(rs.getString("address"));
			row.add(rs.getString("year"));
			
			data.add(row);
		}
		
		rs.close();
		stmt.close();
		con.close();
	}
	
}
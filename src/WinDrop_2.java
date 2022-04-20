import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Vector;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.JLabel;
import javax.swing.JTextField;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JScrollPane;
import javax.swing.JTable;

public class WinDrop_2 extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private JTextField txtID;
	private JScrollPane scrollPane = new JScrollPane();
	private JTable table;
	Vector<String> columnNames = new Vector<>();
	private JButton btnDrop = new JButton("Ż��");

	/**
	 * Create the dialog.
	 */
	public WinDrop_2() {
		setTitle("ȸ�� Ż��");
		setBounds(100, 100, 450, 385);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(new BorderLayout(0, 0));
		
		JPanel panel = new JPanel();
		contentPanel.add(panel, BorderLayout.NORTH);
		
		JLabel lblNewLabel = new JLabel("\uD559\uBC88 :");
		panel.add(lblNewLabel);
		
		txtID = new JTextField();
		panel.add(txtID);
		txtID.setColumns(10);
		
		JButton btnSearch = new JButton("��ȸ");
		btnSearch.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				String id = txtID.getText();
				
				String sql = "select * from addressbook where id = '" + id +"';";
				
				//System.out.println(sql);
				
				try {
					DeleteIDSearchQuery(sql);
				} catch (ClassNotFoundException | SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				
				btnDrop.setEnabled(true);
			}
		});
		panel.add(btnSearch);
		btnDrop.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int result = JOptionPane.showConfirmDialog(null, "������ Ż���Ͻðڽ��ϱ�?", "Confirm",JOptionPane.YES_NO_OPTION);
				
				if(result == JOptionPane.CLOSED_OPTION) { // Ż��-�׳� ����
					
				}
				else if(result == JOptionPane.YES_OPTION) {// Ż��-��
				
					String id = txtID.getText();
					String sql = "delete from addressbook where id = '" + id + "';";
					
					//System.out.println(sql);
					
					try {
						DeleteIDQuery(sql);
					} catch (ClassNotFoundException | SQLException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				}
				else { // Ż��-�ƴϿ�	
				}
			}
		});
		
		
		btnDrop.setEnabled(false);
		panel.add(btnDrop);
		
		
		contentPanel.add(scrollPane, BorderLayout.CENTER);
		
		//���̺� ����
		
		columnNames.add("�й�");
		columnNames.add("�̸�");
		columnNames.add("��ȭ��ȣ");
		columnNames.add("�ּ�");
		
		
	}
	
	
	public void DeleteIDSearchQuery(String sql) throws ClassNotFoundException, SQLException {
		Class.forName("com.mysql.cj.jdbc.Driver");
		String strCon = "jdbc:mysql://localhost/sampledb?user=root&password=1234";
		Connection con = DriverManager.getConnection(strCon);
		Statement stmt = con.createStatement();
		ResultSet rs = stmt.executeQuery(sql);
		
		Vector records = new Vector<>();
		
		while(rs.next()) {
			Vector cols = new Vector<>();				
				cols.add(rs.getString("id"));
				cols.add(rs.getString("name"));
				cols.add(rs.getString("phone"));
				cols.add(rs.getString("address"));

			records.add(cols);
		}

		
		rs.close();
		stmt.close();
		con.close();
		
		DefaultTableModel dtm = new DefaultTableModel(records,columnNames);
		table = new JTable(dtm);		
		
		
		scrollPane.setViewportView(table);
	}

	public void DeleteIDQuery(String sql) throws ClassNotFoundException, SQLException {
		Class.forName("com.mysql.cj.jdbc.Driver");
		String strCon = "jdbc:mysql://localhost/sampledb?user=root&password=1234";
		Connection con = DriverManager.getConnection(strCon);
		Statement stmt = con.createStatement();
		if(stmt.executeUpdate(sql) > 0)
			JOptionPane.showMessageDialog(null, "Ż�� �Ϸ�");
		else
			JOptionPane.showMessageDialog(null, "�Է��Ͻ� ������ �ٽ� Ȯ�����ּ���.");
		
		stmt.close();
		con.close();
	}
}

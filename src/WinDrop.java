import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JTextField;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class WinDrop extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private JTextField txtID;

	/**
	 * Create the dialog.
	 */
	public WinDrop() {
		setTitle("È¸¿ø Å»Åð");
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
		
		JButton btnNewButton = new JButton("\uC870\uD68C");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				String id = txtID.getText();
				
				String sql = "select * from addressbook where id = '" + id +"';";
				
				System.out.println(sql);
				
				try {
					InsertQuery(sql);
				} catch (ClassNotFoundException | SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				
				
			}
		});
		panel.add(btnNewButton);
		
		JButton btnNewButton_1 = new JButton("\uD0C8\uD1F4");
		btnNewButton_1.setEnabled(false);
		panel.add(btnNewButton_1);
	}
	
	
	public void InsertQuery(String sql) throws ClassNotFoundException, SQLException {
		Class.forName("com.mysql.cj.jdbc.Driver");
		String strCon = "jdbc:mysql://localhost/sampledb?user=root&password=1234";
		Connection con = DriverManager.getConnection(strCon);
		Statement stmt = con.createStatement();
		ResultSet rs = stmt.executeQuery(sql);
		
		
		
		
		
		stmt.close();
		con.close();
	}

}

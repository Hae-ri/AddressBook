import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import java.awt.Toolkit;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Vector;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JScrollPane;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JPopupMenu;
import java.awt.Component;

public class WinSearch extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private JTextField txtName;
	private JTable table;
	private JTextField txtID;
	private JScrollPane scrollPane;

	/**
	 * Create the dialog.
	 */
	
	public WinSearch() throws ClassNotFoundException, SQLException {
		initGUI();
	}

	public void initGUI() throws ClassNotFoundException, SQLException {
		setIconImage(Toolkit.getDefaultToolkit().getImage("C:\\workspace\\JAVA\\AddressBook\\image\\search.png"));
		setTitle("회원 찾기");
		setBounds(100, 100, 675, 444);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(new BorderLayout(0, 0));
		
		JPanel panel = new JPanel();
		contentPanel.add(panel, BorderLayout.NORTH);
		
		JLabel lblNewLabel_1 = new JLabel("학번 :");
		panel.add(lblNewLabel_1);
		
		txtID = new JTextField();
		txtID.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				if(e.getKeyCode() == KeyEvent.VK_ENTER) {
					String sID = txtID.getText().trim();
					String sql = "select * from addressbook where id = '" + sID + "';";
					System.out.println(sql);
					try {
						SearchName(sql);
					} catch (ClassNotFoundException | SQLException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				}
			}
		});
		panel.add(txtID);
		txtID.setColumns(10);
		
		JLabel lblNewLabel = new JLabel("이름 : ");
		panel.add(lblNewLabel);
		
		txtName = new JTextField();
		txtName.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				if(e.getKeyCode() == KeyEvent.VK_ENTER) {
					String sName = txtName.getText().trim();
					String sql = "select * from addressbook where name = '" + sName + "';";
					try {
						SearchName(sql);
					} catch (ClassNotFoundException | SQLException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				}
			}
		});
		panel.add(txtName);
		txtName.setColumns(10);
		
		JButton btnSearch = new JButton("찾기");
		btnSearch.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String sID = txtID.getText().trim();
				String sName = txtName.getText().trim();
				String sql = "select * from addressbook where id = '" + sID + "' or name = '" + txtName.getText() + "';";
				try {
					SearchName(sql);
				} catch (ClassNotFoundException | SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		panel.add(btnSearch);
		
		scrollPane = new JScrollPane();
		contentPanel.add(scrollPane, BorderLayout.CENTER);   
		
	}

	public void SearchName(String sql) throws ClassNotFoundException, SQLException {
		Class.forName("com.mysql.cj.jdbc.Driver");
		String strCon = "jdbc:mysql://localhost/sampledb?user=root&password=1234";
		Connection con = DriverManager.getConnection(strCon);
		Statement stmt = con.createStatement();
		ResultSet rs = stmt.executeQuery(sql);
		
		// =================================================================
		Vector<String> columnNames = new Vector<>();
		columnNames.add("학번");
		columnNames.add("이름");
		columnNames.add("전화번호");
		columnNames.add("주소");
		columnNames.add("입학년도");
				
		Vector data = new Vector<>(); // 조회 결과를 저장할 벡터
		while (rs.next()) {
			Vector<String> row = new Vector<>();

			row.add(rs.getString("id"));
			row.add(rs.getString("name"));
			row.add(rs.getString("phone"));
			row.add(rs.getString("address"));
			row.add(rs.getString("year"));
			
			data.add(row);
		}
		
		DefaultTableModel dtm = new DefaultTableModel(data, columnNames);
		table = new JTable(dtm);
		
		scrollPane.setViewportView(table);
	
		table.addMouseListener(new MouseAdapter() {
	         @Override
	         public void mouseReleased(MouseEvent e) {
	            if (e.getButton() == 3)  { //mouse 오른쪽 버튼을 클릭한 후, 뗄 때                  
	            	String id = table.getValueAt(table.getSelectedRow(), 0).toString();
	            	String name = table.getValueAt(table.getSelectedRow(), 1).toString();
	            	WinShowMember dlg = new WinShowMember(id, name);
	            	dlg.setModal(true);
	            	dlg.setVisible(true);
	            	//setVisible(false);
               }
	         }
	      });
		// =================================================================
		rs.close();
		stmt.close();
		con.close();
	}
	private static void addPopup(Component component, final JPopupMenu popup) {
		component.addMouseListener(new MouseAdapter() {
			public void mousePressed(MouseEvent e) {
				if (e.isPopupTrigger()) {
					showMenu(e);
				}
			}
			public void mouseReleased(MouseEvent e) {
				if (e.isPopupTrigger()) {
					showMenu(e);
				}
			}
			private void showMenu(MouseEvent e) {
				popup.show(e.getComponent(), e.getX(), e.getY());
			}
		});
	}
}

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
import javax.swing.JList;
import javax.swing.JScrollPane;
import javax.swing.ListSelectionModel;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.Toolkit;

public class WinDoroSearch extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private JList list = new JList();
	private String strAddress;

	public String getAddress() {		
		return strAddress;
	}	
	
	public WinDoroSearch(String doro) {
		setIconImage(Toolkit.getDefaultToolkit().getImage("C:\\workspace\\JAVA\\AddressBook\\image\\address.png"));
		setTitle("도로명(" + doro + ") -> 주소찾기");
		setBounds(100, 100, 450, 300);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(new BorderLayout(0, 0));
		
		JScrollPane scrollPane = new JScrollPane();
		contentPanel.add(scrollPane);
		list.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if(e.getClickCount() == 2) { // 리스트를 더블클릭하면 선택된 값을 반환
					strAddress = list.getSelectedValue().toString();
					//JOptionPane.showMessageDialog(null, strAddress);		
					setVisible(false);
				}
			}
		});

		
		
		list.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		
		scrollPane.setViewportView(list);
		

		String sql = "select * from addressTBL where doro = '" + doro + "';"; 
		try {
			SearchAddress(sql);	
		} catch (ClassNotFoundException | SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
	}

	public void SearchAddress(String sql) throws ClassNotFoundException, SQLException {
		Class.forName("com.mysql.cj.jdbc.Driver");
		String strCon = "jdbc:mysql://localhost/sampledb?user=root&password=1234";
		Connection con = DriverManager.getConnection(strCon);
		Statement stmt = con.createStatement();
		ResultSet rs = stmt.executeQuery(sql);
		
		Vector<String> v = new Vector<String>();
		String pAddress="";
		while (rs.next()) { // rs에 저장된 다음 행으로 커서를 옮긴다.
			String si = rs.getString("si");
			String gu = rs.getString("gu");
			String dong = rs.getString("dong");
			String doro = rs.getString("doro");
			if (!dong.equals("")) {
				pAddress = si + " " + gu + " " + doro + "(" + dong +")";
			}else if (dong.equals("")) {
				pAddress = si + " " + gu + " " + doro;
			}
			//System.out.println(pAddress);
			v.add(pAddress);
			}
		list.setListData(v);
		
		rs.close();
		stmt.close();
		con.close();
	}

}

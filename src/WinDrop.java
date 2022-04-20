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
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableColumnModel;
import javax.swing.table.DefaultTableModel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JPopupMenu;
import java.awt.Component;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Toolkit;

public class WinDrop extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private JTable table;
	private String sName = "";  // 전달된 이름 저장
	private Vector data = null; // 여러명의 정보 저장할 벡터

	public WinDrop(String name) throws ClassNotFoundException, SQLException {
		setIconImage(Toolkit.getDefaultToolkit().getImage("C:\\workspace\\JAVA\\AddressBook\\image\\delete.png"));
		sName = name;
		initGUI();
	}

	public void initGUI() throws ClassNotFoundException, SQLException {
		setTitle("회원(" + sName + ") 탈퇴");
		setBounds(100, 100, 450, 300);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);	
		contentPanel.setLayout(new BorderLayout(0, 0));
		
		JScrollPane scrollPane = new JScrollPane();
		contentPanel.add(scrollPane);
		
		Vector<String> columnNames = new Vector<>();
		columnNames.add("학번");
		columnNames.add("이름");
		columnNames.add("전화번호");
		
		String sql = "select * from addressbook where name = '" + sName + "';";
		SearchName(sql);
		
		DefaultTableModel dtm = new DefaultTableModel(data, columnNames);
				
		table = new JTable(dtm);
		
		JPopupMenu popup = new JPopupMenu();
	    JMenuItem mnuDelete = new JMenuItem("삭제");
	    popup.add(mnuDelete);      
	    
	    mnuDelete.addActionListener(new ActionListener() {
	    	public void actionPerformed(ActionEvent e) {
	    		String id = table.getValueAt(table.getSelectedRow(), 0).toString();
					//System.out.println(id);	
				int result = JOptionPane.showConfirmDialog(null, "정말로 탈퇴하시겠습니까?", "확인",JOptionPane.YES_NO_OPTION);
				if(result == JOptionPane.CLOSED_OPTION) {} // 탈퇴-그냥 닫음
				else if(result == JOptionPane.YES_OPTION) {// 탈퇴-예
					String sql = "delete from addressbook where id = '" + id + "';";
					//System.out.println(sql);
					try {
						DeleteIDQuery(sql);
					} catch (ClassNotFoundException | SQLException e1) {
					// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				}
				else {} // 탈퇴-아니오	
	    	}
	    });    
	    
	    table.addMouseListener(new MouseAdapter() {
	         @Override
	         public void mouseReleased(MouseEvent e) {
	            if (e.getButton() == 3)  { //mouse 오른쪽 버튼을 클릭한 후, 뗄 때                  
                    int row = table.rowAtPoint( e.getPoint() );
                    int column = table.columnAtPoint( e.getPoint() );
                    if (! table.isRowSelected(row))
                       table.changeSelection(row, column, false, false);
                    popup.show(e.getComponent(), e.getX(), e.getY());
                    String str = (String) table.getValueAt(row, 0);
                    System.out.println(str);                    
                }
	         }
	      });
	      
	      scrollPane.setViewportView(table);
	   }

	
	
	public void SearchName(String sql) throws ClassNotFoundException, SQLException {
		Class.forName("com.mysql.cj.jdbc.Driver");
		String strCon = "jdbc:mysql://localhost/sampledb?user=root&password=1234";
		Connection con = DriverManager.getConnection(strCon);
		Statement stmt = con.createStatement();
		ResultSet rs = stmt.executeQuery(sql);
		
		data = new Vector<>();
		while (rs.next()) {
			Vector<String> row = new Vector<>();

			row.add(rs.getString("id"));
			row.add(rs.getString("name"));
			row.add(rs.getString("phone"));
			
			data.add(row);
		}
		rs.close();
		stmt.close();
		con.close();
	}
	
	
	public void DeleteIDQuery(String sql) throws ClassNotFoundException, SQLException {
		Class.forName("com.mysql.cj.jdbc.Driver");
		String strCon = "jdbc:mysql://localhost/sampledb?user=root&password=1234";
		Connection con = DriverManager.getConnection(strCon);
		Statement stmt = con.createStatement();
		if(stmt.executeUpdate(sql) > 0) {
			JOptionPane.showMessageDialog(null, "'" + sName + "' 님 탈퇴 완료");
			setVisible(false);
		}
		else
			JOptionPane.showMessageDialog(null, "입력하신 내용을 다시 확인해주세요.");
		
		stmt.close();
		con.close();
	}
}

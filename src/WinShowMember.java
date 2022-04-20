import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.Image;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFileChooser;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import java.awt.SystemColor;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import javax.swing.ImageIcon;

import java.awt.event.ActionListener;
import java.time.LocalDate;
import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.Toolkit;
import java.awt.Color;

public class WinShowMember extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private JTextField txtName;
	private JTextField txtID;
	private JTextField txtPhone1;
	private JTextField txtPhone2;
	private JTextField txtPhone3;
	private JTextField txtAddress;
	private String pathName="";
	private WinDoroSearch dialog;
	private String strID = "";
	private String strName = "";
	private JTextField cbYear;
	private JLabel lblPic;
	

	public void SearchName(String sql) {
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			String strCon = "jdbc:mysql://localhost/sampledb?user=root&password=1234";
			Connection con = DriverManager.getConnection(strCon);
			Statement stmt = con.createStatement();
			ResultSet rs = stmt.executeQuery(sql);
			
			while (rs.next()) {
				txtID.setText(rs.getString("id"));
				txtName.setText(rs.getString("name"));
				txtPhone1.setText(rs.getString("phone").substring(0, 3));
				txtPhone2.setText(rs.getString("phone").substring(3, 7));
				txtPhone3.setText(rs.getString("phone").substring(7, 11));
				txtAddress.setText(rs.getString("address"));
				cbYear.setText(rs.getString("year"));
				
				String picPath = rs.getString("pic");
				if(!picPath.equals("")) {
					ImageIcon rs_icon = new ImageIcon(picPath);
					Image rs_pic = rs_icon.getImage();
					rs_pic = rs_pic.getScaledInstance(115, 130, Image.SCALE_SMOOTH);
					ImageIcon rs_imgCon = new ImageIcon(rs_pic);
					lblPic.setIcon(rs_imgCon);
				}
			}
			rs.close();
			stmt.close();
			con.close();
		}catch (ClassNotFoundException | SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
	}
	
	public WinShowMember(String id, String name) {
		strID=id;
		strName=name;
		initGUI();
		
		String sql = "select * from addressbook where id = '" + strID + "';";
		SearchName(sql);

	}
		
	private void initGUI() {
		setIconImage(Toolkit.getDefaultToolkit().getImage("C:\\workspace\\JAVA\\AddressBook\\image\\add.png"));
		setTitle(strName + "(" + strID + ") 회원정보");
		setBounds(100, 100, 450, 242);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		
		JLabel lblName = new JLabel("\uC774\uB984 :");
		lblName.setBounds(155, 13, 57, 15);
		contentPanel.add(lblName);
		
		txtName = new JTextField();
		txtName.setEditable(false);
		txtName.setBounds(232, 10, 116, 21);
		contentPanel.add(txtName);
		txtName.setColumns(10);
		
		lblPic = new JLabel("");
		
		ImageIcon icon = new ImageIcon("C:\\workspace\\JAVA\\AddressBook\\image\\picture.png");
		Image pic = icon.getImage();
		pic = pic.getScaledInstance(115, 130, Image.SCALE_SMOOTH);
		ImageIcon imgCon = new ImageIcon(pic);
		lblPic.setIcon(imgCon);
		
		
		
		lblPic.setToolTipText("\uB354\uBE14\uD074\uB9AD\uD558\uC5EC \uC0AC\uC9C4 \uCD94\uAC00");
		
		
		lblPic.setHorizontalAlignment(SwingConstants.CENTER);
		lblPic.setBounds(12, 13, 115, 130);
		contentPanel.add(lblPic);
		
		JLabel lblID = new JLabel("\uD559\uBC88 :");
		lblID.setBounds(155, 47, 57, 15);
		contentPanel.add(lblID);
		
		txtID = new JTextField();
		txtID.setEditable(false);
		txtID.setColumns(10);
		txtID.setBounds(232, 44, 116, 21);
		contentPanel.add(txtID);
		
		JLabel lblPhone = new JLabel("\uC804\uD654\uBC88\uD638 :");
		lblPhone.setBounds(155, 81, 65, 15);
		contentPanel.add(lblPhone);
		
		txtPhone1 = new JTextField();
		txtPhone1.setEditable(false);
		txtPhone1.setColumns(10);
		txtPhone1.setBounds(232, 78, 49, 21);
		contentPanel.add(txtPhone1);
		
		txtPhone2 = new JTextField();
		txtPhone2.setEditable(false);
		txtPhone2.setColumns(10);
		txtPhone2.setBounds(287, 78, 57, 21);
		contentPanel.add(txtPhone2);
		
		txtPhone3 = new JTextField();
		txtPhone3.setEditable(false);
		txtPhone3.setColumns(10);
		txtPhone3.setBounds(350, 78, 57, 21);
		contentPanel.add(txtPhone3);
		
		JLabel lblNewLabel_2 = new JLabel("-");
		lblNewLabel_2.setBounds(280, 83, 17, 15);
		contentPanel.add(lblNewLabel_2);
		
		JLabel lblNewLabel_2_1 = new JLabel("-");
		lblNewLabel_2_1.setBounds(343, 83, 17, 15);
		contentPanel.add(lblNewLabel_2_1);
		
		JLabel lblAddress = new JLabel("\uC8FC\uC18C : ");
		lblAddress.setBounds(12, 156, 57, 15);
		contentPanel.add(lblAddress);
		
		txtAddress = new JTextField();
		txtAddress.setEditable(false);

		txtAddress.setBounds(67, 153, 340, 21);
		contentPanel.add(txtAddress);
		txtAddress.setColumns(10);
		
		JLabel lblNewLabel_1_1_1_1 = new JLabel("\uC785\uD559\uB144\uB3C4 : ");
		lblNewLabel_1_1_1_1.setBounds(155, 115, 76, 15);
		contentPanel.add(lblNewLabel_1_1_1_1);
		
		cbYear = new JTextField();
		cbYear.setEditable(false);
		cbYear.setBounds(232, 109, 76, 21);
		contentPanel.add(cbYear);
		cbYear.setColumns(10);
		
	}
}

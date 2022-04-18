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
import java.sql.SQLException;
import java.sql.Statement;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class WinAdd extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private JTextField txtName;
	private JTextField txtID;
	private JTextField txtPhone1;
	private JTextField txtPhone2;
	private JTextField txtPhone3;
	private JTextField txtAddress;
	private JButton btnInsert = new JButton("추가");
	private String pathName="";

	/**
	 * Create the dialog.
	 * @throws ClassNotFoundException 
	 * @throws SQLException 
	 */
	
	public void InsertQuery(String sql) throws ClassNotFoundException, SQLException {
		Class.forName("com.mysql.cj.jdbc.Driver");
		String strCon = "jdbc:mysql://localhost/sampledb?user=root&password=1234";
		Connection con = DriverManager.getConnection(strCon);
		Statement stmt = con.createStatement();
		if(stmt.executeUpdate(sql) > 0)
			JOptionPane.showMessageDialog(null, "추가 완료");
		else
			JOptionPane.showMessageDialog(null, "입력하신 내용을 다시 확인해주세요.");
		
		stmt.close();
		con.close();
	}

	public WinAdd() {
		setTitle("회원 등록");
		setBounds(100, 100, 450, 285);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		
		JLabel lblNewLabel_1 = new JLabel("\uC774\uB984 :");
		lblNewLabel_1.setBounds(155, 13, 57, 15);
		contentPanel.add(lblNewLabel_1);
		
		txtName = new JTextField();
		txtName.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				if(e.getKeyCode() == KeyEvent.VK_ENTER)
					txtID.requestFocus();
			}
		});
		txtName.setBounds(232, 10, 116, 21);
		contentPanel.add(txtName);
		txtName.setColumns(10);
		
		JLabel lblPic = new JLabel("picture");
		lblPic.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if(e.getClickCount() == 2) { // 더블클릭
					JFileChooser chooser = new JFileChooser("C:/JavaTest/");
					FileNameExtensionFilter filter = new FileNameExtensionFilter("그림파일", "jpg","png","gif");
					chooser.setFileFilter(filter);
					chooser.setFileSelectionMode(JFileChooser.FILES_AND_DIRECTORIES);
					int ret = chooser.showOpenDialog(null);
					if (ret != JFileChooser.APPROVE_OPTION) {
						JOptionPane.showMessageDialog(null, "파일을 선택하지 않았습니다.");
						return;
					}
					// 사용자가 파일을 선택하고 "열기" 버튼을 누른 경우
					pathName = chooser.getSelectedFile().getPath();
					ImageIcon image = new ImageIcon(pathName);
					Image pic = image.getImage();
					pic = pic.getScaledInstance(115, 130, Image.SCALE_SMOOTH);
					ImageIcon imgCon = new ImageIcon(pic);
					lblPic.setText("");
					lblPic.setIcon(imgCon);	
					
					pathName = pathName.replaceAll("\\\\", "\\\\\\\\");
			}
			}
		});
		lblPic.setBackground(SystemColor.activeCaption);
		lblPic.setOpaque(true);
		lblPic.setHorizontalAlignment(SwingConstants.CENTER);
		lblPic.setBounds(12, 13, 115, 130);
		contentPanel.add(lblPic);
		
		JLabel lblNewLabel_1_1 = new JLabel("\uD559\uBC88 :");
		lblNewLabel_1_1.setBounds(155, 47, 57, 15);
		contentPanel.add(lblNewLabel_1_1);
		
		txtID = new JTextField();
		txtID.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				if(e.getKeyCode() == KeyEvent.VK_ENTER)
					txtPhone2.requestFocus();
			}
		});
		txtID.setColumns(10);
		txtID.setBounds(232, 44, 116, 21);
		contentPanel.add(txtID);
		
		JLabel lblNewLabel_1_1_1 = new JLabel("\uC804\uD654\uBC88\uD638 :");
		lblNewLabel_1_1_1.setBounds(155, 81, 65, 15);
		contentPanel.add(lblNewLabel_1_1_1);
		
		txtPhone1 = new JTextField();
		txtPhone1.setText("010");
		txtPhone1.setColumns(10);
		txtPhone1.setBounds(232, 78, 49, 21);
		contentPanel.add(txtPhone1);
		
		txtPhone2 = new JTextField();
		txtPhone2.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				if(e.getKeyCode() == KeyEvent.VK_ENTER)
					txtPhone3.requestFocus();
			}
		});
		txtPhone2.setColumns(10);
		txtPhone2.setBounds(287, 78, 57, 21);
		contentPanel.add(txtPhone2);
		
		txtPhone3 = new JTextField();
		txtPhone3.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				if(e.getKeyCode() == KeyEvent.VK_ENTER)
					txtAddress.requestFocus();
			}
		});
		txtPhone3.setColumns(10);
		txtPhone3.setBounds(350, 78, 57, 21);
		contentPanel.add(txtPhone3);
		
		JLabel lblNewLabel_2 = new JLabel("-");
		lblNewLabel_2.setBounds(280, 83, 17, 15);
		contentPanel.add(lblNewLabel_2);
		
		JLabel lblNewLabel_2_1 = new JLabel("-");
		lblNewLabel_2_1.setBounds(343, 83, 17, 15);
		contentPanel.add(lblNewLabel_2_1);
		
		JLabel lblNewLabel_3 = new JLabel("\uC8FC\uC18C : ");
		lblNewLabel_3.setBounds(12, 156, 57, 15);
		contentPanel.add(lblNewLabel_3);
		
		txtAddress = new JTextField();
		txtAddress.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				if(e.getKeyCode() == KeyEvent.VK_ENTER)
					btnInsert.requestFocus();
			}
		});
		txtAddress.setBounds(67, 153, 340, 21);
		contentPanel.add(txtAddress);
		txtAddress.setColumns(10);
		
		JLabel lblNewLabel_1_1_1_1 = new JLabel("\uC785\uD559\uB144\uB3C4 : ");
		lblNewLabel_1_1_1_1.setBounds(155, 115, 76, 15);
		contentPanel.add(lblNewLabel_1_1_1_1);
		
		JComboBox cbYear = new JComboBox();
		cbYear.setModel(new DefaultComboBoxModel(new String[] {"1990"}));
		cbYear.setBounds(232, 111, 76, 23);
		contentPanel.add(cbYear);
		cbYear.removeAllItems();
		LocalDate now = LocalDate.now(); // 시스템으로부터 현재 연도 불러오기
		int year = now.getYear();
		for(int i=1990 ; i<= year ; i++)
			cbYear.addItem(i);
		
		btnInsert.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String id = txtID.getText();
				String name = txtName.getText();
				String pic = pathName;
				String phone = txtPhone1.getText() + txtPhone2.getText() + txtPhone3.getText();
				String address = txtAddress.getText();
				String Year = cbYear.getSelectedItem().toString();
				
				String sql = "insert into addressbook values('";
				sql = sql + id + "','" + name  + "','" + pic  + "','" + phone  + "','" + address  + "'," + Year + ");";
				
				//System.out.println(sql);
				
				try {
					InsertQuery(sql);
				} catch (ClassNotFoundException | SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		btnInsert.setBounds(171, 196, 97, 23);
		contentPanel.add(btnInsert);
	}
}

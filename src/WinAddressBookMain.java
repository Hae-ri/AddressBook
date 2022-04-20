import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.GridLayout;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.awt.event.ActionEvent;
import javax.swing.ImageIcon;
import java.awt.Toolkit;

public class WinAddressBookMain extends JFrame {

	private JPanel contentPane;
	private WinDrop dlg;
	private WinSearch sdlg;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					WinAddressBookMain frame = new WinAddressBookMain();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public WinAddressBookMain() {
		setIconImage(Toolkit.getDefaultToolkit().getImage("C:\\workspace\\JAVA\\AddressBook\\image\\addressbook.png"));
		setTitle("\uB3D9\uCC3D\uD68C \uC8FC\uC18C(2022\uB144)");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(new GridLayout(2, 2, 5, 5));
		
		JButton btnAllShow = new JButton("\uC804\uCCB4 \uD68C\uC6D0 \uBCF4\uAE30");
		btnAllShow.setIcon(new ImageIcon("C:\\workspace\\JAVA\\AddressBook\\image\\viewall.png"));
		btnAllShow.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//WinAllShow_2 dlg = null;
				WinAllShow dlg = null;
				try {
					//dlg = new WinAllShow_2();
					dlg = new WinAllShow();
				} catch (ClassNotFoundException | SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				dlg.setModal(true);
				dlg.setVisible(true);
			}
		});
		contentPane.add(btnAllShow);
		
		JButton btnAdd = new JButton("\uD68C\uC6D0 \uCD94\uAC00");
		btnAdd.setIcon(new ImageIcon("C:\\workspace\\JAVA\\AddressBook\\image\\add.png"));
		btnAdd.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				WinAdd dlg= new WinAdd();
				dlg.setModal(true);
				dlg.setVisible(true);
			}
		});
		contentPane.add(btnAdd);
		
		JButton btnSearch = new JButton("\uAC1C\uC778 \uD68C\uC6D0 \uAC80\uC0C9");
		btnSearch.setIcon(new ImageIcon("C:\\workspace\\JAVA\\AddressBook\\image\\search.png"));
		btnSearch.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					sdlg = new WinSearch();
				} catch (ClassNotFoundException | SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				sdlg.setModal(true);
				sdlg.setVisible(true);
			}
		});
		contentPane.add(btnSearch);
		
		JButton btnDrop = new JButton("회원탈퇴");
		btnDrop.setIcon(new ImageIcon("C:\\workspace\\JAVA\\AddressBook\\image\\delete.png"));
		btnDrop.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//WinDrop_2 dlg= new WinDrop_2();
				String name = JOptionPane.showInputDialog("삭제할 이름을 입력하시오");
				
				try {
					dlg = new WinDrop(name);
				} catch (ClassNotFoundException | SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				dlg.setModal(true);
				dlg.setVisible(true);
			}
		});
		contentPane.add(btnDrop);
	}

}

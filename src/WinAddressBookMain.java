import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.GridLayout;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class WinAddressBookMain extends JFrame {

	private JPanel contentPane;

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
		setTitle("\uB3D9\uCC3D\uD68C \uC8FC\uC18C(2022\uB144)");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(new GridLayout(2, 2, 5, 5));
		
		JButton btnAllShow = new JButton("\uC804\uCCB4 \uD68C\uC6D0 \uBCF4\uAE30");
		btnAllShow.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				WinAllShow dlg= new WinAllShow();
				dlg.setModal(true);
				dlg.setVisible(true);
			}
		});
		contentPane.add(btnAllShow);
		
		JButton btnAdd = new JButton("\uD68C\uC6D0 \uCD94\uAC00");
		btnAdd.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				WinAdd dlg= new WinAdd();
				dlg.setModal(true);
				dlg.setVisible(true);
			}
		});
		contentPane.add(btnAdd);
		
		JButton btnSearch = new JButton("\uAC1C\uC778 \uD68C\uC6D0 \uAC80\uC0C9");
		btnSearch.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				WinSearch dlg= new WinSearch();
				dlg.setModal(true);
				dlg.setVisible(true);
			}
		});
		contentPane.add(btnSearch);
		
		JButton btnDrop = new JButton("\uD68C\uC6D0 \uD0C8\uD1F4");
		btnDrop.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				WinDrop dlg= new WinDrop();
				dlg.setModal(true);
				dlg.setVisible(true);
			}
		});
		contentPane.add(btnDrop);
	}

}

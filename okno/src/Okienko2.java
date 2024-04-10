

import java.awt.BorderLayout;
import java.awt.EventQueue;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JButton;
import javax.swing.JTextArea;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.GridLayout;
import javax.swing.JLabel;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import javax.swing.JTextField;
import javax.swing.BoxLayout;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.JRadioButton;
import javax.swing.JSpinner;
import javax.swing.JTextPane;
import java.awt.Font;
import javax.swing.JCheckBox;
import java.awt.Color;
import java.awt.ComponentOrientation;
import javax.swing.JSlider;
import javax.swing.JToggleButton;



public class Okienko2 extends JFrame {
	SysTick systick = new SysTick();
	Generator_Tikowy generator=new Generator_Tikowy();
	Knob k = new Knob();
	private JPanel contentPane;
	private JTextField textField;
	private JTextField textField_1;

	private JTextField txtCsr;
	private JTextField txtSystick;
	private JTextField txtJakubWrona;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Okienko2 frame = new Okienko2();
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
	public Okienko2() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 637, 581);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(new GridLayout(2, 2, 0, 0));
		
		JPanel panel = new JPanel();
		contentPane.add(panel);
		panel.setLayout(new BorderLayout(0, 0));
		
		JPanel panel_5 = new JPanel();
		panel.add(panel_5, BorderLayout.SOUTH);
		panel_5.setLayout(new GridLayout(0, 1, 0, 0));
		
		JButton btnNewButton_1 = new JButton("RESET");
		panel_5.add(btnNewButton_1);
		btnNewButton_1.addActionListener(e->  {
			systick.reset();
			textField.setText(String.valueOf(systick.getCSR())+" CVR");
		
	});
		
		JPanel panel_6 = new JPanel();
		panel.add(panel_6, BorderLayout.CENTER);
		panel_6.setLayout(new GridLayout(3, 0, 0, 0));
		
		textField = new JTextField();
		panel_6.add(textField);
		textField.setColumns(10);
		textField.setText(String.valueOf(systick.getCVR())+ " CVR");
		

		JSpinner spinner = new JSpinner();
		panel_6.add(spinner);
		
		JButton btnNewButton = new JButton("Set CVR");
		panel_6.add(btnNewButton);
		
		textField_1 = new JTextField();
		panel_6.add(textField_1);
		textField_1.setColumns(10);
		textField_1.setText(String.valueOf(systick.getRVR())+ " RVR");
		
		JSpinner spinner_1 = new JSpinner();
		panel_6.add(spinner_1);
		
		JButton btnSetrvr = new JButton("Set RVR");
		panel_6.add(btnSetrvr);
		
		txtCsr = new JTextField();
		panel_6.add(txtCsr);
		txtCsr.setText("0 CSR");
		txtCsr.setColumns(10);
		
		JSpinner spinner_2 = new JSpinner();
		panel_6.add(spinner_2);
		
		JButton btnSetcsr = new JButton("Set CSR");
		panel_6.add(btnSetcsr);
		btnSetcsr.addActionListener(e->  {
			systick.setCSR((Integer)spinner_2.getValue());
			txtCsr.setText(String.valueOf(systick.isCSR())+" CSR");
		
		});
		btnSetrvr.addActionListener(e-> {
				systick.setRVR((Integer)spinner_1.getValue());
				textField_1.setText(String.valueOf(systick.getRVR())+" RVR");
			
		});
		btnNewButton.addActionListener(e->  {
				systick.setCVR((Integer)spinner.getValue());
				textField.setText(String.valueOf(systick.getCVR())+" CVR");
			
		});
		
		JPanel panel_1 = new JPanel();
		contentPane.add(panel_1);
		panel_1.setLayout(new GridLayout(0, 2, 0, 0));
		
		
		JToggleButton btn_ON = new JToggleButton("Włącz Generator");
		panel_1.add(btn_ON);
		btn_ON.addActionListener(e->  {
			 if (btn_ON.isSelected())
	            {
	                btn_ON.setText("Wyłącz Generator");
	                generator.trigger();
	            }
	            else 
	            {
	            	btn_ON.setText("Włącz Generator");
	            	generator.halt();
	            }
			
		
	});
		
		JToggleButton btn_Mode = new JToggleButton("Ustaw Mod gen\r\n");
		
		panel_1.add(btn_Mode);
		btn_Mode.addActionListener(e->  {
			 if (btn_Mode.isSelected())
	            {
				 	btn_Mode.setText("BURST_MODE");
	                generator.setMode((byte) 0);
	            }
	            else 
	            {
	            	btn_Mode.setText("CONTINOUS_MODE");
	                generator.setMode((byte) 1);
	            }
			
		
	});
		
		
		JSpinner spinner_3 = new JSpinner();
		panel_1.add(spinner_3);
		
		JButton Burst = new JButton("Set Burst");
		panel_1.add(Burst);
		Burst.addActionListener(e->  {
			generator.setPulseCount((Integer)spinner_3.getValue());

		
	});
		
		
		
		JPanel panel_2 = new JPanel();
		contentPane.add(panel_2);
		panel_2.setLayout(new GridLayout(0, 2, 0, 0));
		
		JCheckBox chckbxEnable = new JCheckBox("Enable");
		panel_2.add(chckbxEnable);
		chckbxEnable.addActionListener(e->  {
				 if (chckbxEnable.isSelected())
		            {
		                systick.setEnable();
		            }
		            else if (!chckbxEnable.isSelected())
		            {
		                systick.setDisable();
		            }
				
			
		});
		
		JRadioButton rdbtnEnable = new JRadioButton("Enable");
		panel_2.add(rdbtnEnable);
		rdbtnEnable.setSelected(systick.isEnableFlag());
		rdbtnEnable.setEnabled(false);
		
		JCheckBox chckbxTickinit = new JCheckBox("TickInternal");
		panel_2.add(chckbxTickinit);
		
	
		chckbxTickinit.addActionListener(e-> {
				 if (chckbxTickinit.isSelected())
		            {
		              
		                systick.setInterruptEnable();
		            }
		            else if (!chckbxTickinit.isSelected())
		            {
		                systick.setInterruptDisable();
		            }
				
			
		});
		
		
		JRadioButton rdbtnTickint = new JRadioButton("TickInternal");
		panel_2.add(rdbtnTickint);
		rdbtnTickint.setSelected(systick.isInterruptFlag());
		rdbtnTickint.setEnabled(false);
		
		JCheckBox chckbxSource = new JCheckBox("Source");
		panel_2.add(chckbxSource);
		chckbxSource.addActionListener(e->  {
				 if (chckbxSource.isSelected())
		            {
		              
		                systick.setSourceInternal();
		            }
		            else if (!chckbxSource.isSelected())
		            {
		                systick.setSourceExternal();
		            }
				
			
		});
		
		
		
		JRadioButton rdbtnSource = new JRadioButton("Source");
		panel_2.add(rdbtnSource);
		rdbtnSource.setSelected(systick.isSourceFlag());
		rdbtnSource.setEnabled(false);
		
		JCheckBox countflag = new JCheckBox("CountFlag");
		panel_2.add(countflag);
		
		
		JRadioButton rdbtnCountflag = new JRadioButton("CountFlag");
		panel_2.add(rdbtnCountflag);
		rdbtnCountflag.setEnabled(false);
		
		
		rdbtnCountflag.setSelected(systick.isCountFlag());
		countflag.addActionListener(e-> {
				 if (countflag.isSelected())
		            {
		              
		               systick.setCountFlagON();
		            }
		            else if (!countflag.isSelected())
		            {
		            	systick.setCountFlagOFF();
		            }
				
			
		});
		
		
		
		
		JPanel panel_3 = new JPanel();
		contentPane.add(panel_3);
		panel_3.setLayout(new GridLayout(2, 0, 0, 0));
		panel_3.add(k);
		k.addActinListener(e->{
			generator.setPulseDelay(k.getValue());
		});
		
		JPanel panel_4 = new JPanel();
		panel_3.add(panel_4);
		panel_4.setLayout(new GridLayout(2, 2, 0, 0));
		
		txtSystick = new JTextField();
		txtSystick.setEnabled(false);
		txtSystick.setEditable(false);
		txtSystick.setFont(new Font("Tahoma", Font.PLAIN, 28));
		txtSystick.setText("Systick ");
		panel_4.add(txtSystick);
		txtSystick.setColumns(10);
		
		txtJakubWrona = new JTextField();
		txtJakubWrona.setEnabled(false);
		txtJakubWrona.setFont(new Font("Tahoma", Font.PLAIN, 21));
		txtJakubWrona.setText("Jakub Wrona 263589");
		txtJakubWrona.setEditable(false);
		panel_4.add(txtJakubWrona);
		txtJakubWrona.setColumns(10);
		
		
//		JTextPane txtpnSystick = new JTextPane();
//		txtpnSystick.setEditable(false);
//		txtpnSystick.setFont(new Font("Times New Roman", Font.PLAIN, 50));
//		txtpnSystick.setText("SysTick");
//		txtpnSystick.setEditable(false);
//		panel_3.add(txtpnSystick);
//		
//		JTextPane txtpnDesignedBy = new JTextPane();
//		txtpnDesignedBy.setFont(new Font("Times New Roman", Font.PLAIN, 50));
//		txtpnDesignedBy.setText("designed by");
//		txtpnDesignedBy.setEditable(false);
//		panel_3.add(txtpnDesignedBy);
//		
//		
//		JTextPane txtpnJakobCrown = new JTextPane();
//		txtpnJakobCrown.setFont(new Font("Times New Roman", Font.PLAIN, 45));
//		txtpnJakobCrown.setText("Jakob Crown");
//		txtpnJakobCrown.setEditable(false);
//		panel_3.add(txtpnJakobCrown);
		systick.addActinListener(e->{
			rdbtnCountflag.setSelected(systick.isCountFlag());
			rdbtnTickint.setSelected(systick.isInterruptFlag());
			rdbtnSource.setSelected(systick.isSourceFlag());
			rdbtnEnable.setSelected(systick.isEnableFlag());
			
			chckbxTickinit.setSelected(systick.isInterruptFlag());
			countflag.setSelected(systick.isCountFlag());
			chckbxEnable.setSelected(systick.isEnableFlag());
			chckbxSource.setSelected(systick.isSourceFlag());
			
			textField.setText(String.valueOf(systick.getCVR()+ " CVR"));
			textField_1.setText(String.valueOf(systick.getRVR())+ " RVR");
			txtCsr.setText(String.valueOf(systick.isCSR())+ " CSR");
		});
		
		generator.addActionListener(e->{
			if(e.getActionCommand()=="ticki1") {
				systick.tickInternal();
			}
		});

		
	}
	
	
}

package vista;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.BorderLayout;
import javax.swing.JTabbedPane;
import javax.swing.JScrollPane;
import javax.swing.BoxLayout;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JComboBox;
import java.awt.GridLayout;
import java.awt.event.ActionListener;
import java.awt.event.ItemListener;

import javax.swing.border.TitledBorder;

import modelo.Cerveceria;

import javax.swing.JSplitPane;
import javax.swing.JLabel;
import java.awt.FlowLayout;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JList;

public class Ventana extends JFrame implements IVista{

	private JPanel contentPane;
	private JTabbedPane tabbedPane;
	private JPanel panel_registro;
	private JPanel panel_mozos;
	private JPanel panel_productos;
	private JPanel panel_promociones;
	private JPanel panel_central_registro;
	private JPanel panel_izq_mozos;
	private JPanel panel_1;
	private JLabel lblMozos;
	private JComboBox comboBox_Mozos;
	private ActionListener actionListener;
	private JPanel panel_central;
	private JScrollPane scrollPane_1;
	private JList list;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Ventana frame = new Ventana();
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
	public Ventana() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 605, 439);
		this.contentPane = new JPanel();
		this.contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(this.contentPane);
		this.contentPane.setLayout(new BorderLayout(0, 0));
		
		this.tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		this.contentPane.add(this.tabbedPane, BorderLayout.CENTER);
		
		this.panel_registro = new JPanel();
		this.tabbedPane.addTab("Registro", null, this.panel_registro, null);
		this.panel_registro.setLayout(new BorderLayout(0, 0));
		
		this.panel_central_registro = new JPanel();
		this.panel_registro.add(this.panel_central_registro, BorderLayout.CENTER);
		
		this.panel_mozos = new JPanel();
		this.tabbedPane.addTab("Mozos", null, this.panel_mozos, null);
		this.panel_mozos.setLayout(new BorderLayout(0, 0));
		
		this.panel_izq_mozos = new JPanel();
		this.panel_mozos.add(this.panel_izq_mozos, BorderLayout.WEST);
		this.panel_izq_mozos.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
		
		this.lblMozos = new JLabel("Mozos");
		this.panel_izq_mozos.add(this.lblMozos);
		
		this.comboBox_Mozos = new JComboBox<String>();//add(comboBox);
		this.panel_izq_mozos.add(this.comboBox_Mozos);
		comboBox_Mozos.addItem(Cerveceria.getInstance().getMozos().get("Marti").getNyA());
		comboBox_Mozos.addItem(Cerveceria.getInstance().getMozos().get("Valen").getNyA());
		comboBox_Mozos.addItem(Cerveceria.getInstance().getMozos().get("Pau").getNyA());
		comboBox_Mozos.addItemListener((ItemListener) this);
		comboBox_Mozos.addActionListener((ActionListener) this);
		
		//comboBox_Mozos.addItemListener(this);
		//comboBox_Mozos.addActionListener(this);
		
		this.panel_1 = new JPanel();
		this.panel_mozos.add(this.panel_1, BorderLayout.CENTER);
		this.panel_1.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
		
		this.panel_productos = new JPanel();
		this.tabbedPane.addTab("Productos", null, this.panel_productos, null);
		this.panel_productos.setLayout(new BorderLayout(0, 0));
		
		this.panel_promociones = new JPanel();
		this.tabbedPane.addTab("Promociones", null, this.panel_promociones, null);
		this.panel_promociones.setLayout(new BorderLayout(0, 0));
		
		this.panel_central = new JPanel();
		this.panel_promociones.add(this.panel_central, BorderLayout.CENTER);
		this.panel_central.setLayout(new BorderLayout(0, 0));
		
		this.scrollPane_1 = new JScrollPane();
		this.panel_central.add(this.scrollPane_1, BorderLayout.CENTER);
		
		this.list = new JList();
		this.scrollPane_1.setViewportView(this.list);
		
		
	}

	@Override
	public void setActionListener(ActionListener actionListener) {
		// TODO Auto-generated method stub
		this.actionListener = actionListener;
		//ver si algun boton o algo lo activa, mepa que no pero chequear!
		
	}
}

package vista;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.BorderLayout;
import javax.swing.JTabbedPane;
import javax.swing.JScrollPane;

public class Ventana extends JFrame {

	private JPanel contentPane;
	private JTabbedPane tabbedPane;
	private JPanel panel_registro;
	private JPanel panel_mesas;
	private JPanel panel_mozos;
	private JPanel panel_productos;
	private JPanel panel_promociones;
	private JScrollPane scrollPane_lista_mesas;
	private JScrollPane scrollPane_lista_mozos;
	private JScrollPane scrollPane_lista_promociones;
	private JScrollPane scrollPane_lista_productos;

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
		setBounds(100, 100, 576, 425);
		this.contentPane = new JPanel();
		this.contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(this.contentPane);
		this.contentPane.setLayout(new BorderLayout(0, 0));
		
		this.tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		this.contentPane.add(this.tabbedPane, BorderLayout.CENTER);
		
		this.panel_registro = new JPanel();
		this.tabbedPane.addTab("Registro", null, this.panel_registro, null);
		this.panel_registro.setLayout(new BorderLayout(0, 0));
		
		this.panel_mesas = new JPanel();
		this.tabbedPane.addTab("Mesas", null, this.panel_mesas, null);
		this.panel_mesas.setLayout(new BorderLayout(0, 0));
		
		this.scrollPane_lista_mesas = new JScrollPane();
		this.panel_mesas.add(this.scrollPane_lista_mesas, BorderLayout.WEST);
		
		this.panel_mozos = new JPanel();
		this.tabbedPane.addTab("Mozos", null, this.panel_mozos, null);
		this.panel_mozos.setLayout(new BorderLayout(0, 0));
		
		this.scrollPane_lista_mozos = new JScrollPane();
		this.panel_mozos.add(this.scrollPane_lista_mozos);
		
		this.panel_productos = new JPanel();
		this.tabbedPane.addTab("Productos", null, this.panel_productos, null);
		this.panel_productos.setLayout(new BorderLayout(0, 0));
		
		this.scrollPane_lista_productos = new JScrollPane();
		this.panel_productos.add(this.scrollPane_lista_productos, BorderLayout.WEST);
		
		this.panel_promociones = new JPanel();
		this.tabbedPane.addTab("Promociones", null, this.panel_promociones, null);
		this.panel_promociones.setLayout(new BorderLayout(0, 0));
		
		this.scrollPane_lista_promociones = new JScrollPane();
		this.panel_promociones.add(this.scrollPane_lista_promociones, BorderLayout.WEST);
	}
}

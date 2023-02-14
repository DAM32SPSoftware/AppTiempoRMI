package tiempoRMI;

import java.awt.EventQueue;
import java.rmi.Naming;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import java.awt.Color;
import javax.swing.JTable;
import javax.swing.JScrollPane;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class ClienteMain extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private static DefaultTableModel model;
	private static String[] parts;
	private static String[][] partsTroceadas;
	private JScrollPane scrollPane;
	private static JTable table;
	private static JLabel lblHoyFecha;
	private static JLabel a;
	private static JLabel b;
	private static JLabel c;
	private static JLabel lblAbrirToldoBool;
	private static JLabel lblEncenderEstufaBool;
	private static JLabel lblAbrirAspersoresBool;
	private JLabel lblprevisinDias;
	private JLabel lblprevisinDias_1;
	private JLabel lblprevisinDias_2;
	private JLabel lblmaxC;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ClienteMain frame = new ClienteMain();
					frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // Already there
					//frame.setExtendedState(JFrame.MAXIMIZED_BOTH);
					frame.setUndecorated(true);
					frame.setVisible(true);
				} catch (Exception e) {
					//e.printStackTrace();
				}
			}
		});
		String linea;
		// https://pastebin.com/raw/0pKQDgRn
		try {
			//BufferedReader entrada=new BufferedReader(new InputStreamReader(System.in));
			//System.out.println("Introduce la URL :");
			//String url = entrada.readLine();
			String url = "https://pastebin.com/raw/0pKQDgRn";
			InterfaceServidor obj=(InterfaceServidor) Naming.lookup("//localhost/Objeto");
			linea = obj.mandarURL(url);
			System.out.println(linea);
			parts = linea.split("#");
			partsTroceadas = new String[7][7];
			for (int i = 0; i < parts.length; i++) {
				String[] partesTemp = parts[i].split("€");
				partsTroceadas[i][0] = partesTemp[0];
				partsTroceadas[i][1] = partesTemp[1]+"°";
				partsTroceadas[i][2] = partesTemp[2]+"°";
				partsTroceadas[i][3] = partesTemp[3];
				partsTroceadas[i][4] = partesTemp[4]+" km/h";
				//String imprimir = parts[i].replace('€', '/');
				//System.out.println(imprimir);
			}
			cargarTabla();
			//entrada.close();
		} catch (Exception e) {
			//System.out.println("Excepcion: "+e.getMessage());
			e.printStackTrace();
		}
		
	}

	/**
	 * Create the frame.
	 */
	public ClienteMain() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 672, 446);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(63, 99, 88));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
//		model = new DefaultTableModel();
//		table.setModel(model);
		
		JLabel lblNewLabel = new JLabel("EL TIEMPO");
		lblNewLabel.setForeground(new Color(255, 255, 128));
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 16));
		lblNewLabel.setBounds(283, 10, 198, 42);
		contentPane.add(lblNewLabel);
		
		JButton btnNewButton = new JButton("SALIR");
		btnNewButton.setFont(new Font("Tahoma", Font.BOLD, 10));
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(JOptionPane.showConfirmDialog(
						btnNewButton, 
						"¿Estás seguro de que quieres salir?", "Salir", 
						JOptionPane.OK_CANCEL_OPTION, JOptionPane.INFORMATION_MESSAGE) == 0) {
					System.exit(0);
				}	
			}
		});
		btnNewButton.setBounds(537, 382, 111, 42);
		contentPane.add(btnNewButton);
		
		scrollPane = new JScrollPane();
		scrollPane.setBounds(21, 80, 627, 138);
		contentPane.add(scrollPane);
		
		table = new JTable();
		table.setEnabled(false);
		table.setBackground(Color.LIGHT_GRAY);
		scrollPane.setViewportView(table);
		
		JLabel lblHoy = new JLabel("HOY:");
		lblHoy.setForeground(new Color(255, 255, 128));
		lblHoy.setFont(new Font("Tahoma", Font.BOLD, 13));
		lblHoy.setBounds(21, 237, 47, 42);
		contentPane.add(lblHoy);
		
		lblHoyFecha = new JLabel("");
		lblHoyFecha.setForeground(new Color(255, 255, 128));
		lblHoyFecha.setFont(new Font("Tahoma", Font.BOLD, 13));
		lblHoyFecha.setBounds(63, 237, 134, 42);
		contentPane.add(lblHoyFecha);
		
		a = new JLabel("-ABRIR TOLDO:");
		a.setForeground(new Color(255, 255, 128));
		a.setFont(new Font("Tahoma", Font.PLAIN, 13));
		a.setBounds(48, 274, 139, 42);
		contentPane.add(a);
		
		b = new JLabel("-ENCENDER ESTUFA:");
		b.setForeground(new Color(255, 255, 128));
		b.setFont(new Font("Tahoma", Font.PLAIN, 13));
		b.setBounds(48, 310, 139, 42);
		contentPane.add(b);
		
		c = new JLabel("-ABRIR ASPERSORES:");
		c.setForeground(new Color(255, 255, 128));
		c.setFont(new Font("Tahoma", Font.PLAIN, 13));
		c.setBounds(48, 349, 149, 42);
		contentPane.add(c);
		
		lblAbrirToldoBool = new JLabel("");
		lblAbrirToldoBool.setForeground(new Color(255, 255, 128));
		lblAbrirToldoBool.setFont(new Font("Tahoma", Font.BOLD, 13));
		lblAbrirToldoBool.setBounds(145, 274, 134, 42);
		contentPane.add(lblAbrirToldoBool);
		
		lblEncenderEstufaBool = new JLabel("");
		lblEncenderEstufaBool.setForeground(new Color(255, 255, 128));
		lblEncenderEstufaBool.setFont(new Font("Tahoma", Font.BOLD, 13));
		lblEncenderEstufaBool.setBounds(175, 310, 134, 42);
		contentPane.add(lblEncenderEstufaBool);
		
		lblAbrirAspersoresBool = new JLabel("");
		lblAbrirAspersoresBool.setForeground(new Color(255, 255, 128));
		lblAbrirAspersoresBool.setFont(new Font("Tahoma", Font.BOLD, 13));
		lblAbrirAspersoresBool.setBounds(185, 349, 134, 42);
		contentPane.add(lblAbrirAspersoresBool);
		
		lblprevisinDias = new JLabel("*Prevision 7 dias");
		lblprevisinDias.setForeground(new Color(255, 255, 128));
		lblprevisinDias.setFont(new Font("Tahoma", Font.PLAIN, 10));
		lblprevisinDias.setBounds(21, 39, 139, 42);
		contentPane.add(lblprevisinDias);
		
		lblprevisinDias_1 = new JLabel("(Max. Cº > 20 OR km/h  < 10)");
		lblprevisinDias_1.setForeground(new Color(255, 255, 128));
		lblprevisinDias_1.setFont(new Font("Tahoma", Font.PLAIN, 10));
		lblprevisinDias_1.setBounds(237, 275, 177, 42);
		contentPane.add(lblprevisinDias_1);
		
		lblprevisinDias_2 = new JLabel("(Min. Cº < 10)");
		lblprevisinDias_2.setForeground(new Color(255, 255, 128));
		lblprevisinDias_2.setFont(new Font("Tahoma", Font.PLAIN, 10));
		lblprevisinDias_2.setBounds(237, 310, 139, 42);
		contentPane.add(lblprevisinDias_2);
		
		lblmaxC = new JLabel("(Min. Cº > 20)");
		lblmaxC.setForeground(new Color(255, 255, 128));
		lblmaxC.setFont(new Font("Tahoma", Font.PLAIN, 10));
		lblmaxC.setBounds(237, 349, 139, 42);
		contentPane.add(lblmaxC);
//		model.addColumn("Fecha");
//		model.addColumn("Max. C°");
//		model.addColumn("Min. C°");
//		model.addColumn("Tiempo");
//		model.addColumn("Vel. viento");
		
	}
	
	public static void cargarTabla()  {
//		Object[] encabezados = new Object[5];
//		encabezados[0] = "Fecha";
//		encabezados[1] = "Max. C°";
//		encabezados[2] = "Min. C°";
//		encabezados[3] = "Tiempo";
//		encabezados[4] = "Vel. viento";
//		model.addRow(encabezados);
		model = new DefaultTableModel();
		table.setModel(model);
		model.addColumn("Fecha");
		model.addColumn("Max. C°");
		model.addColumn("Min. C°");
		model.addColumn("Tiempo");
		model.addColumn("Vel. viento");
		for (int i = 0; i < partsTroceadas.length; i++) {
			Object[] fila = new Object[partsTroceadas[i].length];
			fila[0] = partsTroceadas[i][0];
			fila[1] = partsTroceadas[i][1];
			fila[2] = partsTroceadas[i][2];
			fila[3] = partsTroceadas[i][3];
			fila[4] = partsTroceadas[i][4];
			model.addRow(fila);
			if (i==0) {
				lblHoyFecha.setText(partsTroceadas[i][0]);
				System.out.println(partsTroceadas[i][1]);
				String[] maxTemp = partsTroceadas[i][1].split("°");
				String[] minTemp = partsTroceadas[i][2].split("°");
				String[] kmh = partsTroceadas[i][4].split(" ");
				if (Integer.valueOf(maxTemp[0]) > 20 || Integer.valueOf(kmh[0]) < 10) {
					lblAbrirToldoBool.setText("SI");
				} else {
					lblAbrirToldoBool.setText("NO");
				}
				if (Integer.valueOf(minTemp[0]) < 10) {
					lblEncenderEstufaBool.setText("SI");
				} else {
					lblEncenderEstufaBool.setText("NO");
				}
				if (Integer.valueOf(minTemp[0]) > 20) {
					lblAbrirAspersoresBool.setText("SI");
				} else {
					lblAbrirAspersoresBool.setText("NO");
				}
			}
		}
		
	}
}

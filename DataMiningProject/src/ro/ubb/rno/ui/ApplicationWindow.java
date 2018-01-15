package ro.ubb.rno.ui;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.EventQueue;

import javax.swing.DefaultListModel;
import javax.swing.JFrame;
import java.awt.GridLayout;
import java.awt.Label;
import javax.swing.JPanel;
import javax.swing.JLabel;
import java.awt.Font;
import java.awt.SystemColor;
import javax.swing.JScrollPane;
import javax.swing.ListModel;
import javax.swing.ListSelectionModel;

import ro.ubb.da.DataAccessService;
import ro.ubb.rno.controller.ProductsController;
import ro.ubb.rno.model.Product;

import javax.swing.JList;

public class ApplicationWindow {

	private JFrame frame;

	private ProductsController productsController;
	
	public ApplicationWindow(ProductsController productsController) {
		super();
		this.productsController = productsController;
		initialize();
	}

	public void start() {
		frame.setVisible(true);
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.getContentPane().setBackground(SystemColor.inactiveCaptionBorder);
		frame.setBackground(SystemColor.inactiveCaptionBorder);
		frame.setBounds(100, 100, 385, 493);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		InitializeShoppingCartList();
	}

	private void InitializeShoppingCartList() {
		frame.getContentPane().setLayout(null);
		
		DefaultListModel listModel = new DefaultListModel();
		for(Product product : productsController.getProductsList()) {
			listModel.addElement(product.toString());
		}
		
		JList list = new JList(listModel);
		list.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		list.setVisibleRowCount(10);
		
		JScrollPane scrollPane = new JScrollPane(list);
		scrollPane.setBounds(0, 55, 162, 201);
		
		frame.getContentPane().add(scrollPane);
		
		JLabel lblProducts = new JLabel("Products");
		lblProducts.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblProducts.setBounds(0, 11, 111, 33);
		frame.getContentPane().add(lblProducts);
		
		
		
		
		
	}
}

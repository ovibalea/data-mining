package ro.ubb.rno.ui;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.EventQueue;

import javax.swing.DefaultListCellRenderer;
import javax.swing.DefaultListModel;
import javax.swing.JFrame;
import java.awt.GridLayout;
import java.awt.Label;
import javax.swing.JPanel;
import javax.swing.JLabel;
import java.awt.Font;
import java.awt.SystemColor;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import javax.swing.JScrollPane;
import javax.swing.ListModel;
import javax.swing.ListSelectionModel;

import ro.ubb.da.DataAccessService;
import ro.ubb.rno.controller.ProductsController;
import ro.ubb.rno.model.Product;

import javax.swing.JList;
import javax.swing.JButton;

public class ApplicationWindow {

	private JFrame frame;
	
	private ProductsController productsController;
	private JList<Product> productsList;
	private DefaultListModel<Product> shoppingCart;
	private DefaultListModel<Product> recomandedProducts;
	
	
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
		frame.setBounds(100, 100, 610, 334);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		InitializeProductsList();
		InitializeShoppingCart();
		InitializeRecomanded();
	}

	private void InitializeProductsList() {
		
		
		DefaultListModel<Product> productsListModel = new DefaultListModel<Product>();
		for(Product product : productsController.getProductsList()) {
			productsListModel.addElement(product);
		}
		
		productsList = new JList<Product>(productsListModel);
		productsList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		productsList.setVisibleRowCount(10);
		productsList.setCellRenderer(new ProductCellRenderer() );
		
		
		JScrollPane scrollPane = new JScrollPane(productsList);
		scrollPane.setBounds(10, 55, 162, 201);
		
		frame.getContentPane().add(scrollPane);
		
		JLabel lblProducts = new JLabel("Products");
		lblProducts.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblProducts.setBounds(10, 11, 111, 33);
		frame.getContentPane().add(lblProducts);
		
		
	}
	
	private void InitializeShoppingCart() {
		
		shoppingCart = new DefaultListModel<Product>();
		
		JList<Product> list = new JList<Product>(shoppingCart);
		list.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		list.setVisibleRowCount(10);
		list.setCellRenderer(new ProductCellRenderer() );
		
		JScrollPane scrollPane = new JScrollPane(list);
		scrollPane.setBounds(190, 55, 162, 150);
		
		frame.getContentPane().add(scrollPane);
		
		JLabel lblProducts = new JLabel("Shopping Cart");
		lblProducts.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblProducts.setBounds(190, 11, 111, 33);
		frame.getContentPane().add(lblProducts);
		
		JButton btnAddToCart = new JButton("Add To Cart");
		btnAddToCart.setFont(new Font("Arial", Font.PLAIN, 14));
		btnAddToCart.setBounds(214, 216, 111, 23);
		frame.getContentPane().add(btnAddToCart);
		btnAddToCart.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				if(!productsList.isSelectionEmpty()) {
					Product selectedProduct = (Product)productsList.getSelectedValue();
					shoppingCart.addElement(selectedProduct);
					recomandedProducts.clear();
					for(Product prod : productsController.getRecommendations(Collections.list(shoppingCart.elements()))) {
						recomandedProducts.addElement(prod);
					}
					
				}
			}
		});
		
		
	}
	
private void InitializeRecomanded() {
		
		recomandedProducts = new DefaultListModel<Product>();
		
		JList<Product> list = new JList<Product>(recomandedProducts);
		list.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		list.setVisibleRowCount(10);
		list.setCellRenderer(new ProductCellRenderer() );
		
		JScrollPane scrollPane = new JScrollPane(list);
		scrollPane.setBounds(370, 55, 162, 150);
		
		frame.getContentPane().add(scrollPane);
		
		JLabel lblProducts = new JLabel("Recomanded");
		lblProducts.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblProducts.setBounds(370, 11, 111, 33);
		frame.getContentPane().add(lblProducts);
		
		
		
	}
}

class ProductCellRenderer extends DefaultListCellRenderer {
    public Component getListCellRendererComponent(JList<?> list, Object value, int index, boolean isSelected, boolean cellHasFocus) {
        super.getListCellRendererComponent(list, value, index, isSelected, cellHasFocus);
        if (value instanceof Product) {
            setText(((Product)value).getProductName());
        }
        return this;
    }
}

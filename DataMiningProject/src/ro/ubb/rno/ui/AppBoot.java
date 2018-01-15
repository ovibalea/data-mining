package ro.ubb.rno.ui;

import java.awt.EventQueue;

import ro.ubb.da.DataAccessService;
import ro.ubb.rno.controller.ProductsController;

public class AppBoot {

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					DataAccessService dataAccessService = new DataAccessService();
					ProductsController productsController = new ProductsController(dataAccessService);
					ApplicationWindow window = new ApplicationWindow(productsController);
					window.start();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
}

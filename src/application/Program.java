package application;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Scanner;

import entities.Product;

public class Program {

	public static void main(String[] args) {

		Locale.setDefault(Locale.US);
		Scanner sc = new Scanner(System.in);

		List<Product> list = new ArrayList<>();
		String strPath = "D:\\Temp\\list.csv";

		File path = new File(strPath);
		String sourcePath = path.getParent();

		boolean success = new File(sourcePath + "\\out").mkdir();
		String targetPath = sourcePath + "\\out\\summary.csv";

		try (BufferedReader br = new BufferedReader(new FileReader(strPath))) {

			String line = br.readLine();
			while (line != null) {

				String[] fields = line.split(",");
				String name = fields[0];
				double price = Double.parseDouble(fields[1]);
				int quantity = Integer.parseInt(fields[2]);

				list.add(new Product(name, price, quantity));

				line = br.readLine();
			}

		} catch (IOException e) {
			System.out.println("Error: " + e.getMessage());
		}

		try (BufferedWriter bw = new BufferedWriter(new FileWriter(targetPath))) {
			for (Product item : list) {
				bw.write(item.getName() + "," + String.format("%.2f", item.totalValue()));
				bw.newLine();

			}
		} catch (IOException e) {
			System.out.println("Error: " + e.getMessage());
		}
		
		sc.close();
		
	}
}

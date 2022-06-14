package application;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Locale;
import java.util.Scanner;


import entities.Sale;

public class Program {

	public static void main(String[] args) {

		Locale.setDefault(Locale.US);
		Scanner sc = new Scanner(System.in);

		System.out.print("Entre o caminho do arquivo: ");
		String path = sc.nextLine();

		try (BufferedReader br = new BufferedReader(new FileReader(path))) {

			List<Sale> list = new ArrayList<>();

			String line = br.readLine();

			while (line != null) {

				String[] fields = line.split(",");
				list.add(new Sale(Integer.parseInt(fields[0]), Integer.parseInt(fields[1]), fields[2],
						Integer.parseInt(fields[3]), Double.parseDouble(fields[4])));

				line = br.readLine();

			}


			Comparator<Sale> comp = (pm1,pm2) -> pm1.averagePrice().compareTo(pm2.averagePrice());

			List<Sale> values = list.stream().filter(y -> y.getYear() == 2016)
					.sorted(comp.reversed()).limit(5)
					.toList();
			
			System.out.println();
			System.out.println("Cinco primeiras vendas de 2016 de maior preço médio:");
			values.forEach(System.out::println);
			
			double logan = list.stream().filter(l -> l.getSeller().charAt(0) == 'L')
					.filter(l -> l.getMonth() == 7 || l.getMonth() == 1)
					.map(t -> t.getTotal())
					.reduce(0.0, (x,y) -> x + y );
			
			System.out.println();
			System.out.println("Valor total vendido pelo vendedor Logan nos meses 1 e 7 = " + logan);
			
			

		} catch (IOException e) {
			
			System.out.println("Erro: " + path + " (O sistema não pode encontrar o arquivo especificado)");
		}

		sc.close();

	}

}

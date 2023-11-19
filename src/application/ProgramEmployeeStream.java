package application;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Locale;
import java.util.Scanner;
import java.util.stream.Collector;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import entities.EmplyeeStream;

public class ProgramEmployeeStream {

	public static void main(String[] args) {

		Locale.setDefault(Locale.US);
		Scanner sc = new Scanner(System.in);

		System.out.println("Enter full file path: ");
		String path = sc.nextLine();

		try (BufferedReader br = new BufferedReader(new FileReader(path))) {

			List<EmplyeeStream> list = new ArrayList<>();
			String line = br.readLine();

			while (line != null) {
				String[] fields = line.split(",");
				list.add(new EmplyeeStream(fields[0], fields[1], Double.parseDouble(fields[2])));
				line = br.readLine();

			} // C:\temp\ExercicioStream.txt

			System.out.println("Enter salary:");
			double mediaSalary = sc.nextDouble();

			System.out.println("Email of people whose salary is more than: " + mediaSalary);

			Comparator<String> comp = (s1, s2) -> s1.toUpperCase().compareTo(s2.toUpperCase());

			List<String> email = list.stream()
					.filter(p -> p.getSalary() > mediaSalary).map(p -> p.getEmail())
					.sorted(comp.reversed())
					.collect(Collectors.toList());

			email.forEach(System.out::println);

			System.out.println();
			
			System.out.println("Sum of salary of people whose name starts with 'M': ");

			double sumSalary = list.stream()
					.filter(x -> x.getName().charAt(0) == 'M')
					.map(x -> x.getSalary())
					.reduce(0.0, (x, y) -> x + y);
			System.out.println(sumSalary);
			
			
		} catch (Exception e) {

			System.out.println("Erro " + e.getMessage());
		}

		sc.close();
	}

}

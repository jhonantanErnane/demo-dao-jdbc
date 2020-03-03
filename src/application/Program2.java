package application;

import java.util.Scanner;

import model.dao.DaoFactory;
import model.dao.DepartmentDao;
import model.entities.Department;

public class Program2 {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);

		DepartmentDao depDao = DaoFactory.createDepartmentDao();
		System.out.println("=== TEST 1: Department findById ====");
		Department dep = depDao.findById(2);
		System.out.println(dep);
		
		System.out.println("\n=== TEST 2: Department findAll ====");
		var listDep = depDao.findAll();
		listDep.forEach(System.out::println);
		
		System.out.println("\n=== TEST 3: Department insert ====");
		var depTeste = new Department(null, "Fake");
		depDao.insert(depTeste);
		System.out.println(depTeste);

		
		
		sc.close();
	}

}

package application;

import java.util.Scanner;

import model.dao.DaoFactory;
import model.dao.DepartmentDao;
import model.entities.Department;

public class Program2 {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);

		DepartmentDao depDao = DaoFactory.createDepartmentDao();
		System.out.println("=== TEST 1: seller findById ====");
		Department dep = depDao.findById(2);
		System.out.println(dep);
		
		sc.close();
	}

}

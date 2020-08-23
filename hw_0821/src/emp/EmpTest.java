package emp;

import java.sql.SQLException;
import java.util.List;
import java.util.Scanner;

public class EmpTest {

	public static void main(String[] args) throws SQLException {

		Scanner sc = new Scanner(System.in);
		boolean flag = true;
		
		EmpMgrImpl mgr = EmpMgrImpl.getInstance();
		while(flag) {
			System.out.println("================= 도서 관리 시스템 =================");
			System.out.println("1. 직원 저장");
			System.out.println("2. 직원 정보 전체보기");
			System.out.println("3. 사번으로 직원 조회");
			System.out.println("4. 이름으로 직원 조회");
			System.out.println("5. 직원 부서 변경");
			System.out.println("6. 직원 삭제");
			System.out.println("0. 시스템 종료");
			System.out.print("번호 입력 : ");
			
			int num = sc.nextInt();
			
			switch(num) {
			case 1:{
				Employee emp = new Employee();
				System.out.println("직원 번호를 입력하세요.");
				emp.setEmpNo(sc.nextInt());
				System.out.println("직원 이름을 입력하세요.");
				emp.setName(sc.next());
				System.out.println("직원 직위를 입력하세요.");
				emp.setPosition(sc.next());
				System.out.println("직원 부서를 입력하세요.");
				emp.setDept(sc.next());
				mgr.add(emp);
				System.out.println("직원 정보가 입력되었습니다.");
				break;
			}
			case 2:{
				List<Employee> list = mgr.search();
				for(int i = 0; i < list.size(); i++ ) {
					System.out.println(list.get(i));
				}
				break;
			}
			case 3:{
				System.out.println("조회할 직원 번호를 입력하세요");				
				List<Employee> list = mgr.search(sc.nextInt());
				for(int i = 0; i < list.size(); i++ ) {
					System.out.println(list.get(i));
				}
				break;
			}
			case 4:{
				System.out.println("조회할 직원 이름를 입력하세요");				
				List<Employee> list = mgr.search(sc.next());
				for(int i = 0; i < list.size(); i++ ) {
					System.out.println(list.get(i));
				}
				
				break;
			}
			case 5:{
				System.out.println("직원 번호와 변경할 부서를 입력하세요");
				boolean result = mgr.update(sc.nextInt(), sc.next());
				if(result)
					System.out.println("변경 되었습니다.");
				break;
			}
			case 6:{
				System.out.println("삭제할 직원번호를 입력하세요.");
				boolean result = mgr.delete(sc.nextInt());
				if(result)
					System.out.println("삭제되었습니다.");
				break;
			}
			case 0:{
				break;
			}
			}
			
		}
		

	}
}

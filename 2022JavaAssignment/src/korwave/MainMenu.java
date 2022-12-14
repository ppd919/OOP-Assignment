package korwave;

// 메인 메뉴 클래스 - PrintMenu 인터페이스 상속
public class MainMenu implements PrintMenu{
	int optionSize = 2; // 옵션 개수
	int exitNumber = optionSize; // 프로그램 종료 번호 = 옵션 개수
		
	@Override
	public void printTitle() {
		System.out.println("--------------------");
		System.out.println("[메인 메뉴]");
	}
	
	@Override
	public void printTitle(CountryOutline country) {
		
	}

	@Override
	public void printOptions() {
		System.out.println("1. 국가 선택");
		System.out.println("2. 종료");
		System.out.println("--------------------");
		System.out.println("번호를 입력하세요. (1~2)");
	}

	@Override
	public void printError() {
		System.out.println("[!] 잘못된 값을 입력하였습니다. 1~2 사이의 값을 입력하세요.");
	}

	@Override
	public void printExit() {
		System.out.println("프로그램을 종료합니다.");
	}
}

package korwave;

// 국가 선택 메뉴 클래스 - PrintMenu 인터페이스 상속
public class CountryMenu implements PrintMenu{
	public static int OPTION_SIZE = CountryName.values().length + 1; // 옵션 개수
	int backNumber = OPTION_SIZE; // 뒤로가기 번호 = 옵션 개수
	
	@Override
	public void printTitle() {
		System.out.println("--------------------");
		System.out.println("[국가 선택]");
	}
	
	@Override
	public void printTitle(CountryOutline country) {
		
	}

	@Override
	public void printOptions() {
		// 추후에 국가를 추가하더라도 열거체만 수정하면 되도록 옵션 출력
		for (int i = 0; i < OPTION_SIZE - 1; i++) {
			System.out.printf("%d. %s\n", i+1, CountryName.values()[i]);
		}
		System.out.printf("%d. 뒤로가기\n", OPTION_SIZE);
		System.out.println("--------------------");
		System.out.printf("번호를 입력하세요. (1~%d)\n", OPTION_SIZE);
	}

	@Override
	public void printError() {
		System.out.printf("[!] 잘못된 값을 입력하였습니다. 1~%d 사이의 값을 입력하세요.\n", OPTION_SIZE);
	}

	@Override
	public void printExit() {
		
	}
}

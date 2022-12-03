package korwave;

public class CountryMenu implements PrintMenu{
	public static int OPTION_SIZE = CountryName.values().length + 1;
	public static int BACK_NUMBER = OPTION_SIZE;
	
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

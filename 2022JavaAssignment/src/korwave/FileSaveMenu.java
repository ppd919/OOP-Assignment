package korwave;

public class FileSaveMenu implements PrintMenu {
	int optionSize = 2;
	int backNumber = optionSize;
	
	@Override
	public void printTitle() {
		System.out.println("--------------------");
	}

	@Override
	public void printTitle(CountryOutline country) {
		
	}

	@Override
	public void printOptions() {
		System.out.println("1. txt 파일로 저장하기");
		System.out.println("2. 뒤로가기");
		System.out.println("번호를 입력하세요. (1~2)");
	}

	@Override
	public void printExit() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void printError() {
		System.out.println("[!] 잘못된 값을 입력하였습니다. 1~2 사이의 번호를 입력하세요.");
	}
}

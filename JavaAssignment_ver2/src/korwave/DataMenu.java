package korwave;

// 데이터 메뉴 클래스 - PrintMenu 인터페이스 상속
public class DataMenu implements PrintMenu {
	public static int OPTION_SIZE = 6; // 옵션 개수
	int backNumber = OPTION_SIZE; // 뒤로가기 번호 = 옵션 개수
	@Override
	public void printTitle() {
		
	}
	
	@Override
	public void printTitle(CountryOutline country) {
		System.out.println("--------------------");
		System.out.printf("[%s에서의 한류]\n", country.getCountryName());
	}

	@Override
	public void printOptions() {
		System.out.println("1. 한국 연상 이미지");
		System.out.println("2. 한국 드라마의 인기 요인");
		System.out.println("3. 한국 음악의 인기 요인");
		System.out.println("4. 한국 음식의 인기 요인");
		System.out.println("5. 가장 좋아하는 한국 문화 콘텐츠 (드라마, 영화, 가수/그룹)");
		System.out.println("6. 뒤로가기");
		System.out.println("--------------------");
		System.out.println("번호를 입력하세요. (1~6)");
	}

	@Override
	public void printError() {
		System.out.println("[!] 잘못된 값을 입력하였습니다. 1~6 사이의 값을 입력하세요.");
	}

	@Override
	public void printExit() {
		
	}
}

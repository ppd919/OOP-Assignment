package korwave;

// 메뉴 출력 인터페이스
public interface PrintMenu {
	public void printTitle(); // 메뉴 타이틀 출력 (국가 선택 전)
	public void printTitle(CountryOutline country); // 메뉴 타이틀 출력 (국가 선택 후)
	public void printOptions(); // 선택 가능한 번호 (옵션) 출력
	public void printExit(); // 프로그램 종료 출력
	public void printError(); // 에러 출력 (범위 밖의 번호를 입력 시)
}

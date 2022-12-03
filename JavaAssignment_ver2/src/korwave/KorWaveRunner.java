package korwave;

import java.util.Scanner;
import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStreamReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

public class KorWaveRunner {

	public static void main(String[] args) {
		// 1. 과제 설명 및 항목별 주석 작성 (X)
		// 2. 클래스 상속 (O)
		// 3. 인터페이스 (O)
		// 4. 예외처리 (O, 파일 입출력에서 처리함. 번호 이외의 값은 아직 처리 X)
		// 5. 다형성 (상속/인터페이스) (O)
		// 6. 참조타입 (배열/열거) (O)
		// 7. 컬렉션 프레임워크 (O, ArrayList 사용)
		// 8. 파일 입출력 (O)
		// 9. 동영상 (X)

		// 체크할 점.
		// 변수 관련) 변수명, 메뉴 정적 변수 설정
		// 파일 관련) 파일 불러올 때 위치 + 파일 내보낼 때 파일명
		String[][] fileMultiArray = new String[CountryMenu.OPTION_SIZE - 1][DataMenu.OPTION_SIZE - 1];
		
		try {
			BufferedReader br = null;
			br = new BufferedReader(new InputStreamReader(new FileInputStream("C:/Users/PARK/Desktop/JavaAssignment.txt"), "utf-8"));
			
			for (int i = 0; i < CountryMenu.OPTION_SIZE- 1; i++) {
				int j = 0;
				String[] fileArray = new String[5];
				ArrayList<String> temp = new ArrayList<String>();
				while(true) {
					String line = br.readLine();
					if (line.charAt(0) == '@' || line.charAt(0) == '#' || line.charAt(0) == '!') {
						fileArray[j] = temp.toString();
						temp.clear();
						j += 1;
						if (line.charAt(0) == '#') break;
						if (line.charAt(0) == '!') {
							br.close();
							break;
						}
					}
					else temp.add(line);
				}
				fileMultiArray[i] = fileArray;
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		MainMenu mainMenu = new MainMenu();
		Scanner scanner = new Scanner(System.in);
		
		// 프로그램 실행
		while (true) {
			mainMenu.printTitle();
			mainMenu.printOptions();
			
			int selectMainMenu = scanner.nextInt();
			if (selectMainMenu == mainMenu.exitNumber) {
				mainMenu.printExit();
				break;
			}
			
			// 국가 선택 메뉴
			if (selectMainMenu == 1) {
				CountryMenu countryMenu = new CountryMenu();
				
				CountryOutline country = null;
				while (true) {
					
					countryMenu.printTitle();
					countryMenu.printOptions();
					int selectCountryMenu = scanner.nextInt();
					
					// 뒤로가기
					if (selectCountryMenu == CountryMenu.BACK_NUMBER)
						break;
					
					if (0 < selectCountryMenu && selectCountryMenu < CountryMenu.OPTION_SIZE + 1) {
						CountryName countryName; // 열거체
						country = new Country(); // Country 클래스 객체 생성
						countryName = CountryName.values()[selectCountryMenu - 1];
						country.setCountryName(countryName);
						
						// 국가가 선택 완료 시 데이터 선택 메뉴
						DataMenu dataMenu = new DataMenu();
						
						while (true) {
							dataMenu.printTitle(country);
							dataMenu.printOptions();
							
							int selectDataMenu = scanner.nextInt();
							
							// 뒤로가기
							if (selectDataMenu == dataMenu.backNumber)
								break;
							
							// 한류 출력.
							if (0 < selectDataMenu && selectDataMenu < 6) {
								String[] koreaWaveData = country.showKoreaWave(countryName, selectDataMenu, fileMultiArray);
								for(int i = 0; i < koreaWaveData.length; i++)
									System.out.println(koreaWaveData[i]);
								
								while (true) {
									FileSaveMenu fileSaveMenu = new FileSaveMenu();
									fileSaveMenu.printTitle();
									fileSaveMenu.printOptions();
									
									int selectFileSaveMenu = scanner.nextInt();
									if (selectFileSaveMenu == 2) 
										break;
									
									// txt 파일로 저장
									if (selectFileSaveMenu == 1) {
										System.out.println("\ntxt 파일로 저장합니다.");
										try {
											PrintWriter pw = new PrintWriter(countryName.toString() + " " + koreaWaveData[0] + ".txt");
											for(int i = 0; i < koreaWaveData.length; i++)
												pw.println(koreaWaveData[i]);
											pw.close();
											System.out.println(countryName.toString() + koreaWaveData[0] + ".txt");
											System.out.printf("[!] %s.txt 파일로 저장을 성공하였습니다.\n\n", countryName.toString() + " " + koreaWaveData[0]);
											break;
										} catch (Exception e) {
											// TODO Auto-generated catch block
											e.printStackTrace();
											System.out.println("이거임?");
										}
									}
									else
										fileSaveMenu.printError();
								}
							}
							else
								dataMenu.printError();
						}
					}
					// 이외의 값 입력 시 에러 출력
					else
						countryMenu.printError();
				}
			}
			else
				mainMenu.printError();
		}
		
		scanner.close();
	}

}

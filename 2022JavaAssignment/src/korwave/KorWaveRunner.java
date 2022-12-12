/*
 객체지향 프로그래밍 평가과제
 학과: 컴퓨터공학과
 학번: 202101374
 이름: 박상윤
 
 과제 주제: 한류의 현황 알아보기
 */

package korwave;

import java.util.Scanner;
import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.ArrayList;

public class KorWaveRunner {

	public static void main(String[] args) {
		
		// 번호를 입력하면서 원하는 한류의 현황을 알아보는 프로그램입니다.
		
		
		// 외부 txt 파일 내용을 저장하는 2차원 배열
		// 국가의 개수와 데이터의 개수만큼 생성
		String[][] fileMultiArray = new String[CountryMenu.OPTION_SIZE - 1][DataMenu.OPTION_SIZE - 1];
		
		// 외부 txt 파일 불러오기 - 예외처리 사용
		try {
			BufferedReader br = null;
			br = new BufferedReader(new InputStreamReader(new FileInputStream("KoreanWaveData.txt"), "utf-8"));
			
			// 국가의 개수만큼 반복 (= 모든 국가의 정보 불러오기)
			for (int i = 0; i < CountryMenu.OPTION_SIZE- 1; i++) {
				// 데이터를 저장하는 배열 (데이터의 개수만큼 생성)
				String[] fileArray = new String[DataMenu.OPTION_SIZE - 1];
				
				// 내용을 임시 저장하는 temp ArrayList
				ArrayList<String> temp = new ArrayList<String>();
				
				int j = 0; // fileArray의 인덱스를 저장하는 변수
				while(true) {
					String line = br.readLine(); // 파일 읽기
					// 라인의 시작 문자가 @ 또는 # 또는 !라면
					if (line.charAt(0) == '@' || line.charAt(0) == '#' || line.charAt(0) == '!') {
						fileArray[j] = temp.toString(); // fileArray[j]에 현재까지 temp에 저장한 내용을 저장
						temp.clear(); // temp 초기화
						j += 1; // 다음 인덱스로 이동
						if (line.charAt(0) == '#') break; // 라인 시작 문자가 #이라면 while 탈출 → 다음 데이터 확인
						// 라인 시작 문자가 !이라면
						if (line.charAt(0) == '!') {
							br.close(); // 모든 파일 불러오기 완료
							break; // while 탈출
						}
					}
					// temp에 line 저장
					else temp.add(line);
				}
				// fileMultiArray[i]에 fileArray 저장
				fileMultiArray[i] = fileArray;
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		Scanner scanner = new Scanner(System.in);
		
		// 메인 메뉴 객체 생성
		MainMenu mainMenu = new MainMenu();
		
		// 프로그램 실행
		while (true) {
			// 메인 메뉴 출력
			mainMenu.printTitle();
			mainMenu.printOptions();
			
			// 메인 메뉴에서 번호를 입력받음
			int selectMainMenu = scanner.nextInt();
			
			// 프로그램 종료 번호를 입력하면
			if (selectMainMenu == mainMenu.exitNumber) {
				mainMenu.printExit(); // 프로그램 종료 문구 출력
				break; // 종료
			}
			
			// 국가 선택 번호(1)를 입력하면
			if (selectMainMenu == 1) {
				// 국가 선택 메뉴 객체 생성
				CountryMenu countryMenu = new CountryMenu();
				
				// 추상 클래스 CountryOutline의 객체 country
				CountryOutline country = null;
				while (true) {
					// 국가 선택 메뉴 출력
					countryMenu.printTitle();
					countryMenu.printOptions();
					
					// 국가 선택 메뉴에서 번호를 입력받음
					int selectCountryMenu = scanner.nextInt();
					
					// 뒤로가기 번호를 입력하면
					if (selectCountryMenu == countryMenu.backNumber)
						break; // 국가 선택 메뉴 벗어나기 (메인 메뉴로 복귀)
					
					// 유효한 (범위 내의) 국가의 번호를 입력하면
					if (0 < selectCountryMenu && selectCountryMenu < countryMenu.backNumber) {
						CountryName countryName; // 국가의 이름을 저장한 열거체 CountryName의 객체 countryName
						country = new Country(); // country 객체 타입 변환
						countryName = CountryName.values()[selectCountryMenu - 1]; // countryName의 값을 입력한 국가 번호에 맞게 설정
						country.setCountryName(countryName); // country 객체의 국가 이름 설정
						
						// 국가 선택 완료 시 데이터 선택 메뉴 객체 생성
						DataMenu dataMenu = new DataMenu();
						
						while (true) {
							// 데이터 선택 메뉴 출력
							dataMenu.printTitle(country);
							dataMenu.printOptions();
							
							// 데이터 선택 메뉴에서 번호를 입력받음
							int selectDataMenu = scanner.nextInt();
							
							// 뒤로가기 번호를 입력하면
							if (selectDataMenu == dataMenu.backNumber)
								break; // 데이터 선택 메뉴에서 벗어나기 (국가 선택 메뉴로 복귀)
							
							// 유효한 (범위내의) 데이터 번호를 입력하면
							if (0 < selectDataMenu && selectDataMenu < dataMenu.backNumber) {
								// 앞서 선택한 국가의 선택한 데이터 내용 불러오기
								System.out.println("--------------------");
								String[] koreaWaveData = country.showKoreaWave(countryName, selectDataMenu, fileMultiArray);
								for(int i = 0; i < koreaWaveData.length; i++)
									System.out.println(koreaWaveData[i]); // 내용 출력
								
								// 내용 출력후엔 해당 내용을 새로운 txt 파일로 저장할 것인지를 묻는 메뉴 (=파일 저장 메뉴)출력
								// 파일 저장 메뉴 객체 생성
								FileSaveMenu fileSaveMenu = new FileSaveMenu();
								while (true) {
									// 파일 저장 메뉴 출력
									fileSaveMenu.printTitle();
									fileSaveMenu.printOptions();
									
									// 파일 저장 메뉴에서 번호를 입력받음
									int selectFileSaveMenu = scanner.nextInt();
									
									// 뒤로가기 번호를 입력하면
									if (selectFileSaveMenu == fileSaveMenu.backNumber) 
										break; // 파일 저장 메뉴 벗어나기 (데이터 선택 메뉴로 복귀)
									
									// 파일로 저장 번호(1)를 입력하면
									if (selectFileSaveMenu == 1) {
										System.out.println("\ntxt 파일로 저장합니다.");
										try {
											// 파일 이름은 '국가이름_선택한데이터.txt'로 설정 (공백은 "_" 처리)
											PrintWriter pw = new PrintWriter(countryName.toString() + "_" + koreaWaveData[0].replace(" ", "_") + ".txt");
											// 파일로 저장
											for(int i = 0; i < koreaWaveData.length; i++)
												pw.println(koreaWaveData[i]);
											pw.close();
											System.out.printf("[!] %s.txt 파일로 저장을 성공하였습니다.\n\n", countryName.toString() + "_" + koreaWaveData[0].replace(" ", "_"));
											break;
										} catch (Exception e) {
											e.printStackTrace();
										}
									}
									// 파일 저장 메뉴에서 유효하지 않은 (범위를 벗어난) 번호를 입력하면 error 출력
									else
										fileSaveMenu.printError();
								}
							}
							// 한류 내용 출력 메뉴에서 유효하지 않은 (범위를 벗어난) 번호를 입력하면 error 출력
							else
								dataMenu.printError();
						}
					}
					// 국가 선택 메뉴에서 유효하지 않은 (범위를 벗어난) 번호를 입력하면 error 출력
					else
						countryMenu.printError();
				}
			}
			// 메인 메뉴에서 유효하지 않은 (범위를 벗어난) 번호를 입력하면 error 출력
			else
				mainMenu.printError();
		}
		
		scanner.close();
	}
}

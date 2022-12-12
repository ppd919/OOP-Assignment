package korwave;

// Country 클래스 - CountryOutline 상속
public class Country extends CountryOutline {
	@Override
	String[] showKoreaWave(CountryName countryName, int selectDataMenu, String[][] koreaWaveData) {
		// 국가 이름 열거체의 값, 데이터 선택 메뉴에서 선택한 번호, 맨 처음 불러왔던 외부 txt 파일을 이용
		// 외부 txt 파일에서 파라미터 값에 맞는 데이터 출력.
		return koreaWaveData[countryName.ordinal()][selectDataMenu-1].replace("[", "").replace("]", "").split(",");
	}
}

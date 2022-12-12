package korwave;

// CountryOutline 추상 클래스
public abstract class CountryOutline {
	private String countryName; // 국가 이름
	
	public void setCountryName(CountryName countryName) {
		this.countryName = countryName.toString(); // 국가 이름 설정
	}
	
	public String getCountryName() {
		return this.countryName;
	}
	
	// 한류 데이터를 반환하는 showKoreaWave
	abstract String[] showKoreaWave(CountryName countryName, int selectDataMenu, String[][] koreaWaveData);
}

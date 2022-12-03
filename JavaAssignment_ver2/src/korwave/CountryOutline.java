package korwave;

public abstract class CountryOutline {
	private String countryName;
	
	public void setCountryName(CountryName countryName) {
		this.countryName = countryName.toString();
	}
	
	public String getCountryName() {
		return this.countryName;
	}
	
	abstract String[] showKoreaWave(CountryName countryName, int selectDataMenu, String[][] koreaWaveData);
//	abstract void showPopularityFactors(String countryName, String field);
//	abstract void showFavoriteKoreanContents(String contryName, String field);
}

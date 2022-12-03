package korwave;

public class Country extends CountryOutline {
	@Override
	String[] showKoreaWave(CountryName countryName, int selectDataMenu, String[][] koreaWaveData) {
		return koreaWaveData[countryName.ordinal()][selectDataMenu-1].replace("[", "").replace("]", "").split(",");
	}
}

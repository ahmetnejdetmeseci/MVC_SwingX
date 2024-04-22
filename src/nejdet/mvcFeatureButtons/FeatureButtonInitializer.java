package nejdet.mvcFeatureButtons;

import nejdet.mvcFeatureButtons.arayuz.IFeatureButtonYaratilisDinleyici;

public class FeatureButtonInitializer {
	public static void initializeNewMenu(IFeatureButtonYaratilisDinleyici dinleyici) {
		FeatureButton featureButtonYoneticisi = new FeatureButton();
		dinleyici.featureButtonYaratildi(featureButtonYoneticisi);
	}
}

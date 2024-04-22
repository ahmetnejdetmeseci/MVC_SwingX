package nejdet.mvcMenu;


public class MenuInitializer {
	public static void initializeNewMenu(IMepRaysMenuYaratilisDinleyici dinleyici) {
		MapraysMenu menuYoneticisi = new MapraysMenu();
		dinleyici.menuYaratildi(menuYoneticisi);
	}
}

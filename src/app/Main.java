package app;

public class Main {
	
	public static void main(String[] args) {
		String assetsFolderRoute = "src/assets";
		new SourceManager(assetsFolderRoute);
		App appWindow = new App();
	}
}

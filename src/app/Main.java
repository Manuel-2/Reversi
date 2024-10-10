package app;

public class Main {
	
	public static void main(String[] args) {
		String assetsFolderRoute = "src/assets";
		// this is a singleton
		new SourceManager(assetsFolderRoute);
		new App();
	}
}

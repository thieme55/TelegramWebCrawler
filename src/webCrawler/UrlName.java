package webCrawler;

//data type for storing references to websites and their attributes
public class UrlName {
	public String name;
	public String url;
	public boolean moodle = false;
	
	//init
	public UrlName(String name, String url, boolean moodle) {
		this.name = name;
		this.url = url;
		this.moodle = moodle;
	}

}

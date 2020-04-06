package webCrawler;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;

import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
/**
 * Class stores and recalls links and other data for the ETHCrawler
 * @author Thiemo Zaugg
 *
 */
public class Storage {
	String location;
	
	public Storage(String location) {
		this.location = location;
	}
	
	/**
	 * Creates files to store data
	 * @throws IOException
	 */
	public void creatStorage() throws IOException {
		ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(location));
		out.close();
	}
	
	/**
	 * Methode for storing links
	 * @param links Elements
	 * @throws FileNotFoundException
	 * @throws IOException
	 */
	public void storeLinks(Elements links) throws FileNotFoundException, IOException {
		//Elements to ArrayList of Links
		ArrayList<String> linksString =  new ArrayList<String>();
		for (Element link: links) {
			linksString.add(link.attr("href"));
		}
		ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(location));
		out.writeObject(linksString);
		out.close();
	}
	
	/**
	 * Methode for returning links from storage
	 * @return Elements of links
	 * @throws ClassNotFoundException
	 * @throws IOException
	 */
	public ArrayList<String> getLinks() throws ClassNotFoundException, IOException {
		ObjectInputStream in = new ObjectInputStream(new FileInputStream(location));
		@SuppressWarnings("unchecked")
		ArrayList<String> links = (ArrayList<String>) in.readObject();
		in.close();
		return links;
	}
}

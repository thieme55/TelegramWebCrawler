import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;

/**
 * Searches for new links on website and sends them through a telegram bot to you.
 *
 * @author Thiemo Zaugg
 */
public class EthCrawler {
    public static void main(String[] args) {
        //add users to dobby chat bot
        ArrayList<String> chatIds = new ArrayList<>();
        // make array of all urls
        ArrayList<UrlName> urls = new ArrayList<>();
        // define urls here

        ///////////////////////////////////////////////////////////////////////////
        //Required:
        //setup for the telegram bot:
        String token = "";
        //-> add all channel names (@channel_name)
        chatIds.add("");
        //-> add url's
        final String myWebsiteUrl = "";
        urls.add(new UrlName("NameOfMyWebsite", myWebsiteUrl, false)); //last argument can be set if moodle login is required


        //Optional:
        //Telegram channel name (@channel_name) for error messages
        String errorChatId = "";
        //if moodle login is used:
        String userNameMoodle = "";
        String passwordMoodle = "";

        ///////////////////////////////////////////////////////////////////////////

        // init Telegram MessageBot (Dobby)
        DobbySpiderBot dobby = new DobbySpiderBot(chatIds, errorChatId, token);

        // iterate over all urls
        for (UrlName url : urls) {

            Document doc;
            // get html-Document
            try {
                if (url.moodle) {
                    //additionally log into moodle
                    doc = LogInBot.logOn(url.url, userNameMoodle, passwordMoodle);
                    if (doc == null) continue;
                } else {
                    doc = Jsoup.connect(url.url).get();
                }
            } catch (IOException e) {
                e.printStackTrace();
                System.out.println("couldn't connect to: " + url.name + " website");
                dobby.sendErrMsg("couldn't connect to: " + url.name + " website");
                continue;
            }
            analysePage(doc, url, dobby); //searches for new links and sends them to users
        }
        System.out.println("done! - all sites parsed");
        dobby.sendErrMsg("done! - all sites parsed");
    }

    private static void analysePage(Document doc, UrlName url, DobbySpiderBot dobby) {
        Element content = doc.body(); // select relevant Elements
        Elements links_new = content.select("a[href]"); // get all links
        Storage st = new Storage(url.name + ".txt"); // get Links form last run
        ArrayList<String> links_old;

        //search for new links (compares old List of links with new List of links)
        try {
            links_old = st.getLinks(); // load links from Storage
            for (Element link : links_new) {
                if (!(links_old.contains(link.attr("href")))) {
                    //send message to telegram users
                    dobby.sendMsg("Dobby found something! " + link.text() + " you'll find it here: " + link.attr("href"));
                }
            }
        } catch (FileNotFoundException e1) {
            e1.printStackTrace();
            //trying to create storage files
            try {
                st.createStorage();
            } catch (IOException e) {
                e.printStackTrace();
                System.out.println("couldn't create storage file");
            }
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("couldn't find Storage files!");
            dobby.sendErrMsg("couldn't find Storage files!");
        }

        //store links in Storage
        try {
            st.storeLinks(links_new);
        } catch (FileNotFoundException e1) {
            e1.printStackTrace();
            dobby.sendErrMsg("Storage: FileNotFoundException");
        } catch (IOException e1) {
            e1.printStackTrace();
            dobby.sendErrMsg("Storage: IOException");
        }

        System.out.println(url.name + "--> done");

    }
}

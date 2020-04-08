import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.concurrent.TimeUnit;

/**
 * Class badly implements a telegram bot
 *
 * @author Thiemo Zaugg
 */
public class DobbySpiderBot {
    //storing all the Telegram chatId's
    ArrayList<String> chatIds;
    String errorChatId;
    String token;

    public DobbySpiderBot(ArrayList<String> chatIds, String errorChatId, String token) {
        this.chatIds = chatIds;
        this.token = token;
        this.errorChatId = errorChatId;
    }

    public void sendMsg(String msg) {
        String urlString = "https://api.telegram.org/bot%s/sendMessage?chat_id=%s&text=%s";

        //iterate over all users
        for (String chatId : this.chatIds) {
            urlString = String.format(urlString, this.token, chatId, msg);

            try {
                URL url = new URL(urlString);
                url.openStream();
                TimeUnit.SECONDS.sleep(4); //delay because of telegram message limits
            } catch (IOException | InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public void sendErrMsg(String msg) {
        String urlString = "https://api.telegram.org/bot%s/sendMessage?chat_id=%s&text=%s";

        urlString = String.format(urlString, this.token, this.errorChatId, "!Error: " + msg);

        try {
            URL url = new URL(urlString);
            url.openStream();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

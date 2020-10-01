# TelegramWebCrawler
A web crawler using a Telegram bot as an interface.
Whenever new links are created on a website it sends a notification. 
It also supports ETHZ Moodle websites that require logging in.

## Dependencies:
- `jsoup 1.13`
- `htmlunit 2.37`

## Setup
1. You need a Telegram bot which can be created with the @BotFather Telegram bot.
    This **token** is needed in the [ETHCrawler.java] file.
2. Create a Telegram channel, add your personal bot, make the bot an admin, and allow it to send messages.
3. Add your Telegram channel name to the [ETHCrawler.java] file.
4. Add the URLs you wish to scrape to the [ETHCrawler.java] file.

### Moodle setup (optional)
5. If you want to use Moodle websites that require logging in, provide the credentials in the [ETHCrawler.java] file.

## Docker
It's possible to run the project using [Docker](https://www.docker.com/). 
There is no pre-built image available as of right now but you can still build it yourself.

Use the following command to build and run the project in a container:

```shell script
docker run --rm -it $(docker build -q .)
```

## Remarks
- The Telegram API does not allow sending lots of messages at the same time.
    This may restrict the use of multiple Telegram channels for the same bot (I couldn't get it to work with some delay) 
- Moodle login could easily break at any time due to relative and absolute XPaths used in the [LogInBot.java] file.

[ETHCrawler.java]: src/main/java/EthCrawler.java
[LogInBot.java]: src/main/java/LogInBot.java

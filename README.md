# TelegramWebCrawler
A Webcrawler using using a Telegram Bot as an interface. Whenever new links are created on a website it sends notifications via the bot. (It also contains a type of auto login for ethz moodle websites.)

Libraries used:
- jsoup 1.13.1
- htmlunit 2.37.0 (only needed for moodle "login bot")

## Setup
1. You'll  need a Telegram bot which can be created with the @BotFather Telegram bot. This gives the **token** needed in the *ETHCrawler.java* file.
2. Create a Telegram channel and add the your personal bot. Then make the bot an admin and allow it to send messages.
3. Add your Telegram channel name to the *ETHCrawler.java* file.
4. Add all your urls to the *ETHCrawler.java* file.

## Moodel setup (optional)
5. If you want to use moodle websites, a login is needed to acess website. Provide it in the *ETHCrawler.java*.

### Remarks
- The Telegram api does not allow sending lots of messages at the same time. This may restrict the use of multible Telegram channels for the same Bot (I couldn't get it to work with some delay) 
- moodle login can easily break at any time due to relative and absolute XPaths used in the *LogInBot.java* file.

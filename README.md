# TelegramWebCrawler
A Webcrawler which notifies one over a telegram bot if new links were created on a website. (with some sort of auto login for ethz moodle websites)

Libraris used:
- jsoup 1.13.1
- htmlunit 2.37.0 (only needed for moodle "login bot")

## Setup
1. You'll  need a Telegram bot which can be created with the @BotFather Telegram bot. This gives the **token** needed int the *ETHCrawler.java* file.
2. Creat a Telegram channel and add the your personal bot. Also make the bot an admin and allow him to send messages.
3. Add your Telegram channel name to the *ETHCrawler.java* file.
4. Add all your urls to the *ETHCrawler.java* file.

## Moodel setup (optional)
5. If you want to use moodle websites, a login is needed to get the website. Provide it in the *ETHCrawler.java*.

### Remarks
- The Telegram api does not allow to send lots of meaages at the same time. This may restrict the use of multible Telegram channel for the same Bot (I couldn't get it to work with some delay) 
- moodle login can easily break at any time due to relativ and absolut XPaths used in the *LogInBot.java* file.

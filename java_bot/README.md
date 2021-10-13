# Java bot

This example uses the [twitter4j](https://twitter4j.org/en/index.html) library for Java. You can find the JavaDoc [here](https://twitter4j.org/javadoc/index.html). Be warned, some of the example code is outdated - you might have to do some Googling.

To install, you can grab the Maven snippet from the homepage linked above or [download the source](https://twitter4j.org/archive/twitter4j-4.0.7.zip) which includes `.jar` files you can add to your classpath (depends on your specific IDE).

## Connecting to the Twitter API
This code assumes your API keys are stored in the following [environment variables](https://www.twilio.com/blog/2017/01/how-to-set-environment-variables.html):
- `TWITTERBOT_CONSUMER_KEY`
- `TWITTERBOT_CONSUMER_SECRET`
- `TWITTERBOT_ACCESS_TOKEN`
- `TWITTERBOT_ACCESS_SECRET`

You can also modify this section to read your keys from a file, database, or some other source - just don't push them to your public repo!

## Replying to Tweets
After logging in, the script creates an ArrayList:
```Java
ArrayList<String> triggerWords = new ArrayList<String>();
triggerWords.add("@" + bot.getScreenName());
```
Any strings you put into this ArrayList will be tracked by `twitter4j`, which will trigger a function we're going to implement whenever a Tweet is posted containing any of those words. The bot's handle is included by default to respond to mentions.

The function that gets fired is `StatusListener.onStatus()`. We create a `TwitterStream` to stream new Tweets, then attach a new StatusListener object where we implement `onStatus`.

It takes a single argument, `status`, which contains info about the Tweet. Important to us is the following:
- `status.getText()` - the message text
- `status.getUser().getScreenName()` - the person's @username

The new function analyzes the Tweet text (in this case, checking if it contains "hi" or "hello") and creates a variable `reply` containing the reply, then sends the Tweet with `twitter.updateStatus()`.
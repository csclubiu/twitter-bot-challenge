# Python bot

This example uses the [tweepy](https://pypi.org/project/tweepy/) library for Python, which you can install by running `pip install -r requirements.txt`. You can find the full documentation [here](https://docs.tweepy.org/en/stable/).

## Connecting to the Twitter API
This script assumes your API keys are stored in the following [environment variables](https://www.twilio.com/blog/2017/01/how-to-set-environment-variables.html):
- `TWITTERBOT_CONSUMER_KEY`
- `TWITTERBOT_CONSUMER_SECRET`
- `TWITTERBOT_ACCESS_TOKEN`
- `TWITTERBOT_ACCESS_SECRET`

You can also modify this section to read your keys from a file, database, or some other source - just don't push them to your public repo!

## Replying to Tweets
After logging in, the script creates an array:
```Python
trigger_words = [
    '@' + bot.screen_name
]
```
Any strings you put into this array will be tracked by `tweepy`, which will trigger a function we're going to implement whenever a Tweet is posted containing any of those words. The bot's handle is included by default to respond to mentions.

The function that gets fired is `Stream.on_status()`, which by default just prints the Tweet to the console. Here we create a new class `MyStream` which inherits from `Stream` where we'll override that function.

It takes a single argument, `status`, which contains info about the Tweet. Important to us is the following:
- `status.text` - the message text
- `status.author.screen_name` - the person's @username

The new function analyzes the Tweet text (in this case, checking if it contains "hi" or "hello") and creates a variable `reply` containing the reply, then sends the Tweet with `update_status()`.
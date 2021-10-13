# Python boilerplate code
# See https://github.com/csclubiu/twitter-bot-challenge/blob/master/python_bot/README.md
# -----------------------

import os
import tweepy
from datetime import datetime as dt

# get auth info
CONSUMER_KEY        = os.environ['TWITTERBOT_CONSUMER_KEY']
CONSUMER_SECRET     = os.environ['TWITTERBOT_CONSUMER_SECRET']
ACCESS_TOKEN        = os.environ['TWITTERBOT_ACCESS_TOKEN']
ACCESS_TOKEN_SECRET = os.environ['TWITTERBOT_ACCESS_SECRET']

# log into the API
auth = tweepy.OAuthHandler(CONSUMER_KEY, CONSUMER_SECRET)
auth.set_access_token(ACCESS_TOKEN, ACCESS_TOKEN_SECRET)
api = tweepy.API(auth)
bot = api.verify_credentials()
print('[{}] Logged in as @{}\n-----------'.format(
    dt.now().strftime('%Y-%-m-%-d %-I:%M:%S %p'), bot.screen_name
))

# array of words that will trigger the on_status function
trigger_words = [
    '@' + bot.screen_name # respond to @mentions
]

# override the default Stream class to implement on_status
class MyStream(tweepy.Stream):

    def on_status(self, status):

        message = status.text
        author_handle = status.author.screen_name

        # log the incoming tweet
        print('[{}] Received: "{}" from @{}'.format(
            dt.now().strftime('%Y-%-m-%-d %-I:%M:%S %p'), message, author_handle
        ))

        # check if 'hi' or 'hello' is in the tweet (case insensitive):
        if 'hi' in message.lower() or 'hello' in message.lower():

            # respond with 'Hello!'
            reply = 'Hello!'

            api.update_status(
                # this needs to include the person's username at the beginning
                status='@{} {}'.format(author_handle, reply),
                in_reply_to_status_id=status.id
            )

            print('[{}] Responded to @{}'.format(
                dt.now().strftime('%Y-%-m-%-d %-I:%M:%S %p'), author_handle
            ))

# create a stream to receive tweets
try:
    stream = MyStream(CONSUMER_KEY, CONSUMER_SECRET, ACCESS_TOKEN, ACCESS_TOKEN_SECRET)
    stream.filter(track=trigger_words)
except KeyboardInterrupt:
    print('\nGoodbye')
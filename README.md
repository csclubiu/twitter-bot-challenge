# Twitter Bot Challenge

## Accessing the Twitter API
To access the Twitter API, you'll need to [apply for a developer account](https://developer.twitter.com/en/apply-for-access).

Filling out the application is straightforward - you'll be asked some details about yourself and how you're planning to use the API. Twitter has gotten stricter with access recently to stop malicious use of the API, so you'll want to be as detailed and honest as possible to make sure you get approved. This may be a good time to catch up on Twitter's developer policies, particularly [restricted use cases](https://developer.twitter.com/en/developer-terms/more-on-restricted-use-cases) and the [automation rules](https://help.twitter.com/en/rules-and-policies/twitter-automation).

Once you've been approved, [create a new standalone app](https://developer.twitter.com/en/portal/apps/new). Give it a name, then grab your keys and tokens on the next page (these are your "consumer keys"). Save or write them down somewhere safe - anyone who has those keys could take control of your account.

Next, you'll want to edit your app's settings. Change the app permissions to "Read and Write" so you can post Tweets.

Now to go the "keys and tokens" tab for your app, and under "Authentication Tokens" find "Access Token and Secret" and click "Generate." Save these in a secure place like your other keys.

This will allow you to run your bot under your developer account's handle. If you want to run the bot under another handle, you can check out the [3-legged OAuth flow](https://developer.twitter.com/en/docs/authentication/oauth-1-0a/obtaining-user-access-tokens), but that won't be necessary for this competition.

## Writing a bot
This repo includes two templates you can use to implement your bot: one in [Python](./python_bot) and one in [Java](./java_bot). Both implement an example bot that responds "Hello!" when you mention it and say "hi" or "hello." You can use this code as a jumping off point for your bot. Both directories also include a README that walks through the code and includes helpful links.

If it's taking a while to get approved for API access, you can implement your bot using your preferred language's built-in input and output methods, then simply drop your code into either of the templates once you gain access.
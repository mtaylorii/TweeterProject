When you guys meet today if you could please take a look at the search code and maybe either figure out or explain
to me why it won't search through the accounts, that would be fantastic because I can't seem to understand it.
I've tried for loops, grabbing the name from the database, using contains/equals, and still nothing. Let me know 
what you guys go over today and if you figure anything new out.
-Colton

#What still needs to be done:
- Search via #
- Search for users/send message to user with @
- post a twit
- view twits (all, public only, subscribers only)
- Subscribing
- Viewing different sets of posts (all, public only, subscribed-to only)
- Deleting an account*
- Add functionality for a database file of twit ID's, Twit messages, posters of the Twit, and photo file path (Cole)
- Display photos

Feel free to add/remove anything as you see fit.

New Main:
- create AccountRepository
- Load database
- Options:
  - Not signed in:
    - Sign in
    - View public messages
    - Exit
    - Register
    - View profiles (maybe search for the profiles too, see step below)
    - Search ('@')
  - Signed in:
    - Sign out
    - View all messages
    - View only subscribers
    - Post twit/ send message
    - Search ('@' or '#')
    - Edit profile (bio, pic, or delete account)
    - Exit (sign out first)
    - View profiles
  - Save database (write users and twit files after every action (?))

- Max: view (public, subscriber, all) twits
- Cole: view profiles, make twit id second in database list, change who can view to boolean instead of string, work on new main
- Colton: search
- Parker: db

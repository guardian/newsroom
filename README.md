# Newsroom

*An on-device log surfacing library from The Guardian*

>A newsroom is the central place where journalists—reporters, editors, and producers, along with other staffs—work to gather news to be published in a newspaper and/or an online newspaper or magazine, or broadcast on radio, television, or cable.

https://en.wikipedia.org/wiki/Newsroom

## Making a release

1. Open `newroom-lib/bintray.gradle`.
2. Increase all the version numbers using find and replace.
3. Make a commit and tag it with `git tag -a v<version number> -m "<message>"`.
4. Run `./gradlew clean install && ./gradlew bintrayUpload`.

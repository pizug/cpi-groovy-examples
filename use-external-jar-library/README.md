# Use External JAR Library with Groovy IDE

In Cloud Integration Flow editor, you can upload JAR files and they will be available for your Groovy scripts.

To use JAR files with Groovy IDE Desktop edition, you can add special comments at the start of the script. Since these are just comment lines, it doesn't affect your Flows.

```
//groovyide.com:jar:file:${Location of Jar file}
```
Then, you can import Java classes as usual.

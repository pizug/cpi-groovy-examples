# Reading Credentials

You need to import [SecureStoreService](https://help.sap.com/doc/471310fc71c94c2d913884e2ff1b4039/Cloud/en-US/com/sap/it/api/securestore/SecureStoreService.html) at the start of the script.

Then you can call `getUserCredential` with the credential name, which can be different from the username. Also beware that you need to convert returned password to string.

### Externalize
You probably want to externalize credential name to be able to change it without editing the flow.
- Add a Content Modifier step before
- Add a property & externalize it using `{{credential_name}}`
- Get credential name in Groovy script using property name

### Exception Handling
You can check if `secureStoreService` or `userCredential` is `null`. But if you don't have any solution in the script, you may want to leave exception handling to flow itself.

    Groovy IDE notice
    You can't use SecureStoreService with GroovyIDE at the moment.

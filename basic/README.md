# Basic Script Example for Beginners

The main entry point is function `processData` by default. You can change it in the UI.

This function gets Message object defined in `com.sap.gateway.ip.core.customdev.util.Message` and returns it back.

Message object contains:

- single body
- multiple headers
- multiple properties

It also supports SOAP headers & attachments but for clarity, we will look into that later.

### Changing body

In this basic example we don't modify XML using XML libraries. We just treat it as a string.

`message.getBody` function can return different Java types. There is a [related blog post in SAP Community](https://blogs.sap.com/2020/07/19/available-types-for-the-message-body-in-cpi-groovy-script/).

### Changing headers & properties

They provide similar functions. You can get a Java Map object by calling `message.getHeaders()` and `message.getProperties()`.

You can use functions on the message object to set new headers, or modify existing ones.

- For headers: `message.setHeader( key , value )`
- For properties: `message.setProperty( key , value )`

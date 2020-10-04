# Getting HTTP Error Body

If you are using SOAP adapter you have to change adapter type to HTTP.

Currently, the only option is using an HTTP receiver adapter. In order to convert SOAP Receiver to HTTP Receiver:

* Change the receiver adapter type. Enter the connection details.
* Wrap your request message body in SOAP Envelope in a Content Modifier before calling the adapter.
* (optional) If your service requires, you may need to add the "SOAPAction" HTTP header accordingly.
* Add an exception subprocess to the flow and use the below Groovy script from the [documentation](https://help.sap.com/viewer/368c481cd6954bdfa5d0435479fd4eaf/Cloud/en-US/a443efe1d5d2403fb95ee9def1a672d4.html)


    Groovy IDE notice
    You can't test this example with GroovyIDE at the moment.

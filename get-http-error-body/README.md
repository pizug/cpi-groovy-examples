# Getting HTTP Error Body

This example works if you are using HTTP receiver adapter.

If you are using SOAP adapter you can change adapter type to HTTP, or see the other example "Getting SOAP Error Body"

If you want to convert SOAP Receiver to HTTP Receiver:

* Change the receiver adapter type. Enter the connection details.
* Wrap your request message body in SOAP Envelope in a Content Modifier before calling the adapter.
* (optional) If your service requires, you may need to add the "SOAPAction" HTTP header accordingly.
* Add an exception subprocess to the flow and use the below Groovy script from the [documentation](https://help.sap.com/viewer/368c481cd6954bdfa5d0435479fd4eaf/Cloud/en-US/a443efe1d5d2403fb95ee9def1a672d4.html)


    Groovy IDE notice
    You can't test this example with GroovyIDE at the moment.

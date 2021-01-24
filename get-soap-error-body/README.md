# Getting SOAP Error Body

You are using SOAP adapter and when service returns SOAP Fault, you want to catch, use, or log the exception payload.

This method explained in a [blog post in SAP Community](https://blogs.sap.com/2021/01/06/fetch-soap-fault-error-response-from-receiver-in-sap-cloud-platform-integration/).

The exception payload will be included in message property `CamelExceptionCaught` and has the type `org.apache.cxf.binding.soap.SoapFault`.

To see all available methods in this class [see the Javadoc](https://cxf.apache.org/javadoc/latest/org/apache/cxf/interceptor/Fault.html)


    Groovy IDE notice
    You can't test this example with GroovyIDE at the moment.

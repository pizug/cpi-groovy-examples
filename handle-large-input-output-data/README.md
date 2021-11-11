# Handle Large Input/Output Data with Groovy IDE

This solution works with the Groovy IDE desktop edition.

You can use a wrapper `processData_wrapper` method to avoid UI performance issues. On the Groovy IDE, you can use this wrapper method, and on the Cloud Integration tenant you can just use `processData` method.

It works for both input and output data. Remember to remove it in the wrapper. Also, if you are working with properties and don't need the data after this script step, you can remove it in the `processData` method. You will save some memory for the rest of the flow.

You can use the same solution for dynamic data. For example, you can call an HTTP service and use the response as an input body or property.

We are using `input_data_folder` property to avoid including this developer-machine-specific detail to integration flow in the tenant.
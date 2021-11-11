import com.sap.gateway.ip.core.customdev.util.Message;


def Message processData_wrapper(Message message) {
    String folder = message.getProperties().get("input_data_folder");

    String fileContents = new File(folder + 'toobig.input.property_file.orders.json').getText('UTF-8')
    message.setProperty("orders", fileContents);

    String fileContents2 = new File(folder + 'toobig.input.property_file.deliveries.json').getText('UTF-8')
    message.setProperty("deliveries", fileContents2);
    
    processData(message);

    // You can also write large body or property to file 
    def outputFile = new File(folder + 'toobig.output.property_file.orders.json')
    outputFile.write(message.getProperties().get("orders"))

    // Delete large output to avoid UI issues
    message.setProperty("orders", "removed");
    message.setProperty("deliveries", "removed");

    return message;
}


def Message processData(Message message) {
    // Develop your usual method.
    
    return message;
}


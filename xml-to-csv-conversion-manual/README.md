# Convert XML to CSV (Manual)

CPI provides XML to CSV Converter which is [documented here](https://help.sap.com/viewer/368c481cd6954bdfa5d0435479fd4eaf/Cloud/en-US/902522209e7546f89c3c52ad018603d1.html).

If you need to process XML, pick fields, do some further processing in a single step you can use this script. It doesn't escape "`,`" be careful if your input can contain comma, and in general this is an error-prone way to generate CSV.

You should also check Convert XML to CSV (Apache Commons CSV)

This example also contains a class with main method. It is an idea to enable running the script on your computer with regular IDEs.

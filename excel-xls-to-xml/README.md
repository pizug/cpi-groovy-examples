# Excel XLS to XML Conversion

Excel XLS is a binary format as opposed to XLSX which has an XML format inside a zip archive.

We are using [Apache POI library](https://poi.apache.org/download.html). Latest version (We have used 4.1.2). Download `Binary Distribution` archive. We only need two files from this archive:
- `poi-4.1.2.jar`
- `lib/commons-math3-3.6.1.jar`

Upload these Jar files to Flow as an Archive resource.

We have used base64, but Excel .xls file will usually come as binary from FTP/SFTP source. So you can comment lines with `Base64 Body` and use `Binary Body`.

    Groovy IDE notice
    You can't test external Jar archives with GroovyIDE at the moment.

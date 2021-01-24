# Getting a specific file from SFTP

This is not an endorsed method. They seem to consider adding support for this use case in standard SFTP Adapter.

Setting `StrictHostKeyChecking` to `no` means someone controlling the domain/ip address can change the server without you noticing. That is the reason `known_hosts` file exists.

You should download `jsch.jar` from http://www.jcraft.com/jsch/ and add it to Flow using: Flow->Resources->Add->Archive

    Groovy IDE notice
    You can't test external Jar archives with GroovyIDE at the moment.
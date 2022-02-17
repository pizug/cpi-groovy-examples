# Filename MPL Custom Header Property

This script adds `CamelFileName` header as custom header property `filename`. Then you can see this extra metadata in MPL logs, and search the logs with filename.

It is useful for SFTP->Cloud Integration EDI scenarios.

## Search with more than one identifier

Normally you can only add single identifier to search with `SAP_ApplicationID`. This feature enables storing several identifiers at the same time like IDoc number, PO number, etc. and searching them.

Since 2021 January, this field is included in the standard monitoring fields, so it should be indexed and fine to use.

SAP Community Blog for explaining the feature https://blogs.sap.com/2020/09/13/sap-cpi-a-guide-to-mpl-search/

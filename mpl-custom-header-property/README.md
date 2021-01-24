# MPL Custom Header Property

You can add additional metadata to your MPL log with this script. It will show up in `Logs` > `Open Text View`

## Search With More Than One Identifiers
Normally you can only add single identifier to search with `SAP_ApplicationID`. This feature enables storing several identifiers at the same time like IDoc number, PO number, etc. and searching them.

It is not yet verified if this field is **indexed or not**. So be careful before implementing this for heavy usage. It should be fine for occasional searches.

SAP Community Blog for explaining the feature https://blogs.sap.com/2020/09/13/sap-cpi-a-guide-to-mpl-search/

You can use workspace API for search or you can install [SuperEasy browser extension for CPI](https://chrome.google.com/webstore/detail/mdpgroup-supereasy-extens/nbeijdcbojmlpnhdikiebpdkikkmkljh).


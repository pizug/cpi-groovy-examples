# EDI Segment Counter

EDIFACT messages require total segment count in the segment `UNT`. X12 messages require the same information in segment `SE` data element `96`.

In SAP Process Integration/Orchestration(PI/PO) this requirement was handled by mapping the constant `$B2B_SEG_COUNTER` to the relevant field. SAP Cloud Integration doesn't have this feature yet, but we can create a Groovy script that replicates the same behavior.

- You can put this script just before `XML to EDI converter` step. It is flexible and it can be used when the message body has the XML root element starting with `M_`, or the root element is `Interchange`.
- You can map `$B2B_SEG_COUNTER` to any field. You can use the constant function in Integration Advisor.

Note that `groovy.util.Node` returns `String` in Groovy IDE and a `QName` object in Cloud Integration. This behavior is flexible in the Groovy spec. `getLocalName` function handles this difference.

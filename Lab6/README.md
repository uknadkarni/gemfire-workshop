# Lab 6

## Continuous Querying
GemFire uses continuous querying to enable event-driven architectures. GemFire ties events and data
together so that when an event is processed, the data required to process the event is available without
additional queries to a disk-based database. Clients can subscribe to change notifications so that they can
execute tasks when a specific piece of data changes. 

Here's a typical use case: A client application displays active stock trades to a screen. The client creates a
CQ on the server's trading data set with a WHERE clause specifying "status='active'". The client requests
an initial result set for the CQ and initializes its screen display with those results. From then on, the client
updates its screen display with the CQ events as they arrive from the server. An entry whose status goes
from 'active' to 'inactive' on the server is reported to the client as a query destroy event for the entry, and
the client application removes the entry from the active trades display. Newly 'active' entries are reported
as additions to the result set and are added to the display.

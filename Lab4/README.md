# Lab 4

## Events Features
* Content-based events
* Asynchronous event notifications with conflation
* Synchronous event notifications for low latency
* High availability through redundant messaging queues
* Event ordering and once and only-once delivery
* Distributed event notifications
* Durable subscriptions
* Continuous querying

## Types of Events
* Cache Events provide detail-level notification for changes to your data.
* Administrative events are used by administrative applications without caches. 

When multiple listeners are installed, as can be done with cache listeners, the listeners are invoked sequentially in the order they were added to the region or cache.

Depending on the type of event handler being called, the event handler can receive the events in-order or out-of-order in which they are applied on a Region

An `EntryEvent` contains both the old value and the new value of the entry, which helps to indicate the value that was replaced by the cache operation on a particular key.



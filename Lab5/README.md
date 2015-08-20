# Lab 5

## Querying
GemFire provides a SQL-like querying language called OQL that allows you to access data stored in GemFire regions.
Since GemFire regions are key-value stores where values can range from simple byte arrays to complex 
nested objects, GemFire uses a query syntax based on OQL (Object Query Language) to query region
data. OQL is very similar to SQL, but OQL allows you to query complex objects, object attributes, and methods.
```
// Identify your query string.
String queryString = "SELECT * FROM /exampleRegion";
// Get QueryService from Cache.
QueryService queryService = cache.getQueryService();
// Create the Query Object.
Query query = queryService.newQuery(queryString);
// Execute Query locally. Returns results set.
SelectResults results = (SelectResults)query.execute();
// Find the Size of the ResultSet.
int size = results.size();
// Iterate through your ResultSet.
Portfolio p = (Portfolio)results.iterator().next(); /* Region containing Portfolio object */
```




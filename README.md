# redis-watch
Implements an example of transaction on Redis, using WATCH, MULTI and EXEC.
POC to validate concurrency scenery in a Inventory managment(stock).

Using Redis Template Transactions.

# How test

- Run docker compose.

- Use and GET in the URL /api/transaction/{sku}/{size}?sleep=true

During the sleep time, which is 30 seconds, update the redis key in the console like example:

hset sku:123 qty 15

In the first time the attempt should not update the value, look at the application log.

# Docker compose informations

##Redis
**port**: 6379
**password**: segredo
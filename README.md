IBM MQ Tool
===========

Write message
```bash
$ curl -v -w "\n" -d '{"host":"localhost", "port":1414, "channel":"DEV.ADMIN.SVRCONN", "qm":"QM1", "userId":"admin", "password":"passw0rd", "queue":"DEV.QUEUE.1", "body":"test body"}' -H "Content-Type: application/json" -X POST http://localhost:7070/message
```

List messages
```bash
$ curl -v -w "\n" 'localhost:7070/message?size=1&host=localhost&port=1414&channel=DEV.ADMIN.SVRCONN&qm=QM1&userId=admin&password=passw0rd&queue=DEV.QUEUE.1'
```

Get stats
```bash
$ curl -v -w "\n" 'localhost:7070/message/stats?host=localhost&port=1414&channel=DEV.ADMIN.SVRCONN&qm=QM1&userId=admin&password=passw0rd&queue=DEV.QUEUE.1'
```

SUMMARY
-------

This is a very simple web application for performing basic operations on a queue.

Current Operations:
- Put a message
- List messages
- Get a message

TODO Operations:
- Delete a message

TODO Enhancements:
- Retreive message properties and headers
- Set property and header values when putting a message on a queue

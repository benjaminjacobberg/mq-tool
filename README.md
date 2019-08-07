IBM MQ Tool
===========

```bash
$ curl -v -w "\n" 'localhost:7070/message?size=1&host=localhost&port=1414&channel=DEV.ADMIN.SVRCONN&qm=QM1&userId=admin&password=passw0rd&queue=DEV.QUEUE.1'
```

```bash
$ curl -v -w "\n" -d '{"host":"localhost", "port":1414, "channel":"DEV.ADMIN.SVRCONN", "qm":"QM1", "userId":"admin", "password":"passw0rd", "queue":"DEV.QUEUE.1", "body":"test body"}' -H "Content-Type: application/json" -X POST http://localhost:7070/message
```

SUMMARY
-------

A simple web application tool for IBM MQ that allows you to easily put a message on a queue.
(I made this during my lunch break, so please don't be too harsh on my code.)

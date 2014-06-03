Fibonacci auto-scaler
=====================

This is a very simple demo that uses fibonacci numbers to consume most of an app instance CPU.

The idea is to send several number requests to a rabbitMQ queue, and each app will compute a request fibonacci's number. This will lead to a spike on CPU usage, triggering the auto-scaller.

#### Requirements to run on cloudfoundry:

1. A rabbitmq service bound to the app 
2. A auto-scaller service (gold plan works better)

#### Running

Deploy the application and then post to its only endpoint:

~~~
curl -H "Content-type: application/json" -H "Accept: application/json" -XPOST http://fibonacci.<APP_DOMAIN>/session/create -d '{"min" : 45,"max" : 48,"count" : 30}'

~~~

min and max represent the range of values of sequences to be sent
count represents how many numbers

Once you post, just relax and wait for the auto-scaller to start scalling the app.



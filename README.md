#kafka-tweet-producer
This is an activator project. It describes how to pull tweets from twitter and push into Kafka.

###Pre-Requisites for this project
####Kafka Setup
i) [Download](http://kafka.apache.org/downloads.html) the Kafka-0.10.0.1 or latest version  and unzip it.

ii) Run the following command.
    Start zookeeper & Kafka:
    
         $ bin/zookeeper-server-start.sh config/zookeeper.properties 
         $ bin/kafka-server-start.sh config/server.properties
         
-----------------------------------------------------------------------
###Getting Started:
-----------------------------------------------------------------------

 Clone and run the code:

        $ git@github.com:knoldus/kafka-tweet-producer.git
        $ cd kafka-tweet-producer
        $ env "twitter4j.oauth.consumerKey=*****************" bash
        $ env "twitter4j.oauth.consumerSecret=**************" bash
        $ env "twitter4j.oauth.accessToken=*****************" bash
        $ env "twitter4j.oauth.accessTokenSecret=***********" bash
        $ bin/activator run

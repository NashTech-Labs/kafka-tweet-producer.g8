A [Giter8][g8] template to describes how to pull tweets from twitter and push into Kafka.

About
-----

### Pre-Requisites for this project
#### Kafka Setup
i) [Download](http://kafka.apache.org/downloads.html) the Kafka-0.10.0.1 or latest version  and unzip it.

ii) Run the following command.
    Start zookeeper & Kafka:
    
         $ bin/zookeeper-server-start.sh config/zookeeper.properties 
         $ bin/kafka-server-start.sh config/server.properties
         
-----------------------------------------------------------------------
### Getting Started:
-----------------------------------------------------------------------

 Clone and run the code:

        $ git@github.com:knoldus/kafka-tweet-producer.git
        $ cd kafka-tweet-producer
        $ env "twitter4j.oauth.consumerKey=*****************" bash
        $ env "twitter4j.oauth.consumerSecret=**************" bash
        $ env "twitter4j.oauth.accessToken=*****************" bash
        $ env "twitter4j.oauth.accessTokenSecret=***********" bash
        $ bin/activator run

Template license
----------------
Written in ​ 2017​ by ​ [Knoldus Software LLP](http://knoldus.com)

To the extent possible under law, the author(s) have dedicated all copyright and
related
and neighboring rights to this template to the public domain worldwide.
This template is distributed without any warranty. See
<http://creativecommons.org/publicdomain/zero/1.0/>.
[g8]: http://www.foundweekends.org/giter8/

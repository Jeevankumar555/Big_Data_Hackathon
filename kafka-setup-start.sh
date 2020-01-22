WORKING_DIR= #<< your local dir on Mac/Linus where kafka folder is extracted to >>

nohup $WORKING_DIR/bin/zookeeper-server-start.sh $WORKING_DIR/config/zookeeper.properties > /dev/null 2>&1 &

sleep 2


# edit below server.properties file and add "delete.topic.enable=true"
nohup $WORKING_DIR/bin/kafka-server-start.sh $WORKING_DIR/config/server.properties > /dev/null 2>&1 &

sleep 2

$WORKING_DIR/bin/kafka-topics.sh --create --bootstrap-server localhost:9092 --replication-factor 1 --partitions 1 --topic favourite-locations

$WORKING_DIR/bin/kafka-topics.sh --create --bootstrap-server localhost:9092 --replication-factor 1 --partitions 1 --topic trending-topics


echo "started zookeper , kafka server & created topics"
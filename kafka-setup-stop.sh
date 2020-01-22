WORKING_DIR= #<< your local dir on Mac/Linus where kafka folder is extracted to >>

nohup $WORKING_DIR/bin/kafka-topics.sh --zookeeper localhost:2181 --delete --topic favourite-locations > /dev/null 2>&1 &

sleep 2

nohup $WORKING_DIR/bin/kafka-topics.sh --zookeeper localhost:2181 --delete --topic trending-topics > /dev/null 2>&1 &

sleep 2

nohup $WORKING_DIR/bin/zookeeper-server-stop.sh $WORKING_DIR/config/zookeeper.properties > /dev/null 2>&1 &

sleep 2

nohup $WORKING_DIR/bin/kafka-server-stop.sh $WORKING_DIR/config/server.properties > /dev/null 2>&1 &

sleep 2



echo "deleted topics , stopped zookeeper & kafka server"
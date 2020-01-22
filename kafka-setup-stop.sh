WORKING_DIR= #<<you local dir on Mac/Linus where kafka is present extracted>>

nohup $WORKING_DIR/bin/zookeeper-server-stop.sh $WORKING_DIR/config/zookeeper.properties > /dev/null 2>&1 &

sleep 2

nohup $WORKING_DIR/bin/kafka-server-stop.sh $WORKING_DIR/config/server.properties > /dev/null 2>&1 &

echo "stopped servers"
nohup java -Djavax.net.ssl.trustStore="/usr/local/java/jdk-9/lib/security/cacerts" -Djavax.net.ssl.trustStorePassword="changeit" --add-modules=java.xml.bind -jar mall-shop-service.war >> star-zone.log 2>&1 &


nohup /usr/local/java/jdk-9/bin/java -Djavax.net.ssl.trustStore="/usr/local/java/jdk-9/lib/security/cacerts" -Djavax.net.ssl.trustStorePassword="changeit" --add-modules=java.xml.bind -jar star-zone-back.war >> back.log 2>&1 &


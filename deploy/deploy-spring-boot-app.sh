#!/usr/bin/env sh

echo "!------ Installing software ------!"
apt update
apt install -y mysql-server openjdk-11-jdk maven nginx

echo "!------ Configuring MySQL user ------!"
mysql -u root -proot <<EOF
CREATE USER 'my_user'@'localhost' IDENTIFIED BY 'password';
GRANT ALL PRIVILEGES ON * . * TO 'my_user'@'localhost';
FLUSH PRIVILEGES;
EOF

echo "!------ Building Spring Boot application ------!"
cd ..

mvn clean install

mkdir -p /opt/webapp || exit
cp lesson-04-spring-boot/target/lesson-04-spring-boot-0.0.1-SNAPSHOT.jar /opt/webapp/app.jar

useradd spring_boot
chown spring_boot:spring_boot /opt/webapp/app.jar
chmod 500 /opt/webapp/app.jar

echo "!------ Creating a systemctl daemon descriptor ------!"
cd deploy || exit

cp spring-boot-app.service /etc/systemd/system/spring-boot-app.service

echo "!------ Running the new daemon ------!"
systemctl daemon-reload
systemctl start spring-boot-app

echo "!------ Configuring NGINX ------!"
cp application.nginx.conf /etc/nginx/sites-available/default
systemctl restart nginx

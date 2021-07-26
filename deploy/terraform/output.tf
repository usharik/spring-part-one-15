output "server_public_ip" {
  value = aws_instance.test-ec2-instance.public_ip
}

output "database_url" {
  value = aws_db_instance.mysql_db.endpoint
}

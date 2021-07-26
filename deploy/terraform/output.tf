output "server_public_ip" {
  value = aws_eip.ip-test-env.public_ip
}

output "database_url" {
  value = aws_db_instance.mysql_db.endpoint
}

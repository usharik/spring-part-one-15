data "aws_ami" "ubuntu" {
  most_recent = true

  filter {
    name   = "name"
    values = ["ubuntu/images/hvm-ssd/ubuntu-focal-20.04-amd64-server-*"]
  }

  filter {
    name   = "virtualization-type"
    values = ["hvm"]
  }

  owners = ["099720109477"]
}

resource "tls_private_key" "example" {
  algorithm = "RSA"
  rsa_bits  = 4096
}

resource "aws_key_pair" "generated_key" {
  key_name   = var.ami_key_pair_name
  public_key = tls_private_key.example.public_key_openssh
}

resource "aws_instance" "test-ec2-instance" {
  ami = data.aws_ami.ubuntu.id
  instance_type = "t3.micro"
  key_name = var.ami_key_pair_name
  security_groups = [aws_security_group.ingress-all-test.id]
  tags = {
    Name = var.ami_name
  }
  subnet_id = aws_subnet.subnet-servers.id
}

resource "aws_eip" "ip-test-env" {
  instance = aws_instance.test-ec2-instance.id
  vpc      = true

  provisioner "remote-exec" {
    inline = [
      "(sudo apt update && sudo apt -y install mysql-client) || true"
    ]
    connection {
      type="ssh"
      user="ubuntu"
      private_key = tls_private_key.example.private_key_pem
      host=aws_eip.ip-test-env.public_ip
    }
  }
}

resource "aws_db_instance" "mysql_db" {
  allocated_storage    = 10
  engine               = "mysql"
  engine_version       = "8.0.25"
  instance_class       = "db.t3.micro"
  name                 = "mydb"
  username             = "my_user"
  password             = "my_user_password"
  skip_final_snapshot  = true
  db_subnet_group_name      = aws_db_subnet_group.mysql_subnets.id
  vpc_security_group_ids    = [aws_security_group.mysql-security-group.id]
}

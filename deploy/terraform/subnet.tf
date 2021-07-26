// Subnet for Servers
resource "aws_subnet" "subnet-servers" {
  cidr_block = cidrsubnet(aws_vpc.test-env.cidr_block, 3, 1)
  vpc_id = aws_vpc.test-env.id
  availability_zone = "us-east-1a"
}

resource "aws_route_table" "route-table-test-env" {
  vpc_id = aws_vpc.test-env.id
  route {
    cidr_block = "0.0.0.0/0"
    gateway_id = aws_internet_gateway.test-env-gw.id
  }
  tags = {
    Name = "test-env-route-table"
  }
}

resource "aws_route_table_association" "subnet-association" {
  subnet_id      = aws_subnet.subnet-servers.id
  route_table_id = aws_route_table.route-table-test-env.id
}

// Subnets for Databases
data "aws_availability_zones" "available" {
  state = "available"
}

resource "aws_subnet" "subnet-databases" {
  count = 2
  cidr_block = cidrsubnet(aws_vpc.test-env.cidr_block, 3, 2 + count.index + 1)
  vpc_id = aws_vpc.test-env.id
  availability_zone = element(data.aws_availability_zones.available.names, count.index)
}

resource "aws_db_subnet_group" "mysql_subnets" {
  name        = "db-subnet-group"
  description = "Terraform example RDS subnet group"
  subnet_ids  = aws_subnet.subnet-databases.*.id
}

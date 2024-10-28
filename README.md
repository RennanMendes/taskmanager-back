
Postgres docker

``docker run --name postgres_container -e POSTGRES_USER=postgres -e POSTGRES_PASSWORD=postgres -e POSTGRES_DB=db_taskmanager -p 5431:5431 -d postgres:latest``

Redis

``docker run --name my-redis-senha -p 6379:6379 -d redis redis-server --requirepass "1234"``

docker compose down
git pull
./mvnw clean install
docker compose up -d --build

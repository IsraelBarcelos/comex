mvn clean package


docker build -t alura/comex .
docker-compose up -d

docker login --username=_ --password=$authToken registry.heroku.com
docker image tag alura/comex registry.heroku.com/comex-alura-docker/web:1
docker push registry.heroku.com/comex-alura-docker/web:1
$authToken=$(heroku auth:token)
$imageId=$(docker image inspect registry.heroku.com/comex-alura-docker/web:1 -f "{{.Id}}")

# deploy_on_heroku:
# 	@ curl -X PATCH \
# 					-H "Authorization: Bearer $(heroku auth:token)" \
# 					-H "Content-Type: application/json" \
# 					-H "Accept: application/vnd.heroku+json; version=3.docker-releases" \
# 					-d '{ "updates": [{ "type": "web", "docker_image": "$$(IMAGE_ID)" }] }' \
# 					https://api.heroku.com/apps/comex-alura-docker/formation



curl -X PATCH -H "Content-Type: application/json" -H "Accept: application/vnd.heroku+json; version=3.docker-releases" -H "Authorization: Bearer $authToken" -d "{\"updates\": {\"type\": \"web\", \"docker_image\": \"$imageId\"}}" https://api.heroku.com/apps/comex-alura-docker/formation
#	@ curl -X PATCH -H "Content-Type: application/json" -H "Accept: application/vnd.heroku+json; version=3.docker-releases" -H "Authorization: Bearer $$(heroku auth:token)" -d "{\"updates\": {\"type\": \"web\", \"docker_image\": ${IMAGE_ID}}}" https://api.heroku.com/apps/comex-alura-docker/formation
# URL FUNCIONAL	curl -X PATCH -H "Content-Type: application/json" -H "Accept: application/vnd.heroku+json; version=3.docker-releases" -H "Authorization: Bearer f6c88c87-545f-4cce-8e49-0d40a4aed8ca" -d "{\"updates\": [{\"type\": \"web\", \"docker_image\":\"sha256:5773e606fff7f665dd17cabc19e5aae03902993b0a0f6c98f3c8275cdfdb39fe\"}]}" https://api.heroku.com/apps/comex-alura-docker/formation
	

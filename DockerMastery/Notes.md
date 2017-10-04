# My docker tips
- Please Pin Your Docker Image Versions. It will save us ton of time in the future. [Link](https://nickjanetakis.com/blog/docker-tip-18-please-pin-your-docker-image-versions)
- Simple dockerfile for a nodejs app. [Link](https://developer.okta.com/blog/2017/08/28/developers-guide-to-docker-part-2#get-your-node-app-into-the-image)

# Section 2: Creating and Using Containers Like a Boss
## Starting a Nginx web server
__Showing docker info__  
docker info  

__Download image from docker hub and then start it__  
docker container run --publish 80:80 nginx  
__Run in background__  
docker container run --publish 80:80 --detach nginx  
__List the running container__  
docker container ls  
__Stop the specific container by its id__  
docker container stop e40116230261  
__List all container that included the hidden (stoped) containers__  
docker container ls -a  
__Set name for new running container (If not, the name maybe random)__   
docker container run --publish 80:80 --detach --name webhost nginx  
__Show running logs of running container__   
docker container logs webhost  
__Show running processes of a running container__  
docker container top webhost  
__Show all sub-command for "container" command__  
docker container --help  
__Remove container by it id (prefix of the id is affected as well)__  
docker container rm cb9 034  
__Use option -f to "force" remove the running container__  
docker container rm -f df9  

## Container vs VM
__Container is just a process__  

__We can stop container by its name__  
docker stop mongo = docker container stop `id_of_container`  
docker top mongo = docker container top mongo  
__Start a existed container__  
docker start mongo  
__container ls = ps__  
docker container ls = docker ps  

## Assignment multiples containers
- Example run both: nginx (proxy), mysql (database), httpd (web server)
- Ports: nginx 80:80, httpd: 8080:80, mysql: 3036:3306

__Start mysql, map default mysql port to host's port 3036, send parameter to it__  
docker run --name mysql -p 3036:3306 -e MYSQL_RANDOM_ROOT_PASSWORD=yes -d mysql  
__Start nginx__  
docker container run --publish 80:80 --name nginx --detach nginx  
__Start httpd__  
docker container run --publish 8080:80 --name httpd --detach httpd  
__Show logs of running containers__  
docker logs mysql  
docker logs httpd  
docker logs nginx  
__Make sure all containers run well__  
curl localhost  
curl localhost:8080  
__Stop them all__  
docker container stop nginx httpd mysql  
__List all docker images__  
docker image ls  

## Container CLI explaination
__List the processes in one container__  
docker top `container_name`  
docker container top `container_name`  
__Show details of one container config__  
docker container inspect `container_name`  
docker inspect `container_name`  
__Show performance stats of all containers__  
docker container stats  
docker stats  
__Clean up container after it stop. Using `--rm` option__  
docker run --rm -it --name my_centos centos  

## Getting a shell inside containers
__No ssh needed. Docker cli can do it__  

__Run docker and access bash shell on this container__  
  -t: enable pseudo tty  
  -i: Keep STDIN open even if not attached  
  docker run --help for more information  
docker run -it nginx bash  
__Start an existed container and access it shell inside__  
  -a: attach containers STDOUT/STDERROR  
  -i: attach containers STDIN  
docker start -ai ubuntu  
__Exec additional commands in a running container__  
  -d: detach (run command in background)  
  -e: run command line with environment variable  
  -i: attach container's STDIN  
  -t: allocate new pseudo tty  
docker exec -ai mongo bash  

## Docker networks: concept for public and private networks
__Run existed container__  
docker start mysql  
__Check port mapping for this container__  
docker port mysql = docker container port mysql  

__Each docker container when running it connected to a private virtual network "bridge"__  

__Show ip of container__  
docker inspect --format '{{ .NetworkSettings.IPAddress }}' mysql  

## Client management of the virtual network hosts
__Show networks__  
docker network ls  
__Inspect a network__  
docker network inspect  
__Create a network__  
docker network create --driver `driver_name` `network_name`  
__Attach network to a container__  
docker network connect `network_name` `container_name`  
__Detach a network from container__  
docker network disconnect `network_name` `container_name`  

## How container find each other
_Docker DNS: docker daemon has a built in DNS server that containers use by default_  
__Create two new containers and attach them into the same network `my_app_net`__  
docker run -d --name new_nginx --network my_app_net nginx  
docker run -d --name my_nginx --network my_app_net nginx  
docker network inspect my_app_net  
__Then now they can ping to each other using the name of container. The DNS take the rest job__  
docker exec -it my_nginx ping new_nginx  
docker exec -it new_nginx ping my_nginx  

## DNS Round robin test
docker run -d --network dude --net-alias search elasticsearch:2  
docker run -d --network dude --net-alias search elasticsearch:2  
docker run --rm --net dude alpine nslookup search  
docker run --rm --net dude centos curl -s search:9200  
docker run --rm --net dude centos curl -s search:9200  
docker rm -f f91 bfe  

# Section 3: Container Images, Where To Find Them and How To Build Them
## Images and their layers
__List all docker images__  
docker images ls  
__Show layers of changes made in images__  
docker history `image name`
### Conclusions
Images are made up of file system changes and metadata  

Each layer is uniqly identified and only stored once on a host  

A container is just a singer read/write layer on top of image  

## Image tagging and pushing to docker hub
__Set tag for an existing image__  
docker image tag `mongo` `mongo:test_tag`  
__Log in docker hub__  
docker login  
__Log out docker hub__  
docker logout  
__Docker push images to docker hub__  
docker push `image_name:image_tag`  
__Can add alot of tags for one imageID__  

## Building images
__Build image from the docker files__  
docker image build -t customnginx `docker file folder`

## Extending official image
__Create new iamge from official nginx image__  
docker image build -t nginx-with-html .  
__Re run and test it__  
docker container run -p 80:80 --rm nginx-with-html  
__Change image name to push to the hub__  
docker image tag nginx-with-html:latest tulu1688/nginx-with-html:latest  
__Commit the image to docker hub__  
docker push tulu1688/nginx-with-html  

# Section 4: Container lifetime and Persistent Data
## Data volume
__Clean up unused volumes__  
docker volume prune  
__Run a new mysql docker container__  
docker run -p 3036:3306 -d -name mysql mysql  
__Show detail of the docker image named `mysql`__  
The mounts section of inspection describe the source and destination of mouted volume  
__Show all docker volumes__  
docker volume ls  
__Inspect volume to see detail__  
docker inspect volume `volume name`  
__Create container with setup volume name__  
docker run -p 3036:3306 -d --name mysql _-v mysql-db:/var/lib/mysql_ mysql  
## Bind mounting
__Run a container and mount the workdir__  
docker container run -d --name nginx -p 80:80 -v $(pwd):/usr/share/nginx/html nginx  
## Example: update database posgresql
__Install psql 9.6.1 container and stop it. Install another psql but it's version is 9.6.2 and keep the data of the old version__  
* Step 1: docker container run -d --name psql -v __psql:/var/lib/postgresql/data__ postgres:9.6.1  
* Step 2: docker stop psql  
* Step 3: docker container run -d --name psql1 -v __psql:/var/lib/postgresql/data__ postgres:9.6.2  
## Exmaple: Jekyll serve
Tool to change our text to blog form: Jekyll [link](jekyllrb.com)  
__Install Jekyll-serve docker__  
docker run -p 80:4000 -v $(pwd):/site bretfisher/jekyll-serve  

# Section 5: Making It Easier with Docker Compose: The Multi-Container Tool
## Docker Compose
- Compose configure relationship between containers  
- Save docker containers run settings in easy-to-read file  
- Create one-liner developer environment startups  
- Comprise of 2 parts:
  - YAML file: containers, networks, volumes  
  - docker-compose: CLI tool  

- Docker compose commands:  
  - docker-compose up  
  - docker-compose down  
  - docker-compose up -d  : run in backgroud
  - docker-compose down -v : remove all volumes
  - docker-compose down --help : for more details

Documentation for compose [link](docs.docker.com/compose/compose-file/)  

## Adding image to build compose file

# Section 6: Swarm, build-in orchestration
Swarm mode is a clustering solution built inside Docker  
## Basic example
__Run docker show info to know swarm is active or not__  
docker info  
__Init swarm if swarm mode is inactive__  
docker swarm init  
`new master node will be created`
__Show created nodes__  
docker node ls  
__Create a service__  
docker service create alpine ping 8.8.8.8  
__List the service__  
docker service ls  
__Show container of service__  
docker service ps `service_name`  
__Replicas service to 3__  
docker service update `service_name` --replicas 3  
docker service update `service_id` --replicas 3  
__Remove docker service__  
docker service rm `service_name`  
docker service rm `service_id`  
_notes:_ after remove docker services, the related containers will be deleted also  
## Creating 3-node swarm
__host options__  
1. Play with docker: Use [play with docker](http://labs.play-with-docker.com/) to get free docker host in 4 hours  
Play-with-docker provides share session feature  
[Training](training.play-with-docker.com)  
2. Install docker machine to local  
> docker-machine create node1 --> to create a node  
> docker-machine ssh node1 --> go to node1
> docker-machine env node1

__Example__  
docker swarm init --advertise-addr 10.0.32.3 -> `init the master node and get the token string`  

docker swarm join --token SWMTKN-1-1wysaoumeyswkm37i9wrxk6k84w750j2yqxcqt5lt1wdjsktxo-53de1pur9egtbg9wcbtoqqwzi 10.0.32.3:2377  -> `join master as a worker`  

docker node update role manager node2 -> `update node2 to be the new manager`

docker swarm join-token manager -> `generate join-token in master node and assign new node in manager mode`  

docker swarm join --token SWMTKN-1-1wysaoumeyswkm37i9wrxk6k84w750j2yqxcqt5lt1wdjsktxo-c4tz60cp8qdb6cj7r6b4mp4sv 10.0.32.3:2377 -> `join new node as a manager`  

docker service create --replicas 3 alpine ping 8.8.8.8 -> `run services with 3 replicas in all manager nodes`

docker service ls --> `show docker services`  

## Overlay Multi-Host Networking
__Setup postgres image__  
docker network create --driver overlay mydrupal  -> `Create new docker overlay network`  
docker service create --name psql --network mydrupal -e POSTGRES_PASSWORD=mypass postgres  -> `Create new docker service using the created network above`  
docker service ls  
docker service ps psql  
docker container logs psql.1.8921unkgsf7ciwkzib58t9mah  -> `Show logs of docker container attached with docker service`  
__Setup drupal image__  
docker service create --name drupal --network mydrupal -p 80:80 drupal -> `Create new service name drupal with same network`  
docker service ps drupal  
__=> Now we have a drupal website run load balancing__

## Create multi-service app sample
docker network create -d overlay backend  
docker network create -d overlay frontend  
docker service create --name vote -p 80:80 --network frontend --replicas 2 dockersamples/examplevotingapp_vote:before  
docker service create --name redis --network frontend --replicas 1 redis:3.2    
docker service create --name worker --network frontend --network backend dockersamples/examplevotingapp_worker  
docker service create --name db --network backend --mount type=volume,source=/db-data,target=/var/lib/postgresql/data postgres:9.4  
docker service create --name result --network backend -p 5001:80 dockersamples/examplevotingapp_result:before  

## Stacks: Production Grade Compose
- Stacks accepts compose files as their declarative definition for services, networks, and volumes  
- use `docker stack deploy` rather than `docker service create`  

> docker stack deploy -c example.yml `stack_name` -> Create stack  
> docker stack ls -> show all stacks  
> docker stack ps `stack_name`  -> show stack info by images  
> docker stack service `stack_name` -> show stack info by services  
> docker stack deploy -c example.yml `stack_name` -> Call again to update stack  

## Swarm secrets: securely store config vars
> docker secret create psql_user psql_user.txt  

> echo "myDBPassword" | docker secret create -  -> read password from standard input  

> docker secret ls  

> docker service create --name psql --secret psql_user --secret psql_pass -e POSTGRES_PASSWORD_FILE=/run/secrets/psql_pass -e POSTGRES_USER_FILE=/run/secrets/psql_user postgres   

> docker exec -it `container_name` bash -> go to bash shell of container of above service  

> ls /run/secrets/ -> show secrets folder inside container shell  

> cat /run/secrets/psql_user -> display psql_user  

## Building stacks with linked secrets

## Building secret with local docker compose

# Section 7: Image storage and distribution
__DockerHub__ allow to automatically create image from code after commit. DockerHub like a github for images  
__DockerStore__ like an app store for images. Some production images are sole here  
__DockerCloud__ web-based Docker Swarm creation/management. Automated image building, testing, and deployment. Includes a image security scanning service.  
__Docker Registry__
> docker container run -d -p 5000:5000 --name registry registry  
> docker pull hello-world  
> docker run hello-world
> docker tag hello-world 127.0.0.1:5000/hello-world `retag image`  
> docker push 127.0.0.1:5000/hello-world `push image to new registry`  
> docker image remove hello-world  
> docker image rm 127.0.0.1:5000/hello-world  
> docker pull 127.0.0.1:5000/hello-world `pull image from new registry`  
> docker run 127.0.0.1:5000/hello-world:latest  
> docker container kill registry  
> docker container rm registry  
> docker container run -d -p 5000:5000 --name registry -v $(pwd)/registry-data:/var/lib/registry registry `re-create registry using a bind mount and see how it stores data`  
> docker push 127.0.0.1:5000/hello-world `push the image to registry`  
> tree registry-data/ `show the image stored in registry`  
> docker system prune `clean up them all`  

# Section 8: References
* Journey to Docker Production  [link](https://www.youtube.com/watch?v=ZdUcKtg84T8)
* Swarm Quorum and Recovery [link](https://www.youtube.com/watch?v=Qsv-q8WbIZY)
* Bret's Docker and DevOps Newsletter [link](www.bretfisher.com/newsletter)
* Using Prune to Keep Your Docker System Clean [link](https://www.youtube.com/watch?v=_4QzP7uwtvI&feature=youtu.be)
* What's New In Docker 17.06[link](https://www.youtube.com/watch?v=-NeaXUGEK_g)

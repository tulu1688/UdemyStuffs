# Section 2
## Starting a Nginx web server
docker info
docker
--> Download image from docker hub and then start it
docker container run --publish 80:80 nginx
--> Run in background
docker container run --publish 80:80 --detach nginx
--> List the running container
docker container ls
--> Stop the specific container by its id
docker container stop e40116230261
--> List all container that included the hidden (stoped) containers
docker container ls -a
--> Set name for new runing container (If not, the name maybe random)
docker container run --publish 80:80 --detach --name webhost nginx
--> Show running logs of running container
docker container logs webhost
--> Show running processes of a running container
docker container top webhost
--> Show all sub-command for "container" command
docker container --help
--> Remove container by it id (prefix of the id is affected as well)
docker container rm cb9 034
--> Use option -f to "force" remove the running container
docker container rm -f df9

## Container vs VM
- Container is just a process

--> We can stop container by its name
docker stop mongo = docker container stop `id_of_container`
docker top mongo = docker container top mongo
--> Start a existed container
docker start mongo
--> container ls = ps
docker container ls = docker ps

## Assignment multiples containers
- Example run both: nginx (proxy), mysql (database), httpd (web server)
- Ports: nginx 80:80, httpd: 8080:80, mysql: 3036:3306

--> Start mysql, map default mysql port to host's port 3036, send parameter to it
docker run --name mysql -p 3036:3306 -e MYSQL_RANDOM_ROOT_PASSWORD=yes -d mysql
--> Start nginx
docker container run --publish 80:80 --name nginx --detach nginx
--> Start httpd
docker container run --publish 8080:80 --name httpd --detach httpd
--> Show logs of running containers
docker logs mysql
docker logs httpd
docker logs nginx
--> Make sure all containers run well
curl localhost
curl localhost:8080
--> Stop them all
docker container stop nginx httpd mysql
--> List all docker images
docker image ls

## Container CLI explaination
- List the processes in one container
docker top `container_name`
docker container top `container_name`
- Show details of one container config
docker container inspect `container_name`
docker inspect `container_name`
- Show performance stats of all containers
docker container stats
docker stats
- Clean up container after it stop. Using `--rm` option
docker run --rm -it --name my_centos centos

## Getting a shell inside containers
- No ssh needed. Docker cli can do it
--> Run docker and access bash shell on this container
  -t: enable pseudo tty
  -i: Keep STDIN open even if not attached
  docker run --help for more information
docker run -it nginx bash
--> Start an existed container and access it shell inside
  -a: attach containers STDOUT/STDERROR
  -i: attach containers STDIN
docker start -ai ubuntu
--> Exec additional commands in a running container
  -d: detach (run command in background)
  -e: run command line with environment variable
  -i: attach container's STDIN
  -t: allocate new pseudo tty
docker exec -ai mongo bash

## Docker networks: concept for public and private networks
--> Run existed container
docker start mysql
--> Check port mapping for this container
docker port mysql = docker container port mysql
- Each docker container when running it connected to a private virtual network "bridge"
--> Show ip of container
docker inspect --format '{{ .NetworkSettings.IPAddress }}' mysql

## Client management of the virtual network hosts
--> Show networks
docker network ls
--> Inspect a network
docker network inspect
--> Create a network
docker network create --driver `driver_name` `network_name`
--> Attach network to a container
docker network connect `network_name` `container_name`
--> Detach a network from container
docker network disconnect `network_name` `container_name`

## How container find each other
- Docker DNS: docker daemon has a built in DNS server that containers use by default
--> Create two new containers and attach them into the same network `my_app_net`
docker run -d --name new_nginx --network my_app_net nginx
docker run -d --name my_nginx --network my_app_net nginx
docker network inspect my_app_net
--> Then now they can ping to each other using the name of container. The DNS take the rest job
docker exec -it my_nginx ping new_nginx
docker exec -it new_nginx ping my_nginx

## DNS Round robin test
docker run -d --network dude --net-alias search elasticsearch:2
docker run -d --network dude --net-alias search elasticsearch:2
docker run --rm --net dude alpine nslookup search
docker run --rm --net dude centos curl -s search:9200
docker run --rm --net dude centos curl -s search:9200
docker rm -f f91 bfe

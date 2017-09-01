# Introduction to Kubernetes
- Kubernetes is an open source orchestration system for Docker container:
+ It let we schedule containers on a cluster of machines
+ Can run multiple containers on one machine
+ Kubernetes will manage the state of these containers
- Kubernetes likes Docker Swarm, Mesos

## Kubernetes setup
- Kubernetes should be really run anywhere
- But more features only on certain Cloud Providers
+ Volume, External Load Balancer work only with supported Cloud Providers
- `Minikube` help us easily to spin up a local single machine with a Kubernetes cluster
- `Kops` is a tool used to spin up a highly available production cluster

## Setup local Kubernetes with Minikube
- `Minikube` is a tool that makes it easy to run Kubernetes locally
- `Minikube` runs a single-node Kubernetes cluster inside a Linux VM
- It's only for testing and development. It cannot spin up a production cluster. It's a one node machine with no high availability
- It's works on Windows, Linux, MacOS
- We have to use Virtualization Software to create VM to run minikube. [Link for Minikube](https://github.com/kubernetes/minikube)
- To launch simple cluster: `minikube start`

> curl -Lo minikube https://storage.googleapis.com/minikube/releases/v0.19.1/minikube-darwin-amd64 && chmod +x minikube && sudo mv minikube /usr/local/bin/  

> minikube start  

> cat ~/.kube/config  

> curl -LO https://storage.googleapis.com/kubernetes-release/release/$(curl -s https://storage.googleapis.com/kubernetes-release/release/stable.txt)/bin/darwin/amd64/kubectl -> `install kubectl`  

> chmod +x kubectl && sudo mv kubectl /usr/local/bin/ -> `save downloaded kubectl in /usr/local/bin`  

> kubectl run hello-minikube --image=gcr.io/google_containers/echoserver:1.4 --port=8080  

> minikube service hello-minikube --url  

> minikube stop  

# SC Fleets

## Setup

### Build Locally 
Build docker image
```bash
docker build . -t scfleetsapi:latest
```
Tag docker image with registry name
```bash
docker tag scfleetsapi:latest registry.digitalocean.com/scfleets/scfleetsapi:latest
```
Init Digital Ocean Auth 
```bash
doctl auth init --context two-guys
```
Login to Digital Ocean Registry
```bash
doctl registry login --context two-guys
```
Push to docker registry
```bash
docker push registry.digitalocean.com/scfleets/scfleetsapi:latest
```
### Build Locally No Docker
Make sure you can run ./gradlew
```bash
chmod 777 ./gradlew
```
Run Build
```bash
./gradw build
```
---
## Links
- [Docker Registry](https://cloud.digitalocean.com/registry?i=1052a3)
- [Apps](https://cloud.digitalocean.com/apps?i=1052a3)
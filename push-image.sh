#!/bin/zsh
#docker login --username=dingyue_cloudnative registry.cn-beijing.aliyuncs.com
version=$1
repo_domain=second-instance-registry-vpc.cn-beijing.cr.aliyuncs.com
repo_ns=first_ns
imageName=k8s-web
mvn clean package -Dmaven.test.skip=true
docker build -t $imageName:$version ./
ImageId=`docker build -t $imageName:v1 ./ |grep "Successfully built"|awk -F" " '{print $3}'`
docker tag $ImageId $repo_domain/$repo_ns/$imageName:$version
docker push $repo_domain/$repo_ns/$imageName:$version

date

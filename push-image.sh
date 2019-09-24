#!/bin/zsh
#docker login --username=dingyue_cloudnative registry.cn-beijing.aliyuncs.com
version=$1
imageName=k8s-web
docker build -t $imageName:$version ./
ImageId=`docker build -t $imageName:v1 ./ |grep "Successfully built"|awk -F" " '{print $3}'`
docker tag $ImageId registry.cn-beijing.aliyuncs.com/k8s-demo-dingyue/$imageName:$version
docker push registry.cn-beijing.aliyuncs.com/k8s-demo-dingyue/$imageName:$version

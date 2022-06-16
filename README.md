# k8s-demo-springcloud-web

1. start.spring.io 创建一个空的项目
2. derrick init 命令生成对应的dockfile deploy等信息
derrick 生成的文件非常多，包括
docker-compose.xml Jekinsfile kubernetes-deployment.yaml

# 项目运行环境
* jdk8

# 更新与发布项目

最优解是通过ci/cd来进行
当前项目写了一个简单的push-image脚本来推送镜像到镜像仓库
`./push-image.sh v9`
只有一个参数是版本号，然后在k8s控制台就可以随意操作这个镜像
本地运行命令的前提是先通过docker命令登录到对应的registy
同时确保本地的docker仓库是启动的

# 本地运行
mvn clean package -Dmaven.test.skip=true
docker-compose build
docker-compose up

# 更新
1. `push-image.sh v9`
2. `k apply -f deploy.yaml`

# feature
1. arms 已集成
2. ahas线路 已集成
3. 数据库 已集成
4. ingress 无
5. 优雅上下线 无 -- 待完成
6. test

# touch 4 ci
touch 1

# k8s-demo-springcloud-web

1. start.spring.io 创建一个空的项目
2. derrick init 命令生成对应的dockfile deploy等信息

# 更新与发布项目

最优解是通过ci/cd来进行
当前项目写了一个简单的push-image脚本来推送镜像到镜像仓库
`./push-image.sh v9`
只有一个参数是版本号，然后在k8s控制台就可以随意操作这个镜像
本地运行命令的前提是先通过docker命令登录到对应的registy

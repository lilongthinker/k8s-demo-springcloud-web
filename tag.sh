#!/bin/zsh
tag=$1
git tag release-$tag
git push origin release-$tag

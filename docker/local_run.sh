#!/bin/bash

#change dir
cd `dirname $0`
if [[ `pwd` == *\/docker ]]; then
    # if is docker folder, go to father folder
    cd ..
fi
# echo "$PWD"

usage (){
    echo "USAGE: run.sh git_branch"
    echo "Notice: 1. if set git_branch, will pull the newest source from git."
    echo ""
}
usage


git_branch=$1

#update source
if [ ! -n "$git_branch" ]; then
    git_branch="master"
else
    git checkout "$git_branch" || echo 'you need install GIT at first, or check the error output.'
    git pull
fi

#build
cd docker
bash build_prod.sh

#build docker
bash build_image.sh

#run
bash local_start.sh


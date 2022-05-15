#!/bin/bash

git config user.name github-actions[bot]
git config user.email 41898282+github-actions[bot]@users.noreply.github.com
git fetch origin gh-pages
git reset --hard
git checkout origin/gh-pages

rm -rf .

if [ ! -d "docs" ]; then
  mkdir docs
fi;

for dir in ./*
do
  rm -rf "./docs/${dir/.\//}"
done

for dir in ./*
do
  if [ "$dir" == "./docs" ]; then
    continue
  fi

  if [ -d "$dir" ]; then
    if [ ! -f "./docs/$dir" ]
    then
        mkdir "./docs/${dir/.\//}"
    fi
    cp -Rfv "$dir"/target/apidocs/* ./docs/"${dir/.\//}"/
  fi
done

for dir in ./premium/*
do
  if [ -d "$dir" ]; then
    if [ ! -f "./docs/$dir" ]
    then
        mkdir "./docs/${dir/.\//}"
    fi
    cp -Rfv "$dir"/target/apidocs/* ./docs/"${dir/.\//}"/
  fi
done

mv ./docs/* .
git add .
git commit -m "Update InceptusGames JavaDocs"
git branch -D gh-pages
git branch -m gh-pages
git push -f origin gh-pages
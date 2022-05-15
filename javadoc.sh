#!/bin/bash

git config user.name github-actions[bot]
git config user.email 41898282+github-actions[bot]@users.noreply.github.com
git fetch origin gh-pages
git checkout origin/gh-pages

for dir in ./*
do
  rm -rf "$dir"
done

for dir in ./*
do
  if [ -d "$dir" ]; then
    if [ ! -f "$dir" ]
    then
        mkdir "$dir"
    fi
    cp -Rfv "$dir"/target/apidocs/* "$dir"/
  fi
done

for dir in ./premium/*
do
  if [ -d "$dir" ]; then
    if [ ! -f "$dir" ]
    then
        mkdir "$dir"
    fi
    cp -Rfv "$dir"/target/apidocs/* "$dir"/
  fi
done

git add .
git commit -m "Update InceptusGames JavaDocs"
git push -f origin gh-pages
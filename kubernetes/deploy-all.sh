#!/bin/bash
for dir in .
do
    for filename in $dir
    do
        print $filename #kubectl apply -f filename
    done
done

#!/bin/bash

for dir in . ; do
    for filename in $dir; do
        print $filename
    done
done
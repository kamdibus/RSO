#!/usr/bin/env bash
kubectl delete deployment consumerservice testdeploy
kubectl delete service consumer-lb testdeploy-lb
kubectl delete gateway http-gateway
kubectl delete virtualservice external-services
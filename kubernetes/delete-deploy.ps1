#!/usr/bin/env bash
kubectl delete deployment consumerservice testdeploy payment
kubectl delete service consumer-lb testdeploy-lb payment-lb
kubectl delete gateway http-gateway
kubectl delete virtualservice external-services
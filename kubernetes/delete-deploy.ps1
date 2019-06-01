#!/usr/bin/env bash
kubectl delete deployment consumer testdeploy payment frontend
kubectl delete service consumer-lb testdeploy-lb payment-lb frontend
kubectl delete gateway http-gateway
kubectl delete virtualservice external-services
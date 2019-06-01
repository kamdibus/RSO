#!/usr/bin/env bash
kubectl delete deployment consumer testdeploy payment frontend invoice
kubectl delete service consumer-lb testdeploy-lb payment-lb frontend invoice-lb
kubectl delete gateway http-gateway
kubectl delete virtualservice external-services
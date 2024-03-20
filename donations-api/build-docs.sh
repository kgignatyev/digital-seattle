#!/usr/bin/env bash
API_RELEASE=$(yq eval '.info.version' definitions/donations-svc.v1.openapi.yaml)
mkdir -p "./out"
rm -rf ./out/*
docker run --rm  \
   -v $PWD/definitions:/spec \
   -v $PWD/out:/out \
   redocly/cli \
    build-docs /spec/donations-svc.v1.openapi.yaml -o /out/donations-svc-api-docs-${API_RELEASE}.html
cp definitions/donations-svc.v1.openapi.yaml out/donations-svc-api-${API_RELEASE}.yaml

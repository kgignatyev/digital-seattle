#!/usr/bin/env bash

export API_RELEASE=$(yq  '.info.version' definitions/donations-svc.v1.openapi.yaml)
set -e
set -x

./build-docs.sh

cd ../donations-ui
./generate-api-client.sh

cd ../donations-api-server-stubs
./generate-server-stubs.sh






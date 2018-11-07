#!/usr/bin/env bash
./gradlew clean
./gradlew build
./gradlew publishToMavenLocal
./gradlew bintrayUpload

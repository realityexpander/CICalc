# CICalc
Calculator app for Bitrise project

Sample Android app created to test BitRise CI/CD functionality

## Dashboards

Bitrise: https://app.bitrise.io/dashboard

Firebase (App Distribution): https://console.firebase.google.com/u/0/project/app-distribution-redev/overview

Google Play Console: https://play.google.com/console/u/0/developers/7466162782462237210/app-list

Google Cloud: https://console.cloud.google.com/home/dashboard?project=pc-api-7466162782462237210-872

## Build Pipeline notes

1. **Push** to `*.*.*/staging` branch will trigger a *UNSIGNED DEBUG* build 
on Bitrise and deploy to Firebase App Distribution testers
2. **PR** to main branch will trigger a *SIGNED RELEASE* build on Bitrise 
and deploy to Play Store production track

_TODO_: Push to `*.*.*/staging-release` branch will trigger a *RELEASE* build on Bitrise and deploy to Firebase App Distribution


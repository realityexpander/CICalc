# CICalc
Calculator app for Bitrise project

Sample Android app created to test BitRise CI/CD functionality

## Dashboards

Bitrise: https://app.bitrise.io/dashboard

Firebase (App Distribution): https://console.firebase.google.com/u/0/project/app-distribution-redev/overview

Google Play Console: https://play.google.com/console/u/0/developers/7466162782462237210/app-list

Google Cloud: https://console.cloud.google.com/home/dashboard?project=pc-api-7466162782462237210-872

## Build Pipeline notes

1. **PR** to `dev` branch will trigger a BitRise *UNSIGNED DEBUG* APK build, Run Tests
   and deploy APK to BitRise Artifacts Only
2. **Push** to `*.*.*/staging` branch will trigger a BitRise build for an *UNSIGNED DEBUG* APK 
and deploy APK to Firebase App Distribution testers
3. **PR** from `*.*.*/release` to `main` branch will trigger a *SIGNED RELEASE* build on Bitrise and deploy to Play Store production track

Note: "Staging" Variant is not used in this project. (even though we are using a `staging` branch to deploy to Firebase App Distribution)

### Flow for a new feature:
1. Create a new `x.x.x/feature` branch from `dev` branch
2. Create a PR from new `x.x.x/feature` branch to `dev` branch 
   (debug build & test artifacts will only be available on BitRise for developers to view, download and check)
3. Merge PR to `dev` branch (and possibly delete `x.x.x/feature` branch)

### Flow for a new test release:
1. Create a new `x.x.x/staging` branch from `dev` branch
2. Modify `release-notes.txt` and `whatsnew` folder with release notes.
3. Push `x.x.x/release` branch to trigger a BitRise build for an *UNSIGNED DEBUG* APK 
   (debug build APK on BitRise and sent to Firebase App Distribution testers)

### Flow for a new production release:
1. Create a new PR from `x.x.x/release` to `main` branch 
   (this will create a new build *SIGNED RELEASE* AAB on BitRise and deploy to Play Store production track)
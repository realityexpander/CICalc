# CICalc
Calculator app for Bitrise project

Sample Android app created to test BitRise CI/CD functionality

## Dashboards

Bitrise: https://app.bitrise.io/dashboard

Firebase (App Distribution): https://console.firebase.google.com/u/0/project/app-distribution-redev/overview

Google Play Console: https://play.google.com/console/u/0/developers/7466162782462237210/app-list

Google Cloud: https://console.cloud.google.com/home/dashboard?project=pc-api-7466162782462237210-872

## Build Pipeline notes for Bitrise

1. **PR** to `dev` branch will trigger a BitRise *UNSIGNED DEBUG* APK build, Run Tests
   and deploy APK to BitRise Only under "Artifacts" tab
2. **Push** to `*.*.*/staging` branch will trigger a BitRise build for an *UNSIGNED DEBUG* APK
   and deploy APK to Firebase App Distribution testers
3. **PR** from `*.*.*/release` to `main` branch will trigger a Bitrise build for a *SIGNED RELEASE* AAB
   and deploy to Play Store Production track

Note: `staging` Variant is not used in this project.
(even though we are using a `staging`branch to deploy to Firebase App Distribution)

TODO: Use a `staging` branch for `staging` workflow, and build a *SIGNED RELEASE* AAB for
Firebase App Distribution. The `staging` variant uses a staging backend API, and the `release`
variant uses a production backend API.

Note: `dev` means `origin/dev` branch, `x.x.x/staging` means `origin/x.x.x/staging` branch, etc.

### Flow for a new feature:
1. Create a new `x.x.x/feature` branch from `dev` branch
2. Create a PR from new `x.x.x/feature` branch to `dev` branch
   (Builds DEBUG APK & tests it on BitRise, w/ artifacts only on BitRise for developers to review)
3. Merge PR to `dev` branch (and possibly delete `x.x.x/feature` branch)

### Flow for a new test release:
1. On `dev`, modify `release-notes.txt` and `whatsnew` folder with release notes for the x.x.x release.
2. Create a new `x.x.x/staging` branch from `dev` branch
3. Push `x.x.x/staging` branch to trigger a BitRise build for an *UNSIGNED DEBUG* APK
   (Builds DEBUG APK on BitRise and sent to Firebase App Distribution testers)
4. Switch back to `dev` to make changes. Until ready for another `staging` build.
5. Switch to `x.x.x/staging`
6. Select `Merge 'dev' into 'x.x.x/staging'` (from git menu), & Push `x.x.x/staging` branch.
   (Builds DEBUG APK on BitRise and sent to Firebase App Distribution testers)
7. Repeat steps 4-6 until ready to release

### Flow for a new production release:
1. Bump the `versionName` in `build.gradle` file to match the `x.x.x` version of the `x.x.x/staging` branch
2. Create a new `x.x.x/release` branch from `dev` branch
3. Create a new PR from `x.x.x/release` to `main` branch
   (Builds *SIGNED RELEASE* AAB on BitRise and deploys to Play Store production track, puts under review)
4. Merge PR to `main` branch (and possibly delete `x.x.x/staging` branch)
5. Manually Merge `main` branch to `dev` branch

Bitrise AVD Testing Results Download issue:
https://support.bitrise.io/hc/en-us/requests/35460?page=1
